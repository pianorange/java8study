package part2functionaldatahandling.stream;

import java.util.List;

public class Main {
    public static void main(String args[]){
        StreamPrac p = new StreamPrac();

        List<Dish> menu = p.createDishes();
        p.sortDishes(menu);
        for(Dish d: menu) {
            System.out.print("원본상태체크" + d.getName());
        }
        p.sortDishesPalallel(menu);
    }
}
