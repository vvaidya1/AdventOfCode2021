package Common;

import java.util.Objects;

public class Point {
    public int x;
    public int y;

    public Point(int i, int j) {
        x = i;
        y = j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point that = (Point) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}