package listener;

import model.FollowingOperation;
import model.Operation;
import org.optaplanner.core.api.domain.variable.VariableListener;
import org.optaplanner.core.api.score.director.ScoreDirector;
import solution.JobScheduleSolution;

public class OperationTimeUpdatingListener implements VariableListener<JobScheduleSolution, FollowingOperation> {

    private void updateOperationTime(FollowingOperation followingOperation) {

        Operation previousOperation = followingOperation.getPreviousOperation();
        if (previousOperation != null && previousOperation.getStartTime() != null
                && previousOperation.getEndTime() != null) {
            followingOperation.setStartTime(previousOperation.getEndTime());
            followingOperation.setEndTime(followingOperation.getStartTime().plusMinutes(followingOperation.getDuration()));
        }
    }

    @Override
    public void beforeVariableChanged(ScoreDirector<JobScheduleSolution> scoreDirector, FollowingOperation followingOperation) {

    }

    @Override
    public void afterVariableChanged(ScoreDirector<JobScheduleSolution> scoreDirector, FollowingOperation followingOperation) {
        updateOperationTime(followingOperation);
    }

    @Override
    public void beforeEntityAdded(ScoreDirector<JobScheduleSolution> scoreDirector, FollowingOperation followingOperation) {

    }

    @Override
    public void afterEntityAdded(ScoreDirector<JobScheduleSolution> scoreDirector, FollowingOperation followingOperation) {
        updateOperationTime(followingOperation);
    }

    @Override
    public void beforeEntityRemoved(ScoreDirector<JobScheduleSolution> scoreDirector, FollowingOperation followingOperation) {

    }

    @Override
    public void afterEntityRemoved(ScoreDirector<JobScheduleSolution> scoreDirector, FollowingOperation followingOperation) {

    }
}
