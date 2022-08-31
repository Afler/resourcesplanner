package solution;

import model.Equipment;
import model.Operation;
import model.Worker;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.time.LocalDateTime;
import java.util.List;

@PlanningSolution
public class JobScheduleSolution {


    @ValueRangeProvider(id = "workerRange")
    @ProblemFactCollectionProperty
    private List<Worker> workers;

    @ValueRangeProvider(id = "equipmentRange")
    @ProblemFactCollectionProperty
    private List<Equipment> equipment;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @PlanningEntityCollectionProperty
    private List<Operation> operations;

    @PlanningScore
    private HardSoftScore score;

    public JobScheduleSolution(List<Worker> workers, List<Equipment> equipment, LocalDateTime startTime, LocalDateTime endTime, List<Operation> operations) {
        this.workers = workers;
        this.equipment = equipment;
        this.startTime = startTime;
        this.endTime = endTime;
        this.operations = operations;
    }

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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
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
