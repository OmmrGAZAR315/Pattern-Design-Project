//package org.example.socialmedia_proxy.DB_CRUD;
//
//import org.example.socialmedia_proxy.Model.UserProfile;
//
//import java.sql.CallableStatement;
//import java.sql.SQLException;
//
//public class Post_DBCRUD {
//    private Post_DBCRUD instance;
//    public Post_DBCRUD(String dbType) {
//    }
//
//    @Override
//    public void create(UserProfile userProfile) {
//        {
//            String callSql = "{CALL insert_users(?, ?)}";
//
//            try (CallableStatement callableStatement = databaseConnection.getConnection().prepareCall(callSql)) {
//
//                callableStatement.setString(1, userProfile.getName());
//                callableStatement.setInt(2, userProfile.getAge());
//                callableStatement.execute();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//
//    }
//
//    public void read(){
//    }
//
//    @Override
//    public void update() {
//
//    }
//
//    @Override
//    public void delete() {
//
//    }
//
//}