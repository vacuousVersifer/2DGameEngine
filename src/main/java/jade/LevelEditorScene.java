package jade;

import org.joml.Vector2f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20C;
import renderer.Shader;
import renderer.Texture;
import util.Time;

import java.awt.event.KeyEvent;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30C.glBindVertexArray;
import static org.lwjgl.opengl.GL30C.glGenVertexArrays;

public class LevelEditorScene extends Scene {
    private final float[] vertexArray = {
            // position               color                           UV Coords
            100.5f, 0.5f  , 0.0f,     1.0f, 0.0f, 0.0f, 1.0f,         1, 0, // BottomRight  Red     0
            0.5f  , 100.5f, 0.0f,     0.0f, 1.0f, 0.0f, 1.0f,         0, 1, // TopLeft      Green   1
            100.5f, 100.5f, 0.0f,     0.0f, 0.0f, 1.0f, 1.0f,         1, 1, // TopRight     Blue    2
            0.5f  , 0.5f  , 0.0f,     1.0f, 1.0f, 0.0f, 1.0f,         0, 0  // BottomLeft   Yellow  3
    };
    private final int[] elementArray = {
            2, 1, 0,    // TopRight
            0, 1, 3     // BottomLeft
    };

    private int vaoID, vboID, eboID;
    private Shader defaultShader;
    private Texture defaultTexture;

    public LevelEditorScene() {}

    @Override
    public void init() {
        this.camera = new Camera(new Vector2f());

        this.defaultShader = new Shader("assets/shaders/default.glsl");
        this.defaultShader.compile();

        this.defaultTexture = new Texture("assets/images/testImage.png");

        // Generate VBO
        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        // Create float vertices buffer
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
        vertexBuffer.put(vertexArray).flip();

        // Create VBO upload the vertex buffer
        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        // Create the indices and upload
        IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
        elementBuffer.put(elementArray).flip();

        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);

        // Add the vertex attribute pointers
        int positionSize = 3;
        int colorSize = 4;
        int UVSize = 2;

        int vertexSizeBytes = (positionSize + colorSize + UVSize) * Float.BYTES;

        glVertexAttribPointer(0, positionSize, GL_FLOAT, false, vertexSizeBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionSize * Float.BYTES);
        glEnableVertexAttribArray(1);

        glVertexAttribPointer(2, UVSize, GL_FLOAT, false, vertexSizeBytes, (positionSize + colorSize) * Float.BYTES);
        glEnableVertexAttribArray(2);
    }

    @Override
    public void update(float dt) {
        camera.position.x -= dt * 50.0f;
        defaultShader.use();

        // Upload texture to shader
        defaultShader.uploadTexture("TEX_SAMPLER", 0);
        glActiveTexture(GL_TEXTURE0);
        defaultTexture.bind();

        defaultShader.uploadMat4f("uProjection", camera.getProjectionMatrix());
        defaultShader.uploadMat4f("uView", camera.getViewMatrix());
        defaultShader.uploadFloat("uTime", Time.getTime());
        // Bind the vao
        glBindVertexArray(vaoID);

        // Enable the vertex attrib pointers
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);

        // Unbind everything
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glBindVertexArray(0);

        defaultShader.detach();
    }
}
