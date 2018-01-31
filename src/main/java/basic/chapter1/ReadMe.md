자바 8 눈여겨봐야 하는 이유

        <2가지 포인트>
        자연어에 가깝게 더 간단한 방식 코드 구현 가능.
        자바8 병렬실행을 새롭고 단순한 방식으로 접근할 수 있는 방법 제공

- 멀티코어 cpu같은 하드웨어 변화도 자바8에 영향
->기존 자바는 코어중 하나만 사용(즉, 나머지 코어는 유휴상태로 두거나 운영체제나
바이러스 검사 프로그램과 프로세스 파워를 나눠서 사용)
- 자바8 이전 나머지 코어 활용 하려면 스레드 사용 but 스레드 관리 어렵고 많은 문제 가능성
이에 자바 5 스레드풀 실행컬랙션 등 강력한 도구 도입
- 자바7 병렬실행에 도움 줄 수 있는 포크/조인 프래임워크 사용 but 활용도 높지 않았다.<br>

자바8 DB질의 언어에서 표현식 처리 하는 것처럼 병렬 연산을 지원하는 스트림이라는 새로운 API 제공<BR>
DB 질의언어= 고수준 언어로 원하는 동작 표현 -> 최적의 저수준 실행 방법 선택하는 방식으로 동작<BR>
(자바에서는 스트림이 이 역할 수행)<BR>

<B>스트림 API 덕분에 
- 메서드에 코드 전달 하는 간결기법(메서드 레퍼런스와 람다)</B><BR>
- 위의 내용을 behavior parameterization 동작 파라미터화라 부름(기존에는 익명클래스로 구현함)<br>
예를 들어 약간만 다른 두 메서드 있다고 가정, 이때 두 메서드 그대로 유지하는 것 보다
인수 이용 다른 동작을 하도록 합치는게 바람직 할 수 있다.

- 인터페이스의 디폴트 메서드가 추가 됨

<BR>

1.1.2 스트림처리
-
스트림이란 한번에 한 개씩 만들어지는 연속적인 데이터 항목들의 모임.<br>
이론적으로 프로그램은 입력 스트림에서 데이터를 한개씩 읽어 들이며, 마찬가지로 출력스트림으로 데이터를 한개씩 기록<br>
즉, 어떤 프로그램의 출력 스트림은 다른 프로그램의 입력 스트림이 될 수 있다.<br>
일례로 유닉스,리눅스의 많은 프로그램은 표준 입력(유닉스와 c의 stdin, 자바의 System.in)에서 데이터를<br>
읽고, 처리하고, 결과  표준출력(유닉스,c:stdout, 자바 System.out)으로 기록.
유닉스의 cat은 두 파일 연결해서 스트림 생성하며,tr은 스트림 문자 번역하고,sort는 스트림의 행을 정렬<br>
tail -3은 스트림의 마지막 3개 행을 제공한다. 다음 예제처럼 유닉스 명령행에서는 |로 명령 연결 가능<br>

        cat file1 file2 | tr "[A-Z]"|sort| tail -3
        
        유닉스에서는 | 로 구분된 각 구간이 하나의 스트림이며 병렬로 스트림이 각자 처리될 수 있다.
        cat | tr | sort | tail

위 예제 사전순 정렬 뒤 가장마지막 세단어 출력

자바에서는 스트림 API가 이런 역할 수행Stream<T>는 T형식으로 구성된 일련의 항목 의미.<br>
즉, 스트림API가 조립라인 처럼 어떤 항목 연속으로 제공하는 어떤 기능이라고 단순하게 생각하자.<br>
위에서 유닉스가 복잡한 파이프 라인 구성했던 것처럼 스트림 API역시 파이프라인 만드는 다양한 메서드 제공<br>
->스레드라는 복잡한 작업을 하지 않아도 공짜로 병렬성 얻을 수 있는 이유.<br>

스트림2가지 문제 한번에 해결 (1.3.1)
- 컬렉션 처리시 발생 모호함과 반복적인 코드문제
- 멀티코어 활용 어려움 

기존 컬렉션에서는 데이터를 처리할 때 반복되는 패턴이 너무 많았다. 따라서 라이브러리에서 
이러한 반복되는 패턴을 제공한다면 좋을 것 이라는 아이디어가 변화의 동기가 되었다.

 - 필터링(filtering) 자주 반복되는 패턴으로 주어진 조건에 따라 필터링하거나,
 - 그룹화(grouping) 예를 들어 홀수 짝수로 그룹화
 - 추출(extracting) 리스트에서 각 사과의 무게 필드를 추출 
<br> 등의 기능이 있다.

