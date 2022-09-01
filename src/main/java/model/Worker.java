package model;

import java.util.List;

public class Worker extends Resource {

    private WorkerProfession workerProfession;

    private List<Equipment> availableEquipment;

    public List<Equipment> getAvailableEquipment() {
        return availableEquipment;
    }

    public Worker(WorkerProfession workerProfession, List<Equipment> availableEquipment) {
        this.workerProfession = workerProfession;
        this.availableEquipment = availableEquipment;
    }

    public void setAvailableEquipment(List<Equipment> availableEquipment) {
        this.availableEquipment = availableEquipment;
    }

    public WorkerProfession getWorkerProfession() {
        return workerProfession;
    }

    public void setWorkerProfession(WorkerProfession workerProfession) {
        this.workerProfession = workerProfession;
    }


}
