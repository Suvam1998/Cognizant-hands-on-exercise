package Exercise7_FinancialForecasting;

import java.util.HashMap;
import java.util.Map;

/**
 * Financial forecasting tool that predicts a future value from a present value
 * and a growth rate, using recursion.
 *
 * futureValue(P, r, n) = P * (1 + r)^n
 * Recurrence:
 *   futureValue(P, r, 0) = P                         (base case)
 *   futureValue(P, r, n) = futureValue(P, r, n-1) * (1 + r)
 */
public class FinancialForecasting {

    /**
     * Naive recursion. Each call spawns one more call.
     * Time: O(n), Space: O(n) call-stack depth.
     */
    public static double futureValue(double presentValue, double growthRate, int years) {
        if (years == 0) {                       // base case
            return presentValue;
        }
        return futureValue(presentValue, growthRate, years - 1) * (1 + growthRate);
    }

    /**
     * Forecast using a series of DIFFERENT annual growth rates
     * (rates[i] applies to year i). Demonstrates recursion over an array.
     * Time: O(n).
     */
    public static double futureValueVariable(double presentValue, double[] rates, int year) {
        if (year == 0) {
            return presentValue;
        }
        return futureValueVariable(presentValue, rates, year - 1) * (1 + rates[year - 1]);
    }

    /**
     * Memoized version. Caches futureValue for each 'years' so repeated queries
     * on the same (P, r) reuse work instead of recomputing.
     * Amortised O(1) per already-seen year after the first O(n) fill.
     */
    private final Map<Integer, Double> memo = new HashMap<>();

    public double futureValueMemoized(double presentValue, double growthRate, int years) {
        if (years == 0) {
            return presentValue;
        }
        if (memo.containsKey(years)) {
            return memo.get(years);
        }
        double result = futureValueMemoized(presentValue, growthRate, years - 1) * (1 + growthRate);
        memo.put(years, result);
        return result;
    }

    /**
     * Iterative equivalent — the usual optimization when recursion depth (n)
     * could be large enough to risk a StackOverflowError.
     * Time: O(n), Space: O(1).
     */
    public static double futureValueIterative(double presentValue, double growthRate, int years) {
        double value = presentValue;
        for (int i = 0; i < years; i++) {
            value *= (1 + growthRate);
        }
        return value;
    }

    public static void main(String[] args) {
        double principal = 10000.0;   // present value
        double rate = 0.08;           // 8% annual growth
        int years = 5;

        System.out.printf("Present value: %.2f, rate: %.0f%%, years: %d%n",
                principal, rate * 100, years);

        System.out.printf("%nRecursive future value  : %.2f%n",
                futureValue(principal, rate, years));
        System.out.printf("Iterative future value  : %.2f%n",
                futureValueIterative(principal, rate, years));

        FinancialForecasting tool = new FinancialForecasting();
        System.out.printf("Memoized future value   : %.2f%n",
                tool.futureValueMemoized(principal, rate, years));
        System.out.printf("Memoized (cached) year 5: %.2f%n",
                tool.futureValueMemoized(principal, rate, years)); // served from cache

        double[] variableRates = {0.05, 0.07, 0.06, 0.09, 0.08};
        System.out.printf("%nVariable-rate forecast  : %.2f%n",
                futureValueVariable(principal, variableRates, years));

        System.out.println("\nYear-by-year projection (constant 8%):");
        for (int y = 0; y <= years; y++) {
            System.out.printf("  Year %d: %.2f%n", y, futureValue(principal, rate, y));
        }
    }
}
