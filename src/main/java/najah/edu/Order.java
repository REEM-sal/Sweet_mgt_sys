package najah.edu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;

import io.cucumber.core.logging.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;

import static najah.edu.Registration.logger;

public class Order {
    private static final String ORDERS_FILE_PATH = "src/main/resources/myData/orders.txt";
    private Scanner scanner = new Scanner(System.in);
    private static final String CART_FILE_PATH = "src/main/resources/myData/cart.txt";
    private Map<Integer, Integer> cart = new HashMap<>(); 
 private Product productManager;
   
    private int productId;

    private String orderStatus = "pending";
  
    private static final Map<Integer, String> ORDER_STATUS = new HashMap<>();
 
    private double totalAmount = 0.0;
    private static final String CONTENT_FILE_PATH = "src/main/resources/myData/content.txt";

    private static String customerName = null;
    private static String idCustomer = null;
    private int orderId;
    private String orderDate;
    private String deliveryDate;
    private String status;
    private float orderPrice;
    private boolean PendingOrderflag;
    private boolean ifOrderExist;
    private boolean ifOrderAdded; 
    private boolean ifOrderUpdated; 
    private boolean ifOrderDeleted; 
    private boolean ifProductAdded; 
    private Order order;
    private boolean viewOrdersFlag;
    private String gmailIs;
    boolean ifCustomerShowPending ;

    public Order() {
    	productManager = new Product();
    }

    public Order(int orderId, String orderDate, String deliveryDate, String status) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
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
            String line;
            while ((line = ordersReader.readLine()) != null) {
                System.out.println(line);             }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading orders file", e);
        }
    }

  
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

  
    private void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void viewDeliveredOrder(String status) {
        boolean deliveredOrderFound = false;
        int countDelivered = 0;

        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/orders.txt", "rw")) {
            String line;
            while ((line = ref.readLine()) != null) {
                String[] orderDetails = line.split(",");
                if (orderDetails[3].equalsIgnoreCase(status)) {
                    countDelivered++;
                    deliveredOrderFound = true;
                    printDeliveredOrder(orderDetails);
                }
            }

            if (!deliveredOrderFound) {
                logger.info("No delivered orders found for status: " + status);
            } else {
                logger.info("Total delivered orders found: " + countDelivered);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
  
    public void printDeliveredOrder(String[] orderDetails) {
    }

    public boolean isIfCustomerShowPendingOrder() {
        return PendingOrderflag;
    }

    public void setIfCustomerShowPendingOrder(boolean flag) {
        this.PendingOrderflag = flag;
    }

    public void viewPendingOrder(String status, String idCustomer) {
        try (BufferedReader ordersReader = new BufferedReader(new FileReader("src/main/resources/myData/orders.txt"))) {
            String line;
         
                        orderFound = true;
                    }
                } else {
                    System.out.println("Skipping line due to insufficient fields: " + line);
                }
            }
            if (!orderFound) {
                System.out.println("No pending orders found for customer ID: " + idCustomer);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading orders file", e);
        }
    }

    
    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

   
    public String getFileOrderName() {
        return "orders.txt"; // Assuming the file name is orders.txt
    }

    public boolean isIfOrderExist() {
        return ifOrderExist;
    }

   public void setIfOrderExist(boolean flag) {
        this.ifOrderExist = flag;
    }

    public void setStatusOrder(String newStatus) {
        this.status = newStatus;
    }


    public String getAdminName() {
        return "Admin"; 
    }

   


   public static void addToCart(int productId, String name, int quantity, double price) {
    double newTotalPrice = price * quantity;
    double totalPrice = 0.0;

    try (BufferedReader cartReader = new BufferedReader(new FileReader(CART_FILE_PATH))) {
        String line;
        while ((line = cartReader.readLine()) != null) {
            String[] cartDetails = line.split(",");
            if (cartDetails.length == 4) {
                totalPrice += Double.parseDouble(cartDetails[3]);
            }
        }
    } catch (FileNotFoundException e) {
        
    } catch (IOException e) {
        logger.log(Level.SEVERE, "Error reading cart file", e);
    }

    totalPrice += newTotalPrice;

    try (BufferedWriter cartWriter = new BufferedWriter(new FileWriter(CART_FILE_PATH, true))) {
        cartWriter.write(productId + "," + name + "," + quantity + "," + newTotalPrice + "\n");
        cartWriter.write("Total Price," + totalPrice + "\n");
    } catch (IOException e) {
        logger.log(Level.SEVERE, "Error writing to cart file", e);
    }
}
  public static void viewCart() {
    try (BufferedReader cartReader = new BufferedReader(new FileReader(CART_FILE_PATH))) {
        String line;
        double totalPrice = 0.0;
       
        System.out.println("Total Price: " + totalPrice);
    } catch (FileNotFoundException e) {
        System.out.println("Cart is empty.");
    } catch (IOException e) {
        logger.log(Level.SEVERE, "Error reading cart file", e);
    }
}



    public void addProductToOrder() {
        ifProductAdded = true;
        System.out.println("Product added to order.");
    }

    public void setGmailIs(String email) {
        this.gmailIs = email;
    }

    public boolean checkProductExistence(String productId) {
        return productId != null && !productId.isEmpty();
    }

    public boolean validateProductBeforeAdding() {
        return checkProductExistence("someProductId");
    }

    public String getStatusOrder() {
        return this.status;
    }

    public String getIdCustomer() {
        return this.idCustomer;
    }

    public boolean editProductQuantity() {
        return true;
    }
   
    public void setProductId(int productId) {
        this.productId = productId;
    }


