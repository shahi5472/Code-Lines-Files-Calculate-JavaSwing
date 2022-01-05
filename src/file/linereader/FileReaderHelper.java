/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package file.linereader;

import static file.linereader.FileChooserView.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;

/**
 *
 * @author shahi
 */
public class FileReaderHelper {

    public static void fileFilter(File file) {
        try {
            File[] files = file.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    File innerFile = new File(dir, name);
                    if (innerFile.isDirectory()) {
                        fileFilter(innerFile);
                    } else {
                        if (name.toLowerCase().endsWith(fileExtention)) {
                            System.out.println("File Path: " + innerFile.getAbsolutePath());
                            totalFiles += 1;
                            totalLines += readLines(innerFile.getAbsolutePath());
                        }
                    }

                    return name.toLowerCase().endsWith(fileExtention);
                }
            });
        } catch (Exception e) {
            System.out.println("File Filter Error: " + e.getMessage());
        }
    }

    private static int readLines(String filePath) {
        int lines = 0;
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferredReader = new BufferedReader(fileReader);
            while (bufferredReader.readLine() != null) {
                lines += 1;
            }
            fileReader.close();
            return lines;
        } catch (Exception e) {
            System.out.println("Read Lines Error: " + e.getMessage());
            return 0;
        }
    }
}
