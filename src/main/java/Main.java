import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

    public static void main(String[] args) throws IOException {

        SolverFactory<JobScheduleSolution> solutionSolverFactory = SolverFactory.createFromXmlResource("mySolverConfig.xml");
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
        File answerFile = new File("output.json");
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.writeValue(answerFile, solution);
    }

    private static JobScheduleSolution uploadDemoData(String inputStrategy) throws IOException {
        JobScheduleSolution scheduleSolutionFromJson;
        scheduleSolutionFromJson = new ObjectMapper().registerModule(new JavaTimeModule())
                .readValue(new File("input.json"), JobScheduleSolution.class);

        List<LocalDateTime> possibleStartTimeRange = getPossibleStartTimeRange(scheduleSolutionFromJson.getStartTime()
                , scheduleSolutionFromJson.getEndTime());
        if (inputStrategy.equals("A")) Collections.reverse(possibleStartTimeRange);
        scheduleSolutionFromJson.setPossibleStartTimeRange(possibleStartTimeRange);
        List<Operation> operations = scheduleSolutionFromJson.getOperations();
        for (Operation operation : operations) {
            operation.setMaxEndTime(scheduleSolutionFromJson.getEndTime());
        }
        scheduleSolutionFromJson.setOperations(operations);

        return scheduleSolutionFromJson;
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
