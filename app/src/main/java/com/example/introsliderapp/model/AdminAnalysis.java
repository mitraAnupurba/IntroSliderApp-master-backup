package com.example.introsliderapp.model;

public class AdminAnalysis {

    int positiveScore;
    int negativeScore;
    int neutralScore;

    public AdminAnalysis(int positiveScore, int negativeScore, int neutralScore) {
        this.positiveScore = positiveScore;
        this.negativeScore = negativeScore;
        this.neutralScore = neutralScore;
    }

    public AdminAnalysis() {
    }

    public int getPositiveScore() {
        return positiveScore;
    }

    public void setPositiveScore(int positiveScore) {
        this.positiveScore = positiveScore;
    }

    public int getNegativeScore() {
        return negativeScore;
    }

    public void setNegativeScore(int negativeScore) {
        this.negativeScore = negativeScore;
    }

    public int getNeutralScore() {
        return neutralScore;
    }

    public void setNeutralScore(int neutralScore) {
        this.neutralScore = neutralScore;
    }



}

