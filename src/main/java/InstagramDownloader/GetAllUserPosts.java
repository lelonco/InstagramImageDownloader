package InstagramDownloader;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUserFeedRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetAllUserPosts {
    public List<InstagramFeedItem> getAllUserPosts(Instagram4j instagram, String userName) throws IOException {
        InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest(userName));
        String nextMaxId = "";
        long userId = userResult.getUser().getPk();
        int limit = 10;
        List<InstagramFeedItem> userPosts = new ArrayList<>();
        InstagramSearchUsernameResult result = instagram.sendRequest(new org.brunocvcunha.instagram4j.requests.InstagramGetUserInfoRequest(userId));
        if (result.getUser().is_private != true) {
            while (true) {
                InstagramFeedResult userFeed = instagram.sendRequest(new InstagramUserFeedRequest(userId));
                nextMaxId = userFeed.getNext_max_id();
                userPosts.addAll(userFeed.getItems());
                if (nextMaxId == null || userPosts.size() > limit) {
                    break;
                }
            }
        }
        return userPosts;
    }
}
