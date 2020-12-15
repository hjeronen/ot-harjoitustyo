# Vaatimusmäärittely

## Sovelluksen tarkoitus

Tamagotchi on lemmikinkasvatuspeli, jossa käyttäjä huolehtii virtuaalisesta lemmikistä. Lemmikkiä tulee 
ruokkia säännöllisesti, sen kanssa täytyy leikkiä ja sen jäljet siivota, ja sen sairastuessa se tulee hoitaa 
terveeksi. Huonosti hoidettu lemmikki kuolee ja peli päättyy.

## Käyttäjät

Sovelluksessa on ainoastaan yksi käyttäjä.

## Toiminnallisuudet
* Lemmikille voi antaa haluamansa nimen
* Ruoki lemmikkiä
* Leiki lemmikin kanssa yksinkertaisella minipelillä (numeropeli)
* Siivoa lemmikin jätökset
* Lääkitse sairasta lemmikkiä
* Lemmikin tila ilmenee mittareista: energia, hupi, terveys
	* Mittarit hupenevat ajan kuluessa itsekseen
	* Toisten stattien tila vaikuttaa nopeuteen, jolla ne tippuvat, esim. alhainen hygienia heikentää terveyttä nopeasti
* Ajan kulkua laskettaessa otetaan huomioon myös aika, joka kuluu kun ohjelma ei ole käynnissä - lemmikistä
on siis pidettävä säännöllisesti huolta
	* ajan kulkua ei voi nopeuttaa, mutta ohjelmaa voi testata muokkaamalla tallennustiedostoa saveFile.txt
* Lemmikki ikääntyy, ja käy läpi kolme kehitysvaihetta: pentu, nuori ja aikuinen
	* periaatteessa otus voi ikääntyä loputtomiin, ts. niin kauan kuin siitä pidetään huolta
* Peli loppuu, kun lemmikki kuolee, ts. kun sen energian ja terveyden tila tippuvat nollaan
* Kuollut lemmikki siirtyy hautausmaalle - käyttäjä näkee taulukosta aiemmat lemmikit, joita hänellä on ollut, ja kuinka vanhoiksi ne elivät

## Lisätoiminnallisuuksia/Jatkokehitysideoita
* Nälkää ja energiaa voisi mitata erikseen
* Erilaisia ruokia, jotka nostavat energian tasoa enemmän tai vähemmän
* Jatkossa voisi kehitellä myös erilaisia leikkejä
* Erilaisia lemmikkejä, joista käyttäjä voi itse valita haluamansa
* Animaatioita
* InGame kauppa ja valuutta-systeemi: lemmikin kanssa leikkimällä tai sen jätöksiä keräämällä tienaa jonkinlaista valuuttaa, jolla voi ostaa parempaa ruokaa, leluja 
tai itsepuhdistuvan hiekkalaatikon tms. ominaisuuksia, jotka helpottavat siitä huolehtimisesta
* Pokemon battle -tyyppinen pvp-toiminnallisuus - vaatisi myös jonkinlaisen käyttäjätunnuksen ja tilin sekä internetyhteyden
