package com.zj.video.trim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;


/**
 * user author: didikee
 * create time: 3/20/19 4:46 PM
 * description:
 */
public class CropAreaView extends View {
    private final RectF displayRectF = new RectF();
    private RectF rectFParams;
    private Paint paint;
    private int innerColor;
    private int outerColor;

    private Paint paintX;

    public CropAreaView(Context context) {
        super(context);
        init();
    }

    public CropAreaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CropAreaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CropAreaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        //In versions > 3.0 need to define layer Type
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        innerColor = Color.TRANSPARENT;
        outerColor = ContextCompat.getColor(getContext(), R.color.black_translucent);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintX = new Paint(Paint.ANTI_ALIAS_FLAG);
        PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        paintX.setXfermode(xfermode);
    }


    public void setCropRectF(RectF rectFParams) {
        this.rectFParams = rectFParams;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (rectFParams != null) {
            int width = getWidth();
            int height = getHeight();
            displayRectF.left = rectFParams.left * width;
            displayRectF.right = rectFParams.right * width;
            displayRectF.top = rectFParams.top * height;
            displayRectF.bottom = rectFParams.bottom * height;


            paint.setColor(outerColor);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPaint(paint);

            paintX.setColor(innerColor);
            canvas.drawRect(displayRectF, paintX);
        }
    }
}
