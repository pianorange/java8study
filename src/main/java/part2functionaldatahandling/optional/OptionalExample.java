package part2functionaldatahandling.optional;

public class OptionalExample {

    public static void main(String[] args) {
        Computer computer = new Computer();

        String version = computer.getGraphicCard()
                .flatMap(GraphicCard::getGpu)
                .flatMap(GPU::getVersion)
                .orElse("no version");
        System.out.println(
                version
                );


    }


}
