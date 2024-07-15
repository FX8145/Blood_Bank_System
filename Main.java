import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class BloodDonor {
    private String name;
    private String bloodType;
    private String contactNumber;

    public BloodDonor(String name, String bloodType, String contactNumber) {
        this.name = name;
        this.bloodType = bloodType;
        this.contactNumber = contactNumber;
    }

    public String getBloodType() {
        return bloodType;
    }

    @Override
    public String toString() {
        return "Donor Name: " + name + ", Blood Type: " + bloodType + ", Contact: " + contactNumber;
    }
}

class BloodBank {
    private List<BloodDonor> donors;

    public BloodBank() {
        this.donors = new ArrayList<>();
    }

    public void addDonor(BloodDonor donor) {
        donors.add(donor);
    }

    public List<BloodDonor> getDonorsByBloodType(String bloodType) {
        return donors.stream()
                .filter(donor -> donor.getBloodType().equalsIgnoreCase(bloodType))
                .collect(Collectors.toList());
    }

    public void printDonors() {
        if (donors.isEmpty()) {
            System.out.println("No donors available.");
        } else {
            donors.forEach(System.out::println);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BloodBank bank = new BloodBank();

        while (true) {
            System.out.println("\nBlood Bank System");
            System.out.println("1. Add Donor");
            System.out.println("2. Find Donors by Blood Type");
            System.out.println("3. List All Donors");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Donor Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Blood Type: ");
                    String bloodType = scanner.nextLine();

                    System.out.print("Enter Contact Number: ");
                    String contactNumber = scanner.nextLine();

                    BloodDonor donor = new BloodDonor(name, bloodType, contactNumber);
                    bank.addDonor(donor);
                    System.out.println("Donor added successfully.");
                    break;

                case 2:
                    System.out.print("Enter Blood Type to search: ");
                    bloodType = scanner.nextLine();
                    List<BloodDonor> donors = bank.getDonorsByBloodType(bloodType);
                    if (donors.isEmpty()) {
                        System.out.println("No donors found with blood type " + bloodType + ".");
                    } else {
                        System.out.println("Donors with blood type " + bloodType + ":");
                        donors.forEach(System.out::println);
                    }
                    break;

                case 3:
                    System.out.println("Listing all donors:");
                    bank.printDonors();
                    break;

                case 4:
                    System.out.println("Exiting program.");
                    scanner.close(); // It's a good practice to close the scanner before exiting
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
