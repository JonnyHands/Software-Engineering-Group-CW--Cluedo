import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Tile {

    private static final double width = 21.5; // tiles width size
    private static final double height = 21.5; //tiles height size
    private float x; // tiles x co-ordinate
    private float y; // tiles y co-ordinate
    private int row; // row of the array
    private int column;  // column of the array
    private int tileType; // identifies the tile type
    private int room; //distinguishes the room

    public Tile() {
        this.x = 0; //defaults to 0
        this.y = 0; //defaults to 0
        this.row = 0; //defaults to 0
        this.column = 0; //defaults to 0
        this.tileType = 0; //defaults to 0
        this.room = 0; //defaults to 0
    }

    public Tile(float xValue, float yValue, int numberOfRows, int numberOfColumns, int tiletype, int roomNumber) { //creates tile
        this.x = xValue;
        this.y = yValue;
        this.row = numberOfRows;
        this.column = numberOfColumns;
        this.tileType = tiletype;
        this.room = roomNumber;
    }

    public float getXCoordinate() {
        return x;
    }

    public float getYCoordinate() {
        return y;
    }

    public int getRowPosition() {
        return row;
    }

    public int getColumnPosition() {
        return column;
    }

    
    public int getTileType() { //returns what type of tile it is 
        return tileType;
    }

    
    public int getRoom() { //returns room types
        return room;
    }

    
    public String roomTiles() { // shows rooms tiles
        return "[" + row + ", " + column + "]";
    }    
}
