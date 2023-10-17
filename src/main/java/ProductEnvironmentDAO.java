import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductEnvironmentDAO {
    private Connection connection;

    public ProductEnvironmentDAO(Connection connection) {
        this.connection = connection;
    }

    public void createProductEnvironment(ProductEnvironment productEnvironment) throws SQLException {
        String insertQuery = "INSERT INTO productEnvironment (product, environment, version) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, productEnvironment.getProduct());
            preparedStatement.setString(2, productEnvironment.getEnvironment());
            preparedStatement.setString(3, productEnvironment.getVersion());
            preparedStatement.executeUpdate();
        }
    }

    public ProductEnvironment getProductEnvironment(int productId, String environment) throws SQLException {
        String selectQuery = "SELECT * FROM productEnvironment WHERE product = ? AND environment = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, productId);
            preparedStatement.setString(2, environment);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new ProductEnvironment(resultSet.getInt("product"), resultSet.getString("environment"), resultSet.getString("version"));
            }
        }
        return null;
    }

    public List<ProductEnvironment> getAllProductEnvironments(int productId) throws SQLException {
        List<ProductEnvironment> productEnvironments = new ArrayList<>();
        String selectQuery = "SELECT * FROM productEnvironment WHERE product = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, productId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    productEnvironments.add(new ProductEnvironment(
                            resultSet.getInt("product"),
                            resultSet.getString("environment"),
                            resultSet.getString("version")
                    ));
                }
            }
        }
        return productEnvironments;
    }

    public void updateProductEnvironment(ProductEnvironment productEnvironment) throws SQLException {
        String updateQuery = "UPDATE productEnvironment SET version = ? WHERE product = ? AND environment = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, productEnvironment.getVersion());
            preparedStatement.setInt(2, productEnvironment.getProduct());
            preparedStatement.setString(3, productEnvironment.getEnvironment());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteProductEnvironment(int productId, String environment) throws SQLException {
        String deleteQuery = "DELETE FROM productEnvironment WHERE product = ? AND environment = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, productId);
            preparedStatement.setString(2, environment);
            preparedStatement.executeUpdate();
        }
    }
}
