package me.qfdk.rest.resource;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.Metadata;
import me.qfdk.rest.api.Dropbox;
import me.qfdk.rest.api.MyBean;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("v1")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    /**
     * @return MyPojo 以 application/json 形式响应
     */
    @GET
    @Path("files")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> files() throws DbxException {
        Dropbox d = new Dropbox("");
        List<String> l = d.getFiles();
        return l;
    }
    @GET
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MyBean> test() throws DbxException {
        Dropbox d = new Dropbox("-----");
        List<MyBean> l = d.test();
        return l;
    }

    @GET
    @Path("user")
    @Produces(MediaType.APPLICATION_JSON)
    public String user() throws DbxException {
        return new Dropbox("").info();
    }


}
