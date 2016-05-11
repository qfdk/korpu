package me.qfdk.rest.api;


import com.dropbox.core.v2.files.Metadata;

/**
 * Created by 15004879 on 11/05/2016.
 */
public class MyMetadata extends Metadata {

    private String name;

    public MyMetadata(String name, String pathLower, String pathDisplay, String parentSharedFolderId) {
        super(name, pathLower, pathDisplay, parentSharedFolderId);
    }

    public MyMetadata(String name, String pathLower, String pathDisplay) {
        super(name, pathLower, pathDisplay);
    }

    public MyMetadata(Metadata metadata) {
        super("", "", "");
        this.setName(metadata.getName());
    }

    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
}
