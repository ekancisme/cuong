    package ManageResortRequirement.utils;

    import java.util.Scanner;

    public class StringUtils {
        public static boolean isValidServiceCode(String code, String type) {
            String regex = "";
            switch (type) {
                case "Villa":
                    regex = "^SVVL-\\d{4}$";
                    break;
                case "House":
                    regex = "^SVHO-\\d{4}$";
                    break;
                case "Room":
                    regex = "^SVRO-\\d{4}$";
                    break;
            }
            return code.matches(regex);
        }

        public static boolean isValidName(String name) {
            return name.matches("^[A-Z][a-zA-Z]*$");
        }

        public static boolean isValidArea(double area) {
            return area > 30.0;
        }

        public static boolean isValidCost(double cost) {
            return cost > 0;
        }

        public static boolean isValidMaxPeople(int maxPeople) {
            return maxPeople > 0 && maxPeople < 20;
        }

        public static boolean isValidFloors(int floors) {
            return floors > 0;
        }
        private String getValidId(String prefix) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter ID: ");
                String id = scanner.nextLine().trim();
                if (id.isEmpty()) {
                    System.out.println("ID cannot be empty. Please try again.");
                    continue;
                }
                if (StringUtils.isValidServiceCode(id, prefix)) {
                    return id;
                } else {
                    System.out.println("Invalid ID. Please try again.");
                }
            }
        }
    }