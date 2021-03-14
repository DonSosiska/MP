package ua.kpi.comsys.IO8123;

public class Main {

    public static void main(String[] args) {
        CoordinateIS cr = new CoordinateIS(10, 10, 10, Direction.latitude);
        CoordinateIS ew = new CoordinateIS(55, 16, 50, Direction.latitude);
        System.out.println(cr.getFormatedString());
        System.out.println(cr.getString());
        System.out.println(cr.getDiff(ew));
        System.out.println(CoordinateIS.getDiffBetween(cr, ew).getFormatedString());
    }
}
