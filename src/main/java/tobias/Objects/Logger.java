package tobias.Objects;

import java.time.LocalTime;

public class Logger {
   private String name;
    private String info;

    private LocalTime localTime;

    public Logger(String name, String info, LocalTime localTime) {
        this.name = name;
        this.info = info;
        this.localTime = localTime;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }
}
