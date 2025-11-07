package com.fabo.mathlesson.domain;
import android.content.Context;
import android.opengl.GLSurfaceView;

// MyGLSurfaceView.java
public class MyGLSurfaceView extends GLSurfaceView {
    private final MyGLRenderer mRenderer;

    public MyGLSurfaceView(Context context) {
    //    R.id.resultChart
        super(context);
        setEGLContextClientVersion(2); // Specify OpenGL ES 2.0
        mRenderer = new MyGLRenderer();
        setRenderer(mRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY); // Only render when explicitly requested
    }
}
