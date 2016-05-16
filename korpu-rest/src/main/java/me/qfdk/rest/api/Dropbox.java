    package me.qfdk.rest.api;

    import com.dropbox.core.DbxException;
    import com.dropbox.core.DbxRequestConfig;
    import com.dropbox.core.v2.DbxClientV2;
    import com.dropbox.core.v2.files.ListFolderResult;
    import com.dropbox.core.v2.files.Metadata;
    import com.dropbox.core.v2.users.FullAccount;

    import java.util.ArrayList;
    import java.util.List;

    /**
     * Created by qfdk on 16/5/10.
     */
    public class Dropbox {

        private String ACCESS_TOKEN = "";
        DbxRequestConfig config=null;
        DbxClientV2 client=null;
        FullAccount account=null;

        public Dropbox(String token)
        {
            this.ACCESS_TOKEN=token;
             config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
             client = new DbxClientV2(config, ACCESS_TOKEN);
        }

        public String info() throws DbxException {
            // Get current account info
            this.account = client.users().getCurrentAccount();
            return account.getName().getDisplayName();
        }

        public List<MyBean> test()
        {
            List<MyBean> l=new ArrayList<>();
            MyBean a=new MyBean("sasa",1);
            MyBean b=new MyBean("sasa",2);
            MyBean c=new MyBean("sasa",3);
            l.add(a);
            l.add(b);
            l.add(c);
            return l;

        }

        public List<String> getFiles()  {
            List<String> l=new ArrayList<>();
            try {
                ListFolderResult result = client.files().listFolder("");
                while (true) {
                    for (Metadata metadata : result.getEntries()) {
                        l.add(metadata.toStringMultiline());
                        System.out.println(result.getEntries());
                    }
              //      l.addAll(result.getEntries());
                    if (!result.getHasMore()) {
                        return l;
                    }
                    result = client.files().listFolderContinue(result.getCursor());
                }
            } catch (DbxException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
