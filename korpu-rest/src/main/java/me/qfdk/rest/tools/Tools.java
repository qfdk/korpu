package me.qfdk.rest.tools;


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;

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
@SuppressWarnings("ALL")
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

    public static String sendPost(String url, Map<String, String> map) throws Exception {

        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        StringBuilder sb = new StringBuilder();

        for (String s : map.keySet()) {
            sb.append(s).append("=").append(map.get(s)).append("&");
        }
        String urlParameters = sb.toString().substring(0, sb.length() - 1);
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
    public static String sendPostToken(String url, Map<String, String> map,String token) throws Exception {

        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Authorization", "Bearer "+token);

        StringBuilder sb = new StringBuilder();

        for (String s : map.keySet()) {
            sb.append(s).append("=").append(map.get(s)).append("&");
        }
        String urlParameters = sb.toString().substring(0, sb.length() - 1);
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

    public static String sendUpload(String url, InputStream is) throws Exception {

        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpPut httpPut = new HttpPut(url);
        File file = new File("tmp");
        FileOutputStream out = new FileOutputStream(file);
        IOUtils.copy(is, out);
        httpPut.setEntity(new FileEntity(file));
        HttpResponse response = httpClient.execute(httpPut);
        out.close();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));
        System.out.println("\nSending 'Upload' request to URL : " + url);
        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
        String inputLine;
        StringBuilder ss = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            ss.append(inputLine);
        }
        return ss.toString();
    }

    public static String sendDel(String url) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(new HttpDelete(url));
        BufferedReader in = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));
        System.out.println("\nSending 'Delete' request to URL : " + url);
        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
        String inputLine;
        StringBuilder ss = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            ss.append(inputLine);
        }
        in.close();
        return ss.toString();
    }

    public static void saveConf(Map<String, String> map, String file_name) {
        Properties prop = new Properties();
        for (String s : map.keySet()) {
            prop.setProperty(s, map.get(s));
        }
        FileOutputStream fos = null;
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

    public static Properties readConf(String file_name) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file_name);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        Properties prop = new Properties();
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
