package br.com.fiap.smartwattts.model;

public enum Estado {

    SAOPAULO("São Paulo"),
    BAHIA("Bahia"),
    RIODEJANEIRO("Rio de Janeiro"),
    ACRE("Acre"),
    ALAGOAS("Alagoas"),
    AMAPA("Amapá"),
    AMAZONAS("Amazonas"),
    CEARA("Ceará"),
    DISTRITOFEDERAL("Distrito Federal"),
    ESPIRITOSANTO("Espírito Santo"),
    GOIAS("Goiás"),
    MARANHAO("Maranhão"),
    MATO_GROSSO("Mato Grosso"),
    MATO_GROSSO_DO_SUL("Mato Grosso do Sul"),
    MINASGERAIS("Minas Gerais"),
    PARA("Pará"),
    PARAIBA("Paraíba"),
    PARANA("Paraná"),
    PERNAMBUCO("Pernambuco"),
    PIAUI("Piauí"),
    RIOGRANDEDONORTE("Rio Grande do Norte"),
    RIOGRANDEDOSUL("Rio Grande do Sul"),
    RONDONIA("Rondônia"),
    RORAIMA("Roraima"),
    SANTA_CATARINA("Santa Catarina"),
    SERGIPE("Sergipe"),
    TOCANTINS("Tocantins");

    Estado(String label){
        this.label = label;
    }

    private final String label;

    public String getLabel(){
        return label;
    }
}
