package com.support.android.designlibdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.support.android.designlibdemo.controle.*;
import com.support.android.designlibdemo.modelo.*;


import java.util.ArrayList;

public class RedigirAta2Activity extends AppCompatActivity {

    private EditText etDefinicoes, etEncaminhamentos;
    private Spinner spinnerPontosPauta;
    private String[] vetorPautas;
    private CheckBoxAdapterListView adapterListView;
    private BD bd;
    private int codigoGrupo, codigoReuniao;
    private String horarioInicio, horarioFim, data, local;
    private ArrayList<String> arrayParticipantesCompareceram;
    private ArrayList<Pauta> arrayPautas;
    private int indicePonto = 0;
    private int codigoPautaAnterior;
    private String titutloPautaAnterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redigir_ata2);
        etDefinicoes = (EditText) findViewById(R.id.editTextRed2Definicoes);
        etEncaminhamentos = (EditText) findViewById(R.id.editTextRed2Encaminhamentos);
        spinnerPontosPauta = (Spinner) findViewById(R.id.spinnerRed2Pontos);
        bd = new BD(getApplicationContext());
        preencheDadosIntent();
        preenchePontos(codigoReuniao);
        spinnerPontosPauta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                // Atualiza as definiçoes e os encaminhamentos de acordo com o ponto de pauta
                if (indicePonto != 0) {
                    cadastraPonto();
                    String codigoTituloPontoSelecionado = (String) spinnerPontosPauta.getSelectedItem();
                    codigoPautaAnterior = Integer.parseInt(codigoTituloPontoSelecionado.split(" - ")[0]);
                    titutloPautaAnterior = codigoTituloPontoSelecionado.split(" - ")[1];
                } else {
                    indicePonto = 1;
                }
                etDefinicoes.setText(arrayPautas.get(posicao).getDefinicao());
                etEncaminhamentos.setText(arrayPautas.get(posicao).getEncaminhamento());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void cadastraPonto() {
        Pauta p = new Pauta();
        p.setCodigo(codigoPautaAnterior);
        p.setTitulo(titutloPautaAnterior);
        p.setDefinicao(etDefinicoes.getText() + "");
        p.setEncaminhamento(etEncaminhamentos.getText() + "");
        bd.save(p);//salva o ponto anterior e atualiza os campos para as definições e os encaminhamentos do selecionado
    }

    private void preenchePontos(int codigoReuniao) {
        String sql = "SELECT pauCodigo, pauTitulo, pauDefinicao, pauEncaminhamento FROM Pauta JOIN Ata JOIN Reuniao " +
                "WHERE ataCodigo = pau_ataCodigo AND reuCodigo = ata_reuCodigo AND reuCodigo = " + codigoReuniao + ";";
        arrayPautas = bd.getPautasBySql(sql);
        vetorPautas = new String[arrayPautas.size()];
        for (int i = 0; i < arrayPautas.size(); i++) {
            vetorPautas[i] = arrayPautas.get(i).getCodigo() + " - " + arrayPautas.get(i).getTitulo();
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, vetorPautas);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    private void preencheDadosIntent() {
        Intent i = getIntent();
        codigoGrupo = i.getIntExtra("codigoGrupo", 0);
        codigoReuniao = i.getIntExtra("codigoReuniao", 0);
        arrayParticipantesCompareceram = i.getStringArrayListExtra("arrayParticipantesCompareceram");
        horarioInicio = i.getStringExtra("horarioInicio");
        horarioFim = i.getStringExtra("horarioFim");
        data = i.getStringExtra("data");
        local = i.getStringExtra("local");
    }

    public void onClickButtonRed2Confirma(View v) {
        cadastraPonto();
        Reuniao r = bd.getReuniao(codigoReuniao);
        r.setData(data);
        r.setHorarioInicio(horarioInicio);
        r.setHorarioFim(horarioFim);
        r.setLocal(local);
        bd.save(r);
        Ata a = bd.getAta(codigoReuniao);
        a.setStatus("Concluída");
        bd.save(a);
        Servidor servidorLogado = LoginControl.retornaServidorLogado();
        boolean responsavelOutraAta = false;
        for (Reuniao reu : bd.getReunioes()) {
            if (reu.getCodigo() != codigoReuniao && reu.getSiapeResponsavelAta().equals(servidorLogado.getSiape()))
                responsavelOutraAta = true;
        }
        if (!responsavelOutraAta) {
            servidorLogado.setSerResponsavelAta(0);
            bd.save(servidorLogado);
        }
        startActivity(new Intent(this, MenuControl.class));
    }

}
