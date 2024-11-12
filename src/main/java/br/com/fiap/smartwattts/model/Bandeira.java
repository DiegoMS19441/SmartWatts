package br.com.fiap.smartwattts.model;

public enum Bandeira {

    VERDE("Verde"),
    AMARELA("Amarela"),
    VERMELHA("Vermelha");

    Bandeira(String label){this.label=label;}

    private final String label;

    public String getLabel() {return label;}
}
