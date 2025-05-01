package org.ascii_paint.utils.painting;

import java.io.File;

/**
 * Class Paint is an abstract interface of convertor
 * */

public class Paint {
    private Detail quality;
    private String pathToFile;
    private String newPath;
    private String size = "0x0";
    private int    width;

    /// CONSTRUCTORS ////////////////////////////////////////////////////
    public Paint(String pathToFile, String newPath, Detail quality, int width) {
        this.quality = quality;
        this.pathToFile = pathToFile;
        this.newPath = newPath;
        this.width = width;
    }
    /// CONSTRUCTORS ////////////////////////////////////////////////////

    /// Methods /////////////////////////////////////////////////////////
    // Method for converting image to char matrix
    public char[][] convertToAscii_Text() {
          ImageLoader loader = new ImageLoader(pathToFile);
          Converter converter = new Converter(loader.load(), quality, width);
          char[][] res = converter.convertToText();
          size = converter.getSize();
          return res;
    }

    // Method for converting image to file with char matrix
    public File convertToAscii_File() {
        ImageLoader loader = new ImageLoader(pathToFile);
        Converter converter = new Converter(loader.load(), quality, width);
        char[][] chars = converter.convertToText();
        Visualizer filer = new Visualizer(chars);
        size = converter.getSize();
        return filer.create(newPath);
    }

    /// SETTERS /////////////////////////////////////
    public void setQuality(Detail quality) {
        this.quality = quality;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setNewPath(String newPath) {
        this.newPath = newPath;
    }

    public void setSize(String size) {
        this.size = size;
    }

    /// SETTERS /////////////////////////////////////

    /// GETTERS /////////////////////////////////////
    public Detail getQuality() {
        return quality;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public String getNewPath() {
        return newPath;
    }

    public String getSize() {
        return size;
    }

    public int getWidth() {
        return width;
    }
    /// GETTERS /////////////////////////////////////

    /// Methods /////////////////////////////////////////////////////////
}
