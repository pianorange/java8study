package basic.chapter1.lambda;

import java.util.Comparator;
import java.util.List;
import java.util.function.*;

public class functionalInterfacePractice {

    public void practiceMethod () {
        //boolean return
        Predicate<String> predicate = (String str) -> str.contains("a");
        //create instance
        Supplier<dummyClass> supplier = () -> new dummyClass();

        int testint= 100;
        String testreference= "apple";
        dummyClass testdummy1 = supplier.get();
        dummyClass testdummy2 = supplier.get();
        testdummy1.setName("testdummyname");
        //consume Object
        Consumer<dummyClass> consumer =  (dummyClass dummy) -> System.out.println(testint+ dummy.getClass().toString());
        consumer.accept(testdummy2);
        //Comparator.comparing();

        //람다를 사용한 메서드의 지역변수는 final 혹은 final처럼 값이 한번만 할당 되야한다.
        //testint = 200;
        //testinstance = "orange";





        Function<String,Integer> function = (String str) ->  str.length();
        System.out.println(function.apply("aaaaa"));

        ToIntFunction<String> toIntFunction = (String numstr) -> Integer.parseInt(numstr);

        //두 값 조합
        IntBinaryOperator intBinaryOperator = (int a,int b) -> a*b;

        //두 객체 비교
        BiFunction<dummyClass,dummyClass,Integer> biFunction = (dummyClass a,dummyClass b) -> a.toString().length() + b.toString().length();

        ToIntBiFunction<dummyClass,dummyClass> toIntBiFunction = (dummyClass a, dummyClass b) -> a.hashCode();

    }

    public static void main(String args[]){
        functionalInterfacePractice functionalInterfacePractice = new functionalInterfacePractice();
        functionalInterfacePractice.practiceMethod();
    }

}
