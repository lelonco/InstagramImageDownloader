package InstagramDownloader;

import org.brunocvcunha.instagram4j.Instagram4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import org.apache.http.client.CookieStore;


public class Autoload {
    public Instagram4j autoLoadLogin() throws IOException, ClassNotFoundException {
        File loadCoockie = new File(System.getProperty("user.dir"), "Coockie");
        File loadLogin = new File(System.getProperty("user.dir"), "Login");
        if (loadLogin.exists() && loadCoockie.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(loadCoockie));
            CookieStore cookieStore = (CookieStore) ois.readObject();
            ois.close();
            ObjectInputStream lis = new ObjectInputStream(new FileInputStream(loadLogin));
            String inputTemp = (String) (lis.readObject());
            lis.close();
            String[] temp = inputTemp.split(";");
            Instagram4j instagram = Instagram4j.builder().username(temp[0])
                    .password(temp[1])
                    .uuid(temp[2])
                    .cookieStore(cookieStore)
                    .build();
            instagram.setup();
            return instagram;
        }
        else {
            return null;
        }
    }
}

