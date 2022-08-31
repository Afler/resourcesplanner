package provider;

import model.FollowingOperation;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

public class MyConstraintProvider implements ConstraintProvider {
    @Override

    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                // Hard constraints
                professionConflict(constraintFactory),              // wrongProfessionForOperation
                equipmentConflict(constraintFactory),               // unavailableEquipmentForChosenWorker
                timeConflict(constraintFactory),                    // timeOverlappingOrOutOfPeriod
                profitReward(constraintFactory)
        };
    }

    private Constraint professionConflict(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(FollowingOperation.class)
                .filter(operation -> !operation.getRequiredProfession().equals(operation.getChosenWorker().getProfession()))
                .penalize("Wrong profession", HardSoftScore.ONE_HARD);
    }

    private Constraint equipmentConflict(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(FollowingOperation.class)
                .filter(operation -> operation.getChosenEquipment() != operation.getRequiredEquipment()
                        || operation.getChosenWorker().getAvailableEquipment()
                        .stream().noneMatch(elem -> elem.equals(operation.getChosenEquipment())))
                .penalize("Wrong equipment", HardSoftScore.ONE_HARD);
    }

    private Constraint timeConflict(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(FollowingOperation.class)
                .filter(operation -> operation.getPreviousOperation().getEndTime().isAfter(operation.getStartTime()))
                .penalize("Time overlapping", HardSoftScore.ONE_HARD);
    }

    private Constraint profitReward(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(FollowingOperation.class)
                .join(FollowingOperation.class,
                        Joiners.lessThan(FollowingOperation::getId),
                        Joiners.lessThan(FollowingOperation::getProfit))
                .penalize("Cheaper solution", HardSoftScore.ONE_SOFT);
    }
}
