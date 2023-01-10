package org.example.model;

public class User {
    private String lastName;
    private String firstName;
    private String thirdName;
    private String birthDate;
    private Long phone;
    private Character sex;

    public User(String lastName, String firstName, String thirdName, String birthDate, Long phone, Character sex) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.thirdName = thirdName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Full name: " + lastName + " " + firstName + " " + thirdName +
                ". Date of birth: " + birthDate +
                ". Phone number: " + phone +
                ". Sex: " + sex;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }
}
