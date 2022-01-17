package util;

import components.SpriteSheet;

public class Constants {
    public final static String DEFAULT_SPRITESHEET_PATH = "assets/images/spritesheet.png";
    public final static int DEFAULT_SPRITESHEET_SPRITEWIDTH = 16;
    public final static int DEFAULT_SPRITESHEET_SPRITEHEIGHT = 16;
    public final static int DEFAULT_SPRITESHEET_NUMSPRITES = 26;
    public final static int DEFAULT_SPRITESHEET_SPACING = 0;
    public final static SpriteSheet DEFAULT_SPRITESHEET= new SpriteSheet(
            AssetPool.getTexture(
            DEFAULT_SPRITESHEET_PATH),
            DEFAULT_SPRITESHEET_SPRITEWIDTH,
            DEFAULT_SPRITESHEET_SPRITEHEIGHT,
            DEFAULT_SPRITESHEET_NUMSPRITES,
            DEFAULT_SPRITESHEET_SPACING);


    public final static String DEFAULT_SHADER_PATH = "assets/shaders/default.glsl";

    public final static int MARIO_WALK_1 = 0;

    public final static int GOOMBA_1 = 15;
}
