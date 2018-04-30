# Programmes

## `GET /programme/active` :key:
Retourne le programme de la semaine actuelle de l'utilisateur connecté

#### Réponse

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
