package InstagramDownloader;

import org.brunocvcunha.instagram4j.Instagram4j;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String userName="2d_despair";
        String login="2disreal";
        String password= "987456987";
        Instagram4j instagram = Instagram4j.builder().username(login).password(password).build();
        instagram.setup();
        instagram.login();
        GetAllUserPosts getAllUserPosts=new GetAllUserPosts();
        System.out.println(getAllUserPosts.getAllUserPosts(instagram ,userName));
        GetUrl getUrl=new GetUrl();
        System.out.println(getUrl.getUrl(getAllUserPosts.getAllUserPosts(instagram ,userName)));
        new Downloader().download(userName,getUrl.getUrl(getAllUserPosts.getAllUserPosts(instagram ,userName)));
    }
}
