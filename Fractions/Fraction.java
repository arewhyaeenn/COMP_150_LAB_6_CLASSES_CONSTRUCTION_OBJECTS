public class Fraction
{
    private int numerator;
    private int denominator;

    /***
     * The first few methods below have been done, but you should still read
     * their descriptions and figure out how they work.
     */

    /**
     * @return the String representation of the fraction
     */
    public String toString()
    {
        return ("[ " + this.numerator + " / " + this.denominator + " ]");
    }

    /**
     * equals method
     * Compares the two fractions (this and other) to see if they are equal.
     * Because we keep Fractions simplified (by simplifying them whenever we change
     * or construct them in their private methods) and store them with a non-negative
     * denominator, it is easy to check if two instances are equal.
     *
     * @param other another Fraction object
     * @return a boolean; true if the two fractions are equal, false otherwise.
     */
    public boolean equals(Fraction other)
    {
        return (this.numerator == other.numerator
                && this.denominator == other.denominator);
    }

    /**
     * Default constructor:
     * Sets numerator and denominator to 1
     */
    public Fraction()
    {
        this.numerator = 1;
        this.denominator = 1;
    }


    /**
     * Overloaded constructor:
     * Allows client to set beginning values for numerator
     * and denominator.
     * The created fraction is NOT simplified,
     * This constructor takes two parameters.
     * Calls mutator methods to validate new values.
     * Then calls the simplify method to remove any common factors
     * between the numerator and denominator.
     *
     * @param numerator   the numerator of the fraction
     * @param denominator the denominator of the fraction
     */
    public Fraction(int numerator, int denominator)
    {
        setNumerator(numerator);
        setDenominator(denominator);
        simplify();
    }

    /**
     * getNumerator method.
     * public. Can be used by methods in other classes.
     *
     * @return the numerator of the fraction
     */
    public int getNumerator()
    {
        return this.numerator;
    }

    /**
     * Mutator method:
     * Allows client to set value of numerator.
     * Private. Only for internal use.
     *
     * @param numerator the new value of numerator
     */
    private void setNumerator(int numerator)
    {
        this.numerator = numerator;
    }

    /**
     * getDenominator method
     *
     * @return the denominator of the fraction
     */
    public int getDenominator()
    {
        return this.denominator;
    }

    /**
     * Mutator method:
     * Set the value of the denominator to the passed in value.
     * If the argument is 0, print out a warning and set the denominator to 1.
     * Warning should be something like "Denominator set to 0... setting to 1 instead."
     * If the denominator is negative, multiply both it and the numerator by -1
     * to ensure that only the numerator is ever negative.
     *
     * @param denominator the new denominator
     */
    private void setDenominator(int denominator)
    {
        if (denominator != 0) {
            this.denominator = denominator;
            if (this.denominator < 0) {
                // make the denominator positive and multiply numerator by negative one
                // to ensure the same value of the overall fraction
                this.denominator = -this.denominator;
                this.numerator = -this.numerator;
            }
        } else {
            this.denominator = 1;
            System.out.println("Denominator cannot be equal to 0.");
            System.out.println("   Denominator set to 1.");
        }
    }

    /**
     * Simplify the fraction calling this method.
     * The greatest common divisor (gcd) of the numerator and denominator
     * can be found with the gcd method (which is already completed at the bottom
     * of this file).
     */
    private void simplify()
    {
        int gcd = gcd(this.numerator, this.denominator);
        this.setNumerator(this.numerator / gcd);
        this.setDenominator(this.denominator / gcd);
    }

    /**
     * reciprocal method
     *
     * @return null, if the reciprocal would have 0 in its denominator.
     * Otherwise, construct and return the reciprocal Fraction.
     */
    public Fraction reciprocal()
    {
        // TODO
        return null; // THIS IS A STUB (it is an incorrect return and should be replaced)
    }

    /**
     * negate method
     *
     * @return the negation of this Fraction (additive negation, so -1 * this fraction)
     */
    public Fraction negate()
    {
        // TODO
        return null; // THIS IS A STUB
    }

    /**
     * add method
     * Adds two Fraction objects and produces the result.
     * Don't forget to simplify the result before returning it.
     *
     * @param other another Fraction object
     * @return a Fraction, the result of this + other, simplified.
     */
    public Fraction add(Fraction other)
    {
        // TODO
        return null; // THIS IS A STUB
    }

    /**
     * subtract method
     * Hint: You already did negate and add...
     *
     * @param other another Fraction object
     * @return a Fraction, the result of this - other, simplified.
     */
    public Fraction subtract(Fraction other)
    {
        // TODO
        // HINT: use the negate and add methods!
        return null; // THIS IS A STUB
    }

    /**
     * multiply method
     * Multiplies two Fraction objects and produces the simplified result (utilizes simplify method)
     *
     * @param other another Fraction object
     * @return a Fraction, the result of this * other, simplified.
     */
    public Fraction multiply(Fraction other)
    {
        // TODO
        return null; // THIS IS A STUB
    }

    /**
     * divide method
     * Hint: You already did reciprocal and multiply...
     *
     * @param other another Fraction object
     * @return a Fraction, the result of this / other simplified.
     */
    public Fraction divide(Fraction other)
    {
        // TODO
        // HINT: use the reciprocal and multiply methods!
        return null; // THIS IS A STUB
    }

    /**
     * Compute the greatest common divisor of numerator and denominator
     *
     * @return the gcd
     */
    private static int gcd(int a, int b)
    {
        if (a < 0)
        {
            a = -a;
        }
        if (b < 0)
        {
            b = -b;
        }
        if (b == 0)
        {
            return a;
        }

        int r = a % b;
        while (r > 0)
        {
            a = b;
            b = r;
            r = a % b;
        }
        return b;
    }
}