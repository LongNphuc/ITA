package model;

import java.sql.Timestamp;

public class User {

    private int userId;
    private String mobile,accountName, password, oauthId, email;
    private Role role;
    private String fullName, image;
    private Semester semester;
    private Timestamp dateCreated;
    private byte isActive;
    
    public User(int userId, String email,String mobile , String password, String oauthId, Role role,String accountName,String fullName, String image,Timestamp dateCreated,byte isActived) {
        this.userId = userId;
        this.mobile = mobile;
        this.accountName = accountName;
        this.password = password;
        this.oauthId = oauthId;
        this.email = email;
        this.role = role;
        this.fullName = fullName;
        this.image = image;
        this.dateCreated = dateCreated;
        this.isActive = isActived;
    }

    public User(int userId, String email,String mobile , String password, String oauthId, Role role,String accountName,String fullName, String image,Semester semester,Timestamp dateCreated,byte isActived) {
        this.userId = userId;
        this.mobile = mobile;
        this.accountName = accountName;
        this.password = password;
        this.oauthId = oauthId;
        this.email = email;
        this.role = role;
        this.fullName = fullName;
        this.image = image;
        this.semester = semester;
        this.dateCreated = dateCreated;
        this.isActive = isActived;
    }

    public int getUserId() {
        return userId;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getPassword() {
        return password;
    }

    public String getOauthId() {
        return oauthId;
    }

    public String getEmail() {
        try{
            return (!email.equals("null") ? this.email = email : "");
        }catch(Exception e) {
            return "";
        }
    }

    public Role getRole() {
        return role;
    }
  
    public String getFullName() {
        return fullName;
    }

    public String getImage() {
        return image;
    }

    public Semester getSemester() {
        return semester;
    }
    
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public byte getIsActive() {
        return isActive;
    }
    
    public String getIsActiveString() {
        return (isActive == 0) ? "False" : "True";
    }

    public String getMobile() {
        return mobile;
    }
    
    
}
