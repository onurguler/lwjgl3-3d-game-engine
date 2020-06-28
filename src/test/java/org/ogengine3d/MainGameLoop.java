package org.ogengine3d;

import org.joml.Vector3f;
import org.ogengine3d.entities.Camera;
import org.ogengine3d.entities.Entity;
import org.ogengine3d.entities.Light;
import org.ogengine3d.models.RawModel;
import org.ogengine3d.models.TexturedModel;
import org.ogengine3d.rengerEngine.DisplayManager;
import org.ogengine3d.rengerEngine.Loader;
import org.ogengine3d.rengerEngine.OBJLoader;
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

                RawModel model = OBJLoader.loadObjModel(FileUtils.getFilePathFromResources("assets/models/dragon.obj"),
                                loader);
                ModelTexture texture = new ModelTexture(
                                loader.loadTexture(FileUtils.getFilePathFromResources("assets/textures/white.png")));
                texture.setShineDamper(10);
                texture.setReflectivity(1);
                TexturedModel texturedModel = new TexturedModel(model, texture);

                Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -25), new Vector3f(0, 0, 0),
                                new Vector3f(1, 1, 1));

                Camera camera = new Camera(displayManager.getInputManager());

                Light light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));

                while (!displayManager.isCloseRequested()) {
                        entity.addRotation(new Vector3f(0, 1, 0));
                        camera.move();
                        renderer.prepare();
                        shader.start();
                        shader.loadLight(light);
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
