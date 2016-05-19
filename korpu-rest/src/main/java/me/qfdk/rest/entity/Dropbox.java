package me.qfdk.rest.entity;

import me.qfdk.rest.tools.Tools;
import org.json.JSONObject;

import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * Dropbox
 */
class Dropbox implements Operation {

    private String my_token = null;
    private String client_id = null;
    private String client_secret = null;
    private String redirect_uri = null;

    private static Dropbox instance = null;

    private Dropbox() {

        Properties json = Tools.readConf("conf/dropbox.xml");
        this.client_id = json.getProperty("client_id");
        this.client_secret = json.getProperty("client_secret");
        this.redirect_uri = json.getProperty("redirect_uri");

    }

    static Dropbox getInstance() {
        if (instance == null) {
            instance = new Dropbox();
        }
        return instance;
    }

    public Response getUrl() throws Exception {
        String url = "https://www.dropbox.com/1/oauth2/authorize?response_type=code&client_id="
                + client_id
                + "&redirect_uri="
                + redirect_uri;
        return Response.status(200).entity(url).build();
    }

    @Override
    public String getToken() {
        return my_token;
    }

    @Override
    public JSONObject auth(String code) throws Exception {
        Map<String, String> map = new TreeMap<>();
        map.put("code", code);
        map.put("client_id", client_id);
        map.put("client_secret", client_secret);
        map.put("grant_type", "authorization_code");
        map.put("redirect_uri", redirect_uri);
        if (this.my_token == null) {
            String res = Tools.sendPost("https://api.dropboxapi.com/1/oauth2/token", map);
            JSONObject json = new JSONObject(res);
            System.out.println(json);
            this.my_token = json.getString("access_token");
            return json;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error", "already login");
        return jsonObject;

    }

    @Override
    public JSONObject ls() {
        String ret = null;
        try {
            ret = Tools.sendGet("https://api.dropboxapi.com/1/metadata/auto/?access_token=" + this.my_token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JSONObject(ret);
    }

    @Override
    public JSONObject getInfo() {
        String query = null;
        JSONObject jsonObject = new JSONObject();
        try {
            query = Tools.sendGet("https://api.dropboxapi.com/1/account/info?access_token=" + this.my_token);
        } catch (Exception e) {
            jsonObject.put("error", e);
        }
        return new JSONObject(query);
    }

    @Override
    public JSONObject detailFile(String file) {
        String query = null;
        JSONObject jsonObject = new JSONObject();
        try {
            query = Tools.sendGet("https://api.dropboxapi.com/1/metadata/auto/" + file + "?access_token=" + this.my_token);
        } catch (Exception e) {
            JSONObject error = new JSONObject();
            error.put("error", "not found");
            return error;
        }
        return new JSONObject(query);
    }

    @Override
    public JSONObject rm(String file) {
        Map<String, String> map = new TreeMap<>();
        map.put("root", "auto");
        map.put("path", file);
        map.put("access_token", my_token);
        String res = null;
        try {
            res = Tools.sendPost("https://api.dropboxapi.com/1/fileops/delete", map);
        } catch (Exception e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("error", "problem");
            return jsonObject;
        }
        JSONObject json = new JSONObject(res);
        return json;
    }

    @Override
    public JSONObject mv(String from, String to) {
        Map<String, String> map = new TreeMap<>();
        map.put("root", "auto");
        map.put("from_path", from);
        map.put("to_path", to);
        map.put("access_token", my_token);
        String res = null;
        try {
            res = Tools.sendPost("https://api.dropboxapi.com/1/fileops/move", map);
        } catch (Exception e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("error", "problem");
            return jsonObject;
        }
        JSONObject json = new JSONObject(res);
        return json;
    }

    @Override
    public JSONObject mkdir(String dir) {
        Map<String, String> map = new TreeMap<>();
        map.put("root", "auto");
        map.put("path", dir);
        map.put("access_token", my_token);
        String res = null;
        try {
            res = Tools.sendPost("https://api.dropboxapi.com/1/fileops/create_folder", map);
        } catch (Exception e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("error", "problem");
            return jsonObject;
        }
        JSONObject json = new JSONObject(res);
        return json;
    }

    @Override
    public JSONObject upload(String path, InputStream inputStream) {
        Map<String, String> map = new TreeMap<>();
        map.put("path", path);
        map.put("mode","add");
        map.put("autorename","true");
        map.put("mute","false");
        map.put("access_token", my_token);
        String res = null;
        try {
            res = Tools.sendUpload("https://content.dropboxapi.com/2/files/upload", map,inputStream);
        } catch (Exception e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("error", "problem");
            return jsonObject;
        }
        JSONObject json = new JSONObject(res);
        return json;
    }


    @Override
    public JSONObject share(String file) {
        Map<String, String> map = new TreeMap<>();
        map.put("access_token", my_token);
        String res = null;
        try {
            res = Tools.sendPost("https://api.dropboxapi.com/1/shares/auto/" + file, map);
        } catch (Exception e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("error", "problem");
            return jsonObject;
        }
        JSONObject json = new JSONObject(res);
        return json;
    }

}
