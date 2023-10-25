package utils;

import constant.Constant;
import dto.UserGoogleDTO;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.InputStream;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.tomcat.util.codec.binary.Base64;

public class Helper {

    public static String getToken(final String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(Constant.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constant.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constant.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constant.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constant.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogleDTO getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constant.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        UserGoogleDTO googlePojo = new Gson().fromJson(response, UserGoogleDTO.class);

        return googlePojo;
    }

    public static String getRandomNumberString() {//oldpassword generate by system 
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    public static Properties getPropertiesByFileName(String fileName) {
        Properties properties = new Properties();
        try ( InputStream inputStream = Helper.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println(e);
        }
        return properties;
    }

    public static String generatePassword() {
        SecureRandom random = new SecureRandom();
        int length = Integer.valueOf(getPropertiesByFileName("constant/const.properties")
                .getProperty("password.generaterandom.length"));
        StringBuilder captcha = new StringBuilder(length);
        String characters = getPropertiesByFileName("constant/const.properties").getProperty("charater.captcha");
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            captcha.append(randomChar);
        }
        return captcha.toString();
    }

    public static String getRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int charsLength = chars.length();
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(charsLength)));
        }
        return sb.toString();
    }

    public static String getUsernameFromEmail(String email) {
        return email.split("@")[0];
    }


    public static Timestamp getCurrentTimestamp() {
        try {
            return new Timestamp(new Date().getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static int convertTime(String time) {
        String timePart[] = time.split(":");
        int timeCovert = (Integer.parseInt(timePart[0]) * 60 + Integer.parseInt(timePart[1])) * 60 + Integer.parseInt(timePart[2]);
        return timeCovert;
    }
    
    public static String encryptPassword(String password) throws Exception {
        Properties prop = getPropertiesByFileName("constant/const.properties");
        String key = prop.getProperty("secretIsSecret");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);

        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.encodeBase64String(encryptedBytes);
    }

    public static String decryptPassword(String encryptedPassword) throws Exception {
        Properties prop = getPropertiesByFileName("constant/const.properties");
        String key = prop.getProperty("secretIsSecret");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);

        byte[] encryptedBytes = Base64.decodeBase64(encryptedPassword);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            String password = "Long240303@1";

            String encryptedPassword = encryptPassword(password);
            System.out.println("Encrypted Password: " + encryptedPassword);

//            String decryptedPassword = decryptPassword("u4yTLWE/0W37zJGy/arE0w==");
//            System.out.println("Decrypted Password: " + decryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
