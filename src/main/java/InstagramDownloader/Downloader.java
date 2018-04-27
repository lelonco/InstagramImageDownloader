package InstagramDownloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;

public class Downloader {
    public void download (String userName, List<String>urls)
    {
        int counter=0;
        File path=new File(userName);
        if(!path.exists())
        {
            path.mkdir();
        }
        for (String url: urls)
        {
            downloadFileFromURL(url,new File(path,"Photo"+counter+".jpg"));
            counter++;
        }
    }
    public  void downloadFileFromURL(String urlString, File destination) {
        try {
            URL website = new URL(urlString);
            ReadableByteChannel rbc;
            rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(destination);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
