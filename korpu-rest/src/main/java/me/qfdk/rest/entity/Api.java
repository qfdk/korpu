package me.qfdk.rest.entity;

import org.json.JSONObject;
import sun.nio.cs.ext.TIS_620;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * Created by qfdk on 16/5/15.
 */
@Path("v1")
public class Api {

    Dropbox dorpbox;
    Google google;

    public Api()
    {
        this.dorpbox=Dropbox.getInstance();
        this.google=Google.getInstance();
    }

    /**
     * http://localhost:8080/api/v1/getUrl
     * @return
     * @throws Exception
     */
    @GET
    @Path("getUrl")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUrl() throws Exception {
        return Response.status(200).entity("Dropbox+Google").build();
    }

    /**
     * http://localhost:8080/api/v1/callbackDropbox
     * @param code
     * @return
     * @throws Exception
     */
    @GET
    @Path("callbackDropbox")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authDropbox(@QueryParam("code")String code) throws Exception {
        return Response.status(200).entity(this.dorpbox.auth(code).toString()).build();
    }

    /**
     * http://localhost:8080/api/v1/callbackDropbox
     * @param code
     * @return
     * @throws Exception
     */
    @GET
    @Path("callbackGoogle")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authGoogle(@QueryParam("code")String code) throws Exception {
    //        return Response.temporaryRedirect(new URI("http://localhost:8080/")).build();
        return Response.status(200).entity(this.google.auth(code).toString()).build();
    }
    @GET
    @Path("getFiles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFileInfo() {
        JSONObject jsonObject = new JSONObject();
        if(this.dorpbox.getToken()!=null)
        {
            System.out.println(this.dorpbox.getToken());
            jsonObject.put("dropbox",this.dorpbox.getFiles());

        }
        if(this.google.getToken()!=null)
        {
            System.out.println(this.google.getToken());
            jsonObject.put("google",this.google.getFiles());
        }

        if(this.google.getToken()==null&&this.dorpbox.getToken()==null)
        {
            jsonObject.put("error","Login first");
        }

        return Response.status(200).entity(jsonObject.toString()).build();
    }

}
