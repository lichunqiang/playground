package lzuer.net.bubblelayout;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/**
 * @see https://github.com/MasayukiSuda/BubbleLayout
 */
public class Bubble extends Drawable {

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
        this.mArrowPosition = arrowWidth;
        this.mArrowHeight = arrowHeight;
        this.mCornerRadius = cornersRadius;
        this.mArrowPosition = arrowPosition;
        this.mStrokeWidth = strokeWidth;

        mPaint.setColor(bubbleColor);

        if (strokeWidth > 0) {
            mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mStrokePaint.setColor(strokeColor);
            mStrokePath = new Path();

            initPath(arrowDirection, mPath, strokeWidth);
            initPath(arrowDirection, mStrokePath, 0);
        } else {
            initPath(arrowDirection, mPath, 0);
        }
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
    }

    @Override
    public void draw(Canvas canvas) {
        if (mStrokeWidth > 0) {
            canvas.drawPath(mStrokePath, mStrokePaint);
        }
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return (int) mRect.width();
    }

    @Override
    public int getIntrinsicHeight() {
        return (int) mRect.height();
    }

    private void initPath(ArrowDirection mArrowDirection, Path path, float strokWidth) {
        switch (mArrowDirection) {
            case LEFT:
                if (mCornerRadius <= 0) {
                    initLeftSquarePath(mRect, path, strokWidth);
                    break;
                }

                if (strokWidth > 0 && strokWidth > mCornerRadius) {
                    initLeftSquarePath(mRect, path, strokWidth);
                    break;
                }
                initLeftRoundedPath(mRect, path, strokWidth);
                break;
            case TOP:
                if (mCornerRadius <= 0) {
                    initTopSquarePath(mRect, path, strokWidth);
                    break;
                }

                if (strokWidth > 0 && strokWidth > mCornerRadius) {
                    initTopSquarePath(mRect, path, strokWidth);
                    break;
                }
                initTopRoundedPath(mRect, path, strokWidth);
                break;
            case RIGHT:
                if (mCornerRadius <= 0) {
                    initRightSquarePath(mRect, path, strokWidth);
                    break;
                }

                if (strokWidth > 0 && strokWidth > mCornerRadius) {
                    initRightSquarePath(mRect, path, strokWidth);
                    break;
                }
                initRightRoundedPath(mRect, path, strokWidth);
                break;
            case BOTTOM:
                if (mCornerRadius <= 0) {
                    initBottomSquarePath(mRect, path, strokWidth);
                    break;
                }

                if (strokWidth > 0 && strokWidth > mCornerRadius) {
                    initBottomSquarePath(mRect, path, strokWidth);
                    break;
                }
                initBottomRoundedPath(mRect, path, strokWidth);
                break;
        }
    }

    private void initLeftSquarePath(RectF rect, Path path, float strokWidth) {
        path.moveTo(mArrowWidth + rect.left + strokWidth, rect.top + strokWidth);
        path.lineTo(rect.width() - strokWidth, rect.top + strokWidth);

        path.lineTo(rect.right - strokWidth, rect.bottom - strokWidth);

        path.lineTo(rect.left + mArrowWidth + strokWidth, rect.bottom - strokWidth);

        path.lineTo(rect.left + mArrowWidth + strokWidth, mArrowHeight + mArrowPosition - (strokWidth / 2));
        path.lineTo(rect.left + strokWidth * 2, mArrowPosition + mArrowHeight / 2);
        path.lineTo(rect.left + mArrowWidth + strokWidth, mArrowPosition + (strokWidth / 2));

        path.lineTo(rect.left + mArrowWidth + strokWidth, rect.top + strokWidth);

        path.close();
    }
    private void initLeftRoundedPath(RectF rect, Path path, float strokeWidth) {

        path.moveTo(mArrowWidth + rect.left + mCornerRadius + strokeWidth, rect.top + strokeWidth);
        path.lineTo(rect.width() - mCornerRadius - strokeWidth, rect.top + strokeWidth);
        path.arcTo(new RectF(rect.right - mCornerRadius, rect.top + strokeWidth, rect.right - strokeWidth,
                mCornerRadius + rect.top), 270, 90);

        path.lineTo(rect.right - strokeWidth, rect.bottom - mCornerRadius - strokeWidth);
        path.arcTo(new RectF(rect.right - mCornerRadius, rect.bottom - mCornerRadius,
                rect.right - strokeWidth, rect.bottom - strokeWidth), 0, 90);


        path.lineTo(rect.left + mArrowWidth + mCornerRadius + strokeWidth, rect.bottom - strokeWidth);


        path.arcTo(new RectF(rect.left + mArrowWidth + strokeWidth, rect.bottom - mCornerRadius,
                mCornerRadius + rect.left + mArrowWidth, rect.bottom - strokeWidth), 90, 90);

        path.lineTo(rect.left + mArrowWidth + strokeWidth, mArrowHeight + mArrowPosition - (strokeWidth / 2));

        path.lineTo(rect.left + strokeWidth + strokeWidth, mArrowPosition + mArrowHeight / 2);


        path.lineTo(rect.left + mArrowWidth + strokeWidth, mArrowPosition + (strokeWidth / 2));

        path.lineTo(rect.left + mArrowWidth + strokeWidth, rect.top + mCornerRadius + strokeWidth);

        path.arcTo(new RectF(rect.left + mArrowWidth + strokeWidth, rect.top + strokeWidth, mCornerRadius
                + rect.left + mArrowWidth, mCornerRadius + rect.top), 180, 90);

        path.close();
    }
    private void initTopRoundedPath(RectF rect, Path path, float strokeWidth) {
        path.moveTo(rect.left + Math.min(mArrowPosition, mCornerRadius) + strokeWidth, rect.top + mArrowHeight + strokeWidth);
        path.lineTo(rect.left + mArrowPosition + (strokeWidth / 2), rect.top + mArrowHeight + strokeWidth);
        path.lineTo(rect.left + mArrowWidth / 2 + mArrowPosition, rect.top + strokeWidth + strokeWidth);
        path.lineTo(rect.left + mArrowWidth + mArrowPosition - (strokeWidth / 2), rect.top + mArrowHeight + strokeWidth);
        path.lineTo(rect.right - mCornerRadius - strokeWidth, rect.top + mArrowHeight + strokeWidth);

        path.arcTo(new RectF(rect.right - mCornerRadius,
                rect.top + mArrowHeight + strokeWidth, rect.right - strokeWidth, mCornerRadius + rect.top + mArrowHeight), 270, 90);
        path.lineTo(rect.right - strokeWidth, rect.bottom - mCornerRadius - strokeWidth);

        path.arcTo(new RectF(rect.right - mCornerRadius, rect.bottom - mCornerRadius,
                rect.right - strokeWidth, rect.bottom - strokeWidth), 0, 90);
        path.lineTo(rect.left + mCornerRadius + strokeWidth, rect.bottom - strokeWidth);

        path.arcTo(new RectF(rect.left + strokeWidth, rect.bottom - mCornerRadius,
                mCornerRadius + rect.left, rect.bottom - strokeWidth), 90, 90);

        path.lineTo(rect.left + strokeWidth, rect.top + mArrowHeight + mCornerRadius + strokeWidth);

        path.arcTo(new RectF(rect.left + strokeWidth, rect.top + mArrowHeight + strokeWidth, mCornerRadius
                + rect.left, mCornerRadius + rect.top + mArrowHeight), 180, 90);

        path.close();
    }

    private void initTopSquarePath(RectF rect, Path path, float strokeWidth) {
        path.moveTo(rect.left + mArrowPosition + strokeWidth, rect.top + mArrowHeight + strokeWidth);

        path.lineTo(rect.left + mArrowPosition + (strokeWidth / 2), rect.top + mArrowHeight + strokeWidth);
        path.lineTo(rect.left + mArrowWidth / 2 + mArrowPosition, rect.top + strokeWidth + strokeWidth);
        path.lineTo(rect.left + mArrowWidth + mArrowPosition - (strokeWidth / 2), rect.top + mArrowHeight + strokeWidth);
        path.lineTo(rect.right - strokeWidth, rect.top + mArrowHeight + strokeWidth);

        path.lineTo(rect.right - strokeWidth, rect.bottom - strokeWidth);

        path.lineTo(rect.left + strokeWidth, rect.bottom - strokeWidth);


        path.lineTo(rect.left + strokeWidth, rect.top + mArrowHeight + strokeWidth);

        path.lineTo(rect.left + mArrowPosition + strokeWidth, rect.top + mArrowHeight + strokeWidth);


        path.close();
    }


    private void initRightRoundedPath(RectF rect, Path path, float strokeWidth) {

        path.moveTo(rect.left + mCornerRadius + strokeWidth, rect.top + strokeWidth);
        path.lineTo(rect.width() - mCornerRadius - mArrowWidth - strokeWidth, rect.top + strokeWidth);
        path.arcTo(new RectF(rect.right - mCornerRadius - mArrowWidth,
                rect.top + strokeWidth, rect.right - mArrowWidth - strokeWidth, mCornerRadius + rect.top), 270, 90);

        path.lineTo(rect.right - mArrowWidth - strokeWidth, mArrowPosition + (strokeWidth / 2));
        path.lineTo(rect.right - strokeWidth - strokeWidth, mArrowPosition + mArrowHeight / 2);
        path.lineTo(rect.right - mArrowWidth - strokeWidth, mArrowPosition + mArrowHeight - (strokeWidth / 2));
        path.lineTo(rect.right - mArrowWidth - strokeWidth, rect.bottom - mCornerRadius - strokeWidth);

        path.arcTo(new RectF(rect.right - mCornerRadius - mArrowWidth, rect.bottom - mCornerRadius,
                rect.right - mArrowWidth - strokeWidth, rect.bottom - strokeWidth), 0, 90);
        path.lineTo(rect.left + mArrowWidth + strokeWidth, rect.bottom - strokeWidth);

        path.arcTo(new RectF(rect.left + strokeWidth, rect.bottom - mCornerRadius,
                mCornerRadius + rect.left, rect.bottom - strokeWidth), 90, 90);

        path.arcTo(new RectF(rect.left + strokeWidth, rect.top + strokeWidth, mCornerRadius
                + rect.left, mCornerRadius + rect.top), 180, 90);
        path.close();
    }

    private void initRightSquarePath(RectF rect, Path path, float strokeWidth) {

        path.moveTo(rect.left + strokeWidth, rect.top + strokeWidth);
        path.lineTo(rect.width() - mArrowWidth - strokeWidth, rect.top + strokeWidth);

        path.lineTo(rect.right - mArrowWidth - strokeWidth, mArrowPosition + (strokeWidth / 2));
        path.lineTo(rect.right - strokeWidth - strokeWidth, mArrowPosition + mArrowHeight / 2);
        path.lineTo(rect.right - mArrowWidth - strokeWidth, mArrowPosition + mArrowHeight - (strokeWidth / 2));

        path.lineTo(rect.right - mArrowWidth - strokeWidth, rect.bottom - strokeWidth);

        path.lineTo(rect.left + strokeWidth, rect.bottom - strokeWidth);
        path.lineTo(rect.left + strokeWidth, rect.top + strokeWidth);

        path.close();
    }


    private void initBottomRoundedPath(RectF rect, Path path, float strokeWidth) {

        path.moveTo(rect.left + mCornerRadius + strokeWidth, rect.top + strokeWidth);
        path.lineTo(rect.width() - mCornerRadius - strokeWidth, rect.top + strokeWidth);
        path.arcTo(new RectF(rect.right - mCornerRadius,
                rect.top + strokeWidth, rect.right - strokeWidth, mCornerRadius + rect.top), 270, 90);

        path.lineTo(rect.right - strokeWidth, rect.bottom - mArrowHeight - mCornerRadius - strokeWidth);
        path.arcTo(new RectF(rect.right - mCornerRadius, rect.bottom - mCornerRadius - mArrowHeight,
                rect.right - strokeWidth, rect.bottom - mArrowHeight - strokeWidth), 0, 90);

        path.lineTo(rect.left + mArrowWidth + mArrowPosition - (strokeWidth / 2), rect.bottom - mArrowHeight - strokeWidth);
        path.lineTo(rect.left + mArrowPosition + mArrowWidth / 2, rect.bottom - strokeWidth - strokeWidth);
        path.lineTo(rect.left + mArrowPosition + (strokeWidth / 2), rect.bottom - mArrowHeight - strokeWidth);
        path.lineTo(rect.left + Math.min(mCornerRadius, mArrowPosition) + strokeWidth, rect.bottom - mArrowHeight - strokeWidth);

        path.arcTo(new RectF(rect.left + strokeWidth, rect.bottom - mCornerRadius - mArrowHeight,
                mCornerRadius + rect.left, rect.bottom - mArrowHeight - strokeWidth), 90, 90);
        path.lineTo(rect.left + strokeWidth, rect.top + mCornerRadius + strokeWidth);
        path.arcTo(new RectF(rect.left + strokeWidth, rect.top + strokeWidth, mCornerRadius
                + rect.left, mCornerRadius + rect.top), 180, 90);
        path.close();
    }

    private void initBottomSquarePath(RectF rect, Path path, float strokeWidth) {

        path.moveTo(rect.left + strokeWidth, rect.top + strokeWidth);
        path.lineTo(rect.right - strokeWidth, rect.top + strokeWidth);
        path.lineTo(rect.right - strokeWidth, rect.bottom - mArrowHeight - strokeWidth);


        path.lineTo(rect.left + mArrowWidth + mArrowPosition - (strokeWidth / 2), rect.bottom - mArrowHeight - strokeWidth);
        path.lineTo(rect.left + mArrowPosition + mArrowWidth / 2, rect.bottom - strokeWidth - strokeWidth);
        path.lineTo(rect.left + mArrowPosition + (strokeWidth / 2), rect.bottom - mArrowHeight - strokeWidth);
        path.lineTo(rect.left + mArrowPosition + strokeWidth, rect.bottom - mArrowHeight - strokeWidth);


        path.lineTo(rect.left + strokeWidth, rect.bottom - mArrowHeight - strokeWidth);
        path.lineTo(rect.left + strokeWidth, rect.top + strokeWidth);
        path.close();
    }


}
