package org.ogengine3d;

import org.ogengine3d.rengerEngine.DisplayManager;
import org.ogengine3d.rengerEngine.Loader;
import org.ogengine3d.rengerEngine.RawModel;
import org.ogengine3d.rengerEngine.Renderer;
import org.ogengine3d.shaders.StaticShader;

public class MainGameLoop {
    public static void main(String[] args) {
        DisplayManager displayManager = new DisplayManager(1280, 700, "Our First Window");
        displayManager.createDisplay();

        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();

        float[] vertices = { // Quad vertices
                -0.5f, 0.5f, 0, // V0
                -0.5f, -0.5f, 0, // V1
                0.5f, -0.5f, 0, // V2
                0.5f, 0.5f, 0 // V3
        };

        int[] indices = { // Quad indices
                0, 1, 3, // Top left triangle (V0, V1, V3)
                3, 1, 2 // Bottom right triangle (V3, V1, V2)
        };

        RawModel model = loader.loadToVAO(vertices, indices);

        while (!displayManager.isCloseRequested()) {
            renderer.prepare();
            shader.start();
            renderer.render(model);
            shader.stop();
            displayManager.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanUp();
        displayManager.closeDisplay();
    }
}
