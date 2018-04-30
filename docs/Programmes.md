# Programmes

## `GET /programme/active` :key:
Retourne le programme de la semaine actuelle de l'utilisateur connecté

### Réponse

```
Status: 200 OK
```

```json
{
    "@JSONNORECURSION_PROGRAMMEID": 1,
    "id": 200,
    "activites": [
        {
            "@JSONNORECURSION_ACTIVITYID": 2,
            "id": 100,
            "sport": {
                "id": 1,
                "nom": "Course",
                "kmH": 12,
                "kcalH": 880,
                "kcalKm": 73.333336
            },
            "distance": 300,
            "programme": 1,
            "date": "2018-04-23",
            "estRealisee": false
        },
        {
            "@JSONNORECURSION_ACTIVITYID": 3,
            "id": 400,
            "sport": {
                "id": 1,
                "nom": "Course",
                "kmH": 12,
                "kcalH": 880,
                "kcalKm": 73.333336
            },
            "distance": 200,
            "programme": 1,
            "date": "2018-04-26",
            "estRealisee": true
        }
    ],
    "realisations": [
        {
            "@JSONNORECURSION_REALISATIONID": 4,
            "id": 100,
            "distance": 300,
            "date": "2018-04-23",
            "activite": 3
        }
    ]
}
```

## `GET /programme/generate` :key:
Génère le programme pour la semaine courante de l'utilisateur connecté uniquement

### Réponse

```
Status: 200 OK
```

```json
{
    "@JSONNORECURSION_PROGRAMMEID": 1,
    "id": 127,
    "activites": [
        {
            "@JSONNORECURSION_ACTIVITYID": 2,
            "id": 126,
            "sport": {
                "id": 1,
                "nom": "Course",
                "kmH": 12,
                "kcalH": 880,
                "kcalKm": 73.333336
            },
            "distance": 18,
            "date": "2018-05-03",
            "estRealisee": false,
            "centreInteret": {
                "id": 10006,
                "name": "Gwen2kA",
                "point": {
                    "id": 10006,
                    "x": 45.74327516129033,
                    "y": 4.829121612903227
                }
            },
            "timeFrame": {
                "id": 30,
                "heureDebut": 15,
                "heureFin": 18,
                "jour": "JEUDI",
                "evaluation": 0.8110300785697289,
                "date": "2018-05-03",
                "donneeAthmospherique": {
                    "id": 121,
                    "indice": 9.621801603077296,
                    "date": "2018-05-03"
                },
                "weatherData": {
                    "id": 32,
                    "dt_txt": "2018-05-03 15:00:00",
                    "date": "2018-05-02T22:00:00.000+0000",
                    "main": {
                        "temp": 15.09,
                        "temp_min": 15.09,
                        "temp_max": 15.09
                    },
                    "wind": {
                        "speed": 8.52,
                        "deg": 7.00302
                    },
                    "weather": null,
                    "precipitation": 0,
                    "speed": 8.52,
                    "temp": 15.09,
                    "deg": 7.00302,
                    "temp_min": 15.09,
                    "temp_max": 15.09
                },
                "nomJour": "JEUDI"
            }
        }
    ],
    "realisations": null
}```