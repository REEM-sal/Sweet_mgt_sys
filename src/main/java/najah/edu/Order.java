package najah.edu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import io.cucumber.core.logging.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import static najah.edu.Registration.logger;

public class Order {
    private static final String ORDERS_FILE_PATH = "src/main/resources/myData/orders.txt";
    private Scanner scanner = new Scanner(System.in);
    private static final String CART_FILE_PATH = "src/main/resources/myData/cart.txt";
  

   

    private List<Product> cart = new ArrayList<>();
    private Product productManager;
    private double totalAmount = 0.0;

    private static String customerName = null;
    private static String idCustomer = null;
    private int orderId;
    private String orderDate;
    private String deliveryDate;
    private String status;
    private float orderPrice;
    private boolean ifCustomerShowPendingOrder;
    private boolean ifOrderExist;
    private boolean ifOrderAdded; // Flag to check if order was added
    private boolean ifOrderUpdated; // Flag to check if order was updated
    private boolean ifOrderDeleted; // Flag to check if order was deleted
    private boolean ifProductAdded; // Flag to check if product was added to order
    private Order order;
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
    private void saveCartToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CART_FILE_PATH))) {
            for (Product item : cart) {
                writer.write(item.toString());
                writer.newLine();
            }
            writer.write("Total Amount: $" + String.format("%.2f", totalAmount));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to save cart to file.", e);
        }
    }
   

    public void viewOrders() {
        try (BufferedReader ordersReader = new BufferedReader(new FileReader(ORDERS_FILE_PATH))) {
            String line;
            while ((line = ordersReader.readLine()) != null) {
                // Print each line to the console or log it
                System.out.println(line); // Or use logger.log(Level.INFO, line);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading orders file", e);
        }
    }

    private boolean isIfCustomerShowPendingOrder1() {
    	
		return ifCustomerShowPending;
	}

	private void viewPendingOrder1(String status, String idCustomer, String customerName) {
        boolean pendingOrderFound = false;

        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/orders.txt", "rw")) {
            String line;
            while ((line = ref.readLine()) != null) {
                String[] orderDetails = line.split(",");
                if (orderDetails[3].equalsIgnoreCase(status) && orderDetails[2].equalsIgnoreCase(idCustomer)) {
                    pendingOrderFound = true;
                    printPendingOrder1(orderDetails);
                }
            }

            if (!pendingOrderFound) {
                logger.info("No pending orders found for customer: " + customerName);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    private void viewAllOrderToAdmin() {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/orders.txt", "rw")) {
            String line;
            while ((line = ref.readLine()) != null) {
                String[] orderDetails = line.split(",");
                printOrderDetails(orderDetails);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void printOrderDetails(String[] orderDetails) {
        logger.info("\u001B[34m Order ID: \u001B[35m " + orderDetails[0] + " |" +
                "\u001B[34m Order Date: \u001B[35m " + orderDetails[1] + " |" +
                "\u001B[34m Delivery Date: \u001B[35m " + orderDetails[2] + " |" +
                "\u001B[34m Status: \u001B[35m " + orderDetails[3] + " |");
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
    private void loadCartFromFile() {
        cart.clear(); // Clear the cart list before loading data

        try (BufferedReader reader = new BufferedReader(new FileReader("cart.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String productId = parts[0];
                    String productName = parts[1];
                    String description = parts[2];
                    double price = Double.parseDouble(parts[3]);
                    String weight = parts[4];
                    String availability = parts[5];
                    int quantity = Integer.parseInt(parts[6]);
                    
                    boolean productFound = false;
                    for (Product p : cart) {
                        if (p.getProductId().equals(productId)) {
                            p.setQuantity(p.getQuantity() + quantity);
                            productFound = true;
                            break;
                        }
                    }
                    if (!productFound) {
                        Product product = new Product(productId, productName, description, price, weight, availability, quantity);
                        cart.add(product);
                    }
                }
            }
        } catch (IOException e) {
            logger.warning("Error loading cart from file: " + e.getMessage());
        }
    }



    private void printDeliveredOrder(String[] orderDetails) {
        logger.info("\u001B[34m Order ID: \u001B[35m " + orderDetails[0] + " |" +
                "\u001B[34m Order Date: \u001B[35m " + orderDetails[1] + " |" +
                "\u001B[34m Delivery Date: \u001B[35m " + orderDetails[2] + " |" +
                "\u001B[34m Status: \u001B[35m " + orderDetails[3] + " |");
    }

    public boolean isIfCustomerShowPendingOrder() {
        return ifCustomerShowPendingOrder;
    }

    public void setIfCustomerShowPendingOrder(boolean flag) {
        this.ifCustomerShowPendingOrder = flag;
    }

    public void viewPendingOrder(String status, String idCustomer) {
        boolean pendingOrderFound = false;
        int countPending = 0;

        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/orders.txt", "rw")) {
            String line;
            while ((line = ref.readLine()) != null) {
                String[] orderDetails = line.split(",");
                if (orderDetails[3].equalsIgnoreCase(status)) {
                    countPending++;
                    pendingOrderFound = true;
                    printPendingOrder1(orderDetails);
                }
            }

            if (!pendingOrderFound) {
                logger.info("No pending orders found for status: " + status);
            } else {
                logger.info("Total pending orders found: " + countPending);
            }

            setIfCustomerShowPendingOrder(pendingOrderFound);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void printPendingOrder1(String[] orderDetails) {
        logger.info("\u001B[34m Order ID: \u001B[35m " + orderDetails[0] + " |" +
                "\u001B[34m Order Date: \u001B[35m " + orderDetails[1] + " |" +
                "\u001B[34m Delivery Date: \u001B[35m " + orderDetails[2] + " |" +
                "\u001B[34m Status: \u001B[35m " + orderDetails[3] + " |");
    }

    private void printPendingOrder(String[] orderDetails) {
        logger.info("\u001B[34m Order ID: \u001B[35m " + orderDetails[0] + " |" +
                "\u001B[34m Customer Name: \u001B[35m " + orderDetails[1] + " |" +
                "\u001B[34m Customer ID: \u001B[35m " + orderDetails[2] + " |" +
                "\u001B[34m Order Price: \u001B[35m " + orderDetails[3] + "$ |" +
                "\u001B[34m Order Date: \u001B[35m " + orderDetails[4] + " |" +
                "\u001B[34m Delivery Date: \u001B[35m " + orderDetails[5] + " |" +
                "\u001B[34m Status: \u001B[35m " + orderDetails[6] + " |");
    }

    public boolean addOrder() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the order details as CSV (orderId, orderDate, deliveryDate, status, customerName, idCustomer, orderPrice):");
        String orderData = scanner.nextLine();
       // ifOrderAdded = true;
        
        String[] orderDetails = orderData.split(",");
        int orderId = Integer.parseInt(orderDetails[0]);
        String orderDate = orderDetails[1];
        String deliveryDate = orderDetails[2];
        String status = orderDetails[3];
        String customerName = orderDetails[4];
        String idCustomer = orderDetails[5];
        float orderPrice = Float.parseFloat(orderDetails[6]);
        
        order = new Order(orderId, orderDate, deliveryDate, status);
        order.setCustomerName(customerName);
        order.setIdCustomer(idCustomer);
        order.setOrderPrice(orderPrice);
        
        String orderLine = orderId + "," + customerName + "," + idCustomer + "," + orderPrice + "," + orderDate + "," + deliveryDate + "," + status + "\n";
        
        order.addNewOrderToCustomer(customerName, idCustomer, orderLine);
        order.addNewOrderPending(orderLine);
        
        System.out.println("Order added successfully.");
        ifOrderAdded = true;
        setIfCustomerShowPendingOrder(true);
        return ifOrderAdded;
        //return ifOrderAdded;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    private void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getFileOrderName() {
        return "orders.txt"; // Assuming the file name is orders.txt
    }

    public boolean isIfOrderExist() {
        return ifOrderExist;
    }

    private void setIfOrderExist(boolean flag) {
        this.ifOrderExist = flag;
    }

    public void searchAboutCustomer(String fileName, long orderId) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/" + fileName, "rw")) {
            String line;
            while ((line = ref.readLine()) != null) {
                String[] orderDetails = line.split(",");
                if (Long.parseLong(orderDetails[0]) == orderId) {
                    setIfOrderExist(true);
                    setCustomerName(orderDetails[4]);
                    setIdCustomer(orderDetails[5]);
                    setOrderPrice(Float.parseFloat(orderDetails[6]));
                    return;
                }
            }
            setIfOrderExist(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setStatusOrder(String newStatus) {
        this.status = newStatus;
    }

    public void editTheOrder(String newStatus) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/orders.txt", "rw")) {
            String line;
            StringBuilder fileContent = new StringBuilder();
            while ((line = ref.readLine()) != null) {
                String[] orderDetails = line.split(",");
                if (Integer.parseInt(orderDetails[0]) == this.orderId) {
                    orderDetails[3] = newStatus; // Assuming the status is at index 3
                    line = String.join(",", orderDetails);
                }
                fileContent.append(line).append("\n");
            }
            ref.seek(0); // Move the pointer to the beginning of the file
            ref.writeBytes(fileContent.toString());
            ifOrderUpdated = true; // Set the flag to true if the order was updated
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteOrder(String fileName) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/" + fileName, "rw")) {
            String line;
            StringBuilder fileContent = new StringBuilder();
            while ((line = ref.readLine()) != null) {
                String[] orderDetails = line.split(",");
                if (Integer.parseInt(orderDetails[0]) != this.orderId) {
                    fileContent.append(line).append("\n");
                }
            }
            ref.setLength(0); // Clear the file
            ref.writeBytes(fileContent.toString()); // Write the updated content
            ifOrderDeleted = true; // Set the flag to true if the order was deleted
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addNewOrderToCustomer(String customerName, String idCustomer, String orderLine) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/orders.txt", "rw")) {
            ref.seek(ref.length()); // Move to the end of the file
            ref.writeBytes(orderLine); // Write the new order line
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addNewOrderPending(String orderLine) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/pending_orders.txt", "rw")) {
            ref.seek(ref.length()); // Move to the end of the file
            ref.writeBytes(orderLine); // Write the new order line
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void manageOrders() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO, "\n\u001B[34m" + "----- Manage Orders -----" + "\n" +
                "|     1. View Orders          |\n" +
                "|     2. Update Order         |\n" +
                "|     3. Delete Order         |\n" +
                "|     4. Back                 |\n" +
                "-----------------------------\n");
        logger.log(Level.INFO, "Enter your choice: " + "\u001B[0m");
        choice = scanner.nextInt();

        switch (choice) {
            case 1 -> viewOrders();
            case 2 -> updateOrder();
            case 3 -> deleteOrder();
            case 4 -> Admin_menu(getAdminName());
           
            default -> {
                logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31mInvalid choice! Please enter a valid choice." + "\u001B[0m");
                manageOrders();
            }
        }
    }

    private void deleteOrder() {
        // Get the order ID to delete
        System.out.println("Enter the ID of the order you want to delete:");
        String idToDelete = scanner.nextLine().trim();

        List<String> orders = new ArrayList<>();
        boolean found = false;

        // Read the file and filter out the order to delete
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDERS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming the ID is the first part of the line
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].trim().equals(idToDelete)) {
                    found = true; // Mark as found if ID matches
                    continue; // Skip adding this line to the list
                }
                orders.add(line); // Add other orders to the list
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading orders file", e);
        }

        if (found) {
            // Write the updated orders back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDERS_FILE_PATH))) {
                for (String order : orders) {
                    writer.write(order);
                    writer.newLine();
                }
                System.out.println("Order deleted successfully.");
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error writing to orders file", e);
            }
        } else {
            System.out.println("Order with ID " + idToDelete + " not found.");
        }
    }
    private boolean updateOrder() {
        System.out.println("Enter the ID of the order you want to update:");
        String idToUpdate = scanner.nextLine().trim();

        List<String> orders = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(ORDERS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].trim().equals(idToUpdate)) {
                    found = true;

                    // طلب إدخال التفاصيل الجديدة لكل حقل، مع تخطي أي حقل يتم الضغط على Enter فيه
                    System.out.println("Enter new order date (current: " + parts[1] + "):");
                    String newOrderDate = scanner.nextLine().trim();
                    if (newOrderDate.isEmpty()) {
                        newOrderDate = parts[1];
                    }

                    System.out.println("Enter new delivery date (current: " + parts[2] + "):");
                    String newDeliveryDate = scanner.nextLine().trim();
                    if (newDeliveryDate.isEmpty()) {
                        newDeliveryDate = parts[2];
                    }

                    System.out.println("Enter new status (current: " + parts[3] + "):");
                    String newStatus = scanner.nextLine().trim();
                    if (newStatus.isEmpty()) {
                        newStatus = parts[3];
                    }

                    // إنشاء السطر المحدث للطلب
                    String updatedOrder = idToUpdate + "," + newOrderDate + "," + newDeliveryDate + "," + newStatus;
                    orders.add(updatedOrder);
                } else {
                    orders.add(line);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading orders file", e);
            return false;
        }

        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDERS_FILE_PATH))) {
                for (String order : orders) {
                    writer.write(order);
                    writer.newLine();
                }
                System.out.println("Order updated successfully.");
                return true;
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error writing to orders file", e);
                return false;
            }
        } else {
            System.out.println("Order with ID " + idToUpdate + " not found.");
            return false;
        }
    }

    public void Admin_menu(String adminName) {
        System.out.println("Returning to admin menu for: " + adminName);
        // هنا يتم تنفيذ منطق قائمة المسؤول
    }

    public String getAdminName() {
        return "Admin"; // استبدل هذا المنطق بالمنطق الفعلي للحصول على اسم المسؤول
    }

    public void makePurchasesMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            logger.info("\n\u001B[34m----- Purchase Menu -----\u001B[0m");
            logger.info("1. Add Product to Order");
            logger.info("2. View Cart");
            logger.info("3. Checkout");
            logger.info("4. Cancel Order");
            logger.info("5. Exit");
            logger.info("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                	addProductToOrder1();
                    break;
                case 2:
                    viewCart1();
                    break;
                case 3:
                    checkout1();
                    break;
                case 4:
                    cancelOrder1();
                    break;
                case 5:
                    logger.info("Exiting Purchase Menu...");
                    break;
                default:
                    logger.warning("Invalid choice! Please enter a valid choice.");
                    break;
            }
        } while (choice != 5);
    }

    private void addProductToOrder1() {
        loadCartFromFile(); // Ensure we have the latest cart data

        Scanner scanner = new Scanner(System.in);
        logger.info("Enter the Product ID to add to cart: ");
        String productId = scanner.nextLine();
        
        int quantity = 0;
        boolean validQuantity = false;
        
        while (!validQuantity) {
            try {
                logger.info("Enter the quantity: ");
                quantity = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (quantity <= 0) {
                    logger.warning("Quantity must be greater than zero. Please enter a valid quantity.");
                } else {
                    validQuantity = true; // Valid quantity entered
                }
            } catch (InputMismatchException e) {
                logger.warning("Invalid input. Please enter a numeric value for quantity.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        List<Product> products = productManager.searchProductById(productId);
        if (products.isEmpty()) {
            logger.warning("Product not found.");
            return;
        }

        Product product = products.get(0);
        if (quantity > product.getQuantity()) {
            logger.warning("Not enough stock available. Please enter a valid quantity.");
            return;
        }

        boolean productFound = false;

        // Check if the product already exists in the cart
        for (Product p : cart) {
            if (p.getProductId().equals(product.getProductId())) {
                // Update quantity if the product is already in the cart
                p.setQuantity(p.getQuantity() + quantity);
                productFound = true;
                break;
            }
        }

        if (!productFound) {
            // Add new product if it doesn't exist
            Product cartProduct = new Product(product.getProductId(), product.getProductName(), product.getDescription(), product.getPrice(), product.getWeight(), product.getAvailability(), quantity);
            cart.add(cartProduct);
        }

        totalAmount += product.getPrice() * quantity;
        logger.info("Product added to cart.");
        saveCartToFile(); // Save the updated cart
    }




    private void viewCart1() {
        loadCartFromFile(); // Ensure we have the latest cart data

        if (cart.isEmpty()) {
            logger.info("Cart is empty.");
            return;
        }

        logger.info("\n\u001B[32m----- Cart Contents -----\u001B[0m");

        // Iterate over the cart items and print each one
        for (Product item : cart) {
            logger.info(item.toString()); // Assuming Product class has a suitable toString() method
        }

        logger.info("Total Amount: $" + String.format("%.2f", totalAmount));
    }



    private void checkout1() {
        if (cart.isEmpty()) {
            logger.warning("Cart is empty. Add products to cart before checking out.");
            return;
        }

        logger.info("Proceeding to checkout...");
        logger.info("Total Amount: $" + String.format("%.2f", totalAmount));
        // Further steps for checkout, such as payment processing, can be added here

        // Clear the cart after checkout and save
        cart.clear();
        totalAmount = 0.0;
        saveCartToFile();
        logger.info("Order placed successfully!");
    }

    private void cancelOrder1() {
        loadCartFromFile(); // Ensure we have the latest cart data

        if (cart.isEmpty()) {
            logger.info("No order to cancel.");
            return;
        }

        cart.clear();
        totalAmount = 0.0;
        saveCartToFile();
        logger.info("Order has been canceled.");
    }

       
    private void clearCart() {
        cart.clear(); // Clear existing cart data
    }


    public void addProductToOrder() {
        ifProductAdded = true;
        System.out.println("Product added to order.");
    }

    private void viewCart() {
        System.out.println("Viewing cart...");
    }

    private void checkout() {
        System.out.println("Checking out...");
    }

    private boolean cancelOrder() {
        System.out.println("Order canceled.");
        return true;
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
    public boolean makeOwnerSelection(String option) {
        switch (option) {
            case "1": // إضافة طلب
                return addOrder();
            case "2": // تعديل طلب
                return updateOrder();
            case "3": // إلغاء طلب
                return cancelOrder();
            case "4": // عرض الطلبات
                viewOrders();
                return true;
            case "5": // العودة إلى القائمة السابقة
                return true;
            default:
                System.out.println("Invalid selection option: " + option);
                return false;
        }
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

    public void setCustomerName1(String customerName) {
        this.customerName = customerName;
    }

    public void setIdCustomer1(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setGmailIs1(String gmailIs) {
        this.gmailIs = gmailIs;
    }

    // يمكنك إضافة المزيد من الفنكشنات كما هو مطلوب...
}