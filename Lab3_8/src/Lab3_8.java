

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.io.*;

public class Lab3_8  {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Account> list = new ArrayList<>();
        
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("file.txt")))
        {
            list = ((List<Account>)inputStream.readObject());
            System.out.println("Data was loaded from file \"file.txt\"");
        }
            catch(Exception ex){     
            System.out.println(ex.getMessage());
            } 
        
        int flag = 1, caseFlag = 0, indexOfAccount, bufIndexOfAccount;
        double rate = -1, contribution = 0, payment = 0, sum = 0;
        String trigger;
        while(flag == 1){
        trigger = "";
        System.out.printf("\nWhat do you want to do?\n  1.  Create a Debit Account\n  2.  Create a Credit Account\n  3.  Delete an Account\n  4.  Check the balance\n  5.  Transfer money\n"
                + "  6.  Put money\n  7.  Withdraw money\n  8.  Charge interest\n  9.  Print all Accounts\n  10. Check the overall balance\n\nChoose the operation: ");
        trigger = scan.nextLine();
        switch(trigger){
            case "1":
                System.out.println("\nDefault rate = 0%, do you want to set a rate? (1 - Yes; 2 - No)");
                
                while(caseFlag != 1 && caseFlag != 2){  
                    try{
                    caseFlag = scan.nextInt();
                    if (caseFlag != 1 && caseFlag != 2) System.out.println("\nYou have entered an incorrect number. Please, enter 1 if you want to answer Yes or 2 to answer No");
                    } catch (InputMismatchException e)
                        {
                        System.out.println("\nYou have entered an incorrect character. Please, enter 1 if you want to answer Yes or 2 to answer No");
                        scan.nextLine();
                        }
                }
                
                if (caseFlag == 1){
                    System.out.println("\nEnter a rate of the account: ");
                    while(rate < 0){
                        try{
                        rate = scan.nextDouble();
                        if(rate < 0) System.out.println("\nYou have entered an incorrect number");
                        } catch (InputMismatchException e)
                            {
                            System.out.println("\nYou have entered an incorrect character");
                            scan.nextLine();                        
                            }
                    }
                    DebitAccount debit1 = new DebitAccount(rate);
                    list.add(debit1);
                    System.out.println("\nAccount [" + (list.size()-1) + "] created");
                }
                else{
                    DebitAccount debit1 = new DebitAccount();
                    list.add(debit1);
                    System.out.println("\nAccount [" + (list.size()-1) + "] created");
                }
                
                caseFlag = 0;
                rate = -1;
                break;
            case "2":
                System.out.println("\nDefault rate = 0%, do you want to set a rate? (1 - Yes; 2 - No)");
                
                while(caseFlag != 1 && caseFlag != 2){  
                    try{
                    caseFlag = scan.nextInt();
                    if (caseFlag != 1 && caseFlag != 2) System.out.println("\nYou have entered an incorrect number. Please, enter 1 if you want to answer Yes or 2 to answer No");
                    } catch (InputMismatchException e)
                        {
                        System.out.println("\nYou have entered an incorrect character. Please, enter 1 if you want to answer Yes or 2 to answer No");
                        scan.nextLine();
                        }
                }
                
                if (caseFlag == 1){
                    System.out.println("\nEnter a rate of the account: ");
                    while(rate < 0){
                        try{
                        rate = scan.nextDouble();
                        if(rate < 0) System.out.println("\nYou have entered an incorrect number");
                        } catch (InputMismatchException e)
                            {
                            System.out.println("\nYou have entered an incorrect character");
                            scan.nextLine();                        
                            }
                    }
                    CreditAccount credit1 = new CreditAccount(rate);
                    list.add(credit1);
                    System.out.println("\nAccount [" + (list.size()-1) + "] created");
                }
                else{
                    CreditAccount credit1 = new CreditAccount(rate);
                    list.add(credit1);
                    System.out.println("\nAccount [" + (list.size()-1) + "] created");
                }
                
                caseFlag = 0;
                rate = -1;
                break;
            case "3":
                if(list.size() < 2){
                    System.out.println("\nNo Accounts for deletion, please use 1. or 2. operation for creating\n");
                    break;
                }
                
                indexOfAccount = 0;
                
                while(indexOfAccount < 1 || indexOfAccount > (list.size()-1) ){
                try{
                    System.out.println("\nEnter a number of Account for deletion");
                    indexOfAccount = scan.nextInt();
                    if(indexOfAccount < 1 || indexOfAccount > (list.size()-1) )
                    System.out.println("\nYou entered an incorrect number");
                } catch (InputMismatchException e)
                    {
                    System.out.println("\nYou entered an incorrect character");
                    scan.nextLine();
                    }
                }
                
                list.remove(indexOfAccount);
                System.out.println("Account [" + indexOfAccount + "] was deleted");
                break;
            case "4":
                if(list.size() < 2){
                    System.out.println("\nNo Accounts for checking balance, please use 1. or 2. operation for creating\n");
                    break;
                }
                
                indexOfAccount = 0;
                
                while(indexOfAccount < 1 || indexOfAccount > (list.size()-1) ){
                try{
                    System.out.println("\nEnter a number of Account for checking balance");
                    indexOfAccount = scan.nextInt();
                    if(indexOfAccount < 1 || indexOfAccount > (list.size()-1) )
                    System.out.println("\nYou entered an incorrect number");
                } catch (InputMismatchException e)
                    {
                    System.out.println("\nYou entered an incorrect character");
                    scan.nextLine();
                    }
                }
                
                System.out.printf("\nBalance of the Account [%d] = %.2f", indexOfAccount, list.get(indexOfAccount).checkMoney());
                break;
            case "5":
                if(list.size() < 2){
                    System.out.println("\nNo Accounts for transfering money, please use 1. or 2. operation for creating\n");
                    break;
                }
                
                indexOfAccount = 0;
                
                while(indexOfAccount < 1 || indexOfAccount > (list.size()-1) ){
                try{
                    System.out.println("\nEnter a number of paying Account for transfering money");
                    indexOfAccount = scan.nextInt();
                    if(indexOfAccount < 1 || indexOfAccount > (list.size()-1) )
                    System.out.println("\nYou entered an incorrect number");
                } catch (InputMismatchException e)
                    {
                    System.out.println("\nYou entered an incorrect character");
                    scan.nextLine();
                    }
                }
                
                bufIndexOfAccount = 0;
                
                while(bufIndexOfAccount < 1 || bufIndexOfAccount > (list.size()-1) ){
                try{
                    System.out.println("\nEnter a number of receiving Account for transfering money");
                    bufIndexOfAccount = scan.nextInt();
                    if(bufIndexOfAccount < 1 || bufIndexOfAccount > (list.size()-1) )
                    System.out.println("\nYou entered an incorrect number");
                } catch (InputMismatchException e)
                    {
                    System.out.println("\nYou entered an incorrect character");
                    scan.nextLine();
                    }
                }
                
                System.out.println("\nEnter sum of money for transfering");
                while(contribution <= 0){
                    try{
                        contribution = scan.nextDouble();
                        if(contribution <= 0) System.out.println("\nSum of money cannot be equal to a negative number or zero");
                    } catch (InputMismatchException e)
                        {
                        System.out.println("\nYou have entered an incorrect character");
                        scan.nextLine();                        
                        }
                }
                
                list.get(indexOfAccount).withdrawMoney(contribution);
                System.out.println(" [" + indexOfAccount + "]");
                list.get(bufIndexOfAccount).putMoney(contribution);
                System.out.println(" [" + bufIndexOfAccount + "]");
                contribution = 0;
                break;
            case "6":
                if(list.size() < 2){
                    System.out.println("\nNo Accounts for putting money, please use 1. or 2. operation for creating\n");
                    break;
                }
                
                indexOfAccount = 0;
                
                while(indexOfAccount < 1 || indexOfAccount > (list.size()-1) ){
                try{
                    System.out.println("\nEnter a number of Account for putting money");
                    indexOfAccount = scan.nextInt();
                    if(indexOfAccount < 1 || indexOfAccount > (list.size()-1) )
                    System.out.println("\nYou entered an incorrect number");
                } catch (InputMismatchException e)
                    {
                    System.out.println("\nYou entered an incorrect character");
                    scan.nextLine();
                    }
                }
                
                System.out.println("\nEnter a contribution");
                while(contribution <= 0){
                    try{
                        contribution = scan.nextDouble();
                        if(contribution <= 0) System.out.println("\nContribution cannot be equal to a negative number or zero");
                    } catch (InputMismatchException e)
                        {
                        System.out.println("\nYou have entered an incorrect character");
                        scan.nextLine();                        
                        }
                }
                
                list.get(indexOfAccount).putMoney(contribution);
                contribution = 0;
                break;
            case "7":
                if(list.size() < 2){
                    System.out.println("\nNo Accounts for withdrawing money, please use 1. or 2. operation for creating\n");
                    break;
                }
                
                indexOfAccount = 0;
                
                while(indexOfAccount < 1 || indexOfAccount > (list.size()-1) ){
                try{
                    System.out.println("\nEnter a number of Account for withdrawing money");
                    indexOfAccount = scan.nextInt();
                    if(indexOfAccount < 1 || indexOfAccount > (list.size()-1) )
                    System.out.println("\nYou entered an incorrect number");
                } catch (InputMismatchException e)
                    {
                    System.out.println("\nYou entered an incorrect character");
                    scan.nextLine();
                    }
                }
                
                System.out.println("\nEnter a payment");
                while(payment <= 0){
                    try{
                        payment = scan.nextDouble();
                        if(payment <= 0) System.out.println("\nPayment cannot be equal to a negative number or zero");
                    } catch (InputMismatchException e)
                        {
                        System.out.println("\nYou have entered an incorrect character");
                        scan.nextLine();                        
                        }
                }
                
                list.get(indexOfAccount).withdrawMoney(payment);
                payment = 0;
                break;
            case "8":
                if(list.size() < 2){
                    System.out.println("\nNo Accounts for charging interest, please use 1. or 2. operation for creating\n");
                    break;
                }
                
                indexOfAccount = 0;
                
                while(indexOfAccount < 1 || indexOfAccount > (list.size()-1) ){
                try{
                    System.out.println("\nEnter a number of Account for charging interest");
                    indexOfAccount = scan.nextInt();
                    if(indexOfAccount < 1 || indexOfAccount > (list.size()-1) )
                    System.out.println("\nYou entered an incorrect number");
                } catch (InputMismatchException e)
                    {
                    System.out.println("\nYou entered an incorrect character");
                    scan.nextLine();
                    }
                }
                
                list.get(indexOfAccount).chargeInterest();
                break;
            case "9":
                if(list.size() < 2){
                    System.out.println("\nNo Accounts for printing, please use 1. or 2. operation for creating\n");
                    break;
                }
                
                for(int i = 1; i < list.size(); i++)
                { 
                    System.out.print("\n" + list.get(i).getTYPE() + " account [" + i + "]: rate = " + list.get(i).getRate() + "; Balance = ");
                    System.out.printf("%.2f", list.get(i).checkMoney());
                }
                break;
            case "10":
                if(list.size() < 2){
                    System.out.println("\nNo Accounts for printing, please use 1. or 2. operation for creating\n");
                    break;
                }
                
                for(int i = 1; i < list.size(); i++) sum += list.get(i).checkMoney();
                System.out.println("\nOverall balance = " + sum);
                sum = 0;
                break;
            default:
            System.out.println("\nYou entered a wrong number");
            }
        
        System.out.println("\n\nDo you want to continue?(1 - Yes; 2 - No)");
        
        flag = 0;
        
        while(flag != 1 && flag !=2)
        {  
            try{
            flag = scan.nextInt();
            if (flag != 1 && flag != 2) System.out.println("You entered an incorrect number. Please, enter 1 if you want to answer Yes or 2 to answer No\n");
            } catch (InputMismatchException e)
                {
                System.out.println("You have entered an incorrect character. Please, enter 1 if you want to answer Yes or 2 to answer No\n");
                scan.nextLine();
                }
        }
        
        if(flag == 2){
            try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("file.txt")))
            {
            outputStream.writeObject(list);
            System.out.println();
            System.out.println("Data was written in file \"file.txt\"");
            }
                catch(Exception ex){
                System.out.println(ex.getMessage());
                } 
        }
        
        scan.nextLine();
        }
    }
    
}
