package in.zerogravity.marvelcomics.ui.modules.collections;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import butterknife.BindView;
import hugo.weaving.DebugLog;
import in.zerogravity.marvelcomics.AppController;
import in.zerogravity.marvelcomics.R;
import in.zerogravity.marvelcomics.data.model.BaseObject;
import in.zerogravity.marvelcomics.ui.base.BaseFragment;
import in.zerogravity.marvelcomics.ui.modules.collections.characters.CharactersFragment;
import in.zerogravity.marvelcomics.ui.modules.collections.comics.ComicsFragment;
import in.zerogravity.marvelcomics.ui.modules.collections.creators.CreatorsFragment;
import in.zerogravity.marvelcomics.ui.modules.collections.stories.StoriesFragment;

public class CollectionsFragment extends BaseFragment {

    @BindView(R.id.collections_tabs_layout)
    TabLayout collections_tabs_layout;
    @BindView(R.id.collections_view_pager)
    ViewPager collections_view_pager;
    private CollectionsPagerAdapter mCollectionsPagerAdapter;
    private final CollectionsPagerAdapter.PagerItemDelegate collectionTabPagerItemDelegate = new CollectionsPagerAdapter.PagerItemDelegate() {
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return CharactersFragment.newInstance(null);
                case 1:
                    return ComicsFragment.newInstance(null);
                case 2:
                    return CreatorsFragment.newInstance(null);
                case 3:
                    return StoriesFragment.newInstance(null);
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public String getTabTitle(int position) {
            return ((BaseFragment) mCollectionsPagerAdapter.getItem(position)).getPageTitle();
        }
    };

    public CollectionsFragment() {

    }

    @DebugLog
    public static CollectionsFragment newInstance(BaseObject data) {
        CollectionsFragment fragment = new CollectionsFragment();
        if (data != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(BUNDLE_KEY_DATA, data);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected void initializeDagger() {

    }

    @Override
    protected void initializePresenter() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_collections;
    }

    @Override
    public String getPageTitle() {
        return AppController.getInstance().getString(R.string.title_collections);
    }

    @Override
    public void setData(BaseObject data) {
        this.data = data;
        if (data != null) {
            Bundle args = this.getArguments();
            if (args != null) {
                args.putParcelable(BUNDLE_KEY_DATA, data);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    protected int getTheme() {
        return R.style.AppTheme_Collection;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCollectionsPagerAdapter = new CollectionsPagerAdapter(getChildFragmentManager(), collectionTabPagerItemDelegate);
        collections_view_pager.setAdapter(mCollectionsPagerAdapter);
        collections_tabs_layout.setupWithViewPager(collections_view_pager);
    }

}
