package generator;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ForceScanButton extends JButton{
    private static final String TITLE = "Run Once";

    private DirWatcher dirWatcher;

    ForceScanButton(DirWatcher dirWatcher) {
        super(TITLE);
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
        dirWatcher.forceFindNewFiles();
    }

}
