package me.qfdk.rest.entity;

import org.json.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import com.sun.jersey.multipart.FormDataParam;

/**
 *
 * Created by qfdk on 16/5/15.
 *
 */
@Path("v1")
public class Api {

    private Dropbox dropbox;
    private Google google;

    public Api() {
        this.dropbox = Dropbox.getInstance();
        this.google = Google.getInstance();
    }

    /**
     * http://localhost:8080/api/v1/getUrl
     *
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
     *
     * @param code code
     * @return json
     * @throws Exception
     */
    @GET
    @Path("callbackDropbox")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authDropbox(@QueryParam("code") String code) throws Exception {
        return Response.status(200).entity(this.dropbox.auth(code).toString()).build();
    }

    /**
     * http://localhost:8080/api/v1/callbackDropbox
     *
     * @param code code
     * @return json
     * @throws Exception
     */
    @GET
    @Path("callbackGoogle")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authGoogle(@QueryParam("code") String code) throws Exception {
        //        return Response.temporaryRedirect(new URI("http://localhost:8080/")).build();
        return Response.status(200).entity(this.google.auth(code).toString()).build();
    }

    @GET
    @Path("getFiles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFileInfo() {
        JSONObject jsonObject = new JSONObject();
        if (this.dropbox.getToken() != null) {
            System.out.println(this.dropbox.getToken());
            jsonObject.put("dropbox", this.dropbox.ls());

        }
        if (this.google.getToken() != null) {
            System.out.println(this.google.getToken());
            jsonObject.put("google", this.google.ls());
        }

        if (this.google.getToken() == null && this.dropbox.getToken() == null) {
            jsonObject.put("error", "Login first");
        }

        return Response.status(200).entity(jsonObject.toString()).build();
    }

    @GET
    @Path("info")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfo() {
        JSONObject jsonObject = new JSONObject();
        if (this.dropbox.getToken() != null) {
            System.out.println(this.dropbox.getToken());
            jsonObject.put("dropbox", this.dropbox.getInfo());

        }
        if (this.google.getToken() != null) {
            System.out.println(this.google.getToken());
            jsonObject.put("google", this.google.getInfo());
        }

        if (this.google.getToken() == null && this.dropbox.getToken() == null) {
            jsonObject.put("error", "Login first");
        }

        return Response.status(200).entity(jsonObject.toString()).build();
    }

    @GET
    @Path("detailFile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response detailFile(@QueryParam("name") String name) {
        JSONObject jsonObject = new JSONObject();
        if (this.dropbox.getToken() != null) {
            System.out.println(this.dropbox.getToken());
            jsonObject.put("dropbox", this.dropbox.detailFile(name));

        }
        if (this.google.getToken() != null) {
            System.out.println(this.google.getToken());
            jsonObject.put("google", this.google.detailFile(name));
        }

        if (this.google.getToken() == null && this.dropbox.getToken() == null) {
            jsonObject.put("error", "Login first");
        }

        return Response.status(200).entity(jsonObject.toString()).build();
    }

    @GET
    @Path("rm")
    @Produces(MediaType.APPLICATION_JSON)
    public Response rm(@QueryParam("type") String type,@QueryParam("name") String name) {
        JSONObject jsonObject = new JSONObject();
        if (this.dropbox.getToken() != null&&type.equals("dropbox")) {
            System.out.println(this.dropbox.getToken());
            jsonObject.put("dropbox", this.dropbox.rm(name));

        }
        if (this.google.getToken() != null&&type.equals("google")) {
            System.out.println(this.google.getToken());
            jsonObject.put("google", this.google.rm(name));
        }

        if (this.google.getToken() == null && this.dropbox.getToken() == null) {
            jsonObject.put("error", "Login first");
        }

        return Response.status(200).entity(jsonObject.toString()).build();
    }

    @GET
    @Path("mv")
    public Response mv(@QueryParam("from") String from,@QueryParam("to") String to) {
        return Response.status(200).entity(this.dropbox.mv(from,to).toString()).build();
    }

    @GET
    @Path("mkdir")
    @Produces(MediaType.APPLICATION_JSON)
    public Response mkdir(@QueryParam("name") String dir) {
        return Response.status(200).entity(this.dropbox.mkdir(dir).toString()).build();
    }

    @GET
    @Path("share")
    @Produces(MediaType.APPLICATION_JSON)
    public Response share(@QueryParam("name") String name) {
        return Response.status(200).entity(this.dropbox.share(name).toString()).build();
    }

    @GET
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@QueryParam("user") String user,@QueryParam("password") String password) {
        JSONObject jsonObject=new JSONObject();
        if(("a").equals(user))
        {
            jsonObject.put("stat","ok");
        }else {
            jsonObject.put("stat","fail");
        }
        return Response.status(200).entity(jsonObject.toString()).build();
    }

    @POST
    @Path("/upload")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@FormDataParam("file") InputStream uploadedInputStream,@FormDataParam("path")String path)
    {
        System.out.println("coucou");
        System.out.println(path);
        return Response.status(200).entity(this.dropbox.upload(path,uploadedInputStream).toString()).build();
    }

    }
