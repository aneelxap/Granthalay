package librarySystem;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

//---------------------------------------------------------------//

interface Authenticate{
	void Login();
	void signOut();
	void Error(String s);
	boolean isValidMobileNumber(String s);
}
 
 interface Library{
	String[] addBooks();
	String[] deleteBooks();
	void bookDetails();
	void issueBook();
	void returnBook();
}
 
interface OneTimePassword {
	String  getOtp();
}

//---------------------------------------------------------------//
 
 class User extends Otp implements Authenticate {
	 public int RegisterFlag=0;
	 public int loginFlag=0;
	 public int signOutFlag=0;
	 public static String password,name,phone;
	 Scanner sc = new Scanner(System.in);
	@Override
	public void Login() {
		System.out.println("Enter your Mobile Number");
		String phoneIn = sc.next();
		System.out.println("Enter your Password ");
		String passwordIn = sc.next();
		if(passwordIn.equals(password) && phoneIn.equals(phone)) {
			System.out.println(name+" you are Sucessfully Login:)");
            loginFlag=1;
		}
		else {
			Error("loginError");
		}	
	}

	@Override
	public void signOut() {
		// TODO Auto-generated method stub
		System.out.println("Thanks!!! for using eLibrary:)");
		signOutFlag=1;
	}

	public void Register() {
		// TODO Auto-generated method stub
	    int otp,otpInput;
	    if(RegisterFlag!=1) {
	       System.out.println("Enter your Name: ");
	 	    name = sc.next();
	 	   System.out.println("Enter your password: ");
	 	    password = sc.next();
	 	   System.out.println("Enter your 10 digit Phone Number: ");
	 	    String tempPhone = sc.next();
	 	    if(isValidMobileNumber(tempPhone)) {
	 	    	phone=tempPhone; 
	 	        otp = Integer.parseInt(getOtp());	 	    
	 	    System.out.println("Your OTP is: "+otp);
	 	    System.out.println("Please Enter 4 Digit OTP: ");
	 	    otpInput = sc.nextInt();
	 	    		if(otpInput!=otp) {
	 	    	Error("otpError");
	 	    }
	 	    else {
	 	    	System.out.println(name+" You Sucessfully Register with eLibrary");
	 	       RegisterFlag=1;
	 	    }
	    }
   	
	 	    }
	    else {
	    	Error("AlreadyRegistered");
	    }
	    }

	@Override
	public boolean isValidMobileNumber(String num) {
		// TODO Auto-generated method stub
		if (num.length()<11 && num.length()>9) {
			return true;
		}
		else
		return false;
	}

	@Override
	public void Error(String s) {
		// TODO Auto-generated method stub
		if(s=="otpError") {
			System.out.println("Opps!! Incorrect OTp");
		}
		else if(s=="AlreadyRegistered") {
			System.out.println(" Already Registered user please  Login");
		}
		else if(s=="loginError") {
			System.out.println(" Incorrect credentials!!! Try again with Right one:)");
		}
	}
	}
	 
//---------------------------------------------------------------//
 
 class Admin implements Authenticate {
	 public int RegisterFlag=0;
	 public int loginFlag=0;
	 public int signOutFlag=0;
	 public int adminFlag=0;
	 public static String password="admin",uname="admin",phone,name;
	Scanner sc = new Scanner(System.in);
	@Override
	public void Login() {
		System.out.println("Enter your Name");
		String name= sc.next();
		System.out.println("Enter your Username");
		String nameIn = sc.next();
		System.out.println("Enter your Password ");
		String passwordIn = sc.next();
		if(passwordIn.equals(password) && nameIn.equals(uname)) {
			System.out.println(name+" you are Sucessfully Login:)");
            loginFlag=1;
		}
		else {
			Error("loginError");
		}	
	}

	@Override
	public void signOut() {
		// TODO Auto-generated method stub
		System.out.println("//---------------------------------------------------------------//	\n");
		System.out.println("Thanks!!! for using eLibrary:)");
		System.out.println("//---------------------------------------------------------------//	\n");
		signOutFlag=1;
	}

	@Override
	public boolean isValidMobileNumber(String num) {
		// TODO Auto-generated method stub
		if (num.length()<11 && num.length()>9) {
			return true;
		}
		else
		return false;
	}

	@Override
	public void Error(String s) {
		// TODO Auto-generated method stub
		if(s=="otpError") {
			System.out.println("Opps!! Incorrect OTp");
		}
		else if(s=="issueError") {
			System.out.println("Sorry!!! "+name+" you are not issued any Book yet:)");
		}
	}
	 
 }
 
 
