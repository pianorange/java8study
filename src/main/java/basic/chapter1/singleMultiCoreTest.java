package basic.chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class singleMultiCoreTest {

    public singleMultiCoreTest() {
    }

    public void testmethod() {
        List<Apple> Apples = new ArrayList<Apple>();

        Apple a = new Apple();
        a.setWeight(10);
        Apple b = new Apple();
        a.setWeight(20);
        Apple c = new Apple();
        a.setWeight(30);
        Apple d = new Apple();
        a.setWeight(40);

        Apples.add(a);
        Apples.add(b);
        Apples.add(c);
        Apples.add(d);
        long start = System.currentTimeMillis();
        List<Apple> heavyApples =
                Apples.stream().filter((Apple t) -> t.getWeight() > 20)
                        .collect(toList());
        long end = System.currentTimeMillis();
        System.out.println("실행 시간 : " + (end - start) / 1000.0);
        System.out.println(heavyApples.toString());

        long start2 = System.currentTimeMillis();
        List<Apple> heavyApples2 =
                Apples.parallelStream().filter((Apple t2) -> t2.getWeight() > 20)
                        .collect(toList());
        long end2 = System.currentTimeMillis();
        System.out.println("실행 시간 : " + (end2 - start2) / 1000.0);
        System.out.println(heavyApples2.toString());
    }

    public List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }



    public static void main(String args[]){
        singleMultiCoreTest testclass = new singleMultiCoreTest();
        testclass.testmethod();
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple("green",100));
        inventory.add(new Apple("red",50));
        inventory.add(new Apple("red",80));
        inventory.add(new Apple("green",120));

        List<Apple> redAppls =testclass.filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });



    }
}
