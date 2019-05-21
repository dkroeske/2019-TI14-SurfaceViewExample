package com.appsfromholland.surfaceviewexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by dkroeske on 19/12/2016.
 */

public class AnimatedSurfaceView extends SurfaceView {

    private SurfaceHolder surfaceHolder;
    private Bitmap bitmap;

    int x = 0;
    int y = 0;
    int rcx = 1;
    int rcy = 1;

    GameThread gameThread;

    public AnimatedSurfaceView(Context context) {
        super(context);
        init();
    }

    public AnimatedSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnimatedSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        //
        gameThread = new GameThread(this);

        this.surfaceHolder = getHolder();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {

                // Start Thread
                gameThread.running(true);
                gameThread.start();

            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

                // Stop Thread
                gameThread.running(false);
                try {
                    gameThread.join();
                } catch (InterruptedException e) {

                }

            }
        });
    }

    protected void updateCanvas(Canvas canvas) {

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawColor(Color.LTGRAY);
        canvas.drawCircle( x, y, 50, paint);

        // Update X
        if( x >= canvas.getWidth()-25 ) { rcx *= -1; }
        x += (12 * rcx);

        // Update Y
        if( y >= canvas.getWidth()-25 ) { rcy *= -1; }
        y += (17 * rcy);

    }
}
