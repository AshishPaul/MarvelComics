package in.zerogravity.marvelcomics.ui.modules.notification;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import hugo.weaving.DebugLog;
import in.zerogravity.marvelcomics.AppController;
import in.zerogravity.marvelcomics.R;
import in.zerogravity.marvelcomics.data.model.BaseObject;
import in.zerogravity.marvelcomics.ui.base.BaseFragment;

public class NotificationFragment extends BaseFragment{

    private static final String BUNDLE_KEY_DATA = "bundle_key_data";

    public NotificationFragment(){

    }
    @DebugLog
    public static NotificationFragment newInstance(BaseObject data){
        NotificationFragment fragment = new NotificationFragment();
        if(data!=null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(BUNDLE_KEY_DATA, data);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public String getPageTitle() {
        return AppController.getInstance().getString(R.string.title_notifications);
    }

    @Override
    protected void initializeDagger() {

    }

    @Override
    protected void initializePresenter() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_notifications;
    }

    @Override
    protected int getTheme() {
        return R.style.AppTheme_Notification;
    }

    @Override
    public void setData(BaseObject data) {
        this.data = data;
        if(data!=null) {
            Bundle args = this.getArguments();
            if (args != null) {
                args.putParcelable(BUNDLE_KEY_DATA, data);
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.section_label);
        textView.setText(getPageTitle());
    }
}
