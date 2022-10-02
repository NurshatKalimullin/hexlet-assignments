package exercise;

// BEGIN
public class Circle {

    private final int radius;
    private Point point;

    public Circle(Point point, int radius) {
        this.radius = radius;
        this.point = point;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("Radius is negative");
        }
        double PI = 3.14159;
        return PI * radius * radius;
    }
}
// END
