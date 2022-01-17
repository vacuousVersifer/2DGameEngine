package jade;

import components.SpriteRenderer;
import org.joml.Vector2f;
import org.joml.Vector4f;
import renderer.Shader;
import renderer.Texture;
import util.AssetPool;

public class LevelEditorScene extends Scene {
    public LevelEditorScene() {}

    @Override
    public void init() {
        this.camera = new Camera(new Vector2f(-250,0));

        GameObject mario = new GameObject("Mario", new Transform(new Vector2f(100, 100), new Vector2f(125, 125)));
        mario.addComponent(new SpriteRenderer(AssetPool.getTexture("assets/images/testImage.png")));
        this.addGameObjectToScene(mario);

        GameObject goomba = new GameObject("Goomba", new Transform(new Vector2f(300, 100), new Vector2f(125, 125)));
        goomba.addComponent(new SpriteRenderer(AssetPool.getTexture("assets/images/testImage2.png")));
        this.addGameObjectToScene(goomba);

        loadResources();
    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");
    }

    @Override
    public void update(float dt) {
        //System.out.println("FPS: " + (1.0f / dt));
        for(GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();
    }
}
