package org.ogengine3d.textures;

public class ModelTexture {
    private int textureID;

    private int shineDamper = 1;
    private float reflectivity = 0;

    public ModelTexture(int textureID) {
        this.textureID = textureID;
    }

    public int getTextureID() {
        return textureID;
    }

    public float getReflectivity() {
        return reflectivity;
    }

    public void setReflectivity(float reflectivity) {
        this.reflectivity = reflectivity;
    }

    public int getShineDamper() {
        return shineDamper;
    }

    public void setShineDamper(int shineDamper) {
        this.shineDamper = shineDamper;
    }
}
