package info.thelaboflieven.memorydaos;

import info.thelaboflieven.SupportedPact;
import info.thelaboflieven.SupportedPactRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupportedPactDAO implements SupportedPactRepository {

        private List<SupportedPact> supportedPacts = new ArrayList<>();
        private Map<Integer, Map<Integer, SupportedPact>> supportedPactsMap = new HashMap<>();

        @Override
        public void createSupportedPact(SupportedPact supportedPact) throws SQLException {
            try {
                supportedPacts.add(supportedPact);

                int pactId = supportedPact.getPactId();
                int productId = supportedPact.getProduct();

                if (!supportedPactsMap.containsKey(pactId)) {
                    supportedPactsMap.put(pactId, new HashMap<>());
                }
                supportedPactsMap.get(pactId).put(productId, supportedPact);
            } catch (Exception e) {
                throw new SQLException("Failed to create SupportedPact");
            }
        }

        @Override
        public SupportedPact getSupportedPactById(int pactId, int product) throws SQLException {
            try {
                if (supportedPactsMap.containsKey(pactId)) {
                    Map<Integer, SupportedPact> productPacts = supportedPactsMap.get(pactId);
                    if (productPacts.containsKey(product)) {
                        return productPacts.get(product);
                    }
                }
                throw new SQLException("SupportedPact not found");
            } catch (Exception e) {
                throw new SQLException("Failed to get SupportedPact by ID");
            }
        }

        @Override
        public List<SupportedPact> getAllSupportedPacts() throws SQLException {
            try {
                return new ArrayList<>(supportedPacts);
            } catch (Exception e) {
                throw new SQLException("Failed to retrieve all SupportedPacts");
            }
        }

        @Override
        public void updateSupportedPact(SupportedPact supportedPact) throws SQLException {
            try {
                int pactId = supportedPact.getPactId();
                int productId = supportedPact.getProduct();

                if (supportedPactsMap.containsKey(pactId)) {
                    supportedPactsMap.get(pactId).put(productId, supportedPact);
                    for (int i = 0; i < supportedPacts.size(); i++) {
                        SupportedPact sp = supportedPacts.get(i);
                        if (sp.getPactId() == pactId && sp.getProduct() == productId) {
                            supportedPacts.set(i, supportedPact);
                            return;
                        }
                    }
                }
                throw new SQLException("SupportedPact not found for update");
            } catch (Exception e) {
                throw new SQLException("Failed to update SupportedPact");
            }
        }

        @Override
        public void deleteSupportedPact(int pactId, int product) throws SQLException {
            try {
                if (supportedPactsMap.containsKey(pactId)) {
                    Map<Integer, SupportedPact> productPacts = supportedPactsMap.get(pactId);
                    if (productPacts.containsKey(product)) {
                        SupportedPact pactToDelete = productPacts.remove(product);
                        supportedPacts.remove(pactToDelete);
                    } else {
                        throw new SQLException("SupportedPact not found for deletion");
                    }
                } else {
                    throw new SQLException("Pact not found for deletion");
                }
            } catch (Exception e) {
                throw new SQLException("Failed to delete SupportedPact");
            }
        }

}
