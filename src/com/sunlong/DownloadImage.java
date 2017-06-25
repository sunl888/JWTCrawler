package com.sunlong;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Sunlong on 2017/6/25.
 */
public class DownloadImage extends Thread {
    private final String PHOTOURI = "http://211.70.176.123/dbsdb/tp.asp?xh=";
    private final String PHOTOPATH = "./photos/";
    private final String EXT = ".png";
    private StudentInfoUI stuUI = null;
    private String stuNum = "";

    public DownloadImage(String stuNum, StudentInfoUI stuUI) {
        this.stuNum = stuNum;
        this.stuUI = stuUI;
    }

    @Override
    public void run() {
        OutputStream out = null;
        InputStream in = null;
        User user = new User();
        File filePath = new File(PHOTOPATH + stuNum + EXT);
        URL url = null;
        try {
            url = new URL(PHOTOURI + stuNum);
            HttpURLConnection httpUrlConnection = null;
            httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setRequestProperty("User-Agent", "Mozilla");
            if (500 == httpUrlConnection.getResponseCode()) {
                in = httpUrlConnection.getErrorStream();
                out = new BufferedOutputStream(new FileOutputStream(filePath));
                for (int b; (b = in.read()) != -1; ) {
                    out.write(b);
                }
                //刷新头像
                stuUI.refreshImage(PHOTOPATH + stuNum + EXT);
                user.updateImage(stuNum, PHOTOPATH + stuNum + EXT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                in = null;
                out = null;
            }
        }
    }
}
