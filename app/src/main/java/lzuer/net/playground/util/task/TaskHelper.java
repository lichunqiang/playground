package lzuer.net.playground.util.task;


import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;

import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import lzuer.net.playground.util.HardReference;

public class TaskHelper {
    public static enum ConcurrencyType {
        SINGLE, MULTIPLE, PLOADER, OLOADER
    }

    private static final int MSG_TYPE_CALLBACK = 1;
    private static final int THREAD_POOL_THREADS_COUNT = 50;
    private static Executor mExecutorN = new Executor(THREAD_POOL_THREADS_COUNT);
    private static Handler mHandler = new Handler() {
        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(Message msg) {
            if (msg.what != MSG_TYPE_CALLBACK) {
                super.handleMessage(msg);
                return;
            }
            HardReference<Task> aTask = (HardReference<Task>) msg.obj;
            Task task = aTask.get();
            aTask.clear();
            if (task.isCancelled())
                return;
            try {
                task.callback(task.mError);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (Executor.mDebug)
                mExecutorN.afterExecute(task.mSeqNo, task.mError);
        }
    };

    public static abstract class Task {
        /**
         * execute() are executed in thread pool worker thread.
         * if exception thrown, the exception will be passed to callback() method.
         */
        public abstract void execute() throws Exception;

        /**
         * callback() are executed in UI thread
         *
         * @param e the exception thrown by execute() (if any), may be null
         */
        public abstract void callback(Exception e);

        protected Object mCookie;
        // debug only fields
        private static AtomicInteger mNextSeqNo = new AtomicInteger(0);
        private int mSeqNo;
        protected Future<?> mFuture = null;
        protected boolean mCancelled = false;
        protected Exception mError = null;

        public Task() {
            if (Executor.mDebug)
                mSeqNo = mNextSeqNo.incrementAndGet();
        }

        public Task(Object cookie) {
            mCookie = cookie;
            if (Executor.mDebug)
                mSeqNo = mNextSeqNo.incrementAndGet();
        }

        // called by execute/callback to check if task should be cancelled
        public final boolean isCancelled() {
            return mCancelled;
        }

        /**
         * cancel this async task.
         * note: task may already executed or executing.
         */
        public final void cancel(boolean interruptIfRunning) {
            mCancelled = true;
            // cancel task if pending or running in thread pool
            try {
                if (mFuture != null)
                    mFuture.cancel(interruptIfRunning);
            } catch (Exception e) {
            }
            // cancel task if pending for UI callback
            mHandler.removeMessages(MSG_TYPE_CALLBACK, this);
        }
    }

    public static Task exec(final Task task, final long delay) {
        return exec(ConcurrencyType.MULTIPLE, task, delay, 0, null);
    }

    public static Task exec(final Task task) {
        return exec(ConcurrencyType.MULTIPLE, task, 0, 0, null);
    }

    public static Task exec(final Task task, final long backgroundDelay, final long uiDelay) {
        return exec(ConcurrencyType.MULTIPLE, task, backgroundDelay, uiDelay, null);
    }

    public static Task exec(ConcurrencyType ct, final Task task, final long backgroundDelay, final long uiDelay, String tag) {
        final HardReference<Task> aTask = new HardReference<Task>(task);
        try {
            final Executor executor = mExecutorN;
            task.mFuture = executor.schedule(new Runnable() {
                @Override
                public void run() {
                    Task task = aTask.get();
                    // for debug
                    if (Executor.mDebug)
                        executor.beforeExecute(task.mSeqNo);
                    if (task.isCancelled())
                        return;
                    try {
                        task.execute();
                    } catch (Exception e) {
                        task.mError = e;
                    }
                    if (task.isCancelled())
                        return;
                    Message msg = mHandler.obtainMessage(MSG_TYPE_CALLBACK, aTask);
                    mHandler.sendMessageDelayed(msg, uiDelay);  // note: ignore return value here
                }
            }, backgroundDelay, task.mSeqNo, tag);
            return task;
        } catch (RejectedExecutionException e) {
            return null;
        }
    }

    private static class Executor extends ScheduledThreadPoolExecutor {
        // for debug async tasks concurrent executing internals
        private static boolean mDebug = false;
        // debug only fields
        private static SparseArray<String> mRunnables = null;

        static {
            if (mDebug)
                mRunnables = new SparseArray<String>();
        }

        public Executor(int corePoolSize) {
            super(corePoolSize);
        }

        /**
         * schedule an runnable, optionally with some delay.
         *
         * @param r     the runnable
         * @param delay delay before execute runnable
         * @param tag   for debug purpose, logger will use this tag when output traces.
         * @return
         */
        public Future<?> schedule(Runnable r, long delay, int seqNo, String tag) {
            if (mDebug && tag != null)
                mRunnables.put(seqNo, tag);
            return super.schedule(r, delay, TimeUnit.MILLISECONDS);
        }

        // for debug
        // note: we don't override base classes' beforeExecute because it don't use our "Runnable" when call us
        protected void beforeExecute(int seqNo) {
            if (!mDebug)
                return;
        }

        // for debug
        // note: we don't override base classes' beforeExecute because it don't use our "Runnable" when call us
        protected void afterExecute(int seqNo, Throwable t) {
            if (!mDebug)
                return;
            mRunnables.delete(seqNo);
        }
    }
}