import java.util.*;
import java.lang.Math.*;
public class splitwise_app {
    private static Scanner scan;
    private static int index=0,returnid=99;
    private static String index_name ="",current_name="";
    private static ArrayList <user> user_list = new ArrayList <user>();
    private static ArrayList <borrow> borrow_list = new ArrayList <borrow>();
    private static ArrayList <history> history_list = new ArrayList <history>();
    private static ArrayList <user> temp_list = new ArrayList <user>();
    public static void main(String[] args) {
        scan= new Scanner (System.in);
        users_list();
        welcomePage();        
    }
    private static void welcomePage(){
        System.out.println("\t\t-------------------- Welcome to Split Wise App --------------------");
        System.out.println("1.Going Out");
        System.out.println("2.Home");
        System.out.println("3.Exit");
        System.out.print("Enter your choice :");
        int ch = scan.nextInt();
        switch (ch) {
            case 1:
                goingOut();
                break;
            case 2:
                split_home();
                break;
            case 3:
                System.out.println("\033[H\033[2J");
                System.out.flush();
                System.exit(0);
            default:
                System.out.println("Enter valid input...");
                welcomePage();
                break;
        }
    }
    //---------------------------- going out --------------------------//
    private static void goingOut() {
        System.out.println("------------------ Welcome Going Out Page ------------");
        for(int i=0;i<user_list.size();i++){
            System.out.print(user_list.get(i).u_name +"\t\t     "+ "In/out"+"\t");
            String res = scan.next().toLowerCase();
            if(res.equals("in")){
                temp_list.add(new user(user_list.get(i).u_name,user_list.get(i).u_pass,user_list.get(i).u_amount,user_list.get(i).u_id,user_list.get(i).balance));
            }
        }
        System.out.println(temp_list);
        System.out.print("Enter the Type Expense :");
        String type = scan.next();
        System.out.print("Enter the  Expense total :");
        double total = scan.nextDouble();
        System.out.print("Enter the Giver Name :");
        String name = scan.next();
        int count =0;
        for(int k=0;k<temp_list.size();k++){
            if(temp_list.get(k).u_name.equals(name)){
                for(int i=0;i<user_list.size();i++){
                    if(user_list.get(i).u_name.equals(name)){
                        if(user_list.get(i).u_amount>total){
                            int buyer_id=i;
                            current_name=name;
                            user_list.get(i).u_amount-=total;
                            history_list.add(new history(name,type,total));                    
                            count++;
                            set_return(total,buyer_id,type);
                        }
                        else{
                            System.out.println("Your wallet Amount is Too low to Pay.....");
                            System.out.println("Press Enter to continue");
                            scan.nextLine();
                            String s = scan.nextLine();
                            System.out.println("\033[H\033[2J");
                            System.out.flush();
                            welcomePage();
                        }
                    }
                }                
            }
        } //               
        if(count==0){
            System.out.println("Giver name Doesn't Exist...");
            temp_list.clear();
            System.out.println("Press Enter to continue");
            scan.nextLine();
            String s = scan.nextLine();
            System.out.println("\033[H\033[2J");
            System.out.flush();
            goingOut();
        }
    }

    private static void set_return(double total,int buyer_id,String type) {        
        double split = total/temp_list.size();
        for(int i=0;i<temp_list.size();i++){  //====>tem=3
            if(!temp_list.get(i).u_name.equals(current_name)){     //==>user 5           
                returnid++;
                borrow_list.add(new borrow(current_name,temp_list.get(i).u_name,split,returnid,buyer_id,type));
            }
        }
        temp_list.clear();
        System.out.println("Borrow list");
        System.out.println(borrow_list);
        System.out.println("Press Enter to continue");
        scan.nextLine();
        String s = scan.nextLine();
        System.out.println("\033[H\033[2J");
        System.out.flush();
        welcomePage();

    }
 


    //---------------------------- users ---------------------------//
    private static void split_home() {
        System.out.println("\t\t-------------- Welcome to Home -------------");
        System.out.print("Enter User Name :");
        String u_name = scan.next();
        System.out.print("Enter User Password :");
        String u_pass = scan.next();
        if(check(user_list,u_name,u_pass)){
            orgUser();
        }
        else{
            System.out.println("Invalid User Credencials...please Try again....:(");            
            split_home();
        }
    }

