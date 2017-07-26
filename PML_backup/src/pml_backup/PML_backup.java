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
    
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("Mostrar ayuda");
            System.exit(0);
        }else{
            for(String s: args){
                if(s.contains("?")){
                    System.out.println("Mostrar ayuda");
                    System.exit(0);                           
                }
            }
        }
    }
    
}

///<editor-fold defaultstate="collapsed" desc="descripción de los datos">
/*
select count(dosid) from demande

select distinct demo.demid from demo inner join dossier on demo.demid=dossier.demid where (DosBackup is null or DosBackup!=1)

select DEMID,DEMIPP,DEMNOM,DEMPRENOM,DEMNOMJF,DEMSEXE,DEMAGE,DEMAGEUNIT,DEMDATENAI,DEMCOMMENT,DEMADRESSE,DEMMARK,DEMSERVICE,DEMDOCTOR,DEMVETTYPEID from DEMO where DEMID=3397168
select DOSID,DEMID,DOSDATE,DOSTIME,DOSSERVICE,COMMENT1,COMMENT2,DOSDOCTOR,DOSAGE,DOSAGEUNIT,DOSLastDATERUN,DOSLastANAID,DOSLastTUBEGODET,DOSLastTUBEPLATEAU,DOSLastSAMPLEID,DemDateTime,PrelevDateTime,ArchDateTime,DosBackup from dossier where demid=3397167 and (DosBackup is null or DosBackup!=1)
select RUNAID,DOSID,RUNDATETIME,RUNNUMBER,RUNANA,ANATYPE,ANANAME,SERIALNB from RUNA where dosid=3074929
select runAID, run_eqc.protoid, isnull(eqcprotocol.name,protoname), lot, status, userid, errcode, dt, isnull(eqcprotocol.instrid, run_eqc.instrid), ppid, comment from run_a_eqc as run_eqc left outer join eqcprotocol on run_eqc.protoid = eqcprotocol.protoid  where RUNAID in (3464885)
select SDID,DOSID,ANAID,SCANTYPE,DILUT,VAL from SCANDATAA where dosid=3074929 and scantype in ('RULECOMMENT','DEMOA')
select SDID,DOSID,ANAID,SCANTYPE,DILUT,VAL from SCANDATAA where dosid=3074929 and scantype not in ('RULECOMMENT','DEMOA','RUNARESULT', 'RUNAGRAPH', 'RUNACOMMENT')
select SDID,DOSID,ANAID,SCANTYPE,DILUT,VAL from SCANDATAA where dosid=3074929 and scantype in ('RUNARESULT', 'RUNACOMMENT')
select SDID,DOSID,ANAID,SCANTYPE,DILUT,VAL from SCANDATAA where dosid=3074929 and scantype = 'RUNAGRAPH'
select * from reactifa where dosid=3074929
select DOSID,CHIID,BILID,RESULT,RESCRITIQUE,LAST,DEMID,LIBELLE,SAMPLEID,FLAGS,RERUN,ANAID,FDILUT,RESVALIDUSR  from analysea where dosid=3074929
select COMID,COMMENTAIRE from COMMENTAIRE where COMID in (0,0) order by COMINDEX

Update dossier set DosBackup = 1 where dosid=2913435


 select version from util
 select id from sysobjects where type='U' and name='ANALYSEUR'
 select name,length,type from syscolumns where id=53575229 order by colid
 select * from ANALYSEUR
 select id from sysobjects where type='U' and name='ANATYPE'
 select name,length,type from syscolumns where id=85575343 order by colid
 select * from ANATYPE
 select id from sysobjects where type='U' and name='ANAIDENTIFIANT'
 select name,length,type from syscolumns where id=5575058 order by colid
 select * from ANAIDENTIFIANT
 select id from sysobjects where type='U' and name='ARRAYCHIMIE'
 select name,length,type from syscolumns where id=101575400 order by colid
 select * from ARRAYCHIMIE
 select id from sysobjects where type='U' and name='ASTRAMODULE'
 select name,length,type from syscolumns where id=117575457 order by colid
 select * from ASTRAMODULE
 select id from sysobjects where type='U' and name='BARCODE'
 select name,length,type from syscolumns where id=165575628 order by colid
 select * from BARCODE
 select id from sysobjects where type='U' and name='BILAN'
 select name,length,type from syscolumns where id=181575685 order by colid
 select * from BILAN
 select id from sysobjects where type='U' and name='CHIMANA'
 select name,length,type from syscolumns where id=261575970 order by colid
 select * from CHIMANA
 select id from sysobjects where type='U' and name='CHIMBIL'
 select name,length,type from syscolumns where id=277576027 order by colid
 select * from CHIMBIL
 select id from sysobjects where type='U' and name='CHIMIE'
 select name,length,type from syscolumns where id=293576084 order by colid
 select * from CHIMIE
 select id from sysobjects where type='U' and name='HOST'
 select name,length,type from syscolumns where id=837578022 order by colid
 select * from HOST
 select id from sysobjects where type='U' and name='LIBELLE'
 select name,length,type from syscolumns where id=933578364 order by colid
 select * from LIBELLE
 select id from sysobjects where type='U' and name='NATURE'
 select name,length,type from syscolumns where id=981578535 order by colid
 select * from NATURE
 select id from sysobjects where type='U' and name='PRIOBILAN'
 select name,length,type from syscolumns where id=1173579219 order by colid
 select * from PRIOBILAN
 select id from sysobjects where type='U' and name='PRIOCHIMIE'
 select name,length,type from syscolumns where id=1189579276 order by colid
 select * from PRIOCHIMIE
 select id from sysobjects where type='U' and name='QCCONTROL'
 select name,length,type from syscolumns where id=1237579447 order by colid
 select * from QCCONTROL
 select id from sysobjects where type='U' and name='QCITEM'
 select name,length,type from syscolumns where id=1269579561 order by colid
 select * from QCITEM
 select id from sysobjects where type='U' and name='QUERY'
 select name,length,type from syscolumns where id=1317579732 order by colid
 select * from QUERY
 select id from sysobjects where type='U' and name='REPORT'
 select name,length,type from syscolumns where id=1381579960 order by colid
 select * from REPORT
 select id from sysobjects where type='U' and name='SERVICE'
 select name,length,type from syscolumns where id=1493580359 order by colid
 select * from SERVICE
 select id from sysobjects where type='U' and name='TESTREFLEX'
 select name,length,type from syscolumns where id=1509580416 order by colid
 select * from TESTREFLEX
 select id from sysobjects where type='U' and name='UNITE'
 select name,length,type from syscolumns where id=1573580644 order by colid
 select * from UNITE
 select id from sysobjects where type='U' and name='UPC'
 select name,length,type from syscolumns where id=1589580701 order by colid
 select * from UPC
 select id from sysobjects where type='U' and name='UPCXY'
 select name,length,type from syscolumns where id=1605580758 order by colid
 select * from UPCXY
 select id from sysobjects where type='U' and name='UPCY'
 select name,length,type from syscolumns where id=1621580815 order by colid
 select * from UPCY
 select id from sysobjects where type='U' and name='USERS'
 select name,length,type from syscolumns where id=1637580872 order by colid
 select * from USERS
 select id from sysobjects where type='U' and name='VALNOR'
 select name,length,type from syscolumns where id=1669580986 order by colid
 select * from VALNOR
 select id from sysobjects where type='U' and name='VALRANGE'
 select name,length,type from syscolumns where id=1685581043 order by colid
 select * from VALRANGE
 select id from sysobjects where type='U' and name='RULEEX'
 select name,length,type from syscolumns where id=1445580188 order by colid
 select * from RULEEX
 select id from sysobjects where type='U' and name='INSTRUMENTGROUP'
 select name,length,type from syscolumns where id=869578136 order by colid
 select * from INSTRUMENTGROUP
 select id from sysobjects where type='U' and name='ANAGROUPLINK'
 select name,length,type from syscolumns where id=2137058649 order by colid
 select * from ANAGROUPLINK
 select id from sysobjects where type='U' and name='VeterinaryType'
 select name,length,type from syscolumns where id=1314103722 order by colid
 select * from VeterinaryType
 select id from sysobjects where type='U' and name='VeterinaryCode'
 select name,length,type from syscolumns where id=1330103779 order by colid
 select * from VeterinaryCode
 select id from sysobjects where type='U' and name='DISCIPLINE'
 select name,length,type from syscolumns where id=1538104520 order by colid
 select * from DISCIPLINE
 select id from sysobjects where type='U' and name='PARAMETRE'
 select name,length,type from syscolumns where id=1698105090 order by colid
 select * from PARAMETRE
*/
//</editor-fold>
