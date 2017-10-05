package generator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class TargetButton extends JButton {
    private static final String TITLE = "Select Target Directory";

    private String directory;
    private DirWatcher dirWatcher;

    TargetButton(DirWatcher dirWatcher) {
        super(TITLE);

        directory = null;
        this.dirWatcher = dirWatcher;

        createUI();
    }

    private void createUI() {
        setFocusable(false);

        addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClick();
            }
        });
    }

    private void onClick() {
        String baseDir = ".";

        if (this.directory != null)
            baseDir = this.directory;

        JFileChooser chooser = new JFileChooser(directory);

        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            this.directory = chooser.getSelectedFile().getAbsolutePath();
            dirWatcher.setTarget(new File(this.directory));
        }
    }
}
