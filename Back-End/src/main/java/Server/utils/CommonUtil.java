package Server.utils;

import java.util.Random;

public class CommonUtil {

    public static StringBuilder createId() {
        String[] num = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            result.append(num[random.nextInt(10)]);
        }
        return result;
    }
}
