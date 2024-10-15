package ManageResortRequirement.services;

import ManageResortRequirement.model.Facility;
import ManageResortRequirement.model.House;
import ManageResortRequirement.model.Room;
import ManageResortRequirement.model.Villa;
import ManageResortRequirement.repository.FacilityRepositoryImpl;
import ManageResortRequirement.utils.StringUtils;
import ManageResortRequirement.services.BookingServiceImpl;

import java.util.Map;
import java.util.Scanner;

public class FacilityServiceImpl implements FacilityService {
    private FacilityRepositoryImpl facilityRepository = new FacilityRepositoryImpl();
    private BookingServiceImpl bookingService = new BookingServiceImpl(); // Thêm BookingService
    private Scanner scanner = new Scanner(System.in);

    @Override
    public Map<Facility, Integer> displayAllFacilities() {
        return facilityRepository.getAllFacilities();
    }

    @Override
    public void addNewFacility(Facility facility) {
        facilityRepository.addFacility(facility);
    }

    @Override
    public Map<Facility, Integer> displayFacilitiesNeedingMaintenance() {
        return facilityRepository.getFacilitiesNeedingMaintenance();
    }

    @Override
    public void addNewVilla() {
        String id = getValidId("Villa");
        String name = getValidName();
        double area = getValidArea();
        double cost = getValidCost();
        String standardRoom = getValidStandardRoom();
        String description = getValidDescription();
        int numberOfFloor = getValidNumberOfFloor();
        int maxPeople = getValidMaxPeople();
        String rentalType = getValidRentalType();

        Villa newVilla = new Villa(id, name, area, cost, standardRoom, description, numberOfFloor, maxPeople, rentalType);
        addNewFacility(newVilla);
        System.out.println("Added new Villa: " + newVilla.getFacilityName());
    }

    @Override
    public void addNewHouse() {
        String id = getValidId("House");
        String name = getValidName();
        double area = getValidArea();
        double cost = getValidCost();
        String standardRoom = getValidStandardRoom();
        String description = getValidDescription();
        int numberOfFloor = getValidNumberOfFloor();
        int maxPeople = getValidMaxPeople();
        String rentalType = getValidRentalType();

        House newHouse = new House(id, name, area, cost, standardRoom, description, numberOfFloor, maxPeople, rentalType);
        addNewFacility(newHouse);
        System.out.println("Added new House: " + newHouse.getFacilityName());
    }

    @Override
    public void addNewRoom() {
        String id = getValidId("Room");
        String name = getValidName();
        double area = getValidArea();
        double cost = getValidCost();
        String freeService = getValidFreeService();
        int maxPeople = getValidMaxPeople();
        String rentalType = getValidRentalType();

        Room newRoom = new Room(id, name, area, cost, freeService, maxPeople, rentalType);
        addNewFacility(newRoom);
        System.out.println("Added new Room: " + newRoom.getFacilityName());
    }

    @Override
    public void displayFacilities() {
        for (Map.Entry<Facility, Integer> entry : facilityRepository.getAllFacilities().entrySet()) {
            System.out.println("Facility: " + entry.getKey().getFacilityName() + ", Usage: " + entry.getValue());
        }
    }

    @Override
    public void displayFacilitiesUnderMaintenance() {
        for (Map.Entry<Facility, Integer> entry : facilityRepository.getFacilitiesNeedingMaintenance().entrySet()) {
            System.out.println("Facility under maintenance: " + entry.getKey().getFacilityName() + ", Usage: " + entry.getValue());
        }
    }

    // Tăng số lần sử dụng Facility khi có booking mới
    public void increaseUsage(Facility facility) {
        facilityRepository.incrementUsage(facility);
        if (facilityRepository.getAllFacilities().get(facility) >= 5) {
            System.out.println("Facility " + facility.getFacilityName() + " needs maintenance!");
        }
    }

    private String getValidId(String prefix) {
        while (true) {
            System.out.print("Enter ID: ");
            String id = scanner.nextLine();
            if (StringUtils.isValidServiceCode(id, prefix)) {
                return id;
            } else {
                System.out.println("Invalid ID. Please try again.");
            }
        }
    }

    private String getValidName() {
        while (true) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            if (StringUtils.isValidName(name)) {
                return name;
            } else {
                System.out.println("Invalid name. Please try again.");
            }
        }
    }

    private double getValidArea() {
        while (true) {
            System.out.print("Enter area: ");
            try {
                double area = Double.parseDouble(scanner.nextLine());
                if (StringUtils.isValidArea(area)) {
                    return area;
                } else {
                    System.out.println("Invalid area. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private double getValidCost() {
        while (true) {
            System.out.print("Enter cost: ");
            try {
                double cost = Double.parseDouble(scanner.nextLine());
                if (StringUtils.isValidCost(cost)) {
                    return cost;
                } else {
                    System.out.println("Invalid cost. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private String getValidStandardRoom() {
        while (true) {
            System.out.print("Enter standard room: ");
            String standardRoom = scanner.nextLine();
            if (StringUtils.isValidName(standardRoom)) {
                return standardRoom;
            } else {
                System.out.println("Invalid standard room. Please try again.");
            }
        }
    }

    private String getValidDescription() {
        while (true) {
            System.out.print("Enter description: ");
            String description = scanner.nextLine();
            if (StringUtils.isValidName(description)) {
                return description;
            } else {
                System.out.println("Invalid description. Please try again.");
            }
        }
    }

    private int getValidNumberOfFloor() {
        while (true) {
            System.out.print("Enter number of floor: ");
            try {
                int numberOfFloor = Integer.parseInt(scanner.nextLine());
                if (StringUtils.isValidFloors(numberOfFloor)) {
                    return numberOfFloor;
                } else {
                    System.out.println("Invalid number of floor. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private String getValidFreeService() {
        while (true) {
            System.out.print("Enter free service: ");
            String freeService = scanner.nextLine();
            if (StringUtils.isValidName(freeService)) {
                return freeService;
            } else {
                System.out.println("Invalid free service. Please try again.");
            }
        }
    }

    private int getValidMaxPeople() {
        while (true) {
            System.out.print("Enter maximum number of people: ");
            try {
                int maxPeople = Integer.parseInt(scanner.nextLine());
                if (maxPeople > 0) {
                    return maxPeople;
                } else {
                    System.out.println("Invalid maximum number of people. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private String getValidRentalType() {
        while (true) {
            System.out.print("Enter rental type: ");
            String rentalType = scanner.nextLine();
            if (StringUtils.isValidName(rentalType)) {
                return rentalType;
            } else {
                System.out.println("Invalid rental type. Please try again.");
            }
        }
    }
}
