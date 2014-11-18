package system.products;

import java.util.*;

public class ProductData {

    private HashMap products = new HashMap();


    public HashMap getProducts() {
        return products;
    }

    public ProductData() {

        products.put("1", new Product("1", "iPhone 6", "Phones"));
        products.put("2", new Product("2", "Droid MAXX", "Phones"));
        products.put("3", new Product("3", "Moto X", "Phones"));
        products.put("4", new Product("4", "iPhone 5S", "Phones"));
        products.put("5", new Product("5", "iPhone 5C", "Phones"));
        products.put("6", new Product("6", "Galaxy S3", "Phones"));
        products.put("7", new Product("7", "Galaxy S4", "Phones"));

        products.put("8", new Product("8", "Kindle", "Tablets"));
        products.put("9", new Product("9", "Nexus", "Tablets"));
        products.put("10", new Product("10", "Surface", "Tablets"));
        products.put("11", new Product("11", "Galaxy", "Tablets"));
        products.put("12", new Product("12", "iPad", "Tablets"));

        products.put("13", new Product("13", "MacBook", "Laptop"));
        products.put("14", new Product("14", "Asus", "Laptop"));
        products.put("15", new Product("15", "Sony", "Laptop"));
        products.put("16", new Product("16", "Lenovo", "Laptop"));

        products.put("17", new Product("17", "Panasonic", "TV"));
        products.put("18", new Product("18", "Samsung", "TV"));
        products.put("19", new Product("19", "Sony", "TV"));

    }

}
