package in.zerogravity.marvelcomics.data.remote.model;

/**
 * @author glomadrian
 */
public class Comic {

    private int id;
    private String name;
    private String description;
    private Image thumbnail;
    private Character characters;
    private Series series;
    private Story stories;
    private int available;


    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
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

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Character getCharacters() {
        return characters;
    }

    public void setCharacters(Character characters) {
        this.characters = characters;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public Story getStories() {
        return stories;
    }

    public void setStories(Story stories) {
        this.stories = stories;
    }
}
