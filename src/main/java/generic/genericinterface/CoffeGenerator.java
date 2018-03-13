package generic.genericinterface;

import java.util.Iterator;
import java.util.Random;
import java.util.Spliterator;
import java.util.function.Consumer;

public class CoffeGenerator implements  Generator<Coffe>, Iterable<Coffe> {

    private Class[] types = {Latte.class, Mocha.class, Americano.class};
    private static Random random = new Random(20);
    public  CoffeGenerator(){}
    //iteration
    private int size = 0;

    public  CoffeGenerator(int sz) { size = sz; }

    @Override
    public Coffe next() {
        try{
            return (Coffe) types[random.nextInt(types.length)].newInstance();

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    class CoffeIterator implements  Iterator<Coffe> {
        int count = size;

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public Coffe next() {
            count--;
            return CoffeGenerator.this.next();
        }
    }
    @Override
    public Iterator<Coffe> iterator() {
        return new CoffeIterator();
    }

    public static void main (String args[]){
        CoffeGenerator gen = new CoffeGenerator();
        for(int i=0; i < 5; i++){
            System.out.println("gen.next"+gen.next());
        }

        //Iterable 인터페이스를 구현하므로 foreach문에서 사용가능.
        //그러나 반복 범위 지정위해 끝 표시 필요. (5)
        for (Coffe c: new CoffeGenerator(5)){
            System.out.println(c);
        }
    }
}
