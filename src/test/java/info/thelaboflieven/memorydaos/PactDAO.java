package info.thelaboflieven.memorydaos;

import info.thelaboflieven.Pact;
import info.thelaboflieven.PactRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PactDAO implements PactRepository {

        private List<Pact> pacts = new ArrayList<>();
        private int nextPactId = 1;

        @Override
        public void createPact(Pact pact) throws SQLException {
            try {
                pact.setId(nextPactId);
                pacts.add(pact);
                nextPactId++;
            } catch (Exception e) {
                throw new SQLException("Failed to create Pact");
            }
        }

        @Override
        public Pact getPactById(int pactId) throws SQLException {
            try {
                for (Pact pact : pacts) {
                    if (pact.getId() == pactId) {
                        return pact;
                    }
                }
                throw new SQLException("Pact not found");
            } catch (Exception e) {
                throw new SQLException("Failed to get Pact by ID");
            }
        }

        @Override
        public List<Pact> getAllPacts() throws SQLException {
            try {
                return new ArrayList<>(pacts);
            } catch (Exception e) {
                throw new SQLException("Failed to retrieve all Pacts");
            }
        }

        @Override
        public void updatePact(Pact pact) throws SQLException {
            try {
                int index = -1;
                for (int i = 0; i < pacts.size(); i++) {
                    if (pacts.get(i).getId() == pact.getId()) {
                        index = i;
                        break;
                    }
                }
                if (index != -1) {
                    pacts.set(index, pact);
                } else {
                    throw new SQLException("Pact not found for update");
                }
            } catch (Exception e) {
                throw new SQLException("Failed to update Pact");
            }
        }

        @Override
        public void deletePact(int pactId) throws SQLException {
            try {
                Pact pactToDelete = null;
                for (Pact pact : pacts) {
                    if (pact.getId() == pactId) {
                        pactToDelete = pact;
                        break;
                    }
                }
                if (pactToDelete != null) {
                    pacts.remove(pactToDelete);
                } else {
                    throw new SQLException("Pact not found for deletion");
                }
            } catch (Exception e) {
                throw new SQLException("Failed to delete Pact");
            }
        }

}
