package ManageResortRequirement.controllers;

import ManageResortRequirement.services.FacilityService;
import ManageResortRequirement.services.FacilityServiceImpl;
import ManageResortRequirement.repository.FacilityRepositoryImpl;
import java.util.Scanner;

public class FacilityManagementMenu {
    private FacilityService facilityService = new FacilityServiceImpl();
    private FacilityRepositoryImpl facilityRepository = new FacilityRepositoryImpl();
    private Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("1. Display list of facilities");
            System.out.println("2. Add new facility");
            System.out.println("3. Display facilities under maintenance");
            System.out.println("4. Return to main menu");
            System.out.print("Please select a function: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        facilityService.displayFacilities();
                        break;
                    case 2:
                        addNewFacility();
                        break;
                    case 3:
                        facilityService.displayFacilitiesUnderMaintenance();
                        break;
                    case 4:
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }
    }

    private void addNewFacility() {
        boolean isAdding = true;
        while (isAdding) {
            System.out.println("1. Add New Villa");
            System.out.println("2. Add New House");
            System.out.println("3. Add New Room");
            System.out.println("4. Back to menu");
            System.out.print("Please select a facility type: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        facilityService.addNewVilla();
                        break;
                    case 2:
                        facilityService.addNewHouse();
                        break;
                    case 3:
                        facilityService.addNewRoom();
                        break;
                    case 4:
                        facilityRepository.saveFacilitiesToFile();
                        isAdding = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }
    }
}
