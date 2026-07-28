package com.digitalnurture;

/** Exercise 3: service that reads from and writes to files. */
public class FileService {

    private final FileReader fileReader;
    private final FileWriter fileWriter;

    public FileService(FileReader fileReader, FileWriter fileWriter) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
    }

    /**
     * Reads content, processes it, and writes the result back.
     * "Mock File Content" -> "Processed Mock File Content".
     */
    public String processFile() {
        String content = fileReader.read();
        String processed = "Processed " + content;
        fileWriter.write(processed);
        return processed;
    }
}
