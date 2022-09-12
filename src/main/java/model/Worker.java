package model;

import java.util.List;

public class Worker extends Resource {

    private String name;
    private String workerProfession;
    private List<Equipment> availableEquipment;

    public List<Equipment> getAvailableEquipment() {
        return availableEquipment;
    }

    public Worker() {

    }

    public Worker(String name, String workerProfession, List<Equipment> availableEquipment) {
        this.name = name;
        this.workerProfession = workerProfession;
        this.availableEquipment = availableEquipment;
    }

    public void setAvailableEquipment(List<Equipment> availableEquipment) {
        this.availableEquipment = availableEquipment;
    }

    public String getWorkerProfession() {
        return workerProfession;
    }

    public void setWorkerProfession(String workerProfession) {
        this.workerProfession = workerProfession;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormattedEquipmentModelsString() {
        StringBuilder ans = new StringBuilder();
        for (Equipment equipment : availableEquipment) {
            ans.append(equipment.getEquipmentModel()).append(" ");
        }
        return ans.toString().trim();
    }
}
