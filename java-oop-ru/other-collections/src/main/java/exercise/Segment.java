package exercise;

// BEGIN
public class Segment {

    private final Point beginPoint;
    private final Point endPoint;

    public Segment(Point beginPoint, Point endPoint) {
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
    }


    public Point getMidPoint() {
        int x = (beginPoint.getX() + endPoint.getX()) / 2;
        int y = (beginPoint.getY() + endPoint.getY()) / 2;
        return new Point(x, y);
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }
}
// END
