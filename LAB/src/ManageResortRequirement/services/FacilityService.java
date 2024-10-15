package ManageResortRequirement.services;

import ManageResortRequirement.model.Facility;
import java.util.Map;

public interface FacilityService {
    Map<Facility, Integer> displayAllFacilities();
    void addNewFacility(Facility facility);
    Map<Facility, Integer> displayFacilitiesNeedingMaintenance();
    void addNewVilla();
    void addNewHouse();
    void addNewRoom();
    void displayFacilities();
    void displayFacilitiesUnderMaintenance();

}
