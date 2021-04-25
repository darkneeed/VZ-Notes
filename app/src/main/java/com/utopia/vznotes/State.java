package com.utopia.vznotes;

public class State {

    private String id;
    private String category;
    private String text;

    public State(String id, String category, String text){

        this.id = id;
        this.category = category;
        this.text = text;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getText() {
        return this.category;
    }

    public void setText(String text) {
        this.text = text;
    }

}