package library.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileStorage {
    private final Path dataDirectory;

    public FileStorage(String directory) {
        if (directory == null || directory.isBlank()) {
            throw new IllegalArgumentException("Data directory cannot be empty");
        }
        this.dataDirectory = Path.of(directory);
    }

    public void initialize() throws IOException {
        Files.createDirectories(dataDirectory);
    }

    public void saveLines(String filename, List<String> lines) throws IOException {
        validateFilename(filename);
        Files.write(dataDirectory.resolve(filename), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
    }

    public List<String> loadLines(String filename) throws IOException {
        validateFilename(filename);
        Path file = dataDirectory.resolve(filename);
        if (!Files.exists(file)) {
            return List.of();
        }
        return Files.readAllLines(file);
    }

    private void validateFilename(String filename) {
        if (filename == null || filename.isBlank() || !Path.of(filename).getFileName().toString().equals(filename)) {
            throw new IllegalArgumentException("Invalid data filename");
        }
    }
}

