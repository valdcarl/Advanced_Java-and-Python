import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
    /**
     * Attributes: LocalDate date, String orderNumber, arraylist of orderlines called lines
     */
    private LocalDate date;
    private String orderNumber;
    private ArrayList<OrderLine> lines;

    /**
     * Initialized the order with only the orderNumber.
     * @param orderNumber
     */
    public Order(String orderNumber){
        this.orderNumber = orderNumber;
        this.date = null;
        this.lines = new ArrayList<>();
    }

    /**
     * Initializes the Order with the order number and date
     * @param orderNumber
     * @param date
     */
    public Order(String orderNumber, LocalDate date){
        this.orderNumber = orderNumber;
        this.date = date;
        this.lines = new ArrayList<>();
    }

    /**
     * Getters and Setters
     */
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public ArrayList<OrderLine> getLines() { return lines; }
    public void setLines(ArrayList<OrderLine> lines) { this.lines = lines; }

    /**
     * @return the total amount of the order by using the line subtotal.
     */
    public double total(){
        double sum = 0;
        if(!this.getLines().isEmpty()){     // if the lines array is not-empty
            for(OrderLine line: this.getLines()){
                sum += line.subTotal();     // must use line subtotal
            }
        } else {
            sum = 0;    // it is empty, sum is 0
        }
        return sum;

    }

    /**
     * @return a printout of the order.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("------------------------------------------------------------------------------------------------------------\n");
        sb.append(String.format("Order:[%-20s|%-10s]\n",this.getOrderNumber(),this.getDate()));
        for (OrderLine line: this.getLines()){
            sb.append(String.format("--->%-50s\n",line.toString()));
        }
        sb.append(String.format("---> Order Total: $%10.2f\n", this.total()));
        sb.append("============================================================================================================");
        return sb.toString();
    }

    /**
     * Loads the current object from the database.
     * @param db the database connection
     * Hint:
     *           once loaded the object's data, use the static method from OrderLine to load the lines of the order.
     */
    public void load(Connection db){    // load in a specific Order
        try {
            PreparedStatement orderQuery = db
                    .prepareStatement("SELECT order_number, order_date FROM store.purchase_order " +
                                          "WHERE order_number = ?");

            orderQuery.setString(1, this.orderNumber);   // Loads the data into the SQL arg
            ResultSet rows = orderQuery.executeQuery();
            while (rows.next()) {    // Should be one or none if the code doesn't exist.
                this.orderNumber = rows.getString("order_number");
                this.date = rows.getDate("order_date").toLocalDate();
                this.lines = OrderLine.loadLinesForOrder(db, this);
            }

            rows.close();
            orderQuery.close();

        } catch (SQLException throwAbles) {
            throwAbles.printStackTrace();
        }
    }

    /**
     * Loads all the orders in the DB
     * @param db the database connection
     * @return an arraylist of orders.
     * HINT:
     *  Use the order Load method.
     */
    public static ArrayList<Order> loadAll(Connection db){  // load all the Order/s
        ArrayList<Order> orders = new ArrayList<>();
        try {
            PreparedStatement orderQuery = db
                    .prepareStatement("SELECT order_number, order_date FROM store.purchase_order");

            ResultSet rows = orderQuery.executeQuery();
            while (rows.next()){    // Should be one or none if the code doesn't exist.
                Order aOrder = new Order(rows.getString("order_number"));
                aOrder.load(db);
                orders.add(aOrder);
            }

            rows.close();
            orderQuery.close();

        } catch (SQLException throwAbles) {
            throwAbles.printStackTrace();
        }

        return orders;      // returns the orders ArrayList
    }

    /**
     * Loads all the orders in the DB with the order date between a period of time.
     * @param db the database connection
     * @param start the period start (should be included)
     * @param end the period end (should be included)
     * @return an arraylist of orders
     * HINT:
     *  Use the order Load method.
     */
    public static ArrayList<Order> loadInPeriod(Connection db, LocalDate start, LocalDate end){ // load orders between period of time
        ArrayList<Order> orders = new ArrayList<>();
        try {
            PreparedStatement orderQuery = db   // only between sql method in README file
                    .prepareStatement("SELECT order_number, order_date FROM store.purchase_order " +
                                          "WHERE order_date BETWEEN ? and ?");

            orderQuery.setString(1, String.valueOf(start)); // return string represe. of passed param first ?
            orderQuery.setString(2, String.valueOf(end));   // return string represe. of passed param second ?
            ResultSet rows = orderQuery.executeQuery();
            while (rows.next()){
                Order aOrder = new Order(rows.getString("order_number"));
                aOrder.load(db);
                orders.add(aOrder);
            }

            rows.close();
            orderQuery.close();

        } catch (SQLException throwAbles) {
            throwAbles.printStackTrace();
        }

        return orders;      // returns the orders ArrayList
    }

    /**
     * Loads all the orders in the DB that contain a specific product
     * @param db the database connection
     * @param product the product. Can be shallow loaded.
     * @return an arraylist of orders.
     * HINT:
     *  Use the order Load method.
     */
    public static ArrayList<Order> loadOrdersWithProduct(Connection db, Product product){   // load containing specific product
        ArrayList<Order> orders = new ArrayList<>();
        try {
            PreparedStatement orderQuery = db
                    .prepareStatement("SELECT PO.order_number FROM store.purchase_order as PO, store.order_line L" +
                                          " WHERE PO.order_number = L.order_number and L.product_code = ?");

            orderQuery.setString(1, String.valueOf(product.getCode()));
            ResultSet rows = orderQuery.executeQuery();
            while (rows.next()){
                Order aOrder = new Order(rows.getString("order_number"));
                aOrder.load(db);
                orders.add(aOrder);
            }

            rows.close();
            orderQuery.close();

        } catch (SQLException throwAbles) {
            throwAbles.printStackTrace();
        }

        return orders;      // returns the orders ArrayList
    }

} // end class Order
