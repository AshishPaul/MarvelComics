package in.zerogravity.marvelcomics.ui.components;


import android.content.Context;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.security.InvalidParameterException;

import in.zerogravity.marvelcomics.R;

public class DialogFactory {
    public static Snackbar createSnackBar(Context context, View anchorView, String message) {
        if (TextUtils.isEmpty(message))
            throw new InvalidParameterException("Snackbar text message cant not be null or blank");

//        RelativeLayout.LayoutParams layoutParams =
//                (RelativeLayout.LayoutParams) dashboard_snackbar_layout.getLayoutParams();
//        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
//        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
//        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
//        dashboard_snackbar_layout.setLayoutParams(layoutParams);
        Snackbar snackbar = Snackbar
                .make(anchorView, message, Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        try {
            TextView snackbarTextView = (TextView) snackbarView.findViewById(R.id.snackbar_text);
            snackbarTextView.setTextSize(15);
            snackbarTextView.setGravity(View.TEXT_ALIGNMENT_CENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        snackbarView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        return snackbar;
    }
}
