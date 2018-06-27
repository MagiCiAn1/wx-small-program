package com.srg.exchange.util;

import com.srg.exchange.service.IPhotoService;
import com.srg.exchange.vo.ViewShowVo;
import com.srg.exchange.vo.ViewVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Created by srg
 *
 * @date 2018/6/2
 */
public class CommonUtil {

    public static boolean isNotBlank(String string){
        boolean flag = false;
        if (string != null) {
            string = string.trim();
            if(!string.equals("")){
                flag = true;
            }
        }
        return flag;
    }

    static final Base64.Decoder decoder = Base64.getDecoder();
    static final Base64.Encoder encoder = Base64.getEncoder();

    public static String encode(String string){

        byte[] bytes = new byte[0];
        try {
            bytes = string.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String encodedText = encoder.encodeToString(bytes);
        return encodedText;
    }

    public static String decode(String string){
        String s = null;
        try {
            s = new String(decoder.decode(string), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }
}
