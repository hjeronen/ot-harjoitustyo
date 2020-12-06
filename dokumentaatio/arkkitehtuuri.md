# Arkkitehtuurikuvaus

### Rakenne

Pakkauskaaviossa on esitetty ohjelman pakkausrakenne:

![TamagotchiPakkauskaavio](https://user-images.githubusercontent.com/73843204/101286776-bdbcab00-37ec-11eb-94a0-7d496cb0a924.png)

Kaikki käyttöliittymän koostamiseen liittyvät luokat on sijoitettu pakkaukseen ui, pelilogiikka on pakkauksessa logic, pelissä hoidettavaa virtuaalilemmikkiä edustava luokka Pet ja siihen liittyvä luokka Stat ovat pakkauksessa domain ja pelin tallennuksesta vastaavat luokat pakkauksessa dao.

### Käyttöliittymä

Käyttöliittymä on toteutettu JavaFX:llä, ja kaikki sen koostamiseen käytety luokat löytyvät pakkauksesta ui. Pääasiallinen käyttöliittymän hallinnasta vastaava luokka on GUI, joka vastaa eri pelinäkymien käytöstä ja pelilogiikan yhdistämisestä niihin. Pelinäkymiä on neljä, NewGameScene, MainGameScene, MiniGameScene ja GameOverScene. Jokainen niistä on siis FXML-dokumentin määrittelemä Scene-olio, joka GUI:ssa ladataan ja asetetaan oliomuuttujaan stage käyttäjälle näytettäväksi. Jokaisella on oma controller-luokka, jossa ko. näkymän toiminta on määritelty. Nämä kontrolleriluokat on esitetty alempana luokka/pakkauskaaviossa.

Itse pelilogiikasta vastaa luokka PetCare, jonka tuntee vain luokka GUI. GUI:n kautta muissa näkymissä tapahtuvat toiminnot kutsuvat PetCaren tai sen sisältämien luokkien metodeja, tai näkymille välitetään tietoja Petin tilasta. Ainoastaan näkymän MiniGameScene kontrolleriluokka tietää itse ja yksin käyttämänsä MiniGame-luokan, joka siis vastaa ohjelman sisäisen minipelin toimintalogiikasta.

Se, mikä pelinäkymä on kulloinkin esillä, riippuu pelitilanteesta. Aloitettaessa uutta peliä asetetaan näkyville ensin NewGameScene, jossa pelaaja voi valita Petille nimen. Tämän jälkeen tai jos ohjelma löytää edellisen pelin tallennuksen vaihdetaan näkymään MainGameScene, jossa itse peli tapahtuu. Pelaaja näkee mittareista Petin eri stattien tilan, ja voi napeilla Feed, Heal ja Clean hoitaa Pettiä. Play napista painamalla vaihtuu näkymäksi MiniGameScene, jossa voi pelata minipeliä Petin kanssa. Jos Pettiä ei ole pitkään aikaan hoitanut, se kuolee ja näkymäksi vaihtuu GameOverScene, josta pääsee takaisin uuden pelin aloitukseen.

#### AnimationTimer

Koska käyttöliittymä on toteutettu JavaFX:llä, on ns. GameLoop toteutettu sen ominaisuudella AnimationTimer. Se luodaan GUI:n start()-metodin sisällä, ja sen handle()-metodissa päivitetään Petin tila joka 1500 millisekunti, eli 1.5 sekunnin välein. 10 sekunnin välein tarkastetaan myös sairastuuko Pet tai jättääkö se jätöksiä. Renderer-luokkaa käytetään peliloopissa piirtämään Petin visuaalinen representaatio (Sprite) MainGameScenen canvas-oliolle.

### Sovelluslogiikka

Pelin ideana on pitää hengissä virtuaalista lemmikkiä, jota edustaa luokka Pet. Pet käyttää luokan Stat ilmentymiä pitämään kirjaa omasta tilastaan, esim. Petin energian määrää ilmaisee Stat-olio, jolla on maksimi- ja minimiraja sekä arvo, jota voi kasvattaa tai vähentää. Pet ja Stat sijaitsevat pakkauksessa domain.

Pakkauksessa logic pääasiallinen pelilogiikasta vastaava luokka on PetCare, joka tuntee pelin kohteena olevan Petin. PetCare on vastuussa Petin hoitoon liittyvistä toiminnoista feed, play, heal ja clean. Esimerkiksi kun käyttöliittymässä paina nappia Feed, kutsutaan PetCaren metodia feedPet(), joka kasvattaa Petin energia-statin arvoa luvulla 10. Statin arvo ei voi kasvaa yli sen maksimiarvon tai tippua alle sen minimiarvon, mutta tämän vahtimisesta vastaa itse Stat-luokka.

PetCare käyttää apunaan StatManager-luokkaa laskemaan Petin stattien tippumista. Stattien arvo laskee tietyn määrän joka sekunti (alussa 1/108 per sekunti, suurinpiirtein pyöristettynä 0.0009 per sekunti, eli maksimiarvo 100 tippuu nollaan noin kolmessa tunnissa). Koko pelin ideana on, että Petistä on huolehdittava säännöllisesti, muuten sen statit hupenevat nollaan ja Pet todetaan kuolleeksi, jolloin peli päättyy. StatManager sisältää oliomuuttujana Petin, jonka se saa PetCare-luokalta, sekä oliomuuttujan decayRate, jonka perusteella Stat-olioiden arvon lasku lasketaan (aiemmin mainittu 0.0009). Metodeissa annetaan parametrina aika, jolta stattien lasku suoritetaan. Stattien arvon laskuun vaikuttaa myös Petin ikä (petillä on kolme ns. kehitysastetta, joiden perusteella decayRatea muokataan) sekä muiden stattien arvo (esim. terveys alkaa laskea vasta kun energia tai hygienia ovat tarpeeksi matalalla) tai muu Petin sisäinen tila (jos Pet on sairas, terveys alkaa tippua paljon nopeammin). Petin statit laskevat myös siltä ajalta, kun sovellus on ollut suljettuna - ohjelman käynnistyessä StatManagerin metodi calculatePetStats() hakee Petiltä ajan, jolloin sovellus edellisen kerran suljettiin, laskee välissä kuluneen ajan ja päivittää Petin statteja sen mukaan.

Luokka MiniGame sisältää pelin sisäisen minipelin logiikan, ja sitä pelataan sen oman graafisen käyttöliittymän (MiniGameScene) kautta. MiniGamesta saatujen pisteiden perusteella PetCaren metodi play(int) kasvattaa Petin Happiness-stattia (pisteet annetaan metodille parametrina).

Alla on vielä esitetty ohjelman luokka/pakkauskaavio sovelluslogiikan kannalta olennaisin osin. Pakkaus tamagotchi ja sen sisältämä luokka Main, joka käynnistää ohjelman, on tässä jätetty pois, samoin kuin luokat Renderer ja Sprite, joita käytetään vain graafisen ulkonäön luomiseen.

![TamagotchiLuokkaPakkauskaavio](https://user-images.githubusercontent.com/73843204/101286820-feb4bf80-37ec-11eb-88e2-0f4c37ff2c1e.png)

### Tallennus

Pakkauksessa dao on Petin tallennuksesta vastaava luokka FilePetDao. Tällä hetkellä Petin nimi, syntymäpäivä, aika jolloin sovellus suljettiin (Petin lastLogin) ja stattien tila kirjoitetaan yksinkertaisesti tekstitiedostoon sovelluksen sulkeutuessa, samoin kun sovellus taas avataan tallennetun Petin tiedot luetaan ja asetetaan luodulle Pet-oliolle, jonka FilePetDao antaa takaisin oliolle PetCare. Tiedot tallennetaan String-muodossa ja puolipisteillä erotettuna, esim. 

```
Zorblax;2020-11-30;1607261049;98.0;99.0;100.0;99.0
```

Periaatteessa käyttäjä voi halutessaan vaihtaa Petin nimeä tai manipuloida sen tilaa muokkaamalla tallennustiedostoa, esim. tehdä siitä vanhemman muuttamalla syntymäpäivää - kunhan kirjoitusasu (yyyy-mm-dd) pysyy oikeana!

Tallennuksessa on käytetty Data Access Object -mallia, ja pelilogiikan koodissa käsitellään rajapintaa PetDao varsinaisen FilePetDao-luokan sijasta, sillä tallennusmetodia on tarkoitus muuttaa myöhemmin. Käyttöliittymää käynnistettäessä tarpeen mukaan luodaan tai ladataan tallennustiedosto "saveFile.txt". Koska pelissä on vain yksi käyttäjä ja yksi Pet (tällä hetkellä), on tallennustiedostojakin vain yksi. FakePetDao-luokka on olemassa tallennuksen testausta varten.

### Päätoiminnallisuudet

Alla on kuvattu sekvenssikaaviolla Petin ruokkiminen eli 'Feed'-toiminnallisuus.

![TamagotchiFeedSekvenssikaavio](https://user-images.githubusercontent.com/73843204/100790794-a2325880-3418-11eb-8d0b-bca79e6b51e2.png)

Käyttäjä voi ruokkia lemmikkiä painamalla MainGameScenen 'Feed' nappia, joilloin MainGameSceneControllerin tapahtumankäsittelijämetodi hakee graafisen käyttöliittymän sisältämän PetCare-olion ja kutsuu sen feedPet()-metodia. PetCare suorittaa metodin kutsumalla sen sisältämältä Petiltä Energy-nimisen Stat-olion, joka tuntee Petin energia-tason, ja kutsuu sille metodia increase(10). Stat-luokan increase()-metodi tarkastaa, ettei lisäys kasvata valueta yli maksimiarvon (100) - tässä tapauksessa value-muuttujan arvoksi tulee max-muuttujan arvo, muutoin value:n arvoon lisätään parametrina saatu arvo 10. Tämän jälkeen ohjelma palaa tapahtumankäsittelijämetodiin, joka hakee taas käyttöliittymältä PetCare olion, siltä Petin, petiltä olion Energy ja siltä sen arvon, ja asettaa sen omalle oliomuuttujalleen energyProgressBar, joka näyttää Petin energiatason käyttäjälle.

Muut MainGameScenessä mahdolliset toiminnot toimivat saman periaatteen mukaisesti, hakemalla käyttöliittymältä olion PetCare ja kutsumalla sen metodia Petin statin kasvatukseen (tosin cleanPet() metodi nostaa Petin hygieniatason maksimiin). Jos Pet on sairas, tulee yläkulmaan näkyviin musta pääkallo, joka katoaa kun Pet on terve. Samoin Pet saattaa milloin tahansa jättää pieniä vihreitä jätöksiä (tällä hetkellä vain yhden kerrallaan), jotka katoavat kun käytetään Clean-toimintoa.

Poikkeuksena muihin toimintoihin on Play-nappi, joka kutsuu GUI:n metodia setMiniGameScene(), joka siis vaihtaa näkymään Petin kanssa pelattavan minipelin.


