package generic;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;

public class BicDecimalTest {

    public static void main (String args[]){
        int inttest = 1;
        int inttest1 = 1;
        double doubletest1 = 0.1;
        int resultInt = 0;
        double resultDouble = 0.0;
        
        BigDecimal bicdecimal = new BigDecimal("1.1");
        BigDecimal bicdecima2 = new BigDecimal("1.1");
        

        double doubletest2 = 0.1;
        for(int i = 1; i < 3;i++){
            inttest += inttest1;
            doubletest1 += doubletest2;
        }

        System.out.println(doubletest1);
        System.out.println(inttest);
        System.out.println(bicdecimal.add(bicdecima2));
       // className cs = new className<String>();

    }

   class className<T> {
        public T info;
        public<J, R> void  mainMethod(J t) {

        }

    }
}
