package net.javaguides.springboot;

import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class JsonParseUtil {

    @SneakyThrows
    public static String getJson(final String pathname) {
        File file = new ClassPathResource(pathname).getFile();
        return FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
    }
}
