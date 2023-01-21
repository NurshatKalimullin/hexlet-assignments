package exercise;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.concurrent.CompletionException;

class App {

    // BEGIN
    public static CompletableFuture<Long> getDirectorySize(String dest) {
        return CompletableFuture.supplyAsync(() -> {
            Path fullPath = getPath(dest);
            return FileUtils.sizeOfDirectory(fullPath.toFile());
        });
    }


    public static CompletableFuture<String> unionFiles(String firstSourceFilePath,
                                                       String secondSourceFilePath,
                                                       String resultFilePath){

        CompletableFuture<String> firstSourceData = CompletableFuture.supplyAsync(() -> {
            return readFile(firstSourceFilePath);
        });


        CompletableFuture<String> secondSourceData = CompletableFuture.supplyAsync(() -> {
            return readFile(secondSourceFilePath);
        });


        return firstSourceData.thenCombine(secondSourceData, (first, second) -> {
            String content = first + " " + second;
            try {
                Files.write(getPath(resultFilePath), content.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return content;
        }).exceptionally(e -> {
            System.out.println("Something went wrong - " + e.getMessage());
            return e.getMessage();
        });
    }

    private static String readFile(String filepath) {
        Path absolutePath = getPath(filepath);
        System.out.println("Path is " + absolutePath);
        String result = null;
        try {
            result = Files.readString(absolutePath);
        } catch (Exception e) {
            throw new CompletionException(e);
        }
        return result;
    }

    private static Path getPath(String filepath) {
        return Paths.get(filepath).toAbsolutePath().normalize();
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> result = App.unionFiles(
                "src/resources/file1.txt",
                "src/resources/file1.txt",
                "src/resources/file3.txt"
        );
        System.out.println(result.get());
        // END
    }
}

