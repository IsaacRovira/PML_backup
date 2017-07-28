/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pml_backup;
import java.sql.*;
import java.util.*;
import java.io.*;
import java.util.logging.Logger;
import org.sqlite.*;

/**
 *
 * @author Isaac
 * 
 * Crear la base de datos.
 * Crear las tablas.
 * Realizar las consultas
 * 
 */
class sqlitedb {
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

    ///<editor-fold defaultstate="collapsed" desc="getters and setters">
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombredb() {
        return nombredb;
    }

    public void setNombredb(String nombredb) {
        this.nombredb = nombredb;
    }
    //</editor-fold>
    
   
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
     * @param pstmt PreparedStatement
     * @param data datos con los que construir el preparedStatement
     * @return PreparedStatement
     */
    private PreparedStatement pstmtBuilder(PreparedStatement pstmt, Data data){
        try{
            for(int n = 0; n < data.getLista().size(); n++){
                pstmt.setObject(n+1, data.getLista().get(n));
            }
        }catch(Exception ex){
            System.out.println("pstmtBuilder: " + ex.getMessage());
        }
        return pstmt;
    }
    
    /**
     * 
     * @param data datos a enviar en el insert.
     */
    public void insertData(Data data){
        String sql = stmts_insert.get("Insert_" + data.getClass().getSimpleName());
        try{
            if(conn.isValid(0)){
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt = pstmtBuilder(pstmt, data);
                pstmt.executeUpdate();
            }
        }catch(Exception ex){
            System.out.println("insertData: " + ex.getMessage());
        }
    }
           
    /**
     * Crea las tablas en una nueva BD.
     */
    public void crearTablas(){
        stmts_create.forEach((k,V)->consulta(V));        
    }
    
    ///<editor-fold defaultstate="collapsed" desc="Metodos secundarios">
    
    /**
     * 
     * @param string cadena con formato fecha hora "ddMMyyyy HH:mm:ss:SSS"
     * @return devuelve sql.Date
     */
    private java.sql.Date sqlDateFormat(String string){
        java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("ddMMyyyy HH:mm:ss:SSS");
        try{
            java.util.Date formateada = formato.parse(string);
            
            return new java.sql.Date(formateada.getTime());
        }catch(Exception ex){
            System.out.println("sqlDateFormat: " + ex.getMessage());
        }        
        return null;
    }  
    
    
    /**
     * 
     * @param datos classe AnalyseaA que contiene una variable para cada uno de los atributos de la tabla AnalyseA.
     */
    public void insert_AnalyseA(AnalyseA datos){
        String sql = stmts_insert.get("Insert_AnalyseA");
        try{
            if(conn.isValid(0)){
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, datos.getDosid());
                pstmt.setInt(2, datos.getChiid());
                pstmt.setInt(3, datos.getBilid());
                pstmt.setInt(4, datos.getResult());
                pstmt.setString(5, String.valueOf(datos.getRescritique()));
                pstmt.setString(6, String.valueOf(datos.getLast()));
                pstmt.setInt(7, datos.getDemid());
                pstmt.setString(8, datos.getLibelle());
                pstmt.setString(9, datos.getSampleid());
                pstmt.setString(10, datos.getFlags());
                pstmt.setInt(11, datos.getRerun());
                pstmt.setInt(12, datos.getAnaid());
                pstmt.setFloat(13, datos.getFdilut());
                pstmt.setInt(14, datos.getResvalidusr());
            }
        }catch(Exception e){            
            //Error handler here
        }
    }
    
    
    
    /**
     * 
     * @param datos classe Commentaire
     */
    public void insert_Commentaire(Commentaire datos){
        String sql = stmts_insert.get("Insert_Commentaire");
        try{
            if(conn.isValid(0)){
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, datos.getComid());
                pstmt.setInt(2, datos.getComindex());
                pstmt.setString(3, datos.getCommentaire());
                
                pstmt.executeUpdate();
            }
        }catch(Exception e){
            System.out.println("Insert_comentario: " + e.getMessage());            
        }
        
    }
    
    
    
    public void insert_Demo(Demo datos){
        String sql = stmts_insert.get("Insert_Demo");
        try{
            if(conn.isValid(0)){
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, datos.getDemid());
                pstmt.setString(2, datos.getDemipp());
                pstmt.setString(3, datos.getDemnom());
                pstmt.setString(4, datos.getDemprenom());
                pstmt.setString(5, datos.getDemnomjf());
                pstmt.setString(6, String.valueOf(datos.getDEMSEXE()));
                pstmt.setFloat(7, datos.getDemage());
                pstmt.setString(8, String.valueOf(datos.getDemageunit()));
                pstmt.setDate(9, sqlDateFormat(datos.getDemdatenai()));
                pstmt.setString(10, datos.getDemcoment());
                pstmt.setString(11, datos.getDemadresse());
                pstmt.setString(12, String.valueOf(datos.getDemmark()));
                pstmt.setInt(13, datos.getDemservice());
                pstmt.setInt(14, datos.getDemdoctor());
                pstmt.setInt(15, datos.getDemvettype());
                
                pstmt.executeUpdate();
            }
        }catch(Exception e){            
            //Error handler here
        }
    }
    //</editor-fold>
}

