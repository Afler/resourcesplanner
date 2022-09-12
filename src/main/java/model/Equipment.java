package model;

public class Equipment extends Resource {

    private String equipmentModel;

    public Equipment() {

    }

    public Equipment(String equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    public String getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(String equipmentModel) {
        this.equipmentModel = equipmentModel;
    }
}
