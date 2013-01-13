package com.me4502.MAPL.util;

import java.security.MessageDigest;

public class EncodeUtil {

    public static String SHA256(String s) {
        try {
            MessageDigest messagedigest = MessageDigest.getInstance("SHA-256");
            byte abyte0[] = new byte[40];
            messagedigest.update(s.getBytes("iso-8859-1"), 0, s.length());
            abyte0 = messagedigest.digest();
            return convertToHex(abyte0);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String SHA1(String s) {
        try {
            MessageDigest messagedigest = MessageDigest.getInstance("SHA-1");
            byte abyte0[] = new byte[40];
            messagedigest.update(s.getBytes("iso-8859-1"), 0, s.length());
            abyte0 = messagedigest.digest();
            return convertToHex(abyte0);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String MD5(String s) {
        try {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            byte abyte0[] = new byte[40];
            messagedigest.update(s.getBytes("iso-8859-1"), 0, s.length());
            abyte0 = messagedigest.digest();
            return convertToHex(abyte0);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String convertToHex(byte abyte0[]) {
        StringBuffer stringbuffer = new StringBuffer();
        for (int i = 0; i < abyte0.length; i++) {
            int j = abyte0[i] >>> 4 & 0xf;
            int k = 0;
            do {
                if (0 <= j && j <= 9) {
                    stringbuffer.append((char) (48 + j));
                } else {
                    stringbuffer.append((char) (97 + j - 10));
                }
                j = abyte0[i] & 0xf;
            } while (k++ < 1);
        }

        return stringbuffer.toString();
    }
}