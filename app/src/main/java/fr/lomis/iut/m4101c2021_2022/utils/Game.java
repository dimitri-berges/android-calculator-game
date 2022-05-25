package fr.lomis.iut.m4101c2021_2022.utils;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private GameSettings settings;
    private int currentLifeCount;
    private GameCalcul currentCalcul;

    int score;
    private GameEventListener gameOverListener = null;
    private GameEventListener scoreChangedListener = null;
    private GameEventListener healthChangedListener = null;

    public Game(GameSettings settings) {
        this.settings = settings;
        currentLifeCount = settings.getMax_life();
        score = 0;
    }

    public int getCurrentLifeCount() {
        return currentLifeCount;
    }

    private void setCurrentLifeCount(int currentLifeCount) {
        this.currentLifeCount = currentLifeCount;
        healthChangedListener.onEvent(currentLifeCount);
        if (this.currentLifeCount <= 0) {
            this.GameOver();
        }
    }
    private void decrementLife() {
        setCurrentLifeCount(currentLifeCount - 1);
    }

    public int getScore() {
        return score;
    }

    private void setScore(int score) {
        if (score < 0) return;
        scoreChangedListener.onEvent(score);
        this.score = score;
    }
    private void incrementScore() {
        setScore(score + 1);
    }

    private void GameOver() {
        if (gameOverListener != null)
            gameOverListener.onEvent(score);
    }

    public GameCalcul generateNewCalcul() {
        GameCalculOperator operator = pickRandomOperator();
        int leftN = pickRandomLeftNumber(operator);
        int rightN = pickRandomRightNumber(leftN, operator);
        int result = GameCalcul.execCalcul(leftN, operator, rightN);
        GameCalcul calcul;
        if (operator == GameCalculOperator.Division)
            calcul = new GameCalcul(rightN, operator, leftN, result);
        else
            calcul = new GameCalcul(leftN, operator, rightN, result);
        currentCalcul = calcul;
        return calcul;
    }

    private int pickRandomRightNumber(int leftN, GameCalculOperator operator) {
        if (operator == GameCalculOperator.Division) {
            int result = new Random().nextInt(20);
            return result * leftN;
        }
        int bound = (int) Math.pow(10, settings.getMax_nbr_digits());
        if (operator == GameCalculOperator.Soustraction)
            bound = Integer.min(bound, leftN);
        if (operator == GameCalculOperator.Multiplication) {
            bound = 20;
        }
        return new Random().nextInt(bound);
    }

    private int pickRandomLeftNumber(GameCalculOperator operator) {
        int bound = (int) Math.pow(10, settings.getMax_nbr_digits());
        if (operator == GameCalculOperator.Multiplication
        || operator == GameCalculOperator.Division)
            bound = 20;
        bound = Integer.max(bound, 1);
        return new Random().nextInt(bound);
    }

    private GameCalculOperator pickRandomOperator() {
        ArrayList<GameCalculOperator> operators = new ArrayList<>();
        if (settings.isAddition()) operators.add(GameCalculOperator.Addition);
        if (settings.isSoustraction()) operators.add(GameCalculOperator.Soustraction);
        if (settings.isMultiplication()) operators.add(GameCalculOperator.Multiplication);
        if (settings.isDivision()) operators.add(GameCalculOperator.Division);
        if (settings.isDivision_euclid()) operators.add(GameCalculOperator.Division_euclid);
        if (settings.isDivision_reste()) operators.add(GameCalculOperator.Division_reste);
        return operators.get(new Random().nextInt(operators.size()));
    }

    public boolean CompareCalcul(GameCalcul calcul, int userInput) {
        if (calcul.getResult() == userInput) {
            incrementScore();
            return true;
        } else {
            decrementLife();
            return false;
        }
    }

    public void setOnGameOverListener(GameEventListener listener) {
        this.gameOverListener = listener;
    }

    public void setOnScoreChangedListener(GameEventListener listener) {
        this.scoreChangedListener = listener;
    }

    public void setHealthChangedListener(GameEventListener healthChangedListener) {
        this.healthChangedListener = healthChangedListener;
    }

    public GameSettings getSettings() {
        return settings;
    }

    public GameCalcul getCurrentCalcul() {
        return currentCalcul;
    }
}
