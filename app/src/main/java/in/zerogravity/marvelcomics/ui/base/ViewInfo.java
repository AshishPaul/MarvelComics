package in.zerogravity.marvelcomics.ui.base;


import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import in.zerogravity.marvelcomics.data.model.BaseObject;

import static in.zerogravity.marvelcomics.ui.modules.home.HomeActivity.TAB_COLLECTIONS;
import static in.zerogravity.marvelcomics.ui.modules.home.HomeActivity.TAB_FAVORITES;
import static in.zerogravity.marvelcomics.ui.modules.home.HomeActivity.TAB_HOME;
import static in.zerogravity.marvelcomics.ui.modules.home.HomeActivity.TAB_MY_PROFILE;
import static in.zerogravity.marvelcomics.ui.modules.home.HomeActivity.TAB_NOTIFICATIONS;

public interface ViewInfo {
    @TabPosition
    int tabPosition();

    String tag();

    int parentId();

    BaseObject data();

    boolean addToStack();

    @IntDef({TAB_HOME, TAB_COLLECTIONS, TAB_FAVORITES, TAB_NOTIFICATIONS, TAB_MY_PROFILE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TabPosition {
    }
}
