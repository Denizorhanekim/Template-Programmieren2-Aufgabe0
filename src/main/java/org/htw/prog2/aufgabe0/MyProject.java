package org.htw.prog2.aufgabe0;

import org.knowm.xchart.XYChart;
import org.knowm.xchart.SwingWrapper;

import java.util.ArrayList;
import java.util.List;

import java.util.LinkedList;

public class MyProject {
    private static final int MAX_ITERATIONS = 1000;
    /**
     * Calculate root X of a value S according to babylonian algorithm, starting with
     * an initial estimate X(0):
     * <ol>
     *     <li>Estimate the error e(n): e(n)=(S-X(n-1)²)/(2*X(n-1))</li>
     *     <li>Calculate X(n): X(n)=X(n-1)+e(n)</li>
     * </ol>
     * Continue until the estimated error reaches the desired maximum error
     * @param value The value to calculate the root of
     * @param initial The initial value to start the calculation with
     * @param maxError The maximum allowed error
     * @return An array containing the values of all iterations. The last value in the array is the final estimate.
     */
    /*
    public static double[] calculateBabylonianRoot(double value, double initial, double maxerror) {

        if (value <= 0) {
            return new double[] {0};
        }

        if (maxerror <= 0) {
            maxerror = Math.abs(maxerror);
        }

        LinkedList<Double> results = new LinkedList<>();

        double x = initial;
        double error = Double.MAX_VALUE;

        int iterations = 0;
        int maxIterations = 1000;

        while (Math.abs(error) >= maxerror && iterations < maxIterations) {

            results.add(x);

            error = (value - x * x) / (2 * x);
            x = x + error;

            iterations++;
        }

        results.add(x);

        double[] array = new double[results.size()];
        for (int i = 0; i < results.size(); i++) {
            array[i] = results.get(i);
        }

        return array;
    }
     */

      // UPDATED CLEANER VERSION

    public static double[] calculateBabylonianRoot(
            double value,
            double initial,
            double maxError
    ){


        if(value<=0){
            return new double[] {0};
        }
        maxError=Math.abs(maxError);
        // Prevent division by zero
        if (initial == 0) {
            initial = value / 2.0;
        }
        List<Double> results = new ArrayList<>();

        double x=initial;
        double error;

        for (int i =0; i< MAX_ITERATIONS; i++){
            results.add(x);

            error=(value-x*x) / (2*x);
            if(Math.abs(error)<maxError) {
                break;
            }
            x+=error;
        }
//Add final approximation
        results.add(x);

        return results.stream()
                .mapToDouble(Double::doubleValue)
                .toArray();
    }


    public static void plotData(double[] values) {
        XYChart chart = new XYChart(500, 500);
        chart.addSeries("Data", values);
        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        plotData(calculateBabylonianRoot(74821, 5, 0.1));
    }
}
