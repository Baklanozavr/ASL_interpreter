package asl.input;

import asl.model.system.Context;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SourceBasedApplication implements ASLApplication {
    private final Context applicationContext;
    private final Path sourcePath;
    private final ASLExecutor aslExecutor;

    public SourceBasedApplication(Context applicationContext, ApplicationOptions options, ASLExecutor aslExecutor) {
        this.applicationContext = applicationContext;
        this.sourcePath = options.sourcePath;
        this.aslExecutor = aslExecutor;
    }

    @Override
    public void run() {
        if (Files.isDirectory(sourcePath)) {
            readDirectory(sourcePath);
        } else if (isAslFile(sourcePath)) {
            readFile(sourcePath);
        } else {
            throw new IllegalArgumentException("Unknown argument: " + sourcePath);
        }
    }

    public static boolean isAslFile(Path filePath) {
        return Files.isRegularFile(filePath) && filePath.getFileName().toString().endsWith(".asl");
    }

    public void readDirectory(Path directoryPath) {
        System.out.println("Directory: " + directoryPath.toString());
        try (var filePathStream = Files.list(directoryPath)) {
            filePathStream
                    .filter(SourceBasedApplication::isAslFile)
                    .forEach(this::readFile);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void readFile(Path filePath) {
        applicationContext.clear();
        System.out.println("File: " + filePath.getFileName());
        try (var reader = Files.newBufferedReader(filePath)) {
            aslExecutor.execute(reader);
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println();
    }
}
