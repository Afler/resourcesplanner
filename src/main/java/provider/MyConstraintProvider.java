package provider;

import model.Operation;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;


// 1 Если у операций одинаковый сотрудник, то их время не пересекается
// 2 Наложение во времени операций с одинаковым оборудованием невозможно
// 3 Оборудование операции совпадает с доступным оборудованием её работника
// 4 Профессия операции совпадает с профессией её работника
// 5 Время
public class MyConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                // Hard constraints
                sameWorker(constraintFactory),
                sameEquipment(constraintFactory),
                equipmentMismatch(constraintFactory),
                professionMismatch(constraintFactory),
                timeConflict(constraintFactory),
                notEveryWorkerAssigned(constraintFactory),
                maxProfit(constraintFactory)
        };
    }

    private Constraint notEveryWorkerAssigned(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEachIncludingNullVars(Operation.class)
                .filter(operation -> operation.getChosenWorker() == null)
                .penalize("Worker not assigned", HardMediumSoftScore.ONE_MEDIUM);
    }

    private Constraint maxProfit(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Operation.class)
                .join(Operation.class,
                        Joiners.lessThan(Operation::getProfit))
                .penalize("Cheaper solution", HardMediumSoftScore.ONE_SOFT);
    }

    private Constraint timeConflict(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Operation.class)
                .filter(operation -> operation.getEndTime().isAfter(operation.getMaxEndTime()))
                .penalize("Time period mismatch", HardMediumSoftScore.ONE_HARD);
    }

    private Constraint professionMismatch(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Operation.class)
                .filter(operation -> operation.getRequiredWorkerProfession()
                        != operation.getChosenWorker().getWorkerProfession())
                .penalize("Worker profession mismatch", HardMediumSoftScore.ONE_HARD);
    }

    private Constraint equipmentMismatch(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Operation.class)
                .filter(operation -> operation.getChosenWorker()
                        .getAvailableEquipment()
                        .stream()
                        .noneMatch(equipment -> equipment.getEquipmentModel().equals(operation.getRequiredEquipmentModel())
                        ))
                .penalize("Worker available equipment mismatch", HardMediumSoftScore.ONE_HARD);
    }

    private Constraint sameEquipment(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Operation.class)
                .join(Operation.class,
                        Joiners.equal(Operation::getRequiredEquipmentModel),
                        Joiners.overlapping(Operation::getChosenStartTime, Operation::getEndTime),
                        Joiners.lessThan(Operation::getId))
                .penalize("Equipment time available mismatch", HardMediumSoftScore.ONE_HARD);
    }

    private Constraint sameWorker(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Operation.class)
                .join(Operation.class,
                        Joiners.equal(Operation::getChosenWorker),
                        Joiners.overlapping(Operation::getChosenStartTime, Operation::getEndTime),
                        Joiners.lessThan(Operation::getId))
                .penalize("Worker time available mismatch", HardMediumSoftScore.ONE_HARD);
    }
}
