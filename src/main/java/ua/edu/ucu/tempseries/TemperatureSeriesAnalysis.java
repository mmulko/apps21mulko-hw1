package ua.edu.ucu.tempseries;

import java.util.Arrays;

public class TemperatureSeriesAnalysis {

    private final double[] temperatureSeries;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[] {};
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        // Use Arrays.copy
        this.temperatureSeries = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
    }

    public double average() {
        double averageTemp = 0.0;
        if (temperatureSeries.length > 0) {
            for (double temperature : temperatureSeries) {
                averageTemp = averageTemp + temperature;
            }
        } else {
            throw new IllegalArgumentException();
        }
        return averageTemp/temperatureSeries.length;
    }

    public double deviation() {
        double sum = 0.0, standardDeviation = 0.0;
        int length = temperatureSeries.length;
        if (temperatureSeries.length > 0) {
            for (double num : temperatureSeries) {
                sum += num;
            }
            double mean = sum/length;
            for(double num: temperatureSeries) {
                standardDeviation += Math.pow(num - mean, 2);
            }
            return Math.sqrt(standardDeviation/length);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double min() {
        double minValue;
        if (temperatureSeries.length > 0) {
            minValue = temperatureSeries[0];
            for(int i=1;i<temperatureSeries.length;i++){
                if(temperatureSeries[i] < minValue){
                    minValue = temperatureSeries[i];
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
        return minValue;
    }

    public double max() {
        double maxValue = 0;
        if (temperatureSeries.length > 0) {
            maxValue = temperatureSeries[0];
            for(int i=1;i < temperatureSeries.length;i++){
                if(temperatureSeries[i] > maxValue){
                    maxValue = temperatureSeries[i];
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
        return maxValue;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0.0);
    }

    public double findTempClosestToValue(double tempValue) {
        double UNREAL_START_UP_TEMP = 199999.0;
        double closest = UNREAL_START_UP_TEMP;
        double closestDist = UNREAL_START_UP_TEMP;
        if (temperatureSeries.length > 0) {
            for (double temperature : temperatureSeries) {
                if (!(closest == UNREAL_START_UP_TEMP)) {
                    double temperatureDist = temperature - tempValue;
                    if (Math.abs(temperatureDist) < Math.abs(closestDist)) {
                        closest = temperature;
                        closestDist = temperatureDist;
                    } else if (Math.abs(temperatureDist) == Math.abs(closestDist)) {
                        if (temperatureDist > closestDist) {
                            closest = temperature;
                            closestDist = temperatureDist;
                        }
                    }
                } else {
                    closest = temperature;
                    closestDist = temperature - tempValue;
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        double[] newTempSeries = new double[temperatureSeries.length];
        if (temperatureSeries.length > 0) {
            int count = 0;
            for (double temperature: temperatureSeries) {
                if (temperature < tempValue) {
                    newTempSeries[count] = temperature;
                    count++;
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
        return newTempSeries;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        double[] newTempSeries = new double[temperatureSeries.length];
        if (temperatureSeries.length > 0) {
            int count = 0;
            for (double temperature: temperatureSeries) {
                if (temperature > tempValue) {
                    newTempSeries[count] = temperature;
                    count++;
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
        return newTempSeries;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (temperatureSeries.length > 0) {
            return new TempSummaryStatistics(average(), deviation(), min(), max());
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int addTemps(double... temps) {
        int originalLength = temperatureSeries.length;
        int count = 1;
        double[] newTempSeries = temperatureSeries;
        for (double temp: temps) {
            try {
                newTempSeries[originalLength + count] = temp;
            } catch (Exception e) {
                newTempSeries = new double[newTempSeries.length * 2];
            }
        }
        double sum = 0.0;
        for (double temp: newTempSeries) {
            sum += temp;
        }
        return (int) sum;
    }
}
