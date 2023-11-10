package edu.hw5;

import java.util.regex.Pattern;

public class Task5 {
    private Task5() {
    }

    static boolean isRightPlate(String plateNumber) {
        Pattern pattern = Pattern.compile("^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}$");
        var matcher = pattern.matcher(plateNumber);

        return matcher.matches();
    }
}
