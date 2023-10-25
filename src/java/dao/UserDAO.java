package dao;

import connection._DBContext;
import dto.UserGoogleDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Role;
import model.Semester;
import model.User;
import utils.Helper;

public class UserDAO extends _DBContext {

    public static String LOGIN_MOBILE = "mobile", LOGIN_EMAIL = "email";

    public User getUserLogin(String filed, String account, String password) {
        try {
            String strSQL = "select* from account a where a." + filed + "= ? and a.password = ?";
            System.out.println(strSQL);
            PreparedStatement stm = connection.prepareStatement(strSQL);
            stm.setObject(1, account);
            stm.setObject(2, password);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt("accountId"), new Role(rs.getInt("roleId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<User> getUserList(boolean flag) {
        ArrayList<User> listUsers = new ArrayList<>();
        try {
            String strSQL = "select a.*,r.roleName " + ((flag) ? ",s.semesterName" : "") + " from account a\n"
                    + " join Role r on r.roleId = a.roleId\n"
                    + ((!flag) ? " " : " join semester s on s.SemesterId = a.semesterId");

            PreparedStatement stm = connection.prepareStatement(strSQL);
            ResultSet rs = stm.executeQuery();
            System.out.println(strSQL);
            while (rs.next()) {
                if (flag) {
                    User u = new User(
                            rs.getInt("accountId"),
                            String.valueOf(rs.getString("email")),
                            String.valueOf(rs.getString("mobile")),
                            String.valueOf(rs.getString("password")),
                            String.valueOf(rs.getString("oauthId")),
                            new Role(rs.getInt("roleId"), rs.getNString("roleName")),
                            String.valueOf(rs.getString("accountName")),
                            String.valueOf(rs.getString("fullName")),
                            String.valueOf(rs.getString("imgPath")),
                            new Semester(rs.getInt("semesterId"), rs.getString("SemesterName")),
                            rs.getTimestamp("createDate"),
                            rs.getByte("isActived")
                    );listUsers.add(u);
                } else {//int userId, String email,String mobile , String password, String oauthId, Role role,String accountName,String fullName, String image,Timestamp dateCreated,byte isActived
                    User u = new User(
                            rs.getInt("accountId"),
                            String.valueOf(rs.getString("email")),
                            String.valueOf(rs.getString("mobile")),
                            String.valueOf(rs.getString("password")),
                            String.valueOf(rs.getString("oauthId")),
                            new Role(rs.getInt("roleId"), rs.getNString("roleName")),
                            String.valueOf(rs.getString("accountName")),
                            String.valueOf(rs.getString("fullName")),
                            String.valueOf(rs.getString("imgPath")),
                            rs.getTimestamp("createDate"),
                            rs.getByte("isActived")
                    );listUsers.add(u);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listUsers;
    }

    public User getUserByEmailOrMobile(String field, String usernameOrEmail) {
        try {
            String sqlQuery = "select a.*,r.roleName from account a"
                    + " join Role r on r.roleId = a.roleId\n"
                    + " WHERE " + field + "  = ?\n"
                    + "AND a.IsActived = 1";
            System.out.println(sqlQuery);
            PreparedStatement stm = connection.prepareStatement(sqlQuery);
            stm.setString(1, usernameOrEmail);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("accountId"),
                        String.valueOf(rs.getString("email")),
                        String.valueOf(rs.getString("mobile")),
                        String.valueOf(rs.getString("password")),
                        String.valueOf(rs.getString("oauthId")),
                        new Role(rs.getInt("roleId"), rs.getNString("roleName")),
                        String.valueOf(rs.getString("accountName")),
                        String.valueOf(rs.getString("fullName")),
                        String.valueOf(rs.getString("imgPath")),
                        rs.getTimestamp("createDate"),
                        rs.getByte("isActived")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateGoogleUser(int accountId, String oauthId,
            String fullName, String imgPath) {
        String sql = "update account\n"
                + "set OauthId = ?, FullName = ?, ImgPath = ?\n"
                + "where accountId = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, oauthId);
            ps.setString(2, fullName);
            ps.setString(3, imgPath);
            ps.setObject(4, accountId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public static int getRoleIdByRoleName(String roleName) {
        try {
            String sqlQuery = "select *\n"
                    + "from role\n"
                    + "where roleName = ?";
            PreparedStatement stm = new _DBContext().getConnection().prepareStatement(sqlQuery);
            stm.setString(1, roleName);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean addUser(UserGoogleDTO userInfo, int roleId, String mobile, String password) {
        String sql = "insert into account (email,mobile,password,oauthId,roleId,"
                + "accountName,fullName,imgPath,isActived,createDate" + ((roleId == Role.ROLE_STUDENT) ? ",semesterId" : " ") + ") values\n"
                + ((roleId == Role.ROLE_STUDENT) ? "(?,?,?,?,?,?,?,?,?,?,?)" : "(?,?,?,?,?,?,?,?,?,?)");
        System.out.println(sql);
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, userInfo.getEmail());
            ps.setObject(2, mobile);
            ps.setObject(3, password);
            ps.setObject(4, userInfo.getId());
            ps.setObject(5, roleId);
            ps.setObject(6, userInfo.getGiven_name());
            ps.setObject(7, userInfo.getName());
            ps.setObject(8, userInfo.getPicture());
            ps.setObject(9, 1);
            ps.setObject(10, Helper.getCurrentTimestamp());
            if (roleId == Role.ROLE_STUDENT) {
                ps.setObject(11, Semester.semester_FALL2023);
            }
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

//    public boolean createUser(String username, String email) {
//         String sql = "insert into [account] (Username, Email, IsActive)\n"
//                + "values (?, ?, 1)";
//        try ( Connection con = new _DBContext().getConnection();  PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//            ps.setString(1, username);
//            ps.setString(2, email);
////            ps.setInt(3, roleId);
//            ps.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }return false;
//    }
}
