import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Created by srg
 *
 * @date 2018/6/6
 */
public class getBytes {

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
    public static void main(String[] args){
        String s = "5pel5pel5pel";
        String se = encode(s);
//        String sd = decode(se);
//        System.out.println(se);
//        System.out.println(sd);
        System.out.println(decode(s));

    }
}
