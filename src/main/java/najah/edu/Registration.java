package najah.edu;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Registration {
    Order order = new Order();

    public int getNumberOfLine() {
        return numberOfLine;
    }

    public void setNumberOfLine(int numberOfLine) {
        this.numberOfLine = numberOfLine;
    }

    private int numberOfLine;
    private String AdminName ;
    private String customerName;
    Admin admin = new Admin();
    BeneficiaryUser customer = new BeneficiaryUser();
    Owner owner = new Owner();
    static Logger logger = Logger.getLogger(Registration.class.getName());
    private Scanner scanner;
    private  String OwnerName;
    private String name;
    private String email;
    private String password;
    private String comPassword;
    private String TrueEmail;
    private String TruePassword;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    private String address;
    private int theUser;
    private int id;
    private int phone;

    private boolean adminloged;
    private boolean customerLogin;
    private boolean OwnerLogin;
    private boolean customerLoged;

    public boolean isCustomerLoged() {
        return customerLoged;
    }

    public void setCustomerLoged(boolean customerLoged) {
        this.customerLoged = customerLoged;
    }

    public boolean isOwnerLoged() {
        return OwnerLoged;
    }

    public void setOwnerLoged(boolean OwnerLoged) {
        this.OwnerLoged = OwnerLoged;
    }

    private boolean OwnerLoged;

    public boolean isIfEmailFound() {
        return ifEmailFound;
    }

    public void setIfEmailFound(boolean ifEmailFound) {
        this.ifEmailFound = ifEmailFound;
    }

    private boolean ifEmailFound;

    public boolean getCustomerRegistrationCompleted() {
        return customerRegistrationCompleted;
    }

    public void setCustomerRegistrationCompleted(boolean customerRegistrationCompleted) {
        this.customerRegistrationCompleted = customerRegistrationCompleted;
    }

    private boolean customerRegistrationCompleted;

    public boolean getCustomerLogin() {
        return customerLogin;
    }

    public void setCustomerLogin(boolean customerLogin) {
        this.customerLogin = customerLogin;
    }

    public boolean getOwnerLogin() {
        return OwnerLogin;
    }

    public void setOwnerLogin(boolean OwnerLogin) {
        this.OwnerLogin = OwnerLogin;
    }


    public int getTheUser() {
        return theUser;
    }

    public void setTheUser(int theUser) {
        this.theUser = theUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getComPassword() {
        return comPassword;
    }

    public void setComPassword(String comPassword) {
        this.comPassword = comPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdminloged(boolean adminloged) {

        this.adminloged = adminloged;
    }

    public boolean getAdminloged() {

        return adminloged;
    }
 

   public void storeDataToFile(String dataToWrite) {
    try (RandomAccessFile file = new RandomAccessFile("src/main/resources/myData/BeneficiaryData.txt", "rw")) {
        file.seek(file.length());
        file.writeBytes(dataToWrite);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public boolean truepass (Registration ob, String pass, String ConfirmPass){
        if(pass.equals(ConfirmPass)){
            return true;
        }
        return false;
    }
    public void isCustomerRegistrationCompleted (Registration o ,String pass, String conformPass){
        setCustomerRegistrationCompleted(false);
        if((truepass(o,pass,conformPass))){
            setCustomerRegistrationCompleted(true);
        }

    }
    public void customerIslLogin(String email, String password) {
        numberOfLine=-1;
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/BeneficiaryData.txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                numberOfLine = numberOfLine+1;
                String[] loginCustomer = s.split(",");
                customerName=loginCustomer[0];
                TrueEmail = loginCustomer[1];
                TruePassword = loginCustomer[2];

                if (TrueEmail.equals(email) && TruePassword.equals(password)) {
                    order.setGmailIs(email);
                    setCustomerLogin(true);
                    CustomerInInSystem();
                    customer.setTheCustomerIs(numberOfLine);
                    logger.log(Level.WARNING, email+" is login sucessfully!");
                    return;
                }
            }
            notFoundEmailCustomer(email,password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void notFoundEmailCustomer(String email, String password) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/BeneficiaryData.txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] loginCustomer = s.split(",");
                TrueEmail = loginCustomer[1];
                TruePassword = loginCustomer[2];

                if (TrueEmail.equals(email) && !TruePassword.equals(password)) {
                    notFoundPasswordCustomer(email,password);
                    return;
                }
            }
            logger.log(Level.WARNING,"Beneficiary Email is Wrong! Try Again");
            whoIsLogin();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void notFoundPasswordCustomer(String email, String password) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/BeneficiaryData.txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] loginCustomer = s.split(",");
                TrueEmail = loginCustomer[1];
                TruePassword = loginCustomer[2];

                if (TrueEmail.equals(email) && !TruePassword.equals(password)) {
                    logger.log(Level.WARNING,"Beneficiary password is Wrong! Try Again ");
                    whoIsLogin();
                    return;
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void AdminLogin(String email, String password) {

        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/AdminData.txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {

                String[] loginCustomer = s.split(",");
                AdminName = loginCustomer[0];
                TrueEmail = loginCustomer[1];
                TruePassword = loginCustomer[2];

                if (TrueEmail.equals(email) && TruePassword.equals(password)) {
                    setAdminloged(true);
                    AdminInInSystem();
                    return;

                }
            }

            AdminWrongEmail(email,password);

        } catch (IOException e) {


            throw new RuntimeException(e);

        }
    }
    public void AdminInInSystem(){
        admin.setAdminLogin(getAdminloged());
    }
    public void CustomerInInSystem(){
        customer.setCustomerLogin(getCustomerLogin());
    }
    public void OwnerInSystem(){
        owner.setOwnerLogin(getOwnerLogin());
    }
    public void AdminWrongEmail(String email, String password) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/AdminData.txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] loginCustomer = s.split(",");
                TrueEmail = loginCustomer[1];
                TruePassword = loginCustomer[2];

               
            }
            logger.log(Level.WARNING,"Admin Email is Wrong! Try Again");
            whoIsLogin();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

   

    public void OwnerIsLogin(String enterEmail, String enterPassword) {

        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/owner.txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {

                String[] loginCustomer = s.split(",");
                OwnerName = loginCustomer[0];
                TrueEmail = loginCustomer[1];
                TruePassword = loginCustomer[2];

                if (TrueEmail.equals(enterEmail) && TruePassword.equals(enterPassword)) {
                    setOwnerLogin(true);
                    OwnerInSystem();
                    return;

                }
            }

            OwnerWrongEmail(enterEmail,enterPassword);

        } catch (IOException e) {

            throw new RuntimeException(e);

        }
    }
    private void OwnerWrongEmail(String enterEmail, String enterPassword) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/owner.txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] loginCustomer = s.split(",");
                TrueEmail = loginCustomer[1];
                TruePassword = loginCustomer[2];

               
            }
            logger.log(Level.WARNING,"Owner Email is Wrong! Try Again");
            whoIsLogin();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
   
    public void whoIsLogin(){
        if(getTheUser() == 1){
            String enterEmail = enterEmail();
            String enterPassword = enterPass();
            AdminLogin(enterEmail,enterPassword);
            if (adminloged == true)
                admin.Admin_menu(AdminName);
        }

        else if (getTheUser() == 2) {
            String enterEmail = enterEmail();
            String enterPassword = enterPass();
            customerIslLogin(enterEmail,enterPassword);
            if(customerLogin==true);
          
            customer.setTheCustomerIs(getNumberOfLine());

        }   else if (getTheUser() == 3) {
            String enterEmail = enterEmail();
            String enterPassword = enterPass();
            OwnerIsLogin(enterEmail,enterPassword);
            if(OwnerLogin==true)
                owner.owner_Menu(OwnerName);
        }

    }

    public String enterEmail(){
        scanner = new Scanner(System.in);
        logger.log(Level.INFO,"\u001B[32m" + "Enter the email:" + "\u001B[0m");
        return scanner.nextLine();
    }
    public String enterPass(){
        scanner = new Scanner(System.in);
        logger.log(Level.INFO,"\u001B[32m"+ "Enter the password:" + "\u001B[0m");
        return scanner.nextLine();
    }
    public void searchIfEmailIsAlreadyExist(String enteredEmail) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/BeneficiaryData.txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] loginCustomer = s.split(",");
                TrueEmail = loginCustomer[1];
                if (TrueEmail.equals(enteredEmail)) {
                    setIfEmailFound(true);
                    return;
                }
            }
            setIfEmailFound(false);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void signupMenu(){
      
    }

    public void returnEnterEmail() {
        logger.log(Level.INFO,"Enter The Email:");
        scanner = new Scanner(System.in);
        setEmail(scanner.next());
        searchIfEmailIsAlreadyExist(getEmail());
        if(isIfEmailFound()){
            returnEnterEmail();
        }
        else if (!truepass(this,getPassword(),getComPassword())) {
            returnEnterPass(this);
        }
        else {
            String dD =getName()+"," +getEmail() + "," + getPassword() + "," + getComPassword() + "," + getId()+","+getPhone()+","+"\n";
            storeDataToFile(dD);
            logger.log(Level.INFO,"The registration process was completed successfully ");
          

        }
    }
    public void returnEnterPass(Registration ob ){
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.WARNING,"\u001B[1m" +"\u001B[31m" + "The two passwords do not match!");
        logger.log(Level.INFO,"Enter your Password:");
        ob.setPassword(scanner.next());
        logger.log(Level.INFO,"Confirm your Password:");
        ob.setComPassword(scanner.next());
        if(truepass(ob,ob.getPassword(), ob.getComPassword())){
            String dD =getName()+"," +getEmail() + "," + getPassword() + "," + getComPassword() + "," + getId()+","+getPhone()+","+"\n";
            storeDataToFile(dD);
            logger.log(Level.INFO,"The registration process was completed successfully\n");

         

        }
        else {
            returnEnterPass(this);
        }
    }


   
}
