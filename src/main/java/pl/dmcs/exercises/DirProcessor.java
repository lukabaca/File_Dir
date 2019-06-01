package pl.dmcs.exercises;
import org.apache.commons.io.FilenameUtils;
import java.io.File;


public class DirProcessor {

    public int getNumberOfCharactersInAllFilesInDirectory(String directoryPath) {
        try {
            FileProcessor fileProcessor = new FileProcessor();
            File folder = new File(directoryPath);
            File[] filesInFolder = folder.listFiles();
            int sum = 0;
            for (File file : filesInFolder) {
                if (file != null) {
                    String fileExtension = FilenameUtils.getExtension(file.getPath());
                    if (!file.isDirectory() && fileExtension.equals("txt")) {
                        int numberOfCharactersInFile = fileProcessor.getNumberOfCharactersInFile(file.toPath().toString());
                        if (numberOfCharactersInFile != -1) {
                            sum += numberOfCharactersInFile;
                        }
                    }
                }
            }
            return sum;
        } catch (Exception e) {
            return -1;
        }
    }
}
