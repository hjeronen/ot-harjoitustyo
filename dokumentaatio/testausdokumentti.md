# Testausdokumentti

Ohjelmassa testattuja luokkia ovat tallennukseen käytetyt luokat pakkauksessa tamagotchi.dao, Pet- ja Stat-luokat pakkauksessa tamagotchi.domain, sekä sovelluislogiikasta vastaavat luokat pakkauksessa tamagotchi.logic.

Ohjelmaa on testattu sekä yksikkö- että integraatiotesteillä. Luokkaa Stat on testattu lähinnä yksikkötesteillä, sillä sen metodeissa ei tehdä mitään kovin monimutkaista eikä niissä käsitellä muita luokkia. Pet-luokkaa on testattu sekä yksikkö- että integraatiotesteillä, sillä sen metodeissa hyödynnetään paljon Stat-luokan metodikutsuja. Luokkia PetCare ja StatManager on testattu lähinnä integraatiotestein, sillä molempien luokkien metodit käsittelevät pääasiassa luokkia Pet ja Stat ja kutsuvat niiden metodeja. MiniGame luokka ei vuorovaikuta muiden luokkien kanssa mitenkään, sen metodeja ja niiden toimintaa on testattu sekä yksikkö- että integraatiotestein. Lisäksi ohjelman toimivuutta on testattu manuaalisesti sen suorituksen aikana.

## Yksikkö- ja integraatiotestaus

### Pet- ja Stat-luokat

Testipakkauksissa pakkaus tamagotchi.domain sisältää luokkien Pet ja Stat testiluokat. StatTest-luokassa testataan, että Stat-olioiden sisältämä arvo kasvaa tai vähenee oikein, ja ettei se kasva yli maksimiarvon tai vähene alle minimiarvon.

PetTest-luokassa on yksikkötesteillä testattu mm. että nimen asetus Petille toimii oikein, että kontsruktori asettaa oletusarvoisen nimen oikein, ja että Petille voi asettaa uuden syntymäpäivän.

Pet-luokassa stattien arvot asettavat metodit hyödyntävät Stat-luokan metodeja Stat-olioiden käsittelyyn. Näiden yhteistoimintaa on testattu myös PetTest-luokassa, esimerkiksi testimetodi setEnergySetsValueRight testaa että energy-nimiselle Statille voidaan asettaa oikein uusi arvo.

Pet-luokka sisältää lähinnä settereitä ja gettereitä, joiden toiminta ei sinänsä ole kovin mielenkiintoista, mutta joita hyödynnetään aktiivisesti toisaalla ohjelmakoodissa, ja siksi niidenkin toimivuutta on tarkasteltu.

### Sovelluslogiikka

Ohjelman sovelluslogiikasta vastaavien luokkien testaus löytyy testipakkauksesta tamagotchi.logic.

Pääasiassa sovelluslogiikasta huolehtiva luokka PetCare sisältää lähinnä Pet- ja Stat-luokkia hyödyntäviä metodeja, sillä sen toimintojen tarkoituksena on käsitellä Petin statteja. PetCareTest testaa luokan toimintaa integraatiotestein, joissa tarkastetaan, että metodit kasvattavat Petin stattien arvoa oikein tai muuttavat sen muita oliomuuttujia asianmukaisesti. PetCare-luokka myös tarkastaa jos Pet sairastuu tai jos se tarvitsee pesun - testeissä tarkastetaan, että näiden muuttujien arvo lasketaan ja asetetaan oikein Petille.

PetCare-luokan vaatimana PetDaona käytetään luokkaa FakePetDao, joka ei tallenna Petin tietoja mihinkään. Tallennuksen testaus on suoritettu omassa osiossaan (kts. alempana).

StatManager-luokka vastaa Petin stattien alenemisen laskemisesta. StatManagerTest-luokassa testataan integraatiotestein, että StatManagerin metodit laskevat ja asettavat Petin sisältämien Stat-olioiden arvot oikein.

Minipelin sovelluslogiikkaa testaavassa MiniGameTest-luokassa suoritetaan yksikkö- ja integraatiotestejä minipelin metodeille ja niiden yhteistoiminnalle. Se ei vuorovaikuta muiden luokkien kanssa, vaikkakin minipelistä saatavat pisteet annetaan kyllä PetCare-luokan play()-metodille parametrina, mutta play() metodia on testattu PetCareTest-luokassa ja minipelin pisteidenlaskua MiniGameTest-luokassa.

Pisteiden laskun suhteen testeissä tarkastetaan, että oikeat vastaukset kasvattavat pistemäärää ja väärät eivät. Samoin tarkastetaan, että peli arpoo uudet numerot, että pelikierroksia kasvatetaan oikein ja ettei samalla kierroksella voi antaa enempää kuin yhden vastauksen. Testeillä tarkastetaan myös, että peli resetoituu oikein, eli pelikierroksien ja pisteiden arvoksi asetetaan nolla.

