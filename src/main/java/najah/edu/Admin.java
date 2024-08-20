package najah.edu;
import static najah.edu.Registration.logger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
public class Admin {
		    private static final String INVALID_CHOICE_MESSAGE = "\u001B[31mInvalid choice! Please enter a valid choice.";

	    private static final String A_FILE_PATH = "src/main/resources/myData/Admin.txt";
	 private static final String RECIPES_FILE_PATH = "src/main/resources/myData/recipes.txt";
	    private static final String POSTS_FILE_PATH = "src/main/resources/myData/posts.txt";
	    private static final String BENEFICIARY_FILE_PATH = "src/main/resources/myData/BeneficiaryData.txt";
	    private static final String CONTENT_FILE_PATH = "src/main/resources/myData/content.txt";
	    private static final String REPORT_FILE_PATH = "src/main/resources/myData/financial_report.html";
	    private static final String ENTER_YOUR_CHOICE = "Enter your choice: ";
	    public static final String BOLD = "\u001B[1m";
	    public static final String RESET_COLOR = "\u001B[0m";
    Order order = new Order();
    Product product = new Product();
    
    BeneficiaryUser beneficiaryUser = new BeneficiaryUser();
    Owner owner = new Owner();
    private String first;
    private String sec;
    private String third;
    private String adminName;
    private boolean PostsFlag;
    private boolean adminLogin;
    private boolean productFlag;
    private boolean feedbackFlagFlag;
    private boolean userAccountsFlag;
    private boolean recipesFlag;
    private int adminRoleChoice;
    private boolean generateReportsFlag ; 
    private boolean viewUserStatsFlag ;

    public Admin() {
        adminLogin = true;
        productFlag = false;
        feedbackFlagFlag = false;
        userAccountsFlag = false;
        PostsFlag = false;
        recipesFlag = false;
        generateReportsFlag = false;
        viewUserStatsFlag = false;
    }
    
   
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public boolean isAdminLogin() {
        return adminLogin;
    }
    

    public void setAdminLogin(boolean adminLogin) {
        this.adminLogin = adminLogin;
    }
   
   
    public void setfeedback(boolean feedbackFlagFlag) {
        this.feedbackFlagFlag = feedbackFlagFlag;
    }
    
    public boolean isProductsFlag() {
        return productFlag;
    }

    public void setProductsFlag(boolean productFlag) {
        this.productFlag = productFlag;
    }
    
    public void setRecipes(boolean recipesFlag) {
        this.recipesFlag = recipesFlag;
    }
   
    public void setPosts(boolean PostsFlag) {
        this.PostsFlag = PostsFlag;
    }
    public void setViewUserStatsFlag(boolean flag) {
        this.viewUserStatsFlag = flag;
    }

    public boolean isViewUserStatsFlag() {
        return viewUserStatsFlag;
    }
    public void setGenerateReportsFlag(boolean flag) {
        this.generateReportsFlag = flag;
    }

    public boolean isGenerateReportsFlag() {
        return generateReportsFlag;
    }
    
    public boolean isUserAccountsFlag() {
        return userAccountsFlag;
    }
    public String getFinancialReportContent() {
        StringBuilder reportContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(REPORT_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                reportContent.append(line).append("\n");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading financial report", e);
        }

        return reportContent.toString();
    }

    public void setUserAccountsFlag(boolean userAccountsFlag) {
        this.userAccountsFlag = userAccountsFlag;
    }

   
  
    public int getAdminRoleChoice() {
        return adminRoleChoice;
    }

