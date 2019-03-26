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
    public void kortinSaldoAlussaOikein(){
        assertEquals("saldo: 0.10", kortti.toString());
    }
    @Test
    public void kortilleLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(25);
        assertEquals("saldo: 0.35", kortti.toString());
    }
    @Test
    public void saldoVaheneeOikeinJosRahaaOnTarpeeksi() {
        
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.5", kortti.toString());
        
    }
    
    @Test
    public void palauttaaTrueJosRahatRiittavat() {
        
        assertEquals(kortti.otaRahaa(5), true);
    }
    
    @Test
    public void palauttaaFalseJosRahatEivatRiita() {
        
        assertEquals(kortti.otaRahaa(15), false);
    }
    @Test
    public void saldoEiMuutuJosRahaaEiTarpeeksi() {
        
        kortti.otaRahaa(15);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    @Test
    public void palauttaaSaldon() {
        
        assertEquals(kortti.saldo(), 10);
    }
}

