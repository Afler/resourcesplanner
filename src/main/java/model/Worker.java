package model;

import java.util.ArrayList;
import java.util.List;

public class Worker extends Resource {

    private String profession;

    private final List<Equipment> availableEquipment = new ArrayList<>();

    public List<Equipment> getAvailableEquipment() {
        return availableEquipment;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
