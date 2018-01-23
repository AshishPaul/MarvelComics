package in.zerogravity.marvelcomics;


import timber.log.Timber;

public class LoggingModule {
    public static void init(){
        Timber.plant(new Timber.DebugTree(){
            @Override
            protected String createStackElementTag(StackTraceElement element) {
                return super.createStackElementTag(element)+": Line "+element.getLineNumber()+":";
            }
        });
    }
}
