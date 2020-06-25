package org.ogengine3d.shaders;

import org.joml.Matrix4f;
import org.ogengine3d.entities.Camera;
import org.ogengine3d.toolbox.Maths;
import org.ogengine3d.util.FileUtils;

public class StaticShader extends ShaderProgram {

    private static final String VERTEX_SHADER_FILE =
            FileUtils.getFilePathFromResources("shaders/shader.vs.glsl");
    private static final String FRAGMENT_SHADER_FILE =
            FileUtils.getFilePathFromResources("shaders/shader.fs.glsl");

    private int locationTransformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;

    public StaticShader() {
        super(VERTEX_SHADER_FILE, FRAGMENT_SHADER_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

    @Override
    protected void getAllUniformLocations() {
        locationTransformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(locationTransformationMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix) {
        super.loadMatrix(location_projectionMatrix, matrix);
    }

    public void loadViewMatrix(Camera camera) {
        Matrix4f viewMatrix = Maths.createViewMatrix(camera);
        super.loadMatrix(location_viewMatrix, viewMatrix);
    }

}
