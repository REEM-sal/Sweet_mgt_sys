package najah.edu;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Order {
    private static final String ORDERS_FILE_PATH = "src/main/resources/myData/orders.txt";
    private Scanner scanner = new Scanner(System.in);
    private static final String CART_FILE_PATH = "src/main/resources/myData/cart.txt";
    private Map<Integer, Integer> cart = new HashMap<>(); 
    private Product productManager;
    private int productId;
    private String orderStatus = "pending";
    private String idCustomer = null;
    private int orderId;
    private String status;
    private boolean PendingOrderflag;
    private boolean ifOrderExist;
    private boolean viewOrdersFlag;

    public Order() {
    	productManager = new Product();
    }

    public Order(int orderId, String status) {
        this.orderId = orderId;
        this.status = status;
    }
   
    public void setViewOrdersFlag(boolean flag) {
        this.viewOrdersFlag = flag;
    }

    public boolean isViewOrdersFlag() {
        return this.viewOrdersFlag;
    }

    public void viewOrders() {
        try (BufferedReader ordersReader = new BufferedReader(new FileReader(ORDERS_FILE_PATH))) {
        } catch (IOException e) {
        }
    }

    public boolean isIfCustomerShowPendingOrder() {
        return PendingOrderflag;
    }

    public void setIfCustomerShowPendingOrder(boolean flag) {
        this.PendingOrderflag = flag;
    }

    public void viewPendingOrder(String status, String idCustomer) {
        try (BufferedReader ordersReader = new BufferedReader(new FileReader("src/main/resources/myData/orders.txt"))) {
        } catch (IOException e) {
        }
    }
    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void viewCart() {    }
    public boolean canManageOrders() {
        return true;  }
    public void viewDeliveredOrder(String orderId) { }
    public boolean canViewPendingOrders() {
        return true; }

    public void printDeliveredOrder(String[] orderDetails) { }}

    public boolean isIfOrderExist() {
        return ifOrderExist;
    }

    public void setIfOrderExist(boolean flag) {
        this.ifOrderExist = flag;
    }

    public void searchAboutCustomer(String fileName, long orderId) {
        try (BufferedReader ref = new BufferedReader(new FileReader("src/main/resources/myData/" + fileName))) {
            setIfOrderExist(false);
        } catch (IOException e) {
        }
    }

    public void setStatusOrder(String newStatus) {
        this.status = newStatus;
    }

    public boolean checkProductExistence(String productId) {
        return productId != null && !productId.isEmpty();
    }

    public String getStatusOrder() {
        return this.status;
    }

    public String getIdCustomer() {
        return this.idCustomer;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public boolean isProductExisting() {
        try (BufferedReader contentReader = new BufferedReader(new FileReader("src/main/resources/myData/content.txt"))) {
            String line;
            while ((line = contentReader.readLine()) != null) {
                if (line.startsWith(String.valueOf(productId))) {
                    return true;
                }
            }
        } catch (IOException e) {
        }
        return false;
    }
}