    public void setAdminRoleChoice(int adminRoleChoice) {
        this.adminRoleChoice = adminRoleChoice;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void Admin_menu(String AdminName) {
        setProductsFlag(false);
        setUserAccountsFlag(false);
        setRecipes(false);
        setAdminName(AdminName);
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO, "\n\u001B[37m" + "----------  Welcome " + AdminName + " -------" + "\n" +
                "|    1. Manage user accounts                  |\n" +
                "|    2. Manage feedback                       |\n" +
                "|    3. Manage recipes                        |\n" +
                "|    4. Manage posts                          |\n" +
                "|    5. Generate financial reports            |\n" +
                "|    6. Gather and display user statistics    |\n" +
                "|    7. Identify best-selling products        |\n" +
                "|    8. Exit                                 |\n" +
                "----------------------------------------------\n");
        logger.log(Level.INFO, ENTER_YOUR_CHOICE + RESET_COLOR);
        choice = scanner.nextInt();

        switch (choice) {
        case 1 ->{
        	 userAccountsFlag =true;
        	 menuManageAccountUser();
        	
        }
        case 2 -> {
            feedbackFlagFlag = true;
            manageFeedback();
            
        }
       
        
        case 5->
        
        {generateFinancialReports();}
        
        case 6 -> gatherAndDisplayUserStatistics();
        case 7 -> identifyBestSellingProducts();

        case 8 -> logger.log(Level.INFO, "Exiting...");
        default -> {
 logger.log(Level.WARNING, INVALID_CHOICE_MESSAGE);
		Admin_menu(AdminName);
        }
    }
}
    public boolean isrecipes() {
        return recipesFlag;
    }
  
    public void menuManageAccountUser() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO, "\n\u001B[34m" + "----- Manage User Accounts -----" + "\n" +
                "|     1. Admin                |\n" +
                "|     2. BeneficiaryUser      |\n" +
                "|     3. Back                 |\n" +
                "-------------------------------\n");
        logger.log(Level.INFO,ENTER_YOUR_CHOICE +  RESET_COLOR);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> editAdminProfile();
            case 2 -> BenficaryuserAccountMenu();
           
            case 3 -> Admin_menu(getAdminName());
            default -> {
             logger.log(Level.WARNING, INVALID_CHOICE_MESSAGE);
                menuManageAccountUser();
            }
        }
    }
