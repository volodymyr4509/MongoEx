package com.mongoex.volodymyr.domain;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by vkret on 20.10.16.
 */
public class Author implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private int age;
    private String eyeColor;
    private String gender;
    private String favoriteFruit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFavoriteFruit() {
        return favoriteFruit;
    }

    public void setFavoriteFruit(String favoriteFruit) {
        this.favoriteFruit = favoriteFruit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(age, author.age) &&
                Objects.equals(id, author.id) &&
                Objects.equals(name, author.name) &&
                Objects.equals(eyeColor, author.eyeColor) &&
                Objects.equals(gender, author.gender) &&
                Objects.equals(favoriteFruit, author.favoriteFruit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, eyeColor, gender, favoriteFruit);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", eyeColor='" + eyeColor + '\'' +
                ", gender='" + gender + '\'' +
                ", favoriteFruit='" + favoriteFruit + '\'' +
                '}';
    }

}
