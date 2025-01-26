public class Cat extends Pet {
    private String nature;

    public Cat(String name, String breed, int age, boolean adopted, String nature) {
        super(name, breed, age, adopted);
        this.nature = nature;
    }

    @Override
    public String getNature() {
        return nature;
    }

    @Override
    public String toString() {
        return super.toString() + ", Nature: " + nature;
    }
}