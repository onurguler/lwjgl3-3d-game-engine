package org.ogengine3d;

import org.joml.Vector3f;
import org.ogengine3d.entities.Camera;
import org.ogengine3d.entities.Entity;
import org.ogengine3d.models.RawModel;
import org.ogengine3d.models.TexturedModel;
import org.ogengine3d.rengerEngine.DisplayManager;
import org.ogengine3d.rengerEngine.Loader;
import org.ogengine3d.rengerEngine.Renderer;
import org.ogengine3d.shaders.StaticShader;
import org.ogengine3d.textures.ModelTexture;
import org.ogengine3d.util.FileUtils;

public class MainGameLoop {
        public static void main(String[] args) {
                DisplayManager displayManager = new DisplayManager(1280, 700, "Our First Window");
                displayManager.createDisplay();

                Loader loader = new Loader();
                StaticShader shader = new StaticShader();
                Renderer renderer = new Renderer(displayManager, shader);

                // float[] vertices = { // Quad vertices
                // -0.5f, 0.5f, 0, // V0
                // -0.5f, -0.5f, 0, // V1
                // 0.5f, -0.5f, 0, // V2
                // 0.5f, 0.5f, 0 // V3
                // };

                // int[] indices = { // Quad indices
                // 0, 1, 3, // Top left triangle (V0, V1, V3)
                // 3, 1, 2 // Bottom right triangle (V3, V1, V2)
                // };

                // float[] textureCoords = { // Quad texture coords
                // 0, 0, // V0
                // 0, 1, // V1
                // 1, 1, // V2
                // 1, 0 // V3
                // };

                float[] vertices = {-0.5f, 0.5f, 0, -0.5f, -0.5f, 0, 0.5f, -0.5f, 0, 0.5f, 0.5f, 0,

                                -0.5f, 0.5f, 1, -0.5f, -0.5f, 1, 0.5f, -0.5f, 1, 0.5f, 0.5f, 1,

                                0.5f, 0.5f, 0, 0.5f, -0.5f, 0, 0.5f, -0.5f, 1, 0.5f, 0.5f, 1,

                                -0.5f, 0.5f, 0, -0.5f, -0.5f, 0, -0.5f, -0.5f, 1, -0.5f, 0.5f, 1,

                                -0.5f, 0.5f, 1, -0.5f, 0.5f, 0, 0.5f, 0.5f, 0, 0.5f, 0.5f, 1,

                                -0.5f, -0.5f, 1, -0.5f, -0.5f, 0, 0.5f, -0.5f, 0, 0.5f, -0.5f, 1

                };

                float[] textureCoords = {

                                0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1,
                                0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1,
                                1, 0


                };

                int[] indices = {0, 1, 3, 3, 1, 2, 4, 5, 7, 7, 5, 6, 8, 9, 11, 11, 9, 10, 12, 13,
                                15, 15, 13, 14, 16, 17, 19, 19, 17, 18, 20, 21, 23, 23, 21, 22

                };

                // RawModel model = loader.loadToVAO(vertices, indices);
                RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
                ModelTexture texture = new ModelTexture(loader.loadTexture(
                                FileUtils.getFilePathFromResources("images/image.png")));
                TexturedModel texturedModel = new TexturedModel(model, texture);

                Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -1),
                                new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));

                Camera camera = new Camera(displayManager.getInputManager());

                while (!displayManager.isCloseRequested()) {
                        entity.addRotation(new Vector3f(1, 1, 0));
                        camera.move();
                        renderer.prepare();
                        shader.start();
                        shader.loadViewMatrix(camera);
                        renderer.render(entity, shader);
                        shader.stop();
                        displayManager.updateDisplay();
                }

                shader.cleanUp();
                loader.cleanUp();
                displayManager.closeDisplay();
        }
}
