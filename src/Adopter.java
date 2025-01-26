import java.util.ArrayList;
import java.util.List;

public class Adopter {
    private String name;
    private String contactInfo;
    private List<Pet> adoptedPets = new ArrayList<>();

    public Adopter(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public void adoptPet(Pet pet) {
        if (!pet.isAdopted()) {
            pet.setAdopted(true);
            adoptedPets.add(pet);
        } else {
            System.out.println("This pet is already adopted.");
        }
    }

    @Override
    public String toString() {
        adoptedPets.sort(null);
        StringBuilder result = new StringBuilder();
        result.append("Adopter: ").append(name).append(" (Contact: ").append(contactInfo).append(")\n");
        result.append("Adopted Pets:\n");
        for (Pet pet : adoptedPets) {
            result.append("- ").append(pet).append("\n");
        }
        return result.toString();
    }
}