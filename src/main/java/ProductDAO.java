import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements ProductRepository {
    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createProduct(Product product) throws SQLException {
        String insertQuery = "INSERT INTO product (name) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Product getProductById(int productId) throws SQLException {
        String selectQuery = "SELECT * FROM product WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Product(resultSet.getInt("id"), resultSet.getString("name"));
            }
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String selectQuery = "SELECT * FROM product";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt("id"), resultSet.getString("name")));
            }
        }
        return products;
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        String updateQuery = "UPDATE product SET name = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteProduct(int productId) throws SQLException {
        String deleteQuery = "DELETE FROM product WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
        }
    }
}
