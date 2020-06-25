package org.ogengine3d.rengerEngine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL32.*;
import org.lwjgl.opengl.GL;
import org.ogengine3d.io.InputManager;

public class DisplayManager {
    private long window;

    private int width;
    private int height;
    private String title;

    InputManager inputManager;

    public DisplayManager(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void createDisplay() {
        if (!glfwInit()) {
            throw new RuntimeException("Failed to initialize GLFW!");
        }

        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        window = glfwCreateWindow(width, height, title, 0, 0);

        if (window == 0) {
            throw new RuntimeException("Failed to create window!");
        }

        glfwMakeContextCurrent(window);
        GL.createCapabilities();

        glfwShowWindow(window);

        inputManager = new InputManager(window);
    }

    public void updateDisplay() {
        glfwPollEvents();
        glfwSwapBuffers(window);
    }

    public void closeDisplay() {
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public boolean isCloseRequested() {
        return glfwWindowShouldClose(window);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public InputManager getInputManager() {
        return inputManager;
    }
}
