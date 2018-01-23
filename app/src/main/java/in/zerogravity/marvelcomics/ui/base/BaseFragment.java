package in.zerogravity.marvelcomics.ui.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.zerogravity.marvelcomics.AppController;
import in.zerogravity.marvelcomics.R;
import in.zerogravity.marvelcomics.data.model.BaseObject;
import timber.log.Timber;

public abstract class BaseFragment extends Fragment implements BaseView {
    protected static final String BUNDLE_KEY_DATA = "bundle_key_data";
    protected FragmentManager fragmentManager;

    protected Presenter presenter;
    protected View view;
    private Unbinder unbinder;
    protected BaseObject data;
    private String toolbarTitleKey;

    protected abstract void initializeDagger();

    protected abstract void initializePresenter();


    @LayoutRes
    protected abstract int getLayoutId();

    public abstract String getPageTitle();

    protected abstract void setData(BaseObject data);

    public BaseObject getData(){
        return this.data;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i(getPageTitle() + ":onCreate");
        fragmentManager = getActivity().getSupportFragmentManager();
        initializeDagger();
        initializePresenter();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Timber.i(getPageTitle() + ":onCreateView");
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), getTheme());
        // clone the inflater using the ContextThemeWrapper
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        // inflate the layout using the cloned inflater, not default inflater
        view = localInflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        if (presenter != null) {
            presenter.initialize(getArguments());
        }
        return view;
    }

    @StyleRes
    protected int getTheme(){
        return R.style.AppTheme;
    }

    @Override
    public void onStart() {
        super.onStart();
        Timber.i(getPageTitle() + ":onStart");
        if (presenter != null) {
            presenter.start();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Timber.i(getPageTitle() + ":onStop");
        if (presenter != null) {
            presenter.finalizeView();
        }
    }

    @Override
    public void onDestroyView() {
        Timber.i(getPageTitle() + ":onDestroyView");
        unbinder.unbind();
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        Timber.i(getPageTitle() + ":onDestroy");
        super.onDestroy();
        RefWatcher refWatcher = AppController.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
