# Programmes

## `GET /programme/active` :key:
Retourne le programme de la semaine actuelle de l'utilisateur connecté.

### Réponse

```
Status: 200 OK
```

```json
{
    "id": 10020,
    "objectifs": [
        {
            "@JSONNORECURSION_ID": 1,
            "objectif": 10,
            "sport": {
                "id": 1,
                "nom": "Course",
                "kmH": 12,
                "kcalH": 880,
                "kcalKm": 73.333336
            }
        }
    ],
    "dateDebut": "2018-04-30",
    "activites": [
        {
            "id": 10080,
            "sport": {
                "id": 1,
                "nom": "Course",
                "kmH": 12,
                "kcalH": 880,
                "kcalKm": 73.333336
            },
            "distancePrevue": 10.9238,
            "distanceRealisee": 0,
            "datePrevue": "2018-05-01",
            "dateRealisee": null,
            "estRealisee": false,
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
                "id": 14,
                "heureDebut": 15,
                "heureFin": 18,
                "jour": "MARDI",
                "evaluation": -1,
                "date": null,
                "donneeAthmospherique": null,
                "weatherData": null,
                "nomJour": "MARDI"
            },
            "timeFrameId": 14,
            "tauxCompletion": 0
        },
        {
            "id": 10081,
            "sport": {
                "id": 1,
                "nom": "Course",
                "kmH": 12,
                "kcalH": 880,
                "kcalKm": 73.333336
            },
            "distancePrevue": 8.03512,
            "distanceRealisee": 0,
            "datePrevue": "2018-05-02",
            "dateRealisee": null,
            "estRealisee": false,
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
                "id": 20,
                "heureDebut": 9,
                "heureFin": 12,
                "jour": "MERCREDI",
                "evaluation": -1,
                "date": null,
                "donneeAthmospherique": null,
                "weatherData": null,
                "nomJour": "MERCREDI"
            },
            "timeFrameId": 20,
            "tauxCompletion": 0
        },
        {
            "id": 10082,
            "sport": {
                "id": 1,
                "nom": "Course",
                "kmH": 12,
                "kcalH": 880,
                "kcalKm": 73.333336
            },
            "distancePrevue": 9.3684,
            "distanceRealisee": 0,
            "datePrevue": "2018-05-03",
            "dateRealisee": null,
            "estRealisee": false,
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
                "id": 31,
                "heureDebut": 18,
                "heureFin": 21,
                "jour": "JEUDI",
                "evaluation": -1,
                "date": null,
                "donneeAthmospherique": null,
                "weatherData": null,
                "nomJour": "JEUDI"
            },
            "timeFrameId": 31,
            "tauxCompletion": 0
        },
        {
            "id": 10083,
            "sport": {
                "id": 1,
                "nom": "Course",
                "kmH": 12,
                "kcalH": 880,
                "kcalKm": 73.333336
            },
            "distancePrevue": 7.7073,
            "distanceRealisee": 0,
            "datePrevue": "2018-05-04",
            "dateRealisee": null,
            "estRealisee": false,
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
                "id": 39,
                "heureDebut": 18,
                "heureFin": 21,
                "jour": "VENDREDI",
                "evaluation": -1,
                "date": null,
                "donneeAthmospherique": null,
                "weatherData": null,
                "nomJour": "VENDREDI"
            },
            "timeFrameId": 39,
            "tauxCompletion": 0
        }
    ],
    "tauxCompletion": 0
}
```
```
Status: 204 No Content
(if no active program)
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
}
```

## `GET /programme/getbydate?date=?date=Mon%20Apr%2023%202018` :key:
Retourne le programme de la semaine de la date passée en paramètre (correspondant à un lundi).

### Paramètres
La date est au format DAYOFWEEK%20MONTH%20DAYOFMONTH%20YEAR.

### Réponse

```
Status: 200 OK
```

```json
{
    "id": 10021,
    "objectifs": [
        {
            "@JSONNORECURSION_ID": 1,
            "objectif": 10,
            "sport": {
                "id": 1,
                "nom": "Course",
                "kmH": 12,
                "kcalH": 880,
                "kcalKm": 73.333336
            }
        }
    ],
    "dateDebut": "2018-04-23",
    "activites": [
        {
            "id": 10084,
            "sport": {
                "id": 1,
                "nom": "Course",
                "kmH": 12,
                "kcalH": 880,
                "kcalKm": 73.333336
            },
            "distancePrevue": 10.9318,
            "distanceRealisee": 10.9318,
            "datePrevue": "2018-04-17",
            "dateRealisee": "2018-04-17",
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
                "id": 29,
                "heureDebut": 12,
                "heureFin": 15,
                "jour": "JEUDI",
                "evaluation": -1,
                "date": null,
                "donneeAthmospherique": null,
                "weatherData": null,
                "nomJour": "JEUDI"
            },
            "timeFrameId": 29,
            "tauxCompletion": 100
        },
        {
            "id": 10085,
            "sport": {
                "id": 1,
                "nom": "Course",
                "kmH": 12,
                "kcalH": 880,
                "kcalKm": 73.333336
            },
            "distancePrevue": 9.92401,
            "distanceRealisee": 9.92401,
            "datePrevue": "2018-04-18",
            "dateRealisee": "2018-04-18",
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
                "id": 11,
                "heureDebut": 6,
                "heureFin": 9,
                "jour": "MARDI",
                "evaluation": -1,
                "date": null,
                "donneeAthmospherique": null,
                "weatherData": null,
                "nomJour": "MARDI"
            },
            "timeFrameId": 11,
            "tauxCompletion": 100
        },
        {
            "id": 10086,
            "sport": {
                "id": 1,
                "nom": "Course",
                "kmH": 12,
                "kcalH": 880,
                "kcalKm": 73.333336
            },
            "distancePrevue": 9.42682,
            "distanceRealisee": 9.42682,
            "datePrevue": "2018-04-19",
            "dateRealisee": "2018-04-19",
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
                "id": 44,
                "heureDebut": 9,
                "heureFin": 12,
                "jour": "SAMEDI",
                "evaluation": -1,
                "date": null,
                "donneeAthmospherique": null,
                "weatherData": null,
                "nomJour": "SAMEDI"
            },
            "timeFrameId": 44,
            "tauxCompletion": 100
        },
        {
            "id": 10087,
            "sport": {
                "id": 1,
                "nom": "Course",
                "kmH": 12,
                "kcalH": 880,
                "kcalKm": 73.333336
            },
            "distancePrevue": 10.8257,
            "distanceRealisee": 10.8257,
            "datePrevue": "2018-04-20",
            "dateRealisee": "2018-04-20",
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
                "id": 51,
                "heureDebut": 6,
                "heureFin": 9,
                "jour": "DIMANCHE",
                "evaluation": -1,
                "date": null,
                "donneeAthmospherique": null,
                "weatherData": null,
                "nomJour": "DIMANCHE"
            },
            "timeFrameId": 51,
            "tauxCompletion": 100
        }
    ],
    "tauxCompletion": 100
}
```
```
Status: 204 No Content
(if no program found for the date in parameter)
```
