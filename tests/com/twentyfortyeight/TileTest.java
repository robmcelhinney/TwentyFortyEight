package com.twentyfortyeight;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class TileTest {

    @Test
    void initialisesZero() {
        Tile tile = new Tile();
        Assert.assertEquals(tile.getValue(), 0);
    }

    @Test
    void equals() {
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();
        Tile tile3 = new Tile(2);

        Assert.assertTrue(tile1.equals(tile2));
        Assert.assertFalse(tile2.equals(tile3));
    }

    @Test
    void merge() {
        Tile tile1 = new Tile(3);
        Tile tile2 = new Tile(4);

        tile1.merge(tile2);

        Assert.assertEquals(3 + 4, tile1.getValue());
    }
}