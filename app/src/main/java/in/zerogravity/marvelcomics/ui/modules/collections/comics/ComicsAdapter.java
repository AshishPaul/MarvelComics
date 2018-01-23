package in.zerogravity.marvelcomics.ui.modules.collections.comics;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.zerogravity.marvelcomics.R;
import in.zerogravity.marvelcomics.ui.components.BaseRecyclerAdapter;
import in.zerogravity.marvelcomics.ui.components.BaseViewHolder;
import in.zerogravity.marvelcomics.ui.components.EmptyViewClickListener;
import in.zerogravity.marvelcomics.ui.components.EmptyViewHolder;
import in.zerogravity.marvelcomics.ui.components.ListItemClickListener;
import in.zerogravity.marvelcomics.ui.components.LoadingViewHolder;
import in.zerogravity.marvelcomics.ui.model.CharacterUiModel;


public class ComicsAdapter extends BaseRecyclerAdapter<CharacterUiModel> {


    public ComicsAdapter(ListItemClickListener<CharacterUiModel> listItemClickListener, EmptyViewClickListener emptyViewClickListener) {
        super(listItemClickListener, emptyViewClickListener);
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, @ListState int viewType) {
        BaseViewHolder viewHolder;
        View itemView;
        switch (viewType) {
            case NORMAL:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_character,
                        parent, false);
                viewHolder = new ComicViewHolder(itemView, listItemClickListener);
                break;
            case EMPTY_LOADING:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_view,
                        parent, false);
                viewHolder = new LoadingViewHolder(itemView);
                break;
            case EMPTY_RETRY:
            default:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_view,
                        parent, false);
                viewHolder = new EmptyViewHolder(itemView, error, emptyViewClickListener);
        }
        return viewHolder;
    }

    @Override
    protected void onBindViewHolder(BaseViewHolder holder, int position, CharacterUiModel itemData) {

    }

    @ListState
    @Override
    public int getItemViewType(int position) {
        return getCurrentListState();
    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 1;
        }
    }


    static class ComicViewHolder extends BaseViewHolder {

        private final ListItemClickListener<CharacterUiModel> listener;

        public ComicViewHolder(View itemView, ListItemClickListener<CharacterUiModel> listener) {
            super(itemView);
            this.listener = listener;
        }


        @Override
        public void onBind(int position) {

        }
    }


}
