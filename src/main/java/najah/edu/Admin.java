package najah.edu;
import static najah.edu.Registration.logger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
public class Admin {
		    private static final String INVALID_CHOICE_MESSAGE = "\u001B[31mInvalid choice! Please enter a valid choice.";
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
    private boolean orderCustomerFlag;
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
      
        choice = scanner.nextInt();

        switch (choice) {
       
        case 5->
        
        {generateFinancialReports();}
        
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
            boolean isHeader = true; // Skip header line

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue; // Skip header line
                }

                String[] parts = line.split(",");
                if (parts.length == 7) { // Ensure there are 7 columns
                    try {
                        // Read quantity and price
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

        // Calculate expenses based on total items
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
        return totalItems * 1.00; // Example expense rate per item
    }

    private String formatDouble(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(value);
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
