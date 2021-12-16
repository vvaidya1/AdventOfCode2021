package Common;

import java.util.Comparator;

public class WeightedPointComparator implements Comparator<WeightedPoint> {

    @Override
    public int compare(WeightedPoint wp1, WeightedPoint wp2) {
        return Integer.compare(wp1.value, wp2.value);
    }
}
