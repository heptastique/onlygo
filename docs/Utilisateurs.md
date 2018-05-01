# Utilisateur

## `POST /user/add` :key:
Création d'un utilisateur

### Requête
```json
{
	"username": "example",
	"password": "pwd",
	"firstname": "first",
	"lastname": "last",
	"email": "mail@domain.com"
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
    "autohorities": [
        {
            "authority": "ROLE_USER"
        }
    ],
    "enabled": true
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
	"distance": "10"
}
```
#### Réponse

```
Status: 200 OK
```

```json
{
    "distance": 10.0
}
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
