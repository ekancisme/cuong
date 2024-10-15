package ManageResortRequirement.model;

public class Person {
    private String id;       // This is the ID for the person
    private String name;
    private String dateOfBirth;
    private String gender;
    private String cmnd;     // ID card number
    private String phone;     // Phone number
    private String email;
    private String position;  // Employee's position or customer type

    // Constructor
    public Person(String id, String name, String dateOfBirth, String gender, String cmnd, String phone, String email, String position) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.cmnd = cmnd;
        this.phone = phone;
        this.email = email;
        this.position = position; // This is either the customer's type or the employee's position
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getCmnd() {
        return cmnd; // ID card number
    }

    public String getPhone() {
        return phone; // Phone number
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }
    // Setters for Person class
    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
