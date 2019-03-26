package com.mycompany.unicafe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author papirila
 */
public class KassapaateTest {
    
    Kassapaate kassapaate;
    Maksukortti maksukortti;
    
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        maksukortti = new Maksukortti(10000);
    }
    
    @Test
    public void rahaMaaraOikein() {
        
        assertEquals(kassapaate.kassassaRahaa(), 100000);
    }
    
    @Test
    public void edullisiaLounaitaMyytyOikeaMaara() {
        
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 0);
    }
    @Test
    public void maukkaitaLounaitaMyytyOikeaMaara() {
        
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 0);
    }
    
    @Test
    public void kateisosastoToimii1() {
        
        assertEquals(kassapaate.syoEdullisesti(250), 10);
    }
    
    @Test
    public void kateisosastoToimii2() {
        
        assertEquals(kassapaate.syoEdullisesti(230), 230);
    }
    @Test
    public void kateisosastoToimii3() {
        
        assertEquals(kassapaate.syoMaukkaasti(420), 20);
    }
    @Test
    public void kateisosastoToimii4() {
        assertEquals(kassapaate.syoMaukkaasti(300), 300);
    }
    @Test
    public void kassassaRahaaEdullisenOstoksenJalkeen() {
        kassapaate.syoEdullisesti(250);
        assertEquals(kassapaate.kassassaRahaa(), 100240);
    }
    @Test
    public void kassassaRahaaMaukkaanOstoksenJalkeen() {
        kassapaate.syoMaukkaasti(450);
        assertEquals(kassapaate.kassassaRahaa(), 100400);
    }
    @Test
    public void josMaksuOnRiittavaEdullistenLounaidenMaaraKasvaa() {
        kassapaate.syoEdullisesti(240);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 1);
    }
    @Test
    public void josMaksuOnRiittavaMaukkaittenLounaidenMaaraKasvaa() {
        kassapaate.syoMaukkaasti(400);
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 1);
    }
    @Test
    public void josMaksuEiOleRiittavaEdullisesti1() {
        kassapaate.syoEdullisesti(230);
        assertEquals(kassapaate.kassassaRahaa(), 100000);
    }
    @Test
    public void josMaksuEiOleRiittavaMaukkaasti1() {
        kassapaate.syoMaukkaasti(350);
        assertEquals(kassapaate.kassassaRahaa(), 100000);
    }
    @Test
    public void josMaksuEiOleRiittavaEdullisesti2() {
        kassapaate.syoEdullisesti(200);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 0);
    }
    @Test
    public void josMaksuEiOleRiittavaMaukkaasti2() {
        kassapaate.syoMaukkaasti(200);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 0);
    }
    @Test 
    public void korttiosastoToimii1() {
        assertEquals(kassapaate.syoEdullisesti(maksukortti), true);
    }
    @Test 
    public void korttiosastoToimii2() {
        assertEquals(kassapaate.syoMaukkaasti(maksukortti), true);
    }
    @Test 
    public void korttiosastoToimiiEdullisesti() {
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 1);
    }
    @Test 
    public void korttiosastoToimiiMaukkaasti() {
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 1);
    }
    @Test
    public void korttiOsastotoimiiJosMaksuEiRiittava1() {
        Maksukortti kortti2 = new Maksukortti(200);
        assertEquals(kassapaate.syoEdullisesti(kortti2), false);
    }
    @Test
    public void korttiOsastotoimiiJosMaksuEiRiittava2() {
        Maksukortti kortti2 = new Maksukortti(200);
        assertEquals(kassapaate.syoMaukkaasti(kortti2), false);
    }
    @Test
    public void korttiosastoToimii3() {
        Maksukortti kortti2 = new Maksukortti(200);
        kassapaate.syoEdullisesti(kortti2);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 0);
    }
    @Test
    public void korttiosastoToimii4() {
        Maksukortti kortti2 = new Maksukortti(200);
        kassapaate.syoMaukkaasti(kortti2);
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 0);
    }
    @Test
    public void korttiosastoToimii5() {
        Maksukortti kortti2 = new Maksukortti(200);
        kassapaate.syoEdullisesti(kortti2);
        assertEquals(kortti2.saldo(), 200);
    }
    @Test
    public void korttiosastoToimii6() {
        Maksukortti kortti2 = new Maksukortti(200);
        kassapaate.syoMaukkaasti(kortti2);
        assertEquals(kortti2.saldo(), 200);
    }
    @Test
    public void korttiosastoToimii7() {
        Maksukortti kortti2 = new Maksukortti(240);
        kassapaate.syoEdullisesti(kortti2);
        assertEquals(kassapaate.kassassaRahaa(), 100000);
    }
    @Test
    public void korttiosastoToimii8() {
        Maksukortti kortti2 = new Maksukortti(400);
        kassapaate.syoMaukkaasti(kortti2);
        assertEquals(kassapaate.kassassaRahaa(), 100000);
    }
    @Test
    public void korttiOsastoToimii9() {
        maksukortti.lataaRahaa(200);
        assertEquals(maksukortti.saldo(), 10200);
    }
    @Test
    public void korttiOsastoToimii10() {
        kassapaate.lataaRahaaKortille(maksukortti, 100);
        assertEquals(kassapaate.kassassaRahaa(), 100100);
    }
    @Test
    public void kortilleEiVoiLadataNegatiivista() {
        kassapaate.lataaRahaaKortille(maksukortti, -20);
        assertEquals(maksukortti.saldo(), 10000);
    }
    @Test
    public void kortilleEiVoiLadataNegatiivista2() {
        kassapaate.lataaRahaaKortille(maksukortti, -20);
        assertEquals(kassapaate.kassassaRahaa(), 100000);
    }
    
}
