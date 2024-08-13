package najah.edu;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import static najah.edu.Registration.logger;

import  najah.edu.Owner;
public class BeneficiaryUser {
	 private static final String CONTENT_FILE_PATH = "src/main/resources/myData/content.txt";
	 private Product productManager = new Product();
	 private Gmail gmail;
    Order order =new Order();
   Owner owner = new Owner();
   public String getUserName() {
       return userName;
   }
   public void theBeneficiaryUserEnter(String Choice) {
	   if (Choice.equals("1")){
		   setBrowseProductsFlag(true);
       } else if (Choice.equals("2")) {
    	   setMakePurchasesFlag(true);
       } else if (Choice.equals("3")) {
    	   setViewOrdersFlag(true);
       }
       else {
    	   setViewOrdersFlag(false);
           setBrowseProductsFlag(false);
           setMakePurchasesFlag(false);
       }
   }
   public int getNumberOfLine() {
       return numberOfLine;
   }

   public void setNumberOfLine(int numberOfLine) {
       this.numberOfLine = numberOfLine;
   }

   int  numberOfLine;
   public void setUserName(String userName) {
       this.userName = userName;
   }

   public Gmail getGmail() {
       return gmail;
   }

   public void setGmail(String gmail) {
       Gmail = gmail;
   }

   public String getPassword() {
       return Password;
   }

