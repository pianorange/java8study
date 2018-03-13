package part2functionaldatahandling.optional;

import java.util.Optional;

public class Computer {

    private Optional<GraphicCard> graphicCard = Optional.empty();

    public Optional<GraphicCard> getGraphicCard() {
        return graphicCard;
    }

    public void setGraphicCard(Optional<GraphicCard> graphicCard) {
        this.graphicCard = graphicCard;
    }
}
