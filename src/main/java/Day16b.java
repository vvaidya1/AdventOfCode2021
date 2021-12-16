import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day16b {
    String binary;
    int cursor = 0;

    public void process(List<String> input) {
        StringBuilder builder = new StringBuilder();
        for (char ch : input.get(0).toCharArray()) {
            String binaryLocal = new BigInteger(String.valueOf(ch), 16).toString(2);
            builder.append("0".repeat(4 - binaryLocal.length())).append(binaryLocal);
        }
        binary = builder.toString();
        System.out.print(parsePacket());
    }

    long parsePacket() {
        //Parse version
        readPartInt(3);
        int type = readPartInt(3);

        return switch (type) {
            case 0 -> calculateSum();
            case 1 -> calculateProduct();
            case 2 -> findMin();
            case 3 -> findMax();
            case 4 -> parseLiteral();
            case 5 -> findGreaterThan();
            case 6 -> findLessThan();
            case 7 -> findEqualTo();
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

    private long findEqualTo() {
        List<Long> nums = parseOperator();
        return nums.get(0).equals(nums.get(1)) ? 1 : 0;
    }

    private long findLessThan() {
        List<Long> nums = parseOperator();
        return nums.get(0) < nums.get(1) ? 1 : 0;
    }

    private long findGreaterThan() {
        List<Long> nums = parseOperator();
        return nums.get(0) > nums.get(1) ? 1 : 0;
    }

    private long findMax() {
        long toReturn = Long.MIN_VALUE;
        for (long val : parseOperator()) {
            toReturn = Math.max(toReturn, val);
        }
        return toReturn;
    }

    private long findMin() {
        long toReturn = Long.MAX_VALUE;
        for (long val : parseOperator()) {
            toReturn = Math.min(toReturn, val);
        }
        return toReturn;
    }

    private long calculateProduct() {
        long toReturn = 1;
        for (long val : parseOperator()) {
            toReturn *= val;
        }
        return toReturn;
    }

    private long calculateSum() {
        long toReturn = 0;
        for (long val : parseOperator()) {
            toReturn += val;
        }
        return toReturn;
    }

    List<Long> parseOperator() {
        List<Long> nums = new ArrayList<>();
        int lengthIndicator = readPartInt(1);
        if (lengthIndicator == 0) {
            int length = readPartInt(15);
            int startCursor = cursor;
            while (cursor < startCursor + length) {
               nums.add(parsePacket());
            }
        } else {
            int numPackets = readPartInt(11);
            for (int i = 0; i < numPackets; i++) {
                nums.add(parsePacket());
            }
        }
        return nums;
    }

    long parseLiteral() {
        int start = readPartInt(1);
        StringBuilder sb = new StringBuilder();
        while (start == 1) {
            sb.append(readPart(4));
            start = readPartInt(1);
        }
        sb.append(readPart(4));
        return Long.parseLong(sb.toString(), 2);
    }

    String readPart(int length) {
        String returnStr = binary.substring(cursor, cursor + length);
        cursor += length;
        return returnStr;
    }

    int readPartInt(int length) {
        return Integer.parseInt(readPart(length), 2);
    }
}
