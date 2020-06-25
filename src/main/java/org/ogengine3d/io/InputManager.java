package org.ogengine3d.io;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.opengl.GL11;

public class InputManager {
    long window;

    public InputManager(long window) {
        this.window = window;
    }

    public boolean getKey(int key) {
        return glfwGetKey(window, key) == GL11.GL_TRUE;
    }
}
