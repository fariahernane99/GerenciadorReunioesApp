package com.support.android.designlibdemo.controle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.support.android.designlibdemo.modelo.*;

import java.util.ArrayList;

public class BD extends SQLiteOpenHelper {
    private static final String TAG = "sql";

    // Nome do banco
    private static final String NOME_BANCO = "reunioes";
    private static final String TABELA1 = "aluno";
    private static final String TABELA2 = "ata";
    private static final String TABELA3 = "grupo";
    private static final String TABELA4 = "pauta";
    private static final String TABELA5 = "reuniao";
    private static final String TABELA6 = "servidor";
    private static final String TABELA7 = "aluno_grupo";
    private static final String TABELA8 = "servidor_grupo";
    private static final int VERSAO_BANCO = 1;

    public BD(Context context) {
        // context, nome do banco, factory, versão
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando a BD " + NOME_BANCO + "...");
        db.execSQL("create table if not exists " + TABELA1 + " (" +
                "aluMatricula text primary key," +
                "aluNome text, " +
                "aluEmail text" + ");" +
                //criando Ata
                "create table if not exists " + TABELA2 + " (" +
                "ataCodigo int primary key autoincrement," +
                "ataStatus text, " +
                "ata_reuCodigo int" + ");" +
                //criando Grupo
                "create table if not exists " + TABELA3 + " (" +
                "gruCodigo int primary key autoincrement," +
                "gruNome text, " +
                "gruDescricao text, " +
                "gruSiapeCoordenador int" + ");" +
                //criando Pauta
                "create table if not exists " + TABELA4 + " (" +
                "pauCodigo int primary key autoincrement," +
                "pauTitulo text, " +
                "pauDefinicao text, " +
                "pauEncaminhamento text," +
                "pau_ataCodigo int" + ");" +
                //criando Reuniao
                "create table if not exists " + TABELA5 + " (" +
                "reuCodigo int primary key autoincrement," +
                "reuNome text, " +
                "reuData text, " +
                "reuHorarioInicio text," +
                "reuHorarioFim text, " +
                "reuSiapeResponsavelAta int, " +
                "reuLocal text, " +
                "reu_gruCodigo int " + ");" +
                //criando Servidor
                "create table if not exists " + TABELA6 + " (" +
                "serSiape String primary key," +
                "serNome text, " +
                "serTelefone text, " +
                "serEmail text," +
                "serSenha text, " +
                "serArea text, " +
                "serResponsavelAta int, " +
                "serCoordenador int, " +
                "serDe int " + ");" +
                //criando Aluno_Grupo
                "create table if not exists " + TABELA7 + " (" +
                "algCodigo int autoincrement," +
                "alg_aluMatricula String," +
                "alg_gruCodigo int " + ");" +
                //criando Servidor_Grupo
                "create table if not exists " + TABELA8 + " (" +
                "segCodigo int autoincrement," +
                "seg_serSiape String," +
                "seg_gruCodigo int " + ");");
        Log.d(TAG, "BD " + NOME_BANCO + " criado com sucesso.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Caso mude a versão do banco de dados, podemos executar um SQL aqui
        if (oldVersion == 1 && newVersion == 2) {
            // Execute o script para atualizar a versão...
        }
    }

    // Insere um novo Aluno, ou atualiza se já existe.
    public long save(Aluno aluno) {
        String matricula = aluno.getMatricula();
        SQLiteDatabase db = getWritableDatabase();
        try {

            ContentValues values = new ContentValues();
            values.put("aluNome", aluno.getNome());
            values.put("aluEmail", aluno.getEmail());

            long id = Integer.parseInt(matricula);
            if (id != 0) {//SE O ID É DIFERENTE DE 0 ATUALIZA,

                String _id = String.valueOf(aluno.getMatricula());
                String[] whereArgs = new String[]{_id};

                // update contato set values = ... where _id=?
                int count = db.update(TABELA1, values, "aluMatricula=?", whereArgs);

                return count;
            } else { // SE O ID FOR 0, SIGNIFICA QUE NÃO TEM ID, ASSIM VAI INSERIR O DADO
                Log.d(TAG, aluno.toString());
                id = db.insert(TABELA1, "", values);
                return id;
            }
        } finally {
            db.close();
        }
    }

    // Insere uma nova Ata, ou atualiza se já existe.
    public long save(Ata ata) {
        long id = ata.getCodigo();
        SQLiteDatabase db = getWritableDatabase();
        try {

            ContentValues values = new ContentValues();
            values.put("ataStatus", ata.getStatus());
            values.put("ata_reuCodigo", ata.getAta_reuCodigo());

            if (id != 0) {//SE O ID É DIFERENTE DE 0 ATUALIZA,

                String _id = String.valueOf(ata.getCodigo());
                String[] whereArgs = new String[]{_id};

                // update contato set values = ... where _id=?
                int count = db.update(TABELA2, values, "ataCodigo=?", whereArgs);

                return count;
            } else { // SE O ID FOR 0, SIGNIFICA QUE NÃO TEM ID, ASSIM VAI INSERIR O DADO
                Log.d(TAG, ata.toString());
                id = db.insert(TABELA2, "", values);
                return id;
            }
        } finally {
            db.close();
        }
    }

    // Insere um novo Grupo, ou atualiza se já existe.
    public long save(Grupo grupo) {
        long id = grupo.getCodigo();
        SQLiteDatabase db = getWritableDatabase();
        try {

            ContentValues values = new ContentValues();
            values.put("gruNome", grupo.getNome());
            values.put("gruDescricao", grupo.getDescricao());
            values.put("gruSiapeCoordenador", grupo.getSiapeCoordenador());

            if (id != 0) {//SE O ID É DIFERENTE DE 0 ATUALIZA,

                String _id = String.valueOf(grupo.getCodigo());
                String[] whereArgs = new String[]{_id};

                // update contato set values = ... where _id=?
                int count = db.update(TABELA3, values, "gruCodigo=?", whereArgs);

                return count;
            } else { // SE O ID FOR 0, SIGNIFICA QUE NÃO TEM ID, ASSIM VAI INSERIR O DADO
                Log.d(TAG, grupo.toString());
                id = db.insert(TABELA3, "", values);
                return id;
            }
        } finally {
            db.close();
        }
    }

    // Insere um novo Ponto de Pauta, ou atualiza se já existe.
    public long save(Pauta pauta) {
        long id = pauta.getCodigo();
        SQLiteDatabase db = getWritableDatabase();
        try {

            ContentValues values = new ContentValues();
            values.put("pauTitulo", pauta.getTitulo());
            values.put("pauDefinicao", pauta.getDefinicao());
            values.put("pauEncaminhamento", pauta.getEncaminhamento());
            values.put("pau_ataCodigo", pauta.getPau_ataCodigo());

            if (id != 0) {//SE O ID É DIFERENTE DE 0 ATUALIZA,

                String _id = String.valueOf(pauta.getCodigo());
                String[] whereArgs = new String[]{_id};

                // update contato set values = ... where _id=?
                int count = db.update(TABELA4, values, "pauCodigo=?", whereArgs);

                return count;
            } else { // SE O ID FOR 0, SIGNIFICA QUE NÃO TEM ID, ASSIM VAI INSERIR O DADO
                Log.d(TAG, pauta.toString());
                id = db.insert(TABELA4, "", values);
                return id;
            }
        } finally {
            db.close();
        }
    }

    // Insere uma nova Reuniao, ou atualiza se já existe.
    public long save(Reuniao reuniao) {
        long id = reuniao.getCodigo();
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("reuNome", reuniao.getNome());
            values.put("reuData", reuniao.getData());
            values.put("reuHorarioInicio", reuniao.getHorarioInicio());
            values.put("reuHorarioFim", reuniao.getHorarioFim());
            values.put("reuLocal", reuniao.getLocal());
            values.put("reuSiapeResponsavelAta", reuniao.getSiapeResponsavelAta());
            values.put("reu_gruCodigo", reuniao.getReu_gruCodigo());

            if (id != 0) {//SE O ID É DIFERENTE DE 0 ATUALIZA,

                String _id = String.valueOf(reuniao.getCodigo());
                String[] whereArgs = new String[]{_id};

                // update contato set values = ... where _id=?
                int count = db.update(TABELA5, values, "reuCodigo=?", whereArgs);

                return count;
            } else { // SE O ID FOR 0, SIGNIFICA QUE NÃO TEM ID, ASSIM VAI INSERIR O DADO
                Log.d(TAG, reuniao.toString());
                id = db.insert(TABELA5, "", values);
                return id;
            }
        } finally {
            db.close();
        }
    }

