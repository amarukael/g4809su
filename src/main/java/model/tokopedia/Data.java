package model.tokopedia;

public class Data {
    public String type;
    public String id;
    public Attribute attribute;

    public Data(String type, String id, Attribute attribute){
        this.type = type;
        this.id = id;
        this.attribute = attribute;
    }

    // Constructor handling optional attribute
    public Data(String type, String id){
        this.type = type;
        this.id = id;
        // Initialize attribute as null when not provided
        this.attribute = null;
    }

    // Getters and setters if needed
    // ...
}
