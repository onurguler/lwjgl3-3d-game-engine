package org.ogengine3d.entities;

import static org.lwjgl.glfw.GLFW.*;
import org.joml.Vector3f;
import org.ogengine3d.io.InputManager;

public class Camera {
    private InputManager inputManager;
    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch = 10;
    private float yaw;
    private float roll;

    public Camera(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    public void move() {
        if (inputManager.getKey(GLFW_KEY_W)) {
            position.y += 0.5f;
        }

        if (inputManager.getKey(GLFW_KEY_S)) {
            position.y -= 0.5f;
        }

        if (inputManager.getKey(GLFW_KEY_D)) {
            position.x += 0.5f;
        }

        if (inputManager.getKey(GLFW_KEY_A)) {
            position.x -= 0.5f;
        }

        if (inputManager.getKey(GLFW_KEY_UP)) {
            position.z -= 0.5f;
        }

        if (inputManager.getKey(GLFW_KEY_DOWN)) {
            position.z += 0.5f;
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}
