package org.ogengine3d;

import org.ogengine3d.models.RawModel;
import org.ogengine3d.models.TexturedModel;
import org.ogengine3d.rengerEngine.DisplayManager;
import org.ogengine3d.rengerEngine.Loader;
import org.ogengine3d.rengerEngine.Renderer;
// import org.ogengine3d.shaders.StaticShader;
import org.ogengine3d.shaders.TexturedStaticShader;
import org.ogengine3d.textures.ModelTexture;
import org.ogengine3d.util.FileUtils;

public class MainGameLoop {
    public static void main(String[] args) {
        DisplayManager displayManager = new DisplayManager(1280, 700, "Our First Window");
        displayManager.createDisplay();

        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        // StaticShader shader = new StaticShader();
        TexturedStaticShader shader = new TexturedStaticShader();

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

        float[] textureCoords = { // Quad texture coords
                0, 0, // V0
                0, 1, // V1
                1, 1, // V2
                1, 0 // V3
        };

        // RawModel model = loader.loadToVAO(vertices, indices);
        RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
        ModelTexture texture = new ModelTexture(
                loader.loadTexture(FileUtils.getFilePathFromResources("images/image.png")));
        TexturedModel texturedModel = new TexturedModel(model, texture);

        while (!displayManager.isCloseRequested()) {
            renderer.prepare();
            shader.start();
            renderer.render(texturedModel);
            shader.stop();
            displayManager.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanUp();
        displayManager.closeDisplay();
    }
}
