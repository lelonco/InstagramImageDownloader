package InstagramDownloader;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetMediaInfoRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetMediaInfoResult;

import java.io.*;
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

    public List<String> getUrl(List<InstagramFeedItem> userPosts) throws IOException {
        List<LinkedHashMap> tempArr;
        List<String> url = new ArrayList<>();
        ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(new File("list")));
        int counter=0;
        int lenth;
        try {
            for (InstagramFeedItem item : userPosts) {
//                if(counter==84)
//                {
//                    System.out.println(item);
//                    counter++;
//                    continue;
//                }
                if(item.getImage_versions2()!=null) {
                    for (Map.Entry<String, Object> tempMap : item.getImage_versions2().entrySet()) {
                        tempArr = (ArrayList) tempMap.getValue();
                        lenth = tempArr.get(0).values().toArray().length;
                        if (lenth > 0) {
                            url.add((String) tempArr.get(0).values().toArray()[lenth - 1]);
                        }
                        counter++;
                    }
                }
                else
                {
                    FileWriter writer=new FileWriter("list",false);
                    writer.write(item.toString());
                    writer.flush();
                }
            }
        }
        catch (Exception e)
        {
            oos.close();
            System.out.println(counter);
            throw e;
        }
        oos.close();
        return url;
    }
}
