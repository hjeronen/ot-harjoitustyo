# Käyttöohje

## Ohjelman käynnistäminen

Ohjelman voi käynnistää jar-tiedostosta komennolla

```
java -jar tamagotchi.jar
```

tai sen voi ajaa komentoriviltä komennolla

```
mvn compile exec:java -Dexec.mainClass=tamagotchi.Main
```

## Aloitusnäkymä

Jos peli käynnistyy ensimmäisen kerran, tai aiemmin luotua tallennustiedostoa ei löydy, ohjelma avaa ensimmäisenä aloitusruudun:

![Aloitusruutu](https://user-images.githubusercontent.com/73843204/101353556-1430f480-3894-11eb-9485-cad2737127cb.png)

Käyttäjä voi antaa lemmikille haluamansa nimen ja tallentaa sen painamalla napista 'Save name!'. Jos nimeä ei tallenna tai tallennettu syöte on tyhjä (eli null), ohjelma antaa lemmikille oletusnimen "Zorblax". Itse peli käynnistetään painamalla napista 'Start game'.
