# Vaatimusmäärittely

## Studytracker

Sovelluksen tarkoitus on pitää kirjaa opiskelijan opinnoista. Useamman käyttäjän on mahdollista käyttää sovellusta ja kaikilla
käyttäjillä on oma henkilökohtainen Studytrackerinsa.

Suunnitellut toiminnallisuudet:

* Opiskelija voi kirjautua sisään ohjelmaan
* Opiskelija voi lisätä kurssisuorituksia listalle
* Opiskelija voi lisätä kurssi-ilmoittautumisia listalle
* Opiskelija voi poistaa kurssi-ilmoittautumisia
* Ohjelma näyttää opiskelijan tämänhetkisen op määrän
* Ohjelmaan voi lisätä uuden käyttäjän

## Käyttöliittymäluonnos

Ohjelmalla on kirjautumisnäkymä, ja kurssilistausnäkymä. Kurssilistausnäkymään (UI) siirrytään kirjautumisnäkymästä, kun kirjautuminen
suoritetaan onnistuneesti. Ohjelma aukeaa kirjautumisnäkymään.

Ennen kirjautumista käyttäjä voi luoda uuden käyttäjän tietokantaan. Sen jälkeen käyttäjän on mahdollista kirjautua ohjelmaan.
Käyttäjän nimen ja salasanan yhdistelmä tarkastetaan tietokannasta, ja jos yhdistelmä löytyy, kirjautuminen onnistuu.
Käyttäjänimen ei tarvitse olla uniikki. Järjestelmä ilmoittaa, jos käyttäjänimen ja salasanan yhdistelmää ei löydy tietokannasta.

Kirjautumisen jälkeen käyttäjä voi lisätä kursseja listoille, poistaa ilmoittauduttuja kursseja ja merkitä ilmoittauduttuja kursseja 
suoritetuiksi. Ohjelma näyttää käyttäjän kokonaisopintopistemäärän.

### Jatkokehitysideoita

* Myös suoritettuja kursseja voi poistaa listalta ja tietokannasta
* Kokonaisopintopistemäärä päivittyisi heti kun suoritettu kurssi lisätään tietokantaan
* Merkkimäärärajoitukset tekstikentille
* Kursseja, joilla on jokin kentistä tyhjä, ei voi lisätä
* Samoja kursseja ei voi lisätä useampaa kuin yhden
* Opintopistemäärän voi korjata, jos se on väärin
* Tavoite opintopistemäärä voisi näkyä, ja se kuinka paljon käyttäjä on siitä suorittanut (esim. 50/180 op.)