<b>동작 병렬화:</b> 두 cpu 가진 환경에서 리스트를 필터링할 때 한cpu는 리스트 앞부분<br>
또 다른 cpu 리스트 뒷부분 처리하도록 요청 가능.<br>
->이 과정을 포킹단계forking step라 한다. -> 병렬처리진행 -> 마지막으로 하나의 cpu가 두 결과를 정리 

기존의 컬렉션: 데이터를 어떻게 저장하고 접근할지에 중점
스트림: 데이터에 어떤 계산을 할 것인지 묘사하는 것에 중점
    
    singleMultiCoreTest.java
    
    [순차처리 실행시간]
    Apples.stream().filter((Apple t)->t.getWeight()>20)
    실행 시간 : 0.049
    [basic.chapter1.Apple@378bf509]
    [병렬처리 실행시간]
    Apples.parallelStream().filter((Apple t2)->t2.getWeight()>20)
    실행 시간 : 0.003
    [basic.chapter1.Apple@378bf509]
    



1.1.3 동작 파라미터화로 메서드에 코드 전달하기<br>
-자바8 메서드(코드)를 다른메서드의 인수로 전달하는 기능 제공
 
람다: 람다 문법 형식으로 구현된 프로그램을 함수형 프로그래밍, 즉 '함수를 일급값으로 넘겨주는 프로그램을 구현한다'
라고한다.

1.4 디폴트메서드
자바8에서는 라이브러리 설계자 더 쉽게 변화할 수 있는 인터페이스 만들 수 있도록 디폴트 메서드 추가됨.<br>
-> 미래에 프로그램이 쉽게 변화할 수 있는 환경 제공하는 기능<br>
자바8 이전 List<T>가 스트림 지원하지 않아서 컴파일 불가<br>
-> 가장 간단한 해결책 직접 인터페이스 만들어서<br>
Collection인터페이스에 stream메서드 추가하고 구현하는것<br>
자바8개발자들이 인터페이스를 추가한 방식 
예를 들어 List인터페이스에 다음과 같은 디폴트 메서드 정의 추가되어서
우리는 List를 구현한 모든 클래스에서 sort를 사용할 수 있다.

    default void sort(Comparator<? super E> c) {
        Collections.sort(this, c);
    } 

자바 8 이전에는 List구현하는 모든 클래스가 sort 구현해야 했지만<br>
자바 8 부터는 디폴트sort구현하지 않아도 된다.<br>

->하나의 클래스 여러 인터페이스 구현가능 ->다중상속관련 문제발생->이는 9장에서자세히설명<br>


1.5 함수형 프로그램에서 가져온 다른 유용한 아이디어

자바에 포함된 함수형 프로그래밍의 핵심적 아이디어 2개
1. 메서드와 람다를 일급값으로 사용
2. 가변공유상태가 없는 병렬실행을 이용해서 효율적이고 안전하게 함수나 메서드 호출 가능

그밖의 아이디어
-null 회피기법
자바 8 Optional<T> 클래스를 제공
Optional<T>는 값을 갖거나 갖지 않을 수 있는 컨테이너 객체.
값이 없는 상황을 어떻게 처리할지 명시적으로 구현하는 메서드를 포함
즉, Optinal<T>사용하면 NullPointer예외를 피할 수 있다. 
NullObject패턴인듯


- 구조적 패턴 매칭 기법
패턴 매칭은 수학에서 다음 예제처럼 사용

    f(0) = 1
    f(n) = n*f(n-1)//그렇지 않으면

자바에서는 if-then else나 switch 문 사용 
다른언어에서는 이보다 더 정확한 비교 구현할 수 있다는 사실을 증명했다.
물론 자바에서도 다형성, 메서드 오버라이딩 을 이용해서 if-then-else대신하는 비교문 만들 수 있다<br>



Chapter 2 동작 파라미터화 코드 전달하기
-
요구사항은 항상 바뀐다.<br>
동작 파라미터화를 사용하면 자주 바뀌는 요구사항에 효과적으로 대응할 수 있다.<br>
동작 파라미터화란 아직 어떻게 움직일지 정해지지 않은 코드블록을 의미한다.<br>
예를 들어 차로 슈퍼마켓에 갔다오는 길을 알고있다.그래서 룸메이트에게 빵, 치즈 등의 식료품을<br>
사오라고 부탁했다.
이 경우 goAndBuy라는 메서드를 호출하면서 물품을 인수로 제공하는 것에 비유할 수 있다.<br>
그런데 룸메이트에게 우체국에 다녀와 달라는 부탁을 하고싶어졌다.하지만 goAndBuy메서드는<br>
우체국 업무보는 법을모른다. 이 경우 좀더 포괄적인 작업 수행할 수 있는 go 메서드 사용<br>
원하는 동작을 go메서드의 인수로 전달할 수 있다.<br>


