package model;

import java.util.List;

public class Worker extends Resource {

    private String profession;

    private List<Equipment> availableEquipment;

    public List<Equipment> getAvailableEquipment() {
        return availableEquipment;
    }

    public Worker(String profession, List<Equipment> availableEquipment) {
        this.profession = profession;
        this.availableEquipment = availableEquipment;
    }

    public void setAvailableEquipment(List<Equipment> availableEquipment) {
        this.availableEquipment = availableEquipment;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }


}
