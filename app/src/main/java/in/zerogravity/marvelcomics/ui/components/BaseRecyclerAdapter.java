package in.zerogravity.marvelcomics.ui.components;

import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import in.zerogravity.marvelcomics.exception.BaseException;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    public static final int NORMAL = 1;
    public static final int EMPTY_LOADING = -1;
    public static final int EMPTY_RETRY = 0;
    protected final ListItemClickListener<T> listItemClickListener;
    protected final EmptyViewClickListener emptyViewClickListener;
    @ListState
    private int currentListState = EMPTY_LOADING;
    protected List<T> list;
    private ScrollAnimator scrollAnimator;
    protected BaseException error;

    protected BaseRecyclerAdapter(ListItemClickListener<T> listItemClickListener, EmptyViewClickListener emptyViewClickListener) {
        this.listItemClickListener = listItemClickListener;
        this.emptyViewClickListener = emptyViewClickListener;
        changeState(true);
    }

    public void setScrollAnimator(ScrollAnimator itemAnimator) {
        this.scrollAnimator = itemAnimator;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = getViewHolder(parent, viewType);
        if (scrollAnimator != null && viewType == NORMAL) {
            scrollAnimator.runEnterAnimation(holder.itemView);
        }
        return holder;
    }

    protected abstract BaseViewHolder getViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        onBindViewHolder(holder, position, getItem(position));
    }

    protected abstract void onBindViewHolder(BaseViewHolder holder, int position, T itemData);

    public void setData(List<T> _list) {
        this.list = _list;
        changeState(false);
        this.notifyDataSetChanged();
    }
    public void showRetryView(BaseException error){
        //reset existing list data if any.
        if(list!=null) {
            list.clear();
            list = null;
        }
        this.error = error;
        changeState(false);
        this.notifyDataSetChanged();
    }

    /**
     * Change current state of the adapter i.e. does it contain data or should it show initial empty loading or should it show retry view.
     * <p>
     * Must notify adapter about this change after calling this method for this change to take effect.
     *
     * @param shouldReset if true, current state will be set to {@link BaseRecyclerAdapter#EMPTY_LOADING}
     */
    private void changeState(boolean shouldReset) {
        if (shouldReset) {
            currentListState = EMPTY_LOADING;
            return;
        }
        currentListState = (list != null && list.size() > 0) ? NORMAL : EMPTY_RETRY;
    }

    public void addData(T item) {
        if (list == null) {
            list = new ArrayList<T>();
        }

        list.add(item);
        notifyItemInserted(list.size() - 1);
    }

    public void removeData(int position) {
        if (list != null && position < list.size()) {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void removeData(T item) {
        if (list != null && list.contains(item)) {
            int position = list.indexOf(list);
            list.remove(item);
            notifyItemRemoved(position);
        }
    }


    public T getItem(int position) {
        if (list != null && position < list.size()) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @ListState
    public int getCurrentListState() {
        return currentListState;
    }


    @IntDef({EMPTY_LOADING, EMPTY_RETRY, NORMAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ListState {
    }

}
