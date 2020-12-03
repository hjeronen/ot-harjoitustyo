### Ohjelmistotekniikka 2020

# Harjoitustyö: Tamagotchi

Tamagotchi on lemmikinkasvatuspeli, jossa käyttäjä huolehtii virtuaalisesta lemmikistä. Lemmikkiä tulee ruokkia säännöllisesti, sen kanssa täytyy leikkiä ja sen jäljet siivota, ja sen sairastuessa se tulee hoitaa terveeksi. Huonosti hoidettu lemmikki kuolee tai karkaa pois (game over).

## Dokumentaatio

* [Vaatimusmäärittely](https://github.com/hjeronen/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)
* [Työaikakirjanpito](https://github.com/hjeronen/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)
* [Arkkitehtuurikuvaus](https://github.com/hjeronen/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Releaset

* [Viikko 5](https://github.com/hjeronen/ot-harjoitustyo/releases/tag/viikko5)

## Komentorivitoiminnot

Ohjelman voi suorittaa komentoriviltä komennolla
```
mvn compile exec:java -Dexec.mainClass=tamagotchi.Main
```

### Testaus

Testit suoritetaan juurihakemistossa 'Tamagotchi' komennolla
```
mvn test
```

Samoin testikattavuusraportin saa komennolla
```
mvn test jacoco:report
```

Raportti löytyy polusta target/site/jacoco/index.html.

### Suoritettavan jarin generointi

Jar luodaan komennolla
```
mvn package
```

joka luo target hakemistoon tiedoston Tamagotchi-1.0-SNAPSHOT.jar (vaatii päivitetyn javan suoritukseen).

### Checkstyle

Tiedoston [checkstyle.xml](https://github.com/hjeronen/ot-harjoitustyo/blob/master/Tamagotchi/checkstyle.xml) määritelmien mukainen tarkastus suoritetaan komennolla
```
mvn jxr:jxr checkstyle:checkstyle
```
