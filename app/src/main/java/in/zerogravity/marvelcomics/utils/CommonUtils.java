package in.zerogravity.marvelcomics.utils;


public class CommonUtils {
    public static void checkNullAndThrow(Object object){
        if(object==null)
            throw new NullPointerException("");
    }
}
