package com.jkxy.car.api.pojo;


public class Car {
    private int id;
    private String carName;
    private String carType;
    private String price;
    private String carSeries;
    private int carStock;
    private int version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCarSeries() {
        return carSeries;
    }

    public void setCarSeries(String carSeries) {
        this.carSeries = carSeries;
    }

    public int getCarStock() {
        return carStock;
    }

    public int setCarStock(int carStock) {
       return this.carStock = carStock;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carName='" + carName + '\'' +
                ", carType='" + carType + '\'' +
                ", price='" + price + '\'' +
                ", carSeries='" + carSeries + '\'' +
                ", carStock='" + carStock + '\''+
                ", version='" + version + '\''+
                '}';
    }



}
