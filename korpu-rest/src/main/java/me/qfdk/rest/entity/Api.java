package me.qfdk.rest.entity;

import org.glassfish.jersey.message.internal.EntityInputStream;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import com.sun.jersey.multipart.FormDataParam;

/**
 * Created by qfdk on 16/5/15.
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
//        return Response.status(200).entity(this.dropbox.auth(code).toString()).build();
        this.dropbox.auth(code);
        return Response.temporaryRedirect(new URI("http://localhost:9000/#/dashboard")).build();

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
        this.google.auth(code);
        return Response.temporaryRedirect(new URI("http://localhost:9000/#/dashboard")).build();
//        return Response.temporaryRedirect(new URI("http://localhost:8080/")).build();
//        return Response.status(200).entity(this.google.auth(code).toString()).build();
    }

    @GET
    @Path("getFiles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFileInfo() {
        JSONObject jsonObject = new JSONObject();
        if (null != this.dropbox.getToken()) {
            System.out.println(this.dropbox.getToken());
            jsonObject.put("dropbox", this.dropbox.ls());

        }
        if (null != this.google.getToken()) {
            System.out.println(this.google.getToken());
            jsonObject.put("google", this.google.ls());
        }

        if (null == this.google.getToken() && null == this.dropbox.getToken()) {
            jsonObject.put("error", "Login first");
        }

        return Response.status(200).entity(jsonObject.toString()).build();
    }

    @GET
    @Path("info")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfo() {
        JSONObject jsonObject = new JSONObject();
        if (null != this.dropbox.getToken()) {
            System.out.println(this.dropbox.getToken());
            jsonObject.put("dropbox", this.dropbox.getInfo());
        }
        if (null != this.google.getToken()) {
            System.out.println(this.google.getToken());
            jsonObject.put("google", this.google.getInfo());
        }

        if (null == this.google.getToken() && null == this.dropbox.getToken()) {
            jsonObject.put("error", "Login first");
        }

        return Response.status(200).entity(jsonObject.toString()).build();
    }

    @GET
    @Path("detailFile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response detailFile(@QueryParam("name") String name) throws URISyntaxException {
        JSONObject jsonObject = new JSONObject();
        if (null != this.dropbox.getToken()) {
            System.out.println(this.dropbox.getToken());
            jsonObject.put("dropbox", this.dropbox.detailFile(name));

        }
        if (null != this.google.getToken()) {
            System.out.println(this.google.getToken());
            jsonObject.put("google", this.google.detailFile(name));
        }

        if (null == this.google.getToken() && null == this.dropbox.getToken()) {
            jsonObject.put("error", "Login first");
        }

        return Response.status(200).entity(jsonObject.toString()).build();
    }

    @GET
    @Path("rm")
    @Produces(MediaType.APPLICATION_JSON)
    public Response rm(@QueryParam("type") String type, @QueryParam("name") String name) throws URISyntaxException {
        JSONObject jsonObject = new JSONObject();
        if (null != this.dropbox.getToken() && ("dropbox").equals(type)) {
            System.out.println(this.dropbox.getToken());
            jsonObject.put("dropbox", this.dropbox.rm(name));

        }
        if (null != this.google.getToken() && ("google").equals(type)) {
            System.out.println(this.google.getToken());
            jsonObject.put("google", this.google.rm(name));
        }

        if (null == this.google.getToken() && null == this.dropbox.getToken()) {
            jsonObject.put("error", "Login first");
        }
        return Response.temporaryRedirect(new URI("http://localhost:9000/#/dashboard")).build();

//        return Response.status(200).entity(jsonObject.toString()).build();
    }

    @GET
    @Path("mv")
    public Response mv(@QueryParam("from") String from, @QueryParam("to") String to) throws URISyntaxException {
        this.dropbox.mv(from, to);
        this.google.mv(from, to);
        return Response.temporaryRedirect(new URI("http://localhost:9000/#/dashboard")).build();
//        return Response.status(200).entity(this.dropbox.mv(from, to).toString()).build();
    }

    @GET
    @Path("mkdir")
    @Produces(MediaType.APPLICATION_JSON)
    public Response mkdir(@QueryParam("name") String dir) throws URISyntaxException {
        this.dropbox.mkdir(dir);
        this.google.mkdir(dir);
        return Response.temporaryRedirect(new URI("http://localhost:9000/#/dashboard")).build();
//        return Response.status(200).entity(this.dropbox.mkdir(dir).toString()).build();
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
    public Response login(@QueryParam("user") String user, @QueryParam("password") String password) {
        JSONObject jsonObject = new JSONObject();
        if (("a").equals(user)) {
            jsonObject.put("stat", "ok");
        } else {
            jsonObject.put("stat", "fail");
        }
        return Response.status(200).entity(jsonObject.toString()).build();
    }

    @POST
    @Path("/upload")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@FormDataParam("file") InputStream uploadedInputStream, @FormDataParam("path") String path) throws URISyntaxException {
        InputStream inputStream = new EntityInputStream(uploadedInputStream);
        this.dropbox.upload(path, inputStream);
//        return Response.status(200).entity(this.dropbox.upload(path, inputStream).toString()).build();
//        return Response.temporaryRedirect(new URI("http://localhost:9000/#/dashboard").build();
        return Response.seeOther(new URI("http://localhost:9000/#/dashboard")).build();
    }

    @GET
    @Path("/space")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpace() {
        JSONObject jsonObject = new JSONObject();
        if (null != this.dropbox.getToken()) {
            jsonObject.put("dropbox", this.dropbox.get_space_usage());
        }
        if (null != this.google.getToken()) {
            System.out.println(this.google.getToken());
            jsonObject.put("google", this.google.get_space_usage());
        }
        if (null == this.google.getToken() && null == this.dropbox.getToken()) {
            jsonObject.put("error", "Login first");
        }
        return Response.status(200).entity(jsonObject.toString()).build();
    }
}
