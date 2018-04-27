# Liste des points d'accès REST

> Ce fichier sera amené à être modifié en même temps que le code implémentant les points d'accès

## Points d'accès de test

### `GET /protected` :key:
Accessible uniquement pour les utilisateurs ayant le privilège admin

#### Réponse

```
Status: 200 OK
```

```
Greetings from admin protected method!
```

### `GET /hello` :key:
Accessible à tous les utilisateurs authentifiés

#### Réponse

```
Status: 200 OK
```

```
Ok
```

### `POST /user/add` :key:
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

#### Réponse

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


### `GET /user` :key:
Renvoie l'utilisateur actuellement connecté

#### Réponse

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

### `GET /user/all`
Renvoie la liste de tous les utilisateurs enregistrés

#### Réponse

```
Status: 200 OK
```

```json
[
    {
        "id": 1,
        "username": "admin",
        "password": "$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi",
        "firstname": "admin",
        "lastname": "admin",
        "email": "admin@admin.com",
        "enabled": true,
        "lastPasswordResetDate": "2003-01-21T23:00:00.000+0000",
        "authorities": [
            {
                "id": 1,
                "name": "ROLE_USER"
            },
            {
                "id": 2,
                "name": "ROLE_ADMIN"
            }
        ]
    },
    {
        "id": 2,
        "username": "user",
        "password": "$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC",
        "firstname": "user",
        "lastname": "user",
        "email": "enabled@user.com",
        "enabled": true,
        "lastPasswordResetDate": "2003-01-21T23:00:00.000+0000",
        "authorities": [
            {
                "id": 1,
                "name": "ROLE_USER"
            }
        ]
    },
    {
        "id": 3,
        "username": "disabled",
        "password": "$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC",
        "firstname": "user",
        "lastname": "user",
        "email": "disabled@user.com",
        "enabled": false,
        "lastPasswordResetDate": "2003-01-21T23:00:00.000+0000",
        "authorities": [
            {
                "id": 1,
                "name": "ROLE_USER"
            }
        ]
    }
]
```

### `GET /sport/all` :key:
Renvoie la liste de tous les sports.

#### Réponse

```
Status: 200 OK
```

```json
[
    {
        "id": 1,
        "nom": "Course",
        "kmH": 12,
        "kcalH": 880,
        "kcalKm": 73.333336
    },
    {
        "id": 2,
        "nom": "Marche",
        "kmH": 4,
        "kcalH": 245,
        "kcalKm": 61.25
    },
    {
        "id": 3,
        "nom": "Cyclisme",
        "kmH": 20,
        "kcalH": 690,
        "kcalKm": 34.5
    }
]
```

### `POST /ativity/add` :key:
Ajoute une activité.

### Requête
```json
{
    "distance": 400,
    "date": "2018-04-22",
    "sportName": "Course"
}
```

#### Réponse

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

### `GET /programme/active` :key:
Retourne le programme de la semaine actuelle.
(Identifie l'utilisateur à partir de son token).
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

### `POST /realisation/add` :key:
Ajoute une réalisation en la liant, ou non, à une activité existante
du programme encore non réalisée.
activityId et ciId (centre d'intérêt) sont facultatifs.

### Requête
```json
{
	"sportId": 1,
	"distance": 2000,
	"activityId": 300,
	"date": "2018-04-24",
	"ciId": 10001
}
```

#### Réponse

```
Status: 200 OK
```

```json
{
    "@JSONNORECURSION_REALISATIONID": 1,
    "id": 1,
    "distance": 2000,
    "date": "2018-04-24T00:00:00.000+0000",
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
        "distance": 200,
        "date": "2018-04-24",
        "estRealisee": true,
        "centreInteret": {
            "id": 10000,
            "name": "Parc de la Tête d'Or",
            "point": {
                "id": 10000,
                "x": 0.6097772747903308,
                "y": 0.06464012629501724
            }
        }
    },
    "centreInteret": {
        "id": 10001,
        "name": "Tour Parc de la Tête d'Or",
        "point": {
            "id": 10001,
            "x": 0.7563748869000787,
            "y": 0.08019959382376082
        }
    }
}
```
