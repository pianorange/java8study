package basic.chapter1.methodreferance;

@FunctionalInterface
public interface PracInterface<T,U,V,R> {

    R apply(T t,U u,V v);

}
