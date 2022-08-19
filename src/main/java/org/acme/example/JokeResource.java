package org.acme.example;

import io.micrometer.core.annotation.Timed;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestResponse.Status;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Path("jokes")
public class JokeResource {

    @Inject
    Logger LOGGER;

    @RestClient
    JokeService jokeService;

    public static final String URL = "url";
    public static final String API_JOKE = "api.joke";

    @Path("init")
    @GET
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public Response init() {
        LOGGER.debug("Updating the db from external service");
        List<Joke> jokes = Joke.find("merged", false).list();
        for (Joke joke : jokes) {
            String language = joke.language;
            FunnyStory anyContent = jokeService.getAnyContent(language, URLEncoder.encode(joke.content, StandardCharsets.UTF_8).replaceAll("\\+", "%20"));
            System.out.println(anyContent);
            String content = (anyContent.joke() != null) ? anyContent.joke() : anyContent.setup() + anyContent.delivery();
            joke.update(content, joke.id, language);
        }
        LOGGER.debug("End update of the db");

        return Response.status(Status.CREATED).entity("DB initialized").build();

    }

    @POST
    @Transactional
    @Timed(value = "joke.creation", longTask = true, extraTags = {URL, API_JOKE})
    public Joke create(Joke joke) {
         Joke.persist(joke);
         return joke;
    }

    @GET
    @Path("/sysresources")
    @Produces(MediaType.TEXT_PLAIN)
    public String getSystemResources() {
        long memory = Runtime.getRuntime().maxMemory();
        int cores = Runtime.getRuntime().availableProcessors();
        return " Memory: " + (memory / 1024 / 1024) +
                " Cores: " + cores + "\n";
    }

    @GET
    public List<Joke> findAll() {
        return Joke.findAll().list();
    }
    

}