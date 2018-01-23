package in.zerogravity.marvelcomics.ui.components;

import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class SlideInAnimator implements ScrollAnimator{

    @Override
    public void runEnterAnimation(View view) {
        view.setTranslationY(500);
        view.animate()
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator(1.f))
                .setDuration(700)
                .start();
    }
}
