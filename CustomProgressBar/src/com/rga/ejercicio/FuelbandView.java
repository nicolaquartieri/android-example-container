package com.rga.ejercicio;

import com.example.ejercicio.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class FuelbandView extends View {

    private Bitmap colorMeter;
    private Bitmap shadow;
    
    private Paint paint;
    private RectF oval;
    private Paint text;

    private static final float SWEEP_INC = 2;
    private float mSweep;
    
    public FuelbandView (Context context) {
        super(context);
        init(context);
    }

    public FuelbandView (Context context, AttributeSet st) { 
        super(context);
        init(context);
    }
    
    public FuelbandView (Context context, AttributeSet st, int ds) {
        super(context);
        init(context);
    }
    
    private void init(Context context) {
    	
        colorMeter = BitmapFactory.decodeResource(getResources(), R.drawable.daily_goal_color_meter);
        shadow = BitmapFactory.decodeResource(getResources(), R.drawable.daily_goal_ring_blur);
        
        paint = new Paint();
        oval = new RectF();
        
        text = new Paint();
        text.setTextSize(36);
        text.setTextAlign(Align.CENTER);
        text.setColor(Color.WHITE);
        
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "AkzidGrtskProBolCnd.otf");
        text.setTypeface(tf);		
	}
    
    @Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(colorMeter.getWidth(), colorMeter.getHeight());
	}

	@Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Matrix m = new Matrix();
        RectF src = new RectF(0, 0, colorMeter.getWidth(), colorMeter.getHeight());
        RectF dst = new RectF(0, 0, colorMeter.getWidth(), colorMeter.getHeight());
        m.setRectToRect(src, dst, ScaleToFit.CENTER);
        Shader shader = new BitmapShader(colorMeter, TileMode.CLAMP, TileMode.CLAMP);
        shader.setLocalMatrix(m);
        paint.setShader(shader);
        m.mapRect(oval, src);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mSweep += SWEEP_INC;
        if (mSweep < 360) { 
        	canvas.drawBitmap(shadow, 0, 0, paint);
            canvas.drawArc(oval, 90, mSweep, true, paint);
            canvas.drawText(String.valueOf(mSweep), colorMeter.getWidth() / 2, colorMeter.getHeight() / 2, text);
        } else {
        	mSweep = 0f;
        }
        invalidate();
    }

}