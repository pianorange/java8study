package part2functionaldatahandling.optional;

import java.util.Optional;

public class GraphicCard {


    private Optional<GPU> gpu = Optional.empty();

    public Optional<GPU> getGpu() {
        return gpu;
    }

    public void setGpu(Optional<GPU> gpu) {
        this.gpu = gpu;
    }
}
