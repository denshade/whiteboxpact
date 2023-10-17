package info.thelaboflieven.sqldao;

import info.thelaboflieven.Pact;
import info.thelaboflieven.PactRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PactDAO implements PactRepository {
    private Connection connection;

    public PactDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createPact(Pact pact) throws SQLException {
        String insertQuery = "INSERT INTO pact (creationDate, payload) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setTimestamp(1, pact.getCreationDate());
            preparedStatement.setString(2, pact.getPayload());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Pact getPactById(int pactId) throws SQLException {
        String selectQuery = "SELECT * FROM pact WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, pactId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Pact(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("creationDate"),
                        resultSet.getString("payload")
                );
            }
        }
        return null;
    }

    @Override
    public List<Pact> getAllPacts() throws SQLException {
        List<Pact> pacts = new ArrayList<>();
        String selectQuery = "SELECT * FROM pact";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                pacts.add(new Pact(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("creationDate"),
                        resultSet.getString("payload")
                ));
            }
        }
        return pacts;
    }

    @Override
    public void updatePact(Pact pact) throws SQLException {
        String updateQuery = "UPDATE pact SET creationDate = ?, payload = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setTimestamp(1, pact.getCreationDate());
            preparedStatement.setString(2, pact.getPayload());
            preparedStatement.setInt(3, pact.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deletePact(int pactId) throws SQLException {
        String deleteQuery = "DELETE FROM pact WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, pactId);
            preparedStatement.executeUpdate();
        }
    }
}
