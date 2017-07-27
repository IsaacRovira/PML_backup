/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pml_backup;

import java.util.List;


class Data{
    protected List lista;   
    
    ///<editor-fold defaultstate="collapsed" desc="getters and setters">
    public List getLista() {
        return lista;
    }

    public void setLista(List lista) {
        this.lista = lista;
    }
    //</editor-fold>    
    
}
/**
 * Almacena en sendos int array las identificaciones dosid, runaid, demoid de los datos que vamos a tratar.
 * @author Isaac
 */
class atributosID{    
    int[] dosid, runAID, demoid;
    
    ///<editor-fold defaultstate="collapsed" desc="getters and setters">
    public int[] getDosid() {
        return dosid;
    }

    public void setDosid(int[] dosid) {
        this.dosid = dosid;
    }

    public int[] getRunAID() {
        return runAID;
    }

    public void setRunAID(int[] runAID) {
        this.runAID = runAID;
    }

    public int[] getDemoid() {
        return demoid;
    }

    public void setDemoid(int[] demoid) {
        this.demoid = demoid;
    }
    //</editor-fold>
}

/**
 * Respresenta los atributos de la tabla Demo de la base de datos de la ML.
 * @author HotLine.Madrid
 */
class Demo extends Data{    
    int demid, demservice, demdoctor, demvettype;
    String demipp, demnom, demprenom, demnomjf, demdatenai, demcoment, demadresse;
    char demsexe, demageunit, demmark;
    float demage;
    
    ///<editor-fold defaultstate="collapsed" desc="getters and setters">
    public int getDemid() {
        return demid;
    }

    public void setDemid(int demid) {
        this.demid = demid;
    }

    public int getDemservice() {
        return demservice;
    }

    public void setDemservice(int demservice) {
        this.demservice = demservice;
    }

    public int getDemdoctor() {
        return demdoctor;
    }

    public void setDemdoctor(int demdoctor) {
        this.demdoctor = demdoctor;
    }

    public int getDemvettype() {
        return demvettype;
    }

    public void setDemvettype(int demvettype) {
        this.demvettype = demvettype;
    }

    public String getDemipp() {
        return demipp;
    }

    public void setDemipp(String demipp) {
        this.demipp = demipp;
    }

    public String getDemnom() {
        return demnom;
    }

    public void setDemnom(String demnom) {
        this.demnom = demnom;
    }

    public String getDemprenom() {
        return demprenom;
    }

    public void setDemprenom(String demprenom) {
        this.demprenom = demprenom;
    }

    public String getDemnomjf() {
        return demnomjf;
    }

    public void setDemnomjf(String demnomjf) {
        this.demnomjf = demnomjf;
    }

    public String getDemdatenai() {
        return demdatenai;
    }

    public void setDemdatenai(String demdatenai) {
        this.demdatenai = demdatenai;
    }

    public String getDemcoment() {
        return demcoment;
    }

    public void setDemcoment(String demcoment) {
        this.demcoment = demcoment;
    }

    public String getDemadresse() {
        return demadresse;
    }

    public void setDemadresse(String demadresse) {
        this.demadresse = demadresse;
    }

    public char getdemsexe() {
        return demsexe;
    }

    public void setdemsexe(char DEMSEXE) {
        this.demsexe = DEMSEXE;
    }

    public char getDemageunit() {
        return demageunit;
    }

    public void setDemageunit(char demageunit) {
        this.demageunit = demageunit;
    }

    public char getDemmark() {
        return demmark;
    }

    public void setDemmark(char demmark) {
        this.demmark = demmark;
    }

    public float getDemage() {
        return demage;
    }

    public void setDemage(float demage) {
        this.demage = demage;
    }
    //</editor-fold>
    public Demo(int demid, int demservice, int demdoctor, int demvettype, String demipp, String demnom, String demprenom, String demnomjf, String demdatenai, String demcoment, String demadresse, char DEMSEXE, char demageunit, char demmark, float demage) {
        this.demid = demid;
        this.demservice = demservice;
        this.demdoctor = demdoctor;
        this.demvettype = demvettype;
        this.demipp = demipp;
        this.demnom = demnom;
        this.demprenom = demprenom;
        this.demnomjf = demnomjf;
        this.demdatenai = demdatenai;
        this.demcoment = demcoment;
        this.demadresse = demadresse;
        this.demsexe = DEMSEXE;
        this.demageunit = demageunit;
        this.demmark = demmark;
        this.demage = demage;
        DemoListBuilder();
    }
    
