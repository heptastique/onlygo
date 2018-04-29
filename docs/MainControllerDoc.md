#Points d'accès REST
#`MainController`
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
