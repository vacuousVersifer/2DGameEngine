package jade;

public abstract class Component {
    protected transient GameObject gameObject = null;

    public void update(float dt) {

    };

    public void start() {

    }

    public void imgui() {

    }

    public GameObject getGameObject() {
        return this.gameObject;
    }
}
