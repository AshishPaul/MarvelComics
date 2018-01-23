package in.zerogravity.marvelcomics.ui.modules.home;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import hugo.weaving.DebugLog;
import in.zerogravity.marvelcomics.R;
import in.zerogravity.marvelcomics.ui.base.BaseActivity;
import in.zerogravity.marvelcomics.ui.base.BaseFragment;
import in.zerogravity.marvelcomics.ui.base.FragmentTransactionManager;
import in.zerogravity.marvelcomics.ui.base.ViewInfo;
import in.zerogravity.marvelcomics.ui.components.DialogFactory;
import in.zerogravity.marvelcomics.ui.modules.collections.CollectionsFragment;
import in.zerogravity.marvelcomics.ui.modules.favorite.FavoritesFragment;
import in.zerogravity.marvelcomics.ui.modules.notification.NotificationFragment;
import in.zerogravity.marvelcomics.ui.modules.profile.ProfileFragment;
import in.zerogravity.marvelcomics.utils.AndroidUtils;
import timber.log.Timber;

public class HomeActivity extends BaseActivity {

    public static final int TAB_HOME = 0x00;
    public static final int TAB_COLLECTIONS = 0x01;
    public static final int TAB_FAVORITES = 0x02;
    public static final int TAB_NOTIFICATIONS = 0x03;
    public static final int TAB_MY_PROFILE = 0x04;


    private static final String BUNDLE_KEY_SELECTED_TAB = "bundle_key_selected_tab";

    @BindView(R.id.home_bottomBar)
    BottomBar bottomBar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.appbar)
    AppBarLayout appbar;

