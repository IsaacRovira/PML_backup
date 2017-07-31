/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pml_backup;
import microsoft.sql.*;
import com.microsoft.sqlserver.jdbc.*;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Isaac
 */
class mssqldb {
    String nombredb;
    String url;
    
    final String URL = "jdbc:sqlserver://localhost\\\\SQLEXPRESS;databaseName=MYDB";
    
    //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    
    final String USER = "abx";
    final String PASS = "hr1528";
    final String JDBC_MSSQL = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        
    AtributosID datosID;
    String dosid, demid, runaid;
    Connection conn;    
    PreparedStatement pstmt;
    Map<String,String> stmts;
    
    
   public mssqldb(){
       this.nombredb = null;
       this.conn = null;       
       this.pstmt = null;
       this.dosid = null;
       this.demid = null;
       this.runaid = null;
   }
   
   public void connectar(){
       try{
           this.conn = DriverManager.getConnection(URL, USER, PASS);
       }catch(Exception ex){
           System.out.println("Conexión MSQL: " + ex.getMessage());
       }
   }
   
   private void get_dosid(){       
       try{
           Statement stmt = this.conn.createStatement();
           ResultSet results = stmt.executeQuery(dosid);
           if(results.last()){
               datosID.dosid = convert(results);
           }
       }catch(Exception ex){
           System.out.println("msqldb_get_dosid: " + ex.getMessage());
       }
   }
   
   private void get_demid(){
       try{
           Statement stmt = this.conn.createStatement();
           ResultSet results = stmt.executeQuery(demid);
           if(results.last()){
               datosID.demoid = convert(results);
           }
       }catch(Exception ex){
           System.out.println("msqldb_get_dosid: " + ex.getMessage());
       }
   }
      
   private void get_runaid(){       
       try{
           Statement stmt = this.conn.createStatement();
           ResultSet results = stmt.executeQuery(runaid);
           if(results.last()){
               datosID.runAID = convert(results);
           }
       }catch(Exception ex){
           System.out.println("msqldb_get_dosid: " + ex.getMessage());
       }
   }
   
   
   
   
   
   
   
   
   
   
   
   
   private int[] convert(ResultSet resultados){
       int n = 0;
       int[] matriz = null;
       try{
           matriz = new int[resultados.getRow()];           
           resultados.first();           
           do{
               matriz[n] = resultados.getInt(1);
               n++;
           }
           while(resultados.next());
           
       }catch(Exception ex){
           System.out.println("msql_convert: " + ex.getMessage());
       }
       return matriz;
   }   
   
   ///<editor-fold defaultstate="collapsed" desc="getters and setters">
   public AtributosID getDatosID() {
       return datosID;
   }
   public String getNombredb() {
       return nombredb;
   }
   public void setNombredb(String nombredb) {
       this.nombredb = nombredb;
   }
   public String getUrl() {
       return url;
   }
   public void setUrl(String url) {
       this.url = url;
   }
   //</editor-fold>
   
   
   
   
   //Obtener todos los "dosid" desde "Dossier". ArchiveDateTime
   //Obtener todos los "demid" desde "Dossier".
   //Obtener todos los "runaid" desde "RunA" según los "dosid" obtenidos.


    
}


class ConsultasSQL{
    String fecha_inicio, fecha_fin, dosid, demid, runaid;
    
    public ConsultasSQL(){
        fecha_inicio = fecha_fin = null;
        dosid = "SELECT dosid FROM dossier WHERE (DosBackup is null or DosBackup!=1)";
        demid = "SELECT demid FROM dossier WHERE (DosBackup is null or DosBackup!=1)";
        runaid= "SELECT runaid FROM runa WHERE dosid IN (" + this.dosid + ")";
    }
    
    public ConsultasSQL(String fecha_inicio, String fecha_fin, boolean backup){
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        if(backup){
            dosid = "SELECT dosid FROM dossier WHERE (DosBackup is null or DosBackup!=1) and ArchDateTime between \'" + fecha_inicio + " 00:00:00.000\' and \'" + fecha_fin + " 23:59:59.999\'";
            demid = "SELECT demid FROM dossier WHERE (DosBackup is null or DosBackup!=1) and ArchDateTime between \'" + fecha_inicio + " 00:00:00.000\' and \'" + fecha_fin + " 23:59:59.999\'";            
        }else{
            dosid = "SELECT dosid FROM dossier WHERE ArchDateTime between \'" + fecha_inicio + " 00:00:00.000\' and \'" + fecha_fin + " 23:59:59.999\'";
            demid = "SELECT demid FROM dossier WHERE ArchDateTime between \'" + fecha_inicio + " 00:00:00.000\' and \'" + fecha_fin + " 23:59:59.999\'";
        }
        runaid= "SELECT runaid FROM runa WHERE dosid IN (" + this.dosid + ")";
    }    
    
    /**
     * Construye las sentencias para la obtención de los dosid, demid y runaid en función de
     * los parámetros introducidos que actuan como clausilas del WHERE.
     * ej: SELECT dosid FROM dossier WHERE + dosid
     * @param dosid clausala WHERE para filtrar la consulta que obtiene los dosid y los runaid.
     * @param demid clausula WHERE para filtrar la consulta que obtiene los demid.
     */
    public ConsultasSQL(String dosid, String demid){
        this.dosid = "SELECT dosid FROM dossier WHERE " + dosid;
        this.demid = "SELECT demid FROM dossier WHERE " + demid;
        runaid= "SELECT runaid FROM runa WHERE dosid IN (" + this.dosid + ")";
    }

