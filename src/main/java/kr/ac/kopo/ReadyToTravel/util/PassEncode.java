package kr.ac.kopo.ReadyToTravel.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PassEncode {

    /**
     * @param password (String)
     * @return 인코딩된 SHA-512 Password
     */
    public static String encode(String password) {
        try {
            // SHA-512 해시 함수 생성
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(password.getBytes());

            // Base64 인코딩
            return Base64.getEncoder().encodeToString(hash);
    //이거 한번 읽어보시고
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
