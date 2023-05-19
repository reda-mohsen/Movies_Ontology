/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MoviePack;

import java.util.ArrayList;

/**
 *
 * @author Nour El-Din
 */

public class Person {
    String name;
    String age;
    String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Person() {
    }
     public Person(String name, String age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public static boolean isValidName(String name) {
        return name != null && !name.isEmpty();
    }

    public static boolean isDuplicateName(ArrayList<Person> actors, String name) {
        return actors.stream().anyMatch(person -> person.getName().equals(name));
    }

    public static void addPerson(ArrayList<Person> actors, Person person) {
        if (isValidName(person.getName()) && !isDuplicateName(actors, person.getName())) {
            person.gender="Male";
            actors.add(person);
        }
    }
}
