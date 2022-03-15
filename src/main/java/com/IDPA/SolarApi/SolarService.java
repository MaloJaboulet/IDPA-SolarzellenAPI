package com.IDPA.SolarApi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Malo Jaboulet
 * @version 1.2
 * @since 15.03.2022
 * <p>
 * Diese Klass rechnet die Leistung von Solarzellen in verschiedenen Situationen aus.
 * Es können vier Parameter geändert werden: Wetter, Jahreszeit, Winkel der Solarzelle und Anzahl der Solarzellen.
 * Aus diesen Parametern wird dann die Leistung ausgerechnet.
 */
@Path("solar")
public class SolarService {

    @GET
    @Path("power")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readDeal(
            @QueryParam("wetter") String wetterS,
            @QueryParam("jahreszeit") String jahreszeitS,
            @QueryParam("winkel") String winkelS,
            @QueryParam("anzahl") String anzahlS
    ) {
        int httpStatus = 200;
        double power = -1;
        //Ertrag für Frühling, Sommer, Herbst und Winter in kWh/d/m^2
        final double[] ertragJahreszeit = {4, 5, 2.5, 1.5};
        final double panelGroesse = 1.7;            //1.7 m^2 grosse Panel
        // Faktoren des Wetters: Sonnig, Leicht bewölkt, Stark bewölkt, Trüb/Nebel/Gewitter/Schnee
        final double[] wetterFaktor = {1, 0.6, 0.3, 0.1};
        //Maximaler Einfallswinkel der Sonnenstrahlen in den Jahreszeiten Frühling, Sommer, Herbst und Winter
        final double[] jahreszeitWinkel = {43, 66, 42, 19};

        try {
            int wetter = Integer.parseInt(wetterS);
            int jahreszeit = Integer.parseInt(jahreszeitS);
            int winkel = Integer.parseInt(winkelS);
            int anzahl = Integer.parseInt(anzahlS);

            if ((wetter <= 4 && wetter >= 0) && (jahreszeit <= 4 && jahreszeit >= 0) &&
                    (winkel <= 90 && winkel >= 0) && (anzahl <= 100 && anzahl >= 0)) {

                if (wetter == 0 && jahreszeit == 0 && winkel == 0 && anzahl == 0) {
                    wetter = 1;
                    jahreszeit = 1;
                    winkel = 20;
                    anzahl = 10;
                }

                double groesse = anzahl / panelGroesse;

                double radian = Math.toRadians((180 - jahreszeitWinkel[jahreszeit - 1] - winkel));
                double winkelErtrag = Math.sin(radian);


                power = ertragJahreszeit[jahreszeit - 1] * wetterFaktor[wetter - 1] * groesse * winkelErtrag;
            }

        } catch (NumberFormatException exception) {
            httpStatus = 415;
            power = -1;
        }
        return Response
                .status(httpStatus)
                .entity(power)
                .build();
    }


}
