package in.zerogravity.marvelcomics.ui.modules.home;


import in.zerogravity.marvelcomics.ui.base.Presenter;

public class HomePresenter extends Presenter<HomeContract.View> implements HomeContract.Presenter{

    private final HomeUseCase homeUseCase;

    public HomePresenter(HomeUseCase homeUseCase){
        this.homeUseCase = homeUseCase;
    }

}
