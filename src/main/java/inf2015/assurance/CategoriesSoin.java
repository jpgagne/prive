package inf2015.assurance;

public class CategoriesSoin 
{


private MapIntervalle<Soin> mapSpoins;

private static CategoriesSoin instance = null;
private CategoriesSoin() 
{
chargerSoins();
}


public static CategoriesSoin getInstance()
    {
    if(instance == null)
        {
        instance = new CategoriesSoin();
        }
    return instance;
    }

private void chargerSoins()
{
try {
    this.mapSpoins = new MapIntervalle<Soin>();

    Soin nouveauSoin = new Soin(0, "Massothérapie"); 
    ajouterSoin (nouveauSoin);
    nouveauSoin = new Soin(100, "Ostéopathie"); 
    ajouterSoin (nouveauSoin);
    nouveauSoin = new Soin(150, "Kinésithérapie");
    ajouterSoin (nouveauSoin);
    nouveauSoin = new Soin(175, "Médecin généraliste privé");
    ajouterSoin (nouveauSoin);
    nouveauSoin = new Soin(200, "Psychologie individuelle"); 
    ajouterSoin (nouveauSoin);
    nouveauSoin = new Soin(new Intervalle(300, 399), "Soins dentaires"); 
    ajouterSoin (nouveauSoin);
    nouveauSoin = new Soin(400, "Naturopathie, acuponcture"); 
    ajouterSoin (nouveauSoin);
    nouveauSoin = new Soin(500, "Chiropratie"); 
    ajouterSoin (nouveauSoin);
    nouveauSoin = new Soin(600, "Physiothérapie"); 
    ajouterSoin ( nouveauSoin);
    nouveauSoin = new Soin(700, "Orthophonie, ergothérapie"); 
    ajouterSoin ( nouveauSoin);
    }
catch (ExceptionIntervalle excI)
    {
    throw new RuntimeException("Debordement dans les codes de soin", excI);
    }
}

private void ajouterSoin (Soin nouveauSoin) throws ExceptionIntervalle
    {
    this.mapSpoins.inserer(nouveauSoin.getIntervalleNoSoin(), nouveauSoin);
    }

public boolean soinExiste(Integer noSoin)
    {
    return this.mapSpoins.present(noSoin);
    }

public Soin trouverSoin(Integer noSoin)
    {
    return this.mapSpoins.acceder(noSoin);
    }
}
