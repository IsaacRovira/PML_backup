/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pml_backup;
import java.sql.*;
import java.util.*;
import org.sqlite.*;
/**
 *
 * @author Isaac
 */
public class sqlitedb {
    static final String USER = "usuario";
    static final String PASS = "password";
    static final String JDBC_SQL = "org.sqlite.JDBC";
    
    String nombredb;    
    Connection conn;    
    Statement stmt;
    PreparedStatement pstmt;
    Map<String,String> stmts;  
    
        
    public sqlitedb(String nombredb){
        this.nombredb = nombredb;
        this.stmts = null;
        this.conn = null;
        this.stmt = null;
        this.pstmt=null;
        this.stmts.put("Crear_AnalyseA", "CREATE TABLE AnalyseA(DOSID integer null,CHIID integer null,BILID integer null,RESULT float null,RESCRITIQUE char(1) null,LAST char(1) null,DEMID integer not null,LIBELLE varchar(20) null,SAMPLEID varchar(16) null,FLAGS varchar(15) null,rerun integer null,ANAID integer null,fdilut float null,resvalidusr integer null)");
        this.stmts.put("Crear_Commentaire", "CREATE TABLE Commentaire(COMID integer not null,COMMENTAIRE text null)");
        this.stmts.put("Crear_Demo", "CREATE TABLE Demo (DEMID integer not null,DEMIPP varchar (25) null,DEMNOM varchar (20) null,DEMPRENOM varchar(20) null,DEMNOMJF varchar(20) null,DEMSEXE char(1) null,DEMAGE float null,DEMAGEUNIT char(1) null,DEMDATENAI datetime null,DEMCOMMENT varchar(256) null,DEMADRESSE varchar(75) null,DEMMARK char(1) null,DEMSERVICE integer null,DEMDOCTOR integer null,DEMVetTypeId integer null)");
        this.stmts.put("Crear_Dossier", "CREATE TABLE Dossier(DOSID integer not null,DEMID integer null,DOSDATE datetime null,DOSTIME datetime null,DOSSERVICE integer null,COMMENT1 integer null,COMMENT2 integer null,DOSDOCTOR integer null,DOSAGE float null,DOSAGEUNIT char(1) null,DOSLastDATERUN datetime null,DOSLastANAID integer null,DOSLastTUBEGODET integer null,DOSLastTUBEPLATEAU integer null,DOSLastSAMPLEID varchar(16) null,DemDateTime datetime null,PrelevDateTime datetime null,ArchDateTime datetime null,DosBackup integer null)");
        this.stmts.put("Crear_Key", "CREATE TABLE KEY_VALUE(KEY varchar(64) not null,VALUE text null)");
        this.stmts.put("Crear_Run_A_EQC", "CREATE TABLE RUN_A_EQC(runAID integer not null,protoid integer NULL,protoname VARCHAR(50) NULL,lot VARCHAR(30) NULL,status TINYINT NULL,userid integer NULL,errcode TINYINT NULL,dt DATETIME NULL,instrid integer NULL,ppid integer NULL ,comment VARCHAR(128)NULL)");
        this.stmts.put("Crear_ReactifA", "CREATE TABLE ReactifA(REAGENTAID integer not null,DOSID integer null,REAGENTLOT varchar(20) null,REAGENTTYPE varchar(20) null,REAGENTRUNAID integer null,REAGENTDATETIME datetime null)");
        this.stmts.put("Crear_RunA", "CREATE TABLE RunA(RUNAID integer not null,DOSID integer null,RunDateTime datetime null,RunNumber integer null,RunAna integer null,AnaType varchar(20) null,ANANAME varchar(12) null,SERIALNB varchar(25) null)");
        this.stmts.put("Crear_ScandataA","CREATE TABLE ScandataA(sdid integer not null,dosid integer null,anaid integer null,scantype varchar(50) null,dilut varchar(10) null,val text null)");
    }
    
    public void conectar(){
        try{
            Class.forName(JDBC_SQL);
            conn = DriverManager.getConnection("jdbc:sqlite:" + nombredb);            
        }catch(Exception e){
            //ERROR HANDLER HERE
        }
    }
    
    public void consulta(String sql){
        try{
            if(conn.isValid(0)){
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
            }            
        }catch(Exception e){
            //ERROR HANDLER HERE
        }finally{
            stmt.close();
        }     
    }
}
