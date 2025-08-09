package org.cloudbus.cloudsim.examples.power.planetlab;

import java.io.IOException;

/**
 * A simulation of a heterogeneous power-aware data center using the Elite Sparrow Search Algorithm (ESSA)
 * for dynamic VM allocation optimization.
 * 
 * This example uses a real PlanetLab workload: 20110303.
 * @author AbhiRam
 * @since April 2, 2025
 */
public class elitesparrow {

    /**
     * The main method.
     * 
     * @param args the arguments
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void main(String[] args) throws IOException {
        boolean enableOutput = false;
        boolean outputToFile = false;
        String inputFolder = elitesparrow.class.getClassLoader().getResource("workload/planetlab").getPath();
        String outputFolder = "output";
        String workload = "20110303"; // PlanetLab workload
        String vmAllocationPolicy = "elite-sparrow"; // Elite Sparrow Search Algorithm (ESSA) for VM allocation
        String vmSelectionPolicy = "mu"; // Example selection policy
        String parameter = "1.1"; // Custom parameter for tuning ESSA

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
