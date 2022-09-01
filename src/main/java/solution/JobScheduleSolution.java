package solution;

import model.Equipment;
import model.Operation;
import model.Worker;
import org.optaplanner.core.api.domain.solution.*;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;

import java.time.LocalDateTime;
import java.util.List;

@PlanningSolution
public class JobScheduleSolution {

    @ProblemFactProperty
    private LocalDateTime startTime;

    @ProblemFactProperty
    private LocalDateTime endTime;

    @ProblemFactProperty
    @ValueRangeProvider(id = "startTimeRange")
    private List<LocalDateTime> possibleStartTimeRange;

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "workerRange")
    private List<Worker> workers;

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "equipmentRange")
    private List<Equipment> equipment;

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "operationRange")
    private List<Operation> operations;

    @PlanningScore
    private HardMediumSoftScore score;

    public JobScheduleSolution() {
    }

    public JobScheduleSolution(LocalDateTime startTime, LocalDateTime endTime, List<LocalDateTime> possibleStartTimeRange, List<Worker> workers, List<Equipment> equipment, List<Operation> operations) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.possibleStartTimeRange = possibleStartTimeRange;
        this.workers = workers;
        this.equipment = equipment;
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

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public HardMediumSoftScore getScore() {
        return score;
    }

    public void setScore(HardMediumSoftScore score) {
        this.score = score;
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

    public List<LocalDateTime> getPossibleStartTimeRange() {
        return possibleStartTimeRange;
    }

    public void setPossibleStartTimeRange(List<LocalDateTime> possibleStartTimeRange) {
        this.possibleStartTimeRange = possibleStartTimeRange;
    }
}
