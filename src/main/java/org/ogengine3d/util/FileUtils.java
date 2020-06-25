package org.ogengine3d.util;

import java.net.URL;

public class FileUtils {
    public static String getFilePathFromResources(String filename) {
        ClassLoader classLoader = FileUtils.class.getClassLoader();

        URL resource = classLoader.getResource(filename);

        if (resource == null)
            throw new IllegalArgumentException("File is not found!");
        else
            return resource.getPath();
    }
}
