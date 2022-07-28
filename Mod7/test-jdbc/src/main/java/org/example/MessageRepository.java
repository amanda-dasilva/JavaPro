package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageRepository {


    public void insertMessage(Message message) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            String sql = "Insert into messages values(?,?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, message.getId());
            preparedStatement.setString(2, message.getMessage());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public List<Message> findAll() {
        String sql = "select * from messages";
        PreparedStatement preparedStatement = null;
        try (Connection conn = ConnectionFactory.getConnection()){
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Message> messages = new ArrayList<>();
            while(resultSet.next()) {
                Message message = new Message(resultSet.getLong(1), resultSet.getString(2));
                messages.add(message);
            }
            resultSet.close();
            return messages;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Message message){
        String sql = "update messages set message = ? where id = ?";
        PreparedStatement preparedStatement = null;
        try (Connection conn = ConnectionFactory.getConnection()){
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, message.getMessage());
            preparedStatement.setLong(2, message.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id){
        String sql = "delete from messages where id = ?";
        PreparedStatement preparedStatement = null;
        try (Connection conn = ConnectionFactory.getConnection()){
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
