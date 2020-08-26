package com.poker;

import java.util.*;

public class PlayPoker {
    public String play(List<String> black, List<String> white) {
        List<String> blackResult = JudgeLevel(black);
        List<String> whiteResult = JudgeLevel(white);
        Integer blackLevel = Integer.parseInt(blackResult.get(0));
        Integer whiteLevel = Integer.parseInt(whiteResult.get(0));
        String winner = "";
        String type = "";
        String explanation = "";

        if (blackLevel == whiteLevel && blackResult.get(0).equals("1")) {
            type = whiteResult.get(1);
            Integer maxWhite = Integer.parseInt(whiteResult.get(3));
            Integer maxBlack = Integer.parseInt(blackResult.get(3));
            if (maxWhite > maxBlack) {
                winner = "White";
                explanation=whiteResult.get(2);
            }
        }
        return winner + " wins. -with " + type + " : " + explanation;
    }

    private List<String> JudgeLevel(List<String> pokers) {
        System.out.println(String.valueOf(pokers));
        List<String> result = new ArrayList<>();
        Boolean isFlush = false;
        Boolean isStraight = true;
        List<Integer> pokersNumber = ExtractNumber(pokers);
        Set<String> pokersColor = ExtractColor(pokers);
        if (pokersColor.size() == 1) {
            isFlush = true;
        }
        Collections.sort(pokersNumber);
        isStraight = IsStraight(pokersNumber);
        if (isStraight && isFlush) {
            result.add(0, "9");
            result.add(1, "straight flush");
        }
        if (!isStraight && isFlush) {
            result.add(0, "6");
            result.add(1, "flush");
        }
        if (isStraight && !isFlush) {
            result.add(0, "5");
            result.add(1, "straight");
        }
        result.add(0, "1");
        result.add(1, "high card");
        String maxCharacter = numberToCharacter(pokersNumber.get(4));
        System.out.println("maxCharacter"+maxCharacter);
        result.add(2, maxCharacter);
        result.add(3,String.valueOf(pokersNumber.get(4)));
        System.out.println("isFlushaaaa" + pokersNumber.get(4));
        System.out.println(result.get(2));
        System.out.println("isFlush :" + isFlush);
        System.out.println("isStraight :" + isStraight);

        return result;
    }

    private String numberToCharacter(Integer number) {
        String maxCharacter =String.valueOf(number);
        if (number == 11) {
            maxCharacter = "Jack";
        }
        if (number == 12) {
            maxCharacter = "Queen";
        }
        if (number == 13) {
            maxCharacter = "King";
        }
        if (number == 14) {
            maxCharacter = "Ace";
        }

        return maxCharacter;
    }

    private List<Integer> ExtractNumber(List<String> pokers) {
        List<Integer> pokersNumber = new ArrayList<>();
        pokers.forEach(poker -> {
            String number = String.valueOf(poker.charAt(0));
            if (number.equals("T")) {
                pokersNumber.add(10);
            } else if (number.equals("J")) {
                pokersNumber.add(11);
            } else if (number.equals("Q")) {
                pokersNumber.add(12);
            } else if (number.equals("K")) {
                pokersNumber.add(13);
            } else if (number.equals("A")) {
                pokersNumber.add(14);
            } else {
                pokersNumber.add(Integer.parseInt(number));
            }
        });
        System.out.println("pokersNumber :" + pokersNumber);
        return pokersNumber;
    }

    private Set<String> ExtractColor(List<String> pokers) {
        Set<String> pokersColor = new HashSet<String>();
        pokers.forEach(poker -> {
            pokersColor.add(poker.substring(1));
        });
        System.out.println("pokersColor :" + pokersColor);
        return pokersColor;
    }


    private Boolean IsStraight(List<Integer> pokersNumber) {
        Boolean isStraight = true;
        for (int i = 0; i < pokersNumber.size() - 1; i++) {
            Integer differ = pokersNumber.get(i + 1) - pokersNumber.get(i);
            System.out.println(differ);
            if (differ != 1) {
                isStraight = false;
            }
        }
        return isStraight;
    }

}
