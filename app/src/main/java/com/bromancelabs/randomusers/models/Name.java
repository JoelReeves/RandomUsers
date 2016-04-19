package com.bromancelabs.randomusers.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "first",
        "last"
})

public class Name {

    @JsonProperty("title")
    private String title;

    @JsonProperty("first")
    private String first;

    @JsonProperty("last")
    private String last;

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("first")
    public String getFirst() {
        return first;
    }

    @JsonProperty("first")
    public void setFirst(String first) {
        this.first = first;
    }

    @JsonProperty("last")
    public String getLast() {
        return last;
    }

    @JsonProperty("last")
    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return capitalizeFirstLetter(title) + ". " + capitalizeFirstLetter(first) + " " + capitalizeFirstLetter(last);
    }

    private String capitalizeFirstLetter(String text) {
        return String.valueOf(text.charAt(0)).toUpperCase() + text.substring(1, text.length());
    }
}
