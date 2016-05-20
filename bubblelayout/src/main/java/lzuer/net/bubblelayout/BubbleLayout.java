package lzuer.net.bubblelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;

import org.w3c.dom.Attr;

/**
 * Created by chunqiang on 2016/5/20.
 */
public class BubbleLayout extends FrameLayout {

    public static float DEFAULT_STROKE_WIDTH = -1;

    private ArrowDirection mArrowDirection;
    private Bubble mBubble;

    private float mArrowWidth;
    private float mCornersRadius;
    private float mArrowHeight;
    private float mArrowPosition;
    private int mBubbleColor;
    private float mStrokeWidth;
    private int mStrokeColor;

    public BubbleLayout(Context context) {
        this(context, null, 0);
    }

    public BubbleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BubbleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.BubbleLayout);

        mArrowWidth = a.getDimension(R.styleable.BubbleLayout_bl_arrowWidth, convertDpToPixel(8, context));
        mArrowHeight = a.getDimension(R.styleable.BubbleLayout_bl_arrowHeight, convertDpToPixel(8, context));
        mCornersRadius = a.getDimension(R.styleable.BubbleLayout_bl_cornerRadius, 0);
        mArrowPosition = a.getDimension(R.styleable.BubbleLayout_bl_arrowPosition, convertDpToPixel(12, context));
        mBubbleColor = a.getColor(R.styleable.BubbleLayout_bl_bubbleColor, Color.WHITE);

        mStrokeWidth = a.getDimension(R.styleable.BubbleLayout_bl_strokeWidth, DEFAULT_STROKE_WIDTH);
        mStrokeColor = a.getColor(R.styleable.BubbleLayout_bl_strokeColor, Color.GRAY);

        int location = a.getInt(R.styleable.BubbleLayout_bl_arrowDirection, ArrowDirection.LEFT.getValue());
        mArrowDirection = ArrowDirection.fromInt(location);

        a.recycle();

        initPadding();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        initDrawable(0, getWidth(), 0, getHeight());
    }

    private void initDrawable(int left, int right, int top, int bottom) {
        if (right < left || bottom < top) return;

        RectF rectF = new RectF(left, top, right, bottom);
        mBubble = new Bubble(rectF, mArrowWidth, mCornersRadius,
                mArrowHeight, mArrowPosition, mStrokeWidth, mStrokeColor, mBubbleColor, mArrowDirection);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (mBubble != null) {
            mBubble.draw(canvas);
        }
        super.dispatchDraw(canvas);
    }

    private void initPadding() {
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        switch (mArrowDirection) {
            case LEFT:
                paddingLeft += mArrowWidth;
                break;
            case RIGHT:
                paddingRight += mArrowWidth;
                break;
            case TOP:
                paddingTop += mArrowHeight;
                break;
            case BOTTOM:
                paddingBottom += mArrowHeight;
                break;
        }

        if (mStrokeWidth > 0) {
            paddingLeft += mStrokeWidth;
            paddingRight += mStrokeWidth;
            paddingTop += mStrokeWidth;
            paddingBottom += mStrokeWidth;
        }

        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    private float convertDpToPixel(float dp, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return dp * (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

}
