package info.thelaboflieven;

import java.sql.SQLException;
import java.util.List;

public interface SupportedPactRepository {
    void createSupportedPact(SupportedPact supportedPact) throws SQLException;

    SupportedPact getSupportedPactById(int pactId, int product) throws SQLException;

    List<SupportedPact> getAllSupportedPacts() throws SQLException;

    void updateSupportedPact(SupportedPact supportedPact) throws SQLException;

    void deleteSupportedPact(int pactId, int product) throws SQLException;
}
