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

Käyttäjä voi antaa lemmikille haluamansa nimen ja tallentaa sen painamalla napista 'Save name!'. Nimen täytyy olla 1-10 merkkiä pitkä, se ei saa sisältää merkkiä ';' ja se täytyy muistaa tallentaa, muuten peli antaa käyttäjälle huomautuksen eikä päästä siirtymään eteenpäin. Onnistuneen nimentallennuksen jälkeen itse peli käynnistetään painamalla napista 'Start game'.

## Pelinäkymä

![MainGameScene](https://user-images.githubusercontent.com/73843204/102375304-a9359b00-3fc2-11eb-8d5b-d039d08dfd5a.png)

Ikkunassa pomppii pelin tähti, eli pelaajan ikioma virtuaalinen lemmikki (ulkomuodosta päätellen se ei ole kotoisin tältä planeetalta). Otuksella on kolme eri kehitysvaihetta, ja ensimmäisen kolmen päivän ajan se on ns. pentu-vaiheessa. Tällöin se tarvitsee enemmän huolehtimista kuin vanhempana. Neljän päivän ikäisenä siitä tulee nuori, ja kahdeksantena päivänä aikuinen, jolloin se tarvitsee enää hyvin vähän huomiota.

### Mittarit

Ikkunan vasemmassa reunassa näkyvät mittarit ilmaisevat lemmikin tilaa. Niistä näkee sen energian, onnellisuuden, terveyden ja hygienian tason. Mittarit tippuvat melko hitaasti, mutta hupenevat silti jatkuvasti, myös siltä ajalta kun ohjelma ei ole päällä.

Eri tekijät vaikuttavat nopeuteen, jolla mittarit tippuvat. Energia tippuu jatkuvasti tasaisella vauhdilla. Onnellisuus laskee nopeammin kuin energia, hygienia puolestaan hitaammin. Terveys alkaa heiketä vain, jos energia tippuu nollaan, jos lemmikki sairastuu tai jos sen hygienia tippuu alle puoleen.

Lemmikin kehitysaste vaikuttaa siihen, kuinka nopeasti mittarit hupenevat. Ensimmäisessä vaiheessa energiamittarin pitäisi huveta nollaan noin kolmessa tunnissa. Aikuisena se hupenee huomattavasti hitaammin, ja otus selviää pidempään ilman huolenpitoa.

Jos sekä energia että terveys tippuvat nollaan, lemmikki kuolee, ja peli loppuu.

### Lemmikistä huolehtiminen

Ikkunan alalaidassa olevista napeista painamalla lemmikkiä voi ruokkia, sen kanssa voi leikkiä, sen voi hoitaa terveeksi ja sen jäljet voi siivota.

Jos terveys tippuu alle puoleen, lemmikki saattaa sairastua, jolloin ikkunan oikeaan yläkulmaan ilmaantuu musta kallo. Tällöin terveys alkaa tippua huomattavasti nopeammin. Lemmikki toipuu sairastumisesta vasta kun terveysmittari on takaisin maksimilukemissa.

Lemmikki saattaa milloin tahansa jättää jälkeensä jätöksiä, jotka saa siivottua pois painamalla 'Clean' nappia. Siivoamattomat jätökset saavat hygieniamittarin laskemaan nopeammin.

### Minipeli

Painamalla nappia 'Play' käynnistyy lemmikin kanssa pelattava minipeli, jossa tarkoituksena on arvata, ajatteleeko lemmikki suurempaa vai pienempää lukua kuin esillä oleva numero.

![MiniGameScene](https://user-images.githubusercontent.com/73843204/101354436-7807ed00-3895-11eb-8f92-c23c836de054.png)

Napeista painamalla voi arvata joko suurempi tai pienempi. Painikkeesta 'Next' siirrytään seuraavaan kierrokseen, ja peli arpoo uuden luvun. Pelissä pelataan viisi kierrosta, ja pelaaja saa oikein arvatessaan yhden pisteen. Lopussa pelistä saadut pisteet määrittävät sen, kuinka paljon onnellisuusmittari nousee - nolla pistettä ei siis nosta onnellisuutta ollenkaan!

Painikkeesta 'Back to Game' pääsee milloin tahansa takaisin pääpelinäkymään, mutta ansaitut pisteet eivät tällöin kasvata onnellisuutta.

## Game Over

Pelin jujuna on siis pitää lemmikki elossa ja tyytyväisenä. Huonosti hoidettu lemmikki kuolee, ja peli loppuu:

![GameOverScene](https://user-images.githubusercontent.com/73843204/101355540-fdd86800-3896-11eb-9c19-305f4958d17e.png)

'Restart' painikkeesta siirrytään aloitusnäkymään, jossa pelin voi aloittaa uudestaan.

Ehkä pelaaja voi haastaa itsensä pitämään lemmikin hengissä mahdollisimman pitkään.

## VINKKI!

Peli ei ole kovin aktiivinen, eikä ajan kulkua tällä hetkellä pysty nopeuttamaan, mutta pelissä voi "huijata" hieman muokkaamalla tallennustiedostoa 'saveFile.txt'. Lemmikin tiedot tallennetaan muodossa

```
LemmikinNimi;yyyy-mm-dd;unixAika;50.0;50.0;50.0;50.0
```

Tiedostoa muokkaamalla voi esimerkiksi vaihtaa lemmikin nimen tai sen syntymäpäivän, kunhan sen esitysmuoto on oikea. Tallennettuna on myös sovelluksen edellisen sulkeutumisen ajankohta unix-ajassa ilmaistuna. Sitä seuraavat luvut ilmaisevat järjestyksessä lemmikin energiaa, onnellisuutta, terveyttä ja hygieniaa.
