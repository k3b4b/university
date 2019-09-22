

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public abstract class Account {
    
    
    protected double amountOfMoney;
    protected double rate;
    protected static double MAX_AMOUNT_OF_MONEY = Double.MAX_VALUE;
        
    
    public void putMoney(double contribution){
        if(this.amountOfMoney <= (this.MAX_AMOUNT_OF_MONEY - contribution)){
            this.amountOfMoney += contribution;
            System.out.print("\nMoney was added to Account");
        }
        else System.out.println("\nExceeded the maximum amount of money in the Account");   
    }
    
    public abstract void withdrawMoney(double payment);
    
    public double checkMoney(){
        return this.amountOfMoney;
    } 
    
    public final void setRate(double newRate){
        if(newRate >= 0) this.rate = newRate;
        else System.out.println("\nRate cannot be less than zero");
    }
    
    public final double getRate(){
        return this.rate;
    }
    
    public abstract void chargeInterest();
   
    public abstract String getTYPE();
}
