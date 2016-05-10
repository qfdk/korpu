/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.qfdk.korpu.share;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

/**
 *
 * @author qfdk
 */
public class Dropbox {

    private static String ACCESS_TOKEN = "";
    
    public Dropbox(String token)
    {
        Dropbox.ACCESS_TOKEN=token;
    }
    public static void main(String args[]) throws DbxException {
        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
    }
}
