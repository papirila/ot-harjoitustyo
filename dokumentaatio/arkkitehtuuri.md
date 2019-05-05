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

## Sekvenssikaaviota

Ohjelman päätoiminnallisuuksia vastaavia sekvenssikaavioita.

### Kirjautuminen

Kun käyttäjä syöttää kirjautumisnäkymässä käyttäjätunnuksen ja salasanan, sekä painaa nappia login, etenee sovelluksen kontrolli seuraavasti
<img src="https://github.com/papirila/ot-harjoitustyo/blob/master/dokumentaatio/Logging%20in.png" width="600">

Kaaviossa sisäänkirjautuminen onnistuu. Käyttäjä (nimeltä tommi, salasanalla "testeri") painaa nappia login, jonka jälkeen Login-luokka kyselee UserDao-luokalta, löytyykö käyttäjää tietokannasta. UserDao palauttaa käyttäjän Login-luokalle, jonka jälkeen Login-luokka asettaa UI-luokalle tiedon käyttäjän id:stä. UI-luokka kyselee käyttäjän id:n tiedon User-luokalta, joka palauttaa sen UI-luokalle. Lopuksi näkymä siirtyy UI-luokkaan.

### Uuden käyttäjän luominen

Kun käyttäjä syöttää kirjautumisnäkymässä käyttäjätunnuksen ja salasanan, sekä painaa nappia newUser, etenee sovelluksen kontrolli seuraavasti
<img src="https://github.com/papirila/ot-harjoitustyo/blob/master/dokumentaatio/Creating%20a%20new%20user.png" width="600">

Kaaviossa luodaan onnistuneesti uusi käyttäjä. Käyttäjä luo uuden käyttäjän (tommi2 salasanalla "testeri2" ja id:llä 1), jolloin Login-luokka kyselee UserDao-luokalta onko käyttäjää olemassa. UserDao palauttaa null, eli käyttäjää ei ole vielä, jolloin Login-luokka lähettää UserDao luokalle käskyn luoda käyttäjä. Tämän jälkeen tulee esiin hälytys, jonka mukaan uusi käyttäjä luotiin onnistuneesti.

### Uuden suoritetun kurssin luominen

Kun käyttäjä painaa UI-luokassa nappia Add suoritetut kurssit -listan alla, etenee sovelluksen kontrolli seuraavasti
<img src="https://github.com/papirila/ot-harjoitustyo/blob/master/dokumentaatio/Creating%20a%20new%20passed%20course.png" width="600">

Kaaviossa UI-luokka luo uuden course-olion, jonka jälkeen UI-luokka kyselee CourseDao-luokalta tietoa kurssilistan suurimmasta id:stä (7). CourseDao-luokka palauttaa UI-luokalle suurimman tämänhetkisen id:n, jonka jälkeen UI-luokka asettaa olion course id:ksi suurimman id:n arvon, johon on summattu luku 1 (8). Course-olio palauttaa UI-luokalle tiedon, että sen id on tällä hetkellä 8. UI-luokka lisää course-olion näkyvän listan dataan ja käskee CourseDao-luokkaa luomaan kurssin tietokantaan. Tämän jälkeen uusi kurssi näkyy suoritettujen kurssien listalla.

### Muut toiminnallisuudet

Samalla tavalla muissa toiminnallisuuksissa käyttöliittymän tapahtumakäsittelijä kutsuu sopivaa sovelluslogiikan metodia, jolloin sovelluslogiikka päivittää käyttäjien ja kurssien tilaa. Näkymä päivittyy viimeistään kun ohjelma käynnistetään uudestaan.

# Ohjelman heikkoudet

Kurssien opintopistemäärä ei päivity välittömästi, vaan päivittyy vasta kun ohjelma ajetaan uudestaan.
Testikenttien merkkimääriä ei ole rajoitettu mitenkään.
Samoja kursseja voi mahdollisesti lisätä listoille ja tietokantaan.
Kursseja luodessa on mahdollista jättää jokin tekstikentistä tyhjäksi, ohjelma olettaa että käyttäjä kirjaa kaikki tiedot kursseista.
Kurssin läpäisemistä ei voi perua.













