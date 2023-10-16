package constant;

public interface Constant {

     String GOOGLE_CLIENT_ID = "294131505979-k1j339cr1irk9m29s6koi4voeucule3u.apps.googleusercontent.com";

     String GOOGLE_CLIENT_SECRET = "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";

     String GOOGLE_REDIRECT_URI = "http://localhost:9999/ITA/LoginGoogleController";

     String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

     String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

     String GOOGLE_GRANT_TYPE = "authorization_code";
    
     String GOOGLE_URL_LOGIN ="https://accounts.google.com/o/oauth2/auth?scope=email profile&redirect_uri=http://localhost:9999/ITA/LoginGoogleController&response_type=code&client_id=294131505979-k1j339cr1irk9m29s6koi4voeucule3u.apps.googleusercontent.com&approval_prompt=force";
}
