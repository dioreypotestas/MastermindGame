package com.example.diorey_potestas.mastermindgame;


public class Mastermind_Class {
    //already working
    public int[] code_pattern = new int[4];

    public Mastermind_Class() {
    }

    //returns random index....
    public int random_index(int from, int to) {
        return (int) Math.floor((Math.random() * (to - from)) + from);
    }

    public void generate_code_pattern(int[] colors, int colorSlots) {
        for (int i = 0; i < colorSlots; i++) {
            code_pattern[i] = colors[random_index(0, 8)];
        }
    }

    public int[] getPattern() {
        return this.code_pattern;
    }

    public int[] evaluate(int[] inputG) {
        int[] tempCodeColor = new int[4];
        int[] result = {0, 0};//result[0] --> correct code; result[1] -->wrong position
        int i;
        for (i = 0; i < code_pattern.length; i++) {
            tempCodeColor[i] = code_pattern[i];
        }
        for (i = 0; i < tempCodeColor.length; i++) {
            if (tempCodeColor[i] == (inputG[i])) {
                result[0]++;
                tempCodeColor[i] = -1;
                inputG[i] = -1;
            }
        }
        for (i = 0; i < tempCodeColor.length; i++) {
            if (tempCodeColor[i] != -1) {
                for (int j = 0; j < inputG.length; j++) {
                    if (tempCodeColor[i] == (inputG[j])) {
                        result[1]++;
                        inputG[j] = -1;
                        break;
                    }
                }
            }
        }
        return result;
    }
}
