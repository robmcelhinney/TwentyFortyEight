package com.twentyfortyeight;

public class Tile {

    private int value;

    public Tile() {
        this(0);
    }

    public Tile(int value) {
        setValue(value);
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    void merge(Tile otherTile) {
        setValue(this.getValue() + otherTile.getValue());
    }

    public boolean equals(Tile otherTile) {
        return this == otherTile || getClass() == otherTile.getClass() && this.getValue() == otherTile.getValue();
    }
}
