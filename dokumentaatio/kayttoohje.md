# Käyttöohje

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla java -jar studytracker.jar

## Kirjautuminen

Sovellus käynnistyy Login-näkymään (kirjautuminen)

<img src="https://github.com/papirila/ot-harjoitustyo/blob/master/dokumentaatio/pictures/login-n%C3%A4kym%C3%A4.PNG" width="400">

Kirjautuminen onnistuu kirjoittamalla vasemmanpuoliseen testikenttään käyttäjätunnuksen ja oikeanpuoliseen tekstikenttään 
salasanan, ja painamalla nappia "Log in". Huom! Sovellus asettaa käyttäjän id:ksi arvon 1 jos UI-luokka avataan ilman sisäänkirjautumista.

Jos kirjautuminen ei onnistu, näkyviin tulee error-hälytysikkuna.

## Uuden käyttäjän luominen

Kirjautumisnäkymästä voi luoda uuden käyttäjän kirjoittamalla vasemmanpuoliseen testikenttään käyttäjätunnuksen ja oikeanpuoliseen 
tekstikenttään salasanan, ja painamalla nappia "Create new user".

Jos käyttäjän luominen onnistuu, näytetään hälytysikkuna, joka kertoo onnistumisesta.

## Kurssinäkymä

Jos kirjautuminen onnistuu, näkymä siirtyy UI-luokan näkymään

<img src="https://github.com/papirila/ot-harjoitustyo/blob/master/dokumentaatio/pictures/ui-n%C3%A4kym%C3%A4.PNG" width="400">

Näkymä mahdollistaa kurssien listaamisen kahdelle eri listalle painamalla nappia "Add", sekä kurssien poistamisen toiselta (ilmoittaudutut 
kurssit) listalta kurssin id:llä, painamalla nappia "Delete with id" ja syöttämällä poistettavan kurssin id:n. 
Kurssin voi myös merkitä suoritetuksi listalta ilmoittaudutut kurssit painamalla nappia "Pass course with id and insert grade". Kenttään 
tulee siis kirjoittaa suoritettavan kurssin id, ja sen arvosana.