public void editAdminProfile(){
    int choice;
    Scanner scanner = new Scanner(System.in);
    Scanner scanner1 = new Scanner(System.in);

    logger.log(Level.INFO,"\n\u001B[36m" + "----- Admin Profile -----"+"\n"+
            "|   1. edit userName   |\n"+
            "|   2. edit Password   |\n"+
            "|   3. edit Gmail      |\n"+
            "|   4. back            |\n"+
            "-----------------------\n");
    logger.log(Level.INFO,ENTER_YOUR_CHOICE+ RESET_COLOR);
    choice = scanner.nextInt();
    String choice2 ;
    String oldPass ;
    String newPass ;
    String newPassCon ;

    if (choice == 1) {
        logger.log(Level.INFO,"Enter The new user Name:"+ RESET_COLOR);
        choice2 = scanner1.nextLine();
        editeUserName(choice2);
        logger.log(Level.INFO,"The user name has been changed successfully"+ RESET_COLOR);
        editAdminProfile();
    }
    else if (choice ==2) {
        logger.log(Level.INFO,"Enter The old password:"+ RESET_COLOR);
        oldPass = scanner1.nextLine();
        logger.log(Level.INFO,"Enter The new password:"+ RESET_COLOR);
        newPass = scanner1.nextLine();
        logger.log(Level.INFO,"Confirm The  password:"+ RESET_COLOR);
        newPassCon = scanner1.nextLine();
        editePassword(oldPass,newPass,newPassCon);
        editAdminProfile();

    }
    else if (choice ==3) {
        logger.log(Level.INFO,"Enter The new Gmail:"+RESET_COLOR);
        choice2 = scanner1.nextLine();
        editeGmail(choice2);
        logger.log(Level.INFO,"The Gmail has been changed successfully"+RESET_COLOR);
        editAdminProfile();

    }
    else if (choice==4){
    	  Admin_menu(getAdminName());
    }
    else {
   logger.log(Level.WARNING, INVALID_CHOICE_MESSAGE);
    }
}

    private void editeGmail(String choice2) {
        fileFunction();
        deleteFileFunction();
        writeToFile(getFirst()+","+choice2+","+getThird());

    }
   private void editPassword(String oldPass, String newPass, String newPassCon) {
    fileFunction();
    if (truepass(oldPass, getThird())) {
        if (truepass(newPass, newPassCon)) {
            deleteFileFunction();
            writeToFile(getFirst() + "," + getSec() + "," + newPass);
            logger.log(Level.INFO, "\u001B[35m" + "The Password has been changed successfully" + RESET_COLOR);
        }
    } else {
        logger.log(Level.WARNING, BOLD + "\u001B[31mThe password is incorrect" + RESET_COLOR);
    }
}

    private void editeUserName(String choice2) {
        fileFunction();
        deleteFileFunction();
        writeToFile(choice2+","+getSec()+","+getThird());
    }
    public void fileFunction(){

        try (RandomAccessFile raf = new RandomAccessFile(A_FILE_PATH, "rw")) {
            String s;
            while ((s = raf.readLine()) != null) {
                String[] loginCustomer = s.split(",");
                first=loginCustomer[0];
                sec=loginCustomer[1];
                third=loginCustomer[2];
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
        public void deleteFileFunction(){

            try (RandomAccessFile raf = new RandomAccessFile(A_FILE_PATH, "rw")) {
                long start = 0;
                long currentPos = raf.getFilePointer();
                int currentLine = -1;

                while (currentLine < 0) {
                    start = currentPos;
                    raf.readLine();
                    currentPos = raf.getFilePointer();
                    currentLine++;
                }

                long end = raf.length();
                byte[] remainingBytes = new byte[(int) (end - currentPos)];
                raf.read(remainingBytes);

                raf.seek(start);
                raf.write(remainingBytes);
                raf.setLength(start + remainingBytes.length);
                raf.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
 public void writeToFile(String dataToWrite) {
            try (RandomAccessFile file = new RandomAccessFile(A_FILE_PATH, "rw")) {
                file.seek(file.length()); 
                file.writeBytes(dataToWrite); 
                } catch (IOException e) {
                e.printStackTrace(); 
            }
        }
        public boolean truepass (String pass, String ConfirmPass){
            if(pass.equals(ConfirmPass)){
                return true;
            }
            return false;
        }
    
  
    public void BenficaryuserAccountMenu() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            logger.log(Level.INFO, "\n\u001B[34m" + "----- Manage Beneficiary User Accounts -----" + "\n" +
                    "|     1. View User Accounts              |\n" +
                    "|     2. Update User Details             |\n" +
                    "|     3. Delete User Account             |\n" +
                    "|     4. Back to Previous Menu           |\n" +
                    "-------------------------------------------\n");
            logger.log(Level.INFO, ENTER_YOUR_CHOICE + RESET_COLOR);
             break;
        }
    }
   
    
    

  
    public void whatAdminEnter(String AdminChoice){
        if (AdminChoice.equals("1")){
        	setUserAccountsFlag(true);
        } else if (AdminChoice.equals("2")) {
        	setfeedback(true);
        } else if (AdminChoice.equals("3")) {
        	setRecipes(true);
        }
        else if (AdminChoice.equals("4")) {
        	setPosts(true);
        }
        
        else {
        	setUserAccountsFlag(false);
        	setRecipes(false);
        	setfeedback(false);
        	setPosts(false);
        	
        }
    }
   
    public void generateFinancialReports() {
        logger.log(Level.INFO, "Generating financial reports...");

        double totalRevenue = 0.0;
        double totalExpenses = 0.0;
        int totalItems = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(CONTENT_FILE_PATH))) {
            String line;
            boolean isHeader = true; 

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length == 7) { 
                    try {
                       
                        double quantity = Double.parseDouble(parts[6].trim());
                        double price = Double.parseDouble(parts[3].trim());

                        // Calculate revenue
                        totalRevenue += quantity * price;
                        totalItems += (int) quantity;
                    } catch (NumberFormatException e) {
                        logger.log(Level.WARNING, "Error parsing line: " + line, e);
                    }
                } else {
                    logger.log(Level.WARNING, "Skipping invalid product line: " + line);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading content file", e);
        }

     
        totalExpenses = calculateExpenses(totalItems); 
        double profit = totalRevenue - totalExpenses;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT_FILE_PATH))) {
            writer.write("<!DOCTYPE html>");
            writer.write("<html>");
            writer.write("<head>");
            writer.write("<title>Financial Report</title>");
            writer.write("<style>");
            writer.write("body { font-family: Arial, sans-serif; margin: 20px; }");
            writer.write("h1 { color: #2C3E50; }");
            writer.write("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
            writer.write("th, td { border: 1px solid #BDC3C7; padding: 10px; text-align: left; }");
            writer.write("th { background-color: #3498DB; color: white; }");
            writer.write("tr:nth-child(even) { background-color: #ECF0F1; }");
            writer.write("tr:hover { background-color: #BDC3C7; }");
            writer.write("</style>");
            writer.write("</head>");
            writer.write("<body>");
            writer.write("<h1>Financial Report</h1>");
            writer.write("<p>Date: " + java.time.LocalDate.now() + "</p>");
            writer.write("<table>");
            writer.write("<tr><th>Total Items</th><td>" + totalItems + "</td></tr>");
            writer.write("<tr><th>Total Revenue</th><td>$" + formatDouble(totalRevenue) + "</td></tr>");
            writer.write("<tr><th>Total Expenses</th><td>$" + formatDouble(totalExpenses) + "</td></tr>");
            writer.write("<tr><th>Profit</th><td>$" + formatDouble(profit) + "</td></tr>");
            writer.write("</table>");
            writer.write("</body>");
            writer.write("</html>");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing financial report", e);
        }
        
      
    }

    private double calculateExpenses(int totalItems) {
        return totalItems * 1.00;
    }

    private String formatDouble(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(value);
    }

