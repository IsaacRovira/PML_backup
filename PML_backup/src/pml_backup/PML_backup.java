/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pml_backup;

import java.*;
import org.sqlite.*;
import microsoft.sql.*;
import com.microsoft.sqlserver.jdbc.*;
        
/**
 *
 * @author Isaac
 */
public class PML_backup {

    /**
     * @param args the command line arguments
     */
    public static void conectar(){
        Connection conexion = null;
        try{
            String url = "jdbc:sqlite:c:/sqlite/db/chinook.db";
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(ex.getMessage());
            }
        }
        
    }
    public static void main(String[] args) {
        conectar();
    }
    
}
