/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.unicafe.Kassapaate;
import com.mycompany.unicafe.Maksukortti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heli
 */
public class KassapaateTest {
    
    public KassapaateTest() {
    }
    
    Kassapaate kassa;
    Maksukortti kortti;
    Maksukortti rahatonKortti;

    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
        rahatonKortti = new Maksukortti(100);

    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void alussaRahaaOikein() {
        assertTrue(100000 == kassa.kassassaRahaa());
    }
    
    @Test
    public void alussaEdullistenLounaidenMaaraOikein() {
        assertTrue(0 == kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void alussaMaukkaidenLounaidenMaaraOikein() {
        assertTrue(0 == kassa.maukkaitaLounaitaMyyty());
    }
    
    //käteisosaston toiminta
    
    @Test
    public void syoEdullisestiKateisellaKassaKasvaa() {
        kassa.syoEdullisesti(300);
        assertTrue(100240 == kassa.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiKateisellaKassaKasvaa() {
        kassa.syoMaukkaasti(500);
        assertTrue(100400 == kassa.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiVaihtorahanMaaraOikein() {
        int vaihtoraha = kassa.syoEdullisesti(300);
        assertTrue(60 == vaihtoraha);
    }
    
    @Test
    public void syoMaukkaastiVaihtorahanMaaraOikein() {
        int vaihtoraha = kassa.syoMaukkaasti(500);
        assertTrue(100 == vaihtoraha);
    }
    
    @Test
    public void edullistenLounaidenMaaraKasvaa() {
        kassa.syoEdullisesti(300);
        assertTrue(1 == kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaidenLounaidenMaaraKasvaa() {
        kassa.syoMaukkaasti(500);
        assertTrue(1 == kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiJosMaksuEiRiitaKassaEiMuutu() {
        kassa.syoEdullisesti(100);
        assertTrue(100000 == kassa.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiJosMaksuEiRiitaKassaEiMuutu() {
        kassa.syoEdullisesti(100);
        assertTrue(100000 == kassa.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiJosMaksuEiRiitaRahatPalautetaan() {
        int vaihtoraha = kassa.syoEdullisesti(100);
        assertTrue(100 == vaihtoraha);
    }
    
    @Test
    public void syoMaukkaastiJosMaksuEiRiitaRahatPalautetaan() {
        int vaihtoraha = kassa.syoMaukkaasti(100);
        assertTrue(100 == vaihtoraha);
    }
    
    @Test
    public void syoEdullisestiJosMaksuEiRiitaLounaidenMaaraEiMuutu() {
        kassa.syoEdullisesti(100);
        assertTrue(0 == kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiJosMaksuEiRiitaLounaidenMaaraEiMuutu() {
        kassa.syoMaukkaasti(100);
        assertTrue(0 == kassa.maukkaitaLounaitaMyyty());
    }
    
    //korttiostot
    
    @Test
    public void syoEdullisestiKortillaTrue() {
        assertTrue(kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void syoMaukkaastiKortillaTrue() {
        assertTrue(kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void syoEdullisestiKortillaLounaidenMaaraKasvaa() {
        kassa.syoEdullisesti(kortti);
        assertTrue(1 == kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiKortillaLounaidenMaaraKasvaa() {
        kassa.syoMaukkaasti(kortti);
        assertTrue(1 == kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiJosEiTarpeeksiRahaaKortinSaldoEiMuutu() {
        kassa.syoEdullisesti(rahatonKortti);
        assertTrue(100 == rahatonKortti.saldo());
    }
    
    @Test
    public void syoMaukkaastiJosEiTarpeeksiRahaaKortinSaldoEiMuutu() {
        kassa.syoMaukkaasti(rahatonKortti);
        assertTrue(100 == rahatonKortti.saldo());
    }
    
    @Test
    public void syoEdullisestiJosEiTarpeeksiRahaaLounaidenMaaraEiMuutu() {
        kassa.syoEdullisesti(rahatonKortti);
        assertTrue(0 == kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiJosEiTarpeeksiRahaaLounaidenMaaraEiMuutu() {
        kassa.syoMaukkaasti(rahatonKortti);
        assertTrue(0 == kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiKortillaFalse() {
        assertTrue(!kassa.syoEdullisesti(rahatonKortti));
    }
    
    @Test
    public void syoMaukkaastiKortillaFalse() {
        assertTrue(!kassa.syoMaukkaasti(rahatonKortti));
    }
    
    @Test
    public void syoEdullisestiKortillaKassaEiMuutu() {
        kassa.syoEdullisesti(kortti);
        assertTrue(100000 == kassa.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiKortillaKassaEiMuutu() {
        kassa.syoMaukkaasti(kortti);
        assertTrue(100000 == kassa.kassassaRahaa());
    }
    
    @Test
    public void lataaKortilleRahaaToimii() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertTrue(1500 == kortti.saldo());
    }
    
    @Test
    public void kortilleEiVoiLadataNegatiivistaSummaa() {
        kassa.lataaRahaaKortille(kortti, -500);
        assertTrue(1000 == kortti.saldo());
    }
    
    @Test
    public void lataaKortilleRahaaKasvattaaKassaa() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertTrue(100500 == kassa.kassassaRahaa());
    }
}
