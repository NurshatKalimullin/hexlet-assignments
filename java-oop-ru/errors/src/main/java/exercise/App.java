package exercise;

// BEGIN
public class App {

    public static int printSquare(Circle circle) throws NegativeRadiusException {
        int square = 0;
        try {
            square = (int) Math.round(circle.getSquare());
        } catch (NegativeRadiusException e) {
            System.out.println("Не удалось посчитать площадь");
        } finally {
            System.out.println("Вычисление окончено");
        }
        return square;
    }
}
// END
