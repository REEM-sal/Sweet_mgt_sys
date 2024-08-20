package najah.edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.Map;
import java.util.HashMap;
import static najah.edu.Registration.logger;


public class Owner {

	 private List<String> emailMessages = new ArrayList<>();
	 private static final String CONTENT_FILE_PATH = "src/main/resources/myData/content.txt";
	    private static final String SALES_FILE_PATH = "src/main/resources/myData/sales.txt";
	    public static final String BOLD = "\u001B[1m";
	    public static final String RESET_COLOR = "\u001B[0m";
	    private String idCustomer;
	    private String customerName;
	    private String phoneCustomer;
	    private String userName;
	    private Gmail gmail;
	    private String password;
	    private String address;
	    private int id;
	    private int phone;
	    private boolean manageProductsFlag;
	    private boolean  manageOrdersFlag;
	    private boolean monitorSalesFlag;
	    private boolean bestSellingProductsFlag;

	    private boolean dynamicDiscountFlag;
	    private boolean notificationsFlag;
	    private boolean trackOrdersFlag;
	    private Product productManager = new Product();

	    public Owner() {
	    	 emailMessages = new ArrayList<>();
	        manageProductsFlag = false;
	        manageOrdersFlag=false;
	        monitorSalesFlag = false;
	        bestSellingProductsFlag = false;
	        dynamicDiscountFlag = false;
	        notificationsFlag = false;
	        trackOrdersFlag = false;
	        gmail = new Gmail();
	      
	    }    

	    public String getIdCustomer() {
	        return idCustomer;
	    }

	    public void setIdCustomer(String idCustomer) {
	        this.idCustomer = idCustomer;
	    }
	    
	    public String getCustomerName() {
	        return customerName;
	    }
	    public boolean isProductsFlag() {
	        return manageProductsFlag;
	    }
	    public void setProductsFlag(boolean productsFlag) {
	        this.manageProductsFlag = productsFlag;
	    }
	    public void setCustomerName(String customerName) {
	        this.customerName = customerName;
	    }
	    public String getPhoneCustomer() {
	        return phoneCustomer;
	    }
	    private boolean ownerLogin;
	    
	    public boolean isOwnerLogin() {
	        return ownerLogin;
	    }
	    public void setOwnerLogin(boolean ownerLogin) {
	        this.ownerLogin = ownerLogin;
	    }
	    public void setPhoneCustomer(String phoneCustomer) {
	        this.phoneCustomer = phoneCustomer;
	    }
	    public void login(String username, String password) {
	        if (username.equals("Yara@gmail.com") && password.equals("121")) {
	            setOwnerLogin(true); 
	            }
	    }
	  
        public void owner_Menu(String ownerName) {
	                Scanner scanner = new Scanner(System.in);
	                int choice;
	                do {
	                    logger.log(Level.INFO, "\n\u001B[32m" + " -------  Welcome " + ownerName + " ---------" + "\n" +
	                            "|                                   |\n" +
	                            "|      1. Manage Products           |\n" +
	                            "|      2. Monitor Sales             |\n" +
	                            "|      3. Identify Best-selling Products |\n" +
	                            "|      4. Manage Orders             |\n" +  	                            "|      5. Implement Dynamic Discount|\n" +
	                            "|      6. Receive Notifications     |\n" +
	                            "|      7. Exit                      |\n" +  	                            "|                                   |\n" +
	                            "------------------------------------\n");
	                    logger.log(Level.INFO, "Enter your choice: " + RESET_COLOR );
	                    choice = scanner.nextInt();
	                    
	                } while (choice != 7); 
	            }
	   
	   
	   


	    public void setGmail(Gmail gmail) {
	        this.gmail = gmail;
	    }

	    

	    public String getUserName() {
	        return userName;
	    }

	    public void setUserName(String userName) {
	        this.userName = userName;
	    }

	    public Gmail getGmail() {
	        return gmail;
	    }

	   

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public int getPhone() {
	        return phone;
	    }

	    public void setPhone(int phone) {
	        this.phone = phone;
	    }

	    public boolean isManageProductsFlag() {
	        return manageProductsFlag;
	    }

	 
	    public void setManageProductsFlag(boolean manageProductsFlag) {
	        this.manageProductsFlag = manageProductsFlag;
	    }

	    public boolean isMonitorSalesFlag() {
	        return monitorSalesFlag;
	    }

	    public void setMonitorSalesFlag(boolean monitorSalesFlag) {
	        this.monitorSalesFlag = monitorSalesFlag;
	    }
	    public void whatAdminEnter(String AdminChoice){
	        if (AdminChoice.equals("1")){
	        	setManageProductsFlag(true);
	        } else if (AdminChoice.equals("2")) {
	        	setMonitorSalesFlag(true);
	        } else if (AdminChoice.equals("3")) {
	        	setBestSellingProductsFlag(true);
	        }
	        else if (AdminChoice.equals("4")) {
	        	setTrackOrdersFlag(true);
	        }
	        
	        else {
	        	setManageProductsFlag(false);
	        	setMonitorSalesFlag(false);
	        	setBestSellingProductsFlag(false);
	        	setTrackOrdersFlag(false);
	        	
	        }
	    }
	    public boolean isBestSellingProductsFlag() {
	        return bestSellingProductsFlag;
	    }

	    public void setBestSellingProductsFlag(boolean bestSellingProductsFlag) {
	    	 this.bestSellingProductsFlag = bestSellingProductsFlag;
	    }
	   


	    public boolean isDynamicDiscountFlag() {
	        return dynamicDiscountFlag;
	    }

	    public void setDynamicDiscountFlag(boolean dynamicDiscountFlag) {
	        this.dynamicDiscountFlag = dynamicDiscountFlag;
	    }

	    public boolean isNotificationsFlag() {
	        return notificationsFlag; 
	    }

	    public void setNotificationsFlag(boolean notificationsFlag) {
	        this.notificationsFlag = notificationsFlag;
	    }

	    public boolean isTrackOrdersFlag() {
	        return trackOrdersFlag;
	    }

	    public void setTrackOrdersFlag(boolean trackOrdersFlag) {
	        this.trackOrdersFlag = trackOrdersFlag;
	    }




}
