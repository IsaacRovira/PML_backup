/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pml_backup;
import java.sql.*;
import java.util.*;
import java.io.*;
import org.sqlite.*;
/**
 *
 * @author Isaac
 * 
 * Crear la base de datos.
 * Crear las tablas.
 * 
 */
public class sqlitedb {
    static final String USER = "usuario";
    static final String PASS = "password";
    static final String JDBC_SQL = "org.sqlite.JDBC";
    
    String path;
    String url;
    String nombredb;
    Connection conn;    
    PreparedStatement pstmt;
    Map<String,String> stmts_create;
    Map<String,String> stmts_insert;
    
    
    public sqlitedb(){
        nombredb = null;
        path = null;
        url = null;        
        conn = null;
        pstmt=null;
        stmts_create = mapCreateStatements();
        stmts_insert = mapInsertStatements();
    }          
    public sqlitedb(String nombredb, String path){
        this.nombredb = nombredb;
        this.path = path;        
        url = path + nombredb;        
        conn = null;
        pstmt=null;
        stmts_create = mapCreateStatements();
        stmts_insert = mapInsertStatements();
    }
    
    private Map<String, String> mapCreateStatements(){
        //Create tables statements
        HashMap<String,String> map = new HashMap<>();
        map.put("Crear_AnalyseA", "CREATE TABLE AnalyseA(DOSID integer null,CHIID integer null,BILID integer null,RESULT float null,RESCRITIQUE char(1) null,LAST char(1) null,DEMID integer not null,LIBELLE varchar(20) null,SAMPLEID varchar(16) null,FLAGS varchar(15) null,rerun integer null,ANAID integer null,fdilut float null,resvalidusr integer null)");
        map.put("Crear_Commentaire", "CREATE TABLE Commentaire(COMID integer not null,COMMENTAIRE text null)");
        map.put("Crear_Demo", "CREATE TABLE Demo (DEMID integer not null,DEMIPP varchar (25) null,DEMNOM varchar (20) null,DEMPRENOM varchar(20) null,DEMNOMJF varchar(20) null,DEMSEXE char(1) null,DEMAGE float null,DEMAGEUNIT char(1) null,DEMDATENAI datetime null,DEMCOMMENT varchar(256) null,DEMADRESSE varchar(75) null,DEMMARK char(1) null,DEMSERVICE integer null,DEMDOCTOR integer null,DEMVetTypeId integer null)");
        map.put("Crear_Dossier", "CREATE TABLE Dossier(DOSID integer not null,DEMID integer null,DOSDATE datetime null,DOSTIME datetime null,DOSSERVICE integer null,COMMENT1 integer null,COMMENT2 integer null,DOSDOCTOR integer null,DOSAGE float null,DOSAGEUNIT char(1) null,DOSLastDATERUN datetime null,DOSLastANAID integer null,DOSLastTUBEGODET integer null,DOSLastTUBEPLATEAU integer null,DOSLastSAMPLEID varchar(16) null,DemDateTime datetime null,PrelevDateTime datetime null,ArchDateTime datetime null,DosBackup integer null)");
        map.put("Crear_Key", "CREATE TABLE KEY_VALUE(KEY varchar(64) not null,VALUE text null)");
        map.put("Crear_Run_A_EQC", "CREATE TABLE RUN_A_EQC(runAID integer not null,protoid integer NULL,protoname VARCHAR(50) NULL,lot VARCHAR(30) NULL,status TINYINT NULL,userid integer NULL,errcode TINYINT NULL,dt DATETIME NULL,instrid integer NULL,ppid integer NULL ,comment VARCHAR(128)NULL)");
        map.put("Crear_ReactifA", "CREATE TABLE ReactifA(REAGENTAID integer not null,DOSID integer null,REAGENTLOT varchar(20) null,REAGENTTYPE varchar(20) null,REAGENTRUNAID integer null,REAGENTDATETIME datetime null)");
        map.put("Crear_RunA", "CREATE TABLE RunA(RUNAID integer not null,DOSID integer null,RunDateTime datetime null,RunNumber integer null,RunAna integer null,AnaType varchar(20) null,ANANAME varchar(12) null,SERIALNB varchar(25) null)");
        map.put("Crear_ScandataA","CREATE TABLE ScandataA(sdid integer not null,dosid integer null,anaid integer null,scantype varchar(50) null,dilut varchar(10) null,val text null)");
        map.put("Crear_IDX_AnalyseA_DOSID", "CREATE INDEX IDX_ANALYSEA_DOSID on ANALYSEA (DOSID)");
        map.put("Crear_IDX_ANALYSEA_SAMPLEID", "CREATE INDEX IDX_ANALYSEA_SAMPLEID on ANALYSEA (SAMPLEID)");
        map.put("Crear_IDX_COMMENTAIRE_COMID", "CREATE INDEX IDX_COMMENTAIRE_COMID on COMMENTAIRE (COMID)");
        map.put("Crear_IDX_DEMO_DEMID","CREATE INDEX IDX_DEMO_DEMID on Demo (DEMID)");
        map.put("Crear_IDX_DEMO_DEMIPP", "CREATE INDEX IDX_DEMO_DEMIPP on Demo (DEMIPP)");
        map.put("Crear_IDX_DEMO_DEMNOM", "CREATE INDEX IDX_DEMO_DEMNOM on Demo (DEMNOM)");
        map.put("Crear_IDX_DOSSIER_DEMID", "CREATE INDEX IDX_DOSSIER_DEMID on DOSSIER (DEMID)");
        map.put("Crear_IDX_DOSSIER_DOSID", "CREATE INDEX IDX_DOSSIER_DOSID on DOSSIER (DOSID)");
        map.put("Crear_IDX_REACTIFA_DOSID", "CREATE INDEX IDX_REACTIFA_DOSID on REACTIFA (DOSID)");
        map.put("Crear_IDX_RUNA_DOSID", "CREATE INDEX IDX_RUNA_DOSID on RUNA (DOSID)");
        map.put("Crear_IDX_RUNA_RUNAID", "CREATE INDEX IDX_RUNA_RUNAID on RUNA (RUNAID)");
        map.put("Crear_IDX_RUN_A_EQC_RUNAID", "CREATE INDEX IDX_RUN_A_EQC_RUNAID on RUN_A_EQC (RUNAID)");
        map.put("Crear_IDX_SCANDATAA_DOSID", "CREATE INDEX IDX_SCANDATAA_DOSID on SCANDATAA (DOSID)");
        
        return map;
    }
    
