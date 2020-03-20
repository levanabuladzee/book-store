package dao;

import model.Books;
import model.Cart;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class CartDAO {
    public void addCart(int bookId) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "INSERT INTO cart(cart_book_id) VALUES(?)";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public ArrayList<Cart> findAll() {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        ArrayList<Cart> carts = new ArrayList<>();
        String sql = "SELECT cart_id, book_id, book_name, book_author, book_price " +
                "FROM cart " +
                "INNER JOIN books " +
                "ON cart.cart_book_id = books.book_id " +
                "ORDER BY cart_id ASC";

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int cartId = resultSet.getInt("cart_id");
                int bookId = resultSet.getInt("book_id");
                String bookName = resultSet.getString("book_name");
                String bookAuthor = resultSet.getString("book_author");
                double bookPrice = resultSet.getDouble("book_price");

                Cart cart = new Cart(cartId, bookId, bookName, bookAuthor, bookPrice);
                carts.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (statement != null) statement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        return carts;
    }

    public void removeBook(int cartId) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "DELETE FROM cart WHERE cart_id = (SELECT cart_id FROM cart WHERE cart_id = ?)";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cartId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
