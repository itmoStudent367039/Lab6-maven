package org.example.products;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@JsonAutoDetect
@Getter
@Setter
public class Person implements Valid, Comparable<Person> {
    private String name;
    private int height;
    private Color eyeColor;
    private Color hairColor;
    private Country nationality;
    private Location location;

    public Person(String name, int height, Color eyeColor, Color hairColor, Country nationality, Location location) {
        this.name = name;
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    public Person() {
    }

    @Override
    public boolean isValid() {
        return !Objects.isNull(name) &&
                !name.isEmpty() &&
                height > 0 &&
                !Objects.isNull(eyeColor) &&
                !Objects.isNull(hairColor) &&
                !Objects.isNull(nationality) &&
                !Objects.isNull(location) &&
                location.isValid();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", eyeColor=" + eyeColor +
                ", hairColor=" + hairColor +
                ", nationality=" + nationality +
                ", location=" + location +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Person o) {
        return this.name.toUpperCase().compareTo(o.getName().toUpperCase()) * -1;
    }
}
