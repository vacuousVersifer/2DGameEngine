package jade;

public abstract class Component {
    protected GameObject gameObject = null;

    public abstract void update(float dt);

    public void start() {

    }

    public GameObject getGameObject() {
        return this.gameObject;
    }
}
