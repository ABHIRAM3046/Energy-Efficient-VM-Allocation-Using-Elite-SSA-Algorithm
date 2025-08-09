# Energy-Efficient VM Allocation Using Elite SSA Algorithm

## 📌 Overview
This project implements an **energy-efficient virtual machine (VM) allocation strategy** in cloud data centers using **metaheuristic optimization algorithms** — specifically the **Sparrow Search Algorithm (SSA)**, **Elite Sparrow Search Algorithm (E-SSA)**, and **Whale Optimization Algorithm (WOA)**.  
The proposed method integrates these algorithms with **Minimum Utilization (MU)** and **Minimum Migration Time (MMT)** policies to reduce **energy consumption**, **SLA violations**, and **VM migration overhead**.

All simulations are conducted using **CloudSim 3.0.3** with real-world workload traces from **PlanetLab**.

---

## ✨ Key Features
- **Metaheuristic VM Allocation**
  - Sparrow Search Algorithm (SSA)
  - Elite Sparrow Search Algorithm (E-SSA)
  - Whale Optimization Algorithm (WOA)
- **Selection Policies**
  - Minimum Utilization (MU)
  - Minimum Migration Time (MMT)
- **Performance Metrics**
  - Power Consumption (kWh)
  - SLA Violation (%)
  - VM Migration Count
  - Execution Time
- **Baseline Comparison**
  - Dynamic Voltage and Frequency Scaling (DVFS)

---

## 📊 Research Contribution
From the experimental analysis:
- **E-SSA + MMT** achieved:
  - Lowest **Power Consumption**: **202.59 kWh**
  - Low **SLA Violation**: **0.00405%**
  - Reduced **VM Migrations**: **29,355**
  - Competitive execution time for real-time usage.
- **SSA + MMT** had the **lowest SLA violation** at **0.00334%**.
- DVFS ensured 0% SLA violation but consumed the most energy (**803.91 kWh**), making it unsuitable for dynamic workloads.

---

## 🛠️ Tech Stack
- **Language:** Java (CloudSim-based)
- **Simulation Framework:** CloudSim 3.0.3
- **Platform:** Windows / Linux
- **Processor:** AMD Ryzen 3 (tested)
- **RAM:** 12 GB

---

## 📂 Repository Structure
```
📦 Energy-Efficient-VM-Allocation-Using-Elite-SSA-Algorithm
 ┣ 📜 README.md
 ┣ 📜 LICENSE
 ┣ 📂 src
 ┃ ┣ 📜 Elitesparrow_mmt.java
 ┃ ┣ 📜 PowerVmAllocationPolicyMigrationEliteSparrowSearch.java
 ┃ ┣ 📜 PowerVmAllocationPolicyMigrationSparrowSearch.java
 ┃ ┣ 📜 PowerVmAllocationPolicyMigrationWhaleOptimization.java
 ┃ ┣ 📜 PowerVmSelectionPolicyMinimumMigrationTime.java
 ┃ ┣ 📜 PowerVmSelectionPolicyMinimumUtilization.java
 ┃ ┣ 📜 elitesparrow.java
 ┃ ┣ 📜 sparrowMu.java
 ┃ ┣ 📜 sparrowmmt.java
 ┃ ┣ 📜 whalemmt.java
 ┃ ┗ 📜 whalemu.java
 ┗ 📂 results
   ┗ 📊 Graphs
```

---

## 🚀 How to Run
1. **Clone the Repository**
   ```bash
   git clone https://github.com/ABHIRAM3046/Energy-Efficient-VM-Allocation-Using-Elite-SSA-Algorithm.git
   cd Energy-Efficient-VM-Allocation-Using-Elite-SSA-Algorithm
   ```

2. **Set Up CloudSim**
   - Download [CloudSim 3.0.3](https://github.com/Cloudslab/cloudsim).
   - Place CloudSim JAR files in the `lib` folder.

3. **Compile the Code**
   ```bash
   javac -cp ".;lib/*" src/*.java
   ```

4. **Run the Simulation**
   ```bash
   java -cp ".;lib/*;src" CloudSimMain
   ```

5. **View Results**
   - Check `results/` for graphs and performance metrics.

---

## Algorithm Workflow
![Workflow](/Results/fig1.jpg)
## 📈 Results Summary

| Algorithm | Policy | SLA Violation (%) | Power (kWh) | VM Migrations | Exec Time (s) |
|-----------|--------|------------------|-------------|---------------|---------------|
| DVFS      | N/A    | 0.00000          | 803.91      | 0             | -             |
| SSA       | MMT    | **0.00334**      | 206.74      | 27,541        | 0.20164       |
| E-SSA     | MMT    | 0.00405          | **202.59**  | 29,355        | 0.24971       |
| WOA       | MMT    | 0.00492          | 217.01      | 32,561        | 0.23129       |

---

## References
This project is based on the research paper:  
**"Energy-Efficient VM Allocation Using Elite Sparrow Search Algorithm and MU/MMT Policies"**  
*Abhiram Varma Jampana, Amarnath Kolla, M. Manickam — SRM Institute of Science and Technology (2025)*

---

## Future Work
- Develop a **hybrid optimization algorithm** combining E-SSA, SSA, and WOA.
- Evaluate with **real-world cloud deployments**.
- Incorporate **security-aware VM allocation**.

---

## 📜 License
This project is licensed under the MIT License.
