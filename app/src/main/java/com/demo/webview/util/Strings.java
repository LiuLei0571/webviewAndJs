package com.demo.webview.util;

import android.text.TextUtils;

import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * 用途：
 * Created by milk on 17/1/18.
 * 邮箱：649444395@qq.com
 */

public abstract class Strings {

         public static final String EMPTY = "";
        public static final String BLANK = " ";
        public static final String EQUAL = "=";
        public static final String AND = "&";
        public static final String QMARK = "?";
        public static final String TRUE = "true";
        public static final String FALSE = "false";
        public static final String CANCEL = "cancel";
        public static final String NULL_STR = "null";
        public static final String SUCCESS = "success";
        public static final String FAIL = "fail";
        public static final String ZERO = "0";
        public static final String SEMICOLON = ";";
        public static final String AT = "@";
        public static final String COMMA = ",";

        /**
         * 去除字符串中的空格
         */
        public static String trimAll(CharSequence s) {
            if (s == null || s.length() == 0) return Strings.EMPTY;
            StringBuilder sb = new StringBuilder(s.length());
            for (int i = 0, L = s.length(); i < L; i++) {
                char c = s.charAt(i);
                if (c != ' ') sb.append(c);
            }
            return sb.toString();
        }

        /**
         * 判断是否为手机号码
         */
        public static boolean isPhoneNum(String phoneNum) {
            if (phoneNum == null || phoneNum.length() != 11) return false;
            if (!TextUtils.isDigitsOnly(phoneNum)) return false;
            Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");
            return p.matcher(phoneNum).find();
        }

        /**
         * 判断是否为座机号码
         */
        public static boolean isTelNum(String num) {
            if (TextUtils.isEmpty(num)) return false;
            Pattern p = Pattern.compile("(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?\\d{8}");
            return p.matcher(num).find();
        }

        /**
         * 判断字符串中是否有中文，有中文返回true
         */
        public static boolean isChinese(String string) {
            boolean flag = false;
            for (int i = 0, L = string.length(); i < L; i++) {
                char c = string.charAt(i);
                if ((c >= 0x4e00) && (c <= 0x9FA5)) {
                    flag = true;
                } else {
                    return false;
                }
            }
            return flag;
        }

        /**
         * 判断字符长度是否在范围里面。 当start end 为 -1 时，表示字符长度不考虑上线或下线
         *
         * @param str
         * @param start
         * @param end
         * @return
         */
        public static boolean isLengthInRange(String str, int start, int end) {
            boolean isInRange = true;
            int length = str.length();
            if (start != -1 && length < start) {
                isInRange = false;
            }
            if (end != -1 && end < length) {
                isInRange = false;
            }
            return isInRange;
        }

        /**
         * 字符串转换为long型
         */
        public static long toLong(String text, long defaultVal) {
            if (TextUtils.isDigitsOnly(text)) {
                try {
                    return Long.parseLong(text);
                } catch (NumberFormatException e) {
                }
            }
            return defaultVal;
        }

        /**
         * 字符串转换为int
         */
        public static int toInt(String text, int defaultVal) {
            if (TextUtils.isDigitsOnly(text)) {
                try {
                    return Integer.parseInt(text);
                } catch (NumberFormatException e) {
                }
            }
            return defaultVal;
        }

        /**
         * 首字母是否匹配
         */
        public static boolean isAcronymMatch(String name, String keyword) {
            String acronym = getPinYinHeadChar(name);
            return acronym.contains(keyword.toLowerCase()) || acronym.contains(keyword.toUpperCase());
        }

        /**
         * 提取每个汉字的首字母(大写)
         *
         * @param str
         * @return
         */
        public static String getPinYinHeadChar(String str) {
            return PinYinUtil.getAllFirstLetter(str);
        }

        public static String join(Iterable<?> iterable, String separator) {

            // handle null, zero and one elements before building a buffer
            if (iterable == null) {
                return null;
            }
            Iterator<?> iterator = iterable.iterator();
            if (!iterator.hasNext()) {
                return EMPTY;
            }
            Object first = iterator.next();
            if (!iterator.hasNext()) {
                return toString(first);
            }

            // two or more elements
            StringBuffer buf = new StringBuffer(256); // Java default is 16, probably too small
            if (first != null) {
                buf.append(first);
            }

            while (iterator.hasNext()) {
                if (separator != null) {
                    buf.append(separator);
                }
                Object obj = iterator.next();
                if (obj != null) {
                    buf.append(obj);
                }
            }
            return buf.toString();
        }

        public static String toString(Object obj) {
            return obj == null ? "" : obj.toString();
        }

        /**
         * 判断是否为字符串是否为空
         */
        public static boolean toBoolean(String property, boolean defaultVal) {
            return property == null ? defaultVal : Boolean.valueOf(property);
        }
    }

