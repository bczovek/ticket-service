package com.epam.training.ticketservice.model;

public final class Room {

    private Long id;
    private final String name;
    private final int numberOfRows;
    private final int numberOfColumns;

    public Room(String name, int numberOfRows, int numberOfColumns) {
        this.name = name;
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
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
