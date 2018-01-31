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


** 3.2 어디에, 어떻게 람다를 사용할까? **
- - -

-> 함수형 인터페이스라는 문맥에서 람다표현식을 사용할 수 있다.<br>

	List<Apple> greenApples = filter(appleboxlist, (Apple a) -> 	
                                                      "green".equals(a.getColor()))

함수형 인터페이스 Predicate<T>를 기대하는 filter메서드의 두번째 인수로 람다표현식을 전달했다.

**3.2.1 함수형 인터페이스**
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



