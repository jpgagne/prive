/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamationsassurance;

/**
 *
 * @author JP
 */
public class Contrat 
{
 /* A,B,C ou D, auto converti à uppercase sinon exceptionDonneeInvalide*/
    private char typeContrat;  
    
    
    
    //<editor-fold defaultstate="collapsed" desc="attributs du contrat">
    
    
    
    private String soin0Nom = "Massothérapie";
    private int soin0Taux;
    private int soin0Max = -1;
    
    private String soin1Nom = "Ostéopathie";
    private int soin1Taux;
    private int soin1Max = -1;
    
    private String soin2Nom = "Psychologie individuelle";
    private int soin2Taux;
    private int soin2Max = -1;
    
    private String soin3Nom = "Soins dentaires";
    private int soin3Taux;
    private int soin3Max = -1;
    
    private String soin4Nom = "Naturopathie, acupuncture";
    private int soin4Taux;
    private int soin4Max = -1;
    
    private String soin5Nom = "Chiropratie";
    private int soin5Taux;
    private int soin5Max = -1;
    
    private String soin6Nom = "Physiothérapie";
    private int soin6Taux;
    private int soin6Max = -1;
    
    private String soin7Nom = "Ortophonie, ergothérapie";
    private int soin7Taux;
    private int soin7Max = -1;
    //</editor-fold>
    
    /* constructeur*/
    Contrat( char typeContrat)throws ExceptionDonneeInvalide
    {
        this.typeContrat = Character.toUpperCase(typeContrat);
        etablirTaux();
    }
    
    //<editor-fold defaultstate="collapsed" desc="etablirTaux()throws ExceptionDonneeInvalide">
    private void etablirTaux()throws ExceptionDonneeInvalide
    {
        
        switch (typeContrat)
        {
            case 'A':
                soin0Taux = -1;
                soin0Max = -1;
                soin1Taux = -1;
                soin1Max = -1;
                soin2Taux = -1;
                soin2Max = -1;
                soin3Taux = -1;
                soin3Max = -1;
                soin4Taux = -1;
                soin4Max = -1;
                soin5Taux = -1;
                soin4Max = -1;
                soin6Taux = -1;
                soin6Max = -1;
                soin7Taux = -1;
                soin7Max = -1;
                
                break;
                
                
            case 'B':
                soin0Taux = -1;
                soin0Max = -1;
                soin1Taux = -1;
                soin1Max = -1;
                soin2Taux = -1;
                soin2Max = -1;
                soin3Taux = -1;
                soin3Max = -1;
                soin4Taux = -1;
                soin4Max = -1;
                soin5Taux = -1;
                soin4Max = -1;
                soin6Taux = -1;
                soin6Max = -1;
                soin7Taux = -1;
                soin7Max = -1;
                
                break;
                
                
            case 'C':
                soin0Taux = -1;
                soin0Max = -1;
                soin1Taux = -1;
                soin1Max = -1;
                soin2Taux = -1;
                soin2Max = -1;
                soin3Taux = -1;
                soin3Max = -1;
                soin4Taux = -1;
                soin4Max = -1;
                soin5Taux = -1;
                soin4Max = -1;
                soin6Taux = -1;
                soin6Max = -1;
                soin7Taux = -1;
                soin7Max = -1;
                
                break;
                
            case 'D':
                soin0Taux = -1;
                soin0Max = -1;
                soin1Taux = -1;
                soin1Max = -1;
                soin2Taux = -1;
                soin2Max = -1;
                soin3Taux = -1;
                soin3Max = -1;
                soin4Taux = -1;
                soin4Max = -1;
                soin5Taux = -1;
                soin4Max = -1;
                soin6Taux = -1;
                soin6Max = -1;
                soin7Taux = -1;
                soin7Max = -1;
                
                break;
                
            default :
                throw new ExceptionDonneeInvalide("Contrat type inconnu: "+typeContrat);
        }
    
    }
    //</editor-fold>

    
protected char getTypeContrat()
{
    return this.typeContrat;
}
protected int getTaux( int noSoin)
{
    return 0;
}
protected int getMax( int noSoin)
{
    return 0;
}

protected String getNomSoin(int noSoin)
{
    return "";
}
}
