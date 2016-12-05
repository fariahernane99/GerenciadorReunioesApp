package com.support.android.designlibdemo.controle;

import com.support.android.designlibdemo.modelo.*;
import com.support.android.designlibdemo.modelo.Aluno;

import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class PutListas {

	public static String geraJSONAlunos(ArrayList<Aluno> alunos) {
        ArrayList<JSONObject> tabelaAlunos = new ArrayList<JSONObject>();
        JSONObject registro;
        //cria um registro primeiro
        for (Aluno x : alunos) {
            registro = new JSONObject();
            try {
                registro.put("aluMatricula", x.getMatricula());
                registro.put("aluNome", x.getNome());
                registro.put("aluEmail",x.getEmail());
            } catch (JSONException k) {
            }
            //adiciona registro à lista de registros
            tabelaAlunos.add(registro);
        }

        //adiciona tabela
        JSONObject bd = new JSONObject();
        try {
            bd.putOpt("alunos", (Object) tabelaAlunos);
        } catch (JSONException u) {
        }

        String f = bd.toString();
        //gambiarra!!! para funcionar também com outras linguagens
        f = f.replace("\\", "");
        f = f.replace(":\"[", ":[");
        f = f.replace("]\"}", "]}");
        f = f.replace("]\"", "]");
        f = f.replace(", {", ",{");
        return f;
    }

    public static String geraJSONAtas(ArrayList<Ata> atas) {
        ArrayList<JSONObject> tabelaAta = new ArrayList<JSONObject>();
        JSONObject registro;
        //cria um registro primeiro
        for (Ata x : atas) {
            registro = new JSONObject();
            try {
                registro.put("ataCodigo", x.getCodigo());
                registro.put("ataStatus", x.getStatus());
                registro.put("ata_reuCodigo",x.getAta_reuCodigo());
            } catch (JSONException k) {
            }
            //adiciona registro à lista de registros
            tabelaAta.add(registro);
        }

        //adiciona tabela
        JSONObject bd = new JSONObject();
        try {
            bd.putOpt("atas", (Object) tabelaAta);
        } catch (JSONException u) {
        }

        String f = bd.toString();
        //gambiarra!!! para funcionar também com outras linguagens
        f = f.replace("\\", "");
        f = f.replace(":\"[", ":[");
        f = f.replace("]\"}", "]}");
        f = f.replace("]\"", "]");
        f = f.replace(", {", ",{");
        return f;
    }

    public static String geraJSONGrupos(ArrayList<Grupo> grupos) {
        ArrayList<JSONObject> tabelaGrupos = new ArrayList<JSONObject>();
        JSONObject registro;
        //cria um registro primeiro
        for (Grupo x : grupos) {
            registro = new JSONObject();
            try {
                registro.put("gruCodigo", x.getCodigo());
                registro.put("gruNome", x.getNome());
                registro.put("gruDescricao", x.getDescricao());
                registro.put("gruSiapeCoordenador", x.getSiapeCoordenador());
            } catch (JSONException k) {
            }
            //adiciona registro à lista de registros
            tabelaGrupos.add(registro);
        }

        //adiciona tabela
        JSONObject bd = new JSONObject();
        try {
            bd.putOpt("grupos", (Object) tabelaGrupos);
        } catch (JSONException u) {
        }

        String f = bd.toString();
        //gambiarra!!! para funcionar também com outras linguagens
        f = f.replace("\\", "");
        f = f.replace(":\"[", ":[");
        f = f.replace("]\"}", "]}");
        f = f.replace("]\"", "]");
        f = f.replace(", {", ",{");
        return f;
    }
	
    public static String geraJSONPautas(ArrayList<Pauta> pautas) {
        ArrayList<JSONObject> tabelaPontos = new ArrayList<JSONObject>();
        JSONObject registro;
        //cria um registro primeiro
        for (Pauta x : pautas) {
            registro = new JSONObject();
            try {
                registro.put("pauCodigo", x.getCodigo());
                registro.put("pauDefinicao", x.getDefinicao());
                registro.put("pauTitulo", x.getTitulo());
                registro.put("pauEncaminhamento", x.getEncaminhamento());
                registro.put("pau_ataCodigo", x.getPau_ataCodigo());
            } catch (JSONException k) {
            }
            //adiciona registro à lista de registros
            tabelaPontos.add(registro);
        }

        //adiciona tabela
        JSONObject bd = new JSONObject();
        try {
            bd.putOpt("pautas", (Object) tabelaPontos);
        } catch (JSONException u) {
        }

        String f = bd.toString();
        //gambiarra!!! para funcionar também com outras linguagens
        f = f.replace("\\", "");
        f = f.replace(":\"[", ":[");
        f = f.replace("]\"}", "]}");
        f = f.replace("]\"", "]");
        f = f.replace(", {", ",{");
        return f;
    }
	
	public static String geraJSONReunioes(ArrayList<Reuniao> reunioes) {
        ArrayList<JSONObject> tabelaReuniao = new ArrayList<JSONObject>();
        JSONObject registro;
        //cria um registro primeiro
        for (Reuniao x : reunioes) {
            registro = new JSONObject();
            try {
                registro.put("reuCodigo", x.getCodigo());
                registro.put("reuNome", x.getNome());
                registro.put("reuHorarioInicio",x.getHorarioInicio());
                registro.put("reuData", x.getData());
                registro.put("reuHorarioFim", x.getHorarioFim());
                registro.put("reuLocal", x.getLocal());
                registro.put("reuSiapeResponsavelAta", x.getSiapeResponsavelAta());
				registro.put("reu_gruCodigo", x.getReu_gruCodigo());
            } catch (JSONException k) {
            }
            //adiciona registro à lista de registros
            tabelaReuniao.add(registro);
        }

        //adiciona tabela
        JSONObject bd = new JSONObject();
        try {
            bd.putOpt("reunioes", (Object) tabelaReuniao);
        } catch (JSONException u) {
        }

        String f = bd.toString();
        //gambiarra!!! para funcionar também com outras linguagens
        f = f.replace("\\", "");
        f = f.replace(":\"[", ":[");
        f = f.replace("]\"}", "]}");
        f = f.replace("]\"", "]");
        f = f.replace(", {", ",{");
        return f;
    }
	
	public static String geraJSONServidores(ArrayList<Servidor> servidores) {
        ArrayList<JSONObject> tabelaServidores = new ArrayList<JSONObject>();
        JSONObject registro;
        //cria um registro primeiro
        for (Servidor x : servidores) {
            registro = new JSONObject();
            try {
                registro.put("serSiape", x.getSiape());
                registro.put("serEmail", x.getEmail());
                registro.put("serNome", x.getNome());
                registro.put("serTelefone", x.getTelefone());
                registro.put("serSenha", x.getSenha());
                registro.put("serArea", x.getArea());
                registro.put("serResponsavelAta", x.getSerResponsavelAta());
                registro.put("serCoordenador", x.getSerCoordenador());
                registro.put("serDe",x.getSerDe());
            } catch (JSONException k) {
            }
            //adiciona registro à lista de registros
            tabelaServidores.add(registro);
        }

        //adiciona tabela
        JSONObject bd = new JSONObject();
        try {
            bd.putOpt("servidores", (Object) tabelaServidores);
        } catch (JSONException u) {
        }

        String f = bd.toString();
        //gambiarra!!! para funcionar também com outras linguagens
        f = f.replace("\\", "");
        f = f.replace(":\"[", ":[");
        f = f.replace("]\"}", "]}");
        f = f.replace("]\"", "]");
        f = f.replace(", {", ",{");
        return f;
    }
	
	public static String geraJSONAlunosGrupo(ArrayList<AlunoGrupo> alunosGrupo) {
        ArrayList<JSONObject> tabelaAlunoGrupo = new ArrayList<JSONObject>();
        JSONObject registro;
        //cria um registro primeiro
        for (AlunoGrupo x : alunosGrupo) {
            registro = new JSONObject();
            try {
				registro.put("algCodigo", x.getCodigo());
                registro.put("alg_aluMatricula", x.getAlg_aluMatricula());
                registro.put("alg_gruCodigo", x.getAlg_gruCodigo());
            } catch (JSONException k) {
            }
            //adiciona registro à lista de registros
            tabelaAlunoGrupo.add(registro);
        }

        //adiciona tabela
        JSONObject bd = new JSONObject();
        try {
            bd.putOpt("alunosGrupo", (Object) tabelaAlunoGrupo);
        } catch (JSONException u) {
        }

        String f = bd.toString();
        //gambiarra!!! para funcionar também com outras linguagens
        f = f.replace("\\", "");
        f = f.replace(":\"[", ":[");
        f = f.replace("]\"}", "]}");
        f = f.replace("]\"", "]");
        f = f.replace(", {", ",{");
        return f;
    }
	
	public static String geraJSONServidoresGrupo(ArrayList<ServidorGrupo> servidoresGrupo) {
        ArrayList<JSONObject> tabelaServidorGrupo = new ArrayList<JSONObject>();
        JSONObject registro;
        //cria um registro primeiro
        for (ServidorGrupo x : servidoresGrupo) {
            registro = new JSONObject();
            try {
				registro.put("segCodigo", x.getCodigo());
                registro.put("seg_serSiape", x.getSeg_serSiape());
                registro.put("seg_gruCodigo", x.getSeg_gruCodigo());
            } catch (JSONException k) {
            }
            //adiciona registro à lista de registros
            tabelaServidorGrupo.add(registro);
        }

        //adiciona tabela
        JSONObject bd = new JSONObject();
        try {
            bd.putOpt("servidoresGrupo", (Object) tabelaServidorGrupo);
        } catch (JSONException u) {
        }

        String f = bd.toString();
        //gambiarra!!! para funcionar também com outras linguagens
        f = f.replace("\\", "");
        f = f.replace(":\"[", ":[");
        f = f.replace("]\"}", "]}");
        f = f.replace("]\"", "]");
        f = f.replace(", {", ",{");
        return f;
    }

}
