package generator;

import config.Config;

public final class Main {
    public static void main(String args[]) {
        Config.load();

        new Window();
    }
}
