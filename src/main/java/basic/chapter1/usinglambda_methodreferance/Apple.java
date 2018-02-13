package basic.chapter1.usinglambda_methodreferance;

public class Apple {
    String name;
    int weight;

    public Apple(){}
    public Apple(String name){ this.name = name; }
    public Apple(Integer weight){ this.weight = weight; }
    public Apple(String name, int weight){ this.name = name; this.weight = weight; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
