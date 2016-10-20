package com.mongoex.volodymyr.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by vkret on 20.10.16.
 */
public class Author implements Serializable {
    private static final long serialVersionUID = 1L;

    private int index;
    private String name;
    private int age;
    private String eyeColor;
    private String gender;
    private String favoriteFruit;

    public int getIndex() {
        return index;
    }

    public Author setIndex(int index) {
        this.index = index;
        return this;
    }

    public String getName() {
        return name;
    }

    public Author setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Author setAge(int age) {
        this.age = age;
        return this;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public Author setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Author setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getFavoriteFruit() {
        return favoriteFruit;
    }

    public Author setFavoriteFruit(String favoriteFruit) {
        this.favoriteFruit = favoriteFruit;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(age, author.age) &&
                Objects.equals(index, author.index) &&
                Objects.equals(name, author.name) &&
                Objects.equals(eyeColor, author.eyeColor) &&
                Objects.equals(gender, author.gender) &&
                Objects.equals(favoriteFruit, author.favoriteFruit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, name, age, eyeColor, gender, favoriteFruit);
    }

    @Override
    public String toString() {
        return "Author{" +
                "index='" + index + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", eyeColor='" + eyeColor + '\'' +
                ", gender='" + gender + '\'' +
                ", favoriteFruit='" + favoriteFruit + '\'' +
                '}';
    }

}
