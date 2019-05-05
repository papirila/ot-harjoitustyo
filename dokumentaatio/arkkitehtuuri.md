# Arkkitehtuurikuvaus 

## Ohjelman rakenne

Ohjelman pakkausrakenne on seuraava 
<img src="https://github.com/papirila/ot-harjoitustyo/blob/master/dokumentaatio/Pakkausrakenne.jpeg" width="600">

Pakkaus studytracker.ui sisältää JavaFX:llä toteutetun käyttöliittymän ja studytracker.domain sovelluslogiikan. Studytracker.dao vastaa tietojen pysyväistallennuksesta.

## Käyttöliittymä

Käyttöliittymä sisältää kaksi eri näkymää (toteutettu Scene-olioina):
* sisäänkirjautuminen
* opintolistojen näkymä

Ensimmäinen näkymä on login-näkymä, josta on mahdollista siirtyä ui-näkymään onnistuneen sisäänkirjautumisen jälkeen. Login-näkymä suljetaan siirryttäessä ui-näkymään, jolloin loginin stage suljetaan ja ui:n stage "aloitetaan". Jos käyttäjänimi tai salasana on väärin, tai käyttäjää ei ole olemassa, ohjelma näyttää error-hälytyksen.

Login-näkymässä on myös mahdollista luoda uusi käyttäjä, jolloin ohjelma tarkastaa, onko käyttäjä jo olemassa. Käyttäjä luodaan, jos sitä ei löydy tietokannasta, muulloin ohjelma näyttää error-hälytyksen.

Kun käyttäjä kirjautuu ohjelmaan, käyttäjä voi lisätä kursseja kahdelle eri listalle. Toisesta listasta kursseja voidaan myös poistaa tai siirtää ensimmäiselle listalle. Listat on toteutettu TableView-olioina. Ensimmäisellä listalla näkyvät käyttäjän suoritetut kurssit, toisella ilmoittaudutut kurssit. Ilmoittauduttuja kursseja voi poistaa kurssin listalla näkyvän id:n avulla. Kursseja voi siirtää listalta suoritettujen kurssien listalle syöttämällä tekstikenttään tiedot muodossa "kurssin_id kurssin_uusi_arvosana".

Käyttöliittymä on erillään sovelluslogiikasta, jolloin se ainoastaan kutsuu tarvittaessa sovelluslogiikan luokkien metodeja.

## Sovelluslogiikka

Ohjelman domain luokassa olevat luokat toimivat vastaavan relaatiokaavion mukaisesti
<img src="https://github.com/papirila/ot-harjoitustyo/blob/master/dokumentaatio/Sovelluslogiikka.jpeg" width="600">

Luokat kuvaavat käyttäjää ja käyttäjällä olevia kursseja.

Ohjelman osien suhdetta kuvaava pakkauskaavio:
<img src="https://github.com/papirila/ot-harjoitustyo/blob/master/dokumentaatio/pakkauskaavio.jpeg" width="600">

## Tietojen pysyväistallennus

Tietojen tallennuksesta vastaavat pakkauksessa studytracker.dao sijaitsevat UserDao ja CourseDao, joiden kautta saadaan tieto luokista User ja Course. Dao-luokat ottavat yhteyden tietokantaan Database-luokan metodin avulla. UserDao ja CourseDao käyttävät rajapintaa Dao.

Sovelluslogiikan testeissä hyödynnetään tietokannassa jo valmiiksi olevaa tietoa. 

## Tietokanta

Ohjelma tallettaa tiedot studies-tietokantaan, jossa käyttäjät talletetaan muodossa 1, "matti", "123" (käyttäjän id, käyttäjän nimi, käyttäjän salasana). Kurssit tallennetaan muodossa 1, 3, "ohpe", 6, 5, false (kurssin id, kurssin käyttäjään viittaava id, kurssin nimi, kurssin opintopisteet, kurssin arvosana, onko kurssi suoritettu vai ei).
