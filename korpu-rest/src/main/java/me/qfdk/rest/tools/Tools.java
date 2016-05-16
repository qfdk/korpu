package me.qfdk.rest.tools;


import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import static javax.ws.rs.core.HttpHeaders.USER_AGENT;

/**
 * Created by qfdk on 16/5/12.
 */
public class Tools {

    // HTTP GET request
    public static String sendGet(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        return response.toString();

    }
    public static String sendPost(String url,Map<String,String> map) throws Exception {

        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        StringBuilder sb=new StringBuilder();

        for(String s : map.keySet())
        {
            sb.append(s).append("=").append(map.get(s)).append("&");
        }
        String urlParameters = sb.toString().substring(0,sb.length()-1);
        System.out.println(urlParameters);
        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder ss = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            ss.append(inputLine);
        }
        in.close();
        return ss.toString();
    }

    public static void saveConf(Map<String,String> map,String file_name)  {
        Properties prop = new Properties();
        for(String s:map.keySet())
        {
            prop.setProperty(s,map.get(s));
        }
        FileOutputStream fos= null;
        try {
            fos = new FileOutputStream(file_name);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        try {
            prop.storeToXML(fos, file_name);
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            fos.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * get conf from conf xml
     */

    public static Properties readConf(String file_name)  {
        FileInputStream fis= null;
        try {
            fis = new FileInputStream(file_name);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        Properties prop=new Properties();
        try {
            prop.loadFromXML(fis);
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            fis.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return prop;
    }
}
