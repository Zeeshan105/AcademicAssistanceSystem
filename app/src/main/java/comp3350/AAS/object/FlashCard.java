package comp3350.AAS.object;

public class FlashCard {

    private final String title;
    private final String description;

    public FlashCard(String title, String description){
        this.title = title;
        this.description = description;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }

}
