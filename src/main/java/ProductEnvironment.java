public class ProductEnvironment {
    private int product;
    private String environment;
    private String version;

    public ProductEnvironment(int product, String environment, String version) {
        this.product = product;
        this.environment = environment;
        this.version = version;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ProductEnvironment{" +
                "product=" + product +
                ", environment='" + environment + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
