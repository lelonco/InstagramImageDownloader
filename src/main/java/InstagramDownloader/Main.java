package InstagramDownloader;

import org.brunocvcunha.instagram4j.Instagram4j;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String userName="_.e._.s._.c._.a._.p._.e._";
        Instagram4j instagram = new Autoload().autoLoadLogin();

        GetAllUserPosts getAllUserPosts=new GetAllUserPosts();
        GetUrl getUrl=new GetUrl();
        new Downloader().download(userName,getUrl.getUrl(getAllUserPosts.getAllUserPosts(instagram ,userName)));

    }
}
