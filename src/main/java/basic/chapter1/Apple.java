package basic.chapter1;

public class Apple {
    int weight;
    String color;

    public Apple(){};

    public Apple(String color,int weight){
        this.color = color;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
