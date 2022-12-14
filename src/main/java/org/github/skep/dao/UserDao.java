package org.github.skep.dao;

import org.github.skep.object.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao implements IContracts{
    @Override
    public User getUserDetails(String username) {
        User user = new User();
        Connection conn = H2DatabaseConnection.getConnectionToDatabase();
        try {
            String sql = "SELECT * FROM user";
            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setGender(rs.getString("gender"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
