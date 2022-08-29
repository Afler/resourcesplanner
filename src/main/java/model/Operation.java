package model;

public class Operation {

    private String requiredProfession;
    private String requiredEquipment;
    private int duration;
    private int profit;

    public String getRequiredProfession() {
        return requiredProfession;
    }

    public void setRequiredProfession(String requiredProfession) {
        this.requiredProfession = requiredProfession;
    }

    public String getRequiredEquipment() {
        return requiredEquipment;
    }

    public void setRequiredEquipment(String requiredEquipment) {
        this.requiredEquipment = requiredEquipment;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }
}
