package com.example.bujimuapp;

import android.graphics.Color;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AppConfig {
    public static final int NITROGEN_COLOR_CODE = 2;
    public static final String NITROGEN_COLOR_EXTRA = "nitrogen";
    public static final String COLOR_GREEN = "green";
    public static final String COLOR_BLUE = "blue";
    public static final String COLOR_RED = "red";
    public static final int PHOSPHOROUS_COLOR_CODE = 3;
    public static final int PH_COLOR_CODE = 4;
    public static final String PHOSPHOROUS_STATE = "phosphorous_state";
    public static final String NITROGEN_STATE = "nitrogen_state";
    public static final String PHOSPHOROUS_REC = "phos_recommendation";
    public static final String NITROGEN_REC = "nitro_recommendation";
    public static final String PH_REC ="PH_recommendation";

    public static final int[] highPhosphous = {
            Color.rgb(0, 0, 0),

    };

    public static Map<Integer, String> highPhos = Arrays.stream(highPhosphous).boxed().collect(
            Collectors.toMap(Function.identity(), e -> "high")
    );

    public static final int [] adequatePhosphorous = {
        Color.rgb(255,255,255),
        Color.rgb(230,230,250),
        Color.rgb(176,224,230),
        Color.rgb(173,216,230),
        Color.rgb(135,206,250),
        Color.rgb(135,206,235),
        Color.rgb(0,191,255),
        Color.rgb(176,196,222),
        Color.rgb(30,144,255),
        Color.rgb(100,149,237),
        Color.rgb(70,130,180),
        Color.rgb(95,158,160),
        Color.rgb(123,104,238),
        Color.rgb(106,90,205),
        Color.rgb(72,61,139),
        Color.rgb(65,105,225),
        Color.rgb(0,0,255),
        Color.rgb(0,0,205),
        Color.rgb(0,0,139),
        Color.rgb(0,0,128),
        Color.rgb(25,25,112),
        Color.rgb(138,43,226),
        Color.rgb(75,0,130)
    };

    public static Map<Integer, String> adequatePhos = Arrays.stream(adequatePhosphorous).boxed().collect(
            Collectors.toMap(Function.identity(), e -> "adequate")
    );

    public static final int [] lowPhosphorous = {
            Color.rgb(255, 255, 255)
    };

    public static Map<Integer, String> lowPhos = Arrays.stream(lowPhosphorous).boxed().collect(
            Collectors.toMap(Function.identity(), e -> "low")
    );

    public static final int[] adequateNitrogen = {
            Color.rgb(0, 0, 0),
    };

    public static Map<Integer, String> adequateNiTro = Arrays.stream(adequateNitrogen).boxed().collect(
            Collectors.toMap(Function.identity(), e -> "adequate")
    );



    public static final int [] inAdequateNitrogen = {
            Color.rgb(255  , 255, 255)

//            Color.rgb(255, 255, 255),
    };

    public static Map<Integer, String> inAdequateNitro = Arrays.stream(inAdequateNitrogen).boxed().collect(
            Collectors.toMap(Function.identity(), e -> "inadequate")
    );

    public static final int [] noNitrogen = {
            Color.rgb(255  , 255, 255),
            Color.rgb(226, 230, 235),
            Color.rgb(193, 207, 218),
            Color.rgb(124, 148, 173),
            Color.rgb(143, 194, 213),
            Color.rgb(183, 224, 232),
            Color.rgb(247, 243, 229),
            Color.rgb(249, 241, 241),
            Color.rgb(246, 236, 233),
            Color.rgb(189, 163, 137),
            Color.rgb(167, 140, 116),
            Color.rgb(170, 143, 120),
            Color.rgb(174, 216, 247),
            Color.rgb(157, 185, 215),
            Color.rgb(191, 190, 195),
            Color.rgb(171, 150, 135),
            Color.rgb(191, 168, 155),
            Color.rgb(185, 162, 145),
            Color.rgb(185, 165, 160),
            Color.rgb(203, 189, 159),
            Color.rgb(173, 151, 131),
            Color.rgb(204, 187, 175),
            Color.rgb(172, 153, 164),
            Color.rgb(166, 156, 170),
            Color.rgb(133, 148, 173),
            Color.rgb(142, 159, 187),
            Color.rgb(96, 113, 147)
    };

    public static Map<Integer, String> noNitro = Arrays.stream(noNitrogen).boxed().collect(
            Collectors.toMap(Function.identity(), e -> "noNitro")
    );


}