### DAO-luokan testaus

FilePetDao-luokka on vastuussa pelin tallentamisesta, ja sen toimintaa testataan FilePetDaoTest-luokassa. Testien alussa luodaan testitallennustiedosto "test_save.txt", joka poistetaan testien lopussa. Testeissä testataan, että Petin tiedot kirjoitetaan tiedostoon ja luetaan sieltä oikein.

## Testauskattavuus

Käyttöliittymä poisluettuna testien rivikattavuus on 96%, ja haarautumakattavuus 98%.

![Testikattavuusraportti](https://user-images.githubusercontent.com/73843204/101990733-792b8680-3ca8-11eb-8cc1-435aa2b756fe.png)

Sovelluslogiikan puolella tilanteissa, joissa on käytetty satunnaislukugeneraattoria, on testattu ainoastaan ääritapaukset. Tallennustiedostojen suhteen ei ole testattu tilanteita, joissa yritys lukea tai kirjoittaa tiedostoon päätyy virheeseen. Tilanteita, joissa tallennustiedosto puuttuu tai sen sisältö on virheellinen, on tosin testattu manuaalisesti.

## Järjestelmätestaus

Järjestelmätestaus on tehty manuaalisesti. Ohjelma ei vaadi käyttäjältä toimenpiteitä asennuksen ja konfiguroinnin puolesta, sillä ohjelma osaa itse tehdä itselleen tallennustiedoston. Tätä on testattu suorittamalla ohjelma ilman oikein nimettyä tallennustiedostoa.

Ohjelmaa on testattu myös valmiiksi tehdyllä, mutta puuttellisella tai tyhjällä tallennustiedostolla. Jos tallennustiedon tiedoissa on virhe, ohjelma luo kokonaan uuden tallenteen (eli aloittaa pelin alusta). Samoin jos tallennustiedosto on tyhjä, ohjelma aloittaa uuden pelin alusta.

### Toiminnallisuudet

Määrittelydokumentissa ja käyttöohjeessa mainittuja toiminnallisuuksia (ja samalla käyttöliittymän toimintaa) on myös testattu manuaalisesti.

#### Nimen syöttö

Jos Petille annettua nimeä ei tallenneta, tai jos käyttäjä tallentaa tyhjän syötteen, Petille annetaan oletusnimi. Nimi voi sisältää mitä tahansa muita merkkejä, paitsi merkin ';', sillä se aiheuttaa virheen tallennustiedoston lukemisessa. Jos käyttäjän syöttämä nimi sisältää merkin ';', Pet saa jälleen oletusnimen.

Edellä mainittuja tilanteita on testattu manuaalisesti. On myös testattu tilannetta, jossa käyttäjä lisää virheellisen nimen suoraan tallennustiedostoon, ja tällöin ohjelma luo kokonaan uuden tallenteen - tällaisessa tilanteessa ohjelma ei oikein voi muutoinkaan varautua virhesyötteisiin.

#### Pääpelin toiminnallisuudet

Pääpelinäkymässä (MainGameScene) on manuaalisesti testattu nappien 'Feed', 'Play', 'Heal' ja 'Clean' toimintaa. 'Feed' kasvattaa Petin energia tasoa oikein, samoin 'Heal' ja 'Clean', mutta taso ei kasva yli maksimirajan (tämän tarkistukseen on hyödynnetty tilapäisiä tulostuskomentoja ohjelmakoodissa). Ruutuun ilmaantuu asianmukaisesti myös musta kallo silloin, kun Pet on sairas, ja vihreä papana silloin kun se tarvitsee pesun - molemmat kuvakkeet myös katoavat oikein kun Health- ja Hygiene-arvot on kasvatettu maksimiin.

Ohjelma osaa myös asettaa Petille oikean Spriten sen iän/kehitysasteen mukaan. Tätä on testattu muuttamalla Petin syntymäpäivää sen tallennustiedostossa.

#### MiniGame

'Play'-napista painaminen vaihtaa oikein näkymään minipelin. Testauksessa on varmistettu, että minipeli toimii oikein. Napeista painamalla voi antaa vastauksen, mutta yhdellä kierroksella ei voi antaa useampaa vastausta. Peli myös näyttää verrattavan numeron ja vastauksen oikein, ja pelaajan antamaan vastaukseen reagoidaan oikein. Ansaitut pisteet nostattavat onnellisuutta oikein, ja napista painamalla pääsee takaisin pääpelinäkymään.

#### GameOver

Pelin päättymistä on testattu muuttamalla tallennustiedostossa Petin energy- ja health-stattien arvoksi nolla. Ohjelman käynnistyessä latautuu oikein GameOverScene, jonka napista pääsee takaisin aloitusnäkymään.

## Sovellukseen jääneet laatuongelmat

Nimen syötön yhteydessä ohjelma ei huomauta käyttäjälle virheellisestä syötteestä, vaan käsittelee sen itse asettamalla Petille oletusnimen.