   public void setPassword(String password) {
       Password = password;
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

   String userName;
   String Gmail;
   String Password;
   String address;
   int id;
   int phone;
   public void setCustomerLogin(boolean customerLogin) {
       this.customerLogin = customerLogin;
   }
   private boolean customerLogin ;
   private boolean browseProductsFlag;
   public boolean isSettingFlag() {
       return settingFlag;
   }
   public boolean isfeedbackflag() {
	  return feedbackFlag;
   }
   public void setfeedbackflag(boolean feedbackflag) {
	   this.feedbackFlag=feedbackflag;

   }

   public void setSettingFlag(boolean settingFlag) {
       this.settingFlag = settingFlag;
   }

   public boolean settingFlag;

 

  
   public void setBrowseProductsFlag(boolean browseProductsFlag) {
       this.browseProductsFlag = browseProductsFlag;
   }

   public boolean isMakePurchasesFlag() {
       return makePurchasesFlag;
   }

   public void setMakePurchasesFlag(boolean makePurchasesFlag) {
       this.makePurchasesFlag = makePurchasesFlag;
   }

   public boolean isViewOrdersFlag() {
       return viewOrdersFlag;
   }

   public void setViewOrdersFlag(boolean viewOrdersFlag) {
       this.viewOrdersFlag = viewOrdersFlag;
   }


   private boolean  feedbackFlag;

   private boolean makePurchasesFlag;
   private boolean viewOrdersFlag;
   public BeneficiaryUser() {
       customerLogin = true;
       browseProductsFlag=false;
       makePurchasesFlag=false;
       viewOrdersFlag=false;
     
       settingFlag=false;
   }

   public boolean isCustomerLogin() {
       return customerLogin;
   }



   public void Customer_menu (String CostName) {
       setUserName(CostName);
       setBrowseProductsFlag(false);
       setViewOrdersFlag(false);
       setMakePurchasesFlag(false);
      
       setSettingFlag(false);
       int choice;
       Scanner scanner = new Scanner(System.in);
       logger.log(Level.INFO, "\n\u001B[32m" + " -------  Welcome " +  CostName  + " ---------" + "\n" +
               "|                                  |\n" +
               "|      1. Browse products           |\n" +
               "|      2. Make purchases            |\n" +
               "|      3. View orders               |\n" +
               "|      4. Manage personal account   |\n" +
               "|      5. Provide feedback          |\n" +  // Added feedback option
               "|                                  |\n" +
               "-----------------------------------\n");
    logger.log(Level.INFO, "Enter your choice: " + "\u001B[0m");


       choice = scanner.nextInt();
       if (choice == 1) {
           browseProductsFlag = true;
           performProductSearch();
       } else if (choice == 2) {
           makePurchasesFlag = true;
           userAccountMenu();

       } else if (choice == 3) {
           viewOrdersFlag = true;
           userAccountMenu();

       }
       else if (choice == 4) {
    	   settingFlag = true;
           settingMenu();

       }
       else if (choice == 5) {
    	   feedback();
    	   feedbackFlag = true;

       }
       else {
    	    logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31mInvalid choice! Please enter one, two, or three.\u001B[0m");
    	}
   }
   private void performProductSearch() {
       Scanner scanner = new Scanner(System.in);
       System.out.println("Search products by:");
       System.out.println("1. ID");
       System.out.println("2. Name");
       System.out.println("3. Description");
       System.out.println("4. Availability");
       System.out.print("Enter your choice: ");
       int searchChoice = scanner.nextInt();
       scanner.nextLine(); // Consume newline

       switch (searchChoice) {
           case 1:
               System.out.print("Enter product ID: ");
               String id = scanner.nextLine();
               List<Product> resultsById = productManager.searchProductById(id);
               if (productManager.isSearchProductFlag()) {
                   System.out.println("Products found: " + resultsById);
               } else {
                   System.out.println("Product with ID " + id + " not found.");
               }
               break;
           case 2:
               System.out.print("Enter product name: ");
               String name = scanner.nextLine();
               List<Product> resultsByName = productManager.searchProductByName(name);
               if (productManager.isSearchProductFlag()) {
                   System.out.println("Products found: " + resultsByName);
               } else {
                   System.out.println("Product with name " + name + " not found.");
               }
               break;
           case 3:
               System.out.print("Enter product description: ");
               String description = scanner.nextLine();
               List<Product> resultsByDescription = productManager.searchProductByDescription(description);
               if (productManager.isSearchProductFlag()) {
                   System.out.println("Products found: " + resultsByDescription);
               } else {
                   System.out.println("Product with description containing " + description + " not found.");
               }
               break;
           case 4:
               System.out.print("Enter product availability: ");
               String availability = scanner.nextLine();
               List<Product> resultsByAvailability = productManager.searchProductByAvailability(availability);
               if (productManager.isSearchProductFlag()) {
                   System.out.println("Products found: " + resultsByAvailability);
               } else {
                   System.out.println("Product with availability " + availability + " not found.");
               }
               break;
           default:
               System.out.println("Invalid choice.");
               break;
       }
   }
 

   public static String getTheNameOfCat(Scanner scanner) {
       return scanner.next();
   }

   public void userAccountMenu(){
       if (browseProductsFlag){
           BrowseProductsMenu();
           back();
       }
       else if (makePurchasesFlag) {
           
           order.makePurchasesMenu();
           back();
       }
       else if (viewOrdersFlag) {
           searchTheCustomer();
           owner.manageProductsMenu();
           back();
       }
     
       else {
           logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+"\u001B[0m");
           back();
       }
   }
   public void back() {
       logger.log(Level.INFO, """

               1) back\s
               2) Exit""");
       int choice;
       Scanner scanner = new Scanner(System.in);
       choice = scanner.nextInt();
       if (choice == 1)
           Customer_menu(getUserName());
       System.exit(0);
   }

   public void BrowseProductsMenu() {
       Scanner scanner = new Scanner(System.in);
       boolean running = true;
       
       while (running) {
           logger.log(Level.INFO, """
              
              \u001B[35m---------------------------------
              |                                    |
              |      1. Show all products          |
              |      2. Search products            |
              |      3. Back                       |
              |                                    | 
              --------------------------------------
              """);
           logger.log(Level.INFO, "Enter your choice: " + "\u001B[0m");
           int choice = scanner.nextInt();
           scanner.nextLine();  // Consume newline

           switch (choice) {
               case 1:
                   showAllProducts();
                   break;
               case 2:
                   logger.log(Level.INFO, "Enter the search term: ");
                   String searchTerm = scanner.nextLine();
                   searchProducts(searchTerm);
                   break;
               case 3:
            	   Customer_menu(getUserName());
                   break;
               default:
                   logger.log(Level.INFO, "Invalid choice. Please try again.");
                   break;
           }
       }
   }

   private void showAllProducts() {
       List<String> products = readProductsFromFile();
       if (products.isEmpty()) {
           logger.log(Level.INFO, "No products available.");
       } else {
           logger.log(Level.INFO, "Products List:");
           for (String product : products) {
               logger.log(Level.INFO, product);
           }
       }
   }

   private void searchProducts(String searchTerm) {
       List<String> products = readProductsFromFile();
       boolean found = false;
       logger.log(Level.INFO, "Search Results:");
       for (String product : products) {
           if (product.toLowerCase().contains(searchTerm.toLowerCase())) {
               logger.log(Level.INFO, product);
               found = true;
           }
       }
       if (!found) {
           logger.log(Level.INFO, "No products found matching: " + searchTerm);
       }
   }

   private List<String> readProductsFromFile() {
       List<String> products = new ArrayList<>();
       try (BufferedReader reader = new BufferedReader(new FileReader(CONTENT_FILE_PATH))) {
           String line;
           while ((line = reader.readLine()) != null) {
               products.add(line);
           }
       } catch (IOException e) {
           logger.log(Level.SEVERE, "Error reading the products file.", e);
       }
       return products;
   }

  

   


   public void menuCustomerAdmin() {
       int choice;
       Scanner scanner = new Scanner(System.in);
       logger.log(Level.INFO,"\n\u001B[33m"+"----- Manage BeneficiaryUser Accounts -----"+"\n"+
               "|                                    |\n" +
               "|     1. View user accounts.     |\n"+
               "|     2. Add user Account.       |\n"+
               "|     3. Delete user Account.    |\n"+
               "|                                    |\n"+
               "-------------------------------------\n");
       logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
       choice = scanner.nextInt();
       if (choice == 1) {
           printCustomerAccount();
       } else if (choice == 2) {
           addNewCustomer();
       } else if (choice == 3) {

       } else {
           logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+"\u001B[0m");
       }
   }
  public void printCustomerAccount(){

   }
public void addNewCustomer(){

}
private void settingMenu() {
    int choice;
    Scanner scanner = new Scanner(System.in);
    Scanner scanner1 = new Scanner(System.in);

    logger.log(Level.INFO,"\n\u001B[36m" + "------- Beneficiary User Profile -------"+"\n"+
            "|                             |\n"+
            "|     1. edit Password        |\n"+
            "|     2. edit Gmail           |\n"+
            "|     3. edit PhoneNumber     |\n"+
            "|     4. edit Address         |\n"+
            "|     5. back                 |\n"+
            "|                             |\n"+
            "------------------------------\n");
    logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
    choice = scanner.nextInt();
    String choice2 ;
    String oldPass ;
    String newPass ;
    String newPassCon ;


    if (choice ==1) {
        logger.log(Level.INFO,"Enter The old password:"+"\u001B[0m");
        oldPass = scanner1.nextLine();
        logger.log(Level.INFO,"Enter The new password:"+"\u001B[0m");
        newPass = scanner1.nextLine();
        logger.log(Level.INFO,"Confirm The  password:"+"\u001B[0m");
        newPassCon = scanner1.nextLine();
        editePassword(oldPass,newPass,newPassCon);
         settingMenu();
    }
    else if (choice ==2) {
        logger.log(Level.INFO,"Enter The new Gmail:"+"\u001B[0m");
          choice2 = scanner1.nextLine();
          editeGmail(choice2);
          logger.log(Level.INFO,"The Gmail has been changed successfully"+"\u001B[0m");
        settingMenu();

    }
    else if (choice==3){
        logger.log(Level.INFO,"Enter The new Phone Number:"+"\u001B[0m");
        choice2 = scanner1.nextLine();
        editeNumber(choice2);
        logger.log(Level.INFO,"The Phone Number has been changed successfully"+"\u001B[0m");
        settingMenu();

    }
    else if (choice==4){
        logger.log(Level.INFO,"Enter The new Address:"+"\u001B[0m");
        choice2 = scanner1.nextLine();
        editeAddress(choice2);
        logger.log(Level.INFO,"The Address has been changed successfully"+"\u001B[0m");
        settingMenu();
    }
    else if (choice==5){
        Customer_menu (getUserName());
    }
    else {
        logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+"\u001B[0m");
    }
}


   public boolean truepass (String pass, String ConfirmPass){
       if(pass.equals(ConfirmPass)){
           return true;
       }
       return false;
   }
   public void writeToFile(String dataToWrite,String fileName){
       try {


           RandomAccessFile file = new RandomAccessFile("src/main/resources/myData/"+fileName+".txt", "rw");

           file.seek(file.length());
           file.writeBytes(dataToWrite);
           file.close();
       }
       catch (IOException e) {
           e.printStackTrace();
       }}
