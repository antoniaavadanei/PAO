package com.company.person;

import java.util.Objects;
import java.util.Scanner;

public class Client {
    private Integer id;
    private Integer age;
    private String lastName;
    private String firstName;
    private Boolean hasSubscription;


    public Client(Integer id, String lastName, String firstName, Integer age,Boolean hasSubscription) {
        this.id=id;
        this.age = age;
        this.lastName = lastName;
        this.firstName = firstName;
        this.hasSubscription = hasSubscription;
    }


    public Client() { }

    public Client(Integer age, String lastName, String firstName, Boolean hasSubscription) {
        this.age = age;
        this.lastName = lastName;
        this.firstName = firstName;
        this.hasSubscription = hasSubscription;
    }

    public void setId(Integer id) { this.id = id; }

    public void setAge(Integer age) { this.age = age; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setHasSubscription(Boolean hasSubscription) {
        this.hasSubscription = hasSubscription;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getId() { return id; }

    public String getFirstName() {
        return firstName;
    }

    public Integer getAge() {
        return age;
    }

    public Boolean getHasSubscription() {
        return hasSubscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(age, client.age) &&
                Objects.equals(lastName, client.lastName) &&
                Objects.equals(firstName, client.firstName) &&
                Objects.equals(hasSubscription, client.hasSubscription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, lastName, firstName, hasSubscription);
    }

    public Client readClient() {

        System.out.println("Last Name, First Name, Age, Has Subscription?(TRUE/FALSE):");
        Scanner myObj = new Scanner(System.in);
        String LastName = myObj.nextLine();
        String FirstName = myObj.nextLine();
        Integer Age = myObj.nextInt();
        Boolean HasSubscription = myObj.nextBoolean();
        return new Client(Age,LastName, FirstName,  HasSubscription);
    }

    @Override
    public String toString() {
        return "Client{" +
              "Last Name='" + lastName + '\'' +
                ", First Name='" + firstName + '\'' +
               ", Age=" + age +
                ", Has Subscription=" + hasSubscription +
                '}';
    }

}