public void manageFeedback() {
    Scanner scanner = new Scanner(System.in);
    String feedbackFilePath = "src/main/resources/myData/Feedback.txt";
    
    logger.log(Level.INFO, "\n\u001B[33m" + "----- Manage Feedback -----" + "\n" +
               "| 1. View feedback                      |\n" +
               "| 2. Edit feedback                      |\n" +
               "| 3. Delete feedback                    |\n" +
               "| 4. Return to admin menu               |\n" +
               "-----------------------------------------\n" + RESET_COLOR);
    logger.log(Level.INFO, ENTER_YOUR_CHOICE + RESET_COLOR);
    int choice = scanner.nextInt();
    scanner.nextLine();  

    switch (choice) {
        case 1:
            viewFeedback(feedbackFilePath);
            break;
        case 2:
            editFeedback(feedbackFilePath, scanner);
            break;
        case 3:
            deleteFeedback(feedbackFilePath, scanner);
            break;
        case 4:
        	Admin_menu(getAdminName());
        default:
            logger.log(Level.WARNING, BOLD +INVALID_CHOICE_MESSAGE + RESET_COLOR);
           
            
    }
}

private void viewFeedback(String feedbackFilePath) {
    try (BufferedReader reader = new BufferedReader(new FileReader(feedbackFilePath))) {
        String line;
        logger.log(Level.INFO, "\u001B[32m" + "----- Feedback -----" + "\u001B[0m");
        while ((line = reader.readLine()) != null) {
            logger.log(Level.INFO, line);
        }
    } catch (IOException e) {
        logger.log(Level.SEVERE, BOLD + "\u001B[31mError reading feedback: " + e.getMessage() + RESET_COLOR);
    }
    
}

private void editFeedback(String feedbackFilePath, Scanner scanner) {
    List<String> feedbackList = readFeedbackFromFile(feedbackFilePath);
    if (feedbackList == null) {
        return;     }

    logger.log(Level.INFO, "\u001B[32m" + "----- Select Feedback to Edit -----" + "\u001B[0m");
    for (int i = 0; i < feedbackList.size(); i++) {
        logger.log(Level.INFO, (i + 1) + ". " + feedbackList.get(i));
    }
    logger.log(Level.INFO, "Enter the number of the feedback to edit: " + "\u001B[0m");
    int feedbackNumber = scanner.nextInt();
    scanner.nextLine();  
    if (feedbackNumber < 1 || feedbackNumber > feedbackList.size()) {
        logger.log(Level.WARNING, BOLD + "\u001B[31mInvalid number! Please enter a valid number." + RESET_COLOR);
        return;
    }

    logger.log(Level.INFO, "Enter the new feedback: " + RESET_COLOR);
    String newFeedback = scanner.nextLine();
    feedbackList.set(feedbackNumber - 1, newFeedback);

    writeFeedbackToFile(feedbackFilePath, feedbackList);
    logger.log(Level.INFO, "\u001B[32mFeedback updated successfully!\u001B[0m");
   

}

