package me.qfdk.rest.entity;

import me.qfdk.rest.tools.Tools;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * Created by qfdk on 16/5/15.
 */
public class Google implements Opetration {

    private String client_id=null;
    private String client_secret=null;
    private String redirect_uri=null;

    private String my_token=null;

    private static Google instance =null;

    private Google() {
        Properties json= Tools.readConf("conf/google.xml");
        this.client_id=json.getProperty("client_id");
        this.client_secret=json.getProperty("client_secret");
        this.redirect_uri=json.getProperty("redirect_uri");
    }

    public static Google getInstance()
    {
        if(instance==null){
            instance=new Google();
        }
        return instance;
    }

    @Override
    public String getToken() {
        return my_token;
    }

    //https://accounts.google.com/o/oauth2/auth?
    // redirect_uri=https%3A%2F%2Fdevelopers.google.com%2Foauthplayground&
    // response_type=code&
    // client_id=407408718192.apps.googleusercontent.com&
    // scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fdrive+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fdrive+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fdrive&
    // approval_prompt=force&
    // access_type=offline
    @Override
    public JSONObject auth(String code) throws Exception {
        Map<String,String> map= new TreeMap<>();
        map.put("code",code);
        map.put("client_id", client_id);
        map.put("client_secret", client_secret);
        map.put("grant_type", "authorization_code");
        map.put("redirect_uri",redirect_uri);
        if(this.my_token==null)
        {
            String res=Tools.sendPost("https://www.googleapis.com/oauth2/v3/token",map);

            JSONObject json=new JSONObject(res);
            System.out.println(json);
            this.my_token=json.getString("access_token");
            return  json;
        }
        return  new JSONObject("error:aready login");
    }

    @Override
    public JSONObject getInfo() {
        String ret= null;
        try {
            ret = Tools.sendGet("https://www.googleapis.com/drive/v3/files?access_token="+this.my_token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JSONObject(ret);
    }

    @Override
    public JSONObject getFiles() {
        String ret= null;
        try {
            ret = Tools.sendGet("https://www.googleapis.com/drive/v3/files?access_token="+this.my_token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JSONObject(ret);
    }
}
