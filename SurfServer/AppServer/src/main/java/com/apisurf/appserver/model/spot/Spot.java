package com.apisurf.appserver.model.spot;

import jakarta.persistence.*;

@Entity
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String destination;
    private String address;
    private int difficulty_level;
    private String photo_url;

   @Enumerated(EnumType.STRING)
    private SurfBreak surfBreak;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDifficulty_level() {
        return difficulty_level;
    }

    public void setDifficulty_level(int difficulty_level) {
        this.difficulty_level = difficulty_level;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Spot{" +
                "address='" + address + '\'' +
                ", id=" + id +
                ", destination='" + destination + '\'' +
                ", difficulty_level=" + difficulty_level +
                ", photo_url='" + photo_url + '\'' +
                ", surfBreak=" + surfBreak +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public SurfBreak getSurfBreak() {
        return surfBreak;
    }

    public void setSurfBreak(SurfBreak surfBreak) {
        this.surfBreak = surfBreak;
    }
}
