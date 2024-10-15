package ManageResortRequirement.repository;

import ManageResortRequirement.model.Facility;
import ManageResortRequirement.model.House;
import ManageResortRequirement.model.Room;
import ManageResortRequirement.model.Villa;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class FacilityRepositoryImpl {
    private static final String FILE_PATH = "src/ManageResortRequirement/data/facility.csv";
    private static Map<Facility, Integer> facilityMap = new LinkedHashMap<>();

    static {
        loadFacilitiesFromFile();
    }

    public static Map<Facility, Integer> getAllFacilities() {
        return facilityMap;
    }

    public static void addFacility(Facility facility) {
        facilityMap.put(facility, 0);
    }

    public static Map<Facility, Integer> getFacilitiesNeedingMaintenance() {
        Map<Facility, Integer> facilitiesNeedingMaintenance = new LinkedHashMap<>();
        for (Map.Entry<Facility, Integer> entry : facilityMap.entrySet()) {
            if (entry.getValue() >= 5) {
                facilitiesNeedingMaintenance.put(entry.getKey(), entry.getValue());
            }
        }
        return facilitiesNeedingMaintenance;
    }

    public static void incrementUsage(Facility facility) {
        facilityMap.put(facility, facilityMap.get(facility) + 1);
    }

    private static void loadFacilitiesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 7) {
                    System.out.println("Insufficient data for facility: " + line);
                    continue;
                }

                String id = data[0];
                String type = data[1];
                double area = Double.parseDouble(data[2]);
                double cost = Double.parseDouble(data[3]);
                int maxPeople = Integer.parseInt(data[4]);
                String rentalType = data[5];
                String roomStandard = data[6];
                int numberOfFloors = data.length > 7 ? Integer.parseInt(data[7]) : 0;
                String freeService = data.length > 8 ? data[8] : "";

                Facility facility = null;

                switch (type) {
                    case "Villa":
                        facility = new Villa(id, id, area, cost, roomStandard, "Description", numberOfFloors, maxPeople, rentalType);
                        break;
                    case "House":
                        facility = new House(id, id, area, cost, roomStandard, "Description", numberOfFloors, maxPeople, rentalType);
                        break;
                    case "Room":
                        facility = new Room(id, id, area, cost, freeService, maxPeople, rentalType);
                        break;
                    default:
                        System.out.println("Unknown facility type: " + type);
                        continue;
                }

                if (facility != null) {
                    facilityMap.put(facility, 0);
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load facilities from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing data: " + e.getMessage());
        }
    }

    public static void saveFacilitiesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Map.Entry<Facility, Integer> entry : facilityMap.entrySet()) {
                Facility facility = entry.getKey();
                if (facility instanceof Villa) {
                    Villa villa = (Villa) facility;
                    writer.write(String.format("%s,Villa,%.2f,%.2f,%d,%s,%s,%d\n",
                            villa.getId(), villa.getArea(), villa.getCost(),
                            villa.getMaxPeople(), villa.getRentalType(),
                            villa.getStandardRoom(), villa.getNumberOfFloors()));
                } else if (facility instanceof House) {
                    House house = (House) facility;
                    writer.write(String.format("%s,House,%.2f,%.2f,%d,%s,%s,%d\n",
                            house.getId(), house.getArea(), house.getCost(),
                            house.getMaxPeople(), house.getRentalType(),
                            house.getStandardRoom(), house.getNumberOfFloors()));
                } else if (facility instanceof Room) {
                    Room room = (Room) facility;
                    writer.write(String.format("%s,Room,%.2f,%.2f,%d,%s\n",
                            room.getId(), room.getArea(), room.getCost(),
                            room.getMaxPeople(), room.getRentalType()));
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to save facilities to file: " + e.getMessage());
        }
    }
}
