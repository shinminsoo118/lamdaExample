package compare;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * 디렉토리안의 파일을 리스트 하기
 */
@Slf4j
public class FileList {
    public static void main(String[] args) throws IOException {
        filesInDirectory();
        onlyIsDirectoryInCurrentDirectory();
        onlyIsAnythingExtationFilesInCurrentDirectory();
        onlyIsHiddenFilesInCurrentDirectory();
    }

    /**
     * 현재 디렉토리안의 파일 리스트를 가져오기
     * @throws IOException
     */
    public static void filesInDirectory() throws IOException {
        Files.list(Paths.get(".")).forEach(f -> log.info("{}", f.getFileName()));
    }

    /**
     * 현재 디렉토리안의 디렉토리 리스트를 가져오기
     * @throws IOException
     */
    public static void onlyIsDirectoryInCurrentDirectory() throws IOException {
        Files.list(Paths.get(".")).filter(Files::isDirectory).forEach(f->log.info("{}", f.getFileName()));
    }

    /**
     * 현재 디렉토리안에 ".java"로 끝나는 파일 리스트를 가져오기
     * @throws IOException
     */
    public static void onlyIsAnythingExtationFilesInCurrentDirectory() throws IOException {
        log.info("path : {}",Paths.get("src/main/java/compare/").toAbsolutePath());
        Files.newDirectoryStream(Paths.get("src/main/java/compare/"), path -> path.toString().endsWith(".java"))
                .forEach(f->log.info("{}",f.getFileName()));
    }

    /**
     * 현재 디렉토리안에 히든 파일 리스트를 가져오기
     * @throws IOException
     */
    public static void onlyIsHiddenFilesInCurrentDirectory() throws IOException {
        final File[] files = new File(".").listFiles(File::isHidden);
        Arrays.stream(files).forEach(f->log.info("{}", f.toString()));
    }

    /**
     * 현재 디렉토리안의 하위디렉토리들의 파일 리스트를 가져오기
     * @throws IOException
     */
    public static void filesUnderDirectoryInCurrentDirectory() throws IOException {
        //List<File> files
    }
}
