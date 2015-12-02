package edu.westga.retirement.model;

/**
 * Model the activity of one year of retirement savings
 * @author Kenji Okamoto
 * @version 20151201
 *
 */
public final class RetirementYear {
    private final int age;
    private final int beginBalance;
    private final int withdrawal;
    private final int socialSecurityIncome;
    private double appreciationRate;
    private final int appreciation;
    private final int endBalance;
    private final int socialSecurityRate;
    /**
     * The age at which social security payments start
     */
    public static final int SOCIAL_SECURITY_START_AGE = 68;

    /**
     * Create a new year of retirement
     * @param age Age at the beginning of the year
     * @param beginBalance The savings balance at the start of the year
     * @param withdrawal How much will be withdrawn from retirement for the year
     * @param socialSecurity Expected yearly social security income starting at age 68
     * @param appreciationRate The investment appreciation rate
     */
    public RetirementYear(int age, int beginBalance, int withdrawal, int socialSecurity, double appreciationRate) {
        if (age < 1) {
            throw new IllegalArgumentException("Age cannot be less than 1");
        }
        this.age = age;
        this.beginBalance = beginBalance;
        this.socialSecurityRate = socialSecurity;
        if (age < SOCIAL_SECURITY_START_AGE) {
            this.socialSecurityIncome = 0;
        } else {
            this.socialSecurityIncome = socialSecurity;
        }
        this.withdrawal = withdrawal;
        this.appreciationRate = appreciationRate;
        if (this.beginBalance > 0) {
            this.appreciation = (int) (this.beginBalance * appreciationRate);
        } else {
            this.appreciation = 0;
        }
        this.endBalance = this.beginBalance - this.withdrawal + this.socialSecurityIncome + this.appreciation;
    }

    /**
     * Get the beginning balance for the year
     * @return The balance at the start of the year
     */
    public int getBeginBalance() {
        return this.beginBalance;
    }

    /**
     * Get the amount withdrawn from the retirement for the year
     * @return The amount withdrawn from the retirement
     */
    public int getWithdrawal() {
        return this.withdrawal;
    }

    /**
     * Get the social security income for the year. For ages under {@value #SOCIAL_SECURITY_START_AGE} it is 0
     * @return The social security income for the year
     */
    public int getSocialSecurity() {
        return this.socialSecurityIncome;
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
     * Get the age at this year
     * @return The age when the year starts
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Get the next RetirementYear data based on this year's data
     * @return The next RetirementYear starting with this year's end balance
     */
    public RetirementYear getNextYear() {
        return new RetirementYear(this.age + 1, this.endBalance, this.withdrawal, this.socialSecurityRate, this.appreciationRate);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.age;
        result = prime * result + this.appreciation;
        long temp;
        temp = Double.doubleToLongBits(this.appreciationRate);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + this.beginBalance;
        result = prime * result + this.endBalance;
        result = prime * result + this.socialSecurityRate;
        result = prime * result + this.socialSecurityIncome;
        result = prime * result + this.withdrawal;
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return (this.hashCode() == obj.hashCode());

    }



}
