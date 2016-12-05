package com.support.android.designlibdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;


import com.support.android.designlibdemo.controle.*;
import com.support.android.designlibdemo.modelo.*;

import java.util.ArrayList;

public class RedigirAta1Activity extends AppCompatActivity {

    private Servidor servidorLogado;
    private ArrayList<Servidor> arrayServidores;
    private ArrayList<Aluno> arrayAlunos;
    private String[] vetorGrupos, vetorReunioes;
	private BD bd;
    private ListView listView;
    private Spinner spinnerGrupo, spinnerReuniao;
    private EditText etHorarioInicio, etHorarioFim, etData, etLocal;
    private CheckBoxAdapterListView adapterListView;
    private Button btConfirmar;
    private ArrayList<String> arrayParticipantes = new ArrayList<String>();
    private ArrayList<Boolean> listaCheck = new ArrayList<Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redigir_ata1);
        spinnerGrupo = (Spinner) findViewById(R.id.spinnerRed1Grupos);
        spinnerReuniao = (Spinner) findViewById(R.id.spinnerRed1Reunioes);
        listView = (ListView) findViewById(R.id.listViewRed1Participantes);
        etData = (EditText) findViewById(R.id.editTextRed1Data);
        etHorarioInicio = (EditText) findViewById(R.id.editTextRed1HorInicio);
        etHorarioFim = (EditText) findViewById(R.id.editTextRed1HorFim);
        etLocal = (EditText) findViewById(R.id.editTextRed1Local);
        servidorLogado = LoginControl.retornaServidorLogado();
		bd = new BD(getApplicationContext());
        adapterListView = new CheckBoxAdapterListView(this);
        preencheGrupos();
        preencheReunioes((String) spinnerGrupo.getSelectedItem());
        preencheParticipantes();
        spinnerGrupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                // Atualiza o Spinner de Reunioes
                preencheReunioes((String) spinnerGrupo.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void preencheGrupos(){
        String sql = "SELECT gruCodigo, gruNome, gruDescricao, gruSiapeCoordenador FROM Grupo JOIN Servidor JOIN Servidor_Grupo " +
                "WHERE gruCodigo = seg_gruCodigo AND serSiape = seg_serSiape AND serSiape = '" + servidorLogado.getTelefone() + "';";
		ArrayList<Grupo> arrayGrupos = bd.getGruposBySql(sql);
		vetorGrupos = new String[arrayGrupos.size()];
		for (int i = 0; i < arrayGrupos.size(); i++){
			vetorGrupos[i] = arrayGrupos.get(i).getCodigo() + " - " + arrayGrupos.get(i).getNome();
		}
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, vetorGrupos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGrupo.setAdapter(adaptador);
    }

    private void preencheReunioes(String grupoSelecionado){
        int codigoGrupoSelecionado = Integer.parseInt(grupoSelecionado.split(" - ")[0]);
        String sql = "SELECT reuCodigo, reuNome, reuData, reuHorarioInicio, reuHorarioFim, reuLocal, reuSiapeResponsavelAta, " +
                "reu_gruCodigo FROM Reuniao JOIN Grupo WHERE gruCodigo = reu_gruCodigo AND  gruSiapeCoordenador = " + codigoGrupoSelecionado + ";";
        ArrayList<Reuniao> arrayReunioes = bd.getReunioesBySql(sql);
        vetorReunioes = new String[arrayReunioes.size()];
        for (int i = 0; i < arrayReunioes.size(); i++){
            vetorReunioes[i] = arrayReunioes.get(i).getCodigo() + " - " + arrayReunioes.get(i).getNome();
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, vetorReunioes);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerReuniao.setAdapter(adaptador);
    }

    private void preencheParticipantes(){
        arrayAlunos = bd.getAlunos();
        arrayServidores = bd.getServidores();
        for (Aluno alu :arrayAlunos){
            arrayParticipantes.add(alu.getMatricula() + " - " + alu.getNome());
        }
        for (Servidor ser :arrayServidores){
            arrayParticipantes.add(ser.getSiape() + " - " + ser.getNome());
        }
        adapterListView.setLista(arrayParticipantes);//atribuindo a lista vindo da principal no adapter
        adapterListView.setListaCheck(listaCheck);//atribuindo a lista vindo da principal no adapter
        listView.setAdapter(adapterListView);//setando o adapter
    }

    public void onClickButtonRed1Confirma(View v){
        String codGrupo = (String) spinnerGrupo.getSelectedItem();
        codGrupo = codGrupo.split(" - ")[0];
        String codReuniao = (String) spinnerReuniao.getSelectedItem();
        codReuniao = codReuniao.split(" - ")[0];
        int codigoGrupo = Integer.parseInt(codGrupo);
        int codigoReuniao = Integer.parseInt(codReuniao);
        Intent i = new Intent("reuniao");
        i.putExtra("codigoGrupo", codigoGrupo);
        i.putExtra("codigoReuniao", codigoReuniao);
        i.putStringArrayListExtra("arrayParticipantesCompareceram", retornaParticipantesCompareceram());
        i.putExtra("horarioInicio", etHorarioInicio.getText()+"");
        i.putExtra("horarioFim", etHorarioFim.getText()+"");
        i.putExtra("data", etData.getText()+"");
        i.putExtra("local", etLocal.getText()+"");
        startActivity(i);
    }

    public ArrayList<String> retornaParticipantesCompareceram(){
        ArrayList<Integer> indicesListView = new ArrayList<Integer>();// array que adiciona os indices do listview que estão com checkbox selecionado
        ArrayList<String> codigoNomeParticipantes = new ArrayList<String>();// array que armazena codigo e nome dos participantes
        for (int i = 0; i < listaCheck.size(); i++)
            if (listaCheck.get(i)) {// se o checkbox dessa posição está selecionado
                indicesListView.add(i);
            }
        for (int i = 0; i < indicesListView.size(); i++){
            codigoNomeParticipantes.add(arrayParticipantes.get(indicesListView.get(i)));// adiciona os participantes selecionados
        }
        return codigoNomeParticipantes;
    }

}
