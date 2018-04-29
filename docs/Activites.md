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
