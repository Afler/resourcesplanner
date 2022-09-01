package solution;

import model.AnchorOperation;
import model.Equipment;
import model.FollowingOperation;
import model.Worker;
import org.optaplanner.core.api.domain.solution.*;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@PlanningSolution
public class JobScheduleSolution {

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "workerRange")
    private List<Worker> workers;

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "equipmentRange")
    private List<Equipment> equipment;

    @ProblemFactProperty
    private LocalDateTime startTime;

    @ProblemFactProperty
    private LocalDateTime endTime;

    @ProblemFactProperty
    private AnchorOperation anchorOperation;

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "followingOperationRange")
    private List<FollowingOperation> followingOperations;

    @PlanningScore
    private HardMediumSoftScore score;

    public JobScheduleSolution() {
    }

    public JobScheduleSolution(List<Worker> workers, List<Equipment> equipment, LocalDateTime startTime, LocalDateTime endTime, AnchorOperation anchorOperation, List<FollowingOperation> operations) {
        this.workers = workers;
        this.equipment = equipment;
        this.startTime = startTime;
        this.endTime = endTime;
        this.anchorOperation = anchorOperation;
        this.followingOperations = operations;
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

    @ValueRangeProvider(id = "anchorOperationRange")
    public List<AnchorOperation> getAnchorOperation() {
        return Collections.singletonList(anchorOperation);
    }

    public void setAnchorOperation(AnchorOperation anchorOperation) {
        this.anchorOperation = anchorOperation;
    }

    public List<FollowingOperation> getFollowingOperations() {
        return followingOperations;
    }

    public void setFollowingOperations(List<FollowingOperation> followingOperations) {
        this.followingOperations = followingOperations;
    }

    public HardMediumSoftScore getScore() {
        return score;
    }

    public void setScore(HardMediumSoftScore score) {
        this.score = score;
    }
}
