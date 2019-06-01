package pl.dmcs.exercises;

import java.io.File;


public class DirProcessor {
    public int getNumberOfCharactersInAllFilesInDirectory(String directoryPath) {
        FileProcessor fileProcessor = new FileProcessor();
        File folder = new File(directoryPath);
        File[] filesInFolder = folder.listFiles();
        int sum = 0;
        for (File file : filesInFolder) {
            if (file != null) {
                if (!file.isDirectory()) {
                    int numberOfCharactersInFile = fileProcessor.getNumberOfCharactersInFile(file.toPath().toString());
                    if (numberOfCharactersInFile != -1 ) {
                        sum += numberOfCharactersInFile;
                    }
                }
            }
        }
        return sum;
    }
}
