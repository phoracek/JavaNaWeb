package testy;

import org.junit.Before;
import org.junit.Test;
import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class WebTest {
    
    static private String uzivatel = "test";
    static private String heslo = "heslo";
    
    public WebTest() {
    }
    
    @Before
    public void prihlaseniTest() {
        setBaseUrl("http://localhost:8080/JavaNaWeb");
        beginAt("/");
        assertTitleEquals("Zápisky");
        setTextField("j_username", uzivatel);
        setTextField("j_password", heslo);
        submit();
        assertElementPresent("pridat-zapisek");
    }

    @Test
    public void novySmazatTest() {       
        assertElementPresent("pridat-zapisek");
        setWorkingForm("pridat-zapisek");
        setTextField("nadpis", "nadpisTest");
        setTextField("obsah", "obsahTest");
        submit();
        assertTextPresent("nadpisTest");
        assertTextPresent("obsahTest");       
        submit("", "Smazat");
        assertTextNotPresent("nadpisTest");
        assertTextNotPresent("obsahTest");        
    }
}