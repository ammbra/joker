package org.acme.example;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.inject.Singleton;

@RegisterRestClient
@Singleton
@Path("joke")
public interface JokeService {

    @GET
    @Path("Any")
    @Produces(MediaType.APPLICATION_JSON)
    FunnyStory getAnyContent(@QueryParam("lang") String lang, @QueryParam("contains") String contains);

    @GET
    @Path("{category}")
    @Produces(MediaType.APPLICATION_JSON)
    FunnyStory getContent(@PathParam("category") String category, @QueryParam("lang") String language);

}