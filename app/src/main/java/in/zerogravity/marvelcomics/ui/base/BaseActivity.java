package in.zerogravity.marvelcomics.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity implements BaseView{
    private static final String BUNDLE_KEY_SELECTED_THEME = "key_selected_theme";
    protected Presenter presenter;



//    @Nullable
//    @BindView(R.id.ic_toolbar_setting)
//    ImageView icSettings;
//
//    @Nullable
//    @BindView(R.id.ic_toolbar_refresh)
//
//    protected ImageView icHome;

    private Unbinder unbinder;

//    @StyleRes
//    private int currentTheme = R.style.AppTheme;

    protected abstract void initializeDagger();

    protected abstract void initializePresenter();

    protected abstract void initializeToolbar();

    protected abstract int getLayoutId();

    protected abstract void setPageTitle(String pageTitle);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        setTheme(currentTheme);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        initializeDagger();
        initializePresenter();
        initializeToolbar();

        if (presenter != null) {
            presenter.initialize(getIntent().getExtras());
        }
    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        outState.putInt(BUNDLE_KEY_SELECTED_THEME,currentTheme);
//        super.onSaveInstanceState(outState);
//    }
//
//    protected void setCurrentTheme(@StyleRes int theme){
//        this.currentTheme = theme;
//        TaskStackBuilder.create(this)
//                .addNextIntent(getIntent())
//                .startActivities();
//    }

    @Override
    protected void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.finalizeView();
        }
    }

//    protected void initializeToolbar() {
//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
//            getSupportActionBar().setTitle("");
//        }
//    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
