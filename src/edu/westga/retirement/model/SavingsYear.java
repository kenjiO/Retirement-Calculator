package edu.westga.retirement.model;

/**
 * Model the activity of one year of retirement savings
 * @author Kenji Okamoto
 * @version 20151120
 *
 */
public final class SavingsYear {
    private final int beginBalance;
    private final int contribution;
    private double appreciationRate;
    private final int appreciation;
    private final int endBalance;

    /**
     * Create a new year of savings
     * @param beginBalance The savings balance at the start of the year
     * @param contribution The contribution amount for the year
     * @param appreciationRate The investment appreciation rate
     */
    public SavingsYear(int beginBalance, int contribution, double appreciationRate) {
        this.beginBalance = beginBalance;
        this.contribution = contribution;
        this.appreciationRate = appreciationRate;
        this.appreciation = (int) (this.beginBalance * appreciationRate);
        this.endBalance = this.beginBalance + this.contribution + this.appreciation;
    }

    /**
     * Get the beginning balance for the year
     * @return The balance at the start of the year
     */
    public int getBeginBalance() {
        return this.beginBalance;
    }

    /**
     * Get the contribution made for the year
     * @return The contribution made for the year
     */
    public int getContribution() {
        return this.contribution;
    }

    /**
     * Get the appreciation on the savings for the year
     * @return The amount the savings grew from appreciation
     */
    public int getAppreciation() {
        return this.appreciation;
    }

    /**
     * Get the end balance for the year
     * @return The ending balance for the year
     */
    public int getEndBalance() {
        return this.endBalance;
    }

    /**
     * Get the next SavingsYear data based on this year's data
     * @return The next SavingYear starting with this year's end balance
     */
    public SavingsYear getNextYear() {
        return new SavingsYear(this.endBalance, this.contribution, this.appreciationRate);
    }

}
