class Transportes {
    private int codigo;
    private String situacao;
    private double peso;
    private String tipoCarga;
    private int qtdPessoas;
    private boolean cargaPerigosa;
    private double tempMin, tempMax;

    public Transportes(int codigo, String situacao, double peso, String tipoCarga, int qtdPessoas, boolean cargaPerigosa, double tempMin, double tempMax) {
        this.codigo = codigo;
        this.situacao = situacao;
        this.peso = peso;
        this.tipoCarga = tipoCarga;
        this.qtdPessoas = qtdPessoas;
        this.cargaPerigosa = cargaPerigosa;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }


    public int getCodigo() {
    return codigo;
    }

    public int getQtdPessoas() {
        return qtdPessoas;
    }

    public boolean getCargaPerigosa() {
        return cargaPerigosa;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setCargaPerigosa(boolean cargaPerigosa) {
        this.cargaPerigosa = cargaPerigosa;
    }

    public void setQtdPessoas(int qtdPessoas) {
        this.qtdPessoas = qtdPessoas;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getTipoCarga(){
        return tipoCarga;
    }

    public String getSituacao() {
    return situacao;
}

public double getPeso() {
    return peso;
}

@Override
public String toString() {
    return "Código: " + codigo + ", Situação: " + situacao + ", Peso: " + peso;
}

}