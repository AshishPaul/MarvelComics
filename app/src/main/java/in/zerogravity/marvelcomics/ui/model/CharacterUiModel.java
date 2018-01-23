package in.zerogravity.marvelcomics.ui.model;


import in.zerogravity.marvelcomics.data.model.MarvelCharacter;

public class CharacterUiModel {
    MarvelCharacter model;

    public CharacterUiModel(MarvelCharacter model) {
        this.model = model;
    }

    public String getImageUrl() {
        return model.getImageURL();
    }

    public String getTitle() {
        return model.getName();
    }

    public String getSubtitle() {
        return String.valueOf(model.getId());
    }
}
