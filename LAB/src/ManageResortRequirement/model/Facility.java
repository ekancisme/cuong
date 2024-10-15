package ManageResortRequirement.model;

public abstract class Facility {
    private String id;
    private String name;
    private double area;
    private double cost;

    public Facility(String id, String name, double area, double cost) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getArea() {
        return area;
    }

    public double getCost() {
        return cost;
    }

    public String getFacilityName() {
        return name;
    }
}
