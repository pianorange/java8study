Tuple 라이브러리
-
- 우리는 종종 한번의 메소드 호출로 다수의 객체 반환받는것 원함.
- return문에는 하나의 객체만 지정 가능.
- 따라서, 반환하고 싶은 여러 객체 저장하고 잇는 객체를 생성하는 것이 해결책.
- but 제네릭은 더 간단하게 문제 해결가능하며, 컴파일 시점에서 타입 안전성 확인 가능.

```
    public class TwoTuple<A,B> {
        
        public final A first;
        public final B second;
        
        public TwoTuple (A a, B b) {first = a; second = b;}
        
        public String toString() {
           reutrn first+ second;
        }
    
    }
```

- 이런 개념을 tuple 이라고 하며 그룹의 객체들을 함께 포장, 단일객체로 만드는 것. 이때 데이터받은 쪽에서는 읽기만 가능. 
- 투플에 저장된 각 객체는 서로 다른 타입이 될 수 있다.
- 우리는 각 객체의 타입을 지정하여 받은 쪽에서 그 값을 읽을 때 확실히 올바른 타입을 얻기를 원한다.
- 투플 생성자에서 각 요소들 순서대로 보존, final로 데이터 변질 방지. (다른값 넣을 수 없으므로)
- 투플의 길이는 상속을 통해 확장 가능

```
public class ThreeTuple<A,B,C> extends TwoTuple<A,B> {
    public final C third;
    public ThreeTuple (A a, B b, C c) {
        super (a, b);
        third = c;
    }
}
```

