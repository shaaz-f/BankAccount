package bankAccount;

import java.util.Scanner;

public class Main {

	public static void main(String[] args)
	{
		System.out.print("ENTER INTIAL BANK ACCOUNT AMOUNT: ");
		Scanner in = new Scanner(System.in);
		double initialAmount = in.nextDouble();
		
		System.out.print("ENTER BANK ACCOUNT NAME: ");
		Scanner in1 = new Scanner(System.in);
		String accountName = in1.nextLine();
		
		System.out.print("ENTER MONTHLY TRANSACTION LIMIT: ");
		Scanner in2 = new Scanner(System.in);
		int transLimit = in.nextInt();
		
		System.out.print("ENTER EXTRA TRANSACTION FEE: ");
		Scanner in3 = new Scanner(System.in);
		double transFee = in.nextDouble();
		
		System.out.print("ENTER MONTHLY FEE: ");
		Scanner in4 = new Scanner(System.in);
		double monthlyFee = in.nextDouble();
		
		System.out.print("ENTER INTEREST RATE: ");
		Scanner in5 = new Scanner(System.in);
		int interestRate = in.nextInt();
		
		System.out.print("ENTER NUMBER OF MONTHS TO SIMULATE: ");
		Scanner in6 = new Scanner(System.in);
		int numberMonths = in.nextInt();
		
		BankAccount account = new BankAccount(initialAmount, accountName, transLimit, transFee, monthlyFee, interestRate, numberMonths);
		account.simulate();
	}
}
