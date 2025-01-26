public abstract class Pet implements Adoptable, Comparable<Pet> {
    private String name;
    private String breed;
    private int age;
    private boolean adopted;

    public Pet(String name, String breed, int age, boolean adopted) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.adopted = adopted;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public boolean isAdopted() {
        return adopted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAdopted(boolean adopted) {
        this.adopted = adopted;
    }

    public abstract String getNature();

    @Override
    public String toString() {
        return "Name: " + name + ", Breed: " + breed + ", Age: " + age + ", Adopted: " + adopted;
    }

    @Override
    public int compareTo(Pet other) {
        return this.name.compareToIgnoreCase(other.name);
    }
}

