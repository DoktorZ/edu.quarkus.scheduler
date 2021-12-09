package edu.quarkus.scheduler;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/count")
public class CountResource {

    private static final Logger LOG = Logger.getLogger(CountResource.class);

    @Inject
    CounterBean counter;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "count: " + counter.get();
    }
}
