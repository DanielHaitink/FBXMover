package generator;

import config.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

final class Window extends JFrame {
    private static final String TITLE = "Cutout generator";
    private static final String MSG_COULD_NOT_SAVE_CFG = "Couldn't save config file";

    private DirWatcher dirWatcher;
    private Thread watchThread;

    Window() {
        createWatcher();
        createUI();
        addListeners();
    }

    private void createUI() {
        setTitle(TITLE);

        setSize(Config.getWindowDimension());
        setLocation(Config.getWindowLocation());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setLayout(new GridLayout(3, 1));

        add(new ScanDirButton(this.dirWatcher));
        add(new TargetButton(this.dirWatcher));
        add(new ForceScanButton(this.dirWatcher));

        setVisible(true);
    }

    private void createWatcher() {
        this.dirWatcher = new DirWatcher();
        this.watchThread = new Thread(dirWatcher);
        this.watchThread.start();
    }

    private void addListeners() {
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                Config.setWindowDimension(getSize());
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                Config.setWindowLocation(getLocation());
            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
    }

    private void close() {
        dispose();

        try {
            Config.save();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, MSG_COULD_NOT_SAVE_CFG);
        }

        System.exit(0);
    }
}
