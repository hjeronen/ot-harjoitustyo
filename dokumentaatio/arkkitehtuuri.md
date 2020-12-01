# Arkkitehtuurikuvaus

### Luokka/pakkauskaavio:
![luokkaPakkauskaavio](https://user-images.githubusercontent.com/73843204/100111876-e3ea5e80-2e6e-11eb-8057-084ab24a9544.jpg)

### Sekvenssikaavio:
Sekvenssikaavio 'Feed' toiminnallisuudesta: käyttäjä voi ruokkia lemmikkiä painamalla 'Feed' nappia, joilloin MainGameSceneControllerin tapahtumankäsittelijämetodi hakee graafisen käyttöliittymän sisältämän PetCare-olion (sovelluslogiikasta vastaava luokka) ja kutsuu sille feedPet()-metodia. PetCare suorittaa metodin kutsumalla sen sisältämältä Petiltä Energy-nimisen olion, joka tuntee Petin energia-tason, ja kutsuu sille metodia increase(10). Metodi tarkastaa, ettei lisäys kasvata valueta yli maksimiarvon (100) - tässä tapauksessa value-muuttujan arvoksi tulee max-muuttujan arvo, muutoin value:n arvoon lisätään parametrina saatu arvo 10. Tämän jälkeen ohjelma palaa tapahtumankäsittelijämetodiin, joka hakee taas käyttöliittymältä PetCare olion, siltä Petin, petiltä olion Energy ja siltä sen arvon, ja asettaa sen omalle oliomuuttujalleen energyProgressBar, joka näyttää Petin energiatason käyttäjälle.

![TamagotchiFeedSekvenssikaavio](https://user-images.githubusercontent.com/73843204/100790794-a2325880-3418-11eb-8d0b-bca79e6b51e2.png)


