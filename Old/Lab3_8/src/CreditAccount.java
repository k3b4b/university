
import java.io.Serializable;


public class CreditAccount extends Account implements Serializable {
    
    
    protected static String TYPE = "Credit";
    protected static double MAX_AMOUNT_OF_CREDIT = 0 - Double.MAX_VALUE;
    
    
    public CreditAccount(){
        this.amountOfMoney = 0;
        this.rate = 0;
    }
    
    public CreditAccount(double newRate){
        this.amountOfMoney = 0;
        setRate(newRate);
    }
    
    @Override
    public void withdrawMoney(double payment){
        if(payment > 0){
            if(this.amountOfMoney >= (this.MAX_AMOUNT_OF_CREDIT + payment)){
                this.amountOfMoney -= payment;
                System.out.print("\nMoney was withdrawed from Account");
            } 
            else System.out.println("\nThe maximum amount of credit on the Credit Account has been exceeded");
        }
        else System.out.println("\nPayment cannot be equal to a negative number or zero");
    }
    
    @Override
    public void chargeInterest(){
        if(this.amountOfMoney < 0){
            if(this.amountOfMoney >= (this.MAX_AMOUNT_OF_CREDIT  -  this.amountOfMoney * 0.01 * this.rate)){
                putMoney(this.amountOfMoney * 0.01 * this.rate);
                System.out.println("\nInterest was charged");
            }
            else System.out.println("\nThe maximum amount of credit on the Credit Account has been exceeded");
        }
        else{
            if(this.amountOfMoney <= (this.MAX_AMOUNT_OF_MONEY  -  this.amountOfMoney * 0.01 * this.rate)){
                putMoney(this.amountOfMoney * 0.01 * this.rate);
                System.out.println("\nInterest was charged");
            }
            else System.out.println("\nExceeded the maximum amount of money in the Account");
        }
    }
    
    @Override
    public String getTYPE(){
        return this.TYPE;
    }
    
}
