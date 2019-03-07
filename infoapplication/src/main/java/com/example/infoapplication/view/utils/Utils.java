package com.example.infoapplication.view.utils;

import java.util.Random;

public class Utils {


    public static int randomNumber(int bound) {

        Random mRandom = new Random();
        int i = mRandom.nextInt(bound);
        if (20 < i && i < bound - 20) {
            return i;
        } else {
            if (i < 20) {
                return i + 20;
            } else {
                return i - 20;
            }
        }
    }
}
