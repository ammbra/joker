package org.acme.example;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestQuery;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.inject.Singleton;

@RegisterRestClient(configKey = "joker-https")
@Singleton
@Path("joke")
public interface JokeService {

    @GET
    @Path("Any")
    @Produces(MediaType.APPLICATION_JSON)
    FunnyStory getAnyContent(@RestQuery("lang") String lang, @RestQuery("contains") String contains);

    @GET
    @Path("{category}")
    @Produces(MediaType.APPLICATION_JSON)
    FunnyStory getContent(@RestQuery("category") String category, @RestQuery("lang") String language);

}