    public ConsultasSQL(String fecha_inicio, String fecha_fin, String dosid, String demid, String runaid) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.dosid = dosid;
        this.demid = demid;
        this.runaid = runaid;
    }  
    
    
    public void set_stmt_FechaInicio(String fecha_inicio, boolean backup){
        this.fecha_inicio = fecha_inicio;
        fecha_fin = null;
        if(backup){
            dosid = "SELECT dosid FROM dossier WHERE (DosBackup is null or DosBackup!=1) and ArchDateTime > \'" + fecha_inicio + " 00:00:00.000\'";
            demid = "SELECT demid FROM dossier WHERE (DosBackup is null or DosBackup!=1) and ArchDateTime > \'" + fecha_inicio + " 00:00:00.000\'";
        }else{
            dosid = "SELECT dosid FROM dossier WHERE ArchDateTime > \'" + fecha_inicio + " 00:00:00.000\'";
            demid = "SELECT demid FROM dossier WHERE ArchDateTime > \'" + fecha_inicio + " 00:00:00.000\'";
        }
        runaid= "SELECT runaid FROM runa WHERE dosid IN (" + this.dosid + ")";

    }
    public void set_stmt_FechaInicio(String fecha_inicio, boolean backup, int numdias){
        this.fecha_inicio = fecha_inicio;
        fecha_fin = null;
        if(backup){
            dosid = "SELECT dosid FROM dossier WHERE (DosBackup is null or DosBackup!=1) and ArchDateTime between \'" + fecha_inicio + " 00:00:00.000\' and convert(datetime,\'" + fecha_inicio + " 00:00:00.000\') + " + String.valueOf(numdias);
            demid = "SELECT demid FROM dossier WHERE (DosBackup is null or DosBackup!=1) and ArchDateTime between \'" + fecha_inicio + " 00:00:00.000\' and convert(datetime,\'" + fecha_inicio + " 00:00:00.000\') + " + String.valueOf(numdias);            
        }else{
            dosid = "SELECT dosid FROM dossier WHERE ArchDateTime between \'" + fecha_inicio + " 00:00:00.000\' and convert(datetime,\'" + fecha_inicio + " 00:00:00.000\') + " + String.valueOf(numdias);
            demid = "SELECT demid FROM dossier WHERE ArchDateTime between \'" + fecha_inicio + " 00:00:00.000\' and convert(datetime,\'" + fecha_inicio + " 00:00:00.000\') + " + String.valueOf(numdias);            
        }
        runaid= "SELECT runaid FROM runa WHERE dosid IN (" + this.dosid + ")";
    }
    
    public void set_stmt_FechaFin(String fecha_fin, boolean backup){
        this.fecha_fin = fecha_fin;
        this.fecha_inicio = null;
        if(backup){
            dosid = "SELECT dosid FROM dossier WHERE (DosBackup is null or DosBackup!=1) and ArchDateTime < \'" + fecha_fin + " 23:59:59.999\'";
            demid = "SELECT demid FROM dossier WHERE (DosBackup is null or DosBackup!=1) and ArchDateTime < \'" + fecha_fin + " 23:59:59.999\'";
        }else{
            dosid = "SELECT dosid FROM dossier WHERE ArchDateTime < \'" + fecha_fin + " 23:59:59.999\'";
            demid = "SELECT demid FROM dossier WHERE ArchDateTime < \'" + fecha_fin + " 23:59:59.999\'";
        }
        runaid= "SELECT runaid FROM runa WHERE dosid IN (" + this.dosid + ")";
    }
    public void set_stmt_FechaFin(String fecha_fin, boolean backup, int numdias){
        this.fecha_fin = fecha_fin;
        this.fecha_inicio = null;
        if(backup){
            dosid = "SELECT dosid FROM dossier WHERE (DosBackup is null or DosBackup!=1) and ArchDateTime between convert(datetime,\'" + fecha_fin + " 23:59:59.999\') - " + numdias + " and \'" + fecha_fin + " 23:59:59.999\'";
            demid = "SELECT demid FROM dossier WHERE (DosBackup is null or DosBackup!=1) and ArchDateTime between convert(datetime,\'" + fecha_fin + " 23:59:59.999\') - " + numdias + " and \'" + fecha_fin + " 23:59:59.999\'";
        }else{
            dosid = "SELECT dosid FROM dossier WHERE ArchDateTime between convert(datetime,\'" + fecha_fin + " 23:59:59.999\') - " + numdias + " and \'" + fecha_fin + " 23:59:59.999\'";
            demid = "SELECT demid FROM dossier WHERE ArchDateTime between convert(datetime,\'" + fecha_fin + " 23:59:59.999\') - " + numdias + " and \'" + fecha_fin + " 23:59:59.999\'";
        }
        runaid= "SELECT runaid FROM runa WHERE dosid IN (" + this.dosid + ")";
    }
    
    
    ///<editor-fold defaultstate="collapsed" desc="getters and setters">

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getDosid() {
        return dosid;
    }

    public void setDosid(String dosid) {
        this.dosid = dosid;
    }

    public String getDemid() {
        return demid;
    }

    public void setDemid(String demid) {
        this.demid = demid;
    }

    public String getRunaid() {
        return runaid;
    }

    public void setRunaid(String runaid) {
        this.runaid = runaid;
    }
    //</editor-fold> 

    
}