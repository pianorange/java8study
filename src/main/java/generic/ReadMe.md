
Generic(JAVA)
- 

이 패키지에서는 자바 5의 신기능으로 등장한 자바 제네릭에 대해 정리한다.

	• 제네릭은 자바 5에서추가된 신기능
	• 객체지향 언어 일반화(generalization)허용하는 한가지 방법은 다형성(polymorphism)이다.
	• 일반화된 메소드를 작성한다고 가정해보자.
	→ 파라미터를 상속관계의 베이스가 되는 클래스 타입(부모 클래스)로 지정 
	→ 인터페이스 타입으로 받고 인터페이스 구현 통해 더 유연한(일반화된) 코드 구현가능
	→ 때로는 인터페이스조차 너무 제한적, 여전히 특정 인터페이스 타입과 동작할 필요가 있기때문.
	■ 만일 우리 코드가 특정 인터페이스나 클래스가 아닌 어떤 미지정 타입과 동작할 수 있다면 더욱 
	일반화된 코드를 작성할 수 있다.
	
	→바로 이것이 제네릭의 개념이며 자바 SE5의 중대한 변화중 하나이다.

제네릭의 특징

	- 제네릭은 파라미터화 타입(parameterized type)개념 구현.다수의 타입 허용한다.
	- 용어'제네릭'은 대규모 그룹의 클래스들과 관련되거나 적합함을 의미
	- 본래의 취지는 클래스나 메소드 작성시 사용하는 타입의 제약을 완화함으로서 최대한의 표현을 허용하는것
	- →하지만, 자바의 제네릭 구현은생각보다 광범위 하지 않다.

간단한 제네릭

	- 제네릭이 끌리는 이유 중 하나는 컨테이너 클래스 생성에 있다. 컨테이너는 객체를 저장하는곳. 
	- 컨테이너 클래스는 별거없고그냥 생성자에 객체받아서 private변수에 인스턴스 담아주는 객체인듯.
	- →위의 컨테이너 클래스는 생성자 파라미터타입 고정 재사용 가능한 도구가 아니다. 
	- So 자바 SE5이전에는 Object를 파라미터로 받았다.

      public class Holder {
        private Object a;
        public Holder (Object a) { this.a = a;  }
        public void set (Object a) { this.a = a; }
        public Object get ( ) ( return a; )
        
        public static void main (String args[]){
            Holder h = new Holder (new Coffe( ));
            Coffe coffe = (Coffe) h.get();
            
            h.set("Not an Coffe");
            String s = (String) h.get();
            
            // AutoBoxes to Integer
            h.set(1);
            Integer x = (Integer)h2.get();
        }
        
      }

	- 위 처럼 Object를 받으면 컨테이너 클래스에 어떤 것이든 저장 가능. 위의 예는 3가지 타입 저장.( Coffe, String, Integer)
	
	- 여러 타입의 객체 저장 컨테이너 필요한 경우 일반적으로 한가지 타입 객체만 하나의 컨테이너에 넣는다.
	→제네릭 필요한 주요한 이유는 컨테이너가 저장하는 객체 타입 지정 컴파일러가 그런 명세 지원하도록 하는것.
	→따라서 Object대신 나중에 결정될 수 있는 미지정 타입 사용.
	클래스명 <타입>
    
    타입파라미터 : 클래스명 다음에 오는 제네릭타입
    
    public class Holder3<T> {
        private T a;
        public Holder3 (T a) { this.a = a; }
        public void set (T a) { this.a = a; }
        public T get ( ) { return a; }
    
        public static void main(String args[]){
            Holder3<Coffe> h3 = 
                    new Holder3<Coffe>(new Coffe());
            Coffe coffe = h3.get(); // Casting 필요없다.
            //h3.set("String"); //에러
            //h3.set(1); // 에러
        }
    }
   
   - 제네릭을 사용하여 클래스의 타입파라미터를 지정했으므로, 객체 생성시 꺽쇠를 이용,<br>
   우리가 원하는 타입을 지정. -> 그 타입의 객체만 컨테이너에 저장가능.<br>
   (타입 유연성 제공위해 파라미터를 Object타입으로 사용할 때와 다르게 Stirng, Integer래퍼 클래스 못들어가지)     
   
   - 타입 보장되므로 컨테이너에서 인스턴스 꺼낼때도 캐스팅 필요없음. 
   