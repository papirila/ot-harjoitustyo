# Harjoitustyö

Harjoitustyön aiheena on **Studytracker**. Ohjelman avulla opiskelija voi pitää kirjaa omista opinnoistaan.

## Dokumentaatio

[Vaatimusmaarittely](https://github.com/papirila/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Tyoaikakirjanpito](https://github.com/papirila/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[Arkkitehtuuri](https://github.com/papirila/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Komentorivitoiminnot

# Testaus

Ohjelman testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella tiedostosta _target/site/jacoco/index.html_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella tiedostosta _target/site/apidocs/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _StudyTracker-1.0-SNAPSHOT.jar_


### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/papirila/ot-harjoitustyo/blob/master/Studytracker/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät tiedostosta _target/site/checkstyle.html_
