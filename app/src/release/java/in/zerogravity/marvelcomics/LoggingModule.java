package in.zerogravity.marvelcomics;


public class LoggingModule {
    public static void init() {
        //Initialize 3rd party crash reporting e.g Crashlytics
        Timber.plant(new ReleaseTree());
    }
}
