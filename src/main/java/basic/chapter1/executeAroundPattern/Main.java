package basic.chapter1.executeAroundPattern;

import java.io.BufferedReader;
import java.io.IOException;

public class Main {

    public static void main(String args[]){
        exucuteAroudPatternClass excuteclass = new exucuteAroudPatternClass();
        try {
            excuteclass.processFile((BufferedReader br) ->
                                                br.readLine()   );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
