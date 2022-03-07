package com.IDPA.SolarApi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("solar")
public class SolarService {

    @GET
    @Path("power")
    @Produces(MediaType.APPLICATION_JSON)

    public Response readDeal(
            @QueryParam("wetter") int wetter,
            @QueryParam("zeit") int zeit,
            @QueryParam("winkel") int winkel,
            @QueryParam("anzahl") int anzahl
    ) {
        int httpStatus = 200;
        int power = 0;


        return Response
                .status(httpStatus)
                .entity(power)
                .build();
    }


}
