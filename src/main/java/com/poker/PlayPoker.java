package com.poker;

import org.omg.CORBA.INTERNAL;

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
            List<Integer> blackNumber = ExtractNumber(black);
            List<Integer> whiteNumber = ExtractNumber(white);
            for (int i = blackNumber.size() - 1; i > 0; i--) {
                if (blackNumber.get(i) > whiteNumber.get(i)) {
                    winner = "Black";
                    explanation = numberToCharacter(blackNumber.get(i));
                    break;
                }
                if (blackNumber.get(i) < whiteNumber.get(i)) {
                    winner = "White";
                    explanation = numberToCharacter(whiteNumber.get(i));
                    break;
                }
                if (blackNumber.get(i) == whiteNumber.get(i)) {
                    blackNumber.remove(i);
                    whiteNumber.remove(i);
                    if (blackNumber.size() == 0) {
                        return "Tie.";
                    }
                }
            }
        }
        if (blackLevel > whiteLevel) {
            winner = "Black";
            type = blackResult.get(1);
            explanation = blackResult.get(2);
        }
        return winner + " wins. -with " + type + " : " + explanation;
    }

    private List<String> JudgeLevel(List<String> pokers) {
        List<String> result = new ArrayList<>();
        Boolean isFlush = false;
        Boolean isStraight = true;
        String explanation = "";
        List<Integer> pokersNumber = ExtractNumber(pokers);
        Set<String> pokersColor = ExtractColor(pokers);
        if (pokersColor.size() == 1) {
            isFlush = true;
        }
        Collections.sort(pokersNumber);
        String type = RepeatNumber(pokersNumber).get(0);
        explanation = RepeatNumber(pokersNumber).get(1);
        isStraight = IsStraight(pokersNumber);
        if (isStraight && isFlush) {
            result.add(0, "9");
            result.add(1, "straight flush");
        } else if (type.equals("4")) {
            result.add(0, "7");
            result.add(1, "full house");
        } else if (!isStraight && isFlush) {
            result.add(0, "6");
            result.add(1, "flush");
        } else if (isStraight && !isFlush) {
            result.add(0, "5");
            result.add(1, "straight");
        } else {
            result.add(0, "1");
            result.add(1, "high card");
            explanation = numberToCharacter(pokersNumber.get(4));

        }
        result.add(2, explanation);

        return result;
    }

    private List<String> RepeatNumber(List<Integer> pokersNumber) {
        List<String> repeatNumberOfTypeAndExplanation = new ArrayList<>();
        String type = "0";
        String explanation = "";
        Map<Integer, Integer> repeatNumber = new HashMap<>();
        for (Integer number : pokersNumber) {
            Integer count = 1;
            if (repeatNumber.get(number) != null) {
                count = repeatNumber.get(number) + 1;
            }
            repeatNumber.put(number, count);
        }
        if (repeatNumber.size() == 4) {
            type = "1";
        }
        if (repeatNumber.size() == 3) {
            if (repeatNumber.containsValue(2)) {
                type = "2";
            } else {
                type = "3";
            }
        }
        if (repeatNumber.size() == 2) {
            if (repeatNumber.containsValue(3)) {
                type = "4";
                Integer max = 0;
                Integer min = 0;
                for (Map.Entry<Integer, Integer> entry : repeatNumber.entrySet()) {
                    if (entry.getValue() == 3) {
                        max = entry.getKey();
                    } else {
                        min = entry.getKey();
                    }
                }
                explanation = max + " over " + min;
            } else {
                type = "5";
            }
        }
        repeatNumberOfTypeAndExplanation.add(0, type);
        repeatNumberOfTypeAndExplanation.add(1, explanation);
        return repeatNumberOfTypeAndExplanation;
    }


    private String numberToCharacter(Integer number) {
        String maxCharacter = String.valueOf(number);
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
        return pokersNumber;
    }

    private Set<String> ExtractColor(List<String> pokers) {
        Set<String> pokersColor = new HashSet<String>();
        pokers.forEach(poker -> {
            pokersColor.add(poker.substring(1));
        });
        return pokersColor;
    }


    private Boolean IsStraight(List<Integer> pokersNumber) {
        Boolean isStraight = true;
        for (int i = 0; i < pokersNumber.size() - 1; i++) {
            Integer differ = pokersNumber.get(i + 1) - pokersNumber.get(i);
            if (differ != 1) {
                isStraight = false;
            }
        }
        return isStraight;
    }

}
