package InstagramDownloader;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetMediaInfoRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetMediaInfoResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GetUrl {
    public String getUrl(Instagram4j instagram, long mediaId) throws IOException {
        String url = "";
        InstagramGetMediaInfoResult result = instagram.sendRequest(new InstagramGetMediaInfoRequest(mediaId));
        List<LinkedHashMap> tempArr;
        for (InstagramFeedItem item : result.getItems()) {
            for (Map.Entry<String, Object> tempMap : item.getImage_versions2().entrySet()) {
                tempArr = (ArrayList) tempMap.getValue();
                url = (String) tempArr.get(0).values().toArray()[2];
            }
        }
        return url;
    }

    public List<String> getUrl(List<InstagramFeedItem> userPosts) {
        List<LinkedHashMap> tempArr;
        List<String> url = new ArrayList<>();
        for (InstagramFeedItem item : userPosts) {
            for (Map.Entry<String, Object> tempMap : item.getImage_versions2().entrySet()) {
                tempArr = (ArrayList) tempMap.getValue();
                url.add((String) tempArr.get(0).values().toArray()[2]);
            }

        }
        return url;
    }
}
