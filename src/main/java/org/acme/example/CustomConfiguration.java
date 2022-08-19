package org.acme.example;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.config.MeterFilter;
import io.quarkus.runtime.configuration.ProfileManager;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;

@Singleton
public class CustomConfiguration {

    @Inject
    GlobalTagsConfig tagsConfig;

    @Produces
    @Singleton
    public MeterFilter configureTagsForAll() {
        return MeterFilter.commonTags(Arrays.asList(
           Tag.of(GlobalTagsConfig.CATEGORY, tagsConfig.category()),
           Tag.of(GlobalTagsConfig.COUNTRY, tagsConfig.country()),
           Tag.of(GlobalTagsConfig.PROFILE, ProfileManager.getActiveProfile())
        ));
    }

}