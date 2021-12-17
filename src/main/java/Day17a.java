import Common.Point;

import java.util.List;

public class Day17a {
    void process(List<String> input) {
        String[] dimensions = input.get(0).replace("target area: x=", "").replace("y=", "").split(",");
        String[] xPart = dimensions[0].trim().split("\\.\\.");
        String[] yPart = dimensions[1].trim().split("\\.\\.");
        Point topLeft = new Point(Integer.parseInt(xPart[0]), Integer.parseInt(yPart[1]));
        Point bottomRight = new Point(Integer.parseInt(xPart[1]), Integer.parseInt(yPart[0]));

        int maxHeight = Integer.MIN_VALUE;
        for (int y = -100; y <= 100; y++) {
            for (int x = 1; x <= 100; x++) {
                int height = shootProbe(x, y, topLeft, bottomRight);
                maxHeight = Math.max(maxHeight, height);
            }
        }
        System.out.println(maxHeight);
    }

    int shootProbe(int xVel, int yVel, Point topLeft, Point bottomRight) {
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
                return maxHeight;
            }
        }
        return -1;
    }
}
