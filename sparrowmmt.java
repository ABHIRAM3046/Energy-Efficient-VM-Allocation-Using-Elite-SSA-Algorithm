package org.cloudbus.cloudsim.examples.power.planetlab;

import java.io.IOException;

/**
 * A simulation of a heterogeneous power-aware data center using the Sparrow Search Algorithm (SSA) 
 * for dynamic VM allocation optimization. 
 * 
 * This example uses a real PlanetLab workload: 20110303. 
 * @author AbhiRam
 * @since April 2, 2025
 */
public class sparrowmmt {

    /**
     * The main method.
     * 
     * @param args the arguments
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void main(String[] args) throws IOException {
        boolean enableOutput = false;
        boolean outputToFile = false;
        String inputFolder = sparrowmmt.class.getClassLoader().getResource("workload/planetlab").getPath();
        String outputFolder = "output";
        String workload = "20110303"; // PlanetLab workload
        String vmAllocationPolicy = "sparrow"; // Sparrow Search Algorithm (SSA) for VM allocation
        String vmSelectionPolicy = "mmt"; // Example selection policy
        String parameter = "1.5"; // Custom parameter for tuning SSA

        new PlanetLabRunner(
                enableOutput,
                outputToFile,
                inputFolder,
                outputFolder,
                workload,
                vmAllocationPolicy,
                vmSelectionPolicy,
                parameter);
    }
}
