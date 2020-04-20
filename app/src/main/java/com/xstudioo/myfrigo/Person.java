package com.xstudioo.myfrigo;

public class Person {

    private long id;
    private String name;
    private String age;
    private String occupation;
    private String image;
    private String barcode;

    public Person() {
    }

    public Person(String name, String age, String occupation, String image, String barcode) {
        this.name = name;
        this.age = age;
        this.occupation = occupation;
        this.image = image;
        this.barcode = barcode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
