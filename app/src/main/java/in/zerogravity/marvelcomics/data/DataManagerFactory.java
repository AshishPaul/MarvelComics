package in.zerogravity.marvelcomics.data;


import in.zerogravity.marvelcomics.AppController;

public class DataManagerFactory {
    public static CharacterDataManager getCharacterDataManager(){
        return AppController.getInstance().getCharacterDataManager();
    }
}
