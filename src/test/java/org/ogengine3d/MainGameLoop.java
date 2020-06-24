package org.ogengine3d;

import org.ogengine3d.rengerEngine.DisplayManager;

public class MainGameLoop {
    public static void main(String[] args) {
        DisplayManager displayManager = new DisplayManager(1280, 700, "Our First Window");
        displayManager.createDisplay();

        while (!displayManager.isCloseRequested()) {
            displayManager.updateDisplay();
        }

        displayManager.closeDisplay();
    }
}
