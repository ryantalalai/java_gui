/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loanaccounthierarchy;

import java.util.ArrayList;

/**
 *
 * @author ryan
 */
public class Customer {
    
    private String firstName;
    private String lastName;
    private String SSN;
    private ArrayList<LoanAccount> loanAccounts;

    public Customer(String firstName, String lastName, String SSN) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.SSN = SSN;
        this.loanAccounts = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSSN() {
        return SSN;
    }
    
    public void addLoanAccount(LoanAccount account) {
        loanAccounts.add(account);
    }
    
    public String printMonthlyReport() {
        String string = "Account Report for Customer: "
                + getFirstName() + " " + getLastName() + " " + "with SSN" + " " + getSSN() + "\n";   
        
        for(int a = 0; a < loanAccounts.size(); a++) {
            string += "\n" + loanAccounts.get(a).toString();
        
        }
        return string;
        
    }
    
}
