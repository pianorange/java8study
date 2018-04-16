package basic.chapter1.examplecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class Main {
    //1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오
    //2. 거래자가 근무하는 모든 도시 중복없이 나열
    //3. 케임브리지에서 근무하는 모든 거래자 찾아 이름순 정렬
    //4. 모든 거래자의 이름 알파벳순으로 정렬해서 반환
    //5. 밀라노에 거래자가 있는가
    //6. 케임브리지 거주 거래자의 모든 트랜잭션값 출력
    //7. 전체 트랜잭션 중 최댓값은 얼마인가.
    //8. 전체 트랜잭션 중 최솟값은 얼마인가.

    //1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오
    public void practice1( List<Transaction> transactions ){
        List<Transaction> transactionStream = transactions.stream()
                                                            .filter(t -> t.getYear() == 2011)
                                                            .sorted(comparing(Transaction::getValue))
                                                            .collect(toList());
        transactionStream.stream().forEach(System.out::println);
    }

    //2. 거래자가 근무하는 모든 도시 중복없이 나열
    public void practice2(List<Transaction> transactions){
        List<String> citylist = transactions.stream()
                                            .map(t -> t.getTrader().getCity())
                                            .distinct()
                                            .collect(toList());
        citylist.stream().forEach(System.out::println);
    }

    //3. 케임브리지에서 근무하는 모든 거래자 찾아 이름순 정렬
    public void practice3(List<Transaction> transactions){
        List<String> namelist = transactions.stream()
                                            .filter(t -> t.getTrader().getCity() == "Cambridge")
                                            .map(t -> t.getTrader().getName())
                                            .distinct()
                                            .sorted()
                                            .collect(toList());
        System.out.println("My Practice3 answer");
        namelist.stream().forEach(System.out::println);
    }
    public void practice3_1(List<Transaction> transactions){
        List<Trader> traders = transactions.stream()
                                .map(Transaction::getTrader)
                                .filter(trader -> trader.getCity().equals("Cambridge"))
                                .distinct()
                                .sorted(comparing(Trader::getName))
                                .collect(toList());
        System.out.println("Practice3_1");
        traders.stream().forEach(System.out::println);
    }

    //4. 모든 거래자의 이름 알파벳순으로 정렬해서 하나의 문자열로 반환
    public void practice4(List<Transaction> transactions){
        String names = transactions.stream()
                    .map(t->t.getTrader().getName())
                    .distinct()
                    .sorted()
                    .reduce("",(n1, n2) -> n1 + n2);
                //reduce 는 두개의 인수를 갖는다.
                // 1.초기값 , 2.두개의 인수를 하나로 만드는 BinaryOperator  (n1, n2) ->n1+n2
                //초기값 accumulator가 없어도 되도록 오버라이드된 reduce는
                //인수가 없을 경우 대비해 Optional 을 반환한다.
        System.out.println("practice4" + names);
    }

    //5. 밀라노에 거래자가 있는가
    public void practice5(List<Transaction> transactions){

    }

    //6. 케임브리지 거주 거래자의 모든 트랜잭션값 출력
    public void practice6(List<Transaction> transactions){

    }

    //7. 전체 트랜잭션 중 최댓값은 얼마인가.
    public void practice7(List<Transaction> transactions){

    }

    //8. 전체 트랜잭션 중 최솟값은 얼마인가.
    public void practice8(List<Transaction> transactions){

    }
    public static void main(String args[]){
        Trader raoul = new Trader("Raoul","Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 100),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

    }


}
