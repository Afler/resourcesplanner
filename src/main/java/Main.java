import model.Equipment;
import model.Operation;
import model.Worker;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import solution.JobScheduleSolution;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SolverFactory<JobScheduleSolution> solutionSolverFactory = SolverFactory.createFromXmlResource("mySolverConfig.xml");
        Solver<JobScheduleSolution> solver = solutionSolverFactory.buildSolver();

        JobScheduleSolution problem = uploadDemoData();

        solver.solve(problem);

        System.out.println(problem.getOperations());

    }

    private static JobScheduleSolution uploadDemoData() {
        List<Equipment> equipment = new ArrayList<>();
        equipment.add(new Equipment("model_1"));
        equipment.add(new Equipment("model_2"));
        equipment.add(new Equipment("model_3"));

        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker("profession_1", new ArrayList<>(List.of(equipment.get(0)))));
        workers.add(new Worker("profession_2", new ArrayList<>(List.of(equipment.get(1)))));
        workers.add(new Worker("profession_3", new ArrayList<>(List.of(equipment.get(0), equipment.get(2)))));

        LocalDateTime startTime = LocalDate.of(2022, Month.SEPTEMBER, 1).atStartOfDay();
        LocalDateTime endTime = LocalDate.of(2022, Month.SEPTEMBER, 30).atTime(23, 59);

        List<Operation> operations = new ArrayList<>();
        long id = 0;
        operations.add(new Operation(id++, "profession_1", equipment.get(0), 90, 5));
        operations.add(new Operation(id++, "profession_2", equipment.get(1), 60, 6));
        operations.add(new Operation(id++, "profession_3", equipment.get(2), 30, 2));
        operations.add(new Operation(id++, "profession_1", equipment.get(0), 15, 1));
        operations.add(new Operation(id++, "profession_2", equipment.get(1), 180, 10));
        operations.add(new Operation(id++, "profession_3", equipment.get(2), 120, 7));

        return new JobScheduleSolution(workers, equipment, startTime, endTime, operations);
    }
}
