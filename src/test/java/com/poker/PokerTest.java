package com.poker;

import com.poker.PlayPoker;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PokerTest {
    @Test
    void should_return_White_wins_with_high_card_when_play_poker_given_Black_2H3D5S9CKD_White_2C3H4S8CAH() {
        //given
        List<String> black = Arrays.asList("2H", "3D", "5S", "9C", "KD");
        List<String> white = Arrays.asList("2C", "3H", "4S", "8C", "AH");

        //when
        PlayPoker playPoker = new PlayPoker();
        String result = playPoker.play(black, white);

        //then
        assertThat(result).isEqualTo("White wins. -with high card : Ace");
    }

    @Test
    void should_return_Black_wins_with_full_house_when_play_poker_given_Black_2H4S4C2D4H_White_2S8SASQS3S() {
        //given
        List<String> black = Arrays.asList("2H", "4S", "4C", "2D", "4H");
        List<String> white = Arrays.asList("2S", "8S", "AS", "QS", "3S");

        //when
        PlayPoker playPoker = new PlayPoker();
        String result = playPoker.play(black, white);

        //then
        assertThat(result).isEqualTo("Black wins. -with full house : 4 over 2");
    }
    @Test
    void should_return_Black_wins_with_high_card_when_play_poker_given_Black_2H3D5S9CKD_White_2C3H4S8CKH() {
        //given
        List<String> black = Arrays.asList("2H", "3D", "5S", "9C", "KH");
        List<String> white = Arrays.asList("2C", "3H", "4S", "8C", "KH");

        //when
        PlayPoker playPoker = new PlayPoker();
        String result = playPoker.play(black, white);

        //then
        assertThat(result).isEqualTo("Black wins. -with high card : 9");
    }
    @Test
    void should_return_Tie_when_play_poker_given_Black_2H3D5S9CKD_White_2D3H5C9SKH() {
        //given
        List<String> black = Arrays.asList("2H", "3D", "5S", "9C", "KH");
        List<String> white = Arrays.asList("2D", "3H", "5C", "9S", "KH");

        //when
        PlayPoker playPoker = new PlayPoker();
        String result = playPoker.play(black, white);

        //then
        assertThat(result).isEqualTo("Tie.");
    }
}
