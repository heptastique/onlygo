# Points d'accès REST 
#`Utilisateur`

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

### `PUT /user/objectif` :key:
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
    "distance":10.0
}
```


### `GET /user/objectif` :key:
Renvoie l'objectif Hebdomadaire de l'utilisateur connecté.

#### Réponse

```
Status: 200 OK
```

```json
{
    "distance":10.0
}
```

### `GET /user/progression` :key:
Renvoie le pourcentage de realisation de l'objectif d'utilisateur

#### Réponse

```
Status: 200 OK
```

```json
{
    "progression":88.0
}
```


### `GET /user/realisation` :key:
Renvoie list des realisations  d'utilisateur

#### Réponse

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
### `GET /user/location` :key:
Renvoie la localisation de preference d'utilisateur

#### Réponse

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
### `PUT /user/location` :key:
Ajout de localisation de preference 
### Requête
```json
{
    "x":45.7824519391364728,
    "y":3.4870780 
} 
```

#### Réponse

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
    "programmes":[{
                    "@JSONNORECURSION_PROGRAMMEID":2,
                    "id":500,"activites":[]
                    ,"realisations":[]
                  }],
    "location":   {
                    "id":3,
                    "x":45.78245193913647,
                    "y":3.487078
                  }
 }
```
