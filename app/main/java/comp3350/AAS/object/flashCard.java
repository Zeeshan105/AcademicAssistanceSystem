package comp3350.AAS.object;

public class flashCard {
    private String title;
    private String description;

    public flashCard(String title,String description){
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
