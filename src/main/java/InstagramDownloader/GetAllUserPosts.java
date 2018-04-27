package InstagramDownloader;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUserFeedRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class GetAllUserPosts {
    public List<InstagramFeedItem> getAllUserPosts(Instagram4j instagram, String userName) throws IOException {
        InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest(userName));
        String nextMaxId="";
        long times=0;
        long userId = userResult.getUser().getPk();
        List<InstagramFeedItem> userPosts = new ArrayList<>();
        InstagramSearchUsernameResult result = instagram.sendRequest(new org.brunocvcunha.instagram4j.requests.InstagramGetUserInfoRequest(userId));
        if (result.getUser().is_private != true) {
            while (true) {
                InstagramFeedResult userFeed = instagram.sendRequest(new InstagramUserFeedRequest(userId,nextMaxId,times));
                nextMaxId = userFeed.getNext_max_id();
                userPosts.addAll(userFeed.getItems());
                System.out.println("Lenth:"+userPosts.size());
                if (nextMaxId == null /*||userPosts.size()>=190*/) {
                    break;
                }
            }
        }
        return userPosts;
    }
}
