/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loanaccounthierarchy;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author ryan
 */
public class LoanAccount {
    private double principle;
    private double annualInterestRate;
    private int months;
    
    NumberFormat df = new DecimalFormat("0.00");

    public LoanAccount(double principle, double annualInterestRate, int months) {
        this.principle = principle;
        this.annualInterestRate = annualInterestRate;
        this.months = months;
    }

    public double getPrinciple() {
        return principle;
    }

    public double getAnnualInterestRate() {
        return (annualInterestRate);
         
    }

    public double getMonths() {
        return months;
    }
    
    public double calculateMonthlyPayment() {
        double monthlyInterest = getAnnualInterestRate()/12;
        double monthlyPayment = getPrinciple() * ( (monthlyInterest/100) / (1 - Math.pow(1 + (monthlyInterest/100), -getMonths())));
        return Math.round(monthlyPayment*100.0)/100.0;
    }   
    
    
    
    @Override
    public String toString(){
        return "Principle: $" + 
                getPrinciple() + 
                "\nAnnual Interest Rate: " + 
                getAnnualInterestRate() + 
                "%\nTerm of Loan in Months: " + 
                getMonths() + 
                "\nMonthly Payment: $" + 
                calculateMonthlyPayment();
    
    }
}