    // Insere um novo Servidor, ou atualiza se já existe.
    public long save(Servidor servidor) {
        String siape = servidor.getSiape();
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("serNome", servidor.getNome());
            values.put("serEmail", servidor.getEmail());
            values.put("serArea", servidor.getArea());
            values.put("serTelefone", servidor.getFone());
            values.put("serSenha", servidor.getSenha());
            values.put("serDe", servidor.getSerDe());
            values.put("serCoordenador", servidor.getSerCoordenador());
            values.put("serResponsavelATA", servidor.getSerResponsavelAta());

            long id = Integer.parseInt(siape);
            if (id != 0) {//SE O ID É DIFERENTE DE 0 ATUALIZA,

                String _id = String.valueOf(servidor.getSiape());
                String[] whereArgs = new String[]{_id};

                // update contato set values = ... where _id=?
                int count = db.update(TABELA6, values, "serCodigo=?", whereArgs);

                return count;
            } else { // SE O ID FOR 0, SIGNIFICA QUE NÃO TEM ID, ASSIM VAI INSERIR O DADO
                Log.d(TAG, servidor.toString());
                id = db.insert(TABELA6, "", values);
                return id;
            }
        } finally {
            db.close();
        }
    }

    // Insere um novo AlunoGrupo.
    public void save(AlunoGrupo alunoGrupo) {
        String sql = "INSERT INTO " + TABELA7 + " (alg_aluMatricula, alg_gruCodigo) VALUES ('" +
                alunoGrupo.getAlg_aluMatricula() + "', " + alunoGrupo.getAlg_gruCodigo() + ");";
        execSQL(sql);
    }

    // Insere um novo ServidorGrupo.
    public void save(ServidorGrupo servidorGrupo) {
        String sql = "INSERT INTO " + TABELA8 + " (seg_serSiape, seg_gruCodigo) VALUES ('" +
                servidorGrupo.getSeg_serSiape() + "', " + servidorGrupo.getSeg_gruCodigo() + ");";
        execSQL(sql);
    }

    // Deleta o Aluno
    public int delete(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            // delete from Aluno where _id=?
            int count = db.delete(TABELA1, "aluMatricula=?", new String[]{String.valueOf(aluno.getMatricula())});
            Log.i(TAG, "Deletou [" + count + "] registro.");
            return count;
        } finally {
            db.close();
        }
    }

    // Deleta a Ata
    public int delete(Ata ata) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            // delete from Ata where _id=?
            int count = db.delete(TABELA2, "ataCodigo=?", new String[]{String.valueOf(ata.getCodigo())});
            Log.i(TAG, "Deletou [" + count + "] registro.");
            return count;
        } finally {
            db.close();
        }
    }

    // Deleta o Grupo
    public int delete(Grupo grupo) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            // delete from Grupo where _id=?
            int count = db.delete(TABELA3, "gruCodigo=?", new String[]{String.valueOf(grupo.getCodigo())});
            Log.i(TAG, "Deletou [" + count + "] registro.");
            return count;
        } finally {
            db.close();
        }
    }

    // Deleta o Ponto de Pauta
    public int delete(Pauta pauta) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            // delete from Pauta where _id=?
            int count = db.delete(TABELA4, "pauCodigo=?", new String[]{String.valueOf(pauta.getCodigo())});
            Log.i(TAG, "Deletou [" + count + "] registro.");
            return count;
        } finally {
            db.close();
        }
    }

    // Deleta a Reuniao
    public int delete(Reuniao reuniao) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            // delete from Reuniao where _id=?
            int count = db.delete(TABELA5, "reuCodigo=?", new String[]{String.valueOf(reuniao.getCodigo())});
            Log.i(TAG, "Deletou [" + count + "] registro.");
            return count;
        } finally {
            db.close();
        }
    }

    // Deleta o Servidor
    public int delete(Servidor servidor) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            // delete from Servidor where _id=?
            int count = db.delete(TABELA6, "serSiape=?", new String[]{String.valueOf(servidor.getSiape())});
            Log.i(TAG, "Deletou [" + count + "] registro.");
            return count;
        } finally {
            db.close();
        }
    }

    // Deleta o AlunoGrupo
    public int delete(AlunoGrupo alunoGrupo) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            // delete from AlunoGrupo where _id=?
            int count = db.delete(TABELA7, "algCodigo=?", new String[]{String.valueOf(alunoGrupo.getCodigo())});
            Log.i(TAG, "Deletou [" + count + "] registro.");
            return count;
        } finally {
            db.close();
        }
    }

    // Deleta o ServidorGrupo
    public int delete(ServidorGrupo servidorGrupo) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            // delete from ServidorGrupo where _id=?
            int count = db.delete(TABELA8, "segCodigo=?", new String[]{String.valueOf(servidorGrupo.getCodigo())});
            Log.i(TAG, "Deletou [" + count + "] registro.");
            return count;
        } finally {
            db.close();
        }
    }

    // Consulta a lista com todos os Alunos
    public ArrayList<Aluno> getAlunos() {
        SQLiteDatabase db = getWritableDatabase();
        try {
            // select * from Aluno
            Cursor c = db.query(TABELA1, null, null, null, null, null, null, null);
            return toListAlunos(c);
        } finally {
            db.close();
        }
    }

    // Consulta a lista com todos as Atas
    public ArrayList<Ata> getAtas() {
        SQLiteDatabase db = getReadableDatabase();
        try {
            // select * from Ata
            Cursor c = db.query(TABELA2, null, null, null, null, null, null, null);
            return toListAtas(c);
        } finally {
            db.close();
        }
    }

    // Consulta a lista com todos os Grupos
    public ArrayList<Grupo> getGrupos() {
        SQLiteDatabase db = getReadableDatabase();
        try {
            // select * from Grupo
            Cursor c = db.query(TABELA3, null, null, null, null, null, null, null);
            return toListGrupos(c);
        } finally {
            db.close();
        }
    }

    // Consulta a lista com todos os Pontos de Pauta
    public ArrayList<Pauta> getPautas() {
        SQLiteDatabase db = getReadableDatabase();
        try {
            // select * from Pauta
            Cursor c = db.query(TABELA4, null, null, null, null, null, null, null);
            return toListPautas(c);
        } finally {
            db.close();
        }
    }

    // Consulta a lista com todos as Reunioes
    public ArrayList<Reuniao> getReunioes() {
        SQLiteDatabase db = getReadableDatabase();
        try {
            // select * from Reuniao
            Cursor c = db.query(TABELA5, null, null, null, null, null, null, null);
            return toListReunioes(c);
        } finally {
            db.close();
        }
    }

    // Consulta a lista com todos os Servidores
    public ArrayList<Servidor> getServidores() {
        SQLiteDatabase db = getReadableDatabase();
        try {
            // select * from Servidor
            Cursor c = db.query(TABELA6, null, null, null, null, null, null, null);
            return toListServidores(c);
        } finally {
            db.close();
        }
    }

    // Consulta a lista com todos os AlunoGrupos
    public ArrayList<AlunoGrupo> getAlunosGrupo() {
        SQLiteDatabase db = getReadableDatabase();
        try {
            // select * from AlunoGrupo
            Cursor c = db.query(TABELA7, null, null, null, null, null, null, null);
            return toListAlunosGrupo(c);
        } finally {
            db.close();
        }
    }

    // Consulta a lista com todos os ServidorGrupos
    public ArrayList<ServidorGrupo> getServidoresGrupo() {
        SQLiteDatabase db = getReadableDatabase();
        try {
            // select * from ServidorGrupo
            Cursor c = db.query(TABELA8, null, null, null, null, null, null, null);
            return toListServidoresGrupos(c);
        } finally {
            db.close();
        }
    }

    // Consulta por sql testar depois
    public ArrayList<Aluno> getAlunosBySql(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        try {
            Cursor c = db.rawQuery(sql, null);
            ArrayList<Aluno> alunos = new ArrayList<Aluno>();
            if (c.moveToFirst()) {
                do {
                    Aluno aluno = new Aluno();
                    // recupera os atributos de Aluno
                    aluno.setMatricula(c.getString(c.getColumnIndex("aluMatricula")));
                    aluno.setNome(c.getString(c.getColumnIndex("aluNome")));
                    aluno.setEmail(c.getString(c.getColumnIndex("aluEmail")));

                    alunos.add(aluno);
                } while (c.moveToNext());
            }
            return alunos;
        } finally {
            db.close();
        }
    }

    // Consulta por sql testar depois
    public ArrayList<Ata> getAtasBySql(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        try {
            Cursor c = db.rawQuery(sql, null);
            ArrayList<Ata> atas = new ArrayList<Ata>();
            if (c.moveToFirst()) {
                do {
                    Ata ata = new Ata();
                    // recupera os atributos de Ata
                    ata.setCodigo(c.getInt(c.getColumnIndex("ataCodigo")));
                    ata.setStatus(c.getString(c.getColumnIndex("ataStatus")));
                    ata.setAta_reuCodigo(c.getInt(c.getColumnIndex("ata_reuCodigo")));

                    atas.add(ata);
                } while (c.moveToNext());
            }
            return atas;
        } finally {
            db.close();
        }
    }

    // Consulta por sql testar depois
    public ArrayList<Grupo> getGruposBySql(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        try {
            Cursor c = db.rawQuery(sql, null);
            ArrayList<Grupo> grupos = new ArrayList<Grupo>();
            if (c.moveToFirst()) {
                do {
                    Grupo grupo = new Grupo();
                    // recupera os atributos de Grupo
                    grupo.setCodigo(c.getInt(c.getColumnIndex("gruCodigo")));
                    grupo.setNome(c.getString(c.getColumnIndex("gruNome")));
                    grupo.setDescricao(c.getString(c.getColumnIndex("gruDescricao")));
                    grupo.setSiapeCoordenador(c.getString(c.getColumnIndex("gruSiapeCoordenador")));

                    grupos.add(grupo);
                } while (c.moveToNext());
            }
            return grupos;
        } finally {
            db.close();
        }
    }

    // Consulta por sql testar depois
    public ArrayList<Pauta> getPautasBySql(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        try {
            Cursor c = db.rawQuery(sql, null);
            ArrayList<Pauta> pautas = new ArrayList<Pauta>();
            if (c.moveToFirst()) {
                do {
                    Pauta pauta = new Pauta();
                    // recupera os atributos de Pontos de Pauta
                    pauta.setCodigo(c.getInt(c.getColumnIndex("pauCodigo")));
                    pauta.setTitulo(c.getString(c.getColumnIndex("pauTitulo")));
                    pauta.setDefinicao(c.getString(c.getColumnIndex("pauDescricao")));
                    pauta.setEncaminhamento(c.getString(c.getColumnIndex("pauEncaminhamento")));
                    pauta.setPau_ataCodigo(c.getInt(c.getColumnIndex("pau_ataCodigo")));

                    pautas.add(pauta);
                } while (c.moveToNext());
            }
            return pautas;
        } finally {
            db.close();
        }
    }

    // Consulta por sql testar depois
    public ArrayList<Reuniao> getReunioesBySql(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        try {
            Cursor c = db.rawQuery(sql, null);
            ArrayList<Reuniao> reunioes = new ArrayList<Reuniao>();
            if (c.moveToFirst()) {
                do {
                    Reuniao reuniao = new Reuniao();
                    // recupera os atributos de Reuniao
                    reuniao.setCodigo(c.getInt(c.getColumnIndex("reuCodigo")));
                    reuniao.setNome(c.getString(c.getColumnIndex("reuNome")));
                    reuniao.setData(c.getString(c.getColumnIndex("reuData")));
                    reuniao.setHorarioInicio(c.getString(c.getColumnIndex("reuHorarioInicio")));
                    reuniao.setHorarioFim(c.getString(c.getColumnIndex("reuHorarioFim")));
                    reuniao.setLocal(c.getString(c.getColumnIndex("reuLocal")));
                    reuniao.setSiapeResponsavelAta(c.getString(c.getColumnIndex("reuSiapeResponsavelAta")));
                    reuniao.setReu_gruCodigo(c.getInt(c.getColumnIndex("reu_gruCodigo")));

                    reunioes.add(reuniao);
                } while (c.moveToNext());
            }
            return reunioes;
        } finally {
            db.close();
        }
    }

    // Consulta por sql testar depois
    public ArrayList<Servidor> getServidoresBySql(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        try {
            Cursor c = db.rawQuery(sql, null);
            ArrayList<Servidor> servidores = new ArrayList<Servidor>();
            if (c.moveToFirst()) {
                do {
                    Servidor servidor = new Servidor();
                    // recupera os atributos de Servidor
                    servidor.setSiape(c.getString(c.getColumnIndex("serSiape")));
                    servidor.setNome(c.getString(c.getColumnIndex("serNome")));
                    servidor.setEmail(c.getString(c.getColumnIndex("serEmail")));
                    servidor.setTelefone(c.getString(c.getColumnIndex("serTelefone")));
                    servidor.setSenha(c.getString(c.getColumnIndex("serSenha")));
                    servidor.setArea(c.getString(c.getColumnIndex("serArea")));
                    servidor.setSerDe(c.getInt(c.getColumnIndex("serDe")));
                    servidor.setSerCoordenador(c.getInt(c.getColumnIndex("serCoordenador")));
                    servidor.setSerResponsavelAta(c.getInt(c.getColumnIndex("serResponsavelAta")));

                    servidores.add(servidor);
                } while (c.moveToNext());
            }
            return servidores;
        } finally {
            db.close();
        }
    }

    // Consulta por sql testar depois
    public ArrayList<AlunoGrupo> getAlunosGrupoBySql(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        try {
            Cursor c = db.rawQuery(sql, null);
            ArrayList<AlunoGrupo> alunoGrupos = new ArrayList<AlunoGrupo>();
            if (c.moveToFirst()) {
                do {
                    AlunoGrupo alunoGrupo = new AlunoGrupo();
                    // recupera os atributos de Aluno_Grupo
                    alunoGrupo.setCodigo(c.getInt(c.getColumnIndex("algCodigo")));
                    alunoGrupo.setAlg_aluMatricula(c.getString(c.getColumnIndex("alg_aluMatricula")));
                    alunoGrupo.setAlg_gruCodigo(c.getInt(c.getColumnIndex("alg_gruCodigo")));

                    alunoGrupos.add(alunoGrupo);
                } while (c.moveToNext());
            }
            return alunoGrupos;
        } finally {
            db.close();
        }
    }

    // Consulta por sql testar depois
    public ArrayList<ServidorGrupo> getServidoresGrupoBySql(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        try {
            Cursor c = db.rawQuery(sql, null);
            ArrayList<ServidorGrupo> servidorGrupos = new ArrayList<ServidorGrupo>();
            if (c.moveToFirst()) {
                do {
                    ServidorGrupo servidorGrupo = new ServidorGrupo();
                    // recupera os atributos de Servidor_Grupo
                    servidorGrupo.setCodigo(c.getInt(c.getColumnIndex("segCodigo")));
                    servidorGrupo.setSeg_serSiape(c.getString(c.getColumnIndex("seg_serSiape")));
                    servidorGrupo.setSeg_gruCodigo(c.getInt(c.getColumnIndex("seg_gruCodigo")));

                    servidorGrupos.add(servidorGrupo);
                } while (c.moveToNext());
            }
            return servidorGrupos;
        } finally {
            db.close();
        }
    }

    // Lê o cursor e cria a lista de Alunos
    private ArrayList<Aluno> toListAlunos(Cursor c) {
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        if (c.moveToFirst()) {
            do {
                Aluno aluno = new Aluno();
                // recupera os atributos de Aluno
                aluno.setMatricula(c.getString(c.getColumnIndex("aluMatricula")));
                aluno.setNome(c.getString(c.getColumnIndex("aluNome")));
                aluno.setEmail(c.getString(c.getColumnIndex("aluEmail")));

                alunos.add(aluno);
            } while (c.moveToNext());
        }
        return alunos;
    }

    // Lê o cursor e cria a lista de Atas
    private ArrayList<Ata> toListAtas(Cursor c) {
        ArrayList<Ata> atas = new ArrayList<Ata>();
        if (c.moveToFirst()) {
            do {
                Ata ata = new Ata();
                // recupera os atributos de Ata
                ata.setCodigo(c.getInt(c.getColumnIndex("ataCodigo")));
                ata.setStatus(c.getString(c.getColumnIndex("ataStatus")));
                ata.setAta_reuCodigo(c.getInt(c.getColumnIndex("ata_reuCodigo")));

                atas.add(ata);
            } while (c.moveToNext());
        }
        return atas;
    }

    // Lê o cursor e cria a lista de Grupos
    private ArrayList<Grupo> toListGrupos(Cursor c) {
        ArrayList<Grupo> grupos = new ArrayList<Grupo>();
        if (c.moveToFirst()) {
            do {
                Grupo grupo = new Grupo();
                // recupera os atributos de Grupo
                grupo.setCodigo(c.getInt(c.getColumnIndex("gruCodigo")));
                grupo.setNome(c.getString(c.getColumnIndex("gruNome")));
                grupo.setDescricao(c.getString(c.getColumnIndex("gruDescricao")));
                grupo.setSiapeCoordenador(c.getString(c.getColumnIndex("gruSiapeCoordenador")));

                grupos.add(grupo);
            } while (c.moveToNext());
        }
        return grupos;
    }

    // Lê o cursor e cria a lista de Pontos de Pauta
    private ArrayList<Pauta> toListPautas(Cursor c) {
        ArrayList<Pauta> pautas = new ArrayList<Pauta>();
        if (c.moveToFirst()) {
            do {
                Pauta pauta = new Pauta();
                // recupera os atributos de Pontos de Pauta
                pauta.setCodigo(c.getInt(c.getColumnIndex("pauCodigo")));
                pauta.setTitulo(c.getString(c.getColumnIndex("pauTitulo")));
                pauta.setDefinicao(c.getString(c.getColumnIndex("pauDescricao")));
                pauta.setEncaminhamento(c.getString(c.getColumnIndex("pauEncaminhamento")));
                pauta.setPau_ataCodigo(c.getInt(c.getColumnIndex("pau_ataCodigo")));

                pautas.add(pauta);
            } while (c.moveToNext());
        }
        return pautas;
    }

    // Lê o cursor e cria a lista de Reuniao
    private ArrayList<Reuniao> toListReunioes(Cursor c) {
        ArrayList<Reuniao> reunioes = new ArrayList<Reuniao>();
        if (c.moveToFirst()) {
            do {
                Reuniao reuniao = new Reuniao();
                // recupera os atributos de Reuniao
                reuniao.setCodigo(c.getInt(c.getColumnIndex("reuCodigo")));
                reuniao.setNome(c.getString(c.getColumnIndex("reuNome")));
                reuniao.setData(c.getString(c.getColumnIndex("reuData")));
                reuniao.setHorarioInicio(c.getString(c.getColumnIndex("reuHorarioInicio")));
                reuniao.setHorarioFim(c.getString(c.getColumnIndex("reuHorarioFim")));
                reuniao.setLocal(c.getString(c.getColumnIndex("reuLocal")));
                reuniao.setSiapeResponsavelAta(c.getString(c.getColumnIndex("reuSiapeResponsavelAta")));
                reuniao.setReu_gruCodigo(c.getInt(c.getColumnIndex("reu_gruCodigo")));

                reunioes.add(reuniao);
            } while (c.moveToNext());
        }
        return reunioes;
    }

    // Lê o cursor e cria a lista de Servidor
    private ArrayList<Servidor> toListServidores(Cursor c) {
        ArrayList<Servidor> servidores = new ArrayList<Servidor>();
        if (c.moveToFirst()) {
            do {
                Servidor servidor = new Servidor();
                // recupera os atributos de Servidor
                servidor.setSiape(c.getString(c.getColumnIndex("serSiape")));
                servidor.setNome(c.getString(c.getColumnIndex("serNome")));
                servidor.setEmail(c.getString(c.getColumnIndex("serEmail")));
                servidor.setTelefone(c.getString(c.getColumnIndex("serTelefone")));
                servidor.setSenha(c.getString(c.getColumnIndex("serSenha")));
                servidor.setArea(c.getString(c.getColumnIndex("serArea")));
                servidor.setSerDe(c.getInt(c.getColumnIndex("serDe")));
                servidor.setSerCoordenador(c.getInt(c.getColumnIndex("serCoordenador")));
                servidor.setSerResponsavelAta(c.getInt(c.getColumnIndex("serResponsavelAta")));

                servidores.add(servidor);
            } while (c.moveToNext());
        }
        return servidores;
    }

    // Lê o cursor e cria a lista de Aluno_Grupo
    private ArrayList<AlunoGrupo> toListAlunosGrupo(Cursor c) {
        ArrayList<AlunoGrupo> alunoGrupos = new ArrayList<AlunoGrupo>();
        if (c.moveToFirst()) {
            do {
                AlunoGrupo alunoGrupo = new AlunoGrupo();
                // recupera os atributos de Aluno_Grupo
                alunoGrupo.setCodigo(c.getInt(c.getColumnIndex("algCodigo")));
                alunoGrupo.setAlg_aluMatricula(c.getString(c.getColumnIndex("alg_aluMatricula")));
                alunoGrupo.setAlg_gruCodigo(c.getInt(c.getColumnIndex("alg_gruCodigo")));

                alunoGrupos.add(alunoGrupo);
            } while (c.moveToNext());
        }
        return alunoGrupos;
    }

    // Lê o cursor e cria a lista de Servidor_Grupo
    private ArrayList<ServidorGrupo> toListServidoresGrupos(Cursor c) {
        ArrayList<ServidorGrupo> servidorGrupos = new ArrayList<ServidorGrupo>();
        if (c.moveToFirst()) {
            do {
                ServidorGrupo servidorGrupo = new ServidorGrupo();
                // recupera os atributos de Servidor_Grupo
                servidorGrupo.setCodigo(c.getInt(c.getColumnIndex("segCodigo")));
                servidorGrupo.setSeg_serSiape(c.getString(c.getColumnIndex("seg_serSiape")));
                servidorGrupo.setSeg_gruCodigo(c.getInt(c.getColumnIndex("seg_gruCodigo")));

                servidorGrupos.add(servidorGrupo);
            } while (c.moveToNext());
        }
        return servidorGrupos;
    }

    public Reuniao getReuniao(int codigoReuniao){
        Reuniao reuniao = null;
        for (Reuniao reu : getReunioes()) {
            if (reu.getCodigo() == codigoReuniao)
                reuniao = reu;
        }
        return reuniao;
    }

    public Ata getAta(int codigoReuniao){
        Ata ata1 = null;
        for (Ata ata2 : getAtas()) {
            if (ata2.getAta_reuCodigo() == codigoReuniao)
                ata1 = ata2;
        }
        return ata1;
    }

    // Executa um SQL
    public void execSQL(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.execSQL(sql);
        } finally {
            db.close();
        }
    }

}
