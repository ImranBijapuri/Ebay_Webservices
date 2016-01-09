
package com.ebay_webservices.resource;

import com.ebay_webservices.model.Ads;
import com.ebay_webservices.model.AdsServed;
import com.ebay_webservices.service.AdsService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Imran Bijapuri
 */
// This is the primary resource class and contains all the endpoints

@Path("/ads")
public class AdsResource {

    private AdsService adsservice = new AdsService();

    // This endpoint allows you add new Ads to the system
    // Sample call http://localhost:8084/Ebay_Webservices/rest/ads 
    // Request Type POST
    // Parameters -  form data "adText"
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAds(@FormParam("adText") String text) {
        adsservice.addAds(text);
        return Response.status(Response.Status.CREATED).build();
    }

    // This endpoint allows you to view Ads as per id
    // Sample call http://localhost:8084/Ebay_Webservices/rest/ads/3
    // Request Type GET
    // Parameters -  Path Param adId
    @GET
    @Path("/{adId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdbyId(@PathParam("adId") int id) {
        Ads ads = adsservice.getadbyId(id);
        return Response.status(Response.Status.ACCEPTED).entity(ads).build();
    }

    // This endpoint allows you to delete Ads as per id
    // Sample call http://localhost:8084/Ebay_Webservices/rest/ads/7
    // Request Type DELETE
    // Parameters -  Path Param adId
    @DELETE
    @Path("/{adId}")
    public Response delAdbyId(@PathParam("adId") int id) {
        adsservice.deladbyId(id);
        return Response.status(Response.Status.ACCEPTED).build();
    }

    // This endpoint allows you to update an Ads click count as per id
    // Sample call http://localhost:8084/Ebay_Webservices/rest/ads/trackclick/1
    // Request Type PUT
    // Parameters -  Path Param adId
    @PUT
    @Path("trackclick/{adId}")
    public Response updatetrackclick(@PathParam("adId") int adId) {
        adsservice.updatetrackclick(adId);
        return Response.status(Response.Status.ACCEPTED).build();
    }
    
    // This endpoint allows you to update an Ads impression count as per id
    // Sample call http://localhost:8084/Ebay_Webservices/rest/ads/trackimpression/1
    // Request Type PUT
    // Parameters -  Path Param adId
    @PUT
    @Path("trackimpression/{adId}")
    public Response trackimpression(@PathParam("adId") int adId) {
        adsservice.trackimpression(adId);
        return Response.status(Response.Status.ACCEPTED).build();
    }
    
    // This endpoint allows you to get an Ad from the system as per out implementation of smart adserver
    // Sample call http://localhost:8084/Ebay_Webservices/rest/ads/getAd
    // Request Type GET
    @GET
    @Path("getAd")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAd() {
        AdsServed adsserved = adsservice.getAd();
        return Response.status(Response.Status.ACCEPTED).entity(adsserved).build();
    }
    
}
