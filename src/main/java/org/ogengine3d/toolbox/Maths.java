package org.ogengine3d.toolbox;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.ogengine3d.entities.Camera;

public class Maths {
    public static Matrix4f createTransformationMatrix(Vector3f translation, Vector3f rotation,
            Vector3f scale) {
        Matrix4f matrix = new Matrix4f();
        matrix.translate(translation, matrix);
        matrix.rotate((float) Math.toRadians(rotation.x), new Vector3f(1, 0, 0), matrix);
        matrix.rotate((float) Math.toRadians(rotation.y), new Vector3f(0, 1, 0), matrix);
        matrix.rotate((float) Math.toRadians(rotation.z), new Vector3f(0, 0, 1), matrix);
        matrix.scale(scale, matrix);
        return matrix;
    }

    public static Matrix4f createViewMatrix(Camera camera) {
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.rotate((float) Math.toRadians(camera.getPitch()), new Vector3f(1, 0, 0),
                viewMatrix);
        viewMatrix.rotate((float) Math.toRadians(camera.getYaw()), new Vector3f(1, 0, 0),
                viewMatrix);
        Vector3f cameraPos = camera.getPosition();
        Vector3f negativeCameraPos = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        viewMatrix.translate(negativeCameraPos, viewMatrix);
        return viewMatrix;
    }
}
