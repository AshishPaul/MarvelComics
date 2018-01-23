package in.zerogravity.marvelcomics.utils;


import android.content.Context;
import android.support.annotation.DimenRes;

public class AndroidUtils {
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static float dpToPixelFromDimenResources(Context context, @DimenRes int dpFromDimen) {
        return context.getResources().getDimensionPixelSize(dpFromDimen);
    }

}
