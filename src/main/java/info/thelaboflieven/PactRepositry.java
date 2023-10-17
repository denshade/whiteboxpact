package info.thelaboflieven;

import java.sql.SQLException;
import java.util.List;

public interface PactRepositry {
    void createPact(Pact pact) throws SQLException;

    Pact getPactById(int pactId) throws SQLException;

    List<Pact> getAllPacts() throws SQLException;

    void updatePact(Pact pact) throws SQLException;

    void deletePact(int pactId) throws SQLException;
}
