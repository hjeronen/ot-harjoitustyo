# Testausdokumentti

Ohjelmassa testattuja luokkia ovat tallennukseen käytetyt luokat pakkauksessa tamagotchi.dao, Pet- ja Stat-luokat pakkauksessa tamagotchi.domain, sekä sovelluslogiikasta vastaavat luokat pakkauksessa tamagotchi.logic. Näitä luokkia on testattu yksikkö- ja integraatiotesteillä - luokkien yhteistoimintaa on testattu erityisesti luokan PetCare testeissä, sillä se on pääasiallisesti vastuussa muiden luokkien yhteistoiminnasta. Lisäksi ohjelman toimivuutta ja käyttöliittymää on testattu manuaalisesti sen suorituksen aikana.

## Yksikkö- ja integraatiotestaus

### Pet- ja Stat-luokat

Testipakkauksissa pakkaus tamagotchi.domain sisältää luokkien Pet ja Stat testiluokat. StatTest-luokassa testataan, että Stat-olioiden sisältämä arvo kasvaa tai vähenee oikein, ja ettei se kasva yli maksimiarvon tai vähene alle minimiarvon.

PetTest-luokassa on testattu mm. että nimen asetus Petille toimii oikein, että kontsruktori asettaa oletusarvoisen nimen oikein, että Petille voi asettaa uuden syntymäpäivän, että Petin ikä lasketaan oikein ja että Petin kehitysaste asetetaan oikein.

Stat- ja Pet-luokat sisältävät lähinnä settereitä ja gettereitä, joiden toiminta ei sinänsä ole kovin mielenkiintoista, mutta joita hyödynnetään aktiivisesti toisaalla ohjelmakoodissa, ja siksi niidenkin toimivuutta on tarkasteltu.

### Sovelluslogiikka

Ohjelman sovelluslogiikasta vastaavien luokkien testaus löytyy testipakkauksesta tamagotchi.logic.

Pääasiassa pelilogiikasta huolehtiva luokka PetCare vastaa Petistä huolehtimisesta ja sen tilan valvomisesta. PetCare-luokassa yhdistetään luokkien Pet, Stat ja StatManager metodeja, kuin myös käytetään tallennusluokkia PetDao ja PetCemetery. Luokan testeissä tarkastellaan näiden yhteistoimintaa ja sovelluslogiikan toimivuutta kokonaisuutena.

Esimerkiksi PetCareTest-luokan testeissä tarkastetaan, että luokan omat metodit kasvattavat Petin stattien arvoa oikein tai muuttavat sen muita oliomuuttujia asianmukaisesti. PetCare-luokka on myös vastuussa sen valvomisesta, onko Pet sairas tai tarvitseeko se pesun - testeissä tarkastetaan, että näiden muuttujien arvo lasketaan ja asetetaan oikein Petille.

PetCare on myös vastuussa StatManagerin hyödyntämisestä Petin stattien alenemisen laskemisessa, ja se tarkastaa tämän perusteella, onko Pet vielä hengissä. Testeissä on tarkastettu, että stattien arvo lasketaan oikein ja että kuollut Pet tallennetaan PetCemeterylle.

PetCare-luokan vaatimina tallennusluokkina (PetDao ja PetCemeteryDao) käytetään valekomponentteja FakePetDao ja FakePetCemetery, jotka eivät tallenna Petin tietoja pysyväismuistiin. Tallennuksen testaus on suoritettu omassa osiossaan (kts. alempana).

StatManager-luokka vastaa Petin stattien alenemisen laskemisesta. StatManagerTest-luokassa testataan vielä tarkemmin, että StatManagerin metodit laskevat ja asettavat Petin sisältämien Stat-olioiden arvot oikein, vaikka tätä on jo osittain tarkasteltu PetCare-luokan testien yhteydessä.

