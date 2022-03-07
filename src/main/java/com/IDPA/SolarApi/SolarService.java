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
            @QueryParam("wetter") String wetter,
            @QueryParam("zeit") String zeit,
            @QueryParam("winkel") String winkel,
            @QueryParam("anzahl") String anzahl
    ) {
        int httpStatus = 200;
        int power = 0;

        try {
            int wetterZ = Integer.parseInt(wetter);
            int zeitZ = Integer.parseInt(zeit);
            int winkelZ = Integer.parseInt(winkel);
            int anzahlZ = Integer.parseInt(anzahl);



            if (wetterZ == 0 && zeitZ == 0 && winkelZ == 0 && anzahlZ == 0) {
                wetterZ = 1;
                zeitZ = 800;
                winkelZ = 20;
                anzahlZ = 10;
            }

            power = ((wetterZ * zeitZ) / winkelZ) * anzahlZ;

        }catch (NumberFormatException exception){
            httpStatus = 415;
        }
        return Response
                .status(httpStatus)
                .entity(power)
                .build();
    }


}
