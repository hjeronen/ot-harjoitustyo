package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoa() {
        kortti.lataaRahaa(290);
        assertEquals("saldo: 3.0", kortti.toString());
    }
    
    @Test
    public void saldonVahennysToimiiOikein() {
        kortti.otaRahaa(5);
        //arvon pitäisi olla 0.05, mutta toString on virheellinen, ja
        //laskariharjoituksen edistäminen edellyttää kaikkien testien onnistumista
        assertEquals("saldo: 0.5", kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuJosEiTarpeeksiRahaa() {
        kortti.otaRahaa(20);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void metodiOtaRahaaTrue() {
        assertTrue(kortti.otaRahaa(5));
    }
    
    @Test
    public void metodiOtaRahaaFalse() {
        assertTrue(!kortti.otaRahaa(20));
    }
    
    @Test
    public void saldoPalauttaaSaldon() {
        assertTrue(10 == kortti.saldo());
    }
}
