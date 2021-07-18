import java.util.*;
public class bankAccount{
	private String accountName;
	private int accountNumber;
	private String accountEmail;
	private String accountPhoneNumber;
	private double accountBalance;
	private double prevTransaction;
	private static int accountNumTracker = 0; //this is used to set the different account numbers
	static bankAccount val[] = new bankAccount[300];
	
	public bankAccount(String accountName, String accountEmail, String accountPhoneNumber ) {
		this.accountName = accountName;
		this.accountEmail = accountEmail;
		this.accountPhoneNumber = accountPhoneNumber;
		this.accountNumber = accountNumTracker;
		val[accountNumTracker] = this;
		accountNumTracker++;
	}
	
	public bankAccount(String accountName) {
		this(accountName,"abc.gmail.com", "000-000-000");
	}
	
	private static bankAccount checker1(char user1) {
		bankAccount user2 = null;
		Scanner input = new Scanner(System.in);
		while(user2 == null) {
			System.out.println("You do not have an existing account with this account number. a: Try again b: make a new account answer: ");
			user1 = input.next().charAt(0); 
			if(user1 == 'a') {
				System.out.print("Print your account number: ");
				int z = input.nextInt();
				for(int i = 0; i < accountNumTracker; i++) {
					if(z == val[i].accountNumber) {
						System.out.println("Your account was found!");
						user2 = val[i];
						return user2;
					}
				}
			}
			else if(user1 == 'b') {
				System.out.print("Please print your name, email, and phone nubmer that you want attached to this account");
				String name1 = input.next();
				String email1 = input.next();
				String phonenumber1 = input.next();
				user2 = new bankAccount(name1, email1, phonenumber1);
				System.out.println("You have a new account now!");
				return user2;
			}
			else {
				System.out.println("Invalid input. Please try again");
			}
		}
		return null;
	}
	
	public void deposit(double x) {
		if(x > 0) {
			accountBalance += x;
			prevTransaction = x;
		}
	}
	
	public void withdrawal(double x) {
		if(x > 0 && x <= accountBalance)
		accountBalance -= x;
		prevTransaction = x;
	}
	
	public void summarizer(){
		System.out.println("Account name: " + accountName);
		System.out.println("Account number: " + accountNumber);
		System.out.println("Account email: " + accountEmail);
		System.out.println("Account name: " + accountBalance);
		System.out.println("Most Recent Transaction: " + prevTransaction);
	}
	
	public static bankAccount createAccount(String accountName, String accountEmail, String accountPhoneNumber) {
		bankAccount newer = new bankAccount(accountName, accountEmail, accountPhoneNumber);
		boolean user = false;
		System.out.print("The account was made! Would you like to see the summary of it? Type Y or N: ");
		Scanner input = new Scanner(System.in);
		char x = input.next().charAt(0);
		while(user == false) {
			if(x == 'y' || x == 'Y') {
				newer.summarizer();
				return newer;
			}
			else if(x == 'n' || x == 'N') {
				return newer;
			}
			else {
				System.out.println("Invalid input. Try again!");
				System.out.print("Type Y or N: ");
				x = input.next().charAt(0);
				if(x == 'y' || x == 'Y') {
					newer.summarizer();
					user = true;
				}
				else if(x == 'n' || x == 'N') {
					user = true;
				}
		
			}
			
		}
		return null;
	}
	
	public static void summarizer2() {
		System.out.println("1: Summarize your account? ");
		System.out.println("2: Make a deposit from your account? ");
		System.out.println("3: Withdrawl from your account? ");
		System.out.println("4: Make a new account? ");
		System.out.println("5: Finish? ");
	}
	
	private static void seperater() {
		System.out.println();
		System.out.println("__________________________");
		System.out.printf("\n \n \n \n ");
		System.out.println("__________________________");
		System.out.println();
	}
	
	public static void main(String args[]) {
		bankAccount user = new bankAccount("Trial");
		boolean exp = false;
		bankAccount user2 = null;
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to your banking account! Are you a new user? Type Y or N: ");
		char user1 = input.next().charAt(0);
		while(exp == false) {
			if(user1 == 'y' || user1 == 'Y') {
				System.out.println("Since you are a new user, please enter the name, email, and phone number you want on the account: ");
				String name1 = input.next();
				String email1 = input.next();
				String phonenumber1 = input.next();
				user2 = new bankAccount(name1, email1, phonenumber1);
				System.out.println("You have now made a new account!");
				exp = true;
				System.out.println();
			}
			else if(user1 == 'n' || user1 == 'N') {
				System.out.println("Since you are a returning user, please print your account number: ");
				int a1 = input.nextInt();
				for(int i = 0; i < accountNumTracker; i++) {
					if(a1 == val[i].accountNumber) {
					    user2 = val[i];
						exp = true;
						System.out.println("Account found!");
					}
				}
				if(user2 == null) {
					user2 = checker1(user1); //makes an account if the account number is not valid
				}
				exp = true;
				
			}
			else {
				System.out.println("Invalid input. Try again!");
				System.out.println("Type Y or N: ");
				user1 = input.next().charAt(0);
			}
		}
		
		int x = 0;
		while(x != 5) {
			bankAccount.summarizer2();
			System.out.print("Choose a number: ");
		    x = input.nextInt();
			if(x == 1) {
				user2.summarizer();
				bankAccount.seperater();
			}
			else if(x == 2) {
				System.out.print("How much money would you like to deposit: ");
				double z = input.nextDouble();
				user2.deposit(z);
				System.out.println("Deposit made!");
				bankAccount.seperater();
			}
			else if(x == 3) {
				System.out.print("How much money would you like to withdrawl: ");
				double z = input.nextDouble();
				user2.withdrawal(z);
				System.out.println("Withdrawal made!");
				bankAccount.seperater();
			}
			else if(x == 4) {
				System.out.print("Please enter the name, email, and phone number you want on the account: ");
				String name1 = input.next();
				String email1 = input.next();
				String phonenumber1 = input.next();
				bankAccount newest = bankAccount.createAccount(name1, email1, phonenumber1);
				bankAccount.seperater();
			}
			else if(x > 5 && x < 0){
				System.out.println("Invalid number. Please try again!");
			}
		}
		System.out.println("Thank you for your time!");
	}
		
}
