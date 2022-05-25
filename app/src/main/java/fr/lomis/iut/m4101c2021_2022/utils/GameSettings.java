package fr.lomis.iut.m4101c2021_2022.utils;

import java.io.Serializable;

public class GameSettings implements Serializable {
    private final int max_nbr_digits; // nombre de chiffre maximum pour la taille des résultats
    private final int max_time_in_seconds_per_calcul; // Temps maximum par calcul (en secondes)
    private final int max_life; // Nombre maximum de vies pour une partie
    private final boolean addition; // Est ce qu'on fait des additions
    private final boolean soustraction; // Est ce qu'on fait des soustractions
    private final boolean multiplication; // Est ce qu'on fait des multiplications de nombre entiers
    private final boolean division; // Est ce qu'on fait des divisions où tout les nombres sont entiers
    private final boolean division_euclid; // Est ce qu'on fait des divisions euclidienne où on demande que le quotient
    private final boolean division_reste; // Est ce qu'on fait des divisions euclidienne où on demande que le reste

    private static final int MAX_TIME_IN_SECONDS_CHILL_MODE = 60;

    public GameSettings(int max_nbr_digits, int max_time_in_seconds_per_calcul, int max_life, boolean addition, boolean soustraction, boolean multiplication, boolean division, boolean division_euclid, boolean division_reste) {
        this.max_time_in_seconds_per_calcul = max_time_in_seconds_per_calcul;
        this.max_life = max_life;
        this.addition = addition;
        this.soustraction = soustraction;
        this.multiplication = multiplication;
        this.division = division;
        this.division_euclid = division_euclid;
        this.division_reste = division_reste;
        this.max_nbr_digits = max_nbr_digits;
    }

    public GameSettings(int max_nbr_digits, int max_time_in_seconds_per_calcul, int max_life, boolean addition, boolean soustraction, boolean multiplication, boolean division, boolean division_euclid) {
        this(max_nbr_digits, max_time_in_seconds_per_calcul, max_life, addition, soustraction, multiplication, division, division_euclid, false);
    }

    public GameSettings(int max_nbr_digits, int max_time_in_seconds_per_calcul, int max_life, boolean addition, boolean soustraction, boolean multiplication, boolean division) {
        this(max_nbr_digits, max_time_in_seconds_per_calcul, max_life, addition, soustraction, multiplication, division, false);
    }

    public GameSettings(int max_nbr_digits, int max_time_in_seconds_per_calcul, int max_life, boolean addition, boolean soustraction, boolean multiplication) {
        this(max_nbr_digits, max_time_in_seconds_per_calcul, max_life, addition, soustraction, multiplication, false);
    }

    public GameSettings(int max_nbr_digits, int max_time_in_seconds_per_calcul, int max_life, boolean addition, boolean soustraction) {
        this(max_nbr_digits, max_time_in_seconds_per_calcul, max_life, addition, soustraction, false);
    }

    public int getMax_life() {
        return max_life;
    }

    public boolean isAddition() {
        return addition;
    }

    public boolean isSoustraction() {
        return soustraction;
    }

    public boolean isMultiplication() {
        return multiplication;
    }

    public boolean isDivision() {
        return division;
    }

    public boolean isDivision_euclid() {
        return division_euclid;
    }

    public boolean isDivision_reste() {
        return division_reste;
    }

    public int getMax_nbr_digits() {
        return max_nbr_digits;
    }

    public int getMax_time_in_seconds_per_calcul() {
        return max_time_in_seconds_per_calcul;
    }

    public static GameSettings Try = new GameSettings(1, 30, 5, true, false);
    public static GameSettings Easy = new GameSettings(2, 10, 5, true, true);
    public static GameSettings Normal = new GameSettings(2, 10, 3, true, true, true, false);
    public static GameSettings Hard = new GameSettings(3, 10, 3, true, true, true, true, true, true);
    public static GameSettings Impossible = new GameSettings(3, 6, 2, true, true, true, true, true, true);

    public static GameSettings Chill_Easy = new GameSettings(2, MAX_TIME_IN_SECONDS_CHILL_MODE, 5, true, true);
    public static GameSettings Chill_Normal = new GameSettings(2, MAX_TIME_IN_SECONDS_CHILL_MODE, 5, true, true, true, false);
    public static GameSettings Chill_Hard = new GameSettings(3, MAX_TIME_IN_SECONDS_CHILL_MODE, 5, true, true, true, true, true, true);
    public static GameSettings Chill_Impossible = new GameSettings(3, MAX_TIME_IN_SECONDS_CHILL_MODE, 5, true, true, true, true, true, true);

}
