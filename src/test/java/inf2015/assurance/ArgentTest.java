package inf2015.assurance;



import junit.framework.TestCase;

public class ArgentTest extends TestCase {
    
    public ArgentTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of estPositif method, of class Argent.
     */
    public void testEstPositif() {
        System.out.println("estPositif");
        Argent instance = new Argent(1);
        boolean expResult = false;
        boolean result = instance.estPositif();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMontantCentimes method, of class Argent.
     */
    public void testGetMontantCentimes() {
        System.out.println("getMontantCentimes");
        Argent instance = new Argent(1);
        Integer expResult = 1;
        Integer result = instance.getMontantCentimes();
        assertEquals(expResult, result);
    }

    /**
     * Test of floatValue method, of class Argent.
     */
    public void testFloatValue() {
        System.out.println("floatValue");
        Argent instance = new Argent(1);
        Double expResult = 1.0;
        Double result = instance.floatValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of multiplierPar method, of class Argent.
     */
    public void testMultiplierPar() {
        System.out.println("multiplierPar");
        Integer multiplicateur = 1;
        Argent instance = new Argent(1);
        instance.multiplierPar(multiplicateur);
    }

    /**
     * Test of diviserPar method, of class Argent.
     */
    public void testDiviserPar() {
        System.out.println("diviserPar");
        Integer diviseur = 1;
        Argent instance = new Argent(1);
        instance.diviserPar(diviseur);
    }

    /**
     * Test of additionner method, of class Argent.
     */
    public void testAdditionner() {
        System.out.println("additionner");
        Argent valeurAdditionnee = new Argent(1);
        Argent instance = new Argent(1);
        instance.additionner(valeurAdditionnee);
    }

    /**
     * Test of soustraire method, of class Argent.
     */
    public void testSoustraire() {
        System.out.println("soustraire");
        Argent valeurSoustraite = new Argent(1);
        Argent instance = new Argent(1);
        instance.soustraire(valeurSoustraite);
    }

    /**
     * Test of toString method, of class Argent.
     */
    public void testToString() {
        System.out.println("toString");
        Argent instance = new Argent(1);
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Argent.
     */
    public void testCompareTo() {
        System.out.println("compareTo");
        Argent o = new Argent(1);
        Argent instance = new Argent(1);
        int expResult = 0;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Argent.
     */
    public void testEquals() {
        System.out.println("equals");
        Object autreObjet = null;
        Argent instance = new Argent(hashCode());
        boolean expResult = false;
        boolean result = instance.equals(autreObjet);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Argent.
     */
    public void testHashCode() {
        System.out.println("hashCode");
        Argent instance = new Argent(hashCode());
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
}
