/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pml_backup;

/**
 *
 * @author Isaac
 */
public class datos {    
    Demo demo;
    Dossier dossier;
}

class Demo{    
    int demid, demservice, demdoctor, demvettype;
    String demipp, demnom, demprenom, demnomjf, demdatenai, demcoment, demadresse;
    char DEMSEXE, demageunit, demmark;
    float demage;    
}

class Dossier{    
    int dosid, demid, dosservice, comment1, comment2,dosdoctor, doslastanaid, doslasttubegodet, doslasttubeplateau, dosbackup;
    String dosdate, dostime, doslastdaterun, doslastsampleid, demdatetime, prelevdatetime, archdatetime;
    float dosage;
    char dosageunit;
}