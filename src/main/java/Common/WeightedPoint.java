package Common;

import java.util.Objects;

public class WeightedPoint {
    public Point point;
    public int value;

    public WeightedPoint(Point point, int value) {
        this.point = point;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightedPoint that = (WeightedPoint) o;
        return this.point.equals(that.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }
}

