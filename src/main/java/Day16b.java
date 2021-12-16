import java.math.BigInteger;
import java.util.List;

public class Day16b {
    String binary;
    int cursor = 0;
    int solution1;

    public void process(List<String> input) {
        StringBuilder builder = new StringBuilder();
        for (char ch : input.get(0).toCharArray()) {
            String binaryLocal = new BigInteger(String.valueOf(ch), 16).toString(2);
            builder.append("0".repeat(4 - binaryLocal.length())).append(binaryLocal);
        }
        binary = builder.toString();
        parsePacket();
        System.out.print(solution1);
    }

    int parsePacket() {
        int version = readPartInt(3);
        int type = readPartInt(3);
        solution1 += version;

        if (type == 4) {
            parseLiteral();
        } else {
            parseOperator();
        }
        return solution1;
    }

    void parseOperator() {
        int lengthIndicator = readPartInt(1);
        if (lengthIndicator == 0) {
            int length = readPartInt(15);
            int startCursor = cursor;
            while (cursor < startCursor + length) {
                parsePacket();
            }
        } else {
            int numPackets = readPartInt(11);
            for (int i = 0; i < numPackets; i++) {
                parsePacket();
            }
        }
    }

    void parseLiteral() {
        int start = readPartInt(1);
        while (start == 1) {
            readPart(4);
            start = readPartInt(1);
        }
        readPart(4);
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
