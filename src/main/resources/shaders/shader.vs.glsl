#version 400 core

in vec3 position;
in vec2 textureCoordinates;

out vec3 colour;
out vec2 pass_textureCoordinates;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

void main(void) {
    gl_Position = projectionMatrix * viewMatrix * transformationMatrix * vec4(position, 1.0);
    pass_textureCoordinates = textureCoordinates;
    colour = vec3(position.x + 0.5, 1.0, position.y + 0.5);
}
