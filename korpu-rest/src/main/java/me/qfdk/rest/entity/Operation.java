package me.qfdk.rest.entity;

import org.json.JSONObject;

import java.io.InputStream;

/**
 *
 * Created by qfdk on 16/5/15.
 *
 */

interface Operation {

    String getToken();
    // get authorisation
    JSONObject auth(String code) throws Exception;

    // get All files
    JSONObject ls();
    // space user
    JSONObject getInfo();
    // detail file
    JSONObject detailFile(String file);
    // rm file
    JSONObject rm(String file);
    // mv file
    JSONObject mv(String from,String to);
    // creer file
    JSONObject mkdir(String dir);
    // upload file
    JSONObject upload(String path, InputStream inputStream);

    JSONObject share(String file);

}
