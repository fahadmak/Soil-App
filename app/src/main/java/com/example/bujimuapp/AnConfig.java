package com.example.bujimuapp;

import android.graphics.Color;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AnConfig {
    public static final int NITROGEN_COLOR_CODE = 2;
    public static final String NITROGEN_COLOR_EXTRA = "nitrogen";
    public static final String COLOR_GREEN = "green";
    public static final String COLOR_BLUE = "blue";
    public static final String COLOR_RED = "red";
    public static final int PHOSPHOROUS_COLOR_CODE = 3;
    public static final int PH_COLOR_CODE = 4;
    public static final String PHOSPHOROUS_STATE = "phosphorous_state";
    public static final String NITROGEN_STATE = "nitrogen_state";

    public static final int[] highPhosphous = {
            Color.rgb(77, 69, 78),
            Color.rgb(32, 24, 46),
            Color.rgb(61, 58, 82),
            Color.rgb(82, 75, 99),
            Color.rgb(57, 64, 99),
            Color.rgb(46, 45, 73),
            Color.rgb(101, 103, 141),
            Color.rgb(83, 90, 125),
            Color.rgb(90, 93, 123),
            Color.rgb(76, 83, 115),
            Color.rgb(85, 88, 115),
            Color.rgb(154, 165, 178),
            Color.rgb(20, 14, 38),
            Color.rgb(42, 35, 69),
            Color.rgb(167, 151, 188),
            Color.rgb(126, 117, 149),
            Color.rgb(94, 73, 102),
            Color.rgb(97, 75, 106),
            Color.rgb(30, 16, 42),
            Color.rgb(124, 148, 173),
            Color.rgb(143, 194, 213),
            Color.rgb(157, 185, 215),
            Color.rgb(142, 159, 187),
    };

    public static Map<Integer, String> highPhos = Arrays.stream(highPhosphous).boxed().collect(
            Collectors.toMap(Function.identity(), e -> "high")
    );

    public static final int [] adequatePhosphorous = {
            Color.rgb(158, 163, 179),
            Color.rgb(176, 181, 193),
            Color.rgb(179, 187, 198),
            Color.rgb(192, 202, 213),
            Color.rgb(168, 176, 190),
            Color.rgb(176, 181, 195),
            Color.rgb(193, 209, 227),
            Color.rgb(165, 179, 200),
            Color.rgb(222, 236, 246),
            Color.rgb(172, 182, 199),
            Color.rgb(161, 174, 193),
            Color.rgb(165, 184, 210),
            Color.rgb(164, 182, 206),
            Color.rgb(197, 215, 232),
            Color.rgb(197, 215, 231),
            Color.rgb(181, 198, 220),
            Color.rgb(187, 201, 222),
            Color.rgb(179, 188, 203),
            Color.rgb(201, 222, 239),
            Color.rgb(245, 253, 255),
    };

    public static Map<Integer, String> adequatePhos = Arrays.stream(adequatePhosphorous).boxed().collect(
            Collectors.toMap(Function.identity(), e -> "adequate")
    );

    public static final int [] lowPhosphorous = {
            Color.rgb(236, 241, 242),
            Color.rgb(245, 242, 229),
            Color.rgb(191, 168, 174),
            Color.rgb(170, 145, 154),
            Color.rgb(255, 253, 244),
            Color.rgb(249, 225, 205),
            Color.rgb(254, 229, 206),
            Color.rgb(255, 247, 242),
            Color.rgb(251, 244, 241),
            Color.rgb(136, 98, 83),
            Color.rgb(180, 133, 102),
            Color.rgb(179, 144, 128),
            Color.rgb(202, 173, 158),
            Color.rgb(172, 145, 135),
            Color.rgb(148, 119, 109),
            Color.rgb(125, 93, 82),
            Color.rgb(238, 202, 170),
            Color.rgb(231, 185, 142),
            Color.rgb(243, 220, 203),
            Color.rgb(245, 225, 207),
            Color.rgb(245, 253, 255),
            Color.rgb(222, 219, 227),
            Color.rgb(154, 165, 178),
    };

    public static Map<Integer, String> lowPhos = Arrays.stream(lowPhosphorous).boxed().collect(
            Collectors.toMap(Function.identity(), e -> "low")
    );

    public static final int[] adequateNitrogen = {
            Color.rgb(132, 0, 13),
            Color.rgb(133, 0, 11),
            Color.rgb(135, 0, 13),
            Color.rgb(132, 0, 13),
            Color.rgb(127, 0, 8),
            Color.rgb(126, 0, 7),
            Color.rgb(130, 0, 10),
            Color.rgb(55, 102, 169),
            Color.rgb(80, 144, 203),
            Color.rgb(59, 100, 150),
            Color.rgb(65, 130, 186),
            Color.rgb(97, 150, 204),
            Color.rgb(92, 118, 164),
            Color.rgb(64, 104, 175),
            Color.rgb(81, 88, 130),
            Color.rgb(41, 61, 116),
            Color.rgb(31, 47, 107),
            Color.rgb(30, 49, 116),
            Color.rgb(116, 132, 200),
            Color.rgb(69, 97, 172),
            Color.rgb(101, 134, 201),
            Color.rgb(97, 95, 153),
            Color.rgb(56, 65, 128),
            Color.rgb(42, 48, 105),
            Color.rgb(84, 106, 160),
            Color.rgb(91, 117, 169),
            Color.rgb(60, 72, 127),
            Color.rgb(73, 80, 132),
            Color.rgb(63, 90, 155),
            Color.rgb(32, 61, 131),
            Color.rgb(22, 70, 149),

            Color.rgb(131, 92, 12),
            Color.rgb( 139, 0, 2),
            Color.rgb( 145, 0, 2),
            Color.rgb( 148, 0, 3),
            Color.rgb( 137, 0, 2),
            Color.rgb( 141, 0, 2),
            Color.rgb( 149, 0, 5),
            Color.rgb( 139, 0, 2),
            Color.rgb( 136, 0, 1),
            Color.rgb( 136, 0, 1),
            Color.rgb( 139, 0, 1),
            Color.rgb( 136, 0, 1),
            Color.rgb( 138, 0, 2),
            Color.rgb( 147, 0, 3),
            Color.rgb( 138, 0, 2),
            Color.rgb( 140, 0, 2),
            Color.rgb( 137, 0, 2),
            Color.rgb (145, 0, 26),
            Color.rgb(37, 0, 21),
            Color.rgb(143, 0, 26),
            Color.rgb(139, 0, 19),
            Color.rgb(153, 0, 35)
    };


    public static final int [] inAdequateNitrogen = {
            Color.rgb(115, 145, 191),
            Color.rgb(101, 126, 176),
            Color.rgb(82, 123, 184),
            Color.rgb(75, 113, 170),
            Color.rgb(161, 181, 207),
            Color.rgb(150, 161, 185),
            Color.rgb(149, 156, 179),
            Color.rgb(191, 219, 236),
            Color.rgb(136, 177, 214),
            Color.rgb(65, 117, 214),
            Color.rgb(81, 184, 236),
            Color.rgb(121, 167, 210),
            Color.rgb(106, 147, 192),
            Color.rgb(32, 170, 235),
            Color.rgb(122, 203, 245),
//            Color.rgb(255, 255, 255),
    };

    public static final int [] noNigrogen = {
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


}
