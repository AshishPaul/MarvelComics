package in.zerogravity.marvelcomics.data.remote.model;

public class CharacterDataWrapper {

    private int code;
    private String status;
    private CharacterDataContainer data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CharacterDataContainer getCharacterDataContainer() {
        return data;
    }

    public void setCharacterDataContainer(CharacterDataContainer data) {
        this.data = data;
    }
}
