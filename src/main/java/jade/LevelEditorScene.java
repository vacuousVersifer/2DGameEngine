package jade;

import components.Sprite;
import components.SpriteRenderer;
import components.SpriteSheet;
import imgui.ImGui;
import org.joml.Vector2f;
import org.joml.Vector4f;
import util.AssetPool;

public class LevelEditorScene extends Scene {

    public LevelEditorScene() {}

    @Override
    public void init() {
        loadResources();

        this.camera = new Camera(new Vector2f(-250,0));

        SpriteSheet sprites = AssetPool.getSpriteSheet("assets/images/spritesheets/marioAndGoomba.png");

        GameObject obj1 = new GameObject("Object 1", new Transform(new Vector2f(200, 400), new Vector2f(125, 125)), 2);
        obj1.addComponent(new SpriteRenderer(
                new Vector4f(1, 0, 0, 1)
        ));
        this.addGameObjectToScene(obj1);

        GameObject obj2 = new GameObject("Object 2", new Transform(new Vector2f(300, 400), new Vector2f(125, 125)), 1);
        obj2.addComponent(new SpriteRenderer(new Sprite(
                AssetPool.getTexture("assets/images/sprites/blendRed.png")
        )));
        this.addGameObjectToScene(obj2);

        this.activeGameObject = obj1;
    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");
        AssetPool.addSpriteSheet("assets/images/spritesheets/marioAndGoomba.png", new SpriteSheet(AssetPool.getTexture("assets/images/spritesheets/marioAndGoomba.png"), 16, 16, 24, 0));
    }

    @Override
    public void update(float dt) {
        for(GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();
    }

    @Override
    public void imgui() {
        ImGui.begin("Test Window");
        ImGui.text("Some random text");
        ImGui.end();
    }
}
