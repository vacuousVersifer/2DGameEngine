package jade;

import components.SpriteRenderer;
import org.joml.Vector2f;
import org.joml.Vector4f;
import renderer.Shader;
import renderer.Texture;

public class LevelEditorScene extends Scene {
    public LevelEditorScene() {}

    @Override
    public void init() {
        this.camera = new Camera(new Vector2f(0,0));

        int xOffset = 10;
        int yOffset = 10;

        float totalWidth = (float) (600 - xOffset * 2);
        float totalHeight = (float) (300 - yOffset * 2);
        float sizeX = totalWidth / 100.0f;
        float sizeY = totalHeight / 100.0f;

        float spacing = 1.0f;
        for(float i = 0; i < 100 * spacing; i += spacing) {
            for(float j = 0; j < 100 * spacing; j += spacing) {
                float xPos = xOffset + (i * sizeX);
                float yPos = yOffset + (j * sizeY);

                GameObject go = new GameObject("Obj" + i + "" + j, new Transform(new Vector2f(xPos, yPos), new Vector2f(sizeX, sizeY)));
                go.addComponent(new SpriteRenderer(new Vector4f(xPos / totalWidth, yPos / totalHeight, 1, 1)));
                this.addGameObjectToScene(go);
            }
        }
    }

    @Override
    public void update(float dt) {
        System.out.println("FPS: " + (1.0f / dt));
        for(GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();
    }
}
