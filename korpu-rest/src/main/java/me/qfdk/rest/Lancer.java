package me.qfdk.rest;

import java.net.URI;

import org.glassfish.jersey.jetty.JettyHttpContainerFactory;

/**
 * Created by qfdk on 16/5/10.
 */
public class Lancer {

  public static final String BASE_URI = "http://localhost:8888/";
  public static void main(String[] argv) {
      JettyHttpContainerFactory.createServer(URI.create(BASE_URI),
              new RestApplication());
  }
}