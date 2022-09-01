import model.AnchorOperation;
import model.Equipment;
import model.FollowingOperation;
import model.Worker;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;
import provider.MyConstraintProvider;
import solution.JobScheduleSolution;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        SolverFactory<JobScheduleSolution> solutionSolverFactory = SolverFactory.createFromXmlResource("mySolverConfig.xml");
        SolverFactory<JobScheduleSolution> solutionSolverFactory = SolverFactory.create(new SolverConfig()
                .withSolutionClass(JobScheduleSolution.class)
                .withEntityClasses(FollowingOperation.class)
                .withConstraintProviderClass(MyConstraintProvider.class)
                .withTerminationSpentLimit(Duration.ofSeconds(30)));
        Solver<JobScheduleSolution> solver = solutionSolverFactory.buildSolver();

        JobScheduleSolution problem = uploadDemoData();


        solver.solve(problem);

        System.out.println(problem.getFollowingOperations());

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
        LocalDateTime endTime = LocalDate.of(2022, Month.SEPTEMBER, 2).atStartOfDay();

        long id = 0;
        AnchorOperation anchorOperation = new AnchorOperation(id++, "profession_2", equipment.get(2), 10, 2);
        anchorOperation.setStartTime(startTime);
        anchorOperation.setEndTime(startTime);

        List<FollowingOperation> operations = new ArrayList<>();
        operations.add(new FollowingOperation(id++, "profession_1", equipment.get(0), 240, 5));
        operations.add(new FollowingOperation(id++, "profession_2", equipment.get(1), 120, 6));
        operations.add(new FollowingOperation(id++, "profession_3", equipment.get(2), 480, 2));
        operations.add(new FollowingOperation(id++, "profession_1", equipment.get(0), 720, 15));
        operations.add(new FollowingOperation(id++, "profession_2", equipment.get(1), 180, 10));
        operations.add(new FollowingOperation(id++, "profession_3", equipment.get(2), 120, 7));


        return new JobScheduleSolution(workers, equipment, startTime, endTime, anchorOperation, operations);
    }
}