2.2 동작 파라미터화
사과의 어떤 속성에 기초해서 불린값을 반환하는 방법이있다.<br>
이와 같은 동작을 프레디케이트 라고한다. 선택조건을 결정하는 인터페이스를 정의하자.<br>
Predicate 를 사용하는 건 스트래티지(전략)패턴을 이용하는 것과 같다.<br>
ApplePredicate 인터페이스를 상속받은 test 메소드를 구현하는 클래스를 각각 만들고<br>
test메소드에서 색, 무게등을 각각 묻도록 판별행위(코드)를 기술한다.<br>
즉, Predicate군을 만들어 알고리즘 패밀리를 만들고 상황에 따라 알맞은 패밀리를 불러낸다.<br>
이 방식이 아니면 직접 익명클래스를 통해 Predicate의 test()메소드를 구현하도록 하는 방법도 있다.<br>

            List<Apple> inventory = new ArrayList<>();
            inventory.add(new Apple("green",100));
            inventory.add(new Apple("red",50));
            inventory.add(new Apple("red",80));
            inventory.add(new Apple("green",120));
            
             ■Predicate인터페이스를 통해 구현할 코드를 알고리즘 패밀리화 할 수 있다.
             또한 이를 통해 전략 패턴 사용가능. 
             default메소드는 필요없다. 예제구현을 위해 기술한 것일 뿐
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
            
            ■알고리즘 패밀리 클래스를 통한 전략구현
            public class AppleHeavyWeightPredicate implements ApplePredicate {
                @Override
                public boolean test(Apple apple) {
                    return apple.getWeight() > 150;
                }
            }
            public class AppleGreenColorPredicate implements ApplePredicate {
                @Override
                public boolean test(Apple apple) {
                    return apple.getColor().equals("green");
                }
            }
           
            ■익명 클래스를 통한 구현
            List<Apple> redAppls =testclass.filterApples(inventory, new ApplePredicate() {
                @Override
                public boolean test(Apple apple) {
                    return "red".equals(apple.getColor());
                }
            });
    

<b> 익명클래스로도 아직 부족한 부분이있다. </b><br>

 - 첫째 익명클래스는 Predicate를 구현하는 클래스를 일일이 생성하지 않아도 되는 대신<br>
많은 공간을 차지한다. 

            List<Apple> redAppls =testclass.filterApples(inventory, new ApplePredicate() {
                @Override
                public boolean test(Apple apple) {

- 둘째 많은 프로그래머가 익명클래스 사용에 익숙치 않다. 
다음 코드의 결과는 4,5,6,42중 뭘까?

    public class MeaningOfThis {
         public vinal int value = 4;
         pulbic void doIt(){
            int value = 6;
            Runnable r = new Runnable(){
                public final int value = 5;
                public void run() {
                    int value = 10;
                    System.out.println(this.value);
                }
            }
         }
         r.run();
    }

답은 5다.

코드의 장황함은 나쁜 특성이다. 익명클래스로 인터페이스를 구현하는 과정을 조금 줄일 수 있지만<br>
여전히 만족스럽지 않다. 코드조각을 구현해야 한다는 점은 여전히 변치않는다.<br>

지금까지 동작 파라미터화를 이용하면 요구사항 변화에 더 유연하게 대응 할 수 있으므로 
<br> 동작파라미터화를 사용할 것을 권장하며 자바8에서는 람다표현식을 통해 이 문제를 해결했음을<br>
알게 될 것이다.<br>

    public interface Predicate<T>{
        boolean test(T t);
    }
    
    public static<T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for(T e: list) {
            if(p.test(e)){
            result.add(e)
            }
        }
    }
    
    List<Banana> yellowBananas = 
      filter(inventory, (Banana banana) ->"Yellow".equals(banana.getColor()));
      
다음과 같이 위에서 구현해 온 클래스에서 형식파라메터 T타입을 받게하고,<br>
람다표현을 통해 알고리즘패밀리의 코드를 구현해 동작파라메터를 전달하면 보다<br>
간편한 코드 구현이 가능하다.<br>
또한 자바에서 제공하는 다양한 API의 기능들도 람다를 통해 더욱 간결하게 구현할 수 있다.<br>

    java.util.Comparator<br>
    public interface Comparator<T>{
        public int compare(T o1,T o2);
    }
    
    inventory.sort(new Comparator<Apple>(){
        public int compare(Apple a1,Apple a2){
            return a1.getWeight().compareTo(a2.getWeight());    
        }
    });
    
    inventory.sort(
    (Apple a1,Apple a2) ->a1.getWeight().compareTo(a2.getWeight())
    );

CHAPTER3 람다표현식
-
3.1 람다란 무엇인가?<br>
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
람다가 기술적으로 자바 8 이전의 자바로 할 수 없었떤 이릉ㄹ 제공하는 것은 아니다.<br>
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
    
    
























