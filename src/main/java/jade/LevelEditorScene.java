package jade;

import components.Sprite;
import components.SpriteRenderer;
import components.SpriteSheet;
import org.joml.Vector2f;
import util.AssetPool;

public class LevelEditorScene extends Scene {

    public LevelEditorScene() {}

    @Override
    public void init() {
        loadResources();

        this.camera = new Camera(new Vector2f(-250,0));

        SpriteSheet sprites = AssetPool.getSpriteSheet("assets/images/spritesheets/marioAndGoomba.png");

        GameObject obj1 = new GameObject("Object 1", new Transform(new Vector2f(200, 400), new Vector2f(125, 125)), 2);
        obj1.addComponent(new SpriteRenderer(new Sprite(
                AssetPool.getTexture("assets/images/sprites/blendGreen.png")
        )));
        this.addGameObjectToScene(obj1);

        GameObject obj2 = new GameObject("Object 2", new Transform(new Vector2f(300, 400), new Vector2f(125, 125)), 1);
        obj2.addComponent(new SpriteRenderer(new Sprite(
                AssetPool.getTexture("assets/images/sprites/blendRed.png")
        )));
        this.addGameObjectToScene(obj2);
    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");
        AssetPool.addSpriteSheet("assets/images/spritesheets/marioAndGoomba.png", new SpriteSheet(AssetPool.getTexture("assets/images/spritesheets/marioAndGoomba.png"), 16, 16, 24, 0));
    }

//    private int spriteIndex = 0;
//    private float spriteFlipTimeLeft = 0.0f;

    @Override
    public void update(float dt) {
//        mario.transform.getPosition().x += 150 * dt;
//        spriteFlipTimeLeft -= dt;
//        if(spriteFlipTimeLeft <= 0) {
//            spriteFlipTimeLeft = 0.15f;
//            spriteIndex++;
//            if(spriteIndex > 3) {
//                spriteIndex = 0;
//            }
//            mario.getComponent(SpriteRenderer.class).setSprite(sprites.getSprite(spriteIndex));
//        }

        for(GameObject go : this.gameObjects) {
            go.update(dt);
        }

//        System.out.println("FPS: " + (int) (1 / dt));

        this.renderer.render();
    }
}
