package ru.itis.utils;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class FileSaver {
    public static String save(Part file, String directory) {
        try {
            String file_name = UUID.randomUUID().toString() +
                    "-" +
                    file.getSubmittedFileName();

            IOUtils.copyLarge(
                    file.getInputStream(),
                    new FileOutputStream(directory +
                            File.separator +
                            file_name
                    )
            );
            return file_name;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