    private static boolean check(ArrayList<user> user_list2, String u_name, String u_pass) {
        for(int i=0;i<user_list.size();i++){
            if(user_list.get(i).u_name.equals(u_name) && user_list.get(i).u_pass.equals(u_pass)){
                index_name=user_list.get(i).u_name;
                index=i;
                return true;
            }
        }
        return false;
    }
    //-------------------------- org user ---------------------//
    private static void orgUser() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
        System.out.println("\t---------- Welcome "+user_list.get(index).u_name +" ----------");
        System.out.println("1.Amount to be give");
        System.out.println("2.Amount to be Recieve");
        System.out.println("3.Expenses history");
        System.out.println("4.My Wallet");
        System.out.println("5.Add Wallet amount");
        System.out.println("6.Back to home");
        System.out.print("Enter Your choice :");
        int key = scan.nextInt();
        switch (key) {
            case 1:
                given();
                break;
            case 2:
                pending();
                break;
            case 3:
                expenses();
                break;
            case 4:
                wallet();
                break;
            case 5:
                add_amount();
                break;
            case 6:                
                System.out.println("\033[H\033[2J");
                System.out.flush();
                welcomePage();                
            default:
                System.out.println("Enter valid Input....");
                orgUser();
                break;
        }
    }


    private static void pending() {
        int count=0;
        System.out.printf("  %-12s||  %-25s||  %-18s||\n","Returner Name","Amount to be Return","Type of Expenses");
        for(int i=0;i<borrow_list.size();i++){
            if(borrow_list.get(i).buyer_name.equals(index_name)){
                System.out.printf("  %-12s||  %-25s||  %-18s||\n",borrow_list.get(i).given_name,borrow_list.get(i).share,borrow_list.get(i).type); 
                count++;
            }
        }
        if(count==0){
            System.out.println("No Amount to be recieved....");
        }
        System.out.println("Press Enter to continue");
        scan.nextLine();
        String s = scan.nextLine();
        System.out.println("\033[H\033[2J");
        System.out.flush();
        orgUser();
    }



    private static void add_amount() {
        for(int i=0;i<user_list.size();i++ ){
            if(user_list.get(i).u_id==user_list.get(index).u_id){
                System.out.println("Enter Amount to be add :");
                double add_amt = scan.nextDouble();
                user_list.get(i).u_amount += add_amt;
            }
        }
        System.out.println("Press Enter to continue");
        scan.nextLine();
        String s = scan.nextLine();
        System.out.println("\033[H\033[2J");
        System.out.flush();
        orgUser();
    }


    private static void given() {
        System.out.println("\t\t--------- Your Pending Balance --------- ");
        int count=0;
        System.out.printf("%-12s||  %-12s||  %-12s||  %-25s||  %-18s||\n","Return ID" ,"Buyer Name","Your name","Amount to be Return","Type of Expenses");
        for(int i=0;i<borrow_list.size();i++){
            if(borrow_list.get(i).given_name.equals(index_name)){
                System.out.printf("%-12s||  %-12s||  %-12s||  %-25s||  %-15s||\n",borrow_list.get(i).returnid,borrow_list.get(i).buyer_name,borrow_list.get(i).given_name,borrow_list.get(i).share,borrow_list.get(i).type); 
                count++;
            }
        }
        if(count==0){
            System.out.println("No Pending left.....");
        }
        else{
            int temp=0,t;
            System.out.println("\tPress 1 to return amount or press 0 to exit.... ");
            int pending_check = scan.nextInt();
            if(pending_check==1){
                System.out.print("Enter the Return Id :");
                int temp_re =scan.nextInt();
               lable1: for(t=0;t<borrow_list.size();t++){
                    if(borrow_list.get(t).returnid==temp_re && borrow_list.get(t).given_name.equals(index_name)){
                        double return_amt = borrow_list.get(t).share;
                        int buy_id = borrow_list.get(t).buy_id;
                        for(int k=0;k<user_list.size();k++){
                            if(user_list.get(k).u_name.equals(index_name)){
                                if(return_amt>user_list.get(k).u_amount){
                                    System.out.println("\t\tInsufficient wallet amount....!!Add wallet amount");
                                    break lable1;
                                }
                                else{
                                    user_list.get(k).u_amount-=return_amt;
                                    temp++;                                
                                }
                            }
                            if(user_list.get(k).u_id==buy_id){
                                user_list.get(k).u_amount+=return_amt;
                            }
                        }
                        System.out.println("Amount Returned Successfully...");   
                    }  
                }   
                if(temp!=0){
                    for(int v=0;v<borrow_list.size();v++){
                        if(borrow_list.get(v).returnid==temp_re){
                            borrow_list.remove(v);
                            break;
                        }
                    }  
                }                                           
            }            
                
            else if(pending_check==0){
                System.out.println("\033[H\033[2J");
                System.out.flush();
                orgUser();
            }
            else {
                System.out.println("Please enter the Correct Option...");
                scan.nextLine();
                String s = scan.nextLine();
                System.out.println("\033[H\033[2J");
                System.out.flush();
                pending();
            }
        }
        System.out.println("Press Enter to continue");
        scan.nextLine();
        String s = scan.nextLine();
        System.out.println("\033[H\033[2J");
        System.out.flush();
        orgUser();
    }


    private static void wallet() {
        System.out.println("\tYour availalble balance :"+user_list.get(index).u_amount);
        System.out.println("Press Enter to continue");
        scan.nextLine();
        String s = scan.nextLine();
        System.out.println("\033[H\033[2J");
        System.out.flush();
        orgUser();
    }


    private static void expenses() {
        System.out.println("\t\t------------------ Over All Expenses --------------");
        System.out.printf("  %-12s,  %-12s,  %-12s\n","Buyer Name","Expense Type","Total Amount");
        int count=0;
        for (int i = 0; i <history_list.size(); i++) {
            count++;
            System.out.printf("  %-12s,  %-12s,  %-12s\n",history_list.get(i).buyer_name,history_list.get(i).type_expense,history_list.get(i).expense_total);
        }
        if(count==0){
            System.out.println("No Expenses Left...");
        }
        System.out.println("Press Enter to continue");
        scan.nextLine();
        String s = scan.nextLine();
        System.out.println("\033[H\033[2J");
        System.out.flush();
        orgUser();
    }


    private static void users_list(){
        user_list.add(new user("Arun","1234",5000,10,0));
        user_list.add(new user("ChandraMohan","1111",10000,11,0));
        user_list.add(new user("Amrith","2222",1000,12,0));
        user_list.add(new user("Mothilal","3333",8000,13,0));
        user_list.add(new user("Santhose","4444",7000,14,0));
    }

}
class borrow extends splitwise_app{
    String buyer_name,given_name,type;
    double share;
    int returnid,buy_id;
    borrow (String buyer_name,String given_name,double share,int returnid,int buy_id,String type){
        this.buyer_name = buyer_name;
        this.given_name = given_name;
        this.share = share;        
        this.returnid =returnid;
        this.buy_id=buy_id;
        this.type=type;
    }
    public String toString(){
        return buyer_name+ " " +given_name+" "+share+" "+returnid+" "+buy_id+" "+type;
    }
}
class history extends splitwise_app{
    String buyer_name,type_expense;
    double expense_total;
    history (String buyer_name,String type_expense,double expense_total){
        this.buyer_name = buyer_name;
        this.type_expense = type_expense;
        this.expense_total = expense_total;
    }
}
class user extends splitwise_app{
    String u_name,u_pass;
    double u_amount,balance;
    int u_id;
    user(String name,String u_pass,double u_amt,int u_id,double balance){
        this.u_name=name;
        this.u_amount=u_amt;
        this.u_id=u_id;
        this.u_pass=u_pass;
        this.balance = balance;
    }
    public String toString(){
        return u_name+ " " +u_pass+" "+u_amount+" "+u_id+" "+balance;
    }
}
