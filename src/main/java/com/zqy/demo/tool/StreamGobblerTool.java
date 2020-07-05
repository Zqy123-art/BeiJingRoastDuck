package com.zqy.demo.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


/**
 *
 * 流读取，用于shell的执行,防止堵塞
 *
 * **/

public class StreamGobblerTool extends Thread {

        InputStream is;
        String type;
        boolean hasException = false;

        public StreamGobblerTool(InputStream is, String type) {
            this.is = is;
            this.type = type;
        }

        public void run() {
            try {
                InputStreamReader isr = new InputStreamReader(is,Charset.forName("GBK"));
                BufferedReader br = new BufferedReader(isr);
                String line = null;
                while ((line = br.readLine()) != null) {
                    if (type.equals("Error")) {
                        System.out.println("Error   :" + line);
                        hasException = true;
                    } else {
                        System.out.println("Debug:" + line);
                    }
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

    public boolean getIsHasException() {
        return hasException;
    }
}

