import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.*;
import java.util.ArrayList;

public class OrderLine {
    /**
     * Attributes: a Product, and a quantity (int)
     */
    private Product product;
    private int quantity;


    /**
     * Initializes the object with the product and quantity
     * @param product
     * @param quantity
     */
    public OrderLine(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Getters and Setters
     */
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    /**
     * @return the subtotal of the line (item price x qty)
     */
    public double subTotal(){
        double total = 0;
        total = (this.product.getPrice() * this.getQuantity());     // total = price x qty
        return total;
    }

    /**
     * @return the string representation of the order line.
     */
    public String toString(){
        return String.format("Line:{%5d x %s = $%10.2f}", this.getQuantity(), this.getProduct(), this.subTotal());
    }

    /**
     * Loads all the OrderLine objects for a specific Order
     * @param db the database connection
     * @param order the order to search the lines for. (can be shallowed loaded)
     * @return an arraylist of order lines.
     * HINT:
     *     Don't deep load the product, as it will be replaced at the Store object.
     */
    public static ArrayList<OrderLine> loadLinesForOrder(Connection db, Order order){   // load for specific Order
        ArrayList<OrderLine> orderLines = new ArrayList<>();    // ArrayList of order lines
        try {
            PreparedStatement orderLinesQuery = db
                    .prepareStatement("SELECT order_number, product_code, quantity FROM store.order_line" +
                                          " WHERE order_number = ?");

            orderLinesQuery.setObject(1, order.getOrderNumber());   // Loads the data into the SQL arg.
            ResultSet rows = orderLinesQuery.executeQuery();
            while (rows.next()){    // Should be one or none if the code doesn't exist.
                String orderNumber = rows.getString("order_number");
                Product aProduct = new Product(rows.getString("product_code"));
                int quantity = rows.getInt("quantity");
                OrderLine aOrderLine = new OrderLine(aProduct, quantity);

                orderLines.add(aOrderLine);
            }

            rows.close();
            orderLinesQuery.close();

        } catch (SQLException throwAbles) {
            throwAbles.printStackTrace();
        }

        return orderLines;      // return the orderLines ArrayList
    }

}   // end class OrderLine
