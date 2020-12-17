# Arkkitehtuurikuvaus

## Rakenne

Pakkauskaaviossa on esitetty ohjelman pakkausrakenne:

![TamagotchiPakkauskaavio](https://user-images.githubusercontent.com/73843204/101286776-bdbcab00-37ec-11eb-94a0-7d496cb0a924.png)

Kaikki käyttöliittymän koostamiseen liittyvät luokat on sijoitettu pakkaukseen ui, pelilogiikka on pakkauksessa logic, pelissä hoidettavaa virtuaalilemmikkiä edustava luokka Pet ja siihen liittyvä luokka Stat ovat pakkauksessa domain ja pelin tallennuksesta vastaavat luokat pakkauksessa dao.

## Käyttöliittymä

Käyttöliittymä on toteutettu JavaFX:llä, ja kaikki sen koostamiseen käytety luokat löytyvät pakkauksesta ui. Pääasiallinen käyttöliittymän hallinnasta vastaava luokka on GUI, joka vastaa eri pelinäkymien käytöstä ja pelilogiikan yhdistämisestä niihin. Varsinaisia pelinäkymiä on neljä, NewGameScene, MainGameScene, MiniGameScene ja GameOverScene. Näiden lisäksi on myös näkymä PetCemeteryScene, jossa käyttäjä näkee listattuna aiemmat pelissä omistetut Petit. Jokainen näkymä on siis FXML-dokumentin määrittelemä Scene-olio, joka GUI:ssa ladataan ja asetetaan oliomuuttujaan stage käyttäjälle näytettäväksi. Jokaisella on oma controller-luokka, jossa ko. näkymän toiminta on määritelty. Nämä kontrolleriluokat on esitetty alempana luokka/pakkauskaaviossa.

Itse pelilogiikasta vastaa luokka PetCare, jonka tuntee vain luokka GUI. GUI:n kautta muissa näkymissä tapahtuvat toiminnot kutsuvat PetCaren metodeja, tai näkymille välitetään tietoja Petin tilasta. Ainoastaan näkymän MiniGameScene kontrolleriluokka tietää itse ja yksin käyttämänsä MiniGame-luokan, joka siis vastaa ohjelman sisäisen minipelin toimintalogiikasta.

Se, mikä pelinäkymä on kulloinkin esillä, riippuu pelitilanteesta. Aloitettaessa uutta peliä asetetaan näkyville ensin NewGameScene, jossa pelaaja voi valita Petille nimen. Tämän jälkeen tai jos ohjelma käynnistyessään löytää edellisen pelin tallennuksen asetetaan näkymään MainGameScene, jossa itse peli tapahtuu. Pelaaja näkee mittareista Petin eri stattien tilan, ja voi napeilla 'Feed', 'Heal' ja 'Clean' hoitaa Pettiä. 'Play' -napista painamalla vaihtuu näkymäksi MiniGameScene, jossa voi pelata minipeliä Petin kanssa. 'View Cemetery' -napista vaihtuu näkymään PetCemeteryScene, josta pääsee napilla takaisin pääpelinäkymään. Jos Pettiä ei ole pitkään aikaan hoitanut, se kuolee ja näkymäksi vaihtuu GameOverScene, josta pääsee takaisin uuden pelin aloitukseen.

### AnimationTimer

Koska käyttöliittymä on toteutettu JavaFX:llä, on ns. GameLoop toteutettu sen ominaisuudella AnimationTimer. Se luodaan GUI:n start()-metodin sisällä, ja sen handle()-metodissa päivitetään Petin tila joka 1500 millisekunti, eli 1.5 sekunnin välein. 10 sekunnin välein tarkastetaan myös sairastuuko Pet tai jättääkö se jätöksiä. 100 sekunnin välein tarkistetaan myös Petin iän määrittelemä kehitysaste.

### GameRenderer ja Sprite

GameRenderer-luokkaa käytetään piirtämään kuvitusta eri pelinäkymille. GameRenderer saa konstruktorissa tarvitsemansa Canvas-olion, jolle kuva piirretään. Kuvitusta tarvitsevat näkymät MiniGameScene ja GameOverScene käyttävät kumpikin omaa GameRenderer-oliotaan, joka on olemassa vain luokan sisällä, mutta MainGameScenen animaation tarvitsema GameRenderer on GUI-luokan sisällä.

GameRenderer sisältää oliomuuttujina tiedot kuvista, joita se tarvitsee kaikissa näkymissä, paitsi itse Pettiin liittyvän Spriten kuvia, jotka sisältyvät luokkaan Sprite. Oikean Spriten kuvan piirtäminen tapahtuukin Spriten omalla metodilla, jota kutsutaan GameRenderer-luokasta. Sprite myös tietää itse oman kuvakokonsa ja sijaintinsa, jonka GameRenderer laskee ja asettaa sille.

## Sovelluslogiikka

### Pet ja Stat

Pelin ideana on pitää hengissä virtuaalista lemmikkiä, jota edustaa luokka Pet. Pet käyttää luokan Stat ilmentymiä pitämään kirjaa omasta tilastaan, esim. Petin energian määrää ilmaisee Stat-olio, jolla on maksimi- ja minimiraja sekä arvo, jota voi kasvattaa tai vähentää. Pet ja Stat sijaitsevat pakkauksessa domain.

### PetCare
Pakkauksessa logic pääasiallinen pelilogiikasta vastaava luokka on PetCare, joka tuntee pelin kohteena olevan Petin. PetCare on vastuussa Petin hoitoon liittyvistä toiminnoista (feed, play, heal ja clean) sekä Petin tilan valvomisesta (esim. luokan metodeissa tarkastetaan, onko Pet vielä hengissä, tarvitseeko se pesua tai sairastuuko se) ja Petin tallennuksesta PetDao-olion avulla (itse tallennus toteutetaan luokassa FilePetDao). Edesmenneet Petit siirretään PetCemeteryDao-olion tallennettavaksi.

Esimerkiksi kun käyttöliittymässä paina nappia Feed, kutsutaan PetCaren metodia feedPet(), joka kasvattaa Petin energia-statin arvoa luvulla 10. Statin arvo ei voi kasvaa yli sen maksimiarvon tai tippua alle sen minimiarvon, mutta tämän vahtimisesta vastaa itse Stat-olio. Game loopissa ajoittain kutsutaan PetCare:n metodeja päivittämään Petin stattien tila ja tarkastamaan, onko se vielä hengissä.

### StatManager
PetCare käyttää apunaan StatManager-luokkaa laskemaan Petin stattien tippumista. StatManager sisältää oliomuuttujana Petin, jonka se saa konstruktorissaan PetCare-luokalta, sekä oliomuuttujan decayRate, joka ilmaisee tahdin jolla stattien arvo laskee (alussa noin 1/108 per sekunti, suurinpiirtein pyöristettynä 0.0009 per sekunti). Luokan metodeissa annetaan parametrina aika, jolta stattien lasku suoritetaan.

Nopeuteen, jolla stattien arvo laskee, vaikuttaa moni tekijä: Petin ikä (petillä on kolme ns. kehitysastetta, joiden perusteella decayRatea muokataan), muiden stattien arvo (esim. terveys alkaa laskea vasta kun energia tai hygienia ovat tarpeeksi matalalla) tai muu Petin sisäinen tila (jos Pet on sairas, terveys alkaa tippua paljon nopeammin) sekä joissakin tapauksissa muut ylimääräiset muuttujat (energia tippuu hitaasti, puolet normaalista decayRatesta, jos onnellisuuden arvo on yli 75:n).

Petin statit laskevat myös siltä ajalta, kun sovellus on ollut suljettuna - ohjelman käynnistyessä GUI kutsuu PetCaren metodia calculatePetStatus(), jossa kutsutaan StatManagerin metodia calculatePetStats(). Metodi hakee Petiltä ajan, jolloin sovellus edellisen kerran suljettiin, laskee välissä kuluneen ajan sekä Petin iän ja päivittää Petin statteja sen mukaan. Sen jälkeen PetCaren metodissa tarkastetaan onko Pet vielä elossa, ja jos ei, se siirretään PetCemetery-olion tallennettavaksi.

### MiniGame

Luokka MiniGame sisältää pelin sisäisen minipelin logiikan, ja sitä pelataan sen oman graafisen käyttöliittymän (MiniGameScene) kautta. MiniGamesta saatujen pisteiden perusteella PetCaren metodi play(int) kasvattaa Petin Happiness-stattia (pisteet annetaan metodille parametrina).

## Luokka/pakkauskaavio

Alla on vielä esitetty ohjelman luokka/pakkauskaavio sovelluslogiikan kannalta olennaisin osin. Pakkaus tamagotchi ja sen sisältämä luokka Main, joka käynnistää ohjelman, on tässä jätetty pois, samoin kuin luokat GameRenderer ja Sprite, joita käytetään vain graafisen ulkonäön luomiseen.

![LuokkaPakkauskaavio](https://user-images.githubusercontent.com/73843204/102518051-16176680-4091-11eb-80d3-8385a09f8d8c.png)

## Tallennus

Pakkauksessa dao ovat kaikki tallennukseen liittyvät luokat.

### FilePetDao

Itse Petin tallennuksesta vastaava luokka on FilePetDao. Petin nimi, syntymäpäivä, aika jolloin sovellus suljettiin (Petin lastLogin), sen eri stattien tila sekä arvot muuttujille 'needsWash' ja 'isSick' kirjoitetaan tekstitiedostoon sovelluksen sulkeutuessa. Kun sovellus taas avataan tallennetun Petin tiedot luetaan ja asetetaan luodulle Pet-oliolle, jonka FilePetDao antaa takaisin oliolle PetCare. Tiedot tallennetaan String-muodossa ja puolipisteillä erotettuna, esim. 

```
Zorblax;2020-11-30;1607261049;98.00;99.00;100.00;99.00;true;false
```

### SQLPetCemeteryDao

Käyttäjällä aiemmin olleet Petit tallennetaan SQL-tietokantaan, jonka luomisesta vastaa luokka SQLPetCemetery. Petistä tallennetaan ainoastaan sen nimi ja ikä. Käyttäjä pääsee tarkatselemaan taulukon sisältöä MainGaimScenen 'View Cemetery'-napin kautta.

### DAO

Tallennuksessa on käytetty Data Access Object -mallia, ja pelilogiikan koodissa käsitellään rajapintoja PetDao ja PetCemeteryDao varsinaisten tallennusluokkien sijasta. PetCare-luokan testauksessa voidaan näin käyttää valekomponentteja, PetDao-rajapinnan toteuttavaa FakePetDao:a ja PetCemetery-rajapinnan toteuttavaa FakePetCemeteryDao:a, varsinaisten tallennustiedostojen luomisen sijaan. Käyttöliittymän käynnistyessä luodaan olio FilePetDao, joka konstruktorissa injektoidaan PetCare-oliolle. FilePetDao tarpeen mukaan luo tai lataa tallennustiedoston "saveFile.txt". Koska pelissä on vain yksi käyttäjä ja yksi Pet (tällä hetkellä), on tallennustiedostojakin vain yksi. Samoin ohjelman käynnistyessä luodaan olio SQLPetCemetery, joka tarvittaessa luo käyttämänsä tiedoston "petCemetery.db" ja joka annetaan PetCare-oliolle konstruktorin parametrina.

## Päätoiminnallisuudet

#### Petin nimeäminen

Seuraavassa on havainnollistettu sekvenssikaaviolla ohjelman toimintaa, kun käyttäjä antaa uudelle Petille haluamansa nimen:

![NimenantoSekvenssikaavio](https://user-images.githubusercontent.com/73843204/102498899-d6458480-407a-11eb-92ea-a563c4c99f38.png)

Käyttäjä syöttää haluamansa nimen tekstikenttään ja tallentaa sen painamalla napista 'SaveName!', jolloin NewGameSceneControllerin tapahtumankuuntelijametodi setPetName() hakee nimen TextField-oliolta 'inputPetName' ja tallentaa nimen oliomuuttujaan this.petName. Tämän jälkeen käyttäjä painaa nappia 'Start Game', mikä aktivoi kontrolleriluokan metodin startGame(). Metodi tarkastaa ensin nimen kutsumalla metodia checkName(). Jos nimeä ei ole tallennettu, jos se sisältää merkin ';' tai jos se on liian lyhyt tai ei sisällä yhtään kirjainta, kutsutaan metodia showErrorLabel() oikealla parametrilla näyttämään virheilmoituksen käyttäjälle, ja palautetaan false. Jos nimi täyttää kaikki vaatimukset, checkName() palauttaa true, ja metodi startGame() hakee GUI:lta PetCare-olion ja siltä Pet-olion, jolle se asettaa nimen. Sen jälkeen metodi kutsuu GUI:n metodia setMainGameScene(), joka vaihtaa näkymään MainGameScenen.

#### Petistä huolehtiminen

Alla on kuvattu sekvenssikaaviolla Petin ruokkiminen eli 'Feed'-toiminnallisuus.

![TamagotchiFeedSekvenssikaavio](https://user-images.githubusercontent.com/73843204/100790794-a2325880-3418-11eb-8d0b-bca79e6b51e2.png)

Käyttäjä voi ruokkia lemmikkiä painamalla MainGameScenen 'Feed' nappia, joilloin MainGameSceneControllerin tapahtumankäsittelijämetodi hakee GUI:n sisältämän PetCare-olion ja kutsuu sen feedPet()-metodia. PetCare suorittaa metodin kutsumalla sen sisältämältä Petiltä Energy-nimisen Stat-olion, joka tuntee Petin energia-tason, ja kutsuu sille metodia increase(10). Stat-luokan increase()-metodi tarkastaa, ettei lisäys kasvata valueta yli maksimiarvon (100) - tässä tapauksessa value-muuttujan arvoksi tulee max-muuttujan arvo, muutoin value:n arvoon lisätään parametrina saatu arvo 10. Tämän jälkeen ohjelma palaa tapahtumankäsittelijämetodiin, joka hakee taas käyttöliittymältä PetCare-olion, siltä Petin, Petiltä olion Energy ja siltä sen arvon, ja asettaa sen omalle oliomuuttujalleen energyProgressBar, joka näyttää Petin energiatason käyttäjälle.

Muut MainGameScenessä mahdolliset toiminnot toimivat saman periaatteen mukaisesti, hakemalla käyttöliittymältä olion PetCare ja kutsumalla sen metodia Petin statin kasvatukseen. Jos Pet on sairas, tulee yläkulmaan näkyviin musta pääkallo, joka katoaa kun Petin Health-mittari kasvatetaan maksimiin. Samoin Pet saattaa milloin tahansa jättää pieniä vihreitä jätöksiä (tällä hetkellä vain yhden kerrallaan), jotka katoavat kun käytetään Clean-toimintoa. Painamalla napista 'View Cemetery' vaihtuu näkymään PetCemeteryScene, jossa ohjelma listaa taulukkoon käyttäjällä aiemmin olleet Petit.

#### MiniGame

Poikkeuksena muihin toimintoihin on Play-nappi, joka kutsuu GUI:n metodia setMiniGameScene(), joka siis vaihtaa näkymään Petin kanssa pelattavan minipelin. Minipelissä arvataan, ajatteleeko Pet korkeampaa vai matalampaa lukua kuin esillä oleva numero.

Ohessa on sekvenssikaavio pelin toiminnasta, kun pelaaja painaa nappia 'Lower' eli arvaa vastauksen olevan matalampi (esimerkissä oletetaan, että vastaus on myös oikein):

![MiniGameAnswerCorrect](https://user-images.githubusercontent.com/73843204/101374045-f83a4c80-38ad-11eb-8367-a053d4e26deb.png)

Pelaajan painaessa nappia 'Lower' MiniGameControllerissa kutsutaan metodia handleButtonActionGuessLower(). Ensinnäkin kutsutaan oliomuuttujana olevan minipelin metodia handleGuess parametrilla 'false'. Metodi tarkastaa, että oliomuuttujan 'answerGiven' arvo on 'false', eli ettei tällä kierroksella ole jo annettu vastausta. Sen jälkeen muuttujan arvoksi asetetaan 'true', ja tarkastetaan oliko vastaus oikein kutsumalla metodia isHigher parametrina saadulla arvolla 'false'.

Metodi isHigher vertaa arvoa 'false' lauseeseen this.answer > this.number, eli se kysyy onko epätotta että vastaus on suurempi kuin näytetty numero (ts. onko totta että vastaus on matalampi kuin numero) ja palauttaa vertailun tuloksen. Kaaviossa oletetaan, että vertailu on tosi (eli pelaaja on arvannut oikein matalampi) ja metodi handleGuess nostattaa pistemäärää (this.score-muuttujan arvoa) yhdellä. Tämän jälkeen kutsutaan em. vertailua uudestaan ja palautetaan se MiniGameSceneControllerin metodille. Ehtolauseen täyttyessä kutsutaan toista metodia handleGuessCorrect, joka kutsuu guessResult-oliomuuttujan (tyyppiä TextField) metodia setText parametrilla "Correct!", eli peli ilmoittaa pelaajan arvanneen oikein. HandleGuessCorrect metodi kutsuu vielä renderer-oliota piirtämään pelin canvas-oliolle kuvan iloisesta lemmikistä, mutta tätä ei ole tarkemmin kuvattu kaaviossa.

Sen jälkeen palataan takaisin metodiin handleButtonActionGuessLower(), joka kutsuu MiniGameSceneControllerin metodia setUpTextFieldAnswer. Tätä varten haetaan ensin MiniGame-oliolta sen oliomuuttujan answer arvo ja annetaan se metodikutsun parametriksi. Metodi kutsuu oliomuuttujalle answer (TextField) metodia setText antaen parametrina minipeliltä saadun vastauksen. Näin peli näyttää pelaajalle myös oikean vastauksen.

#### Stattien päivitys

Alla on esitetty vielä hieman tiivistettynä PetCaren metodin calculatePetStatus() toiminta sekvenssikaaviona (joka on silti jokseenkin pitkä):

![CalculatePetStatus](https://user-images.githubusercontent.com/73843204/102502424-dfd0eb80-407e-11eb-9b79-025c934a3ffc.png)

PetCaren metodia calculatePetStatus() kutsutaan GUI:ssä ohjelman käynnistyessä. Ensin se ottaa talteen Petille tallennetun iän, ennen kuin se kutsuu StatManagerin metodia calculatePetStats() laskemaan Petin stattien tilan. StatManagerin puolella metodi laskee ensin erotuksen nykyhetken ja ohjelman edellisen sulkemisen ajankohdan välillä. Sen jälkeen kutsutaan Petin omaa metodia laskemaan sen ikä oikein sen syntymäpäivästä nykyhetkeen, ja asetetaan se Petin uudeksi iäksi. Seuraavaksi kutsutaan Petin metodia setDevelopmentStage() asettamaan Petin kehitysaste oikein sen iän perusteella. StatManager asettaa vielä decayRate-muuttujansa arvon oikein Petin kehitysasteen perusteella. Sen jälkeen kutsutaan metodia updateStats() antaen parametrina sekunneissa aika, joka on kulunut viimeisestä sisäänkirjautumisesta. Metodissa päivitetään Petin statit jokainen omalla metodillaan (näitä ei ole tarkemmin kuvattu kaaviossa tilanpuutteen ja selvyyden vuoksi).

Stattien päivityksen jälkeen palataan takaisin PetCaren metodiin, jossa tarkistetaan, onko Pet vielä hengissä. Jos sekä Energy että Health -statti ovat nollassa (petIsAlive() palauttaa 'false', eli pet ei ole elossa), Pet todetaan kuolleeksi, jolloin sille asetetaan sen alkuperäinen ikä, joka otettiin talteen metodin alussa (siltä varalta, että ohjelma on ollut suljettuna esim. sata päivää - Petille ei ole tarkoitus kertyä ikää ajalta, jonka se on ollut kuolleena). PetCare kutsuu PetCemetery-olion metodia addPet() antaen Petin sille parametrina, ja Pet lisätään PetCemetery-tietokantaan.

## Ohjelman rakenteeseen jääneet heikkoudet

### GUI ja AnimationTimer

Pelilogiikka on pyritty suurimmaksi osaksi eriyttämään käyttöliittymästä, mutta tapa jolla Petin tilaa tarkastetaan ohjelman käynnistyessä ja peliloopin pyöriessä on ehkä vähän kömpelö.

### FilePetDao

Pelin tallennus tapahtuu yksinkertaiseen tekstitiedostoon, jota tarpeen tullen pääsee helposti myös muokkaamaan, jolloin se kuitenkin on jokseenkin altis käyttäjän virhesyötteille. Jos Pettiin liittyvän tallennettavan tiedon määrä kasvaisi suuremmaksi, voisi olla järkevämpää käyttää tietokantaa. Tietokantoihin voisi olla helpompi myös tallentaa samalle käyttäjälle useampi Pet-olio.

### PetCare-luokka liian pitkä?

Alunperin PetCare-luokka oli vastuussa kaikesta Pettiin liittyvästä toiminnasta, mutta sen vastuut jaettiin lopulta osittain luokalle StatManager. Luokkaa olisi voinut pilkkoa ehkä vielä hieman lisää, esim. tehdä toinen luokka joka vastaa toiminnoista feed, heal, play ja clean, jättäen pelilogiikasta vastaavan luokan hoidettavaksi ainoastaan Petin tilan valvomisen ja muiden luokkien yhteistoiminnan koordinoimisen.

### GameRenderer

GameRenderer luokka on myös melko laaja, ja ehkä pilkottavissa useampaan luokkaan. Spriten kuvan piirtäminen on sentään ulkoistettu itse Sprite-luokalle, joka osaa valita oikean kuvan Petin kehitysasteen mukaan.

