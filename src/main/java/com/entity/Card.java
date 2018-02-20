package com.entity;

public class Card {

    private String name;
    private String phones;

    public Card() {

    }

    public Card(String name, String phones) {
        this.name = name;
        this.phones = phones;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", phones='" + phones + '\'' +
                '}';
    }
}
