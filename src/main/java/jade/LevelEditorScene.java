package jade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

        Transform marioTransform = new Transform(new Vector2f(200, 400), new Vector2f(125, 125));
        int marioZIndex = 2;

        GameObject mario = new GameObject("Mario", marioTransform, marioZIndex);

        SpriteRenderer marioSpriteRenderer = new SpriteRenderer();
        Sprite marioSprite = new Sprite();
        marioSprite.setTexture(AssetPool.getTexture("assets/images/sprites/mario.png"));
        marioSpriteRenderer.setSprite(marioSprite);
        mario.addComponent(marioSpriteRenderer);

        this.addGameObjectToScene(mario);

        Transform goombaTransform = new Transform(new Vector2f(600, 400), new Vector2f(125, 125));
        int goombaZIndex = 1;

        GameObject goomba = new GameObject("Goomba", goombaTransform, goombaZIndex);

        SpriteRenderer goombaSpriteRenderer = new SpriteRenderer();
        Sprite goombaSprite = new Sprite();
        goombaSprite.setTexture(AssetPool.getTexture("assets/images/sprites/goomba.png"));
        goombaSpriteRenderer.setSprite(goombaSprite);
        goomba.addComponent(goombaSpriteRenderer);

        this.addGameObjectToScene(goomba);

        this.activeGameObject = mario;

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String serialized = gson.toJson(mario);
        GameObject obj = gson.fromJson(serialized, GameObject.class);
        System.out.println(serialized);
        System.out.println(obj);
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
    }
}
