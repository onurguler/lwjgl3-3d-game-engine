package org.ogengine3d.shaders;

import org.ogengine3d.util.FileUtils;

public class StaticShader extends ShaderProgram {

    private static final String VERTEX_SHADER_FILE =
            FileUtils.getFilePathFromResources("shaders/colorful/shader.vs.glsl");
    private static final String FRAGMENT_SHADER_FILE =
            FileUtils.getFilePathFromResources("shaders/colorful/shader.fs.glsl");

    public StaticShader() {
        super(VERTEX_SHADER_FILE, FRAGMENT_SHADER_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }

    @Override
    protected void getAllUniformLocations() {

    }

}
