package org.acme.example;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "global")
interface GlobalTagsConfig {
     String PROFILE = "profile";
     String CATEGORY = "category";
     String COUNTRY="country";

     String category();
     String country();
}