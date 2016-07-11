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

public class CustomImageView extends ImageView {

    /**
     * 控件中的图片
     */
    private Bitmap mImage;
    Rect bitmapRect;
    int mWidth = -1;
    int mHeight = -1;

    public CustomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImageView(Context context) {
        this(context, null);
    }

    /**
     * 初始化所特有自定义类型
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CustomImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomImageView, defStyle, 0);

        int n = a.getIndexCount();

        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);

            switch (attr) {
                case R.styleable.CustomImageView_image:
                    mImage = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
                    break;
            }
        }
        a.recycle();
    }

    public void setImage(Drawable drawable) {
        if (drawable == null) {
            mImage = null;
        } else {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            mImage = bitmapDrawable.getBitmap();
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

        int  h = getHeight();
        int w = getWidth();

        if (mWidth!=w||mHeight!=h) {
            mWidth = w;
            mHeight = h;
            bitmapRect = new Rect(w - mImage.getWidth(), h - mImage.getHeight(), w, h);
        }
        canvas.drawBitmap(mImage, null, bitmapRect, null);
    }

}
