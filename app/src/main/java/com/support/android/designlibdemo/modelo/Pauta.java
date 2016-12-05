package com.support.android.designlibdemo.modelo;

public class Pauta {

    private int codigo;
    private String titulo;
    private String definicao;
    private String encaminhamento;

    public String getEncaminhamento() {
        return encaminhamento;
    }

    public void setEncaminhamento(String encaminhamento) {
        this.encaminhamento = encaminhamento;
    }
    private int pau_ataCodigo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int pauCodigo) {
        this.codigo = pauCodigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String pauTitulo) {
        this.titulo = pauTitulo;
    }

    public String getDefinicao() {
        return definicao;
    }

    public void setDefinicao(String pauDefinicao) {
        this.definicao = pauDefinicao;
    }

    public int getPau_ataCodigo() {
        return pau_ataCodigo;
    }

    public void setPau_ataCodigo(int pau_ataCodigo) {
        this.pau_ataCodigo = pau_ataCodigo;
    }

}
