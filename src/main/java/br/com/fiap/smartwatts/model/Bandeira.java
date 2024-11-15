package br.com.fiap.smartwatts.model;

public enum Bandeira {

    VERDE("Verde"),
    AMARELA("Amarela"),
    VERMELHA("Vermelha");

    Bandeira(String label){this.label=label;}

    private final String label;

    public String getLabel() {return label;}
}
