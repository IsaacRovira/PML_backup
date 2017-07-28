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
        
    Connection conn;    
    Statement stmt;
    PreparedStatement pstmt;
    Map<String,String> stmts;
    
    
    public mssqldb(){
       try{
           this.conn = DriverManager.getConnection(URL, USER, PASS);
       }catch(Exception e){
           System.out.println("mssqldb: " + e.getMessage());
       }
   }
   
   public mssqldb(String nombredb){
       this.nombredb = nombredb;
       this.conn = null;
       this.stmt = null;
       this.stmts.put("Count_DOSID","select count(dosid) from demande");
       this.stmts.put("Get_DEMID","select distinct demo.demid from demo inner join dossier on demo.demid=dossier.demid where (DosBackup is null or DosBackup!=1)");
       this.stmts.put("From_Demo","select DEMID,DEMIPP,DEMNOM,DEMPRENOM,DEMNOMJF,DEMSEXE,DEMAGE,DEMAGEUNIT,DEMDATENAI,DEMCOMMENT,DEMADRESSE,DEMMARK,DEMSERVICE,DEMDOCTOR,DEMVETTYPEID from DEMO where DEMID=" + demid);
       //this.stmts.put("");
   }
   
   public 
   
   //Obtener todos los "dosid" desde "Dossier".
   //Obtener todos los "demid" desde "Dossier".
   //Obtener todos los "runaid" desde "RunA" según los "dosid" obtenidos.
    
}
