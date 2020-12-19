# Käyttöohje

## Ohjelman asentaminen

Lataa tiedosto [tamagotchi.jar](https://github.com/hjeronen/ot-harjoitustyo/releases/tag/loppupalautus).

Ohjelman pitäisi osata luoda itse tarvitsemansa tallennustiedostot, joten se ei edellytä käyttäjältä mitään toimenpiteitä. Mikäli näin ei ole, ohjelma vaatii tallennusta varten juurikansioon tiedostot "saveFile.txt" ja "petCemetery.db".

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

![Aloitusruutu](https://user-images.githubusercontent.com/73843204/102689593-9360e980-41ff-11eb-86d2-d6736a75d204.png)

Käyttäjä voi antaa lemmikille haluamansa nimen kirjoittamalla sen syötekenttään. Nimen täytyy olla 1-10 merkkiä pitkä ja se ei saa sisältää merkkiä ';', muuten peli antaa käyttäjälle virheilmoituksen eikä päästä siirtymään eteenpäin. Jos nimi täyttää vaatimukset, itse peli käynnistetään painamalla napista 'Start Game'.

## Pelinäkymä

![MainGameScene](https://user-images.githubusercontent.com/73843204/102375304-a9359b00-3fc2-11eb-8d5b-d039d08dfd5a.png)

Ikkunassa pomppii pelin tähti, eli pelaajan ikioma virtuaalinen lemmikki (ulkomuodosta päätellen se ei ole kotoisin tältä planeetalta). Otuksella on kolme eri kehitysvaihetta, ja ensimmäisen kolmen päivän ajan se on ns. pentu-vaiheessa. Tällöin se tarvitsee enemmän huolehtimista kuin vanhempana. Neljän päivän ikäisenä siitä tulee nuori, ja kahdeksantena päivänä aikuinen, jolloin se tarvitsee vähemmän huomiota.

### Mittarit

Ikkunan vasemmassa reunassa näkyvät mittarit ilmaisevat lemmikin tilaa. Niistä näkee sen energian, onnellisuuden, terveyden ja hygienian tason. Mittarit tippuvat melko hitaasti, mutta hupenevat silti jatkuvasti, myös siltä ajalta kun ohjelma ei ole päällä.

Eri tekijät vaikuttavat nopeuteen, jolla mittarit tippuvat. Energia tippuu hitaasti silloin, kun onnellisuus on korkealla - jos onnellisuus tippuu alle 75:n, energia alkaa kulua nopeammin, ja vielä nopeammin jos onnellisuus putoaa alle puolivälin. Onnellisuus laskee tasaisella vauhdilla, hygienia puolestaan hitaammin. Terveys alkaa heiketä vain, jos energia tippuu nollaan, jos lemmikki sairastuu tai jos sen hygienia tippuu alle puoleen.

Lemmikin kehitysaste vaikuttaa siihen, kuinka nopeasti mittarit hupenevat. Ensimmäisessä kehitysvaiheessa lemmikki kuolee alle vuorokaudessa ilman hoitoa. Toisessa vaiheessa mittarit hupenevat nollaan alle kahdessa vuorokaudessa, aikuisena kolmessa.

Jos sekä energia että terveys tippuvat nollaan, lemmikki kuolee, ja peli loppuu.

### Lemmikistä huolehtiminen

Ikkunan alalaidassa olevista napeista painamalla lemmikkiä voi ruokkia, sen kanssa voi leikkiä, sen voi hoitaa terveeksi ja sen jäljet voi siivota.

Jos terveys tippuu alle puoleen, lemmikki saattaa sairastua, jolloin ikkunan oikeaan yläkulmaan ilmaantuu musta kallo. Tällöin terveys alkaa tippua huomattavasti nopeammin. Lemmikki toipuu sairastumisesta vasta kun terveysmittari on takaisin maksimilukemissa.

Lemmikki saattaa milloin tahansa jättää jälkeensä jätöksiä, jotka saa siivottua pois painamalla 'Clean' nappia. Siivoamattomat jätökset saavat hygieniamittarin laskemaan nopeammin.

### Minipeli

Painamalla nappia 'Play' käynnistyy lemmikin kanssa pelattava minipeli, jossa tarkoituksena on arvata, ajatteleeko lemmikki suurempaa vai pienempää lukua kuin esillä oleva numero.

![MiniGameScene](https://user-images.githubusercontent.com/73843204/101354436-7807ed00-3895-11eb-8f92-c23c836de054.png)

Napeista painamalla voi arvata joko suurempi tai pienempi. Painikkeesta 'Next' siirrytään seuraavaan kierrokseen, ja peli arpoo uuden luvun. Pelissä pelataan viisi kierrosta, ja pelaaja saa oikein arvatessaan yhden pisteen. Lopussa pelistä saadut pisteet määrittävät sen, kuinka paljon onnellisuusmittari nousee - nolla pistettä ei siis nosta onnellisuutta ollenkaan!

Painikkeesta 'Back to Game' pääsee milloin tahansa takaisin pääpelinäkymään, mutta jos peli on kesken, siihen mennessä ansaitut pisteet eivät tällöin kasvata onnellisuutta.

## Game Over

Pelin jujuna on siis pitää lemmikki elossa ja tyytyväisenä. Huonosti hoidettu lemmikki kuolee, ja peli loppuu:

![GameOverScene](https://user-images.githubusercontent.com/73843204/102385705-77c2cc80-3fce-11eb-95c6-cc669403d182.png)

'Restart' painikkeesta siirrytään aloitusnäkymään, jossa pelin voi aloittaa uudestaan. Kuolleet lemmikit siirtyvät lemmikkien hautausmaalle, jota pääsee katsomaan pääpelinäkymän painikkeesta 'View Cemetery'. Tässä näkymässä listataan järjestyksessä kaikki käyttäjän aiemmat lemmikit:

![PetCemetery](https://user-images.githubusercontent.com/73843204/102378533-36c6ba00-3fc6-11eb-8bf7-7ddeec42fb63.png)

Taulukosta näkee lemmikin nimen ja iän. Kuolleen lemmikin ikä määrittyy sen perusteella, kuinka vanha se oli ohjelman ollessa viimeksi päällä, eli se ei laske aikaa jonka lemmikki on ollut unohdettuna.

Ehkä pelaaja voi haastaa itsensä pitämään lemmikin hengissä mahdollisimman pitkään.

## Pelin tallennus

Peliä ei tarvitse erikseen tallentaa, vaan ohjelma tekee tallennuksen itse pelaajan sulkiessa sen ikkunan x-painikkeesta.

### VINKKI!

Peli ei ole kovin aktiivinen, eikä ajan kulkua tällä hetkellä pysty nopeuttamaan, mutta pelissä voi "huijata" hieman muokkaamalla tallennustiedostoa 'saveFile.txt'. Lemmikin tiedot tallennetaan muodossa

```
LemmikinNimi;yyyy-mm-dd;unixAika;50.00;50.00;50.00;50.00;true;false
```

Tiedostoa muokkaamalla voi esimerkiksi vaihtaa lemmikin nimen tai sen syntymäpäivän, kunhan sen esitysmuoto on oikea (kts. aiempana myös nimeämiseen liittyvät säännöt). Tallennettuna on myös sovelluksen edellisen sulkeutumisen ajankohta unix-ajassa ilmaistuna. Sitä seuraavat luvut ilmaisevat järjestyksessä lemmikin energiaa, onnellisuutta, terveyttä ja hygieniaa. Viimeiset kaksi totuusarvoa kertovat tarvitseeko lemmikki pesua ja onko se sairas.

HUOM. Jos tiedot tallennetaan virheellisessä muodossa, ohjelma luo kokonaan uuden tallenteen ja aloittaa pelin alusta!
