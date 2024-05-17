/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loanaccounthierarchy;

/**
 *
 * @author ryan
 */
public class PrimaryMortgage extends LoanAccount{
    
    private double PMIMonthlyAmount;
    private Address address;
    
    public PrimaryMortgage(double principle, double annualInterestRate, int months, double PMIMonthlyAmount, Address address) {
        super(principle, annualInterestRate, months);
        this.address = address;
        this.PMIMonthlyAmount = PMIMonthlyAmount;
            
    }
    
    @Override
    public String toString(){
        return "\nPrimary Mortgage Loan with: \n" + 
                super.toString() + 
                "\nPMI Monthly Amount: $" + 
                this.PMIMonthlyAmount + 
                address.toString() + 
                "\n";
    }
    
}
