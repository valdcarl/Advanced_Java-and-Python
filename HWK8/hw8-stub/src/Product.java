import java.sql.*;
import java.util.ArrayList;

public class Product {
    /**
     * Attributes code and description (both strings) and the product price (double)
     */
    private String code;
    private String description;
    private double price;

    /**
     * Initializes the object with only the code (for later load)
     * @param code
     */
    public Product(String code){
        // initializing with only the code (for later load)
        this(code, "",0);
    }

    /**
     * Initializes the objects with all code, description and price.
     * Price should be a non-negative number.
     * @param code
     * @param description
     * @param price
     */
    public Product(String code, String description, double price){
        this.code = code;
        this.description = description;
        this.setPrice(price);   // has to be conditionally checked, go to setPrice
    }

    /**
     * Getters and Setters.
     * price must be non-negative. IllegalArgumentException if the price is negative.
     * @return
     */
    public void setCode(String code){ this.code = code; }
    public String getCode(){ return this.code; }

    public void setDescription(String description){ this.description = description; }
    public String getDescription(){ return this.description; }

    public void setPrice(double price) throws IllegalArgumentException {
        if (price >= 0){    // conditional check to ensure price is non-negative
            this.price = price;
        } else {
            throw new IllegalArgumentException("price should be non-negative");
        }
    }
    public double getPrice(){ return this.price; }

    /**
     * @return Product price representation.
     */
    public String toString(){
        return String.format("Product:[%-20s|%-30s|%10.2f]",this.getCode(),this.getDescription(),this.getPrice());
    }

    /**
     * Loads the current product object using the code.
     * @param db the database connection
     */
    public void load(Connection db){    // loads individual Product using the code
        try {
            PreparedStatement productQuery = db
                    .prepareStatement("SELECT code, description, price FROM store.product WHERE code = ?");

            productQuery.setString(1, this.code);   // Loads the data into the SQL arg
            ResultSet rows = productQuery.executeQuery();       // execute the actual query
            while (rows.next()){                                // Should be one or none if the code doesn't exist.
                this.code = rows.getString("code");
                this.description = rows.getString("description");
                this.price = rows.getDouble("price");
            }

            rows.close();
            productQuery.close();

        } catch (SQLException throwAbles) {
            throwAbles.printStackTrace();
        }
    }

    /**
     * Loads all the products in the DB
     * @param db the database connection
     * @return an arraylist of products
     * HINT:
     *      don't use the object load.
     */
    public static ArrayList<Product> loadAll(Connection db){    // loads all Products
        ArrayList<Product> products = new ArrayList<>();
        try {
            PreparedStatement productQuery = db
                    .prepareStatement("SELECT code, description, price FROM store.product");

            ResultSet rows = productQuery.executeQuery();
            while (rows.next()){    // Should be one or none if the code doesn't exist.
                Product aProduct = new Product(rows.getString("code")); // uses the first constructor for Product
                aProduct.setDescription(rows.getString("description"));
                aProduct.setPrice(rows.getDouble("price"));

                products.add(aProduct);
            }

            rows.close();
            productQuery.close();

        } catch (SQLException throwAbles) {
            throwAbles.printStackTrace();
        }

        return products;    // return the products ArrayList
    }
}   // end class product
