package org.ogengine3d.shaders;

import org.joml.Matrix4f;
import org.ogengine3d.util.FileUtils;

public class TranslatedStaticShader extends ShaderProgram {

    private static final String VERTEX_SHADER_FILE =
            FileUtils.getFilePathFromResources("shaders/translated/shader.vs.glsl");
    private static final String FRAGMENT_SHADER_FILE =
            FileUtils.getFilePathFromResources("shaders/translated/shader.fs.glsl");

    private int locationTransformationMatrix;

    public TranslatedStaticShader(String vertexShaderFilePath, String fragmentShaderFilePath) {
        super(VERTEX_SHADER_FILE, FRAGMENT_SHADER_FILE);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void bindAttributes() {
        // TODO Auto-generated method stub
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

    @Override
    protected void getAllUniformLocations() {
        // TODO Auto-generated method stub
        locationTransformationMatrix = super.getUniformLocation("transformationMatrix");
    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(locationTransformationMatrix, matrix);
    }

}