    private Map<String, String> mapInsertStatements(){
        //Insert statements
        HashMap<String,String> map = new HashMap<>();
        map.put("Insert_AnalyseA", "INSERT INTO AnalyseA(DOSID, CHIID, BILID, RESULT, RESCRITIQUE, LAST, DEMID, LIBELLE, SAMPLEID, FLAGS, rerun, ANAID, fdilut, resvalidusr) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        map.put("Insert_Comentaire", "INSERT INTO Comentaire(COMID, COMMENTAIRE) VALUES(?,?)");
        map.put("Insert_Demo", "INSERT INTO Demo(DEMID, DEMIPP, DEMNOM, DEMPRENOM, DEMNOMJF, DEMSEXE, DEMAGE,DEMAGEUNIT, DEMDATENAI, DEMCOMMENT, DEMADRESSE, DEMMARK, DEMSERVICE, DEMDOCTOR, DEMVetTypeId) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        map.put("Insert_Dossier", "INSERT INTO Dossier(DOSID, DEMID, DOSDATE, DOSTIME, DOSSERVICE, COMMENT1, COMMENT2, DOSDOCTOR, DOSAGE, DOSAGEUNIT, DOSLastDATERUN, DOSLastANAID, DOSLastTUBEGODET, DOSLastTUBEPLATEAU, DOSLastSAMPLEID, DemDateTime, PrelevDateTime, ArchDateTime, DosBackup) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        map.put("Insert_KEY_VALUE", "INSERT INTO KEY_VALUE (KEY, VALUE) VALUES (?,?)");
        map.put("Insert_Run_A_EQC", "INSERT INTO Run_A_EQC(runAID, protoid, protoname, lot, status, userid, errcode, dt, instrid, ppid, comment) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
        map.put("Insert_ReactifA", "INSERT INTO ReactifA(REAGENTAID, DOSID, REAGENTLOT, REAGENTTYPE, REAGENTRUNAID, REAGENTDATETIME) VALUES (?,?,?,?,?,?)");
        map.put("Insert_RunA", "INSERT INTO RunA(RUNAID, DOSID, RunDateTime, RunNumber, RunAna, AnaType, ANANAME, SERIALNB) VALUES (?,?,?,?,?,?,?,?)");
        map.put("Insert_ScandataA", "INSERT INTO ScandataA(sdid, dosid, anaid, scantype, dilut, val) VALUES (?,?,?,?,?,?)");
        
        return map;
    }
    public void set_nombredb(String nombredb){
        this.nombredb = nombredb;
    }
    public void set_url(String url){
        this.url = url;
    }
    public void set_path(String path){
        this.path = path;
    }
    public String get_nombredb(){
        return nombredb;
    }
    public String get_url(){
        return url;
    }
    public String get_path(){
        return path;
    }
    
   /**
    * Conectar y crear una nueva base de datos
    * 
    * @param url url del ficheo de la base de datos (path + name)
    */
    public void conectar(String url){
        try{
            File file = new File(url);
            if(file.exists()){                
                Class.forName(JDBC_SQL);
                conn = DriverManager.getConnection("jdbc:sqlite:" + url);
            }else{                
                Class.forName(JDBC_SQL);
                conn = DriverManager.getConnection("jdbc:sqlite:" + url);
                crearTablas();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
        
    
    public void consulta(String sql){
        Statement stmt;
        try{
            if(conn.isValid(0)){
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);                
            }            
        }catch(Exception e){
            //ERROR HANDLER HERE
        }     
    }
    /**
     * 
     * @param key clave que identifica al string del insert en el MAP<String, String> que contiene todos los insert.
     */
    public void insert(String key){
        String sql = stmts_insert.get(key);
        Statement stmt;        
        try{
            if(conn.isValid(0)){
                PreparedStatement pstmt = conn.prepareStatement(sql);
                
                
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
            }
        }catch(Exception e){
            //Error handler here
        }
    }
    
    private String getclass(Object n){
        return n.getClass().getSimpleName();
    }
    
    public void crearTablas(){
        stmts_create.forEach((k,V)->consulta(V));        
    }
    
    
}

