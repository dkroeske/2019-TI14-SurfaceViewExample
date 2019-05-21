package com.appsfromholland.surfaceviewexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by dkroeske on 19/12/2016.
 */

public class SimonSurfaceView extends SurfaceView {

    private SurfaceHolder surfaceHolder;
    private Bitmap bitmap;

    public SimonSurfaceView(Context context) {
        super(context);
        init();
    }

    public SimonSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimonSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

//    public SimonSurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    private void init() {

        this.surfaceHolder = getHolder();

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dk);

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                Canvas canvas = surfaceHolder.lockCanvas();
                updateCanvas(canvas);
                surfaceHolder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

            }
        });
    }

    protected void updateCanvas(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(bitmap, getWidth()/2, getHeight()/2, null);
    }

}
