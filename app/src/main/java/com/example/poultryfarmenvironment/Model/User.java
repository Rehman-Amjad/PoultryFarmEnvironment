package com.example.poultryfarmenvironment.Model;

public class User {

    String Id;
    String Humidity;
    String Temperature;
    String DateTime;
    String Motor;
    String img;

    public User(String id, String humidity, String temperature, String dateTime, String motor, String img) {
        Id = id;
        Humidity = humidity;
        Temperature = temperature;
        DateTime = dateTime;
        Motor = motor;
        this.img = img;
    }

    public User() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        Humidity = humidity;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public String getMotor() {
        return Motor;
    }

    public void setMotor(String motor) {
        Motor = motor;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
