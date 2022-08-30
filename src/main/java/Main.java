import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

public class Main {

    public static void main(String[] args) {

        SolverFactory<JobScheduleSolution> solutionSolverFactory = SolverFactory.createFromXmlResource("mySolverConfig.xml");
        Solver<JobScheduleSolution> solver = solutionSolverFactory.buildSolver();

    }
}
