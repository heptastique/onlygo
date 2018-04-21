# Authentification

Les clients sont authentifiés avec JWT (JSON Web Tokens).

Pour en obtenir un, il faut effectuer une requête `POST` à `/auth` avec le corps suivant :
```json
{
	"username": "user",
	"password": "password"
}
```

On reçoit alors la réponse:
```json
{
    "token": "montoken"
}
```

Toutes les requêtes vers les endpoints protégés doivent contenir le header :
```
Bearer: "montoken"
```

Dans Postman, cela est possible en sélectionnant `Bearer Token` dans la liste des types d'autorisation et de coller le token dans le champ dédié.