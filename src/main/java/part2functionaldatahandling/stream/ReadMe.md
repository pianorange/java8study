Introduction to Stream
-

- 컬랙션 자바에서 가장 많이 사용하는 기능 중 하나. 
- 거의 모든 자바 애프리 케이션 컬랙션 만들고 처리하는 과정 포함.
- 대부분의 비지니스 로직 그룹화, 찾기 등 연산 포함
- sql 질의의 경우 속성을 이용하여 어떻게 필터링 할 것인지 구현을 명시할 필요가 없음.
```
SELECT id FROM mebers WHERE  salary > 3000
```
- Stream 덕분에 컬랙션으로도 비슷한 질의형 기능을 만들수 있게 되었으며, <br>
멀티코어 아키텍처를 활용해서 병렬로 컬랙션 요소를 투명하게 처리할 수 있게 되었다.

- 스트림 이용 선언형(데이터를 처리하는 임시 구현 코드 대신 질의로 표현 가능)으로 컬랙션 처리가능.

<JAVA 7>
```
List<DIsh> lowCaloricDishes = new ArrayList<>();
for(Dish d: menu){
    if(d.getCalories() < 400){
        lowCaloricDishes.add(d);
    }
}

Collections.sort(lowCaloricDishes , new Comparator<Dish>(){
    public int compare(Dish d1, Dish d2){
        return Integer.compare(d1.getCalories(), d2.getCalories());
    }
});




```
