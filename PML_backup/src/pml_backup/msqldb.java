/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pml_backup;
import microsoft.sql.*;
import com.microsoft.sqlserver.jdbc.*;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Isaac
 */
class mssqldb {
    String nombredb;
    String url;
    String pass;
    
    final String URL = "jdbc:sqlserver://localhost\\\\SQLEXPRESS;databaseName=MYDB";
    
    //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    
    final String USER = "abx";
    final String PASS = "hr1528";
    final String JDBC_MSSQL = "com.microsoft.sqlserver.jdbc.SQLServerDriver";    
        
    AtributosID datosID;
    ConsultasSQL sqlqueries;
    Connection conn;
    
    Map<String,String> stmts;
    
    
   public mssqldb(){
       this.nombredb = null;
       this.conn = null;       
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
   
   public void connectar(String url, String user, String pass){
       try{
           this.conn = DriverManager.getConnection(url, user, pass);
       }catch(Exception ex){
           System.out.println("Conexión MSQL: " + ex.getMessage());
       }
   }
   
   public void get_ids(){
       get_dosid();
       get_demid();
       get_runaid();
       get_commid();
   }
   
   private void get_dosid(){       
       try{
           Statement stmt = this.conn.createStatement();
           ResultSet results = stmt.executeQuery(sqlqueries.dosid);
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
           ResultSet results = stmt.executeQuery(sqlqueries.demid);
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
           ResultSet results = stmt.executeQuery(sqlqueries.runaid);
           if(results.last()){
               datosID.runAID = convert(results);
           }
       }catch(Exception ex){
           System.out.println("msqldb_get_dosid: " + ex.getMessage());
       }
   }   
   
   private void get_commid(){
       try{           
           Statement stmt = this.conn.createStatement();
           ResultSet results = stmt.executeQuery(sqlqueries.commid);
           if(results.last()){
               datosID.commid = convert(results);
           }
       }catch(Exception ex){
           System.out.println("msqldb_get_commid: " + ex.getMessage());
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
   
   private ResultSet get_datos(int id, String sql){       
       ResultSet datos = null;       
       try{           
           PreparedStatement pstmt = conn.prepareStatement(sql);           
           pstmt.setInt(1, id);
           datos = pstmt.executeQuery();
       }catch(Exception ex){
           System.out.println("mssqldb get_datos: " + ex.getMessage());
       }       
       return datos;
   }   

   /**
    * 
    * @param id identificación dosid, demid, runaid que se le pasará a la consulta SQL
    * @param type tipo de dato dossier, demo, Analysea, Run_A_EQC, RunA, ReactifA, Commentaire, ScandataA
    * @return devuelve un tipo de datos según el type escogido con los valores obtenidos en la consulta SQL.
    */
   public Data getData(int id, String type){       
       ResultSet valores;
       Data datos;
       try{           
           switch(type){
               case "ReactifA":
                   valores = get_datos(id, sqlqueries.REACTIFA);                   
                   datos = new ReactifA(valores.getInt(1),valores.getInt(2),valores.getInt(5), valores.getString(3), valores.getString(4),valores.getString(6));
                   break;
               case "RunA":
                   valores = get_datos(id, sqlqueries.RUNA);
                   datos = new RunA(valores.getInt(1),valores.getInt(2),valores.getInt(4),valores.getInt(5),valores.getString(3),valores.getString(6),valores.getString(7),valores.getString(8));
                   break;  
               case "Run_A_EQC":
                   valores = get_datos(id, sqlqueries.RUN_A_EQC);
                   datos = new Run_A_EQC(valores.getInt(1),valores.getInt(2),valores.getInt(5), valores.getInt(6),valores.getInt(7),valores.getInt(9),valores.getInt(10),valores.getString(3),valores.getString(4),valores.getString(8),valores.getString(11));
                   break;
               case "ScandataA":
                   valores = get_datos(id, sqlqueries.SCANDATAA);
                   datos = new ScandataA(valores.getInt(1),valores.getInt(2), valores.getInt(3),valores.getString(4),valores.getString(5), valores.getString(6));
                   break;
               case "AnalyseA":
                   valores = get_datos(id, sqlqueries.ANALYSEAA);
                   //int dosid, int chiid, int bilid, int result, int demid, int rerun, int anaid, int resvalidusr, char rescritique, char last, String libelle, String sampleid, String flags, float fdilut
                   datos = new AnalyseA(valores.getInt(1),valores.getInt(2),valores.getInt(3),valores.getFloat(4),valores.getInt(7),valores.getInt(11),valores.getInt(12),valores.getInt(14),valores.getString(5).charAt(0),valores.getString(6).charAt(0), valores.getString(8), valores.getString(9), valores.getString(10),valores.getFloat(13));
                   break;
               case "Demo":
                   valores = get_datos(id, sqlqueries.DEMO);
                   datos = new Demo(valores.getInt(1),valores.getInt(13),valores.getInt(14),valores.getInt(15),valores.getString(2),valores.getString(3),valores.getString(4),valores.getString(5),valores.getString(9),valores.getString(10),valores.getString(11),valores.getString(6).charAt(0),valores.getString(8).charAt(0),valores.getString(12).charAt(0),valores.getFloat(7));
                   break;
               case "Dossier":
                   valores = get_datos(id, sqlqueries.DOSSIER);
                   datos = new Dossier(valores.getInt(1),valores.getInt(2),valores.getInt(5),valores.getInt(6),valores.getInt(7),valores.getInt(8),valores.getInt(12),valores.getInt(13),valores.getInt(14), valores.getInt(19),valores.getString(3),valores.getString(4),valores.getString(11),valores.getString(15),valores.getString(16),valores.getString(17),valores.getString(18),valores.getFloat(9),valores.getString(10).charAt(0));
                   break;
               case "Commentaire":
                   valores = get_datos(id, sqlqueries.COMMENTAIRE);
                   datos = new Commentaire(valores.getInt(1),valores.getInt(2),valores.getString(3));
                   break;
               default: return null;
           }
           return datos;           
       }catch(Exception ex){
           System.out.println("mssqldb getData: " + ex.getMessage());
       }
       return null;
   }
   
   private Method findConstructor(Class clase){
       Method[] methods = clase.getMethods();
       for(Method M: methods){
           if(M.getName().equals(clase.getSimpleName())){
               return M;
           }
       }
       return null;
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
    String fecha_inicio, fecha_fin, dosid, demid, runaid, commid;
    
    final String ANALYSEAA = "SELECT * FROM analysea WHERE dosid = ?";
    final String COMMENTAIRE = "SELECT * FROM commentaire WHERE comid = ?";
    final String DEMO = "SELECT * FROM demo WHERE demid = ?";
    final String DOSSIER = "SELECT * FROM dossier WHERE dosid = ?";
    final String RUN_A_EQC = "SELECT * FROM run_a_eqc WHERE dosid = ?";
    final String REACTIFA = "SELECT * FROM reactifa WHERE dosid = ?";
    final String RUNA = "SELECT * FROM runa WHERE runa = ?";
    final String SCANDATAA = "SELECT * FROM scandataa WHERE dosid = ?";
    
    public ConsultasSQL(){
        fecha_inicio = fecha_fin = null;
        dosid = "SELECT dosid FROM dossier WHERE (DosBackup is null or DosBackup!=1)";
        demid = "SELECT demid FROM dossier WHERE (DosBackup is null or DosBackup!=1)";
        runaid= "SELECT runaid FROM runa WHERE dosid IN (" + this.dosid + ")";
        set_stmt_commid();
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
        set_stmt_commid();
    }    
    
    /**
     * Construye las sentencias para la obtención de los dosid, demid y runaid en función de
     * los parámetros introducidos que actuan como clausulas del WHERE.
     * ej: SELECT dosid FROM dossier WHERE + @param dosid
     * @param dosid clausala WHERE para filtrar la consulta que obtiene los dosid y los runaid.
     * @param demid clausula WHERE para filtrar la consulta que obtiene los demid.
     */
    public ConsultasSQL(String dosid, String demid){
        this.dosid = "SELECT dosid FROM dossier WHERE " + dosid;
        this.demid = "SELECT demid FROM dossier WHERE " + demid;
        runaid= "SELECT runaid FROM runa WHERE dosid IN (" + this.dosid + ")";
        set_stmt_commid();
    }

    public ConsultasSQL(String fecha_inicio, String fecha_fin, String dosid, String demid, String runaid) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.dosid = dosid;
        this.demid = demid;
        this.runaid = runaid;
        set_stmt_commid();
    }  
    
    /**
     * Genera la sentencias para la obtención del dosid, demid y runaid según una fecha inicial y teniendo en cuenta o no si ya se ha marcado como copiados los informes.
     * @param fecha_inicio dato tipo cadena con formato DD/MM/AAAA. Se copiarán los datos a partir de la fecha indicada en este parámetro hasta el último día que contenga la base de datos sobre la que se consulta.
     * @param backup V o F permite definir si se deben incluir o no los datos que ya están marcados como copiados.
     */
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
        set_stmt_commid();
    }
    
    /**
     * Genera las sentencias SQL para la obtención de los dosid, demid, runaid que identificarán los resultados a los que se quiere hacer el backup.
     * @param fecha_inicio dato tipo cadena con formato DD/MM/AAAA que indica la fecha a partir de la cual se recuperaran los datos.
     * @param backup V o F define si se denen considerar los datos ya marcados como copiados.
     * @param numdias define el número de días a partir de la fecha_inicio de los que se hara la copia de seguridad.
     */
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
        set_stmt_commid();
    }
    
    /**
     * Genera la sentencias para la obtención del dosid, demid y runaid según una fecha final y teniendo en cuenta o no si ya se ha marcado como copiado el informe.
     * @param fecha_fin fecha en formato DD/MM/AAAA que marca el último día para la copia de seguridad, incluido.
     * @param backup T o F indica si se quiere tener en cuenta o no los resultados que ya están marcados como copiados.
     */
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
        set_stmt_commid();
    }
    /**
     * Genera la sentencias para la obtención del dosid, demid y runaid según una fecha final y un número de días; Ademas puede tener en cuenta o no si ya se ha marcado como copiado el informe.
     * @param fecha_fin cadena con formato DD/MM/AAAA que marca el último día para la copia de seguridad, incluido.
     * @param backup fecha en formato DD/MM/AAAA que marca el último día para la copia de seguridad, incluido.
     * @param numdias entero que indica el número de días desde la fecha_fin hacia tras de los que se desea hacer la copia.
     */
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
        set_stmt_commid();
    }
    
    private void set_stmt_commid(){
        commid = "SELECT commid FROM Commmentaire WHERE commid IN (SELECT commment1 FROM Dossier WHERE dosid IN (" + dosid + ")) OR commid IN (SELECT commment1 FROM Dossier WHERE dosid IN (" + dosid + "))";
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