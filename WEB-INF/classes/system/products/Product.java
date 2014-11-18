package system.products;

public class Product {

    private String id;
    private String productName;
    private String category;


    public Product (String id, String productName, String category) {
        this.id = id;
        this.productName = productName;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }
}
