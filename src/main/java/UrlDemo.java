
import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;

import java.io.IOException;
import java.net.URLDecoder;

/**
 * @author wangxingpeng
 * @version 1.0
 * @description: TODO
 * @date 2022/1/27 9:58
 */
public class UrlDemo {


    /**
     * URL解密
     *
     * @param uri     加密后的URL
     * @param charset <p>utf-8, gb2312, iso8859-1 ...</p>
     * @return 解密后的URL
     */
    public static String decode(String uri, String charset) {
        String url = uri;
        if (StrUtil.isBlank(url)) return "";

        url = url.replaceAll("/!b1", " ");
        url = url.replaceAll("/!c2", "=");
        url = url.replaceAll("/!c3", "\r");
        url = url.replaceAll("/!c4", "\n");

        //超过5位的编码，进行位置移动
        if (url.length() > 5) {
            char[] input = url.toCharArray();
            char[] output = new char[url.length()];
            //从下标(i=3)开始对调i-1与i+1的位置
            for (int i = 3; i < input.length - 1; i = i + 3) {
                char c1 = input[i - 1];
                input[i - 1] = input[i + 1];
                input[i + 1] = c1;
            }
            //折半调换位置
            System.arraycopy(input, url.length() - url.length() / 2, output, 0, url.length() / 2);
            System.arraycopy(input, 0, output, url.length() / 2, url.length() - url.length() / 2);

            url = new String(output);
        }

        Base64 b64 = new Base64();
        try {
            url = new String(b64.decode(url.getBytes(charset == null ? "utf-8" : charset)));
            url = URLDecoder.decode(url, charset == null ? "utf-8" : charset);
        } catch (IOException e) {
            return uri;
        }
        return url.trim();
    }


    public static void main(String[] args) {
        //1k92QHduYhJBR3BHcSesbz5ERWl0cTJkMEN0kT/!c2gNXc/!c2clVTlnRWb2bDc05jFmREdGdlbpFo5C1GdTJscGN2
        String urls = "/!pf/p/a1RtUCbmRzcvNSlHdWaEdoZvNlUGR0MTMQMwIDdDAjTDOyZ/!c2A0V2JXRmRtbslyRU1WY2UhayVzVWR3UmcvZEVhRXwWaHau/!/";
        urls = urls.replace("/!pf/p/a1","");
        urls = urls.replace("/!/","");
        System.out.println(urls);
        String url = decode(urls, null);
        System.out.printf("1111-" + url);
    }
}
