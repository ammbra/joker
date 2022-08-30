package org.acme.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FunnyStory(String category, String joke, String setup, String delivery) {

}