package com.epam.training.ticketservice.model;

public final class RoomDTO {

    private final String name;
    private final int numberOfRows;
    private final int numberOfColumns;

    public RoomDTO(String name, int numberOfRows, int numberOfColumns) {
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

    @Override
    public String toString() {
        return "Room " + name + " with " + numberOfRows * numberOfColumns
                + " seats, " + numberOfRows + " rows and " + numberOfColumns + " columns";
    }
}
