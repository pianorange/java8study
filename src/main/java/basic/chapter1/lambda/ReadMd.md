CHAPTER3 람다표현식

_ _ _

**4.3.1 람다란 무엇인가?**<br>
<p>람다표현식은 메서드로 전달할 수 있는 익명 함수 단순화한 것이라고 할 수 있다.</p>

이름은 없지만, 
- 파라미터리스트
- 바디
- 반환형식
- 발생할 수 있는 예외리스트
를 갖는다.<br>

<b> 람다의 특징 </b>
- 익명<br>
보통의 메서드와 달리 이름이 없으므로 익명이라 표현한다. 구현해야 할 코드에 대한 걱정거리가 줄어든다.

- 함수<br>
람다는 메서드처럼 특정 클래스에 종속되지 않으므로 함수라 부른다.
하지만 메서드처럼 파라미터 리스트, 바디 반환형식, 예외리스트를 포함한다.

- 전달<br>
람다 표현식을 메서드 인수로 전달하거나 변수로 저장할 수 있따.

- 간결성<br> 
익명 클래스처럼 많은 자질구레한 코드 구현할 필요가 없다.
<br>

람다lambda 는 람다 미적분학 학계에서 개발한 시스탬에서 유래<br>
람다표현식 왜 중요? 코드를 전달하는과정에서 자질구레한 코드가 많이 발생<br>
람다가 기술적으로 자바 8 이전의 자바로 할 수 없었던 일을 제공하는 것은 아니다.<br>
다만, 동작 파라미터를 이용할 때 익명 클래스 등 판에 박힌 코드를 줄일 수 있다.<br>

람다는 3부분으로 이뤄진다.<br>
   
    [   람다 파라메터  ][화살표][             바디                 ]
     (Apple a1,Apple a2) ->   a1.getWeight().compareTo(a2.getWeight()) 
     
     람다의 2가지 형식
     (parameters) -> expression
     (parameters) -> {statements;}
     
     올바른예)
    1. () -> {}
    2. () -> "Raoul"
    3. () -> {return "Mario";}
     
     틀린형식)
    4. (Integer i) -> return "Alen" + i
    5. (String s) -> {"Iorn Man"}

    1.파라미터가 없으며 void를 반환하는 람다 표현식 public void run()처럼 바디가 없는메서드와같다.
    2.파라미터가 없으며 문자열 반환
    3.명시적으로 return문사용 2번과 같다.
    4.return 흐름제어문이다. {} 안에 있어야한다.
    5."IornMan"은 구문statement 가아니라 표현식 expresion이다. {}안이라면 return명시필요
    아니면 () ->"IornMan" 처럼 expresion형태로 기술해야 한다.
    
람다 예제

| 사용사례 | 람다 예제|
|--------|--------|
| 불린표현식    | (List<String>  list) -> list.isEmpty() |
|객체생성|() -> new Apple(10)|
|객체에서 소비|(Apple a) -> {syso(a.getName())}|
|객체에서 추출|(String s )->s.length()|
|두객체 비교|(Apple a1, Apple a2) -> a.getWeight().compareTo(a2.getWeight())|


* * *
<br><br>

**3.2 어디에, 어떻게 람다를 사용할까?**
-

-> 함수형 인터페이스라는 문맥에서 람다표현식을 사용할 수 있다.<br>

	List<Apple> greenApples = filter(appleboxlist, (Apple a) -> 	
                                                      "green".equals(a.getColor()))

함수형 인터페이스 Predicate<T>를 기대하는 filter메서드의 두번째 인수로 람다표현식을 전달했다.

**3.2.1 함수형 인터페이스**
-
함수형 인터페이스는 하나의 추상메서드를 지정하는 인터페이스다.<br>
람다표현식으로 함수형 인터페이스의 추상 메서드 구현을 직접 전달 가능<br>
->전체 표현식을 함수형 인터페이스의 인스턴스 취급<br>
->기술적으로 함수형 인터페이스를 concrete구현한 클래스의 인스턴스가 된다.<br>
자바 API의 함수형 인터페이스로 Comparator, Runnable 등이 있다.<br>

    public interface Comparator<T>{
        int compare(T o1,T o2);
    }

    public interface Predicate<T> {
        boolean test (T t);
    }

    public interface Runnable {
        void run();
    }

    public interface ActionListener extends EventListener{
        void actionPerformed(ActionEvent e);
    }

