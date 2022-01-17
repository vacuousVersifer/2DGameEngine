package jade;

import components.Sprite;
import components.SpriteRenderer;
import components.SpriteSheet;
import org.joml.Vector2f;
import org.joml.Vector4f;
import renderer.Shader;
import renderer.Texture;
import util.AssetPool;
import util.Constants;

public class LevelEditorScene extends Scene {
    public LevelEditorScene() {}

    @Override
    public void init() {
        loadResources();

        this.camera = new Camera(new Vector2f(-250,0));

        SpriteSheet sprites = AssetPool.getSpriteSheet(Constants.DEFAULT_SPRITESHEET_PATH);

        GameObject mario = new GameObject("Mario", new Transform(new Vector2f(100, 100), new Vector2f(125, 125)));
        mario.addComponent(new SpriteRenderer(sprites.getSprite(Constants.MARIO_WALK_1)));
        this.addGameObjectToScene(mario);

        GameObject goomba = new GameObject("Goomba", new Transform(new Vector2f(300, 100), new Vector2f(125, 125)));
        goomba.addComponent(new SpriteRenderer(sprites.getSprite(Constants.GOOMBA_1)));
        this.addGameObjectToScene(goomba);


    }

    private void loadResources() {
        AssetPool.getShader(Constants.DEFAULT_SHADER_PATH);
        AssetPool.addSpriteSheet(Constants.DEFAULT_SPRITESHEET_PATH, Constants.DEFAULT_SPRITESHEET);
    }

    @Override
    public void update(float dt) {
        System.out.println("FPS: " + (1 / dt));
        for(GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();
    }
}