    private void DemoListBuilder(){
        lista.add(0, demid);
        lista.add(1, demipp);
        lista.add(2, demnom);
        lista.add(3, demprenom);
        lista.add(4, demnomjf);
        lista.add(5, demsexe);
        lista.add(6, demage);
        lista.add(7, demageunit);
        lista.add(8, demdatenai);
        lista.add(9, demcoment);
        lista.add(10, demadresse);
        lista.add(11, demmark);
        lista.add(12, demservice);
        lista.add(13, demdoctor);
        lista.add(14,demvettype);        
    }
    
    public void UpdateDemoList(){
        lista.add(0, demid);
        lista.add(1, demipp);
        lista.add(2, demnom);
        lista.add(3, demprenom);
        lista.add(4, demnomjf);
        lista.add(5, demsexe);
        lista.add(6, demage);
        lista.add(7, demageunit);
        lista.add(8, demdatenai);
        lista.add(9, demcoment);
        lista.add(10, demadresse);
        lista.add(11, demmark);
        lista.add(12, demservice);
        lista.add(13, demdoctor);
        lista.add(14,demvettype);        
    }
        
}

/**
 * Representa los atributos de la tabla Dossier de la base de datos de la ML.
 * @author HotLine.Madrid
 */
class Dossier{        
    int dosid, demid, dosservice, comment1, comment2, dosdoctor, doslastanaid, doslasttubegodet, doslasttubeplateau, dosbackup;    
    String dosdate, dostime, doslastdaterun, doslastsampleid, demdatetime, prelevdatetime, archdatetime;
    float dosage;
    char dosageunit;
    
    ///<editor-fold defaultstate="collapsed" desc="getters and setters">
     public int getDosid() {
        return dosid;
    }
    
    public void setDosid(int dosid) {    
        this.dosid = dosid;
    }

    public int getDemid() {
        return demid;
    }

    public void setDemid(int demid) {
        this.demid = demid;
    }

    public int getDosservice() {
        return dosservice;
    }

    public void setDosservice(int dosservice) {
        this.dosservice = dosservice;
    }

    public int getComment1() {
        return comment1;
    }

    public void setComment1(int comment1) {
        this.comment1 = comment1;
    }

    public int getComment2() {
        return comment2;
    }

    public void setComment2(int comment2) {
        this.comment2 = comment2;
    }

    public int getDosdoctor() {
        return dosdoctor;
    }

    public void setDosdoctor(int dosdoctor) {
        this.dosdoctor = dosdoctor;
    }

    public int getDoslastanaid() {
        return doslastanaid;
    }

    public void setDoslastanaid(int doslastanaid) {
        this.doslastanaid = doslastanaid;
    }

    public int getDoslasttubegodet() {
        return doslasttubegodet;
    }

    public void setDoslasttubegodet(int doslasttubegodet) {
        this.doslasttubegodet = doslasttubegodet;
    }

    public int getDoslasttubeplateau() {
        return doslasttubeplateau;
    }

    public void setDoslasttubeplateau(int doslasttubeplateau) {
        this.doslasttubeplateau = doslasttubeplateau;
    }

    public int getDosbackup() {
        return dosbackup;
    }

    public void setDosbackup(int dosbackup) {
        this.dosbackup = dosbackup;
    }

    public String getDosdate() {
        return dosdate;
    }

    public void setDosdate(String dosdate) {
        this.dosdate = dosdate;
    }

    public String getDostime() {
        return dostime;
    }

    public void setDostime(String dostime) {
        this.dostime = dostime;
    }

    public String getDoslastdaterun() {
        return doslastdaterun;
    }

    public void setDoslastdaterun(String doslastdaterun) {
        this.doslastdaterun = doslastdaterun;
    }

