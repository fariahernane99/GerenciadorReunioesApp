package com.support.android.designlibdemo.modelo;

public class Reuniao {

    private int codigo;
    private String nome;
    private String data;
    private String horarioInicio;
    private String horarioFim;
    private String local;
    private String siapeResponsavelAta;
    private int reu_gruCodigo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getSiapeResponsavelAta() {
        return siapeResponsavelAta;
    }

    public void setSiapeResponsavelAta(String siapeResponsavelAta) {
        this.siapeResponsavelAta = siapeResponsavelAta;
    }
	
	public int getReu_gruCodigo() {
        return reu_gruCodigo;
    }

    public void setReu_gruCodigo(int reu_gruCodigo) {
        this.reu_gruCodigo = reu_gruCodigo;
    }

}