package edu.eci.cvds.model.dao.jdbc;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("all")
public class JdbcServices {

    private static final String URL = "jdbc:postgresql://localhost:5432/labinfo";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password";

    public static AuthenticationInfo fetchAuthenticationInfoByUsername(String username, String realmName) {
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            con.setAutoCommit(false);

            String queryString = "SELECT username, \"password\" FROM \"USERS\" WHERE username = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(queryString);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String usernameFetched = resultSet.getString("username");
                String passwordFetched = resultSet.getString("password");

                authenticationInfo = new SimpleAuthenticationInfo(usernameFetched, passwordFetched, realmName);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(JdbcServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return authenticationInfo;
    }

    public static AuthorizationInfo fetchAuthorizationInfoByUsername(String username, String realmName) {
        AuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            con.setAutoCommit(false);

            String queryString = "SELECT r.\"name\" AS \"ROLES_name\" FROM \"USERS_ROLES\" AS ur LEFT JOIN \"ROLES\" AS r ON ur.\"ROLES_id\" = r.id LEFT JOIN \"USERS\" AS u ON ur.\"USERS_id\" = u.id WHERE username = ?;";

            PreparedStatement preparedStatement = con.prepareStatement(queryString);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            Set<String> rolesSet = new HashSet<>();
            if (resultSet.next()) {
                String roleNameFetched = resultSet.getString("ROLES_name");
                rolesSet.add(roleNameFetched);
            }

            authorizationInfo = new SimpleAuthorizationInfo(rolesSet);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(JdbcServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return authorizationInfo;
    }
}
