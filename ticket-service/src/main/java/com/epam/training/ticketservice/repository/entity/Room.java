package com.epam.training.ticketservice.repository.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Room {

    @Id
    private String name;
    private int numberOfRows;
    private int numberOfColumns;

    public Room(String name, int numberOfRows, int numberOfColumns) {
        this.name = name;
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    public Room() {

    }

    public String getName() {
        return name;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

}
