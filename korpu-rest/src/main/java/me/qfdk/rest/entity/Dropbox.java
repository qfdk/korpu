package me.qfdk.rest.entity;

import me.qfdk.rest.tools.Tools;
import org.json.JSONObject;

import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 *
 */
public class Dropbox implements Opetration {

    private String my_token=null;
    private String client_id=null;
    private String client_secret=null;
    private String redirect_uri=null;

    private static Dropbox instance=null;

    private Dropbox(){

        Properties json=Tools.readConf("conf/dropbox.xml");
        this.client_id=json.getProperty("client_id");
        this.client_secret=json.getProperty("client_secret");
        this.redirect_uri=json.getProperty("redirect_uri");

    }

    public static Dropbox getInstance() {
        if(instance==null)
        {
            instance=new Dropbox();
        }
        return instance;
    }

    public Response getUrl() throws Exception {
        String url="https://www.dropbox.com/1/oauth2/authorize?response_type=code&client_id="
                +client_id
                +"&redirect_uri="
                +redirect_uri;
        return Response.status(200).entity(url.toString()).build();
    }

    @Override
    public String getToken() {
        return my_token;
    }

    @Override
    public JSONObject auth(String code) throws Exception {
        Map<String,String> map= new TreeMap<>();
        map.put("code",code);
        map.put("client_id", client_id);
        map.put("client_secret", client_secret);
        map.put("grant_type", "authorization_code");
        map.put("redirect_uri",redirect_uri);
        String res=Tools.sendPost("https://api.dropboxapi.com/1/oauth2/token",map);
        JSONObject json=new JSONObject(res);
        System.out.println(json);
        this.my_token=json.getString("access_token");
        return  json;
    }
    @Override
    public JSONObject getInfo() {
        String ret= null;
        try {
            ret = Tools.sendGet("https://api.dropboxapi.com/1/metadata/auto/?access_token="+this.my_token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JSONObject(ret);
    }

    @Override
    public JSONObject getFiles() {
        String ret= null;
        try {
            ret = Tools.sendGet("https://api.dropboxapi.com/1/metadata/auto/?access_token="+this.my_token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JSONObject(ret);    }
}
