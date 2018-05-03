# Utilisateur

## `POST /user/add` :key:
Création d'un utilisateur.

### Requête
```json
{
	"username": "example",
	"password": "pwd",
	"firstname": "first",
	"lastname": "last",
	"email": "mail@domain.com",
	"objectifHebdo": 15,
	"distanceMax": 5,
	"objectifHebdoCourse" : 5,
	"objectifHebdoMarche" : 2,
	"objectifHebdoCyclisme" : 3,
	"location": {
		"x": 1.0,
		"y": 1.0
	}
}
```

### Réponse

```
Status: 201 Created
```

```json
{
    "username": "example",
    "firstname": "first",
    "lastname": "last",
    "email": "mail@domain.com",
    "objectifHebdo": 15,
    "distanceMax": 5,
    "programmes": null,
    "location": {
        "id": 1,
        "x": 1,
        "y": 1
    }
}
```

## `GET /user` :key:
Renvoie l'utilisateur actuellement connecté

### Réponse

```
Status: 200 OK
```

```json
{
    "username": "admin",
    "firstname": "admin",
    "lastname": "admin",
    "email": "admin@admin.com",
    "authorities": [
        {
            "authority": "ROLE_USER"
        },
        {
            "authority": "ROLE_ADMIN"
        }
    ],
    "enabled": true
}
```

## `PUT /user/objectif` :key:
Ajout objectif pour l'utilisateur connecté.

### Requête
```json
{
    "sportId" : 2,
    "distance": 10
}
```
#### Réponse

```
Status: 200 OK
```

```json
[
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
    },
    {
        "@JSONNORECURSION_ID": 2,
        "objectif": 24,
        "sport": {
            "id": 2,
            "nom": "Marche",
            "kmH": 4,
            "kcalH": 245,
            "kcalKm": 61.25
        }
    }
]
```


## `GET /user/objectif` :key:
Renvoie l'objectif hebdomadaire de l'utilisateur connecté en km.

### Réponse

```
Status: 200 OK
```

```json
{
    "distance":10.0
}
```

## `GET /user/progression` :key:
Renvoie le pourcentage de réalisation de l'objectif de l'utilisateur connecté pour le programme de la semaine courante

### Réponse

```
Status: 200 OK
```

```json
{
    "progression":88.0
}
```

## `GET /user/realisation` :key:
Renvoie la liste des réalisations de l'utilisateur

### Réponse

```
Status: 200 OK
```

```json
[
    {"@JSONNORECURSION_REALISATIONID":1,
     "id":100,
     "distance":300.0,
     "date":"2018-04-23",
     "activite":
                {
                "@JSONNORECURSION_ACTIVITYID":2,
                 "id":200,
                 "sport":
                        {
                        "id":1,
                        "nom":"Course",
                        "kmH":12.0,
                        "kcalH":880.0,
                        "kcalKm":73.333336
                        },
                "distance":200.0,
                "date":"2018-04-26",
                "estRealisee":true,
                "centreInteret":null},
     "centreInteret":null
    }
    
]
```

## `GET /user/location` :key:
Renvoie la préférence de localisation de départ de l'utilisateur connecté

### Réponse

```
Status: 200 OK
```

```json
{
    "id":2,
    "x":45.78245193913647,
    "y":3.487078
}
```

## `PUT /user/location` :key:
Réglage de la préférence de la localisation de départ de l'utilisateur connecté

### Requête
```json
{
    "x":45.7824519391364728,
    "y":3.4870780 
} 
```

### Réponse

```
Status: 200 OK
```

```json
{
    "@JSONNORECURSION_ID":1,
    "username":"admin",
    "firstname":"admin",
    "lastname":"admin",
    "email":"admin@admin.com",
    "objectifHebdo":-1.0,
    "programmes": 
        [
            {
            "@JSONNORECURSION_PROGRAMMEID":2,
            "id":500,"activites":[]
            ,"realisations":[]
            }
        ],
    "location": {
        "id":3,
        "x":45.78245193913647,
        "y":3.487078
    }
 }
```

## `GET /user/maxDistance` :key:
Renvoie le nombre de km maximum par course accepté par l'utilisateur

### Réponse

```
200 OK
```

```json
{
    "distance": 10
}
```

## `PUT /user/maxDistance` :key:
Met à jour la distance max par course en km pour l'utilisateur connecté

### Requête

```json
{
    "distance": 10.0
}
```

### Réponse

```
200 OK
```

```json
{
    "distance": 10
}
```

## `PUT /user/email` :key:
Met a jour l'e-mail de l'utilisateur

### Requête

