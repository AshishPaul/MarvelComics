package in.zerogravity.marvelcomics.ui.modules.collections.creators;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.zerogravity.marvelcomics.AppController;
import in.zerogravity.marvelcomics.R;
import in.zerogravity.marvelcomics.data.AppDataManager;
import in.zerogravity.marvelcomics.data.CharacterDataManager;
import in.zerogravity.marvelcomics.data.model.BaseObject;
import in.zerogravity.marvelcomics.data.model.MarvelCharacter;
import in.zerogravity.marvelcomics.ui.base.BaseFragment;
import in.zerogravity.marvelcomics.ui.components.BaseRecyclerAdapter;
import in.zerogravity.marvelcomics.ui.components.EmptyViewClickListener;
import in.zerogravity.marvelcomics.ui.components.ListItemClickListener;
import in.zerogravity.marvelcomics.ui.components.ScrollAnimator;
import in.zerogravity.marvelcomics.ui.components.SlideInAnimator;
import in.zerogravity.marvelcomics.ui.model.CharacterUiModel;
import in.zerogravity.marvelcomics.exception.BaseException;
import timber.log.Timber;


public class CreatorsFragment extends BaseFragment {


    @BindView(R.id.creators_recycler_view)
    RecyclerView creators_recycler_view;

    private final ListItemClickListener<CharacterUiModel> listItemClickListener = item->onListItemClick(item);
    private final EmptyViewClickListener emptyViewClickListener = ()->onRetryClick();
    private CreatorsAdapter creatorsAdapter;
    private LinearLayoutManager layoutManager;
    private RecyclerView.ItemAnimator itemAnimator;
    private ScrollAnimator scrollAnimator;
    private CharacterDataManager dataManager;

    public CreatorsFragment() {

    }

    public static CreatorsFragment newInstance(BaseObject data) {
        CreatorsFragment fragment = new CreatorsFragment();
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
        return R.layout.fragment_creators;
    }


    @Override
    public String getPageTitle() {
        return AppController.getInstance().getString(R.string.title_creators);
    }

    @Override
    public void setData(BaseObject data) {
        this.data = data;
        Bundle args = this.getArguments();
        if (args != null) {
            args.putParcelable(BUNDLE_KEY_DATA, data);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        creatorsAdapter = new CreatorsAdapter(listItemClickListener,emptyViewClickListener);
        scrollAnimator = new SlideInAnimator();
        creatorsAdapter.setScrollAnimator(scrollAnimator);

        dataManager = ((AppController) getActivity().getApplication()).getCharacterDataManager();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initializeCharacterListView();
        getCharacterList();

    }

    private void initializeCharacterListView() {
        layoutManager = new LinearLayoutManager(getActivity());
        itemAnimator = new DefaultItemAnimator();
        creators_recycler_view.setAdapter(creatorsAdapter);
        creators_recycler_view.setLayoutManager(layoutManager);
        creators_recycler_view.setItemAnimator(itemAnimator);
    }

    private void getCharacterList() {

        dataManager.getCharacterCollection(true,20,
                new AppDataManager.Callback<List<MarvelCharacter>>() {
                    @Override
                    public void onSuccess(List<MarvelCharacter> result) {
                        creatorsAdapter.setData(convertToModelViewList(result));
                    }

                    @Override
                    public void onError(BaseException error) {
                        Timber.d("getCharacterList:onError");
                        if(creatorsAdapter.getCurrentListState()== BaseRecyclerAdapter.NORMAL){
                            //that means the list already has some data. No need to reset the list. Just show some alert.
                            showAlert(error);
                        }else {
                            //show alert via retry view in place of the list
                            creatorsAdapter.showRetryView(error);
                        }
                    }
                });
    }

    private void showAlert(BaseException error) {
        Toast.makeText(getContext(),error.getCauseAndSuggestionMessage(),Toast.LENGTH_LONG).show();
    }

    private List<CharacterUiModel> convertToModelViewList(List<MarvelCharacter> marvelCharacters) {
        List<CharacterUiModel> modelList = new ArrayList<>();

        for (MarvelCharacter marvelCharacter : marvelCharacters) {
            modelList.add(new CharacterUiModel(marvelCharacter));
        }

        return modelList;
    }

    private void onListItemClick(CharacterUiModel item) {

    }

    private void onRetryClick() {
        getCharacterList();
    }
}
