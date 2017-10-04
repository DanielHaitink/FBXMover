package generator;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * Created by DanielHaitink on 20-06-17.
 */
class DirWatcher implements Runnable {

    private static final int TIMEOUT_TIME_SEC = 30;
    private static final String MSG_WRITE_FAIL = "Failed writing to sourceFile";
    private static final FileFilter FILE_FILTER = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            for (String fileSuffix: ImageIO.getReaderFileSuffixes()) {
                if (pathname.toString().endsWith(fileSuffix))
                    return true;
            }
            return false;
        }
    };

    private File sourceFile;
    private File targetFile;
    private FileProcessor processor;

    DirWatcher() {
        this.sourceFile = null;
        this.targetFile = null;
        this.processor = new FileProcessor();
    }

    void setSource(File sourceFile) {
        System.out.println("Set source to " + sourceFile.toString());
        this.sourceFile = sourceFile;
    }

    void setTarget(File targetFile) {
        System.out.println("Set target to " + targetFile.toString());
        this.targetFile = targetFile;
        this.processor.setDirectory(targetFile);
    }

    private void watchForCreate() {
        while (true) {
            findNewFiles();
            try {
                TimeUnit.SECONDS.sleep(TIMEOUT_TIME_SEC);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void findNewFiles() {
        if (sourceFile == null || targetFile == null)
            return;

        System.out.println("Run");

        final File[] fileList = sourceFile.listFiles();

        if (fileList == null)
            return;

        for (File imgFile : fileList){
            if (!this.processor.isProcessed(imgFile))
                try {
                    this.processor.process(imgFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    public void run() {
        watchForCreate();
    }
}
