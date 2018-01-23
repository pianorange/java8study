package basic.chapter1;

import java.util.ArrayList;
import java.util.List;

public interface ApplePredicate {
    boolean test (Apple apple);
    default List<Apple> filterApples(List<Apple> inventory,
                                     ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }
}
