package org.ascii_paint.utils.painting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * Class Visualizer handles the output of ASCII art to files
 */
public class Visualizer {
    private char[][] converted;

    /// CONSTRUCTORS ////////////////////////////////////////////////////
    public Visualizer(char[][] converted) {
        this.converted = converted;
    }
    /// CONSTRUCTORS ////////////////////////////////////////////////////

    /// METHODS /////////////////////////////////////////////////////////
    /**
     * Creates a text file with the ASCII art
     */
    public File create(String path) {
        File file = normalizePath(path);
        createFileIfNotExists(file);
        writeAsciiToFile(file);
        return file;
    }
    /// METHODS /////////////////////////////////////////////////////////

    /// PRIVATE HELPERS /////////////////////////////////////////////////
    private File normalizePath(String path) {
        return new File(path.endsWith(".txt") ? path : path + ".txt");
    }

    private void createFileIfNotExists(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("File creation failed", e);
            }
        }
    }

    private void writeAsciiToFile(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (char[] row : converted) {
                writer.write(convertRowToString(row) + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to write ASCII art", e);
        }
    }

    private String convertRowToString(char[] row) {
        return String.join("",
                Stream.of(row)
                        .map(String::valueOf)
                        .toArray(String[]::new));
    }
    /// PRIVATE HELPERS /////////////////////////////////////////////////

    /// GETTERS /////////////////////////////////////////////////////////
    public char[][] getConverted() {
        return converted;
    }
    /// GETTERS /////////////////////////////////////////////////////////

    /// SETTERS /////////////////////////////////////////////////////////
    public void setConverted(char[][] converted) {
        this.converted = converted;
    }
    /// SETTERS /////////////////////////////////////////////////////////
}