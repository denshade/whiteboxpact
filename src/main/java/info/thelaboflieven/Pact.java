package info.thelaboflieven;

import java.sql.Timestamp;

public class Pact {
    private int id;
    private Timestamp creationDate;
    private String payload;

    public Pact(int id, Timestamp creationDate, String payload) {
        this.id = id;
        this.creationDate = creationDate;
        this.payload = payload;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "info.thelaboflieven.Pact{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", payload='" + payload + '\'' +
                '}';
    }
}