```json
{
    "email": "user@domain.com"
}

### Réponse

```
200 OK
```

```json
{
    "@JSONNORECURSION_ID": 1,
    "username": "user41",
    "firstname": "user41",
    "lastname": "user41",
    "email": "user40@domain.com",
    "objectifs": [
        {
            "@JSONNORECURSION_ID": 2,
            "objectif": 10,
            "sport": {
                "id": 1,
                "nom": "Course",
                "kmH": 12,
                "kcalH": 880,
                "kcalKm": 73.333336
            }
        },
        {
            "@JSONNORECURSION_ID": 3,
            "objectif": 10,
            "sport": {
                "id": 2,
                "nom": "Marche",
                "kmH": 4,
                "kcalH": 245,
                "kcalKm": 61.25
            }
        },
        {
            "@JSONNORECURSION_ID": 4,
            "objectif": 10,
            "sport": {
                "id": 3,
                "nom": "Cyclisme",
                "kmH": 20,
                "kcalH": 690,
                "kcalKm": 34.5
            }
        }
    ],
    "distanceMax": 5,
    "programmes": [
        {
            "id": 10082,
            "objectifs": [
                {
                    "@JSONNORECURSION_ID": 5,
                    "objectif": 10,
                    "sport": {
                        "id": 1,
                        "nom": "Course",
                        "kmH": 12,
                        "kcalH": 880,
                        "kcalKm": 73.333336
                    }
                },
                {
                    "@JSONNORECURSION_ID": 6,
                    "objectif": 10,
                    "sport": {
                        "id": 2,
                        "nom": "Marche",
                        "kmH": 4,
                        "kcalH": 245,
                        "kcalKm": 61.25
                    }
                },
                {
                    "@JSONNORECURSION_ID": 7,
                    "objectif": 10,
                    "sport": {
                        "id": 3,
                        "nom": "Cyclisme",
                        "kmH": 20,
                        "kcalH": 690,
                        "kcalKm": 34.5
                    }
                }
            ],
            "dateDebut": "2018-04-30",
            "activites": [
                {
                    "id": 10328,
                    "sport": {
                        "id": 1,
                        "nom": "Course",
                        "kmH": 12,
                        "kcalH": 880,
                        "kcalKm": 73.333336
                    },
                    "distancePrevue": 9.71566,
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
                    "id": 10329,
                    "sport": {
                        "id": 1,
                        "nom": "Course",
                        "kmH": 12,
                        "kcalH": 880,
                        "kcalKm": 73.333336
                    },
                    "distancePrevue": 8.06553,
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
                    "id": 10330,
                    "sport": {
                        "id": 1,
                        "nom": "Course",
                        "kmH": 12,
                        "kcalH": 880,
                        "kcalKm": 73.333336
                    },
                    "distancePrevue": 11.1406,
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
                    "id": 10331,
                    "sport": {
                        "id": 1,
                        "nom": "Course",
                        "kmH": 12,
                        "kcalH": 880,
                        "kcalKm": 73.333336
                    },
                    "distancePrevue": 11.1923,
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
        },
        {
            "id": 10083,
            "objectifs": [
                {
                    "@JSONNORECURSION_ID": 8,
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
                    "id": 10332,
                    "sport": {
                        "id": 1,
                        "nom": "Course",
                        "kmH": 12,
                        "kcalH": 880,
                        "kcalKm": 73.333336
                    },
                    "distancePrevue": 7.42005,
                    "distanceRealisee": 7.42005,
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
                        "id": 56,
                        "heureDebut": 21,
                        "heureFin": 24,
                        "jour": "DIMANCHE",
                        "evaluation": -1,
                        "date": null,
                        "donneeAthmospherique": null,
                        "weatherData": null,
                        "nomJour": "DIMANCHE"
                    },
                    "timeFrameId": 56,
                    "tauxCompletion": 100
                },
                {
                    "id": 10333,
                    "sport": {
                        "id": 1,
                        "nom": "Course",
                        "kmH": 12,
                        "kcalH": 880,
                        "kcalKm": 73.333336
                    },
                    "distancePrevue": 9.53359,
                    "distanceRealisee": 9.53359,
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
                        "id": 37,
                        "heureDebut": 12,
                        "heureFin": 15,
                        "jour": "VENDREDI",
                        "evaluation": -1,
                        "date": null,
                        "donneeAthmospherique": null,
                        "weatherData": null,
                        "nomJour": "VENDREDI"
                    },
                    "timeFrameId": 37,
                    "tauxCompletion": 100
                },
                {
                    "id": 10334,
                    "sport": {
                        "id": 1,
                        "nom": "Course",
                        "kmH": 12,
                        "kcalH": 880,
                        "kcalKm": 73.333336
                    },
                    "distancePrevue": 8.29084,
                    "distanceRealisee": 8.29084,
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
                        "id": 41,
                        "heureDebut": 0,
                        "heureFin": 3,
                        "jour": "SAMEDI",
                        "evaluation": -1,
                        "date": null,
                        "donneeAthmospherique": null,
                        "weatherData": null,
                        "nomJour": "SAMEDI"
                    },
                    "timeFrameId": 41,
                    "tauxCompletion": 100
                },
                {
                    "id": 10335,
                    "sport": {
                        "id": 1,
                        "nom": "Course",
                        "kmH": 12,
                        "kcalH": 880,
                        "kcalKm": 73.333336
                    },
                    "distancePrevue": 11.2631,
                    "distanceRealisee": 11.2631,
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
                        "id": 32,
                        "heureDebut": 21,
                        "heureFin": 24,
                        "jour": "JEUDI",
                        "evaluation": -1,
                        "date": null,
                        "donneeAthmospherique": null,
                        "weatherData": null,
                        "nomJour": "JEUDI"
                    },
                    "timeFrameId": 32,
                    "tauxCompletion": 100
                }
            ],
            "tauxCompletion": 100
        }
    ],
    "location": {
        "id": 50041,
        "x": 45.77485155844808,
        "y": 4.857194179644911
    }
}
```
