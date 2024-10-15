package ManageResortRequirement.model;

public class Villa extends Facility {
    private String standardRoom;
    private String description;
    private int numberOfFloors;
    private int maxPeople;
    private String rentalType;

    public Villa(String id, String name, double area, double cost, String standardRoom, String description, int numberOfFloors, int maxPeople, String rentalType) {
        super(id, name, area, cost);
        this.standardRoom = standardRoom;
        this.description = description;
        this.numberOfFloors = numberOfFloors;
        this.maxPeople = maxPeople;
        this.rentalType = rentalType;
    }

    public String getStandardRoom() {
        return standardRoom;
    }

    public void setStandardRoom(String standardRoom) {
        this.standardRoom = standardRoom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public String getRentalType() {
        return rentalType;
    }

    @Override
    public String toString() {
        return "Villa{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", area=" + getArea() +
                ", cost=" + getCost() +
                ", standardRoom='" + standardRoom + '\'' +
                ", description='" + description + '\'' +
                ", numberOfFloors=" + numberOfFloors +
                ", maxPeople=" + maxPeople +
                ", rentalType='" + rentalType + '\'' +
                '}';
    }
}
