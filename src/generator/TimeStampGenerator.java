package generator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStampGenerator {

    static String generate() {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
    }
}
