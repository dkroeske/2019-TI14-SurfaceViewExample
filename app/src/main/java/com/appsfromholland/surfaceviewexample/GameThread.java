package com.appsfromholland.surfaceviewexample;

import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by dkroeske on 19/12/2016.
 */

public class GameThread extends Thread {

    private AnimatedSurfaceView surfaceView;
    private Boolean isRunning;

    public GameThread(AnimatedSurfaceView view) {
        this.surfaceView = view;
        this.isRunning = false;
    }

    public void running(boolean running) {
        this.isRunning = running;
    }

    @Override
    public void run() {
        while(isRunning) {

            // Update canvas
            Canvas canvas = surfaceView.getHolder().lockCanvas();
            if( canvas != null ) {
                synchronized (surfaceView.getHolder()) {
                    surfaceView.updateCanvas(canvas);
                }
                surfaceView.getHolder().unlockCanvasAndPost(canvas);
            }

            // Sleep
            try { sleep(1000/60); } catch (InterruptedException ex) {
                Log.e("", ex.getLocalizedMessage());
            }
        }
    }
}
