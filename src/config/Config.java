package config;

import java.awt.*;
import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class Config implements Serializable {
    private static final String FILE = "cutoutGenerator.cfg";
    private static Config config = null;

    private String directory;
    private Dimension windowDimension;
    private Point windowLocation;
    private int scanTimeout;

    {
        directory = "user.home";
        windowDimension = new Dimension(400, 300);
        windowLocation = new Point(10, 10);
        scanTimeout = 1;
    }

    public static void save() throws IOException
    {
        if(config != null)
        {
            ObjectOutputStream out = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(FILE)));

            out.writeObject(config);
            out.close();
        }
    }

    public static void load()
    {
        try {
            ObjectInputStream in = new ObjectInputStream(new GZIPInputStream(new FileInputStream(FILE)));

            config = (Config) in.readObject();
            in.close();
        } catch (ClassNotFoundException | IOException e) {
            config = new Config();
        }
    }

    public static String getDirectory()
    {
        return config.directory;
    }

    public static Dimension getWindowDimension()
    {
        return config.windowDimension;
    }

    public static Point getWindowLocation()
    {
        return config.windowLocation;
    }

    public static int getScanTimeout() {
        return config.scanTimeout;
    }

    public static void setDirectory(final String directory)
    {
        config.directory = directory;
    }

    public static void setWindowDimension(final Dimension windowDimension)
    {
        config.windowDimension = windowDimension;
    }

    public static void setWindowLocation(final Point windowLocation)
    {
        config.windowLocation = windowLocation;
    }

    public static void setScanTimeout(int scanTimeout) {
        config.scanTimeout = scanTimeout;
    }
}
