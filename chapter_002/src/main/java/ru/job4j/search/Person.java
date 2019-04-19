package ru.job4j.search;

public class Person {
    private String name;
    private String surname;
    private String address;
    private String phone;

    public Person(String name, String surname, String address, String phone) {
        this.address = address;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