Minipelin sovelluslogiikkaa testaavassa MiniGameTest-luokassa suoritetaan yksikkö- ja integraatiotestejä minipelin metodeille ja niiden yhteistoiminnalle. Se ei vuorovaikuta muiden luokkien kanssa, vaikkakin minipelistä saatavat pisteet annetaan PetCare-luokan play()-metodille parametrina. Play() metodia on kuitenkin testattu PetCareTest-luokassa ja minipelin pisteidenlaskua MiniGameTest-luokassa.

Pisteiden laskun suhteen testeissä tarkastetaan, että oikeat vastaukset kasvattavat pistemäärää ja väärät eivät. Samoin tarkastetaan, että peli arpoo uudet numerot, että pelikierroksia kasvatetaan oikein ja ettei samalla kierroksella voi antaa enempää kuin yhden vastauksen. Testeillä tarkastetaan myös, että peli resetoituu oikein, eli pelikierroksien ja pisteiden arvoiksi asetetaan nolla.

### DAO-luokan testaus

FilePetDao-luokka on vastuussa pelin tallentamisesta, ja sen toimintaa testataan FilePetDaoTest-luokassa. Testien alussa luodaan testitallennustiedosto "test_save.txt". Testeissä tarkastetaan, että Petin tiedot kirjoitetaan tiedostoon ja luetaan sieltä oikein.

SQLPetCemeteryDao-luokka vastaa menneiden Pettien tallennuksesta, ja sen toimintaa on testattu luokassa SQLPetCemeteryDaoTest. Testiluokassa luodaan tilapäinen database-tiedosto "test.db", jonne testin aikana luodaan SQL-taulukko ja testataan luokan SQL-metodien toimintaa.

Molempien tallennusluokkien testeissä käytetään TemporaryFolder-oliota, jonne tallennustiedosto luodaan testin ajon ajaksi ja jonka mukana se poistetaan testin jälkeen.

## Testauskattavuus

Käyttöliittymä poisluettuna testien rivikattavuus on 97%, ja haarautumakattavuus 98%.

