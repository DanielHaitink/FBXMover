package generator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


/**
 * Created by DanielHaitink on 20-06-17.
 */
class FileProcessor {

    private File directory;

    public FileProcessor() {
        this.directory = null;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }

    void process(File file) throws IOException {
        processFile(file);
    }

    private void processFile(final File file) throws IOException {
        moveFile(file);
    }

    private String getNewFileName(File file) {
        final String name = file.getName().substring(0, file.getName().length() - 4);
        final String extension = file.getName().substring(file.getName().length() - 4);

        return TimeStampGenerator.generate() + '-' + name + extension;
    }

    private File moveFile(File file) throws IOException {
        File target = new File(this.directory.getAbsolutePath() + File.separator + getNewFileName(file));

        return Files.copy(file.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING).toFile();
    }

    boolean isProcessed(File file) {
        if (this.directory == null)
            return true;

        final String fileName = file.getName().substring(0, file.getName().length() - 4);

        File[] fileList = this.directory.listFiles();

        if (fileList == null || fileList.length == 0)
            return false;

        for (File dirFile : fileList) {
            if (dirFile.isFile() && dirFile.getName().endsWith('-' + fileName + ".fbx"))
                return true;
        }

        return false;
    }
}
