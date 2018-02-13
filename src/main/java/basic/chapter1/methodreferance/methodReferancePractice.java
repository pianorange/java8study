package basic.chapter1.methodreferance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class methodReferancePractice {
    public void testmethod(){
        Thread.currentThread().dumpStack();
    }

    public void testmethod2() {
        List<String> str = Arrays.asList("E", "a", "c", "B", "d");
        str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        System.out.println(str);
    }

    public void testmethod3(){
        List<Integer> weights = Arrays.asList(7,4,5,6,10);
        List<Apple> apples = map(weights, Apple::new);

    }

    public void testmethod4(){
        BiFunction<String, Integer, Apple> c3 = Apple::new;
        Apple a3 = c3.apply("green", 110);
        System.out.println(a3.toString());

    }

    public void testmethod5(){
        PracInterface<Integer,Integer,Integer,Color> pracInterface = Color::new;
        Color c1 = pracInterface.apply(100,80,90);
        System.out.println(c1);
    }

    public static List<Apple> map(List<Integer> list,
                                  Function<Integer, Apple> f) {
        List<Apple> result = new ArrayList<>();
        for(Integer e: list) {
            result.add(f.apply(e));
        }
        return result;
    }

    public static void main (String args[]){
        methodReferancePractice testobj = new methodReferancePractice();
        testobj.testmethod2();
        testobj.testmethod5();
    }
}
