import Common.Point;
import java.util.List;

public class Day17b {
    void process(List<String> input) {
        String[] dimensions = input.get(0).replace("target area: x=", "").replace("y=", "").split(",");
        String[] xPart = dimensions[0].trim().split("\\.\\.");
        String[] yPart = dimensions[1].trim().split("\\.\\.");
        Point topLeft = new Point(Integer.parseInt(xPart[0]), Integer.parseInt(yPart[1]));
        Point bottomRight = new Point(Integer.parseInt(xPart[1]), Integer.parseInt(yPart[0]));

        int successfulProbes = 0;
        for (int y = -1000; y <= 1000; y++) {
            for (int x = 1; x <= 1000; x++) {
                if(checkProbe(x, y, topLeft, bottomRight))
                    successfulProbes += 1;
            }
        }
        System.out.println(successfulProbes);
    }

    boolean checkProbe(int xVel, int yVel, Point topLeft, Point bottomRight) {
        int steps = 0, maxHeight = Integer.MIN_VALUE;
        int x = 0, y = 0;
        while (steps < 1000) {
            x += xVel;
            y += yVel;
            xVel += Integer.compare(0, xVel);
            yVel--;

            maxHeight = Math.max(maxHeight, y);
            steps++;
            if (x >= topLeft.x && x <= bottomRight.x && y <= topLeft.y && y >= bottomRight.y) {
                return true;
            }
        }
        return false;
    }
}
