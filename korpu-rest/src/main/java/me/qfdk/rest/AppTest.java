package me.qfdk.rest;

import me.qfdk.rest.tools.Tools;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by qfdk on 16/5/12.
 */
public class AppTest {
    public static void main(String [] args) throws Exception {
        String url="https://accounts.google.com/o/oauth2/token";

        Map<String,String> map= new TreeMap<>();
        map.put("client_id","");
        map.put("auth_uri","https://accounts.google.com/o/oauth2/auth");
        map.put("client_secret","RcFPWHnZ0NwKvs_qlTDHdy0s");
        map.put("redirect_uris","http://localhost:8080/api/v1/callbackGoogle");

        System.out.println(System.getProperty("user.dir"));

        Map<String,String> map2= new TreeMap<>();
        map2.put("client_id", "");
        map2.put("client_secret", "");
        map2.put("grant_type", "authorization_code");
        map2.put("redirect_uri","http://localhost:8080/api/v1/callbackDropbox");

        Tools.saveConf(map,"conf/google.xml");
        Tools.saveConf(map2,"conf/dropbox.xml");

//        JSONObject jsonObject = new JSONObject(Tools.sendPost(url,map).toString());
////        System.out.println(jsonObject);

    }
}
