//partie modifiable par chakib tchantchane
package reclamationsassurance;


public class Contrat 
{
 /* A,B,C ou D, auto converti à uppercase sinon exceptionDonneeInvalide //desactive, fait plus haut*/
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
    Contrat( char typeContrat) throws ExceptionFinProgramme//throws ExceptionDonneeInvalide
    {
        this.typeContrat = Character.toUpperCase(typeContrat);
        etablirTaux();
    }
    
    //<editor-fold defaultstate="collapsed" desc="etablirTaux()throws ExceptionDonneeInvalide">
    private void etablirTaux() throws ExceptionFinProgramme//throws ExceptionDonneeInvalide
    {
        
        switch (typeContrat)
        {
            case 'A':
                soin0Taux = 25;
                soin0Max = -1;
                soin1Taux = 25;
                soin1Max = -1;
                soin2Taux = 25;
                soin2Max = -1;
                soin3Taux = 0;
                soin3Max = -1;
                soin4Taux = 0;
                soin4Max = -1;
                soin5Taux = 25;
                soin4Max = -1;
                soin6Taux = 40;
                soin6Max = -1;
                soin7Taux = 0;
                soin7Max = -1;
                
                break;
                
                
            case 'B':
                soin0Taux = 50;
                soin0Max = 40;
                soin1Taux = 50;
                soin1Max = 50;
                soin2Taux = 100;
                soin2Max = 70;
                soin3Taux = 50;
                soin3Max = -1;
                soin4Taux = 0;
                soin4Max = -1;
                soin5Taux = 50;
                soin4Max = 50;
                soin6Taux = 100;
                soin6Max = -1;
                soin7Taux = 70;
                soin7Max = -1;
                
                break;
                
                
            case 'C':
                soin0Taux = -90;
                soin0Max = -1;
                soin1Taux = -90;
                soin1Max = -1;
                soin2Taux = 90;
                soin2Max = -1;
                soin3Taux = 90;
                soin3Max = -1;
                soin4Taux = 90;
                soin4Max = -1;
                soin5Taux = 90;
                soin4Max = -1;
                soin6Taux = 90;
                soin6Max = -1;
                soin7Taux = 90;
                soin7Max = -1;
                
                break;
                
            case 'D':
            {
                soin0Taux = 100;
                soin0Max = 85;
                soin1Taux = 100;
                soin1Max = 75;
                soin2Taux = 100;
                soin2Max = 100;
                soin3Taux = 100;
                soin3Max = -1;
                soin4Taux = 100;
                soin4Max = 65;
                soin5Taux = 100;
                soin4Max = 75;
                soin6Taux = 100;
                soin6Max = 100;
                soin7Taux = 100;
                soin7Max = 90;
                
                break;
            }
            default :
                throw new ExceptionFinProgramme("Contrat type inconnu: "+typeContrat);
                //throw new ExceptionDonneeInvalide("Contrat type inconnu: "+typeContrat);
        }
    
    }
    //</editor-fold>

    
    
    
    
protected double calculerRemboursement(Integer noSoin, Double dReclamation) throws ExceptionFinProgramme
{
     double montant = 0;
     Integer pourcentageCouvert = 0;
     Integer seuilMax = 0;
     
     
     System.out.println("Calcul du remboursement: NoSoin:"+noSoin+" demandé:"+dReclamation);
     switch (noSoin)
     {
         case 0:
         {
             pourcentageCouvert = this.soin0Taux;
             seuilMax = this.soin0Max;  
             break; 
         }
        case 100:
         {
             pourcentageCouvert = this.soin1Taux;
             seuilMax = this.soin1Max;  
             break; 
         }
     
         case 200:
         {
             pourcentageCouvert = this.soin2Taux;
             seuilMax = this.soin2Max;  
             break; 
         }
   
         case 400:
         {
             pourcentageCouvert = this.soin4Taux;
             seuilMax = this.soin4Max;  
             break; 
         }
        case 500:
         {
             pourcentageCouvert = this.soin5Taux;
             seuilMax = this.soin5Max;  
             break; 
         }
         case 600:
         {
             pourcentageCouvert = this.soin6Taux;
             seuilMax = this.soin6Max;  
             break; 
         }
        case 700:
         {
             pourcentageCouvert = this.soin7Taux;
             seuilMax = this.soin7Max;  
             break; 
         }
         case 800:
         {
             others:
             {
             if (noSoin < 400 & noSoin >= 300)
                {
                pourcentageCouvert = this.soin3Taux;
                seuilMax = this.soin3Max; 
                }
             else 
                {
                 throw new ExceptionFinProgramme("No de soin inconnu= "+ noSoin);
                }
                
             }
             
         }
     } //end switch
     System.out.println("Pourcentage:"+pourcentageCouvert);
     System.out.println("Max:"+seuilMax);
   montant = dReclamation*pourcentageCouvert/100;
   
             if (seuilMax >= 0)
             {
                 if (montant > seuilMax)
                 {
                     montant = seuilMax;
                 }
                     
             }
    System.out.println("Accordé:"+montant);
    
  return montant;

}

protected char getTypeContrat()
{
    return this.typeContrat;
}
//protected int getTaux( int noSoin)
//{
//    
//    
//    switch(noSoin)
//    {
//        case 0:
//        case 100: 
//            
//    }
//    
//    
//    return 0;
//}
//protected int getMax( int noSoin)
//{
//    return 0;
//}
//
//protected String getNomSoin(int noSoin)
//{
//    return "";
//}
}
