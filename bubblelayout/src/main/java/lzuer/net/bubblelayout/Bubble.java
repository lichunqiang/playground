package lzuer.net.bubblelayout;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * @see https://github.com/MasayukiSuda/BubbleLayout
 */
public class Bubble {

    private RectF mRect;
    private Path mPath = new Path();
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mStrokePath;
    private Paint mStrokePaint;
    private float mArrowWidth;
    private float mArrowHeight;
    private float mCornerRadius;
    private float mArrowPosition;
    private float mStrokeWidth;

    public Bubble(RectF rect, float arrowWidth, float cornersRadius,
                  float arrowHeight, float arrowPosition, float strokeWidth,
                  int strokeColor, int bubbleColor, ArrowDirection arrowDirection) {
        this.mRect = rect;
    }

    public void draw(Canvas canvas) {
    }
}
