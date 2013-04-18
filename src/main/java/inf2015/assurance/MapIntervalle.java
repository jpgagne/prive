package inf2015.assurance;

import java.util.TreeMap;


class MapIntervalle<T> extends  TreeMap<Intervalle, T>
{

public MapIntervalle()
    {
    super();
    }


public T inserer(Intervalle key, T objet) throws ExceptionIntervalle
    {
    if (!deborde(key))
        {
        this.put(key, objet);
        return objet;
        }
    else
    {
    throw new ExceptionIntervalle(key);
    }
}

    
public T acceder(Integer key)
    {
    return this.get(trouverCle(key));
    }
    
    
public Intervalle trouverCle(Integer cleInteger)
    {
    for (Intervalle cleIntervalle: this.keySet())
        {
        if (cleIntervalle.inclus(cleInteger))
            {
            return cleIntervalle;
            }
        }
    return null;
    }

public boolean present(Integer cleInteger)
    {
    return (this.trouverCle(cleInteger) != null);
    }

    
public boolean deborde(Intervalle intervalle)
    {
    return (this.present(intervalle.getBornePlafond()))
            ||(this.present(intervalle.getBornePlancher()));
    }



}   // end of class HashMapIntervalle