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
      
    }
   
    public void generateFinancialReports() {
     
      
    }

  


    public void identifyBestSellingProducts() {
    	

}
