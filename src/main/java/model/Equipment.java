package model;

public class Equipment extends Resource {

    private EquipmentModel equipmentModel;

    public Equipment(EquipmentModel equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    public EquipmentModel getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(EquipmentModel equipmentModel) {
        this.equipmentModel = equipmentModel;
    }
}
