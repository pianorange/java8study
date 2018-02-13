
4.3.6 메서드 레퍼런스
-

- 메서드 레퍼런스 이용하면 기존 메소드 정의 재활용해서 람다처럼 전달 가능.(때로는 이쪽이 더 가독성 좋고 자연스럽다)

```
람다사용
inventroy.sort((Apple a1, Apple a2) ->
                                      a1.getWeight().compareTo(a2.getWeight()));

메서드레퍼런스와 java.util.Comparator.comparing 활용 코드
inventory.sort(comparing(Apple::getWeight));
      
```

3.6.1 요약
- 


**메서드 레퍼런스**: 특정 메서드만을 호출하는 람다의 축약형
```
이것도 결국에는 동작파라미터화의 일환 
결과물로 전달되는건 함수형 인터페이스가 갖는
단 하나의 메서드를 구현한 concrete class 즉 동작이다.

Supplier <Apple> c1 = Apple::new( 이것과 같다. () -> new Apple)
Apple a1 = c1.get();

결국 get() 메서드의 시그니처와 일치하는 람다 디스크립터를 구현한것과 같다. 
```

```
    // (Apple a) -> a.getName() 을 축약한 것    
    Apple::getName
```

- 실제로 메서드를 호출하는 것은 아니므로 괄호는 필요없다. 
- 실제로 메서드 레퍼런스 이용하면 기존 메서드 구현으로 람다표현식 만들 수 있다.
- 명시적으로 메서드명 참조함으로서 가독성 높일 수 있음.

<메서드 레퍼런스 예시>

| lambda | methodreferance |
|--------|--------|
|    (Apple a) -> a.getName()    |   Apple::getName     |
|    () -> Thread.currentThread().dumpStack()    |  Thread.currentThread()::dumpStack      |
|    (String s) -> System.out.println(s)   |  System.out::println      |
|    (str, i) -> str.substring(i)   |   String::substring     |

메서드 레퍼런스는 새로운 기능이 아니라 하나의 메서드 참조 람다 편리하게 표현 가능 문법이라 할 수 있다.

메서드 레퍼런스를 만드는 방법
-
1. 정적 메서드 레퍼런스
 예를 들어 Integer의 ParseInt 메서드는 Integer::parseInt 로 표현
2. 다양한 형식의 인스턴스 메서드 레퍼런스
 예를 들어 String 의 length 메서드는 String::length로 표현
3. 기존 객체의 인스턴스 메서드 레퍼런스
 예를 들어 Trasaction객체를 할당 받은 et 지역변수가 있고 Transaction객체에 getValue메서드가 있다면,
 et::getValue로 표현
 
 
 ```
   List<String> str = Arrays.asList("E","a","c","B","d");
         str.sort((s1, s2) ->s1.compareToIgnoreCase(s2));
        
         str.sort(String::compareToIgnoreCase); 
 ```
 
  3.6.2 생성자 레퍼런스
  -
    
``` 
  Supplier <Apple> c1 = Apple::new( 이것과 같다. () -> new Apple)
  Apple a1 = c1.get();
```

 -  결국 get() 메서드의 시그니처와 일치하는 람다 디스크립터를 구현한것과 같다. 

- Apple(Integer weight) 라는 시그니처를 갖는 생성자는 Function인터페이스의 시그니처와 같다.
```
Function<Integer, Apple> c2 = Apple::new;
Apple a2 = c2.apply(110);

Function<Integer, Apple> c2 = (weight) -> new Apple(weight);
Apple a2= c2.apply(110);
```
- Apple(String color, Integer weight)  처럼 두 인수를 갖는 생성자는 BiFunction인터페이스의 시그니처와
같다.

```
  BiFunction<String, Integer, Apple> c3 = Apple::new;
        Apple a3 = c3.apply("green", 110);
```
- 즉, 메서드 레퍼런스(Class::method)처럼 생성자 레퍼런스(Class::new)도 상황에 맞춰 함수형 인터페이스 활용
- 인스턴스화 하지 않고도 생성자에 접근할 수 있는 기능은 다양한 상황에 응용가능
<br> ex) <br>
Map 으로 생성자와 문자열을 연관 시킬 수 있다 .
```
static Map<String, Function<Integer, Fruit>> map = new HashMap<>();
static {
    map.put("apple", Apple::new);
    map.put("orange", Orange::new);
}
```
- Color(int,int,int) 처럼 인수가 세개인 생성자 레퍼런스를 사용하려면?
```

```