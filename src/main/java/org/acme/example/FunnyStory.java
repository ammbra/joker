package org.acme.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FunnyStory(String category, String joke, String setup, String delivery) {

}
//public class FunnyStory {
//    public String category;
//    public String joke;
//    public String setup;
//    public String delivery;
//
//    @Override
//    public String toString() {
//        return "FunnyStory{" +
//                "category='" + category + '\'' +
//                ", joke='" + joke + '\'' +
//                ", setup='" + setup + '\'' +
//                ", delivery='" + delivery + '\'' +
//                '}';
//    }
//}