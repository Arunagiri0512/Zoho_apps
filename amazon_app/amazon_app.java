package amazon_app;
import java.util.*;

public class amazon_app {
    private static Scanner scan;
    static int index=0,newmerchcount=0,userid=95,user_index=0,order_id=0;
    static boolean flag = false;
    static int newmerchid=104,product_id=1006;
    static ArrayList <merchands> m_List = new ArrayList<merchands>();
    static ArrayList <merchands> app_list = new ArrayList<merchands>();
    static ArrayList <products> pro_list = new ArrayList<products>();
    static ArrayList <users> user_list = new ArrayList<users>();
    static ArrayList <orders> ord_list = new ArrayList<orders>();
    static ArrayList <cart> cart_list = new ArrayList<cart>();
    static ArrayList <history> his_list = new ArrayList<history>();
    static String s="";
    static {
        for (int i = 0; i < 137; i++) {
            s+="-";
        }
    }
    public static void amazonHome(){        
        System.out.println("\t-----Welcome to Amazon Home----");
        System.out.println("1. Admin Login");
        System.out.println("2. Vendor Login");
        System.out.println("3.User login");
        System.out.println("4.Exit");
        System.out.println("\nEnter Choice : ");
        int opt = scan.nextInt();
        switch (opt) {
            case 1:
                admin();
                break;
            case 2:
                merchand();
                break;
            case 3:
                user();
                break;
            case 4:
                System.exit(0);
            default:
                amazonHome();
                break;
        }
    }
    private static void admin() {
        System.out.println("Enter the Admin ID: ");
        String adminId = scan.next();
        System.out.println("Enter the Admin password");
        String pswd = scan.next();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        if(adminId.equals("admin") && pswd.equals("1234")){
            amazonadmin();
        }
        else{
            System.out.println("Please Provide Valid Admin credencials");
            amazonHome();
        }
        System.out.println("\tpress enter to continue.....!!");
        scan.nextLine();
        String s9 = scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }
    private static void amazonadmin() {
        System.out.println("------Welcome Amazon Admin------");
        System.out.println("1.Approve List");
        System.out.println("2.Add Merchands");
        System.out.println("3.Remove Merchands");
        System.out.println("4.Exit");
        int min = scan.nextInt();        
        switch (min) {
            case 1:
                approvelist();
                break;
            case 2:
                addmerch();
                break;
            case 3:
                remvmerch();
                break;
            case 4:
                amazonHome();
                break;
            default:
                amazonadmin();
                break;
        }
    }
    private static void approvelist() {
        if(newmerchcount>0){
            for(int i=0;i<app_list.size();i++){
                System.out.println("New Approve Request...!!!\t Press 1 to show");
                int show = scan.nextInt();
                if(show==1){
                    System.out.println("Name of the Merchand\t MerchandId\t Merchand Brand and Details");
                    System.out.println(app_list.get(i));                   
                    System.out.println("Press (1) to Approve or (0) to Decline...");
                    String apprstate = scan.next();
                    if(apprstate.equals("1")){
                        System.out.println("NewMerchand "+app_list.get(i).merchandname+" approved successfully");
                        app_list.get(i).status="approved";                                                
                        m_List.add(app_list.get(i));
                        app_list.remove(i);                       
                    }
                    else if(apprstate.equals("0")){
                        System.out.println("NewMerchand Suspended");                        
                        app_list.remove(i); 
                    }
                    else{
                        System.out.println("Please provide valid input");
                        approvelist();
                    }
                }
                else{
                    System.out.println("Please enter the correct input..:(");
                    //flush
                    approvelist();
                }
                System.out.println("\tpress enter to continue.....!!");
                scan.nextLine();
                String s9 = scan.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                amazonadmin();
            }

        }
        else{
            System.out.println("No new approval pending...");            
        }
        System.out.println("\tpress enter to continue.....!!");
        scan.nextLine();
        String s9 = scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        amazonadmin();
    }
    private static void addmerch() {
        System.out.println("Enter Merchand name : ");
        String newmerchand = scan.next();
        System.out.println("Enter Merchand Password :");
        String newmerchandpass = scan.next();
        System.out.println("Enter Merchand Brand and Details :");
        String newmerchandbrand = scan.next();
        m_List.add(new merchands(newmerchand,newmerchandpass,newmerchandbrand,newmerchid,"approved"));
        System.out.println("new Merchand Id is "+ newmerchid+" Merchand added successfully...:)");
        newmerchid++;
        System.out.println(m_List);
        System.out.println("\tpress enter to continue.....!!");
        scan.nextLine();
        String s9 = scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        amazonadmin();
    }
    private static void remvmerch() {
       int rem_mer_count =0;
        System.out.println("Enter Merchand Id :");
        int newmerchandid = scan.nextInt();        
        for(int i=0;i<m_List.size();i++){
            if(m_List.get(i).merchandId==newmerchandid){
                System.out.println("Merchand "+m_List.get(i).merchandname+ " Removed successfully....");
                m_List.remove(i);
                rem_mer_count=1;
            }                
        }        
        if(rem_mer_count==0){
            System.out.println("Merchand Id you entered Not found");
        }

        System.out.println("\tpress enter to continue.....!!");
        scan.nextLine();
        String s9 = scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        amazonadmin();
    }
    private static void user() {
        System.out.println("Welcome Amazon Users");
        System.out.println("1.New User");
        System.out.println("2.Existing User");
        System.out.println("3.Exit");
        System.out.print("Enter your Choice:");
        int ch = scan.nextInt();
        switch (ch) {
            case 1:
                new_user();
                break;
            case 2:
                exist_user();
                break;
            case 3:
                amazonHome();
                break;
            default:
                System.out.println("Please enter valid input...!!");
                System.out.println("\tpress enter to continue.....!");
                String s3 = scan.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                user();
                break;
        }
    }
    private static void new_user() {
        System.out.print("Enter New User Name:");
        String u_name = scan.next();
        System.out.println("Set User Password");
        String u_password = scan.next();
        System.out.println("Enter your initial Wallet amount");
        double amt = scan.nextInt();
        user_list.add(new users(u_name, u_password,amt,userid));
        System.out.println("Account created succcessfully..User UserId is "+userid);
        userid++;
        System.out.println("\tpress enter to continue.....!!");
        String s3 = scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        user();
    }
    private static void exist_user() {
        System.out.println("Enter UserId");
        int ex_user = scan.nextInt();
        System.out.println("Enter User password");
        String ex_pass=scan.next();
        if(checkuser(user_list,ex_user,ex_pass)){
            orgUser();
        }
        else{
            System.out.println("Please enter valid user login Credencials..");
            exist_user();
        }
    }
    private static boolean checkuser(ArrayList<users> user_list2, int ex_user, String ex_pass) {
        for(int i=0;i<user_list.size();i++){
            if(user_list.get(i).user_id==ex_user && user_list.get(i).user_password.equals(ex_pass) ){
                user_index=i;
                return true;
            }
        }
        return false;
    }
    private static void orgUser(){        
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Welcome "+user_list.get(user_index).user_name+" Amazon User");
        System.out.println("1.Order new Items");
        System.out.println("2.My Order History");
        System.out.println("3.My Wallet");
        System.out.println("4.My cart");
        System.out.println("5.Compare Product");
        System.out.println("6.Exit");
        System.out.print("Enter your choice:");
        int n= scan.nextInt();
        switch(n){
            case 1:
                new_order();
                break;
            case 2:
                order_hist();
                break;
            case 3:
                show_wallet();
                break;
            case 4:
                my_cart();
                break;
            case 5:
                compare_product();
                break;
            case 6:
                System.out.println("Thank you Amazon User...:)");
                System.out.println("\tpress enter to continue.....!!");
                scan.nextLine();
                String s9 = scan.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                user();
            default:
                System.out.println("Please enter valid input!!");
                orgUser();
        }
    }
    private static void compare_product() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        int print=0;
        System.out.print("Enter the Product name to be compared:");
        String pro_name = scan.next();
        for(int i=0;i<pro_list.size();i++){
            if(pro_list.get(i).product_name.equals(pro_name)){
                print=1;
                break;
            }
        }
        if(print==1){
            System.out.printf("%-12s || %-12s || %-12s || %-22s ||%-12s || %-12s|| %-12s \n","Merchand ID",
                "Product Name",
                "Product Brand",
                "Product Details","ProductID",
                "Product Price","Product Discount");
        }
        for(int i=0;i<pro_list.size();i++){
            if(pro_list.get(i).product_name.equals(pro_name)){
                System.out.printf("%-12s || %-12s || %-15s || %-22s ||%-12s || %-12s|| %-12s \n",pro_list.get(i).merchandid,
                pro_list.get(i).product_name,
                pro_list.get(i).product_brand,
                pro_list.get(i).product_details,
                pro_list.get(i).productId,
                pro_list.get(i).product_price,
                pro_list.get(i).product_dis);
            }
        }
        if(print==0){
            System.out.println("Product not Found..");
        }
        System.out.println();
        System.out.println("\tpress enter to continue.....!!");
            scan.nextLine();
            String s3 = scan.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            orgUser();
    }
    private static void new_order() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\t\t\t\t\t\t\t--------Available products---------");
        System.out.println(s);
        for(int i=0;i<pro_list.size();i++){
            System.out.printf("%-15s || %-15s || %-26s ||%-15s || %-15s|| %-15s || %-15s\n",pro_list.get(i).product_name,
            pro_list.get(i).product_brand,
            pro_list.get(i).product_details,pro_list.get(i).productId,pro_list.get(i).merchandid,
            pro_list.get(i).product_price,pro_list.get(i).product_dis);
            // System.out.println(tp.get(i).product_name);
        }
        order_id++;
        double org_amt=0;
        double amt=0,dis=0;
        boolean set = true;
        System.out.println("Enter the product ID to be order");
        int order_pro = scan.nextInt();
        for(int i=0;i<pro_list.size();i++){
            if(pro_list.get(i).productId==order_pro){
                String status="Ordered";
                ord_list.add(new orders(order_id,pro_list.get(i).product_name,
                pro_list.get(i).product_brand,
                pro_list.get(i).product_details,pro_list.get(i).productId,pro_list.get(i).merchandid,
                pro_list.get(i).product_price,pro_list.get(i).product_dis,user_list.get(user_index).user_id,status));
                set=false;
            }
        }        
        if(set==true){
            order_id--;
            System.out.println("Invalid product Id or product you enter not found");
        }
        else{
             System.out.println("Press 1 to order now or 2 to add to cart");
             int cart = scan.nextInt();
             if(cart==1){
                for(int i=0;i<ord_list.size();i++){
                    if(ord_list.get(i).productId==order_pro){                                                
                        amt =ord_list.get(i).product_price;
                        dis =ord_list.get(i).product_dis;
                        double disamt = (amt*(dis/100));
                        org_amt += amt-disamt;
                        his_list.add(new history(order_id,
                        ord_list.get(i).product_name,
                        ord_list.get(i).product_brand,
                        ord_list.get(i).product_details,
                        ord_list.get(i).productId, ord_list.get(i).product_price,
                        ord_list.get(i).product_dis,
                        user_list.get(user_index).user_id,"Delivered"));
                        ord_list.remove(i);
                    }
                }    
                user_list.get(user_index).user_amt-=org_amt; 
                System.out.println("Total amount of the order is:"+org_amt);
                System.out.println("\tTanscation Completed.....!!");
                System.out.println("Available your Amazon balance:"+user_list.get(user_index).user_amt);
                System.out.println("------Thankyou!! Keep Shopping------");            
            }
            else if(cart==2)
            {
                for(int i=0;i<ord_list.size();i++){
                    if(ord_list.get(i).productId==order_pro){
                        String status="Ordered";
                        cart_list.add(new cart(order_id,
                        ord_list.get(i).product_name,
                        ord_list.get(i).product_brand,
                        ord_list.get(i).product_details,
                        ord_list.get(i).productId,
                        ord_list.get(i).merchandid,
                        ord_list.get(i).product_price,
                        ord_list.get(i).product_dis,
                        user_list.get(user_index).user_id,status));
                        his_list.add(new history(order_id,
                        ord_list.get(i).product_name,
                        ord_list.get(i).product_brand,
                        ord_list.get(i).product_details,
                        ord_list.get(i).productId, ord_list.get(i).product_price,
                        ord_list.get(i).product_dis,
                        user_list.get(user_index).user_id,"Order Pending"));
                    }
                }
                System.out.println("Product added to cart successfully");
                System.out.println(cart_list);
            }       
        }
        System.out.println("\tpress enter to continue.....!!");
        scan.nextLine();
        String s3 = scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgUser();
    }
    private static void order_hist() {
        int check=0;
        System.out.println("\t\t\t\t--------Your Order History-------"); 
        System.out.printf("%-12s || %-12s ||%-12s || %-22s ||%-12s || %-12s|| %-12s || %-12s\n","Order_id","Product Name",
        "Product Brand",
        "Product Details","ProductID",
        "Product Price","Product Discount","Status");
        System.out.println(s);
        for(int i=0;i<his_list.size();i++){
            if(his_list.get(i).user_id==user_list.get(user_index).user_id){
                System.out.printf("%-12s || %-12s || %-12s || %-22s ||%-12s || %-12s|| %-12s || %-12s\n",his_list.get(i).orderId,
                    his_list.get(i).product_name,
                    his_list.get(i).product_brand,
                    his_list.get(i).product_details,his_list.get(i).productId,
                    his_list.get(i).product_price,his_list.get(i).product_dis,
                    his_list.get(i).status);
                    check=1;
            }
        }
        if(check==1){
            System.out.println();
            System.out.println("Enter product Id to be cancel or press 1 to home...");
            int cancel =scan.nextInt();
            if(cancel==1){
                System.out.print("\033[H\033[2J");
                System.out.flush();
                orgUser();
            }    
            else if(cancel!=1){
                boolean can=true;   
                for (int i = 0; i < his_list.size(); i++) {
                    if(his_list.get(i).productId==cancel){
                        his_list.remove(i);
                        can=false;
                    }
                }
                if(can==true){
                    System.out.println("Product id not found try again....");
                }
            }
        }       
        if(check==0){
            System.out.println("No order history left...");
        }
        System.out.println("\tpress enter to continue.....!!");
            scan.nextLine();
            String s3 = scan.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            orgUser();
        
    }
    private static void my_cart() {
        int halt=1;
        int usertemp = user_list.get(user_index).user_id;
        System.out.println(usertemp);
        System.out.println("\t\t\t\t\t\t\t---------My Cart---------");
        System.out.printf(" %-15s || %-15s || %-15s || %-26s || %-13s || %-15s|| %-15s \n","Order_Id","Product Name",
        "Product Brand","Product Details","ProductID","Product Price","Product Discount");
        System.out.println(s);
        for(int i=0;i<cart_list.size();i++){
            if(ord_list.get(i).user_id==user_list.get(user_index).user_id){
                System.out.printf("%-15s || %-15s || %-15s ||%-26s || %-13s|| %-15s || %-15s\n",cart_list.get(i).orderId,
                cart_list.get(i).product_name,
                cart_list.get(i).product_brand,
                cart_list.get(i).product_details,cart_list.get(i).productId,
                cart_list.get(i).product_price,cart_list.get(i).product_dis);
                halt=0;
            }
        }
        if(halt==1){
            System.out.println("Your Cart is Empty!! ");   
            System.out.println("\tpress enter to continue.....!!");
            scan.nextLine();
            String s3 = scan.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            orgUser();
        }
        System.out.println("Select product_id to place order or press 1 to exit..");
        int exit=scan.nextInt();//producid or 1
        double pl_amt=0,pl_dis=0,pl_org_amt=0;
        if(exit==1){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            orgUser();
        }
        else if(exit!=1 ){
            boolean fg=true;
            for(int i=0;i<cart_list.size();i++){
                if(cart_list.get(i).productId==exit){                        
                    pl_amt =cart_list.get(i).product_price;
                    pl_dis =cart_list.get(i).product_dis;
                    double disamt = (pl_amt*(pl_dis/100));
                    pl_org_amt += pl_amt-disamt; 
                    his_list.add(new history(order_id,
                        ord_list.get(i).product_name,
                        ord_list.get(i).product_brand,
                        ord_list.get(i).product_details,
                        ord_list.get(i).productId, ord_list.get(i).product_price,
                        ord_list.get(i).product_dis,
                        user_list.get(user_index).user_id,"Delivered"));
                    cart_list.remove(i);
                    fg=false;                  
                }
            }    
            if(fg==true){
                System.out.println("Enter valid product id");
                System.out.println("\tpress enter to continue.....!!");
                scan.nextLine();
                String sp = scan.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                my_cart();
            }
            user_list.get(user_index).user_amt-=pl_org_amt; 
            System.out.println("Total amount of the order is:"+pl_org_amt);
            System.out.println("\tTanscation Completed.....!!");
            System.out.println("Available your Amazon balance:"+user_list.get(user_index).user_amt);
            System.out.println("------Thankyou!! Keep Shopping------"); 
            System.out.println("\tpress enter to continue.....!!");
            scan.nextLine();
            String s3 = scan.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            orgUser();  
        }
        else{
            System.out.println("please enter valid options...");
            System.out.println("\tpress enter to continue.....!!");
            scan.nextLine();
            String sp = scan.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            my_cart();
        }
              
    }
    private static void show_wallet() {
        System.out.println("Your current Amazon  Balance : "+user_list.get(user_index).user_amt);
        System.out.println("\tpress enter to continue.....!!");
        scan.nextLine();
        String sp = scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgUser();
    }
    private static void merchand() {
        System.out.println("-----Welcome Merchand Users-----");
        System.out.println("1.LogIn");
        System.out.println("2.Sign Up");                        
        System.out.println("3.Exit");
        System.out.println("Enter Your Choice:");
        int mer =scan.nextInt();
        switch (mer) {
            case 1:
                loginMer();
                break;
            case 2:
                signup();
                break;
            case 3:
                amazonHome();
                break;
            default:
                break;
        }
    }
    private static void loginMer() {
        System.out.println("Enter Merchand Id");
        int merid=scan.nextInt();
        System.out.println("Enter Merchand Password");
        String merpass = scan.next();
        if(checkmer(m_List, merid,merpass)){
            orgmer();
        }
        else if(waitmer(app_list,merid,merpass)){
            System.out.println("Your approved still in process!!!");
            System.out.println("\tpress enter to continue.....!!");
            scan.nextLine();
            String s9 = scan.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            merchand();
        }
        else{
            System.out.println("Please enter the correct Merchand login credencials");
        }
        System.out.println("\tpress enter to continue.....!!");
        scan.nextLine();
        String s9 = scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgmer();
    }
    private static boolean waitmer(ArrayList<merchands> app_list2, int merid, String merpass) {
        for(int i=0;i<app_list2.size();i++){
            if(app_list.get(i).merchandId==(merid) && app_list.get(i).merchandPass.equals(merpass)){
                return true;
            }
        }
        return false;
    }
    private static void signup() {        
        System.out.println("Enter Merchand Name:");
        String newname =scan.next();
        System.out.println("Enter Merchand Password:");
        String newpass = scan.next();
        System.out.println("Enter Your brand and details");
        String newBrand = scan.next();       
        scan.nextLine();
        System.out.println("Waiting for admin approval...");
        String status = "not approved";
        newmerchcount++;
        merchands obj =  new merchands(newname,newpass,newBrand,newmerchid,status);
        app_list.add(obj);
        newmerchid++;
        amazonHome();
        // System.out.println(app_list.get(0));
       
    }
    private static void orgmer() {
        System.out.println("-----Welcome Merchand"+m_List.get(index).merchandname+"------");
        System.out.println("1.Add product");
        System.out.println("2.remove product");
        System.out.println("3.View product");
        System.out.println("4.Update Product");
        System.out.println("5.Exit");
        System.out.print("Enter your choice:");
        int merint =scan.nextInt();
        scan.nextLine();
        
        switch (merint) {
            case 1:                
                addproduct();
                break;
            case 2:                
                removeproduct();
                break;
            case 3:                
                viewproduct();
                break;
            case 4:
                modifyProduct();
                break;
            case 5:
                System.out.println("\tpress enter to continue.....!!");
                String sl = scan.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                merchand();
                break;
        
            default:
                merchand();
                break;
        }
    }
    private static void addproduct() {
        System.out.print("Enter the product name:");
        String new_prod = scan.next();
        System.out.print("Enter the product Brand:");
        String new_brand = scan.next();
        System.out.print("Enter the product details:");
        String new_detail = scan.next();
        System.out.print("Enter the product Price");
        double new_price = scan.nextDouble();
        System.out.print("Enter the product discount:");
        int new_dis = scan.nextInt();
        product_id++;
        pro_list.add(new products(new_prod,new_brand,new_detail,product_id,m_List.get(index).merchandId,new_price,new_dis));
        System.out.println("Product "+new_prod+"Added successfully...");
        System.out.println("\tpress enter to continue.....!!");
        scan.nextLine();
        String s9 = scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgmer();
    }
    private static void removeproduct() {
        System.out.println("Enter the product name to remove");
        int tem_mer=m_List.get(index).merchandId;
        String rempro = scan.next();
        boolean flag = false;
        for(int i=0;i<pro_list.size();i++){
            if(pro_list.get(i).product_name==rempro && tem_mer==pro_list.get(i).merchandid){
                System.out.println("Product "+pro_list.get(i).product_name+ " Removed successfully....");
                pro_list.remove(i);
                flag=true;
            }    
        }
        if(flag==false){
            System.out.println("Product Not found... please enter valid product Name");
        }
        System.out.println("\tpress enter to continue.....!!");
        scan.nextLine();
        String s9 = scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgmer();
    }
    private static void viewproduct() {
        System.out.printf("%-15s || %-15s || %-20s ||%-15s || %-15s|| %-15s || %-15s\n","Product Name","Product Brand",
        "Product Details","ProductID",
        "MerchantID","Product Price","Product Discount");
        System.out.println(s);
        // System.out.println("Product Name\t Product Brand\t\t product details\t\t productId\t merchandId\t productPrice\t Productdiscount");
        int temp_pro = m_List.get(index).merchandId;
        ArrayList< products> tp = new ArrayList<>();
            for(int i=0;i<pro_list.size();i++){
            // System.out.println(pro_list.get(temp_pro).product_name);
            if(pro_list.get(i).merchandid==temp_pro){
                tp.add(pro_list.get(i));
            }
        }
        display(tp);
    }
    private static void modifyProduct() {
        System.out.println("Enter the product_Id to be modified");
        int modify_id = scan.nextInt();
        int mod_check = 0;
        for (int i = 0; i < pro_list.size(); i++) {
            if (pro_list.get(i).productId == modify_id && pro_list.get(i).merchandid == m_List.get(index).merchandId) {                
                System.out.print("\033[H\033[2J");
                System.out.flush();
                crtModify(modify_id);
                mod_check = 1;
            }
        }
        if (mod_check == 0) {
            System.out.println("Product_id miss matched !!Enter valid Product_ID");
            System.out.println("Press Enter to exit");
            scan.nextLine();
            String e14 = scan.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            orgmer();

        }
    }

    private static void crtModify(int modify_id) {
        System.out.println("1.Modify Price: ");
        System.out.println("2.Modify discount: ");
        System.out.println("3.Modify Product details");
        System.out.println("4.Modify Product Brand");
        System.out.println("5.Go to Merchant page");
        System.out.println("Please choose any of the option");
        int mod_ch=scan.nextInt();
        switch(mod_ch){
            case 1:
                modPrice(modify_id);
                break;
            case 2:
                modDis(modify_id);
                break;
            case 3:
                modDetails(modify_id);
                break;
            case 4:
                modBrand(modify_id);
                break;
            case 5:
                orgmer();
                break;
            default:
                crtModify(modify_id);
                scan.nextLine();
                System.out.println("Please");
                String e15 = scan.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                break;
        }
    }
    private static void modPrice(int modify_id) {
        System.out.println("1.Increase price");
        System.out.println("2.Decrease price");
        System.out.println("3.Go back");
        int pr_ch = scan.nextInt();
        switch (pr_ch) {
            case 1:
                System.out.println("Enter amount to be increased:");
                double i_amt = scan.nextDouble();
                for (int i = 0; i < pro_list.size(); i++) {
                    if (pro_list.get(i).productId == modify_id ) {
                        pro_list.get(i).product_price += i_amt;
                    }
                }
                System.out.println("Updated Successfully");
                System.out.println("\tpress enter to continue.....!!");
                scan.nextLine();
                String s9 = scan.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                orgmer();
                break;
            case 2:
                System.out.println("Enter amount to be decreased");
                double d_amt = scan.nextDouble();
                for (int i = 0; i < pro_list.size(); i++) {
                    if (pro_list.get(i).productId == modify_id) {
                        pro_list.get(i).product_price -= d_amt;
                    }
                }
                System.out.println("Updated Successfully");
                System.out.println("\tpress enter to continue.....!!");
                scan.nextLine();
                String s0 = scan.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                orgmer();
                break;
            case 3:
                orgmer();
                break;
            default:
                modPrice(modify_id);
                break;
        }
    }
    private static void modBrand(int modify_id) {
        String mod_brad="";
        int brad_ind=0;
        for(int i=0;i<pro_list.size();i++){
            if(pro_list.get(i).productId==modify_id ){
                mod_brad=pro_list.get(i).product_brand;
                brad_ind=i;
            }
        }
        System.out.println("Existing brand for "+pro_list.get(brad_ind).product_name+" is "+pro_list.get(brad_ind).product_brand);
        System.out.println("Enter the new brand name to be modified...");
        scan.nextLine();
        String mod_brand = scan.next(); 
        pro_list.get(brad_ind).product_brand=mod_brand;       
        System.out.println("Brand updated successfully");
        System.out.println("\tpress enter to continue.....!!");
        scan.nextLine();
        String s9 = scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgmer();
    }
    private static void modDetails(int modify_id) {
        String cur_det="";
        int mod_index=0;
        for (int i = 0; i < pro_list.size(); i++) {
            if(pro_list.get(i).productId==modify_id){
                cur_det=pro_list.get(i).product_details;
                mod_index=i;
            }
        }
        System.out.println("Existing product details for "+pro_list.get(mod_index).product_name+" from "+pro_list.get(mod_index).product_details);
        System.out.print("Enter the new details:");
        scan.nextLine();
        String mod_det = scan.next();
        pro_list.get(mod_index).product_details=mod_det;        
        System.out.println("Product details updated successfully...");
        System.out.println("\tpress enter to continue.....!!");
        scan.nextLine();
        String s9 = scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgmer();
    }
    private static void modDis(int modify_id) {
        double cur_dis=0;
        int dis_index=0;
        for (int i = 0; i < pro_list.size(); i++) {
            if(pro_list.get(i).productId==modify_id){
                cur_dis=pro_list.get(i).product_dis;
                dis_index=i;
            }
        }
        System.out.println("Available discount for the "+pro_list.get(dis_index).product_name+" product is "+pro_list.get(dis_index).product_dis);
        System.out.print("Enter the new discount:");   
        scan.nextLine();
        double new_dis = scan.nextDouble();
        pro_list.get(dis_index).product_dis=new_dis;
        System.out.println("Product details updated successfully...");
        System.out.println("\tpress enter to continue.....!!");
        scan.nextLine();
        String s9 = scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgmer();       
    }    
    private static void display(ArrayList<products> tp) {
        for(int i=0;i<tp.size();i++){
            System.out.printf("%-15s || %-15s || %-20s ||%-15s || %-15s|| %-15s || %-15s\n",tp.get(i).product_name,
            tp.get(i).product_brand,
            tp.get(i).product_details,
            tp.get(i).productId,
            tp.get(i).merchandid,
            tp.get(i).product_price,
            tp.get(i).product_dis);
            // System.out.println(tp.get(i).product_name);
        }
        System.out.println("\tpress enter to continue.....!!");
        String s9 = scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgmer();
    } 
    private static boolean checkmer(ArrayList<merchands> m_List2, int merid, String merpass) {
        for(int i=0;i<m_List.size();i++){
            if(m_List.get(i).merchandId==merid && m_List.get(i).merchandPass.equals(merpass)){
                index=i;
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        scan = new Scanner (System.in);
        merchandlist();   
        productlist();
        userlist();
        amazonHome();     
        //merchands m=new merchands();
    }
    public static void merchandlist(){
        m_List.add(new merchands( "amrith", "1234","Mobiles",101,"approved"));
        m_List.add(new merchands("ram", "2222","Tablets",102,"approved"));
        m_List.add(new merchands("rithik", "3333","Furniture",103,"approved"));
    }
    public static void productlist(){
        pro_list.add(new products( "Mobiles", "OPPO","RAM-16GB ROM-256GB",1001,101,32000,5));
        pro_list.add(new products( "Tablets", "Samsung","RAM-32GB ROM-512GB",1002,102,45999,7));
        pro_list.add(new products( "Mobiles", "Vivo","RAM-32GB ROM-256GB",1009,101,29999,2));
        pro_list.add(new products( "Book", "Robin Sharma","Motivational - Life story",1003,103,2999,10));
        pro_list.add(new products( "Laptop", "HP","SSD - 128GB HDD-1TB",1004,101,72000,3));
        pro_list.add(new products( "Ear pods", "Sony","Mulit-3D-surround quality",1005,102,2000,0));
        pro_list.add(new products( "Laptop", "Mac Book","SSD - 256GB HDD- 1TB",1007,102,98000,3));
        pro_list.add(new products( "Laptop", "Asus VivoBook","SSD - 512GB HDD- 2TB",1007,103,72000,3));
        pro_list.add(new products( "Laptop", "Dell","RAM-8GB HDD- 1TB",1007,101,88000,3));
        pro_list.add(new products( "Book", "MArk Twain","Greatest Humarised world",1008,102,5000,0));
    }
    public static void userlist(){
        user_list.add(new users( "santhose", "1111",120000,90));
        user_list.add(new users( "subash", "2222",90000,91));
        user_list.add(new users( "aravind", "9999",50000,92));
        user_list.add(new users( "balaji", "5555",40000,93));       
    }
}
class merchands extends amazon_app{
    int merchandId;
    String merchandname;
    String merchandPass,merchandbrand,status;
    merchands(String merchandname,String merchandPass,String merchandbrand,int merchandId,String status){
        this.merchandId=merchandId;
        this.merchandPass=merchandPass;
        this.merchandname=merchandname;
        this.merchandbrand=merchandbrand;
        this.status=status;
    }   
    public String toString(){
        return merchandname+"\t"+ merchandPass+" \t"+merchandbrand+" \t"+merchandId +"\t"+status;
    }
}
class users extends amazon_app{
    String user_name,user_password;
    int user_id;
    double user_amt;
    users(String u_name,String u_pass,double u_amt,int u_id){
        this.user_name=u_name;
        this.user_password=u_pass;
        this.user_amt=u_amt;
        this.user_id=u_id;
    }
}
class products extends amazon_app{
    int productId;
    int merchandid;
    String product_brand,product_details,product_name;
    double product_dis,product_price;
    products(String product_name,String product_brand,
    String product_details,
    int product_id,
    int merchandId,
    double product_price,
    double product_dis){
        this.product_name=product_name;
        this.merchandid=merchandId;
        this.product_brand=product_brand;
        this.product_details=product_details;
        this.productId=product_id;
        this.product_price=product_price;
        this.product_dis=product_dis;
    }
    public String toString(){
        return product_name+" ";
    }
}
class orders extends amazon_app{
    int productId,user_id;
    int merchandid,orderId;
    String product_brand,product_details,product_name;
    double product_dis,product_price;
    String status;
    orders(int orderId,String product_name,String product_brand,String product_details,
    int product_id,int merchandId,double product_price,double product_dis,int user_Id, String status){
        this.product_name=product_name;
        this.merchandid=merchandId;
        this.product_brand=product_brand;
        this.product_details=product_details;
        this.productId=product_id;
        this.product_price=product_price;
        this.product_dis=product_dis;
        this.orderId=orderId;
        this.user_id=user_Id;
        this.status=status;
    }
    public String toString(){
        return orderId+" "+product_name+" "+product_brand+" "+product_details+" "+productId+" "+product_price+" "+product_dis+" "+user_id+" "+status;
    }
}
class cart extends amazon_app{
    int productId,user_id;
    int merchandid,orderId;
    String product_brand,product_details,product_name;
    double product_dis,product_price;
    String status;
    cart(int orderId,String product_name,String product_brand,String product_details,int product_id,
    int merchandId,double product_price,double product_dis,int user_Id, String status){
        this.product_name=product_name;
        this.merchandid=merchandId;
        this.product_brand=product_brand;
        this.product_details=product_details;
        this.productId=product_id;
        this.product_price=product_price;
        this.product_dis=product_dis;
        this.orderId=orderId;
        this.user_id=user_Id;
        this.status=status;
    }
    public String toString(){
        return orderId+" "+product_name+" "+product_brand+" "+product_details+" "+productId+" "+product_price+" "+product_dis+" "+status;
    }
}
class history extends amazon_app{
    int productId,user_id;
    int merchandid,orderId;
    String product_brand,product_details,product_name;
    double product_dis,product_price;
    String status;
    history(int orderId,String product_name,String product_brand,String product_details,int product_id,
    double product_price,double product_dis,int user_Id, String status){
        this.product_name=product_name;
        this.product_brand=product_brand;
        this.product_details=product_details;
        this.productId=product_id;
        this.product_price=product_price;
        this.product_dis=product_dis;
        this.orderId=orderId;
        this.user_id=user_Id;
        this.status=status;
    }
    public String toString(){
        return orderId+" "+product_name+" "+product_brand+" "+product_details+" "+productId+" "+product_price+" "+product_dis;
    }
}