public void setTheCustomerIs(int numberOfLineCustomer){
       setNumberOfLine(numberOfLineCustomer);
}

   public void  editeUserName(String newName){
      searchTheCustomer();
      deleteLine();
      String data= newName+","+getGmail()+","+getPassword()+","+getAddress()+","+getId()+","+getPhone()+"\n";
      writeToFile(data,"BeneficiaryData");
      searchTheCustomerNewLine();
   }
   public void editeGmail(String newGmail){
       searchTheCustomer();
       deleteLine();
       String data= getUserName()+","+newGmail+","+getPassword()+","+getAddress()+","+getId()+","+getPhone()+"\n";
       writeToFile(data,"BeneficiaryData");
       searchTheCustomerNewLine();
   }
   public void editeNumber(String newPhone){
       searchTheCustomer();
       deleteLine();
       String data= getUserName()+","+getGmail()+","+getPassword()+","+getAddress()+","+getId()+","+newPhone+"\n";
       writeToFile(data,"BeneficiaryData");
       searchTheCustomerNewLine();
   }
   public void editeAddress(String newAddress){
       searchTheCustomer();
       deleteLine();
       String data= getUserName()+","+getGmail()+","+getPassword()+","+newAddress+","+getId()+","+getPhone()+"\n";
       writeToFile(data,"BeneficiaryData");
       searchTheCustomerNewLine();
   }
   public void  editePassword(String oldPass,String newPass,String conPass){
       searchTheCustomer();
       if(truepass(oldPass,getPassword())){
           if(truepass(newPass,conPass)) {
               deleteLine();
               String data = getUserName() + "," + getGmail() + "," + newPass + "," + getAddress() + "," + getId() + "," + getPhone() + "\n";
               writeToFile(data, "BeneficiaryData");
               searchTheCustomerNewLine();
               logger.log(Level.INFO, "\u001B[35m" + "The Password has been changed successfully" + "\u001B[0m");

           }
           else
               logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mThe Two password does not match"+"\u001B[0m");

       }

       else
           logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31mThe password is incorrect" + "\u001B[0m");

}

public void deleteLine() {
   try {
       RandomAccessFile raf = new RandomAccessFile("src/main/resources/myData/BeneficiaryData.txt", "rw");
       long start = 0;
       long currentPos = raf.getFilePointer();
       int currentLine = -1;

       while (currentLine < getNumberOfLine()){
           start = currentPos;
           raf.readLine();
           currentPos = raf.getFilePointer();
           currentLine++;
       }

       // Save the rest of the file after the line to be deleted
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
   private void extractedStoreData(String[] productInfo) {
           setUserName(productInfo[0]);
           setGmail(productInfo[1]);
           setPassword(productInfo[2]);
           setAddress(productInfo[3]);
           setId(Integer.parseInt(productInfo[4]));
           setPhone(Integer.parseInt(productInfo[5]));
           order.setIdCustomer(productInfo[4]);
           order.setCustomerName(productInfo[0]);
           owner.setIdCustomer(productInfo[4]);
           owner.setCustomerName(productInfo[0]);
           owner.setPhoneCustomer(productInfo[5]);
           Gmail gmail = new Gmail();
           gmail.setEmail(productInfo[1]);
           owner.setGmail(gmail);
           owner.setAddress(productInfo[3]);
   }

   private List<Product> products;
  


   public boolean isBrowseProductsFlag() {
       return browseProductsFlag;
   }

   public List<String> searchProductsByName(String name) {
       return searchProductsByCriteria(name);
   }

   public List<String> searchProductsByID(String id) {
       return searchProductsByCriteria(id);
   }

   public List<String> searchProductsByDescription(String description) {
       return searchProductsByCriteria(description);
   }

   public List<String> searchProductsByAvailability(String availability) {
       return searchProductsByCriteria(availability);
   }

   private List<String> searchProductsByCriteria(String criteria) {
       List<String> products = readProductsFromFile();
       List<String> matchedProducts = new ArrayList<>();
       for (String product : products) {
           if (product.toLowerCase().contains(criteria.toLowerCase())) {
               matchedProducts.add(product);
           }
       }
       return matchedProducts;
   }

  
   public void searchTheCustomer() {
       int count =-1;
       try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/content.txt", "rw")) {
           String s;
           while ((s = ref.readLine()) != null) {
               count=count+1;
               String[] productInfo = s.split(",");
               if (count==getNumberOfLine()) {
                   extractedStoreData(productInfo);
                   return;
               }
           }
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
   }
   public void searchTheCustomerNewLine() {
       int count=-1;
       try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/content.txt", "rw")) {
           String s;
           String isd= String.valueOf(getId());
           while ((s = ref.readLine()) != null) {
               count=count+1;
               String[] productInfo = s.split(",");
               if (isd.equals(productInfo[4]))
               {
                   setUserName(productInfo[0]);
                   setNumberOfLine(count);
                   return;
               }
           }
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
   }
   

public void feedback() {
    Scanner scanner = new Scanner(System.in);
    logger.log(Level.INFO, "\n\u001B[32m" + "----- Provide Feedback -----" + "\n" +
               "Please enter your feedback below:" + "\u001B[0m");
    String userFeedback = scanner.nextLine();

    String feedbackFilePath = "src/main/resources/myData/Feedback.txt";

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(feedbackFilePath, true))) {
        writer.write(userFeedback);
        writer.newLine();
        logger.log(Level.INFO, "\u001B[32mThank you for your feedback!\u001B[0m");
    } catch (IOException e) {
        logger.log(Level.SEVERE, "\u001B[1m" + "\u001B[31mError writing feedback: " + e.getMessage() + "\u001B[0m");
    }
}
}