//---------------------------------------------------------------//
 
 class Book extends User implements Library {
	int size=20;
	 String[] bookArray = new String[size];	
	public int arrayFlag=0;
	DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
	LocalDateTime currentTime = LocalDateTime.now();  
	public int issueChoice,returnChoice;
	public String choiceSubject,returnSubject;
	public int detailsFlag=0;
	public int issueFlag=0;
	@Override
	public String[] addBooks() {
		// TODO Auto-generated method stub
		String[] temp = new String[1];
		System.out.println("Enter position to add book in Library");
		int bookPosition = sc.nextInt();
		System.out.println("Enter book to add in Library");
		String bookName = sc.next();
		System.out.println("//---------------------------------------------------------------//	");
        int pos = bookPosition-1;
    		 for (int i=0;i<bookArray.length; i++) {
    			 if(i==pos){
    				 temp[0]=bookArray[pos];
    	    			bookArray[pos]=bookName;
    	    			bookArray[pos+1]=temp[0];
    			 }
    			 }
		    return bookArray;
    		 }
	
	@Override
	public String[] deleteBooks() {
		// TODO Auto-generated method stub
				String[] temp = new String[1];
				System.out.println("Enter position to delete book in Library");
				int bookPosition = sc.nextInt();
				System.out.println("//---------------------------------------------------------------//	");
		        int pos = bookPosition-1;
		    		 for (int i=0;i<bookArray.length; i++) {
		    			 if(i==pos && i<bookArray.length-1){
		    			 bookArray[pos]=bookArray[pos+1];
		    	    	 pos++;
		    			 }
		    			 }
				    return bookArray;	
	}

	@Override
	public void bookDetails() {
		// TODO Auto-generated method stub
		for(int i=0;i<bookArray.length;i++) {
			if(bookArray[i]!=null) {
				System.out.println((i+1)+" :  "+bookArray[i]);
			}
		}
	}

	@Override
	public void issueBook() {
		// TODO Auto-generated method stub
		for(int i=0;i<bookArray.length;i++) {
			if(bookArray[i]!=null) {
				System.out.println((i+1)+" :  "+bookArray[i]);
			}
		  }
//		}
	System.out.println("//---------------------------------------------------------------//	");
	for(int i=0;i<bookArray.length;i++) {
		if(bookArray[i]!=null) {
			System.out.println((i+1)+" :  "+bookArray[i]);
		}
	  }
	System.out.println("//---------------------------------------------------------------//	");
	System.out.println("Enter your Choice as ID:  ");
    issueChoice = sc.nextInt();	
//    try  //may throw exception  
//    {     
    	for(int i=0;i<bookArray.length;i++) {
    		if(bookArray[i]!=null) {
    			choiceSubject=bookArray[issueChoice-1];	
    		}
    	  }  
//    }  
        //handling the exception  
//    catch(Exception e)  
//    {  
//        System.out.println(e);  
//    }  
    System.out.println("//---------------------------------------------------------------//	");
    System.out.println(name+" You Issued :"+choiceSubject+" Book on "+df.format(currentTime));
    System.out.println("//---------------------------------------------------------------//	");
    issueFlag=1;
	}

	@Override
	public void returnBook() {
		// TODO Auto-generated method stub
		if(issueFlag==1) {
			for(int i=0;i<bookArray.length;i++) {
				if(bookArray[i]!=null) {
					System.out.println((i+1)+" :  "+bookArray[i]);
				}
			  }
			System.out.println("//---------------------------------------------------------------//	");
			System.out.println("Enter your Choice as ID:  ");
		    returnChoice = sc.nextInt();	
		}
		else {
			Error("issueError");
		}
		try  //may throw exception  
	    {     
	    	for(int i=0;i<bookArray.length;i++) {
	    		if(bookArray[i]!=null) {
	    			returnSubject=bookArray[returnChoice-1];	
	    		}
	    	  }  
	    }  
	        //handling the exception  
	    catch(Exception e)  
	    {  
	        System.out.println(e);  
	    } 
		System.out.println("//---------------------------------------------------------------//	");
	    System.out.println(name+" You Returned :"+returnSubject+" Book on "+df.format(currentTime));
	    System.out.println("//---------------------------------------------------------------//	");
	    issueFlag=0;
	}
	 
	 
 }
 
 
