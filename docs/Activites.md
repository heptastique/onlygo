# Activités

## `POST /activity/add` :key:
Ajoute une activité.

### Requête
```json
{
    "distance": 400,
    "date": "2018-04-22",
    "sportName": "Course"
}
```

### Réponse

```
Status: 200 OK
```

```json
{
    "id": 1,
    "sport": {
        "id": 3,
        "nom": "Cyclisme",
        "kmH": 20,
        "kcalH": 690,
        "kcalKm": 34.5
    },
    "distance": 400,
    "date": "2018-04-22T00:00:00.000+0000"
}
## `GET /activity/itinary?user="nomUser"?activite="idActivite"` :key:
Calcul l'itinéraire d'une activité d'un utilisateur.

### Réponse

```
Status: 200 OK
```

###Exemple

localhost:8080/itinary?user=user1?activite=10008
```json
[
    {
        "id": 50001,
        "x": 45.771984580583826,
        "y": 4.81530101826631
    },
    {
        "id": 12,
        "x": 45.77566,
        "y": 4.8477
    },
    {
        "id": 4,
        "x": 45.77665,
        "y": 4.84571
    },
    {
        "id": 136,
        "x": 45.77725,
        "y": 4.84482
    },
    {
        "id": 116,
        "x": 45.7801,
        "y": 4.84702
    },
    {
        "id": 125,
        "x": 45.77844,
        "y": 4.84556
    ...
    {
        "id": 12,
        "x": 45.77566,
        "y": 4.8477
    },
    {
        "id": 50001,
        "x": 45.771984580583826,
        "y": 4.81530101826631
    }
]
```

