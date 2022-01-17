package jade;

import components.SpriteRenderer;
import components.SpriteSheet;
import org.joml.Vector2f;
import util.AssetPool;
import util.Constants;

public class LevelEditorScene extends Scene {
    private GameObject mario;
    private SpriteSheet sprites;

    public LevelEditorScene() {}

    @Override
    public void init() {
        loadResources();

        this.camera = new Camera(new Vector2f(-250,0));

        sprites = AssetPool.getSpriteSheet(Constants.DEFAULT_SPRITESHEET_PATH);

        mario = new GameObject("Mario", new Transform(new Vector2f(100, 400), new Vector2f(125, 125)));
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

    private int spriteIndex = 0;
    private float spriteFlipTimeLeft = 0.0f;

    @Override
    public void update(float dt) {
        mario.transform.getPosition().x += 150 * dt;
        spriteFlipTimeLeft -= dt;
        if(spriteFlipTimeLeft <= 0) {
            spriteFlipTimeLeft = 0.15f;
            spriteIndex++;
            if(spriteIndex > 3) {
                spriteIndex = 0;
            }
            mario.getComponent(SpriteRenderer.class).setSprite(sprites.getSprite(spriteIndex));
        }

        for(GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();
    }
}
