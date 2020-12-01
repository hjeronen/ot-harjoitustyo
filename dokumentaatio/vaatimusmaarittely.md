# Vaatimusmäärittely

## Sovelluksen tarkoitus

Tamagotchi on lemmikinkasvatuspeli, jossa käyttäjä huolehtii virtuaalisesta lemmikistä. Lemmikkiä tulee 
ruokkia säännöllisesti, sen kanssa täytyy leikkiä ja sen jäljet siivota, ja sen sairastuessa se tulee hoitaa 
terveeksi. Huonosti hoidettu lemmikki kuolee tai karkaa pois (game over).

## Käyttäjät

Sovelluksessa on ainoastaan yksi käyttäjä.

## Toiminnallisuudet
* Anna lemmikille nimi // TEHTY
* Ruoki lemmikkiä // TEHTY
* Leiki lemmikin kanssa jonkinlaisella yksinkertaisella minipelillä
* Siivoa lemmikin jätökset // TEHTY
* Lääkitse sairasta lemmikkiä // TEHTY
* Lemmikin tila ilmenee mittareista: energia, hupi, terveys //TEHTY
	* Mittarit hupenevat ajan kuluessa itsekseen, mutta myös pelaajan toimien seurauksena, esim. leikkiminen
	kuluttaa energiaa, ja likainen elinympäristö heikentää terveyttä
* Ajan kulkua laskettaessa otetaan huomioon myös aika, joka kuluu kun ohjelma ei ole käynnissä - lemmikistä
on siis pidettävä säännöllisesti huolta // TEHTY
	* ohjelman testausta varten olisi hyvä olla myös jonkinlainen override ominaisuus, jolla ajan kulkua voi nopeuttaa
	(esim. pelin sisällä avattava command line, jonne voi kirjoittaa ns. huijauskoodeja) // Lemmikki tallennetaan saveFile.txt -tiedostoon, jossa sen stattien arvoa voi muuttaa
* Lemmikki ikääntyy, ja käy läpi kolme kehitysvaihetta: pentu, nuori ja aikuinen
	* periaatteessa otus voi ikääntyä loputtomiin, ts. niin kauan kuin siitä pidetään huolta
* Peli loppuu, kun lemmikki kuolee tai karkaa pois, ts. kun jokin tai kaikki mittarit ovat nollilla liian kauan // OSITTAIN TEHTY
	* jos energia mittari putoaa nollaan otus kuolee nälkään, tai jos terveys putoaa liian matalalle se kuolee 
	sairauteen - jos otuksen kanssa ei koskaan leiki se karkaa pois
* Ohjelma pitää galluppia lemmikeistä, joita käyttäjällä on ollut - tietoihin listataan lemmikin nimi ja ikä (ehkä myös mitä sille tapahtui)

## Lisätoiminnallisuuksia
* Alkuperäiseen tamagotchiin kuului myös discipline-mittari - jos jää aikaa, voisi implementoida jotain vastaavaa
(myös nälkää ja energiaa mitattiin erikseen, tässä ne on toistaiseksi niputettu samaan mittariin 'energia')
* Jatkossa voisi kehitellä myös erilaisia leikkejä
* Valuutta-systeemi: lemmikin kanssa leikkimällä tienaa jonkinlaista valuuttaa, jolla voi ostaa parempaa ruokaa, leluja 
tai itsepuhdistuvan hiekkalaatikon tms. ominaisuuksia, jotka helpottavat siitä huolehtimisesta
* Pokemon battle -tyyppinen pvp-toiminnallisuus - vaatisi myös jonkinlaisen käyttäjätunnuksen ja tilin sekä internetyhteyden
