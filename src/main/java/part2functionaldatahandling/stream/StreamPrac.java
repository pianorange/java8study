package part2functionaldatahandling.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StreamPrac {

    public List<Dish> createDishes(){
        List<Dish> result = new ArrayList<Dish>();
        String [] namelist = {"pasta","sandwitch","hamberger","pizza","sushi"};
        Random random = new Random();
        Dish inputval;

        for(int i =0; i < namelist.length; i++ ) {
            inputval = new Dish();
            inputval.setName(namelist[i]);
            inputval.setCalories(random.nextInt(500));
            result.add(inputval);
        }
        return result;
    }

    public void sortDishes(List<Dish> menu){
        long start = System.currentTimeMillis();

        List<String> lowCaloricDishesName =
                menu.stream()
                        .filter(d -> d.getCalories() < 400)
                        .sorted(comparing(Dish::getCalories))
                        .map(Dish::getName)
                        .collect(toList());

        long end = System.currentTimeMillis();
        System.out.println("실행 시간 : " + (end - start) / 1000.0);
        System.out.println(lowCaloricDishesName.toString());
    }

    public void sortDishesPalallel(List<Dish> menu){
        long start = System.currentTimeMillis();

        List<String> lowCaloricDishesName =
                menu.parallelStream()
                        .filter(d -> d.getCalories() < 400)
                        .sorted(comparing(Dish::getCalories))
                        .map(Dish::getName)
                        .collect(toList());

        long end = System.currentTimeMillis();
        System.out.println("실행 시간 : " + (end - start) / 1000.0);
        System.out.println(lowCaloricDishesName.toString());
    }
}
