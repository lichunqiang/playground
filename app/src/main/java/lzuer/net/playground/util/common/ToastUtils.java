package lzuer.net.playground.util.common;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by chunqiang on 2016/5/19.
 */
public class ToastUtils {
    /**
     * Normal.
     *
     * @param context  Context
     * @param text     CharSequence
     * @param duration int
     */
    public static void show(Context context, CharSequence text, int duration) {
        Toast.makeText(context, text, duration).show();
    }

    /**
     * @param context Context
     * @param resId   int
     */
    public static void show(Context context, int resId) {
        show(context, context.getResources().getText(resId), Toast.LENGTH_LONG);
    }

    /**
     * @param context  Context
     * @param resId    int
     * @param duration int
     */
    public static void show(Context context, int resId, int duration) {
        show(context, context.getResources().getText(resId), duration);
    }

    /**
     * @param context Context
     * @param resId   int
     * @param args    Object[]
     */
    public static void show(Context context, int resId, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), Toast.LENGTH_LONG);
    }

    /**
     * @param context Context
     * @param format  String
     * @param args    Object[]
     */
    public static void show(Context context, String format, Object... args) {
        show(context, String.format(format, args), Toast.LENGTH_LONG);
    }

    /**
     * @param context  Context
     * @param resId    int
     * @param duration int
     * @param args     Object[]
     */
    public static void show(Context context, int resId, int duration, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), duration);
    }

    /**
     * @param context  Context
     * @param format   String
     * @param duration int
     * @param args     Object[]
     */
    public static void show(Context context, String format, int duration, Object... args) {
        show(context, String.format(format, args), duration);
    }
}
