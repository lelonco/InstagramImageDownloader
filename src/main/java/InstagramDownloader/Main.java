package InstagramDownloader;

import org.brunocvcunha.instagram4j.Instagram4j;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String userName="_.e._.s._.c._.a._.p._.e._";
//        String login="2disreal";
//        String password= "987456987";
        Instagram4j instagram = new Autoload().autoLoadLogin();
//        instagram.setup();
//        instagram.login();
//        new AutoSave().autoSaveLogin(instagram,login,password);
        GetAllUserPosts getAllUserPosts=new GetAllUserPosts();
//        System.out.println(getAllUserPosts.getAllUserPosts(instagram ,userName));
        GetUrl getUrl=new GetUrl();
//        System.out.println(getUrl.getUrl(getAllUserPosts.getAllUserPosts(instagram ,userName)));
        new Downloader().download(userName,getUrl.getUrl(getAllUserPosts.getAllUserPosts(instagram ,userName)));

    }
}
