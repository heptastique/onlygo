# Points d'accès REST 
#`Plages horaires`



### `GET /timeFrame/all` :key:
Renvoie la liste de tous les plages horaires.

#### Réponse

```
Status: 200 OK
```

```json
[
        {           
        "id":1,
        "heureDebut":0,
        "heureFin":3,
        "jour":"LUNDI",
        "evaluation":-1.0,
        "date":null,
        "donneeAthmospherique":null,
        "weatherData":null,
        "nomJour":"LUNDI"
        },
        {
        "id":2,
        "heureDebut":3,
        "heureFin":6,
        "jour":"LUNDI",
        "evaluation":-1.0,
        "date":null,
        "donneeAthmospherique":null
        ,"weatherData":null,
        "nomJour":"LUNDI"
        }
]
```
### `GET /timeFrame/now` :key:
Renvoie la plage horaire actuelle.

#### Réponse

```
Status: 200 OK
```

```json
{
    "id":54,
    "heureDebut":15,
    "heureFin":18,
    "jour":"DIMANCHE",
    "evaluation":-1.0,
    "date":null,
    "donneeAthmospherique":null,
    "weatherData":null,
    "nomJour":"DIMANCHE"
}
```
### `GET /timeFrame/time` :key:
Renvoie la plage horaire d'utilisateur.

#### Réponse

```
Status: 200 OK
```

```json
{
    "id":54,
    "heureDebut":15,
    "heureFin":18,
    "jour":"DIMANCHE",
    "evaluation":-1.0,
    "date":null,
    "donneeAthmospherique":null,
    "weatherData":null,
    "nomJour":"DIMANCHE"
}
```

### `GET /timeFrame/evaluation/now` :key:
Renvoie evaluation de  la plage horaire actuelle.

#### Réponse

```
Status: 200 OK
```

```json
{
 "note":-1.0   
}
```

### `GET /timeFrame/evaluation/time` :key:
Renvoie evaluation de  la plage horaire d'utilisateur.

#### Réponse

```
Status: 200 OK
```

```json
{
 "note":-1.0   
}
```
