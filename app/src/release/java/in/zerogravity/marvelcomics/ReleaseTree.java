package in.zerogravity.marvelcomics;


import android.util.Log;

import timber.log.Timber;

public class ReleaseTree extends Timber.Tree {
    private static final int MAX_LOG_LENGTH = 4000;

    @Override
    protected boolean isLoggable(String tag, int priority) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
            return false;
        }
        //Only log ERROR, WARN, WTF
        return super.isLoggable(tag, priority);
    }

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        if (isLoggable(tag, priority)) {
            //Report to 3rd party Library e.g Crashlytics.

            //Report to Logcat
            //message is short enough, doesn't need to be broken into chunks
            if (message.length() < MAX_LOG_LENGTH) {
                if (priority == Log.ASSERT) {
                    Log.wtf(tag, message);
                } else {
                    Log.println(priority, tag, message);
                }
                return;
            }

            //split by line, then ensure each line can fit into Log's maximum length
            for (int i = 0, length = message.length(); i < length; i++) {
                int newLine = message.indexOf("\n", i);
                newLine = newLine != -1 ? newLine : length;
                do {
                    int end = Math.min(newLine, i + MAX_LOG_LENGTH);
                    String part = message.substring(i, end);
                    if (priority == Log.ASSERT) {
                        Log.wtf(tag, part);
                    } else {
                        Log.println(priority, tag, part);
                    }
                    i = end;
                } while (i < newLine);
            }
        }




    }
}
