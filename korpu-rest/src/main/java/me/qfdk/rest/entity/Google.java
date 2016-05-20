package me.qfdk.rest.entity;

import me.qfdk.rest.tools.Tools;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 *
 * Google Drive
 * Created by qfdk on 16/5/15.
 *
 */
class Google implements Operation {

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

    static Google getInstance()
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
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("error","already login");
        return jsonObject;
    }


    @Override
    public JSONObject ls() {
        String query= null;
        JSONObject jsonObject=new JSONObject();
        try {
            query = Tools.sendGet("https://www.googleapis.com/drive/v3/files?access_token="+this.my_token);
        } catch (Exception e) {
            jsonObject.put("error",e);
        }
        assert query != null;
        return new JSONObject(query);
    }

    @Override
    public JSONObject getInfo() {
        String query = null;
        JSONObject jsonObject = new JSONObject();
        try {
            query = Tools.sendGet("https://www.googleapis.com/drive/v2/about?access_token="+this.my_token);
        } catch (Exception e) {
            jsonObject.put("error",e);
        }
        return new JSONObject(query);
    }

    @Override
    public JSONObject get_space_usage() {
        return null;
    }

    @Override
    public JSONObject detailFile(String file) {
        String query;
        try {
            query = Tools.sendGet("https://www.googleapis.com/drive/v2/files/"+file+"?access_token="+this.my_token);
        } catch (Exception e) {
            JSONObject error = new JSONObject();
            error.put("error","not found");
            return error;
        }
        return new JSONObject(query);
    }

    @Override
    public JSONObject rm(String file) {
        String res;
        try {
            res = Tools.sendDel("https://www.googleapis.com/drive/v2/files/"+file+"?access_token="+this.my_token);
        } catch (Exception e) {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("error","problem");
            return jsonObject;
        }
        return new JSONObject(res);
    }

    @Override
    public JSONObject mv(String from, String to) {

        return null;
    }

    @Override
    public JSONObject mkdir(String dir) {
        return null;
    }

    @Override
    public JSONObject upload(String path, InputStream inputStream) {
        return null;
    }

    @Override
    public JSONObject share(String file) {
        return null;
    }

}
