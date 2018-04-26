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
    "sportId": 3
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
    "programme": null,
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
    "id": 2,
    "user": null,
    "activites": [
        {
            "@JSONNORECURSION_ACTIVITYID": 2,
            "id": 1,
            "sport": {
                "id": 1,
                "nom": "Course",
                "kmH": 12,
                "kcalH": 880,
                "kcalKm": 73.333336
            },
            "distance": 300,
            "programme": 1,
            "date": "2018-04-23"
        },
        {
            "@JSONNORECURSION_ACTIVITYID": 3,
            "id": 4,
            "sport": {
                "id": 1,
                "nom": "Course",
                "kmH": 12,
                "kcalH": 880,
                "kcalKm": 73.333336
            },
            "distance": 200,
            "programme": 1,
            "date": "2018-04-26"
        },
        {
            "@JSONNORECURSION_ACTIVITYID": 4,
            "id": 2,
            "sport": {
                "id": 2,
                "nom": "Marche",
                "kmH": 4,
                "kcalH": 245,
                "kcalKm": 61.25
            },
            "distance": 500,
            "programme": 1,
            "date": "2018-04-24"
        },
        {
            "@JSONNORECURSION_ACTIVITYID": 5,
            "id": 10,
            "sport": {
                "id": 2,
                "nom": "Marche",
                "kmH": 4,
                "kcalH": 245,
                "kcalKm": 61.25
            },
            "distance": 600,
            "programme": 1,
            "date": "2018-04-24"
        },
        {
            "@JSONNORECURSION_ACTIVITYID": 6,
            "id": 11,
            "sport": {
                "id": 2,
                "nom": "Marche",
                "kmH": 4,
                "kcalH": 245,
                "kcalKm": 61.25
            },
            "distance": 100,
            "programme": 1,
            "date": "2018-04-25"
        },
        {
            "@JSONNORECURSION_ACTIVITYID": 7,
            "id": 3,
            "sport": {
                "id": 3,
                "nom": "Cyclisme",
                "kmH": 20,
                "kcalH": 690,
                "kcalKm": 34.5
            },
            "distance": 400,
            "programme": 1,
            "date": "2018-04-25"
        },
        {
            "@JSONNORECURSION_ACTIVITYID": 8,
            "id": 9,
            "sport": {
                "id": 3,
                "nom": "Cyclisme",
                "kmH": 20,
                "kcalH": 690,
                "kcalKm": 34.5
            },
            "distance": 1000,
            "programme": 1,
            "date": "2018-04-23"
        }
    ],
    "realisations": [
        {
            "@JSONNORECURSION_REALISATIONIS": 9,
            "id": 4,
            "distance": 350,
            "date": "2018-04-23",
            "activite": 2
        },
        {
            "@JSONNORECURSION_REALISATIONIS": 10,
            "id": 2,
            "distance": 1200,
            "date": "2018-04-24",
            "activite": 5
        },
        {
            "@JSONNORECURSION_REALISATIONIS": 11,
            "id": 3,
            "distance": 300,
            "date": "2018-04-25",
            "activite": 6
        },
        {
            "@JSONNORECURSION_REALISATIONIS": 12,
            "id": 5,
            "distance": 540,
            "date": "2018-04-25",
            "activite": 4
        },
        {
            "@JSONNORECURSION_REALISATIONIS": 13,
            "id": 1,
            "distance": 300,
            "date": "2018-04-23",
            "activite": 8
        },
        {
            "@JSONNORECURSION_REALISATIONIS": 14,
            "id": 6,
            "distance": 380,
            "date": "2018-04-26",
            "activite": 7
        }
    ]
}
