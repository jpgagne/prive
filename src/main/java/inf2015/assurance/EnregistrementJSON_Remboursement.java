package inf2015.assurance;



public class EnregistrementJSON_Remboursement
{

public String soin;
public String code;
public String date;
public String montant;

public EnregistrementJSON_Remboursement(String soin, String code, String date, String montant) {
    this.soin = soin;
    this.code = code;
    this.date = date;
    this.montant = montant;
}

private EnregistrementJSON_Remboursement()
{

}



}   // end of class EnregistrementJSON_Remboursement