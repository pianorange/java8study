3.7 람다 ,메서드 레퍼런스 활용하기
-
다음 예제에서 동작 파라미터화, 익명 클래스, 람다, 메서드레퍼런스 등 총동원<br>
리스트의 정렬 예제

3.7.1 1단계 코드전달
-
자바8의 List API는 sort메서드를 제공. but 정렬전략 전달할 필요가 있다.<br>

```
void sort(Comparator<? super E> c)
```
-  일단 여기서 List api의  sort메서드의 시그니처를 확인. Comparator인터페이스를
구현하는 클래스가 인수로 들어간다는 것을 확인. 
<br>
1단계 인수로 보낼 Comparator의 구현클래스 를 만든다.
```
public class AppleComparator implements Comparator<Apple> {
    @Override
    public int compare(Apple o1, Apple o2) {
        return o1.getWeight().compareTo(o2);
    }
}
```
하지만 한번 사용할 구현클래스라면 익명클래스로 구현하는게 간단할 수 있다.
```
List<Apple> inventory = new ArrayList<>();
inventory.sort(new Comparator<Apple>(){
                 public int compare(Apple a1, Apple a2){
                    return a1.getWeight().compareTo(a2.getWeight());
                 }     
});
```

3.7.3 3단계 람다 표현식 사용
-
- 코드가 아직도 장황한 편이다. 람다표현식이라는 경량화된 문법으로 코드를 전달할 수 있다.
- <b>함수형 인터페이스를 기대하는 곳</b> 어디서나 람다표현식을 사용할 수 있다.
- 함수형 인터페이스는 오직 하나의 추상메서드 가지며,추상메서드의 시그니처(함수디스크립터라 불림)는
람다표현식의 시그니처를 정의한다.
- Comparator의 함수 디스크립터는 (T, T) -> int 다.
-
```
inventory.sort( (a1,a2)-> a1.getWeight().compareTo(a2.getWeight()) );
```
- 자바 컴파일러는 람다 표현식이 사용된 콘텍스트 활용 람다의 파라미터 형식을 추론하므로 (a1,a2) 가 Apple임을 알수있다.

- Comparator는 Comparable 키를 추출해서 Comparator객체로 만드는 Function 함수 인수로 받는 정적 메서드 comparing 포함.

-

                                                                                                                                     










