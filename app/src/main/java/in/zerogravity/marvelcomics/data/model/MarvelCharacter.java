package in.zerogravity.marvelcomics.data.model;


public class MarvelCharacter {

    int id;
    String name;
    String description;
    String imageURL;
    int comics;
    int stories;
    int series;


    public MarvelCharacter() {
    }

    public MarvelCharacter(int id, String name, String description, String imageURL) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getComics() {
        return comics;
    }

    public void setComics(int comics) {
        this.comics = comics;
    }

    public int getStories() {
        return stories;
    }

    public void setStories(int stories) {
        this.stories = stories;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }
}
