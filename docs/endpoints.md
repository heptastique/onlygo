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