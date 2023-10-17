package info.thelaboflieven;

import java.sql.Timestamp;

public class SupportedPact {
    private int pactId;
    private int product;
    private String version;
    private Timestamp verifiedAt;

    public SupportedPact(int pactId, int product, String version, Timestamp verifiedAt) {
        this.pactId = pactId;
        this.product = product;
        this.version = version;
        this.verifiedAt = verifiedAt;
    }

    public int getPactId() {
        return pactId;
    }

    public void setPactId(int pactId) {
        this.pactId = pactId;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Timestamp getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(Timestamp verifiedAt) {
        this.verifiedAt = verifiedAt;
    }

    @Override
    public String toString() {
        return "info.thelaboflieven.SupportedPact{" +
                "pactId=" + pactId +
                ", product=" + product +
                ", version='" + version + '\'' +
                ", verifiedAt=" + verifiedAt +
                '}';
    }
}
