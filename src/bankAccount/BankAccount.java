package bankAccount;
import java.util.Random;
public class BankAccount {

	private double balance = 0;
	private String accountName = "";
	private int transLimit = 0;
	private double extraTransFee = 0;
	private double monthlyFee = 0;
	private int monthlyTrans = 0;
	private int interest = 0;
	private int monthNumber = 0;
	private double owingAmount = 0;
	
	public BankAccount(double initialBalance, String accountName, int transLimit, double extraTransFee, double monthlyFee, int interest, int monthNumber)
	{
		balance = initialBalance;
		this.accountName = accountName;
		this.transLimit = transLimit;
		this.extraTransFee = extraTransFee;
		this.monthlyFee = monthlyFee;
		this.interest = interest;
		this.monthNumber = monthNumber;
	}
	
	/**
	 * Deposits money to the bank account.
	 * @param amount the amount to deposit
	 */
	public void deposit(double amount)
	{
		System.out.println("DEPOSITING $" + amount + "...");

		if (monthlyTrans < transLimit) // If there are still transactions left in the month before the limit is reached.
		{
			System.out.println("TRANSACTION COMPLETE! $" + amount + " has been deposited to \"" + accountName + "\".");
			balance = balance + amount;
			balance = roundDouble(balance);
			System.out.println("YOUR BALANCE IS: $" + balance + ".");
			System.out.println("");
			monthlyTrans++;
		}
		else // If you are at the monthly transaction limit or over it.
		{
			if ((balance + amount) >= extraTransFee) // If you have enough to pay the fee if you deposit the amount.
			{
				System.out.println("TRANSACTION COMPLETE! $" + amount + " has been deposited to \"" + accountName + "\".");
				System.out.println("$" + extraTransFee + " has been charged due to maximum transaction limit being reached.");
				balance = balance + amount - extraTransFee;
				balance = roundDouble(balance);
				System.out.println("YOUR BALANCE IS: $" + balance + ".");
				System.out.println("");
				monthlyTrans++;
			}
			else // If the amount you are depositing will result in you not being able to pay the transaction fee.
			{
				System.out.println("ERROR! You will not have enough to pay for the transaction fee.");
				System.out.println("");
			}
		}

	}

	/** 
	 * Withdraws money from the bank account. 
	 * @param amount the amount to withdraw 
	 */ 
	public void withdraw(double amount) 
	{ 
		System.out.println("WITHDRAWING $" + amount + "...");
		if (amount <= balance) // If the amount you're withdrawing is less than or equal to the amount you have.
		{
			double totalRemoved = amount;
			if (monthlyTrans < transLimit) // If there are still transactions left in the month before the limit is reached.
			{
				System.out.println("TRANSACTION COMPLETE! $" + amount + " has been withdrawn from \"" + accountName + "\".");
				balance = balance - amount;
				balance = roundDouble(balance);
				System.out.println("YOUR BALANCE IS: $" + balance + ".");
				System.out.println("");
				monthlyTrans++;
			}
			else // If the monthly transaction limit has been reached.
			{
				totalRemoved = totalRemoved + extraTransFee;
				if (totalRemoved <= balance) // If you have the total amount of money (including the transaction fee) being removed from your account.
				{
					System.out.println("TRANSACTION COMPLETE! $" + amount + " has been withdrawn from \"" + accountName + "\".");
					System.out.println("$" + extraTransFee + " has been charged due to maximum transaction limit being reached.");
					balance = balance - totalRemoved;
					balance = roundDouble(balance);
					System.out.println("YOUR BALANCE IS: $" + balance + ".");
					System.out.println("");
					monthlyTrans++;
				}
				else // If you don't have the total money removed from your account (including the transaction fee).
				{
					System.out.println("ERROR! You will not have enough to pay for the transaction fee.");
					System.out.println("");
				}
			}
			
		}
		else // If you're trying to withdraw more money than you have.
		{
			System.out.println("ERROR! You need $" + (roundDouble(amount - balance)) + " more in order to make that withdrawal.");
			System.out.println("");
		}
	} 

	/** 
	 * Gets the current initialBalance of the bank account 
	 * @return the current initialBalance 
	 */ 
	public double getBalance() 
	{ 
		return balance;
	} 
	
	/**
	 * Get the the card name.
	 * @return the card name that the account has.
	 */
	public String getAccountName()
	{
		return accountName;	
	}
	
	/**
	 * Adds interest to your balance.
	 */
	public void addInterest()
	{
		double interestAmount = roundDouble(balance * interest/100);
		balance = balance + interestAmount;
		balance = roundDouble(balance);
		System.out.println("Monthly interest of $" + interestAmount + " has been added to \"" + accountName + "\".");
	}
	
	/**
	 * Takes away the monthly fee from your balance.
	 */
	public void monthlyFee()
	{
		System.out.println("Monthly fee of $" + monthlyFee + " has been charged to \"" + accountName + "\".");
		balance = balance - monthlyFee;
		balance = roundDouble(balance);
		if (balance < 0) // If the monthly fee puts you in debt.
		{
			owingAmount = Math.abs(balance);
			balance = 0;
		}
	}

	/**
	 * Gives a random number between two bounds
	 * @param lower lower limit for random
	 * @param upper upper limit for random
	 * @return random number between limits
	 */
	private double getRandom(double lower, double upper)
	{
		Random rand = new Random();
		double randomDouble = lower + (upper - lower) * rand.nextDouble();
		return randomDouble;
	}
	
	/**
	 * Returns the the double rounded to two decimal places.
	 * @param toRound the double that is to be rounded
	 * @return the rounded number
	 */
	private double roundDouble(double toRound)
	{
		double rounded = toRound * 100;
		rounded = Math.round(rounded);
		rounded = rounded / 100;
		return rounded;
	}

	/**
	 * Simulates the withdrawals and deposits for the amount of months specified.
	 */
	public void simulate()
	{
		for (int i = 1; i < monthNumber + 1; i++) // Loops through months.
		{
			System.out.println("-----------------------------");
			System.out.println("MONTH " + i);
			System.out.println("");
			int number = (int)getRandom(0, transLimit + 2);
			for (int k = 0; k < number; k++) // Loops through random amount of transactions.
			{
				int depOrWith = (int)getRandom(0,50);
				if (depOrWith >= 25) // If the random number is greater than or equal to 25, deposit.
				{
					double depAmount = roundDouble(getRandom(1, balance*0.75 + 20));
					deposit(depAmount);
				}
				else // If the random number is not greater than or equal to 25, withdraw.
				{
					double withAmount = roundDouble(getRandom(1, balance*1.5));
					withdraw(withAmount);
				}
			}
			monthlyFee();
			addInterest();
			monthlyTrans = 0;
			System.out.println("YOUR BALANCE IS: $" + balance + ".");
		}
	}


}
