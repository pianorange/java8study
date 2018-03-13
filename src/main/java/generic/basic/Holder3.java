package generic.basic;

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

        String test = "1111";
        Integer.parseInt(test);
    }
}
