package rest;

import javax.ws.rs.*;

@Path("person")
public class PersonResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}