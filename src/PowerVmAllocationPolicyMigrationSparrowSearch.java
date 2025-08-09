package org.cloudbus.cloudsim.power;

import java.util.Arrays;
import java.util.List;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Vm;

/**
 * The Sparrow Search Algorithm (SSA) based VM allocation policy.
 * 
 * SSA optimizes VM placement in cloud data centers by selecting the best host
 * based on CPU and memory utilization.
 * 
 * @author AbhiRam
 */
public class PowerVmAllocationPolicyMigrationSparrowSearch extends PowerVmAllocationPolicyMigrationAbstract {

    /** The safety parameter. */
    private double safetyParameter = 0;

    /** The fallback VM allocation policy. */
    private PowerVmAllocationPolicyMigrationAbstract fallbackVmAllocationPolicy;

    /**
     * Instantiates a new SSA-based VM allocation policy.
     * 
     * @param hostList the host list
     * @param vmSelectionPolicy the VM selection policy
     * @param safetyParameter the safety parameter
     */
    public PowerVmAllocationPolicyMigrationSparrowSearch(
            List<? extends Host> hostList,
            PowerVmSelectionPolicy vmSelectionPolicy,
            double safetyParameter,
            PowerVmAllocationPolicyMigrationAbstract fallbackVmAllocationPolicy) {
        super(hostList, vmSelectionPolicy);
        setSafetyParameter(safetyParameter);
        setFallbackVmAllocationPolicy(fallbackVmAllocationPolicy);
    }

    /**
     * Determines if a host is over-utilized based on SSA-based dynamic threshold.
     * 
     * @param host the host
     * @return true if over-utilized, false otherwise
     */
    @Override
    protected boolean isHostOverUtilized(PowerHost host) {
        if (!(host instanceof PowerHostUtilizationHistory)) {
            return getFallbackVmAllocationPolicy().isHostOverUtilized(host);
        }

        PowerHostUtilizationHistory _host = (PowerHostUtilizationHistory) host;
        double upperThreshold = 0;
        try {
            upperThreshold = 1 - getSafetyParameter() * getHostUtilizationSparrow(_host);
        } catch (IllegalArgumentException e) {
            return getFallbackVmAllocationPolicy().isHostOverUtilized(host);
        }
        addHistoryEntry(host, upperThreshold);
        double totalRequestedMips = 0;
        for (Vm vm : host.getVmList()) {
            totalRequestedMips += vm.getCurrentRequestedTotalMips();
        }
        double utilization = totalRequestedMips / host.getTotalMips();
        return utilization > upperThreshold;
    }

    /**
     * Calculates the dynamic host utilization threshold using Sparrow Search Algorithm.
     * 
     * @param host the host
     * @return the calculated SSA-based threshold
     */
    protected double getHostUtilizationSparrow(PowerHostUtilizationHistory host) throws IllegalArgumentException {
        double[] data = host.getUtilizationHistory();
        if (countNonZeroBeginning(data) >= 12) { // At least 12 valid values required
            return sparrowSearch(data);
        }
        return calculateMean(data);  // Use mean if not enough data
    }

    /**
     * Implements Sparrow Search Algorithm (SSA) for threshold calculation.
     * 
     * @param data utilization history
     * @return computed threshold value
     */
    private double sparrowSearch(double[] data) {
        double leader = calculateMedian(data);
        double stepSize = 0.1;
        
        for (int i = 0; i < data.length; i++) {
            double randFactor = Math.random() - 0.5;
            double updatedValue = leader + stepSize * randFactor;
            leader = Math.min(leader, updatedValue); // Always select the best value
        }
        return leader;
    }

    /**
     * Calculates the median value of an array.
     * 
     * @param data input array
     * @return median value
     */
    private double calculateMedian(double[] data) {
        Arrays.sort(data);
        int middle = data.length / 2;
        if (data.length % 2 == 0) {
            return (data[middle - 1] + data[middle]) / 2.0;
        } else {
            return data[middle];
        }
    }

    /**
     * Calculates the mean of an array.
     * 
     * @param data input array
     * @return mean value
     */
    private double calculateMean(double[] data) {
        double sum = 0;
        int count = 0;
        for (double value : data) {
            if (value > 0) { // Ignore zero values
                sum += value;
                count++;
            }
        }
        return count == 0 ? 0 : sum / count;
    }

    /**
     * Counts the number of non-zero values at the beginning of an array.
     * 
     * @param data input array
     * @return count of non-zero values at the beginning
     */
    private int countNonZeroBeginning(double[] data) {
        int count = 0;
        for (double value : data) {
            if (value == 0) break;
            count++;
        }
        return count;
    }

    /**
     * Sets the safety parameter.
     * 
     * @param safetyParameter the new safety parameter
     */
    protected void setSafetyParameter(double safetyParameter) {
        if (safetyParameter < 0) {
            Log.printLine("The safety parameter cannot be less than zero. The passed value is: "
                    + safetyParameter);
            System.exit(0);
        }
        this.safetyParameter = safetyParameter;
    }

    /**
     * Gets the safety parameter.
     * 
     * @return the safety parameter
     */
    protected double getSafetyParameter() {
        return safetyParameter;
    }

    /**
     * Sets the fallback VM allocation policy.
     * 
     * @param fallbackVmAllocationPolicy the new fallback VM allocation policy
     */
    public void setFallbackVmAllocationPolicy(
            PowerVmAllocationPolicyMigrationAbstract fallbackVmAllocationPolicy) {
        this.fallbackVmAllocationPolicy = fallbackVmAllocationPolicy;
    }

    /**
     * Gets the fallback VM allocation policy.
     * 
     * @return the fallback VM allocation policy
     */
    public PowerVmAllocationPolicyMigrationAbstract getFallbackVmAllocationPolicy() {
        return fallbackVmAllocationPolicy;
    }
}
