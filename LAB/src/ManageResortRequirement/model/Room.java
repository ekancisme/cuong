package ManageResortRequirement.model;

public class Room extends Facility {
    private String freeService;
    private int maxPeople;
    private String rentalType;

    public Room(String id, String name, double area, double cost, String freeService, int maxPeople, String rentalType) {
        super(id, name, area, cost);
        this.freeService = freeService;
        this.maxPeople = maxPeople;
        this.rentalType = rentalType;
    }

    public String getFreeService() {
        return freeService;
    }

    public void setFreeService(String freeService) {
        this.freeService = freeService;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getRentalType() {
        return rentalType;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", area=" + getArea() +
                ", cost=" + getCost() +
                ", freeService='" + freeService + '\'' +
                ", maxPeople=" + maxPeople +
                ", rentalType='" + rentalType + '\'' +
                '}';
    }
}
