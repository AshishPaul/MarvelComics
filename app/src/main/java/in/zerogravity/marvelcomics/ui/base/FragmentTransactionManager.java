package in.zerogravity.marvelcomics.ui.base;


import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public abstract class FragmentTransactionManager {

    @NonNull
    private final FragmentManager fragmentManager;

    public abstract BaseFragment getNewFragment(ViewInfo viewInfo);

    public FragmentTransactionManager(@NonNull FragmentManager fragmentManager){
        if (fragmentManager == null)
            throw new NullPointerException("FragmentManager can not be null");
        this.fragmentManager = fragmentManager;
    }
    public void addOrReplaceFragment(@NonNull ViewInfo viewInfo) {
        if (viewInfo == null)
            throw new NullPointerException("ViewInfo can not be null");

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        /*
          If fragment manager doesn't have an instance of the fragment, get an instance
          and add it to the transaction. Else, attach the instance to transaction.
         */
        BaseFragment fragment = (BaseFragment) fragmentManager.findFragmentByTag(viewInfo.tag());
//        if (fragment == null) {
            fragment = getNewFragment(viewInfo);
////            fragmentTransaction.add(data.parentId(), fragment, data.tag());
////            if (data.addToStack()) {
////                fragmentTransaction.addToBackStack(data.tag());
////            }
//        }else {
//            // Detach existing primary fragment
////        Fragment curFrag = fragmentManager.getPrimaryNavigationFragment();
////        if (curFrag != null) {
////            fragmentTransaction.detach(curFrag);
////        }
//            fragment.setData(viewInfo.data());
//
//
//        }
        fragmentTransaction.replace(viewInfo.parentId(), fragment, viewInfo.tag());

        // Detach existing primary fragment
//        Fragment curFrag = fragmentManager.getPrimaryNavigationFragment();
//        if (curFrag != null) {
//            fragmentTransaction.detach(curFrag);
//        }
//
//        // Set fragment as primary navigator for child manager back stack to be handled by system
//        fragmentTransaction.setPrimaryNavigationFragment(fragment);
////        fragmentTransaction.setReorderingAllowed(true);
////        fragmentTransaction.commitNowAllowingStateLoss();


        fragmentTransaction.commit();

    }

//    private static BaseFragment createNewFragmentByInfo(FragmentManager fragmentManager, ViewInfo data) throws ClassCastException {
//        switch (data.tag()) {
//            case HomeActivity.TAB_HOME:
//                return CollectionsFragment.newInstance(data);
//            case HomeActivity.TAB_COLLECTIONS:
//                return CollectionsFragment.newInstance(data);
//            case HomeActivity.TAB_FAVORITES:
//                return CollectionsFragment.newInstance(data);
//            case HomeActivity.TAB_NOTIFICATIONS:
//                return CollectionsFragment.newInstance(data);
//            case HomeActivity.TAB_MY_PROFILE:
//                return CollectionsFragment.newInstance(data);
//        }
//
//        return null;
//    }
}
