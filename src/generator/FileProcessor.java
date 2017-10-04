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

        if (!renameFile(file))
            throw new IOException();
        moveFile(file);
    }

    private boolean renameFile(File file) {
        final String name = file.getName().substring(0, file.getName().length() - 4);
        final String extension = file.getName().substring(file.getName().length() - 4);
        final String dir = file.getParent();

        // TODO: rename after moving
        return file.renameTo(new File(dir + "/" + TimeStampGenerator.generate() + '-' + name + extension));
    }

    private void moveFile(File file) throws IOException {
        Files.copy(file.toPath(), this.directory.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    boolean isProcessed(File file) {
        if (this.directory == null)
            return true;

        final String fileName = file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 4);

        File[] fileList = this.directory.listFiles();

        if(fileList == null || fileList.length == 0)
            return false;

        for (File dirFile: fileList) {
            if (dirFile.isFile() && dirFile.getName().contains(fileName))
                return true;
        }

        return false;
    }
}
