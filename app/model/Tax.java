package model;

public class Tax {
    private double totalTax;

    public Tax(double totalTax) {
        this.totalTax = totalTax;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }
}
