#version 400 core

in vec3 position;
int vec2 textureCoordinates;

out vec2 pass_textureCoordinates;

uniform mat4 transformationMatrix;

void main(void) {
    gl_Position = transformationMatrix * vec4(position, 1.0);
    pass_textureCoordinates = textureCoordinates;
}
