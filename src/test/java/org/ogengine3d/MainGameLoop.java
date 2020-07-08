package org.ogengine3d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.joml.Vector3f;
import org.ogengine3d.entities.Camera;
import org.ogengine3d.entities.Entity;
import org.ogengine3d.entities.Light;
import org.ogengine3d.models.RawModel;
import org.ogengine3d.models.TexturedModel;
import org.ogengine3d.rengerEngine.DisplayManager;
import org.ogengine3d.rengerEngine.Loader;
import org.ogengine3d.rengerEngine.MasterRenderer;
import org.ogengine3d.rengerEngine.OBJLoader;
import org.ogengine3d.terrains.Terrain;
import org.ogengine3d.textures.ModelTexture;
import org.ogengine3d.util.FileUtils;

public class MainGameLoop {
        public static void main(String[] args) {
                DisplayManager displayManager = new DisplayManager(1280, 700, "Our First Window");
                displayManager.createDisplay();

                Loader loader = new Loader();

                RawModel model = OBJLoader.loadObjModel(FileUtils.getFilePathFromResources("assets/models/tree.obj"),
                                loader);
                ModelTexture texture = new ModelTexture(
                                loader.loadTexture(FileUtils.getFilePathFromResources("assets/textures/tree.png")));
                // texture.setShineDamper(10);
                // texture.setReflectivity(1);
                TexturedModel staticModel = new TexturedModel(model, texture);
                TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel(
                                FileUtils.getFilePathFromResources("assets/models/grassModel.obj"), loader),
                                new ModelTexture(loader.loadTexture(FileUtils
                                                .getFilePathFromResources("assets/textures/grassTexture.png"))));
                grass.getModelTexture().setHasTransparency(true);
                grass.getModelTexture().setUseFakeLighting(true);
                TexturedModel fern = new TexturedModel(
                                OBJLoader.loadObjModel(FileUtils.getFilePathFromResources("assets/models/fern.obj"),
                                                loader),
                                new ModelTexture(loader.loadTexture(
                                                FileUtils.getFilePathFromResources("assets/textures/fern.png"))));
                fern.getModelTexture().setHasTransparency(true);

                List<Entity> entities = new ArrayList<Entity>();
                Random random = new Random();
                for (int i = 0; i < 500; i++) {
                        entities.add(new Entity(staticModel,
                                        new Vector3f(random.nextFloat() * 800 - 400, 0, random.nextFloat() * -600),
                                        new Vector3f(0, 0, 0), new Vector3f(3, 3, 3)));

                        entities.add(new Entity(grass,
                                        new Vector3f(random.nextFloat() * 800 - 400, 0, random.nextFloat() * -600),
                                        new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)));

                        entities.add(new Entity(fern,
                                        new Vector3f(random.nextFloat() * 800 - 400, 0, random.nextFloat() * -600),
                                        new Vector3f(0, 0, 0), new Vector3f(0.6f, 0.6f, 0.6f)));
                }

                Camera camera = new Camera(displayManager.getInputManager());

                Light light = new Light(new Vector3f(20000, 20000, 2000), new Vector3f(1, 1, 1));

                Terrain terrain = new Terrain(0, -1, loader, new ModelTexture(
                                loader.loadTexture(FileUtils.getFilePathFromResources("assets/textures/grass.png"))));
                Terrain terrain2 = new Terrain(-1, -1, loader, new ModelTexture(
                                loader.loadTexture(FileUtils.getFilePathFromResources("assets/textures/grass.png"))));

                MasterRenderer renderer = new MasterRenderer(displayManager);

                while (!displayManager.isCloseRequested()) {

                        camera.move();
                        renderer.processTerrain(terrain);
                        renderer.processTerrain(terrain2);
                        for (Entity entity : entities) {
                                renderer.processEntity(entity);
                        }
                        renderer.render(light, camera);
                        displayManager.updateDisplay();
                }

                renderer.cleanUp();
                loader.cleanUp();
                displayManager.closeDisplay();
        }
}