    public String getDoslastsampleid() {
        return doslastsampleid;
    }

    public void setDoslastsampleid(String doslastsampleid) {
        this.doslastsampleid = doslastsampleid;
    }

    public String getDemdatetime() {
        return demdatetime;
    }

    public void setDemdatetime(String demdatetime) {
        this.demdatetime = demdatetime;
    }

    public String getPrelevdatetime() {
        return prelevdatetime;
    }

    public void setPrelevdatetime(String prelevdatetime) {
        this.prelevdatetime = prelevdatetime;
    }

    public String getArchdatetime() {
        return archdatetime;
    }

    public void setArchdatetime(String archdatetime) {
        this.archdatetime = archdatetime;
    }

    public float getDosage() {
        return dosage;
    }

    public void setDosage(float dosage) {
        this.dosage = dosage;
    }

    public char getDosageunit() {
        return dosageunit;
    }

    public void setDosageunit(char dosageunit) {
        this.dosageunit = dosageunit;
    }
    //</editor-fold>
    
    public Dossier(int dosid, int demid, int dosservice, int comment1, int comment2, int dosdoctor, int doslastanaid, int doslasttubegodet, int doslasttubeplateau, int dosbackup, String dosdate, String dostime, String doslastdaterun, String doslastsampleid, String demdatetime, String prelevdatetime, String archdatetime, float dosage, char dosageunit) {
        this.dosid = dosid;
        this.demid = demid;
        this.dosservice = dosservice;
        this.comment1 = comment1;
        this.comment2 = comment2;
        this.dosdoctor = dosdoctor;
        this.doslastanaid = doslastanaid;
        this.doslasttubegodet = doslasttubegodet;
        this.doslasttubeplateau = doslasttubeplateau;
        this.dosbackup = dosbackup;
        this.dosdate = dosdate;
        this.dostime = dostime;
        this.doslastdaterun = doslastdaterun;
        this.doslastsampleid = doslastsampleid;
        this.demdatetime = demdatetime;
        this.prelevdatetime = prelevdatetime;
        this.archdatetime = archdatetime;
        this.dosage = dosage;
        this.dosageunit = dosageunit;
    }
}

/**
 * Representa los valores que se pueden obtener de la tabla AnalyseA en la base de datos de la ML.
 * @author HotLine.Madrid
 */
class AnalyseA{
    int dosid, chiid, bilid, result, demid, rerun, anaid, resvalidusr ;
    char rescritique, last;
    String libelle, sampleid, flags;
    float fdilut;
    
    ///<editor-fold defaultstate="collapsed" desc="getters and setters">
    public int getDosid() {
        return dosid;
    }

    public void setDosid(int dosid) {
        this.dosid = dosid;
    }

    public int getChiid() {
        return chiid;
    }

    public void setChiid(int chiid) {
        this.chiid = chiid;
    }

    public int getBilid() {
        return bilid;
    }

