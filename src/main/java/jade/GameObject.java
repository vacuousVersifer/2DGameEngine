package jade;

import java.util.ArrayList;
import java.util.List;

public class GameObject {
    private final String name;
    private final List<Component> components;
    private final Transform transform;
    private final int zIndex;

    public GameObject(String name) {
        this.name = name;
        this.zIndex = 0;
        this.components = new ArrayList<>();
        this.transform = new Transform();
    }

    public GameObject(String name, Transform transform, int zIndex) {
        this.name = name;
        this.zIndex = zIndex;
        this.components = new ArrayList<>();
        this.transform = transform;

    }

    public void addComponent(Component c) {
        this.components.add(c);
        c.gameObject = this;
    }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        for(Component c : components) {
            if(componentClass.isAssignableFrom(c.getClass())) {
                try {
                    return componentClass.cast(c);
                } catch (ClassCastException e) {
                    e.printStackTrace();
                    assert false : "Error: Casting component.";
                }
            }
        }

        return null;
    }

    public <T extends Component> void removeComponent(Class<T> componentClass) {
        for(int i = 0; i < components.size(); i++) {
            Component c = components.get(i);
            if(componentClass.isAssignableFrom(components.getClass())) {
                components.remove(i);
                return;
            }
        }
    }

    public void update(float dt) {
        for (Component c : components) {
            c.update(dt);
        }
    }

    public void start() {
        for (Component c : components) {
            c.start();
        }
    }

    public void imgui() {
        for(Component c : components) {
            c.imgui();
        }
    }

    public Transform getTransform() {
        return this.transform;
    }

    public int getzIndex() {
        return this.zIndex;
    }
}