private void deleteFeedback(String feedbackFilePath, Scanner scanner) {
    List<String> feedbackList = readFeedbackFromFile(feedbackFilePath);
    if (feedbackList == null) {
        return;     }

    logger.log(Level.INFO, "\u001B[32m" + "----- Select Feedback to Delete -----" + RESET_COLOR);
    for (int i = 0; i < feedbackList.size(); i++) {
        logger.log(Level.INFO, (i + 1) + ". " + feedbackList.get(i));
    }
    logger.log(Level.INFO, "Enter the number of the feedback to delete: " + RESET_COLOR);
    int feedbackNumber = scanner.nextInt();
    scanner.nextLine();  
    if (feedbackNumber < 1 || feedbackNumber > feedbackList.size()) {
        logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31mInvalid number! Please enter a valid number." + "\u001B[0m");
        return;
    }

    feedbackList.remove(feedbackNumber - 1);

    writeFeedbackToFile(feedbackFilePath, feedbackList);
    logger.log(Level.INFO, "\u001B[32mFeedback deleted successfully!\u001B[0m");
    manageFeedback();

}

private List<String> readFeedbackFromFile(String feedbackFilePath) {
    List<String> feedbackList = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(feedbackFilePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            feedbackList.add(line);
        }
    } catch (IOException e) {
        logger.log(Level.SEVERE,  BOLD + "\u001B[31mError reading feedback: " + e.getMessage() + RESET_COLOR);
        return null;
    }
    return feedbackList;
}

private void writeFeedbackToFile(String feedbackFilePath, List<String> feedbackList) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(feedbackFilePath))) {
        for (String feedback : feedbackList) {
            writer.write(feedback);
            writer.newLine();
        }
    } catch (IOException e) {
        logger.log(Level.SEVERE,  BOLD+ "\u001B[31mError writing feedback: " + e.getMessage() + RESET_COLOR);
    }
}

public void gatherAndDisplayUserStatistics() {
    logger.log(Level.INFO, "Gathering and displaying user statistics by city...");

    Map<String, Integer> cityUserCount = new HashMap<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(BENEFICIARY_FILE_PATH))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 3) {
                String city = parts[3].trim(); 
                cityUserCount.put(city, cityUserCount.getOrDefault(city, 0) + 1);
            }
        }
    } catch (IOException e) {
        logger.log(Level.SEVERE, "Error reading beneficiary users file", e);
    }

    System.out.println("User Statistics by City:");
    System.out.println("========================");
    for (Map.Entry<String, Integer> entry : cityUserCount.entrySet()) {
        System.out.printf("City: %s, Number of Users: %d%n", entry.getKey(), entry.getValue());
    }
    Admin_menu(getAdminName());
}


    public void identifyBestSellingProducts() {
    	String salesFilePath = "src/main/resources/myData/sales.txt";

        Map<String, Integer> productSalesCount = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(salesFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) { 	                    String[] parts = line.split(", ");
                    if (parts.length >= 4) { 	                        String productName = parts[1];
                        int quantity = Integer.parseInt(parts[2]);

                        productSalesCount.merge(productName, quantity, Integer::sum);
                    } else {
                        logger.log(Level.WARNING, "Skipping invalid sales line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading sales file: " + e.getMessage());
            return;
        }

        String bestSellingProduct = null;
        int maxSales = 0;

        for (Map.Entry<String, Integer> entry : productSalesCount.entrySet()) {
            if (entry.getValue() > maxSales) {
                maxSales = entry.getValue();
                bestSellingProduct = entry.getKey();
            }
        }

        if (bestSellingProduct != null) {
            logger.log(Level.INFO, "Best Selling Product: " + bestSellingProduct + " with " + maxSales + " units sold.");
        } else {
            logger.log(Level.INFO, "No sales data available.");
        }
        Admin_menu(getAdminName());
    }

}
