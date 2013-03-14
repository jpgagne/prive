
import java.util.HashMap;
import java.util.Map;

public class Contrat
{
private char typeContrat;

private Map<Soin, Couverture> couvertures;

Contrat(char typeContrat) 
    {
    this.typeContrat = typeContrat;
    couvertures = new HashMap<>();
    }

protected void ajouterCouverture(Couverture nouvelleCouverture)
    {
    this.couvertures.put(nouvelleCouverture.getSoin(), nouvelleCouverture);
    }

protected void setMapCouvertures (Map<Soin, Couverture> couvertures)
    {
    this.couvertures = couvertures;
    }


protected boolean aCouverture(Soin soin)
    {
    return this.couvertures.containsKey(soin);
    }

protected Couverture trouverCouverture(Soin soin) throws ExceptionSoinNonCouvert
    {
    if (aCouverture(soin))
        {
        return this.couvertures.get(soin);
        }
    throw new ExceptionSoinNonCouvert(soin);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    //<editor-fold defaultstate="collapsed" desc="etablirTaux()throws ExceptionDonneeInvalide">
//    private void etablirTaux() throws ExceptionFinProgramme//throws ExceptionDonneeInvalide
//    {
//
//        switch (typeContrat) {
//
//            case 'A':
//                soin0Taux = 25;
//                soin0Max = -1;
//
//                soin1Taux = 35;
//                soin1Max = -1;
//
//                soin2Taux = 25;
//                soin2Max = -1;
//
//                soin3Taux = 0;
//                soin3Max = -1;
//
//                soin4Taux = 0;
//                soin4Max = -1;
//
//                soin5Taux = 25;
//                soin5Max = -1;
//
//                soin6Taux = 40;
//                soin6Max = -1;
//
//                soin7Taux = 0;
//                soin7Max = -1;
//
//                soin150Taux = 0;
//                soin150Max = -1;
//
//                soin175Taux = 50;
//                soin175Max = -1;
//
//                break;
//
//
//            case 'B':
//                soin0Taux = 50;
//                soin0Max = 40;
//
//                soin1Taux = 50;
//                soin1Max = 50;
//
//                soin2Taux = 100;
//                soin2Max = -1;
//
//                soin3Taux = 50;
//                soin3Max = -1;
//
//                soin4Taux = 0;
//                soin4Max = -1;
//
//                soin5Taux = 50;
//                soin5Max = 50;
//
//                soin6Taux = 100;
//                soin6Max = -1;
//
//                soin7Taux = 70;
//                soin7Max = -1;
//
//                soin150Taux = 0;
//                soin150Max = -1;
//
//                soin175Taux = 75;
//                soin175Max = -1;
//
//                break;
//
//
//            case 'C':
//                soin0Taux = 90;
//                soin0Max = -1;
//
//                soin1Taux = 95;
//                soin1Max = -1;
//
//                soin2Taux = 90;
//                soin2Max = -1;
//
//                soin3Taux = 90;
//                soin3Max = -1;
//
//                soin4Taux = 90;
//                soin4Max = -1;
//
//                soin5Taux = 90;
//                soin5Max = -1;
//
//                soin6Taux = 75;
//                soin6Max = -1;
//
//                soin7Taux = 90;
//                soin7Max = -1;
//
//                soin150Taux = 85;
//                soin150Max = -1;
//
//                soin175Taux = 90;
//                soin175Max = -1;
//
//                break;
//
//
//            case 'D': {
//                soin0Taux = 100;
//                soin0Max = 85;
//
//                soin1Taux = 100;
//                soin1Max = 75;
//
//                soin2Taux = 100;
//                soin2Max = 100;
//
//                soin3Taux = 100;
//                soin3Max = -1;
//
//                soin4Taux = 100;
//                soin4Max = 65;
//
//                soin5Taux = 100;
//                soin5Max = -1;
//
//                soin6Taux = 100;
//                soin6Max = 100;
//
//                soin7Taux = 100;
//                soin7Max = 90;
//
//                soin150Taux = 100;
//                soin150Max = 150;
//
//                soin175Taux = 95;
//                soin175Max = -1;
//
//                break;
//            }
//
//
//            case 'E': {
//                soin0Taux = 15;
//                soin0Max = -1;
//
//                soin1Taux = 25;
//                soin1Max = -1;
//
//                soin2Taux = 12;
//                soin2Max = -1;
//
//                soin3Taux = 60;
//                soin3Max = -1;
//
//                soin4Taux = 25;
//                soin4Max = 15;
//
//                soin5Taux = 30;
//                soin5Max = 20;
//
//                soin6Taux = 15;
//                soin6Max = -1;
//
//                soin7Taux = 22;
//                soin7Max = -1;
//
//                soin150Taux = 15;
//                soin150Max = -1;
//
//                soin175Taux = 25;
//                soin175Max = 20;
//
//                break;
//            }
//            default:
//                throw new ExceptionFinProgramme("Contrat type inconnu: " + typeContrat);
//            //throw new ExceptionDonneeInvalide("Contrat type inconnu: "+typeContrat);
//        }
//
//    }
//    //</editor-fold>
//
//    protected double calculerRemboursement(Integer noSoin, Double dReclamation) throws ExceptionFinProgramme {
//        double montant = 0;
//        Integer pourcentageCouvert = 0;
//        Integer seuilMax = 0;
//
//        System.out.println("Calcul du remboursement: NoSoin:" + noSoin + " demandé:" + dReclamation);
//
//        switch (noSoin) {
//            case 0: {
//                pourcentageCouvert = this.soin0Taux;
//                seuilMax = this.soin0Max;
//                break;
//            }
//            case 100: {
//                pourcentageCouvert = this.soin1Taux;
//                seuilMax = this.soin1Max;
//                break;
//            }
//
//            case 200: {
//                pourcentageCouvert = this.soin2Taux;
//                seuilMax = this.soin2Max;
//                break;
//            }
//
//            case 400: {
//                pourcentageCouvert = this.soin4Taux;
//                seuilMax = this.soin4Max;
//                break;
//            }
//            case 500: {
//                pourcentageCouvert = this.soin5Taux;
//                seuilMax = this.soin5Max;
//                break;
//            }
//            case 600: {
//                pourcentageCouvert = this.soin6Taux;
//                seuilMax = this.soin6Max;
//                break;
//            }
//            case 700: {
//                pourcentageCouvert = this.soin7Taux;
//                seuilMax = this.soin7Max;
//                break;
//            }
//            default: {
//                if (noSoin < 400 & noSoin >= 300) {
//                    pourcentageCouvert = this.soin3Taux;
//                    seuilMax = this.soin3Max;
//                    
//                } else if (noSoin == 150) {
//                    pourcentageCouvert = this.soin150Taux;
//                    seuilMax = this.soin150Max;
//                    
//                }else if (noSoin == 175) {
//                    pourcentageCouvert = this.soin175Taux;
//                    seuilMax = this.soin175Max;
//                    
//                } else {
//                    throw new ExceptionFinProgramme("No de soin inconnu= " + noSoin);
//                }
//            }
//        } //end switch
//
//        System.out.println("Pourcentage:" + pourcentageCouvert);
//        System.out.println("Max:" + seuilMax);
//        montant = dReclamation * pourcentageCouvert / 100;
//
//        if (seuilMax >= 0) {
//            if (montant > seuilMax) {
//                montant = seuilMax;
//            }
//        }
//        System.out.println("Accordé:" + montant);
//
//        return montant;
//    }

    protected char getTypeContrat() {
        return this.typeContrat;
    }

}
