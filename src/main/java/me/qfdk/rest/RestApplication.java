package me.qfdk.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 * Created by qfdk on 16/5/10.
 */
public class RestApplication extends ResourceConfig{
    public RestApplication() {
        // 资源类所在的包路径
        packages("me.qfdk.rest.resource");
        // 注册 MultiPart
        register(MultiPartFeature.class);
    }
}
