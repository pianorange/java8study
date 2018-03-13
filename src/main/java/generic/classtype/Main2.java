package generic.classtype;

import java.io.IOException;
import java.util.ArrayList;

class classT<T> {
    public T temp;

    public T getTemp() {
        return temp;
    }

    public void setTemp(T temp) {
        this.temp = temp;
    }

        public <J,K,L> J testMethod(K j, L l) {

                J jj = null;

               return jj;
        }


}
public class Main2{

    public static void main(String args[]){
        Exclass ex = new Exclass();

        try {
            ex.test3();
        } catch (Exception e) {
            e.printStackTrace();
        }

        classT<String> temp = new classT<String>();
        temp.setTemp("되냐");
        System.out.println(temp.getTemp());
    }

}