/*
 * BowlingGame.java
 * Date: 19.03.13
 * Time: 19:21
 *
 * @author Marcin Deryło, marcinderylo@gmail.com
 */
package com.blogspot.marcinderylo.tddworkshop;

public class BowlingGame {
    private int[] rolls = new int[21];
    private int currentRoll = 0;

    public void roll(int pins) {
        rolls[currentRoll++] = pins;
    }

    public int score() {
        int score = 0;
        int firstInFrame = 0;
        for (int frameNo = 0; frameNo < 10; frameNo++) {
            if (isStrike(firstInFrame)) {
                score += 10 + strikeBonus(firstInFrame);
                firstInFrame += 1;
            } else if (isSpare(firstInFrame)) {
                score += 10 + spareBonus(firstInFrame);
                firstInFrame += 2;
            } else {
                score += rolls[firstInFrame] + rolls[firstInFrame + 1];
                firstInFrame += 2;
            }
        }
        return score;
    }

    private int spareBonus(int firstInFrame) {
        return rolls[firstInFrame + 2];
    }

    private int strikeBonus(int firstInFrame) {
        return rolls[firstInFrame + 1] + rolls[firstInFrame + 2];
    }

    private boolean isSpare(int firstInFrame) {
        return rolls[firstInFrame] + rolls[firstInFrame + 1] == 10;
    }

    private boolean isStrike(int firstInFrame) {
        return rolls[firstInFrame] == 10;
    }

}
