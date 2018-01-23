package in.zerogravity.marvelcomics.ui.modules.home;


import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import in.zerogravity.marvelcomics.ui.base.ViewInfo;

@AutoValue
public abstract class HomeViewInfo implements ViewInfo,Parcelable {

    @Override
    @TabPosition
    public abstract int tabPosition();

    @Override
    public abstract String tag();

    @Override
    public abstract int parentId();

    @Override
    @Nullable
    public abstract in.zerogravity.marvelcomics.data.model.BaseObject data();

    @Override
    public abstract boolean addToStack();

    public static Builder builder(){
        return new AutoValue_HomeViewInfo.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder{

        abstract Builder tabPosition(int tabPosition);
        abstract Builder tag(String tag);

        abstract Builder parentId(@IdRes int viewId);

        abstract Builder data(@Nullable in.zerogravity.marvelcomics.data.model.BaseObject data);

        abstract Builder addToStack(boolean addToBackStack);

        abstract HomeViewInfo build();
    }
}
