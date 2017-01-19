package com.demo.webview.util;

import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * 取得给定汉字串的首字母串,即声母串
 */
public abstract class PinYinUtil {
    private final static int[] li_SecPosValue = {
            1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787, 3106, 3212, 3472,
            3635, 3722, 3730, 3858, 4027, 4086, 4390, 4558, 4684, 4925, 5249, 5590
    };//不包含i,u,v汉语拼音没有这几个
    private final static char[] lc_FirstLetter = "ABCDEFGHJKLMNOPQRSTWXYZ".toCharArray();
    private static final Charset GBK = Charset.forName("GBK");
    private static final Charset ISO8859_1 = Charset.forName("ISO8859-1");

    /**
     * 取得给定汉字串的首字母串,即声母串
     *
     * @param str 给定汉字串
     * @return 声母串
     */
    public static String getAllFirstLetter(String str) {
        if (str == null) return "";
        str = str.trim();
        int L = str.length();
        if (L == 0) return "";
        StringBuilder sb = new StringBuilder(L);
        for (int i = 0; i < L; i++) {
            sb.append(getFirstLetter(str.charAt(i)));
        }
        return sb.toString();
    }

    /**
     * 取得给定汉字的首字母,即声母
     *
     * @return 给定汉字的声母
     */
    public static char getFirstLetter(char c) {
        if (c < 0x4e00 || c > 0x9FA5) {
            return c;//非中文
        }
        CharBuffer cb = ISO8859_1.decode(GBK.encode(CharBuffer.wrap(new char[]{c})));
        if (cb.length() < 2) {//非中文
            return c;
        }

        int li_SectorCode = cb.charAt(0) - 160; //汉字区码
        int li_PositionCode = cb.charAt(1) - 160; //汉字位码
        int li_SecPosCode = li_SectorCode * 100 + li_PositionCode; //汉字区位码

        if (li_SecPosCode <= 1600 || li_SecPosCode >= 5590) {//非gb2312编码字符
            return c;
        }

        for (int i = 0; i < 23; i++) {
            if (li_SecPosCode >= li_SecPosValue[i] &&
                    li_SecPosCode < li_SecPosValue[i + 1]) {
                return lc_FirstLetter[i];
            }
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(getAllFirstLetter("12sd%%&()$要转换编码的字符串士大夫二位交换机更换十分手动阀实打"));
    }
}