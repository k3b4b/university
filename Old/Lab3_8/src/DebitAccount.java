

import java.io.Serializable;


public class DebitAccount extends Account implements Serializable {
    
    
    protected static String TYPE = "Debit";
    
    
    public DebitAccount(){
        this.amountOfMoney = 0;
        this.rate = 0;
    }
    
    public DebitAccount(double newRate){
        this.amountOfMoney = 0;
        setRate(newRate);
    }
    
    @Override
    public void putMoney(double contribution){
        if(contribution > 0){
            if(this.amountOfMoney <= (this.MAX_AMOUNT_OF_MONEY - contribution)){
                this.amountOfMoney += contribution;
                System.out.print("\nMoney was added to Account");
            }
            else System.out.println("\nExceeded the maximum amount of money in the Account");   
        }
        else System.out.println("\nContribution cannot be equal to a negative number or zero");
    }
    
    @Override
    public void withdrawMoney(double payment){
        if(payment > 0){
            if(payment <= this.amountOfMoney){
                this.amountOfMoney -= payment;
                System.out.print("\nMoney was withdrawed from Account");
            }
            else System.out.println("\nNot enough money in the Debit Account");
        }
        else System.out.println("\nPayment cannot be equal to a negative number or zero");
    }
    
    @Override
    public void chargeInterest(){
        if(this.amountOfMoney <= (this.MAX_AMOUNT_OF_MONEY  -  this.amountOfMoney * (0.01 * this.rate))){
            putMoney(this.amountOfMoney * 0.01 * this.rate);
            System.out.println("\nInterest was charged");
        }
        else System.out.println("\nExceeded the maximum amount of money in the Account");
    }
    
    @Override
    public String getTYPE(){
        return this.TYPE;
    }
    
}
