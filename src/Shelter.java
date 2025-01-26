import java.util.*;

public class Shelter {
    private String name;
    private String location;
    private List<Pet> pets = new ArrayList<>();

    public Shelter(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public void addPet(Pet pet) {
        pets.add(pet);
    }

    public List<Pet> getPets() {
        return pets;
    }

    // Filtering by breed
    public List<Pet> filterByBreed(String breed) {
        List<Pet> result = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getBreed().equalsIgnoreCase(breed)) {
                result.add(pet);
            }
        }
        return result;
    }

    // Filtering by attributes
    public List<Pet> filterByAttributes(String breed, int minAge, int maxAge, Boolean adopted) {
        List<Pet> result = new ArrayList<>();
        for (Pet pet : pets) {
            if ((breed == null || pet.getBreed().equalsIgnoreCase(breed))
                    && (minAge <= pet.getAge() && pet.getAge() <= maxAge)
                    && (adopted == null || pet.isAdopted() == adopted)) {
                result.add(pet);
            }
        }
        return result;
    }

    // Searching by name
    public List<Pet> searchByName(String keyword) {
        List<Pet> result = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(pet);
            }
        }
        return result;
    }

    // Searching by age range
    public List<Pet> searchByAgeRange(int minAge, int maxAge) {
        List<Pet> result = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getAge() >= minAge && pet.getAge() <= maxAge) {
                result.add(pet);
            }
        }
        return result;
    }

    // Sorting by name
    public void sortPetsByName() {
        pets.sort(null);
    }

    // Sorting by age
    public void sortByAge() {
        pets.sort(Comparator.comparingInt(Pet::getAge));
    }

    // Sorting by breed
    public void sortByBreed() {
        pets.sort(Comparator.comparing(Pet::getBreed, String.CASE_INSENSITIVE_ORDER));
    }

    // Sorting by adoption status
    public void sortByAdoptionStatus() {
        pets.sort(Comparator.comparing(Pet::isAdopted).reversed());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Shelter: ").append(name).append(" (Location: ").append(location).append(")\n");
        result.append("Pets:\n");
        for (Pet pet : pets) {
            result.append("- ").append(pet).append("\n");
        }
        return result.toString();
    }

    // Enhanced output for search and sorting
    public void displaySearchResults(List<Pet> searchResults) {
        if (searchResults.isEmpty()) {
            System.out.println("No pets found matching the criteria.");
        } else {
            System.out.println("Search Results:");
            for (Pet pet : searchResults) {
                System.out.println(pet);
            }
        }
    }

    public void displaySortedPets() {
        System.out.println("Sorted Pets:");
        for (Pet pet : pets) {
            System.out.println(pet);
        }
    }
}
