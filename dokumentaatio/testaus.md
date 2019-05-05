# Testausdokumentti

Ohjelmaa on testattu manuaalisesti ja automatisoiduin yksikkötestein JUnitilla.

## Yksikkötestaus

### Sovelluslogiikka

Automatisoituja testejä ovat studytracker.domain luokkia testaavat UserTest ja CourseTest.

### Dao-luokat

Testit käyttävät datan pysyväistallennukseen tietokantaa test.db.

### Testauskattavuus

Käyttöliittymäpakkausta lukuunottamatta sovelluksen testauksen rivikattavuus on 89% ja haarautumakattavuus 73%

<img src="https://github.com/papirila/ot-harjoitustyo/blob/master/dokumentaatio/pictures/testikattavuus.PNG" width="800">

Testaamatta jäivät mm tilanteet, joissa tekstikenttien merkkimäärät ylittivät tietokannassa määritellyt merkkimäärät kullekin attribuutille.

## Järjestelmätestaus

Järjestelmätestaus suoritettiin manuaalisesti.

Ohjelmaa on testattu vain Windows-käyttöjärjestelmällä.

## Sovellukseen jääneet laatuongelmat

Ohjelma ei aina anna järkevää virheilmoitusta.
