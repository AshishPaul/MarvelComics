package in.zerogravity.marvelcomics;


import android.content.Context;

import in.zerogravity.marvelcomics.data.DbHelper;
import in.zerogravity.marvelcomics.data.local.db.MockDataBaseImpl;
import in.zerogravity.marvelcomics.data.local.db.MockDbHelper;

public class DatabaseModule {
    public static void init(Context applicationContext){

    }

    public static DbHelper getDbHelper() {
        return new MockDbHelper(new MockDataBaseImpl());
    }
}
