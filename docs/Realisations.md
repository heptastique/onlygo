# Réalisations

## `POST /realisation/add` :key:
Ajoute une réalisation et l'associe à la prochaine activité du programme.

### Paramètres
```json
{
	"distance": 2000,
	"date": "2018-04-24T16:04:12"
}
```

### Réponse

```
Status: 200 OK
```

```json
{
    "@JSONNORECURSION_REALISATIONID": 1,
    "id": 1,
    "distance": 2000,
    "date": "2018-04-24T16:04:12.000+0000",
    "activite": {
        "@JSONNORECURSION_ACTIVITYID": 2,
        "id": 300,
        "sport": {
            "id": 2,
            "nom": "Marche",
            "kmH": 4,
            "kcalH": 245,
            "kcalKm": 61.25
        },
        "distance": 8,
        "date": "2018-05-02",
        "estRealisee": true,
        "centreInteret": {
            "id": 10000,
            "name": "Parc de la Tête d'Or",
            "point": {
                "id": 10000,
                "x": 45.77846429629631,
                "y": 4.852797629629627
            }
        },
        "timeFrame": {
            "id": 35,
            "heureDebut": 6,
            "heureFin": 9,
            "jour": "VENDREDI",
            "evaluation": -1,
            "date": null,
            "donneeAthmospherique": null,
            "weatherData": null,
            "nomJour": "VENDREDI"
        }
    },
    "centreInteret": {
        "id": 10001,
        "name": "Tour Parc de la Tête d'Or",
        "point": {
            "id": 10001,
            "x": 45.77868958333333,
            "y": 4.853984940476191
        }
    },
    "timeFrame": {
        "id": 15,
        "heureDebut": 18,
        "heureFin": 21,
        "jour": "MARDI",
        "evaluation": -1,
        "date": null,
        "donneeAthmospherique": null,
        "weatherData": null,
        "nomJour": "MARDI"
    }
}
```
```
Status: 400 Bad Request
(if no activity planned left in the program)
```
