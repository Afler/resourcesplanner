import model.Equipment;
import model.Operation;
import model.Worker;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.time.Period;
import java.util.List;

@PlanningSolution
public class JobScheduleSolution {


    @ValueRangeProvider(id = "workerRange")
    @ProblemFactCollectionProperty
    private List<Worker> workers;

    @ValueRangeProvider(id = "equipmentRange")
    @ProblemFactCollectionProperty
    private List<Equipment> equipment;

    @ValueRangeProvider(id = "timeRange")
    private Period timeRange;

    @PlanningEntityCollectionProperty
    private List<Operation> operations;

    @PlanningScore
    private HardSoftScore score;

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    public Period getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(Period timeRange) {
        this.timeRange = timeRange;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }
}