![Testikattavuusraportti](https://user-images.githubusercontent.com/73843204/102619139-41a35b00-413c-11eb-9888-1af60f1f504d.png)

Sovelluslogiikan puolella tilanteissa, joissa on käytetty satunnaislukugeneraattoria, on testattu ainoastaan ääritapaukset.

FilePetDao-luokan suhteen ei ole testattu tilannetta, joissa yritys kirjoittaa tiedostoon päätyy virheeseen. Tilanteita, joissa tallennustiedosto puuttuu tai sen sisältö on virheellinen, on tosin testattu manuaalisesti.

SQLPetCemetery-luokan testeissä on sen sijaan otettu huomioon tilanne, jossa tallennukseen käytettävää SQL-taulukkoa ei löydy, jolloin tallennusmetodi palauttaa 'false' - sen sijaan ei ole testattu tilannetta, jossa taulukon luonti päätyy virheeseen.

Ainakin tallennustiedostojen suhteen virhetilanteet on pyritty käsittelemään koodissa try-catch rakenteiden avulla.

## Järjestelmätestaus

Järjestelmätestaus on tehty manuaalisesti. Ohjelma ei vaadi käyttäjältä toimenpiteitä asennuksen ja konfiguroinnin puolesta, sillä ohjelma osaa itse tehdä itselleen kaikki tarvitsemansa tallennustiedostot. Tätä on testattu suorittamalla ohjelma ilman mitään valmiiksi luotuja tiedostoja.

Pääpelin tallennusta on testattu myös valmiiksi tehdyllä, mutta puuttellisella tai tyhjällä tallennustiedostolla. Jos tallennustiedon tiedoissa on virhe tai jos tallennustiedosto on tyhjä, ohjelma luo kokonaan uuden tallenteen (eli aloittaa pelin alusta).

### Toiminnallisuudet

Määrittelydokumentissa ja käyttöohjeessa mainittuja toiminnallisuuksia (ja samalla käyttöliittymän toimintaa) on myös testattu manuaalisesti.

#### Nimen syöttö

Jos Petille annettua nimeä ei tallenneta, tai jos käyttäjä tallentaa tyhjän syötteen, ohjelma näyttää virhetekstin nimensyöttökentän vieressä. Myös Pet-luokan setName-metodissa suoritetaan tarkistus, ettei Petille pääse vahingossa tallentumaan virheellistä nimeä, vaan Petille annetaan oletusnimi - tämän testaus on suoritettu PetTest-luokassa. Petille annettava nimi ei saa olla tyhjä tai sisältää merkkiä ';', sillä se aiheuttaa virheen tallennustiedoston lukemisessa.

Nimenantoa on testattu manuaalisesti antamalle virhesyötteitä tekstikenttään. On myös testattu tilannetta, jossa käyttäjä lisää virheellisen nimen suoraan tallennustiedostoon, ja tällöin ohjelma luo kokonaan uuden tallenteen.

#### Pääpelin toiminnallisuudet

Pääpelinäkymässä (MainGameScene) on manuaalisesti testattu nappien 'Feed', 'Play', 'Heal' ja 'Clean' toimintaa. 'Feed' kasvattaa Petin Energy-progressbaria oikein, samoin 'Heal' ja 'Clean', mutta taso ei kasva yli maksimirajan (tämän tarkistukseen on hyödynnetty tilapäisiä tulostuskomentoja ohjelmakoodissa). Ruutuun ilmaantuu asianmukaisesti myös musta kallo silloin, kun Pet on sairas, ja vihreä jätös silloin kun se tarvitsee pesun - molemmat kuvakkeet myös katoavat oikein kun Health- ja Hygiene-progressbarit on kasvatettu maksimiin.

Ohjelma osaa myös asettaa Petille oikean Spriten sen iän/kehitysasteen mukaan. Tätä on testattu muuttamalla Petin syntymäpäivää sen tallennustiedostossa.

#### MiniGame

'Play'-napista painaminen vaihtaa oikein näkymään minipelin. Testauksessa on varmistettu, että minipeli toimii oikein. Napeista painamalla voi antaa vastauksen, mutta yhdellä kierroksella ei voi antaa useampaa vastausta. Peli myös näyttää verrattavan numeron ja vastauksen oikein, ja pelaajan antamaan vastaukseen reagoidaan oikein. Ansaitut pisteet nostattavat onnellisuutta oikein, ja napista painamalla pääsee takaisin pääpelinäkymään.

#### PetCemetery

Painamalla napista 'View Cemetery' käyttäjä pääsee näkemään aiemmat Petit, joita hänellä on ollut. Tietokannan tallennusta on testattu erikseen ysikkötesteillä, mutta käyttöliittymässä on testattu manuaalisesti, että taulukkoon kirjataan oikein tietokannan sisältö. Näin on myös testattu, että erikoismerkkejä sisältävät nimet kirjoitetaan ja luetaan oikein tietokannasta.

#### GameOver

Pelin päättymistä on testattu muuttamalla tallennustiedostossa Petin energy- ja health-stattien arvoksi nolla. Ohjelman käynnistyessä latautuu oikein GameOverScene, jonka napista pääsee takaisin aloitusnäkymään. On myös manuaalisesti testattu, että jos Pet kuolee ohjelman ollessa käynnissä (pääpelinäkymässä) ohjelma vaihtaa oikein näkymään GameOverScene.

## Sovellukseen jääneet laatuongelmat

Tallennustiedoston muokkaus ei ole kovin suotava tapa testata pelin toimintaa tai manipuloida Petin tietoja, sillä se on altis virhesyötteille, mutta ainakin tässä tilanteessa se oli yksinkertaisin keino. Siksi pääpelin tallennus suoritetaankin edelleen yksinkertaisella tekstitiedostolla. Periaatteessa olisi ollut parempi rakentaa pelin sisälle vastaava toiminnallisuus ja käyttää tallennukseen ennemmin tietokantaa, mutta käytettävissä ollut aika kului lähinnä muiden ongelmien ratkomiseen.
