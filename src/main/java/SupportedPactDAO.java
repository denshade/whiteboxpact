import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SupportedPactDAO {
    private Connection connection;

    public SupportedPactDAO(Connection connection) {
        this.connection = connection;
    }

    public void createSupportedPact(SupportedPact supportedPact) throws SQLException {
        String insertQuery = "INSERT INTO supportedPact (pactId, product, version, verifiedAt) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, supportedPact.getPactId());
            preparedStatement.setInt(2, supportedPact.getProduct());
            preparedStatement.setString(3, supportedPact.getVersion());
            preparedStatement.setTimestamp(4, supportedPact.getVerifiedAt());
            preparedStatement.executeUpdate();
        }
    }

    public SupportedPact getSupportedPactById(int pactId, int product) throws SQLException {
        String selectQuery = "SELECT * FROM supportedPact WHERE pactId = ? AND product = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, pactId);
            preparedStatement.setInt(2, product);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new SupportedPact(
                        resultSet.getInt("pactId"),
                        resultSet.getInt("product"),
                        resultSet.getString("version"),
                        resultSet.getTimestamp("verifiedAt")
                );
            }
        }
        return null;
    }

    public List<SupportedPact> getAllSupportedPacts() throws SQLException {
        List<SupportedPact> supportedPacts = new ArrayList<>();
        String selectQuery = "SELECT * FROM supportedPact";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                supportedPacts.add(new SupportedPact(
                        resultSet.getInt("pactId"),
                        resultSet.getInt("product"),
                        resultSet.getString("version"),
                        resultSet.getTimestamp("verifiedAt")
                ));
            }
        }
        return supportedPacts;
    }

    public void updateSupportedPact(SupportedPact supportedPact) throws SQLException {
        String updateQuery = "UPDATE supportedPact SET version = ?, verifiedAt = ? WHERE pactId = ? AND product = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, supportedPact.getVersion());
            preparedStatement.setTimestamp(2, supportedPact.getVerifiedAt());
            preparedStatement.setInt(3, supportedPact.getPactId());
            preparedStatement.setInt(4, supportedPact.getProduct());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteSupportedPact(int pactId, int product) throws SQLException {
        String deleteQuery = "DELETE FROM supportedPact WHERE pactId = ? AND product = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, pactId);
            preparedStatement.setInt(2, product);
            preparedStatement.executeUpdate();
        }
    }
}
