package org.example.businessmodule.converter;


import org.example.businessmodule.dto.CoordinatesWrite;
import org.example.businessmodule.model.Coordinates;

public class CoordinatesConverter {
    public static Coordinates toCoordinates(CoordinatesWrite coordinatesWrite) {
        Coordinates coordinates = new Coordinates();
        coordinates.setX(coordinatesWrite.getX());
        coordinates.setY(coordinatesWrite.getY());
        return coordinates;
    }

    public static CoordinatesWrite toCoordinatesWrite(Coordinates coordinatesWrite) {
        CoordinatesWrite coordinates = new CoordinatesWrite();
        coordinates.setX(coordinatesWrite.getX());
        coordinates.setY(coordinatesWrite.getY());
        return coordinates;
    }


}
