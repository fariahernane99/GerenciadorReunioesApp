package com.support.android.designlibdemo.controle;

import android.content.Context;

import com.support.android.designlibdemo.modelo.*;
import com.support.android.designlibdemo.controle.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetListas {

    public static void geraAlunosJSON(JSONObject json, Context context) {
        try {
            //pega do json os registros da tag contato
            JSONArray linhas = (JSONArray) json.get("alunos");

            //se existe alguma linha no sub-json
            if (linhas.length() > 0) {
                for (int i = 0; i < linhas.length(); i++) {
                    //pega linha a linha..
                    JSONObject linha = (JSONObject) linhas.get(i);

                    //carrega objeto
                    Aluno a = new Aluno();
                    a.setMatricula(linha.getString("aluMatricula"));
                    a.setNome(linha.getString("aluNome"));
                    a.setEmail(linha.getString("aluEmail"));
                    //adiciona objeto ao bd
					BD bd = new BD(context);
					bd.save(a);
                }
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
	
	public static void geraAtasJSON(JSONObject json, Context context) {
        try {
            //pega do json os registros da tag contato
            JSONArray linhas = (JSONArray) json.get("atas");

            //se existe alguma linha no sub-json
            if (linhas.length() > 0) {
                for (int i = 0; i < linhas.length(); i++) {
                    //pega linha a linha..
                    JSONObject linha = (JSONObject) linhas.get(i);

                    //carrega objeto
                    Ata a = new Ata();
                    a.setCodigo(linha.getInt("ataCodigo"));
                    a.setStatus(linha.getString("ataStatus"));
                    a.setAta_reuCodigo(linha.getInt("ata_reuCodigo"));
                    //adiciona objeto ao bd
					BD bd = new BD(context);
					bd.save(a);
                }
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public static void geraGruposJSON(JSONObject json, Context context) {
        try {
            //pega do json os registros da tag contato
            JSONArray linhas = (JSONArray) json.get("grupos");

            //se existe alguma linha no sub-json
            if (linhas.length() > 0) {
                for (int i = 0; i < linhas.length(); i++) {
                    String texto = "";
                    //pega linha a linha..
                    JSONObject linha = (JSONObject) linhas.get(i);

                    //carrega objeto
                    Grupo g = new Grupo();
                    g.setCodigo(linha.getInt("gruCodigo"));
                    g.setDescricao(linha.getString("gruDescricao"));
                    g.setNome(linha.getString("gruNome"));
                    g.setSiapeCoordenador(linha.getString("gruSiapeCoordenador"));
                    //adiciona objeto ao bd
					BD bd = new BD(context);
					bd.save(g);
                }
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
	
    public static void geraPautasJSON(JSONObject json, Context context) {
        try {
            //pega do json os registros da tag contato
            JSONArray linhas = (JSONArray) json.get("pautas");

            //se existe alguma linha no sub-json
            if (linhas.length() > 0) {
                for (int i = 0; i < linhas.length(); i++) {
                    //pega linha a linha..
                    JSONObject linha = (JSONObject) linhas.get(i);

                    //carrega objeto
                    Pauta p = new Pauta();
                    p.setDefinicao(linha.getString("pauDefinicao"));
                    p.setEncaminhamento(linha.getString("pauEncaminhamento"));
                    p.setCodigo(linha.getInt("pauCodigo"));
                    p.setTitulo(linha.getString("pauTitulo"));
                    p.setPau_ataCodigo(linha.getInt("pau_ataCodigo"));
                    //adiciona objeto ao bd
					BD bd = new BD(context);
					bd.save(p);
                }
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
	
	public static void geraReunioesJSON(JSONObject json, Context context) {
        try {
            //pega do json os registros da tag contato
            JSONArray linhas = (JSONArray) json.get("reunioes");

            //se existe alguma linha no sub-json
            if (linhas.length() > 0) {
                for (int i = 0; i < linhas.length(); i++) {
                    //pega linha a linha..
                    JSONObject linha = (JSONObject) linhas.get(i);

                    //carrega objeto
                    Reuniao r = new Reuniao();
                    r.setCodigo(linha.getInt("reuCodigo"));
                    r.setData(linha.getString("reuData"));
                    r.setHorarioFim(linha.getString("reuHorarioFim"));
                    r.setHorarioInicio(linha.getString("reuHorarioInicio"));
                    r.setLocal(linha.getString("reuLocal"));
                    r.setNome(linha.getString("reuNome"));
                    r.setSiapeResponsavelAta(linha.getString("reuSiapeResponsavelAta"));
					r.setReu_gruCodigo(linha.getInt("reu_gruCodigo"));
					//adiciona objeto ao bd
					BD bd = new BD(context);
					bd.save(r);
                }
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
	
	public static void geraServidoresJSON(JSONObject json, Context context) {
        try {
            //pega do json os registros da tag contato
            JSONArray linhas = (JSONArray) json.get("servidores");
			
            //se existe alguma linha no sub-json
            if (linhas.length() > 0) {
                for (int i = 0; i < linhas.length(); i++) {
                    String texto = "";
                    //pega linha a linha..
                    JSONObject linha = (JSONObject) linhas.get(i);

                    Servidor s = new Servidor();
                    //carrega objeto
                    s.setSiape(linha.getString("serSiape"));
                    s.setNome(linha.getString("serNome"));
                    s.setArea(linha.getString("serArea"));
                    s.setEmail(linha.getString("serEmail"));
					s.setTelefone(linha.getString("serTelefone"));
                    s.setSenha(linha.getString("serSenha"));
					s.setSerDe(linha.getInt("serDe"));
                    s.setSerCoordenador(linha.getInt("serCoordenador"));
                    s.setSerResponsavelAta(linha.getInt("serResponsavelAta"));
                    //adiciona objeto ao bd
					BD bd = new BD(context);
					bd.save(s);
                }
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

	public static void geraAlunosGruposJSON(JSONObject json, Context context) {
        try {
            //pega do json os registros da tag contato
            JSONArray linhas = (JSONArray) json.get("alunosGrupo");

            //se existe alguma linha no sub-json
            if (linhas.length() > 0) {
                for (int i = 0; i < linhas.length(); i++) {
                    //pega linha a linha..
                    JSONObject linha = (JSONObject) linhas.get(i);

                    //carrega objeto
                    AlunoGrupo ag = new AlunoGrupo();
					ag.setCodigo(linha.getInt("algCodigo"));
                    ag.setAlg_aluMatricula(linha.getString("alg_aluMatricula"));
                    ag.setAlg_gruCodigo(linha.getInt("alg_gruCodigo"));
					//adiciona objeto ao bd
					BD bd = new BD(context);
					bd.save(ag);
                }
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
	
    public static void geraServidoresGruposJSON(JSONObject json, Context context) {
        try {
            //pega do json os registros da tag contato
            JSONArray linhas = (JSONArray) json.get("servidoresGrupo");

            //se existe alguma linha no sub-json
            if (linhas.length() > 0) {
                for (int i = 0; i < linhas.length(); i++) {
                    //pega linha a linha..
                    JSONObject linha = (JSONObject) linhas.get(i);

                    //carrega objeto
                    ServidorGrupo sg = new ServidorGrupo();
					sg.setCodigo(linha.getInt("segCodigo"));
                    sg.setSeg_serSiape(linha.getString("seg_serSiape"));
                    sg.setSeg_gruCodigo(linha.getInt("seg_gruCodigo"));
					//adiciona objeto ao bd
					BD bd = new BD(context);
					bd.save(sg);
                }
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
	
}
