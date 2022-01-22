import java.util.*;

public class vehicle_app {
    private static Scanner scan;
    private static int user_id =104,index=0;
    private static ArrayList <user> user_list   = new ArrayList<user>();
    private static ArrayList <vehicle> vehicle_list   = new ArrayList<vehicle>();
    private static ArrayList <vehicle> service_list   = new ArrayList<vehicle>();
    private static ArrayList <history_vehicle> his_list   = new ArrayList<history_vehicle>();    
    private static ArrayList <vehicle_cart> cart_list = new ArrayList <vehicle_cart>();
    private static void clear(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
    private static void enter(){
        System.out.println("Press enter to continue");
        scan.nextLine();
        String s = scan.nextLine();
    }
    private static void home(){
       System.out.println("\t\t------------- Welcome to Car?Bike rental Home --------------"); 
       System.out.println("1.Admin");
       System.out.println("2.User");
       System.out.println("3.Exit");
       System.out.print("Enter your choice :");
       int key = scan.nextInt();
       switch (key) {
           case 1:
               admin();
               break;
            case 2:
                user();
                break;
            case 3:
                System.exit(0);
           default:
                System.out.println("Enter Valid input...");
                enter();
                clear();
               break;
       }
    }
    private static void admin(){
        System.out.print("Enter Admin Id :"); 
        String ad_id = scan.nextLine();
        System.out.print("Enter Admin Password :");
        String ad_pass = scan.next();
        if(ad_id.equals("admin")&& ad_pass.equals("1234")){
            clear();
            orgAdmin();
        }
        else{
            System.out.println("Please Enter Valid Admin login Credencials...");
            enter();clear();
        }
    }

    ///--------------------------------admin----------------------------- ///
    private static void orgAdmin() {
        System.out.println("\t\t---------------- Welcome Admin -----------------");
        System.out.println("1.Add Vechicle");
        System.out.println("2.Remove Vechicle");
        System.out.println("3.Modify Vechicle");
        System.out.println("4.View List of Vechicle");
        System.out.println("5.Search Vechicle");
        System.out.println("6.Service List");
        System.out.println("7.Back Menu");
        System.out.print("Enter your choice :");
        int key = scan.nextInt();
        switch (key) {
            case 1:
                add_vehicle();
                break;
            case 2:
                remove_vehicle();
                break;
            case 3:
                modify_vehicle();
                break;
            case 4:
                list_vehicle();
                break;
            case 5:
                search_vehicle();
                break;
            case 6:
                service_vehicle();
                break;
            case 7:
                System.out.println("Thank you admin...");
                enter();clear();
                home();
            default:
                break;
        }
    }

    private static void service_vehicle() {
        clear();
        System.out.println("\t\t--------------------- Service List ----------------------");
        System.out.printf("  %-13s||  %-13s||  %-18s||  %-13s||  %-13s||  %-13s||  %-13s||\n","Vechicle Name","Vechicle Type",
        "Vechicle Numnber","Service Status","KM runs","Rent Amount","Available");
        for(int i=0;i<service_list.size();i++){
            System.out.printf("  %-13s||  %-13s||  %-18s||  %-13s||  %-13s||  %-13s||  %-13s||\n",service_list.get(i).v_name,
            service_list.get(i).v_type,service_list.get(i).v_no,service_list.get(i).service_status,service_list.get(i).v_km,
            service_list.get(i).v_rent,service_list.get(i).garage);
        }
        System.out.println();
        System.out.print("Press => (1) to Service Vehicle (2) to exit");
        int ch = scan.nextInt();
        if(ch==1){
            int ser_count=0;
            System.out.print("Enter Vehicle number to Service :");
            String ser_done = scan.next();
            for(int i=0;i<service_list.size();i++){
                if(service_list.get(i).v_no.equals(ser_done)){
                    ser_count++;
                    System.out.println("Service updated to "+service_list.get(i).v_name);
                    vehicle_list.add(new vehicle(service_list.get(i).v_name,
                    service_list.get(i).v_type,service_list.get(i).v_no,"no",0,
                    service_list.get(i).v_rent,service_list.get(i).garage));
                    service_list.remove(i);
                }
            }
            if(ser_count==0){
                System.out.println("Vehicle not found in service list...Try again..(");
            }

        }
        if(ch==2){
            enter();clear();
            orgAdmin();
        }
        enter();clear();
            orgAdmin();
    }   


    private static void add_vehicle() {
        clear();String in_gar = "in";
        System.out.print("Enter the Vechicle Name (Model) :");
        String v_name = scan.next();
        scan.nextLine();
        System.out.print("Enter the Vechicle type (Car/Bike) :");
        String v_type = scan.next();
        scan.nextLine();System.out.print("Enter the Vechicle Number (xx00zz0000) :");
        String v_number = scan.next();
        scan.nextLine();
        System.out.print("Enter the Vechicle Service state (Yes/No) :");
        String v_service= scan.next();
        scan.nextLine();
        System.out.print("Enter the Vechicle KM runs :");
        int km = scan.nextInt();
        scan.nextLine();
        System.out.print("Enter the Vechicle Rental Amount :");
        double v_rent = scan.nextDouble();
        scan.nextLine();
        vehicle_list.add(new vehicle(v_name, v_type, v_number, v_service, km, v_rent,in_gar));
        System.out.println("Vechicle "+v_type+" added Successfully....");
        enter();clear();
        orgAdmin();
    }
    
    private static void remove_vehicle() {
        int count=0;
        System.out.print("Enter Vechicle Number to be Remove :");
        String remv_vec = scan.next();
        scan.nextLine();
        for(int i=0;i<vehicle_list.size();i++){
            if(vehicle_list.get(i).v_no.equals(remv_vec)){
                System.out.println("Vechicle "+vehicle_list.get(i).v_name+" Removed Successfully....");
                vehicle_list.remove(i);
                count++;
            }
        }
        if(count==0){
            System.out.println("Vechicle not found....Try again");
        }
        enter();clear();
        orgAdmin();
    }
    private static void modify_vehicle() {
        clear();
        System.out.println("\t\t---------------- Modify Vechicle ----------------");
        System.out.print("Enter the Vechicle number to be modify :");
        String mod = scan.next();
        scan.nextLine();
        for(int i=0;i<vehicle_list.size();i++){
            if(vehicle_list.get(i).v_no.equals(mod)){
                System.out.println("1.Modify Vechicle Name");
                System.out.println("2.Modify Vechicle Type");
                System.out.println("3.Modify Vechicle Number");
                System.out.println("4.Modify Service status");
                System.out.println("5.Modify Vechicle KM");
                System.out.println("6.Modify Vechicle Rent");
                System.out.print("Enter your choice :");
                int check = scan.nextInt();
                switch (check) {
                    case 1:
                        mod_name(i);
                        break;
                    case 2:
                        mod_type(i);
                        break;
                    case 3:
                        mod_number(i);
                        break;
                    case 4:
                        mod_ser(i);
                        break;
                    case 5:
                        mod_km(i);
                        break;
                    case 6:
                        mod_rent(i);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    private static void mod_name(int i) {
        System.out.print("Enter new Vechicle name :");
        String name=scan.next();
        scan.nextLine();
        vehicle_list.get(i).v_name=name;
        System.out.println("Vechicle name Changed Successfully....");
        enter();clear();
        orgAdmin();
    }

    private static void mod_type(int i) {
        System.out.print("Enter new Vechicle Type (Car/Bike) :");
        String type=scan.next();
        scan.nextLine();
        vehicle_list.get(i).v_type=type;
        System.out.println("Vechicle Type Changed Successfully....");
        enter();clear();
        orgAdmin();
    }

    private static void mod_number(int i) {
        System.out.print("Enter new Vechicle number :");
        String no=scan.next();
        scan.nextLine();
        vehicle_list.get(i).v_no=no;
        System.out.println("Vechicle number Changed Successfully....");
        enter();clear();
        orgAdmin();
    }

    private static void mod_ser(int i) {
        System.out.print("Want to make service ? (yes/no) :");
        String ser=scan.next();
        scan.nextLine();
        vehicle_list.get(i).service_status=ser;
        System.out.println("Status Changed Successfully....");
        enter();clear();
        orgAdmin();
    }

    private static void mod_km(int i) {
        System.out.println("1.Increase km");
        System.out.println("2.Decrease km");
        System.out.print("Enter the choice :");
        int check =scan.nextInt();
        if(check==1){
            System.out.print("Enter the Km to Increase :");
            int km=scan.nextInt();
            vehicle_list.get(i).v_km+=km;
            System.out.println("KM Increased Successfully....");
            enter();clear();
        orgAdmin();
        }
        else if(check==2){
            System.out.print("Enter the Km to Decrease :");
            int km=scan.nextInt();
            vehicle_list.get(i).v_km-=km;
            System.out.println("KM Decreased Successfully....");
            enter();clear();
            orgAdmin();
        }
    }

    private static void mod_rent(int i) {
        System.out.print("Enter the new Amount :");
        double amt=scan.nextDouble();
        vehicle_list.get(i).v_rent=amt;
        System.out.println("Amount Changed Successfully....");
        enter();clear();
        orgAdmin();
    }


    private static void list_vehicle() {
        clear();
        System.out.println("\t\t--------------------- Available Vechicles ----------------------");
        System.out.printf("  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||\n","Vechicle Name","Vechicle Type",
        "Vechicle Numnber","Service Status","KM runs","Rent Amount","Available");
        for(int i=0;i<vehicle_list.size();i++){
            System.out.printf("  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||\n",vehicle_list.get(i).v_name,
            vehicle_list.get(i).v_type,vehicle_list.get(i).v_no,vehicle_list.get(i).service_status,vehicle_list.get(i).v_km,
            vehicle_list.get(i).v_rent,vehicle_list.get(i).garage);
        }
        enter();clear();
        orgAdmin();
    }

    private static void search_vehicle() {
        clear();
        System.out.println("\t\t------------------ Search Vehicle -----------------");
        System.out.println("1.Search By Car/Bike");
        System.out.println("2.Search By Number ");
        System.out.print("Enter Your Choice :");
        int find = scan.nextInt();
        if(find==1){
            System.out.println("Car => 1 \t Bike => 2");
            int car_bike = scan.nextInt();            
            if(car_bike==1){               
                System.out.println("\t\t------------------ Available Cars -----------------");
                System.out.printf("  %-13s||  %-13s||  %-17s||  %-13s||  %-13s||  %-13s||  %-13s||\n","Vechicle Name","Vechicle Type",
            "Vechicle Numnber","Service Status","KM runs","Rent Amount","Available");
                for(int i=0;i<vehicle_list.size();i++){
                    if(vehicle_list.get(i).v_type.equals("car")){
                        System.out.printf("  %-13s||  %-13s||  %-17s||  %-13s||  %-13s||  %-13s||  %-13s||\n",vehicle_list.get(i).v_name,
                    vehicle_list.get(i).v_type,vehicle_list.get(i).v_no,vehicle_list.get(i).service_status,vehicle_list.get(i).v_km,
                    vehicle_list.get(i).v_rent,vehicle_list.get(i).garage);
                    }   
                }
            }
            if(car_bike==2){
                System.out.println("\t\t------------------ Available Bikes -----------------");
                System.out.printf("  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||\n","Vechicle Name","Vechicle Type",
            "Vechicle Numnber","Service Status","KM runs","Rent Amount","Available");
                for(int i=0;i<vehicle_list.size();i++){
                    if(vehicle_list.get(i).v_type.equals("bike")){
                        System.out.printf("  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||\n",vehicle_list.get(i).v_name,
                    vehicle_list.get(i).v_type,vehicle_list.get(i).v_no,vehicle_list.get(i).service_status,vehicle_list.get(i).v_km,
                    vehicle_list.get(i).v_rent,vehicle_list.get(i).garage);
                    }   
                }
            }
        }
        if(find ==2){
            int count=0;
            System.out.print("Enter the Vechicle Number/Name to Search :");
            String search = scan.next();
            scan.nextLine();
            System.out.printf("  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||\n","Vechicle Name","Vechicle Type",
            "Vechicle Numnber","Service Status","KM runs","Rent Amount","Available");
            for(int i=0;i<vehicle_list.size();i++){
                if(vehicle_list.get(i).v_name.equals(search) || vehicle_list.get(i).v_no.equals(search)){
                    System.out.printf("  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||\n",vehicle_list.get(i).v_name,
                    vehicle_list.get(i).v_type,vehicle_list.get(i).v_no,vehicle_list.get(i).service_status,vehicle_list.get(i).v_km,
                    vehicle_list.get(i).v_rent,vehicle_list.get(i).garage);
                    count++;
                }
            }
            if(count==0){
                System.out.println("Vechicle not found....");
            }
        }
        enter();clear();
        orgAdmin();
    }
    //------------------------------end admin------------------------//

    private static void user() {
        clear();
        System.out.println("\t\t------------------- Welcome User -------------------");
        System.out.println("1.New User");
        System.out.println("2.Existing User");
        System.out.println("3.Exit");
        System.out.print("Enter your choice :");
        int ch=scan.nextInt();
        switch (ch){
            case 1:
                newUser();
                break;
            case 2:
                existUser();
                break;
            case 3:
                home();
                break;
            default:
                System.out.println("Enter valid Input");
                enter();clear();
        }
    }
    private static void existUser() {
        System.out.print("Enter User Name :");
        String u_name=scan.next();
        scan.nextLine();
        System.out.print("Enter User Password :");
        String u_pass=scan.next();
        if(check(user_list,u_name,u_pass)){
            orgUser();
        }
        else{
            System.out.println("Invalid login Credencials Try again...");
            enter();clear();
            user();
        }
    }
    private static boolean check(ArrayList <user> user_list2,String u_name,String u_pass){       
        for(int i=0;i<user_list.size();i++){
            if(user_list.get(i).u_name.equals(u_name) && user_list.get(i).u_pass.equals(u_pass) ){
                index=i;
                return true;
            }
        }
        return false;
    }
    
    
    private static void orgUser() {
        System.out.println("\t\t------------------- Welcome "+user_list.get(index).u_name+" User -----------------");
        System.out.println("1.Borrow Vehicle");
        System.out.println("2.My Cart");
        System.out.println("3.Return Vehicle");
        System.out.println("4.My Borrow History");
        System.out.println("5.Back");
        System.out.print("Enter your Choice :");
        int key = scan.nextInt();
        switch (key) {
            case 1:
                borrow();
                break;
            case 2:
                cart();
                break;
            case 3:
                return_vehicle();
                break;
            case 4:
                history();
                break;
            case 5:
                System.out.println("\tThank You "+user_list.get(index).u_name+" !!");
                enter();clear();
                home();
                break;
            default:
                break;
        }

    }

    private static void borrow() {
        clear();
        System.out.println("\t\t--------------------- Available Vechicles ----------------------");
        System.out.printf("  %-13s||  %-13s||  %-17s||  %-13s||  %-13s||  %-13s||  %-13s||\n","Vechicle Name","Vechicle Type",
        "Vechicle Numnber","Service Status","KM runs","Rent Amount","Available(in/out)");
        for(int i=0;i<vehicle_list.size();i++){
            System.out.printf("  %-13s||  %-13s||  %-17s||  %-13s||  %-13s||  %-13s||  %-13s||\n",vehicle_list.get(i).v_name,
            vehicle_list.get(i).v_type,vehicle_list.get(i).v_no,vehicle_list.get(i).service_status,vehicle_list.get(i).v_km,
            vehicle_list.get(i).v_rent,vehicle_list.get(i).garage);
        }
        System.out.println();
        System.out.println("Press (1) to borrow or (2) to exit..");
        int borrow_in = scan.nextInt();
        if(borrow_in==1){
            System.out.print("Enter Number of Bookings (max 2) :");
            int book = scan.nextInt();
            int car_count=0,bike_count=0;
            if(book<=2){
                for(int i =0;i<book;i++){
                   int ind_count=0;
                    System.out.print("Enter Vehicle number to be borrow :");
                    String veh_num = scan.next();
           label1:  for(int j=0;j<vehicle_list.size();j++){
                        if(vehicle_list.get(j).v_no.equals(veh_num) && vehicle_list.get(j).garage.equals("in")){
                            if(vehicle_list.get(j).v_type.equals("car")){
                                car_count++;
                            }
                            if(vehicle_list.get(j).v_type.equals("bike")){
                                bike_count++;
                            }
                            if( vehicle_list.get(j).v_type.equals("car") && car_count<=1 || vehicle_list.get(j).v_type.equals("bike") && bike_count<=1 ){
                                System.out.println("\t\tAdd to cart => 1 \t Borrow Now => 2");
                                System.out.print("Enter your Choice :");
                                int cart_borrow = scan.nextInt();
                                if(cart_borrow==1){
                                    cart_list.add(new vehicle_cart(vehicle_list.get(j).v_name,
                                    vehicle_list.get(j).v_type,vehicle_list.get(j).v_no,vehicle_list.get(j).service_status,
                                    vehicle_list.get(j).v_km,
                                    vehicle_list.get(j).v_rent,vehicle_list.get(j).garage,user_list.get(index).u_id));
                                    System.out.println("Vehicle "+vehicle_list.get(j).v_name+" added successfully..");                
                                }
                                else if(cart_borrow==2){
                                    his_list.add(new history_vehicle(user_list.get(index).u_name,vehicle_list.get(j).v_name,
                                    vehicle_list.get(j).v_no,vehicle_list.get(j).v_type,user_list.get(index).u_id,vehicle_list.get(j).v_rent,"NO"));
                                    System.out.println("Vehicle "+vehicle_list.get(j).v_name+" borrowed Successfully....");
                                    vehicle_list.get(i).garage="out";
                                }                                 
                                System.out.println(car_count+"  "+bike_count);
                                ind_count++;     
                                break label1;
                            }             
                        }
                    }
                    if(ind_count==0){
                        System.out.println("Vehicle not found or You want to borrow a car/bike only once at a time..");
                        break;
                    }
                }
            }
            else {
                System.out.println("Borrow limit Exceeded try again....");
            }
            enter();clear();
            orgUser();
        }
        if(borrow_in==2){
            clear();
            orgUser();
        }
    }


    private static void cart() {
        int cart_count =0,veh_count=0;
        System.out.println("\t\t-------------- My Cart -------------");
        System.out.printf("  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||\n","Vechicle Name","Vechicle Type",
        "Vechicle Numnber","Service Status","KM runs","Rent Amount","Available(in/out)");
        for(int i=0;i<cart_list.size();i++){
            if(cart_list.get(i).u_id==user_list.get(index).u_id){
                System.out.printf("  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||  %-13s||\n",
                cart_list.get(i).v_name,cart_list.get(i).v_type,cart_list.get(i).v_no,cart_list.get(i).service_status,
                cart_list.get(i).v_km,cart_list.get(i).v_rent,cart_list.get(i).garage);
                cart_count++;
            }
        }
        if(cart_count==0){
            System.out.println("\t\tCart is Empty.....!!!");
        }
        else{
            System.out.print("Remove Vehicle from Cart ? Y/N =>");
            String str = scan.next().toLowerCase();
            if(str.equals("y")){                
                System.out.println("Enter The Vehicle Number :");
                String num = scan.next();
                for(int i=0;i<cart_list.size();i++){
                    if(cart_list.get(i).v_no.equals(num)){
                        System.out.println(cart_list.get(i).v_name+" Removed Successfully...");
                        cart_list.remove(i);
                        veh_count++;
                    }
                }
                if(veh_count==0){
                    System.out.println("Vehicle not found in cart...");
                }
            }
            else if(str.equals("n")){
                enter();
                clear();orgUser();
            }
        }
        enter();clear();
        orgUser();
    }


    private static void return_vehicle() {
        clear();
        int return_count = 0; 
        System.out.println("----------------- Your history ---------------");
        System.out.printf("  %-13s||  %-13s||  %-17s||  %-13s||  %-13s||  %-13s||\n","User Name","Vechicle Name","Vehicle Number","Vechicle Type",
        "Rent Amount","Return Status");
        for(int i=0;i<his_list.size();i++){
            if(his_list.get(i).u_id==user_list.get(index).u_id){
                System.out.printf("  %-13s||  %-13s||  %-17s||  %-13s||  %-13s||  %-13s||\n",his_list.get(i).u_name,his_list.get(i).v_name,
                his_list.get(i).v_no,his_list.get(i).v_type,his_list.get(i).v_amt,his_list.get(i).return_status);
            }
        }
        System.out.print("Enter Vehicle Number to be return :");
        String ret_num = scan.next();
        scan.nextLine();
        double km_fine = 0,damage_fine=0,total_rent=0;
        for(int i=0;i<his_list.size();i++){
            if(his_list.get(i).v_no.equals(ret_num)){
                System.out.println("You Borrowed Vehicle is "+his_list.get(i).v_name+" Rent for the Vehicle is "+his_list.get(i).v_amt);
                System.out.print("Enter Km driven :");
                int km_driv = scan.nextInt();
                System.out.print("Select Damage Level | Low - 1 | Medium - 2 | High - 3 |\t=>");
                int damage = scan.nextInt();
                if(km_driv>500){
                    km_fine += (int) (0.15*his_list.get(i).v_amt);
                    System.out.println("Your are Fined amount of Rs."+km_fine +" due to excess of Km driven");
                }
                km_fine=his_list.get(i).v_amt;                
                if(damage==1){
                    damage_fine = (int)(0.20*km_fine);
                }
                if(damage==2){
                    damage_fine = (int)(0.50*km_fine);
                }
                if(damage==3){
                    damage_fine = (int)(0.70*km_fine);
                }
                System.out.println("Damage Fined amount of Rs."+damage_fine);
                total_rent =km_fine + damage_fine;
                user_list.get(index).u_balance -=total_rent;
                System.out.println("Total rent of the Vehicle is Rs."+total_rent +" Returned successfully..");
                for(int k=0;k<his_list.size();k++){
                    if(his_list.get(k).u_id==user_list.get(index).u_id && his_list.get(k).v_no.equals(ret_num)){
                        his_list.get(k).return_status ="Returned";
                    }
                }
                for(int j=0;j<vehicle_list.size();j++){
                    if(vehicle_list.get(j).v_no.equals(ret_num)){
                        vehicle_list.get(j).v_km+=km_driv;
                        if(vehicle_list.get(j).v_km>3000 && vehicle_list.get(j).v_type.equals("car")){
                            vehicle_list.get(j).service_status="yes";
                            service_list.add(new vehicle(vehicle_list.get(j).v_name,
                            vehicle_list.get(j).v_type,vehicle_list.get(j).v_no,vehicle_list.get(j).service_status,vehicle_list.get(j).v_km,
                            vehicle_list.get(j).v_rent,"out"));
                            vehicle_list.remove(j);
                        }
                        if(vehicle_list.get(j).v_km>1500 && vehicle_list.get(j).v_type.equals("bike")){
                            vehicle_list.get(j).service_status="yes";
                            service_list.add(new vehicle(vehicle_list.get(j).v_name,
                            vehicle_list.get(j).v_type,vehicle_list.get(j).v_no,vehicle_list.get(j).service_status,vehicle_list.get(j).v_km,
                            vehicle_list.get(j).v_rent,"out"));
                            vehicle_list.remove(j);
                        }
                    }
                }
                return_count++;
            }          
        }
        if(return_count==0){
            System.out.println("Vehicle not found in your history....");
        }
        enter();clear();
        orgUser();
    }

    private static void history() {
        int his_count =0;
        System.out.println("\t\t-------------- My History -------------");
        System.out.printf("  %-13s||  %-13s||  %-17s||  %-13s||  %-13s||  %-13s||\n","User Name","Vechicle Name","Vehicle Number","Vechicle Type",
        "Rent Amount","Return Status");
        for(int i=0;i<his_list.size();i++){
            if(his_list.get(i).u_id==user_list.get(index).u_id){
                System.out.printf("  %-13s||  %-13s||  %-17s||  %-13s||  %-13s||  %-13s||\n",his_list.get(i).u_name,his_list.get(i).v_name,
                his_list.get(i).v_no,his_list.get(i).v_type,his_list.get(i).v_amt,his_list.get(i).return_status);
                his_count++;
            }
        }
        if(his_count==0){
            System.out.println("No borred History left......");
        }
        enter();clear();
        orgUser();
    }
    ///---------------------------- new user ------------------------//
    private static void newUser() {
        clear();
        System.out.print("Enter User Name:");
        String u_name=scan.next();
        scan.nextLine();
        System.out.print("Set User Password");
        String u_pass=scan.next();
        scan.nextLine();
        System.out.print("Set Initial Deposit Amount (Min:30000) :");
        double u_amt=scan.nextDouble();
        user_list.add(new user(u_name,u_pass,u_amt,user_id));
        user_id++;
        System.out.println("User Created Successfully...Try to log In");
        enter();clear();
        user();
    }
    public static void main(String[] args) {
        scan = new Scanner (System.in);
        userlist();
        vehiclelist();
        home();
    }    
    public static void userlist(){
        user_list.add(new user("Arun","1234",50000,100));
        user_list.add(new user("Subash","1111",45000,101));
        user_list.add(new user("Ram","2222",60000,102));
        user_list.add(new user("Meghna","3333",40000,103));
    }
    public static void vehiclelist(){
        vehicle_list.add(new vehicle("Pulsar","bike","tn39cs1279","no",0,1200,"in"));
        vehicle_list.add(new vehicle("Rx100","bike","tn40po1234","no",200,1000,"in"));
        vehicle_list.add(new vehicle("Duke","bike","tn09nm1999","no",150,1900,"in"));
        vehicle_list.add(new vehicle("Honda city","car","tn12bv0001","no",500,5000,"in"));
        vehicle_list.add(new vehicle("Suzuki ciaz","car","tn32cd9090","no",340,7200,"in"));
        vehicle_list.add(new vehicle("Toyata Glory","car","tn22re1239","no",0,4500,"in"));
    }

}
class vehicle extends vehicle_app{
    String v_name,v_type,service_status,v_no,garage;
    int v_km;
    double v_rent;
    vehicle (String v_name,String v_type,String v_no,String service_status, int v_km,double v_rent,String garage){
        this.v_name = v_name;
        this.v_type = v_type;
        this.v_no=v_no;
        this.service_status = service_status;
        this.v_km = v_km;
        this.v_rent = v_rent;
        this.garage=garage;
    }
}
class vehicle_cart extends vehicle_app{
    String v_name,v_type,service_status,v_no,garage;
    int v_km,u_id;
    double v_rent;
    vehicle_cart (String v_name,String v_type,String v_no,String service_status, int v_km,double v_rent,String garage,int u_id){
        this.v_name = v_name;
        this.v_type = v_type;
        this.v_no=v_no;
        this.service_status = service_status;
        this.v_km = v_km;
        this.v_rent = v_rent;
        this.u_id = u_id;
    }
}

class history_vehicle extends vehicle_app{
    String u_name,v_name,v_no,v_type,return_status;
    int u_id;
    double v_amt;
    history_vehicle(String u_name,String v_name,String v_no,String v_type,int u_id,double v_amt,String return_status){
        this.u_name=u_name;
        this.v_name = v_name;
        this.v_no = v_no;
        this.u_id = u_id;
        this.v_amt = v_amt;
        this.v_type = v_type;
        this.return_status = return_status;
    }
}
class user  extends vehicle_app{
    String u_name,u_pass;
    double u_balance;
    int u_id;
    user(String u_name,String u_pass,double u_balance,int u_id){
        this.u_name =u_name;
        this.u_pass = u_pass;
        this.u_balance = u_balance;
        this.u_id = u_id;
    }
}