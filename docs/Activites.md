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
```

## `GET /activity/itinary?user="nomUser"?activite="idActivite"` :key:
Calcul l'itinéraire d'une activité d'un utilisateur.

### Réponse

```
Status: 200 OK
```

###Exemple
```
localhost:8080/itinary?activite=10008
```

```json
{
    "pointCentreInteretList": [
        {
            "id": null,
            "x": 45.72737742628175,
            "y": 4.81175335359024
        },
        {
            "id": 30,
            "x": 45.77285,
            "y": 4.8537
        },
        {
            "id": 24,
            "x": 45.77373,
            "y": 4.85171
        },
        {
            "id": 45,
            "x": 45.7741,
            "y": 4.85754
        }
    ],
    "polyline": "ccbvGmxj\\e{GeeGoDlKiAmc@aM~dA_HfGkNsLhMjLv[g}@yFaRhAxi@kJhZiSvBo^}t@xAyWpDpBqDqB}@c@~bA~X_cA_Yh|@jr@i@kl@is@pk@b{@oXcBkJqWzhAk`@_e@`{@o\\gAhYoM`^xDiLnEaw@gv@qBK`l@ji@v\\vNk_AmQ~dAem@ar@EuOrBgNlZvmAxEjDdGExT}y@{Pnq@yp@qj@|n@fo@xHykAcAxy@x@eDwEdPjDofA_m@np@dKsr@no@x]qOx`@kLbSnSaoA}w@|d@r{@}[ca@n_A_FyFzm@qf@qPnd@nM{j@we@vr@e]cs@vYzo@|QodAka@do@Uqp@~e@p@}o@lH|G{Ih\\jpACeqAc@xoA~SwXem@y@bd@_r@l@hfA}NiiAzFfsAfDcCnFmmA|Abu@eMz\\uD}sABdqAdJwEqUwA~ApAiO{QlKxMaPkeAl_@pqAwi@ulAj{@bn@hHu]w{@hWzOpUzm@qf@d{GdeG"
}
```
