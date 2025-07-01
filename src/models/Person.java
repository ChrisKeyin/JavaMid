package models;

/**
 * Represents a generic person in the pharma system.
 * This class serves as a base for more specific person types such as Patient and Doctor.
 */
public class Person {
    protected int id;
    protected String name;
    protected int age;
    protected String phoneNumber;

    /**
     * Constructs a Person object.
     *
     * @param id          the person's ID
     * @param name        the person's name
     * @param age         the person's age
     * @param phoneNumber the person's phone number
     */
    public Person(int id, String name, int age, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the person's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the person's name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the person's ID.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the person's age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the person's phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

}
