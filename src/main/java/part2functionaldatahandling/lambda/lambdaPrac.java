package part2functionaldatahandling.lambda;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
        //즉, 맵과 달리 스트림의 각 값을 다른 스트림으로 반든 다음 모든 스트림 하나의 스트림으로 연결
        //["Hello","World"] ==>["H","e","l","l","o","W","o","r","l","d"]
        String[] words = {"Hello","World"};

        Arrays.stream(words)
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);

    }

    //1. 숫자리스트 주어졌을 때 각 숫자 제곱근으로 이루어진 리스트를 반환하시오
    //[1,2,3,4,5] ==> [1, 4, 9, 16, 25]
    public void lambdaprac1(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream()
                .map( i -> i * i)
                .collect(toList());
    }

    //[1,2,3]   [3,4]  두개의 숫자 리스트가 있을 때 모든 숫자 쌍의 리스트 반환하시오
    //==> [(1,3), (1,4),(2,3),(2,4),(3,3),(3,4)]
    //두개의 map 이용 두 리스트 반복한 다음 숫자 쌍을 만든다.
    public void lambdaprac2(){
        List<Integer> numbers1 = Arrays.asList(1,2,3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> pairs = numbers1.stream()
                                    .flatMap(i -> numbers2.stream().map(j -> new int [] {i, j}))
                                    .collect(toList());
    }


   // [(1,3), (1,4),(2,3),(2,4),(3,3),(3,4)] 중 두 쌍의 합이 3으로 나누어 떨어지는 요소만 남기시오
    public void lambdaprac3(){
        List<Integer> numbers1 = Arrays.asList(1,2,3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream().filter(j -> (i+j) % 3 == 0)
                                                        .map(j -> new int[]{i,j}))
                        .collect(toList());
    }

    //Optional
    public void lambdaprac4(){
        //     * Returns an {@link Optional} describing some element of the stream, or an
        //      * empty {@code Optional} if the stream is empty.
        //Optional<T> findAny()
      Optional<Dish> dish = menu.stream()
                            .filter(Dish::isVegetarian)
                            .findAny();

    }
    //Optional
    //findFirst, findAny 는 언제 사용하나?
    //병렬성 있냐 없냐에 따라 나눠사용
    //병렬처리는 첫 번째 요소 찾기 어려움 . 따라서 요소 반환 순서 상관 없다면 병렬 스트림에서는
    //제약이 적은 findAny를 사용.
    public void lambdaprac5(){

       String result = menu.stream()
                .filter(Dish::isVegetarian)  // ==> Stream<Dish>
                .findAny()                  //findAny() ==>Optional<Dish> 이후 메소드들은 Optional클래스의 map과 orElse
                .map(Dish::getName)        // map() ==> Optional<String>
                .orElse("dd");
         //     .ifPresent(d -> System.out.println(d.getName()));
    }


    //리듀싱
    



    public static void main(String args[]){
        lambdaPrac lamp = new lambdaPrac();
        lamp.flatMapTest();

    }



}
