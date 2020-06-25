package org.ogengine3d.shaders;

import org.ogengine3d.util.FileUtils;

public class TexturedStaticShader extends ShaderProgram {

    private static final String VERTEX_SHADER_FILE =
            FileUtils.getFilePathFromResources("shaders/textured/shader.vs.glsl");
    private static final String FRAGMENT_SHADER_FILE =
            FileUtils.getFilePathFromResources("shaders/textured/shader.fs.glsl");

    public TexturedStaticShader() {
        super(VERTEX_SHADER_FILE, FRAGMENT_SHADER_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

}
