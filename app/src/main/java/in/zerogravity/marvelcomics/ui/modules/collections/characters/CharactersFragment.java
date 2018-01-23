package in.zerogravity.marvelcomics.ui.modules.collections.characters;

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
import hugo.weaving.DebugLog;
import in.zerogravity.marvelcomics.AppController;
import in.zerogravity.marvelcomics.R;
import in.zerogravity.marvelcomics.data.AppDataManager;
import in.zerogravity.marvelcomics.data.CharacterDataManager;
import in.zerogravity.marvelcomics.data.DataManagerFactory;
import in.zerogravity.marvelcomics.data.model.BaseObject;
import in.zerogravity.marvelcomics.data.model.MarvelCharacter;
import in.zerogravity.marvelcomics.exception.BaseException;
import in.zerogravity.marvelcomics.ui.base.BaseFragment;
import in.zerogravity.marvelcomics.ui.components.BaseRecyclerAdapter;
import in.zerogravity.marvelcomics.ui.components.EmptyViewClickListener;
import in.zerogravity.marvelcomics.ui.components.ListItemClickListener;
import in.zerogravity.marvelcomics.ui.components.ScrollAnimator;
import in.zerogravity.marvelcomics.ui.components.SlideInAnimator;
import in.zerogravity.marvelcomics.ui.model.CharacterUiModel;
import timber.log.Timber;


public class CharactersFragment extends BaseFragment {

    @BindView(R.id.characters_recycler_view)
    RecyclerView characters_recycler_view;

    private CharactersAdapter charactersAdapter;
    private LinearLayoutManager layoutManager;
    private RecyclerView.ItemAnimator itemAnimator;
    private ScrollAnimator scrollAnimator;
    private CharacterDataManager dataManager;

    private final ListItemClickListener<CharacterUiModel> listItemClickListener = item -> onListItemClick(item);
    private final EmptyViewClickListener emptyViewClickListener = () -> onRetryClick();

    public CharactersFragment() {

    }

    public static CharactersFragment newInstance(BaseObject data) {
        CharactersFragment fragment = new CharactersFragment();
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
        return R.layout.fragment_characters;
    }


    @Override
    public String getPageTitle() {
        return AppController.getInstance().getString(R.string.title_characters);
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
        charactersAdapter = new CharactersAdapter(listItemClickListener, emptyViewClickListener);
        scrollAnimator = new SlideInAnimator();
        charactersAdapter.setScrollAnimator(scrollAnimator);

        dataManager = DataManagerFactory.getCharacterDataManager();
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
        characters_recycler_view.setAdapter(charactersAdapter);
        characters_recycler_view.setLayoutManager(layoutManager);
        characters_recycler_view.setItemAnimator(itemAnimator);
    }

    @DebugLog
    private void getCharacterList() {
        dataManager.getCharacterCollection(true, 20,
                new AppDataManager.Callback<List<MarvelCharacter>>() {
                    @Override
                    public void onSuccess(List<MarvelCharacter> result) {
                        Timber.d("getCharacterList:onSuccess");
                        charactersAdapter.setData(convertToModelViewList(result));
                    }

                    @Override
                    public void onError(BaseException error) {
                        Timber.d("getCharacterList:onError");
                        if (charactersAdapter.getCurrentListState() == BaseRecyclerAdapter.NORMAL) {
                            //that means the list already has some data. No need to reset the list. Just show some alert.
                            showAlert(error);
                        } else {
                            //show alert via retry view in place of the list
                            charactersAdapter.showRetryView(error);
                        }
                    }
                });
    }

    private List<CharacterUiModel> convertToModelViewList(List<MarvelCharacter> marvelCharacters) {
        List<CharacterUiModel> modelList = new ArrayList<>();

        for (MarvelCharacter marvelCharacter : marvelCharacters) {
            modelList.add(new CharacterUiModel(marvelCharacter));
        }

        return modelList;
    }

    private void showAlert(BaseException error) {
        Toast.makeText(getContext(), error.getCauseAndSuggestionMessage(), Toast.LENGTH_LONG).show();
    }

    private void onListItemClick(CharacterUiModel item) {

    }

    private void onRetryClick() {
        getCharacterList();
    }
}