//    @BindView(R.id.tv_toolbar_title)
    TextView tv_toolbar_title;

    @ViewInfo.TabPosition
    private int currentTabPosition = TAB_HOME;
    private long backPressed;

    private FragmentTransactionManager fragmentTransactionManager;
    private final OnTabSelectListener onTabSelectListener = tabId -> {
        switch (tabId) {
            case R.id.tab_home:
                showTabView(TAB_HOME);
                break;
            case R.id.tab_collections:
                showTabView(TAB_COLLECTIONS);
                break;
            case R.id.tab_favorites:
                showTabView(TAB_FAVORITES);
                break;
            case R.id.tab_notifications:
                showTabView(TAB_NOTIFICATIONS);
                break;
            case R.id.tab_my_profile:
                showTabView(TAB_MY_PROFILE);
                break;
        }
    };

    @Override
    protected void initializeDagger() {

    }

    @Override
    protected void initializePresenter() {

    }

    @Override
    protected void initializeToolbar() {

        setSupportActionBar(toolbar);
//        appbar.setPadding(0,AndroidUtils.getStatusBarHeight(this),0,AndroidUtils.getStatusBarHeight(this));
//        ((LinearLayout.LayoutParams)toolbar.getLayoutParams()).setMargins(0, AndroidUtils.getStatusBarHeight(this), 0, 0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void setPageTitle(String pageTitle) {
//        TextView tvTitle =
//        toolbar.setTitle(pageTitle);
        getSupportActionBar().setTitle(pageTitle);
//        tv_toolbar_title.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("onCreate");
//        initBackStackActionSet();
        fragmentTransactionManager = new FragmentTransactionManager(getSupportFragmentManager()) {
            @Override
            @DebugLog
            public BaseFragment getNewFragment(ViewInfo viewInfo) {
                return getBottomTabFragments(viewInfo);
            }
        };
        bottomBar.setOnTabSelectListener(onTabSelectListener, true);
        if (savedInstanceState != null) {
            int previouslySelectedTabPosition = savedInstanceState.getInt(BUNDLE_KEY_SELECTED_TAB);
            if (previouslySelectedTabPosition > 0) {
                currentTabPosition = previouslySelectedTabPosition;
            }
        }
        bottomBar.selectTabAtPosition(currentTabPosition);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_KEY_SELECTED_TAB, currentTabPosition);
        super.onSaveInstanceState(outState);
    }

    @DebugLog
    private BaseFragment getBottomTabFragments(@NonNull ViewInfo viewInfo) {
        BaseFragment baseFragment;
        switch (viewInfo.tabPosition()) {
            case TAB_HOME:
                baseFragment = HomeFragment.newInstance(viewInfo.data());
                break;
            case TAB_COLLECTIONS:
                baseFragment = CollectionsFragment.newInstance(viewInfo.data());
                break;
            case TAB_FAVORITES:
                baseFragment = FavoritesFragment.newInstance(viewInfo.data());
                break;
            case TAB_NOTIFICATIONS:
                baseFragment = NotificationFragment.newInstance(viewInfo.data());
                break;
            case TAB_MY_PROFILE:
                baseFragment = ProfileFragment.newInstance(viewInfo.data());
                break;
            default:
                throw new UnsupportedOperationException("Must provide a valid tabPosition");
        }
        setPageTitle(baseFragment.getPageTitle());
        return baseFragment;
    }

    @DebugLog
    private void showTabView(@ViewInfo.TabPosition int selectedTabPosition) {
        currentTabPosition = selectedTabPosition;
        changeTheme(selectedTabPosition);

        in.zerogravity.marvelcomics.data.model.BaseObject data;         //put any data to pass to the fragment inside the ViewInfo or any of its subclass.
        HomeViewInfo viewInfo = null;
        HomeViewInfo.Builder builder = HomeViewInfo.builder().
                parentId(R.id.fl_home_container).
                tabPosition(selectedTabPosition);
        switch (selectedTabPosition) {
            case TAB_HOME:
                data = null;
                viewInfo = builder
                        .addToStack(true)
                        .tag(HomeFragment.class.getSimpleName())
                        .data(data).build();
                break;
            case TAB_COLLECTIONS:
                data = null;
                viewInfo = builder.
                        addToStack(true).
                        tag(CollectionsFragment.class.getSimpleName()).
                        data(data).build();
                break;
            case TAB_FAVORITES:
                data = null;
                viewInfo = builder.
                        addToStack(true).
                        tag(CollectionsFragment.class.getSimpleName()).
                        data(data).build();
                break;
            case TAB_NOTIFICATIONS:
                data = null;
                viewInfo = builder.
                        addToStack(true).
                        tag(CollectionsFragment.class.getSimpleName()).
                        data(data).build();
                break;
            case TAB_MY_PROFILE:
                data = null;
                viewInfo = builder.
                        addToStack(true).
                        tag(CollectionsFragment.class.getSimpleName()).
                        data(data).build();
                break;
        }

        fragmentTransactionManager.addOrReplaceFragment(viewInfo);
    }

    private void changeTheme(@ViewInfo.TabPosition int selectedTabPosition) {
        switch (selectedTabPosition) {
            case TAB_MY_PROFILE:
                appbar.setElevation(AndroidUtils.dpToPixelFromDimenResources(this,R.dimen.spacing_tiny));
                appbar.setBackground(new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimaryMyProfile)));
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDarkMyProfile));
                break;
            case TAB_NOTIFICATIONS:
                appbar.setElevation(AndroidUtils.dpToPixelFromDimenResources(this,R.dimen.spacing_tiny));
                appbar.setBackground(new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimaryNotification)));
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDarkNotification));
                break;
            case TAB_FAVORITES:
                appbar.setElevation(AndroidUtils.dpToPixelFromDimenResources(this,R.dimen.spacing_tiny));
                appbar.setBackground(new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimaryFavorite)));
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDarkFavorite));
                break;
            case TAB_COLLECTIONS:
                appbar.setElevation(0);
                appbar.setBackground(new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimaryCollection)));
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDarkCollection));
                break;
            case TAB_HOME:
            default:
                appbar.setElevation(AndroidUtils.dpToPixelFromDimenResources(this,R.dimen.spacing_tiny));
                appbar.setBackground(new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimaryHome)));
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDarkHome));
        }
    }


    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        fm.executePendingTransactions();

        Fragment currentFragment = getCurrentFragment();

        int backStackCount = fm.getBackStackEntryCount();
        if (backStackCount == 0 && currentFragment != null && !(currentFragment instanceof HomeFragment))
            bottomBar.selectTabWithId(R.id.tab_home);
        else if (currentFragment != null && (currentFragment instanceof HomeFragment)) {
            if (backPressed + 2000 > System.currentTimeMillis()) {
                finish();
            } else {
                DialogFactory.createSnackBar(this, findViewById(R.id.home_coordinator_layout), getString(R.string.alert_home_backpress)).show();
            }
            backPressed = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }

    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.fl_home_container);
    }

    public int getCurrentTabPosition() {
        return currentTabPosition;
    }
}
