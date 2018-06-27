package com.srg.exchange.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by srg
 *
 * @date 2018/5/29
 */
public class FileUtil {

    public static byte[] getFileBytes(File file) {
        byte[] ret = null;
        try {
            if (file == null) {
                return null;
            }
            FileInputStream in = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
            byte[] b = new byte[4096];
            int n;
            while ((n = in.read(b)) != -1) {
                out.write(b, 0, n);
            }
            in.close();
            out.close();
            ret = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
    public static String getUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