public static boolean createOrder(String customerId, String customerName, int productId, int quantity) {
    try (BufferedReader contentReader = new BufferedReader(new FileReader(CONTENT_FILE_PATH))) {
        String line;
        boolean productFound = false;
        
       
            }
        }
        
        if (!productFound) {
            System.out.println("Product not found.");
            return false;
        }
    } catch (IOException e) {
        logger.log(Level.SEVERE, "Error reading content file", e);
        return false;
    }
    return false;
}
private static void saveOrder(String customerId, String customerName, int productId, int quantity, String status) {
    try (BufferedWriter orderWriter = new BufferedWriter(new FileWriter(ORDERS_FILE_PATH, true))) {
        String orderId = UUID.randomUUID().toString().replace("-", "");
        orderWriter.write(orderId + "," + customerId + "," + customerName + "," + productId + "," + quantity + "," + status + "\n");
    } catch (IOException e) {
        logger.log(Level.SEVERE, "Error writing to orders file", e);
    }
}

   public boolean isOrderCreated() {
    try (BufferedReader ordersReader = new BufferedReader(new FileReader(ORDERS_FILE_PATH))) {
        String line;
        while ((line = ordersReader.readLine()) != null) {
            if (line.contains(idCustomer) && line.contains("pending")) {
                return true;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return false;
}

   private void updateCartFile() throws IOException {
    // Update the cart file with the current cart data
    try (BufferedWriter cartWriter = new BufferedWriter(new FileWriter(CART_FILE_PATH))) {
        for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
            cartWriter.write(entry.getKey() + "," + entry.getValue() + "\n");
        }
    } 
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
        e.printStackTrace();
    }
    return false;
}

    public void setOrderId(String orderId) {
        this.idCustomer = orderId;
    }

    public boolean canManageOrders() {
        return true; // Placeholder, replace with actual logic
    }

    public boolean canSendEmailNotifications() {
        return true; 
    }

    public boolean canViewPendingOrders() {
        return true; 
    }


    public boolean isValidOption(int option1, int option2, int option3, int option4) {
        List<Integer> validOptions = Arrays.asList(option1, option2, option3, option4);
        return validOptions.contains(Integer.parseInt(orderStatus));
    }

    public boolean editSpecificProductQuantity(Integer productId) {
        return productId != null && productId > 0;
    }

    public boolean performOwnerOperations() {
        return true;
    }

    public boolean sendEmailNotifications() {
        return gmailIs != null && !gmailIs.isEmpty();
    }

    public double calculateTotalCost() {
        return orderPrice;
    }

    public boolean validateOption(Integer option1, Integer option2, Integer option3, Integer option4) {
        return option1 != null && option2 != null && option3 != null && option4 != null;
    }

}