//---------------------------------------------------------------// 
 
 class Otp implements OneTimePassword{

	@Override
	public String getOtp() {
		// TODO Auto-generated method stub
	    String otp;
		int randomNumber=(int)(Math.random()*9999)+1000;
	    otp=String.valueOf(randomNumber);
	    return otp;	
	}

 }
 
//---------------------------------------------------------------// 
 
 

 
public class eLibrary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
 //      =>      Instantiation
//---------------------------------------------------------------//	
		User user = new User();
		Admin admin = new Admin();
		Book book = new Book();
	    book.bookArray[0]="java";
	    book.bookArray[1]="Python Programming";
	    book.bookArray[2]="React JS";
	    book.bookArray[3]="Web Development";
		
		
//---------------------------------------------------------------//	
		int choiceLoop=1;
		do {				
	    int choice=0;
		System.out.println("1: userLogin");
		System.out.println("2: adminLogin");
		System.out.println("3: newUser");
		System.out.println("4: Exit");
		choice = sc.nextInt();
			
			switch (choice) {
			 
			case 1:
					user.Login();			
				break;
			case 2:
					admin.Login();
					admin.adminFlag=1;
				break;
			case 3:
					user.Register();
				break;		
			case 4:
				System.out.println("//---------------------------------------------------------------// ");
				user.signOut();
				System.out.println("//---------------------------------------------------------------//	\n");
					choiceLoop=0;
//					choice=0;
				break;	
			default:
				System.out.println("Wrong choice Entered");
//				choice=0;
			}

//---------------------------------------------------------------//	
			if(user.RegisterFlag==1) {
		    int choice1=0;
			System.out.println("1: userLogin");
			System.out.println("2: adminLogin");
			System.out.println("3: newUser");
			System.out.println("4: SignOut");
			choice1 = sc.nextInt();
			do {
				switch (choice1) {
				 
				case 1:
					if(user.loginFlag!=1) {
						user.Login();
						if(user.loginFlag==1) {
							choice1=0;
						}
					}	
					else {
						choice1=0;
					}
					break;
				case 2:
					if(admin.loginFlag!=1) {
						admin.Login();
						admin.adminFlag=1;
						if(admin.loginFlag==1) {
							choice1=0;
						}
					}	
					else {
						choice1=0;
					}
					break;
				case 3:
					if(user.RegisterFlag!=1) {
						admin.Login();
						if(user.RegisterFlag==1) {
							choice1=0;
						}
					}	
					else {
						choice1=0;
					}
					break;		
				case 4:
					System.out.println("//---------------------------------------------------------------//	");
					user.signOut();
					System.out.println("//---------------------------------------------------------------//	\n");
						choiceLoop=0;
						choice1=0;
					break;	
				default:
					System.out.println("Wrong choice Entered");
					choice1=0;
				}
			}
			while(choice1!=0);
			}

//---------------------------------------------------------------//	
		if(user.loginFlag==1) {
			int userChoice=0;
			System.out.println("1: issueBook");
			System.out.println("2: returnBook");
			System.out.println("3: bookDetails");
			System.out.println("4: signOut");
			userChoice = sc.nextInt();
//      do {	
			switch (userChoice) {
			 
			case 1:
				if(user.loginFlag==1 && user.RegisterFlag==1) {
					 book.issueBook();
				}
			break;
			case 2:
				book.returnBook();
				userChoice=0;
				break;
			case 3:
//				System.out.println("//---------------------------------------------------------------//	");
//			    book.bookDetails();
//			    System.out.println("//---------------------------------------------------------------//	");
//			    book.detailsFlag=1;
				break;	
			case 4:
				System.out.println("//---------------------------------------------------------------//	");
				user.signOut();
				System.out.println("//---------------------------------------------------------------//	\n");
				choiceLoop=0;
				break;	
			default:
				System.out.println("Wrong choice Entered");
				break;
//				userChoice=0;
			}
//		}
//		while(userChoice!=0);
		}
//---------------------------------------------------------------//	
		if(user.loginFlag==1) {
			System.out.println("//---------------------------------------------------------------//	");
		    book.bookDetails();
		    System.out.println("//---------------------------------------------------------------//	");
			int userChoice1=0;
			System.out.println("1: issueBook");
			System.out.println("2: returnBook");			
			System.out.println("3: adminLogin");
			System.out.println("4: signOut");
			userChoice1 = sc.nextInt();
      do {	
			switch (userChoice1) {
			 
			case 1:
				if(user.RegisterFlag!=1) {
					userChoice1=0;
					if(book.issueFlag!=1) { 
						userChoice1=0;
					}
				}	
				else {
					book.issueBook();
					userChoice1=0;
					break;
				}
			break;
			case 2:
				book.returnBook();
				userChoice1=0;
				break;	
			case 3:
				admin.Login();
				admin.adminFlag=1;
				break;	
			case 4:
				System.out.println("//---------------------------------------------------------------//	");
				user.signOut();
				System.out.println("//---------------------------------------------------------------//	\n");
				choiceLoop=0;
				userChoice1=0;
				break;	
			default:
				System.out.println("Wrong choice Entered");
				userChoice1=0;
			}
		}
		while(userChoice1!=0);
		}
//---------------------------------------------------------------//
		if(admin.adminFlag==1) {
				int adminChoice=0;
				System.out.println("1: addBook");
				System.out.println("2: removeBook");
				System.out.println("3: bookDetails");
				System.out.println("4: signOut");
				adminChoice = sc.nextInt();
	      do {	
				switch (adminChoice) {
				 
				case 1:
				    System.out.println("//---------------------------------------------------------------//	");
					book.bookDetails();
					System.out.println("//---------------------------------------------------------------//	\n");
					String[] bookAdd = book.addBooks();
					System.out.println("Library after Adding Books:) ");
					System.out.println("//---------------------------------------------------------------//	\n");
					for(int i=0;i<bookAdd.length;i++) {
						if(book.bookArray[i]!=null) {
							System.out.println((i+1)+"  "+book.bookArray[i]);
						}
					}
					System.out.println("//---------------------------------------------------------------//	\n");
					adminChoice=0;
					break;
				case 2:
					System.out.println("//---------------------------------------------------------------//	");
					book.bookDetails();
					System.out.println("//---------------------------------------------------------------//	\n");
					String[] bookRemove = book.deleteBooks();
					System.out.println("Library after Adding Books:) ");
					System.out.println("//---------------------------------------------------------------//	\n");
					for(int i=0;i<bookRemove.length;i++) {
						if(book.bookArray[i]!=null) {
							System.out.println((i+1)+"  "+book.bookArray[i]);
						}
					}
					System.out.println("//---------------------------------------------------------------//	\n");
					adminChoice=0;
					break;
				case 3:
					System.out.println("//---------------------------------------------------------------//	");
				    book.bookDetails();
				    System.out.println("//---------------------------------------------------------------//	");
				    adminChoice=0;
					break;	
				case 4:
					System.out.println("//---------------------------------------------------------------//	");
					user.signOut();
					System.out.println("//---------------------------------------------------------------//	\n");
//					choiceLoop=0;
					adminChoice=0;
					break;	
				default:
					System.out.println("Wrong choice Entered");
					adminChoice=0;
				}
			}
			while(adminChoice!=0);
		}
//---------------------------------------------------------------//		
		}
		while(choiceLoop!=0);
		
	}
  }


