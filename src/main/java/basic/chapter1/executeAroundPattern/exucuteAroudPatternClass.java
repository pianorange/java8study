package basic.chapter1.executeAroundPattern;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class exucuteAroudPatternClass {

    public static String processFile(BufferedReaderProcessor b) throws IOException{
        try(BufferedReader br =
                new BufferedReader(new FileReader("data.txt"))){
            return b.process(br);
        }
    }
}
