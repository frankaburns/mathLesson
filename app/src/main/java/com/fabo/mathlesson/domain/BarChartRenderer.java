package com.fabo.mathlesson.domain;//
//
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class BarChartRenderer implements GLSurfaceView.Renderer {

    // Define vertices for a simple rectangle (a single bar)
    private final float[] barVertices = {
            -0.5f, -1.0f, 0.0f, // bottom left
            0.5f, -1.0f, 0.0f, // bottom right
            0.5f,  1.0f, 0.0f, // top right
            -0.5f,  1.0f, 0.0f  // top left
    };

    // Buffer for the bar's vertices
    private final FloatBuffer vertexBuffer;

    // Number of coordinate values per vertex
    static final int COORDS_PER_VERTEX = 3;

    // A simple vertex shader program
    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    // A simple fragment shader program
    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    private final int program;
    private int positionHandle;
    private int colorHandle;

    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per float

    // Bar colors
    private float[] barColor = { 0.2f, 0.709803922f, 0.898039216f, 1.0f }; // Light blue

    // Data for the bar chart
    private final float[] data = {0.8f, 0.5f, 0.9f, 0.6f};

    public BarChartRenderer(GLSurfaceView surfaceView) {
        // Initialize vertex buffer
        ByteBuffer bb = ByteBuffer.allocateDirect(barVertices.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(barVertices);
        vertexBuffer.position(0);

        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        // Create the OpenGL ES program
        program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, fragmentShader);
        GLES20.glLinkProgram(program);
    }

    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background clear color to gray
        GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 unused) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        GLES20.glUseProgram(program);

        // Get handles for shaders
        positionHandle = GLES20.glGetAttribLocation(program, "vPosition");
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);

        colorHandle = GLES20.glGetUniformLocation(program, "vColor");

        // Loop through data to draw each bar
        float barWidth = 0.2f;
        float barSpacing = 0.05f;
        float totalWidth = data.length * (barWidth + barSpacing) - barSpacing;
        float startX = -totalWidth / 2.0f;

        for (int i = 0; i < data.length; i++) {
            float barHeight = data[i] * 2.0f; // Scale data to OpenGL coords
            float barX = startX + i * (barWidth + barSpacing);

            // Set the bar's position and scale with a Model-View-Projection matrix
            // A simplified translation and scale without a matrix
            float[] tempVertices = {
                    barX - barWidth / 2f, -1.0f, 0.0f,
                    barX + barWidth / 2f, -1.0f, 0.0f,
                    barX + barWidth / 2f, -1.0f + barHeight, 0.0f,
                    barX - barWidth / 2f, -1.0f + barHeight, 0.0f
            };
            vertexBuffer.put(tempVertices);
            vertexBuffer.position(0);
            GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                    GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);

            GLES20.glUniform4fv(colorHandle, 1, barColor, 0);
            GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 4);
        }

        GLES20.glDisableVertexAttribArray(positionHandle);
    }

    // Utility method to compile a shader
    public static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }
}
