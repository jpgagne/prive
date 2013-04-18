package inf2015.assurance;

import java.util.List;


public class ValeurEntreeJSON 
{
 
    
 String dossier;
 String mois;
 List<EnregistrementJSON_Reclamation> reclamations;


    
    
    
    @Override
    public String toString()
    {
        return "Dossier: "+dossier+", Mois: "+mois+" NbReclamations: "+reclamations.size();
    }
}
