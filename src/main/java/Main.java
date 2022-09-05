import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;
import provider.MyConstraintProvider;
import solution.JobScheduleSolution;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SolverFactory<JobScheduleSolution> solutionSolverFactory = SolverFactory.create(new SolverConfig()
                .withSolutionClass(JobScheduleSolution.class)
                .withEntityClasses(Operation.class)
                .withConstraintProviderClass(MyConstraintProvider.class)
                .withTerminationSpentLimit(Duration.ofSeconds(30)));
        Solver<JobScheduleSolution> solver = solutionSolverFactory.buildSolver();

        String inputStrategy;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Choose a strategy: [J]ust in time or [A]s soon as possible");
            inputStrategy = scanner.nextLine();
        } while (!(inputStrategy.equals("J") || inputStrategy.equals("A")));
        scanner.close();

        JobScheduleSolution problem = uploadDemoData(inputStrategy);
        JobScheduleSolution solution = solver.solve(problem);
        try {
            writeAnswerToFile(solution);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeAnswerToFile(JobScheduleSolution solution) throws IOException {
        File answerFile = new File("src/main/resources/ans.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(answerFile, solution);
    }

    private static JobScheduleSolution uploadDemoData(String inputStrategy) {
        List<Equipment> equipment = new ArrayList<>();
        equipment.add(new Equipment(EquipmentModel.MODEL_1));
        equipment.add(new Equipment(EquipmentModel.MODEL_2));
        equipment.add(new Equipment(EquipmentModel.MODEL_3));

        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker("Ford", WorkerProfession.FIRST, new ArrayList<>(List.of(equipment.get(0), equipment.get(1)))));
        workers.add(new Worker("Steve", WorkerProfession.SECOND, new ArrayList<>(List.of(equipment.get(1), equipment.get(2)))));
        workers.add(new Worker("Travis", WorkerProfession.THIRD, new ArrayList<>(List.of(equipment.get(0), equipment.get(2)))));

        LocalDateTime startTime = LocalDate.of(2022, Month.SEPTEMBER, 1).atStartOfDay();
        LocalDateTime endTime = LocalDate.of(2022, Month.SEPTEMBER, 5).atStartOfDay();
        List<LocalDateTime> possibleStartTimeRange = getPossibleStartTimeRange(startTime, endTime);
        if (inputStrategy.equals("A")) Collections.reverse(possibleStartTimeRange);

        long id = 0;
        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation(id++, WorkerProfession.THIRD, equipment.get(0).getEquipmentModel(), 1, 10, endTime));
        operations.add(new Operation(id++, WorkerProfession.FIRST, equipment.get(1).getEquipmentModel(), 2, 20, endTime));
        operations.add(new Operation(id++, WorkerProfession.SECOND, equipment.get(2).getEquipmentModel(), 3, 30, endTime));
//        operations.add(new Operation(id++, WorkerProfession.FIRST, equipment.get(1).getEquipmentModel(), 4, 40, endTime));
//        operations.add(new Operation(id++, WorkerProfession.THIRD, equipment.get(2).getEquipmentModel(), 5, 50, endTime));
//        operations.add(new Operation(id++, WorkerProfession.THIRD, equipment.get(0).getEquipmentModel(), 6, 60, endTime));

        return new JobScheduleSolution(startTime, endTime, possibleStartTimeRange, workers, equipment, operations);
    }

    private static List<LocalDateTime> getPossibleStartTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        List<LocalDateTime> possibleStartTimeRange = new ArrayList<>();

        for (int i = 0; i < duration.toHours(); i++) {
            possibleStartTimeRange.add(startTime.plusHours(i));
        }
        return possibleStartTimeRange;
    }
}
