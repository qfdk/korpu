package me.qfdk.rest.entity;

import org.json.JSONObject;

/**
 * Created by qfdk on 16/5/15.
 */

public interface Opetration {

    String getToken();
    JSONObject auth(String code) throws Exception;

    JSONObject getInfo();

    JSONObject getFiles();
}
