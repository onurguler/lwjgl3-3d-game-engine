package org.ogengine3d.entities;

import org.joml.Vector3f;
import org.ogengine3d.models.TexturedModel;

public class Entity {
    private TexturedModel model;

    private Vector3f position;
    private Vector3f rotation;
    private Vector3f scale;

    public Entity(TexturedModel model, Vector3f position, Vector3f rotation, Vector3f scale) {
        this.model = model;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public void addPosition(Vector3f delta) {
        position.add(delta, position);
    }

    public void addRotation(Vector3f delta) {
        rotation.add(delta, rotation);
    }

    public void addScale(Vector3f delta) {
        scale.add(delta, scale);
    }

    public TexturedModel getModel() {
        return model;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setModel(TexturedModel model) {
        this.model = model;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }
}
