package basic.chapter1.lambda;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class functionalInterfacePractice {

    public void practiceMethod () {
        //boolean return
        Predicate<String> predicate = (String str) -> str.contains("a");
        //create instance
        Supplier<dummyClass> supplier = () -> new dummyClass();
        //consume Object
        Consumer<dummyClass> consumer =  (dummyClass dummy) -> System.out.println(dummy.getClass().toString());




    }


}
