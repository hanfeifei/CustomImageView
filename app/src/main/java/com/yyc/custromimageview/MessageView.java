package com.yyc.custromimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class MessageView extends ImageView {

    /**
     * 控件中的图片
     */
    private Bitmap mImage;
    /**
     * 点的宽度
     */
    private int dWidth;
    /**
     * 点的高度
     */
    private int dHeight;
    private int DEFAULT_SIZE = 35;
    Rect bitmapRect;
    int mWidth = -1;
    int mHeight = -1;

    public MessageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MessageView(Context context) {
        this(context, null);
    }

    /**
     * 初始化所特有自定义类型
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public MessageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MessageImageView);

        int n = a.getIndexCount();
        mImage = BitmapFactory.decodeResource(getResources(), a.getResourceId(R.styleable.MessageImageView_image, 0));
        dWidth = (int) a.getDimension(R.styleable.MessageImageView_dWidth, DEFAULT_SIZE);
        dHeight = (int) a.getDimension(R.styleable.MessageImageView_dHeight, DEFAULT_SIZE);
        a.recycle();
    }

    public void hideDot() {
        setImage(null);
    }

    public void showDot() {
        setImage(getResources().getDrawable(R.drawable.ic_top_dot_12));
    }

    public void setImage(Drawable drawable) {
        setImage(drawable, DEFAULT_SIZE, DEFAULT_SIZE);
    }

    public void setImage(Drawable drawable, int w, int h) {
        if (drawable == null) {
            mImage = null;
        } else {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            mImage = bitmapDrawable.getBitmap();
        }
        if (w != 0 && h != 0) {
            dHeight = Math.max(w, h);
            dWidth = Math.max(w, h);
        }
        invalidate();
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mImage == null) {
            return;
        }
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable();
        if (bitmapDrawable == null) {
            return;
        }
        int h = bitmapDrawable.getBitmap().getHeight();
        int w = bitmapDrawable.getBitmap().getWidth();
        //计算位置
        int right = w + (getWidth() - w) / 2;
        int left = right - dWidth;
        int top = (getHeight() - h) / 2;
        int bottom = top + dHeight;
        Log.e("lzw","l " + left + " top " + top + " r " + right + " b " + bottom);
        Log.e("lzw","===== " + dHeight + "  --- " + dWidth);
        bitmapRect = new Rect(left, top, right, bottom);
        canvas.drawBitmap(mImage, null, bitmapRect, null);
    }

}