    public void setBilid(int bilid) {
        this.bilid = bilid;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getDemid() {
        return demid;
    }

    public void setDemid(int demid) {
        this.demid = demid;
    }

    public int getRerun() {
        return rerun;
    }

    public void setRerun(int rerun) {
        this.rerun = rerun;
    }

    public int getAnaid() {
        return anaid;
    }

    public void setAnaid(int anaid) {
        this.anaid = anaid;
    }

    public int getResvalidusr() {
        return resvalidusr;
    }

    public void setResvalidusr(int resvalidusr) {
        this.resvalidusr = resvalidusr;
    }

    public char getRescritique() {
        return rescritique;
    }

    public void setRescritique(char rescritique) {
        this.rescritique = rescritique;
    }

    public char getLast() {
        return last;
    }

    public void setLast(char last) {
        this.last = last;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getSampleid() {
        return sampleid;
    }

    public void setSampleid(String sampleid) {
        this.sampleid = sampleid;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public float getFdilut() {
        return fdilut;
    }

    public void setFdilut(float fdilut) {
        this.fdilut = fdilut;
    }
    //</editor-fold>
    
    public AnalyseA(int dosid, int chiid, int bilid, int result, int demid, int rerun, int anaid, int resvalidusr, char rescritique, char last, String libelle, String sampleid, String flags, float fdilut) {
        this.dosid = dosid;
        this.chiid = chiid;
        this.bilid = bilid;
        this.result = result;
        this.demid = demid;
        this.rerun = rerun;
        this.anaid = anaid;
        this.resvalidusr = resvalidusr;
        this.rescritique = rescritique;
        this.last = last;
        this.libelle = libelle;
        this.sampleid = sampleid;
        this.flags = flags;
        this.fdilut = fdilut;
    }
}

/**
 * Representa los valores que se pueden obtener de la tabla commentaire de la ML.
 * @author HotLine.Madrid
 */
class Commentaire{
    int comid, comindex;
    String commentaire;
    
    ///<editor-fold defaultstate="collapsed" desc="getters and setters">

    public int getComid() {
        return comid;
    }

    public void setComid(int comid) {
        this.comid = comid;
    }

    public int getComindex() {
        return comindex;
    }

    public void setComindex(int comindex) {
        this.comindex = comindex;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    //</editor-fold>

    public Commentaire(int comid, int comindex, String commentaire) {
        this.comid = comid;
        this.comindex = comindex;
        this.commentaire = commentaire;
    }
}

/**
 * Representa los atributos de la tabla Run_A_EQC.
 * @author HotLine.Madrid
 */
class Run_A_EQC{
    int runAID, protoid, status, userid, errcode, instrid, ppid;
    String protoname, lot, dt, comment;
    
    ///<editor-fold defaultstate="collapsed" desc="getters and setters">

    public int getRunAID() {
        return runAID;
    }

    public void setRunAID(int runAID) {
        this.runAID = runAID;
    }

    public int getProtoid() {
        return protoid;
    }

    public void setProtoid(int protoid) {
        this.protoid = protoid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public int getInstrid() {
        return instrid;
    }

    public void setInstrid(int instrid) {
        this.instrid = instrid;
    }

    public int getPpid() {
        return ppid;
    }

    public void setPpid(int ppid) {
        this.ppid = ppid;
    }

    public String getProtoname() {
        return protoname;
    }

    public void setProtoname(String protoname) {
        this.protoname = protoname;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    //</editor-fold>

    public Run_A_EQC(int runAID, int protoid, int status, int userid, int errcode, int instrid, int ppid, String protoname, String lot, String dt, String comment) {
        this.runAID = runAID;
        this.protoid = protoid;
        this.status = status;
        this.userid = userid;
        this.errcode = errcode;
        this.instrid = instrid;
        this.ppid = ppid;
        this.protoname = protoname;
        this.lot = lot;
        this.dt = dt;
        this.comment = comment;
    }
    
}

/**
 * Representa los atributos de la tabla ReactifA.
 * @author HotLine.Madrid
 */
class ReactifA{
    int reagentAID, dosid, reagentRunAID;
    String reagentlot, reagenttype, reagentdatetime;
    
    ///<editor-fold defaultstate="collapsed" desc="getters and setters">

    public int getReagentAID() {
        return reagentAID;
    }

    public void setReagentAID(int reagentAID) {
        this.reagentAID = reagentAID;
    }

    public int getDosid() {
        return dosid;
    }

    public void setDosid(int dosid) {
        this.dosid = dosid;
    }

    public int getReagentRunAID() {
        return reagentRunAID;
    }

    public void setReagentRunAID(int reagentRunAID) {
        this.reagentRunAID = reagentRunAID;
    }

    public String getReagentlot() {
        return reagentlot;
    }

    public void setReagentlot(String reagentlot) {
        this.reagentlot = reagentlot;
    }

    public String getReagenttype() {
        return reagenttype;
    }

    public void setReagenttype(String reagenttype) {
        this.reagenttype = reagenttype;
    }

    public String getReagentdatetime() {
        return reagentdatetime;
    }

    public void setReagentdatetime(String reagentdatetime) {
        this.reagentdatetime = reagentdatetime;
    }
    //</editor-fold>

    public ReactifA(int reagentAID, int dosid, int reagentRunAID, String reagentlot, String reagenttype, String reagentdatetime) {
        this.reagentAID = reagentAID;
        this.dosid = dosid;
        this.reagentRunAID = reagentRunAID;
        this.reagentlot = reagentlot;
        this.reagenttype = reagenttype;
        this.reagentdatetime = reagentdatetime;
    }
}

/**
 * Representa los atributos de la tabla RunA.
 * @author HotLine.Madrid
 */
class RunA{
    int runAID, dosid, RunNumber, RunAna;
    String RunDateTime, AnaType, AnaName, SerialNb;
    
    ///<editor-fold defaultstate="collapsed" desc="getters and setters">

    public int getRunAID() {
        return runAID;
    }

    public void setRunAID(int runAID) {
        this.runAID = runAID;
    }

    public int getDosid() {
        return dosid;
    }

    public void setDosid(int dosid) {
        this.dosid = dosid;
    }

    public int getRunNumber() {
        return RunNumber;
    }

    public void setRunNumber(int RunNumber) {
        this.RunNumber = RunNumber;
    }

    public int getRunAna() {
        return RunAna;
    }

    public void setRunAna(int RunAna) {
        this.RunAna = RunAna;
    }

    public String getRunDateTime() {
        return RunDateTime;
    }

    public void setRunDateTime(String RunDateTime) {
        this.RunDateTime = RunDateTime;
    }

    public String getAnaType() {
        return AnaType;
    }

    public void setAnaType(String AnaType) {
        this.AnaType = AnaType;
    }

    public String getAnaName() {
        return AnaName;
    }

    public void setAnaName(String AnaName) {
        this.AnaName = AnaName;
    }

    public String getSerialNb() {
        return SerialNb;
    }

    public void setSerialNb(String SerialNb) {
        this.SerialNb = SerialNb;
    }
    //</editor-fold>

    public RunA(int runAID, int dosid, int RunNumber, int RunAna, String RunDateTime, String AnaType, String AnaName, String SerialNb) {
        this.runAID = runAID;
        this.dosid = dosid;
        this.RunNumber = RunNumber;
        this.RunAna = RunAna;
        this.RunDateTime = RunDateTime;
        this.AnaType = AnaType;
        this.AnaName = AnaName;
        this.SerialNb = SerialNb;
    }
}

/**
 * Representa los atributos de la tabla ScandataA.
 * @author HotLine.Madrid
 */
class ScandataA extends Data{
    int sdid, dosid, anaid;
    String scantype, dilut, val;    
    
    ///<editor-fold defaultstate="collapsed" desc="getters and setters">

    public int getSdid() {
        return sdid;
    }

    public void setSdid(int sdid) {
        this.sdid = sdid;
    }

    public int getDosid() {
        return dosid;
    }

    public void setDosid(int dosid) {
        this.dosid = dosid;
    }

    public int getAnaid() {
        return anaid;
    }

    public void setAnaid(int anaid) {
        this.anaid = anaid;
    }

    public String getScantype() {
        return scantype;
    }

    public void setScantype(String scantype) {
        this.scantype = scantype;
    }

    public String getDilut() {
        return dilut;
    }

    public void setDilut(String dilut) {
        this.dilut = dilut;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
    //</editor-fold>    

    public ScandataA(int sdid, int dosid, int anaid, String scantype, String dilut, String val) {
        this.sdid = sdid;
        this.dosid = dosid;
        this.anaid = anaid;
        this.scantype = scantype;
        this.dilut = dilut;
        this.val = val;
        super.lista = null;
        ScandataListBuilder();
    }
    
     private void ScandataListBuilder(){
        lista.add(0, sdid);
        lista.add(1, dosid);
        lista.add(2, anaid);
        lista.add(3, scantype);
        lista.add(4, dilut);
        lista.add(5, val);    
    }
    
    public void updateScandataList(){
        lista.add(0, sdid);
        lista.add(1, dosid);
        lista.add(2, anaid);
        lista.add(3, scantype);
        lista.add(4, dilut);
        lista.add(5, val);
    }    
}