■참고<br>
인터페이스는 디폴트 메서드(인터페이스의 메서드를 구현하지 않은 클래스를 고려해서 기본구현을 제공하는 바디를 포함하는 메서드)를 포함할 수 있따. 많은 디폴트 메서드가 있더라도 추상 메서드가 오직 하나면 함수형 인터페이스다.<br>




3.2.2 함수 디스크립터
-
함수형 인터페이스의 추상메서드 시그너처signature는 람다표현식의 시그너처를 가리킨다.<br>

![](https://i.stack.imgur.com/NLlbQ.png)
https://stackoverflow.com/questions/16149285/does-a-methods-signature-in-java-include-its-return-type

람다 표현식의 시그너처를 서술하는 메서드를 함수 디스크립터function descriptor 라고부른다.


@FunctionalInterface란 무엇인가?
자바8 API 중 함수형 어노테이션에 추가된 어노테이션.
이 어노테이션을 선언한 인터페이스가 실제로 단 하나의 추상메서드를 갖는<br>
함수형 인터페이스가 아닐 시 컴파일 에러가 발생한다.<br>

**람다의 활용 예**
-
3.3 람다 활용 실행어라운드 패턴
-
<br>
자원처리에 사용하는 순환패턴 recurrent pattern은 자원을 [열고]
[처리]한다음,[닫는]순서로 이뤄진다.<br>
즉,실제 자원을 처리하는 설정과 정리 두과정이 둘러싸는 형태를 갖는다<br>
이런 형태를 실행 어라운드 패턴execute around pattern 이라 부른다.

| 초기화/준비 코드 | 초기화/준비 코드 |
|--------|--------|
|   작업A     | 작업B       |
|정리/마무리 코드|정리/마무리 코드|

이와 같이 실행 어라운트 패턴
1. 동작파라미터화

```
  public static String processFile() throws IOException{
        try(BufferedReader br =
                new BufferedReader(new FileReader("data.txt"))){
            return br.readLine();
        }
    }
```

위의 코드는 한번에 한줄만 읽어온다.<br>
기존의 설정, 정리과정 재사용하고 processFile메서드만 다른동작을 수행하도록<br>람다를 통해 개선할 수 있다.<br>
활용하고 싶은메서드의 시그니처는 BufferedReader -> String 과 IOException 쓰로우 할 수 있는 형태로 이루어져 있다.<br>
함수형 인터페이스를 사용하기 위해 대상 메서드의 시그니처와 일치하는 함수형 인터페이스를 만든다.

```
@FunctionalInterface
public interface BufferedReaderProcessor {
    String process(BufferedReader b) throws IOException;
}
```
다음과 같이 설정하면, precessFile메서드의 파라메터로 함수형인터페이스를 받아동작파라미터화를 구현할 수 있다.<br>
```
public static String processFile(BufferedReaderProcessor b) 
   throws IOException{
        try(BufferedReader br =
                new BufferedReader(new FileReader("data.txt"))){
            return b.process(br);
        }
    }

```
다음과 같이 함수형인터페이스를 파라미터로 받도록 기존 메서드를 수정하고 
return 부분에서 함수형인터페이스의 추상메서드 process를 구현하도록한 후,
람다표현식을 파라미터로 보냄으로서, processFile메서드를 더 유연하게 사용할 수 있다.
```
   excuteclass.processFile((BufferedReader br) ->
                                                br.readLine()   );
  //두줄을 받아오고 싶을 경우 다음과 같이 유연하게 활용하는 것도 가능하다.
   excuteclass.processFile((BufferedReader br) ->
                                                br.readLine() + br.readLine()  );
                                       
```

지금까지 함수형 인터페이스이용 람다를 전달하는 방법을 배웠다.
다음절에서는 다양한 람다를 전달하는 데 재활용할 수 있도록 자바 8 에추가 된 새로운 인터페이스를 살펴본다.<br>

**3.4 함수형 인터페이스 사용**
-
- 함수형 인터페이스는 오직 하나의 추상메서드 지정.
- **함수형 인터페이스의 추상 메서드는 람다 표현식의 시그너처를 묘사.**

```
함수형 인터페이스의 추상메서드 시그너처 = 
함수 디스크립터function descriptor

```
- 다양한 람다 표현식 사용위해 공통의 함수 디스크립터 기술하는 함수형 인터페이스 집합이 필요하다.
- 이미 자바 API는 Comparable, Runnable, Callable등 다양한 함수형 인터페이스 포함
- 자바 8 라이브러리 설계자들 java.util.function 패키지로 여러 새로운 함수형 인터페이스 제공 (Predicate, Consumer, Function 등)

```

**■ 여기서부터는 제네릭에 대한 이해가 필요**
https://www.bsidesoft.com/?p=2903
http://palpit.tistory.com/667

**자바 제네릭의 활용 종류**
- 제네릭 타입
- 멀티 타입 파라미터
- 제네릭 메소드
- 제한된 타입 파라미터 & 와일드카드 타입
- 제네릭 타입의 상속과 구현



 제네릭 코드에서 물음표(?)는 와일드카드로 불리며, 알 수 없는 타입을 나타낸다. 와일드카드는 파라미터, 필드, 지역 변수의 타입 또는 때때로 반환 타입과 같은 다양한 상황에서 사용될 수 있다. 와일드 카드는 제네릭 메소드 호출에 대한 형식 인수, 제네릭 클래스 인스턴스 생성, 또는 슈퍼타입으로 사용될 수 없다.


1. Upper Bounded Wildcards
  변수의 제한을 완화하기 위해 upper bounded wildcard를 사용할 수 있다. 예를 들어,  List<Integer>, List<Double>, List<Number>를 인자로 받을 수 있는 메소드를 만들기 위해서 upper bounded wildcard를 사용할 수 있다.


  public static void process(List<? extends Number> list) {
          //
   }
  
2. Unbounded Wildcards
  unbounded wildcard 타입은 List<?> 와 같이 물음표 만으로 정의 되어 진다(모든 타입을 인자로 받을 수 있다). 이 방법은 두 가지 유효한 사용법이 있다. 첫 번째는 Object 클래스에서 제공되는 기능만을 사용할 경우이고, 두 번째는 제네릭 클래스의 메소드들중에 List.size, List.clear처럼 타입 파라미터에 의존하지 않는 메소드들만을 사용할 경우이다.

3. Lower Bounded Wildcards
  lower bounded wildcard는 <? super A>와 같이 물음표와 super 키워드로 정의한다. upper bounded wildcard와는 반대로 지정된 타입과 그 상위타입만을 허용한다. 예로 List<? super Integer> 로 정의 하면 Integer의 상위인 Number와 Object 가 사용 가능하다.







```
<br>

**3.4.1 Predicate**
-
```
@FunctionalInterface
public interface Predicate<T>{
	boolean test(T t);
}
```

- java.util.function.Predicate<T> 인터페이스는 test라는 추상 메서드를 정의
- test는 제네릭 형식 T객체를 인수로 받아 boolean반환.

사용예는 다음과 같다.
```

public static <T> List<T> filter(List<T> list, Predicate<T> p) {
	 List<T> results = new ArrayList<>();
     for (T s: list){
     	if(p.test(s)) {
        	results.add(s);
        }
     }
     return results;
}

//호출부
Predicate<String> nonEmptyStringPredicate = 
									(String s) -> !s.isEmpty();
List<String> nonEmpty = filter(listOfStrings, 					   
										nonEmptyStringPredicate);
                                        



```
<br>

**3.4.2 Consumer**
-
- java.util.function.Consumer<T> 인터페이스는 제네릭 형식 T객체 받아 void 반환하는 accept 추상메서드 정의.
- T형식 객체 인수로 받아 어떤 동작 수행하고 싶을 때 사용.
- 예를 들어 Integer 리스트 인수 받아 각 항목 어떤 동작 수행 forEach메서드 정의시 활용.

사용예는 다음과 같다.

```
@FunctionalInterface
public interface Consumer<T> {
	void accept(T t);
}

public static <T> void forEach(List<T> list, Consumer<T> c){
	 for(T i: list) {
     	 c.accept(i);
     }
}

//위의 forEach메서드 호출
//파라미터로 리스트, 람다(Consumer인터페이스의 accept구현)
forEach(
	Arrays,asLIst(1,2,3,4,5),
    (Integer i) -> System.out.println(i)
)

```

**3.4.3 Function**
-
- java.util.function.Function<T,R> 인터페이스는 제네릭형식 T를 인수로 받아서 제네릭형식 R객체를 반환하는 apply메서드 정의.
- 입력을 출력으로 매핑하는 람다를 정의할 때 활용(예를 들어 사과 무게정보 추출하거나, 문자열을 길이와 매핑)
- 다음은 String리스트 인수로 받아 각 String길이를 포함하는 Integer리스트로 변환하는 map메서드 정의하는 예제

```
@FunctionalInterface
public interface Function<T,R> {
	R apply(T t);
}

public static <T, R> List<R> map(List<T> list, Function<T,R> f){
	List<R> result = new ArrayList<>();
    for (T s : list){
    	result.add(f.apply(s));
    }
    return result;
}
//위의 map 메서드 호출부
//파라미터 List , 람다(Function인터페이스의 apply구현)
//[7,2,6]
List<Integer> l = map(
					Array.asList("lambdas","in","action"),
                    (String s) -> s.length()
                    );

```
<br>


■함수형 인터페이스는 제네릭파리미터로 고정된 타입의 제약에서 벗어나게 해줘
더 일반화된 코드를 구현할 수 있도록 해주고, 람다를 통해 구현할 표현식의 시그니처를 제공한다.
T 첫번째파라미터 U 두번째 파라미터 R 리턴타입 으로 구성된 함수형인터페이스 
두개의 파라미터 T, U 리턴타입으로는 R 을 리턴하는 시그니처를 제공
```
//        @FunctionalInterface
//        public interface BiFunction<T, U, R> {
//
//                * @param <T> the type of the first argument to the function
//                * @param <U> the type of the second argument to the function
//                * @param <R> the type of the result of the function
//                  R apply(T t, U u); 

```
 

**기본형 특화**
-
- 자바의 모든형식은 reference type과 primitive type 으로 나뉜다.
- **But** 제네릭 파라미터 (ex Consumer<T>의 T)에는 참조형만 사용가능.
- 이는 제네릭 내부구현 때문에 어쩔 수 없는일.(C#에는 이런 제약없고,스칼라는 참조형타입만 존재한다.)
- 자바에서는 기본형을 참조형으로 박싱하는 기능 제공. (반대는 언박싱이라 부름) <br>
-> 그래서 boxing 과 unboxing 자동으로 이루어지는 autoboxing기능 제공

```
LIst<Integer> list = new ArrayList();
list.add(100); <--Integer로 오토박싱됨
```

**But** 이러한 변환과정 비용 소모.
- 박싱한 값 기본형 감싸는 래퍼이며, 힙에 저장됨.
- 박싱한 값은 메모리를 더 사용하며, 기본형 가져올 때도 메모리 탐색하는 과정이 필요.

```
자바 8에서는 기본형을 입출력으로 사용하는 상황에서 오토박싱 피할 수 있도록 특별한 함수형 인터페이스 제공

ex)
    public interface IntPredicate {
        boolean test(int i);
    }
    
    IntPredicate evenNumbers = (Int i) -> i % 2 == 0;
    evenNumbers.test(1000); <-- boxing X 
    
    Predicate<Integer> oddNumbers = (Integer i) -> i % 2 == 1;
    oddNumbers.test(1000)  <-- autoBoxing 
```

3.5.3 형식추론 
-
자바컴파일러는 람다표현식이 사용된 콘텍스트(대상형식)이용 람다표현식과 관련된 인터페이스 추론<br>
대상형식 이용해서 함수 디스크립터 알 수 있으므로 컴파일러는 람다의 시그니처도 추론<br>
-> 컴파일러 람다표현식 파라미터 형식에 접근가능하므로 람다 문법에서 생략가능

```

filter (ArrayList inventory,Predicate<Apple> p)

List<Apple> grrenApples = 
      filter(inventory, a -> "green".equals(a.getColor()));
 위의 예제에서 filter 메소드의 두번째 파라미터 타입은 Predicate<Apple> 이고, 함수형인터페이스 Predicate에는
 boolean test (String a) 와 같은 시그니처를 가진 메소드가 있으므로 람다 디스크립터에서 사용한 a 의 타입추론가능

//형식추론 사용하지 않음    
Comparator<Apple> c = 
    (Apple a1,Apple a2) -> a1.getWeight().comapareTo(a2.getWeight());
//형식 추론 사용
Comparator<Apple> c = 
    (a1, a2) -> a1.getWeight().comapareTo(a2.getWeight());
   
```

3.5.4 지역변수 사용
-

- 람다표현식은 파라미터로 넘겨진 변수가 아닌 외부에서 정의된 변수(free variable(자유변수)) 사용가능. (람다 캡처링)

```
//지역변수 캡처하는 예제
int portNumber = 1337;
Runnable r = () -> System.out.printls(portNumber);
```

**제약사항**

- 람다 인스턴스변수와 정적변수 자유롭게 캡처(자신의 바디에서 참조)
- 지역변수는 명시적으로 final 선언 or 실질적으로 final 선언된 변수와 똑같이 사용되야한다. <br>
  즉, 람다 표현식은 한번만 할당할 수 있는 지역변수를 캡처가능(참고: 인스턴스 변수 캡처는 final 지역변수 this를 캡처하는 것과 마찬가지)
  
```
//지역변수 캡처하는 예제
int portNumber = 1337;
Runnable r = () -> System.out.printls(portNumber);
int portNumber = 2000; //값을 한번 더 할당하므로 람다 안에서 사용불가 컴파일 할수 없다.
```  

**지역변수의 제약**
-
- 내부적으로 인스턴스 변수와 지역변수 태생부터 다르다.
- **인스턴스 변수는 힙에 저장**되는 반면 **지역변수는 스택에 위치**한다.

```
스택영역: 프로그램 실행과정에서 '임시로 할당' 되고 그게 끝나면 소멸되는 것들이 저장된다.
즉, 메소드 호출시 로컬변수 준비하고 호출 끝나면 메소드 위해 준비했던 모든 변수 스택에서 제거.

1. 스택 영역은 변수값 저장하는데 기본타입인 정수형 변수,실수형 변수, 논리형 변수를 실제값으로 저장.
2. 크기가 정해져 있는 타입이다.
3. 메모리 할당시 컴파일할때 이미 계산이 이루어진다.
4. 메소드 작업이 종료되면 할당되었던 메모리 공간은 반환되어 비워진다.
5. 지역변수와 매개변수가 저장된다.(즉, 선언된 블록안에서만 유효한 변수들이 스택에 담긴다.)


힙영역: 인스턴스 변수의 값이 저장된다.(인스턴스변수의 주소값은 스택에 저장, 스택의 생명주기가 끝나
(메소드 종료 등) 주소값을 잃어버리면 가비지컬랙터가 힙의 메모리를 정리한다.)
스택에 저장되는 로컬,매개 변수와 달리 힙 영역에 보관되는 메모리는 메소드 호출이 끝나도 유지된다.


```
- 람다가 지역변수에 접근할 수 있다는 가정 하에 스레드에서 실행된다면 변수할당 스레드 사라져 변수할당 해제되었는데도 람다 실행하는 스레드에서는 해당 변수에 접근하려 할 수 있다.
- 따라서, 자바구현에서는 원래변수에 접근 허용하는 것이 아니라 자유 지역변수(같은 블록이 아니어도 익명클래스, 람다가 접근할 수 있는 지역변수)의 복사본 제공.
- 즉, 복사본의 값이 바뀌지 않아야 하므로 지역변수에는 한번만 값을 할당해야한다는 제약이 생긴 것.

```
클로저closure

- 클로저란 비지역 변수 자유롭게 참조할 수 있는 함수의 인스턴스.
- 즉, 클로저를 다른 함수의 인수로 전달 가능.
- 클로저는 외부에 정의된 변수의 값에 접근, 수정 가능.

->람다, 익명클래스는 클로저와 비슷하지만 '접근'만 가능. 
람다가 정의된 메서드의 지역변수는 final or 한번만 값이 할당되야 함.

->인스턴스변수(클래스변수, 필드변수)는 스레드가 공유하는 힙에 존재하므로 특별한 제약이 없다.

    Supplier<dummyClass> supplier = () -> new dummyClass();

        int testint= 100;
        String testreference = "apple";
        //consume Object
        Consumer<dummyClass> consumer =  (dummyClass dummy) -> System.out.println( testint+ dummy.getClass().toString() + testreference );
        consumer.accept(testdummy2);

        testint = 200;//컴파일 에러!! 지역변수가 final이거나 final처럼 값이 한번만 할당되야한다.
		testreference = "orange"//컴파일 에러!! 
```

