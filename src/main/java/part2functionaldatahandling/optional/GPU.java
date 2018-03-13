package part2functionaldatahandling.optional;

import java.util.Optional;

public class GPU {

    private Optional<String> version ;

    public Optional<String> getVersion() {
        return version;
    }

    public void setVersion(Optional<String> version) {
        this.version = version;
    }
}
