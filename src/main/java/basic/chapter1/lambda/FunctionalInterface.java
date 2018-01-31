package basic.chapter1.lambda;

public class FunctionalInterface {

    public void test(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.print("HelloWorld");
            }
        };
    }


    public static void main(String args[]){
        //모양만 보면 인터페이스가 인스턴스화 된 것 같아 이상하지만,
        //잘생각해보면
        InterfaceInstanceTest i1 = new InterfaceInstanceTest() {
            public  void run(){
                System.out.println("인터페이스가 인스턴스화 되는게아니라 익명클래스 선언통해 인터페이스를 구현한" +
                        "Concrete클래스가 인스턴스화되는것");
            };
        };

        InterfaceInstanceTest i2 = () -> System.out.println("람다");

        System.out.println();
    }
    }
