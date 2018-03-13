package part2functionaldatahandling.lambda;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class lambdaPrac {

    List<Dish> menu = Arrays.asList(
            new Dish("pork",false,300,Dish.Type.MEAT),
            new Dish("beef",false,400,Dish.Type.MEAT),
            new Dish("chicken",false,255,Dish.Type.MEAT),
            new Dish("rice",true,310,Dish.Type.OTHER),
            new Dish("salmon",false,300,Dish.Type.FISH),
            new Dish("pizza",true,300,Dish.Type.OTHER),
            new Dish("frenchfries",false,300,Dish.Type.OTHER),
            new Dish("prawns",false,300,Dish.Type.FISH),
            new Dish("tuna",false,300,Dish.Type.FISH)
    );

    public void filteringtest1(){
        List<Dish> vegetarianMenu = menu.stream()
                                    .filter(Dish::isVegetarian)
                                    .collect(toList());

       for(Dish item : vegetarianMenu){
           System.out.println(item);
       }
    }

    public void filteringtest2(){
        List<Integer> numbers = Arrays.asList(1,2,1,3,4,2,2,6,5,8);
        numbers.stream()
                .filter( i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    public void filteringtest3(){
        menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(1)
                .forEach(System.out::println);
    }

    public void filteringtest4(){
        menu.stream()
                .filter(d -> d.getCalories() > 200)
                .skip(2)
                .forEach(System.out::println);
    }

    //Mapping
    // map() flatMap()
    public void mappingtest(){
        // List<Dish> ==> List<String> ==> List<Int>
        menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .forEach(System.out::println);
    }

    public void mappingtest2(){
        List<String> words = Arrays.asList("Java8", "Lambdas","In", "Action");
        List<Integer> wordLengths = words.stream()
                                        .map(String::length)
                                        .collect(toList());
        for(int length: wordLengths){
            System.out.println(length);
        }
    }

    public void flatMapTest(){

        //flatMap 스트림 평면화
        //["Hello","World"] ==>["H","e","l","l","o","W","o","r","l","d"]
        String[] words = {"Hello","World"};

        Stream<String> streamOfwords = Arrays.stream(words);


    }



}
