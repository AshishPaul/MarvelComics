package in.zerogravity.marvelcomics;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import in.zerogravity.marvelcomics.data.CharacterDataManager;


public class AppController extends Application {

    private static AppController instance;
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // Should not init your app in this process.
            //TODO Requires understanding (no idea what this means)
            return;
        }
        refWatcher = LeakCanary.install(this);

        LoggingModule.init();
        NetworkModule.init(this);
        DatabaseModule.init(this);


    }
    public static AppController getInstance() {
        return instance;
    }

    public static RefWatcher getRefWatcher(Context context) {
        AppController application = (AppController) context.getApplicationContext();
        return application.refWatcher;
    }


    public CharacterDataManager getCharacterDataManager(){
        return  new CharacterDataManager(DatabaseModule.getDbHelper(), NetworkModule.getApiHelper());
    }


}
