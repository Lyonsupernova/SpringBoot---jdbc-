package log4j;

import org.apache.log4j.Logger;
public class test {
 public static Logger lo=Logger.getLogger(test.class);
    public static void main(String[] args) {
        try {
            int result= 5/0;
        } catch (Exception e) {
            System.out.println("除数不能为零");
            lo.debug("除数不能为0");
        }

    }
}
