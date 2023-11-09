import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

class Main {
  
  public static Stream<Point> pointStream(Point point, Function<Point, Point> f) {
    // Implement here!
  }

  public static Stream<Point> generateGrid(Point point, int n) {
    // Implement here!
  }

  public static Stream<Circle> concentricCircles(Circle circle, Function<Double, Double> f) {
    // Implement here!
  }

  public static Stream<Point> pointStreamFromCircle(Stream<Circle> circles) {
    // Implement here!
  }

  public static double estimatePi(Circle c, Supplier<RandomPoint> supplier, int k) {
    // Implement here!
  }
}
