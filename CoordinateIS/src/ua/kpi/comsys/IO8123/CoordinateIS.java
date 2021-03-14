package ua.kpi.comsys.IO8123;

enum Direction{
    latitude,
    longitude
}

public class CoordinateIS {
    Direction direction;
    int degrees;
    int minutes;
    int seconds;

    CoordinateIS() {
        this.degrees = 0;
        this.minutes = 0;
        this.seconds = 0;
        this.direction = Direction.latitude;
    }

    CoordinateIS(int degrees, int minutes, int seconds, Direction direction) {
        this.direction = direction;
        switch (direction) {
            case latitude:
                if (-90 > degrees && degrees > 90) {
                    this.degrees = 0;
                } else {
                    this.degrees = degrees;
                }
                break;
            case longitude:
                if (-180 > degrees && degrees > 180) {
                    this.degrees = 0;
                } else {
                    this.degrees = degrees;
                }
        }

        if (minutes <= 59 || minutes >= 0) {
            this.minutes = minutes;
        }

        if (seconds <= 59 || seconds >= 0) {
            this.seconds = seconds;
        }

    }

    public String getFormatedString() {
        String d = "";
        switch (this.direction) {
            case latitude:
                if (this.degrees < 0) {
                    d = "N";
                } else {
                    d = "W";
                }
            case longitude:
                if (this.degrees < 0) {
                    d = "S";
                } else {
                    d = "E";
                }
            default:
                return this.degrees + "°" + this.minutes + "'" + this.seconds + "\" " + d;
        }
    }

    public String getString() {
        String d = "";
        switch (this.direction) {
            case latitude:
                if (this.degrees < 0) {
                    d = "N";
                } else {
                    d = "W";
                }
            case longitude:
                if (this.degrees < 0) {
                    d = "S";
                } else {
                    d = "E";
                }
            default:
                double deg = (double) this.degrees + (double) (this.minutes * 60 + this.degrees) / 3600.0D;
                String var10000 = Double.toString(deg);
                return var10000 + " " + d;
        }
    }

    public String getDiff(CoordinateIS coord) {
        if (this.direction == coord.direction){
            return Double.toString(Math.abs((this.degrees + this.minutes/60.0 + this.seconds/3600.0) - (coord.degrees + coord.minutes/60.0 + coord.seconds/3600.0)));
        } else return "nil";
    }

    public static CoordinateIS getDiffBetween (CoordinateIS coord1, CoordinateIS coord2){
        if (coord1.direction == coord2.direction){
            double diff = (coord1.degrees + coord1.minutes/60.0 + coord1.seconds/3600.0) - (coord2.degrees + coord2.minutes/60.0 + coord2.seconds/3600.0);
            int deg = (int)diff;
            diff -= deg;
            diff *= 3600;
            int min = (int)Math.floor(diff/60);
            diff -= min*60;
            int sec = (int)diff;
            return new CoordinateIS(deg, min, sec, coord1.direction);
        } else return new CoordinateIS();
    }
}

