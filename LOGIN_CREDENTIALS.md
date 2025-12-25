# 🔐 IDENTIFIANTS DE CONNEXION

## Identifiants par défaut de l'application

**ID Étudiant:** `2021CS042`  
**Mot de passe:** `student123`

---

## Informations du compte de démonstration

**Nom complet:** Sarah Johnson  
**ID Étudiant:** 2021CS042  
**Semestre actuel:** Spring 2024  

### Statistiques:
- **Cours inscrits:** 6 cours
- **Crédits totaux:** 19 crédits
- **Taux de présence:** 92%

### Accès:
- ✅ Emploi du temps complet
- ✅ 6 tâches (1 en retard)
- ✅ 6 cours détaillés
- ✅ 3 livres empruntés
- ✅ Historique de bibliothèque
- ✅ Annonces du campus

---

## Comment se connecter

1. Lancez l'application
2. Attendez que l'écran Splash se termine (2,5 secondes)
3. Sur l'écran de connexion, entrez:
   - **Student ID:** `2021CS042`
   - **Password:** `student123`
4. Appuyez sur "Sign In"
5. Vous serez redirigé vers l'écran Home

---

## En cas d'erreur

Si vous voyez "Invalid student ID or password":
- Vérifiez que vous avez bien entré `2021CS042` (avec les majuscules)
- Vérifiez que le mot de passe est `student123` (tout en minuscules)
- Assurez-vous qu'il n'y a pas d'espaces avant ou après

---

## Fichier de configuration

Les identifiants sont définis dans:
`app/src/main/java/com/example/yearprojectmobile/data/MockData.kt`

```kotlin
const val DEFAULT_STUDENT_ID = "2021CS042"
const val DEFAULT_PASSWORD = "student123"
```

Pour changer les identifiants, modifiez ces valeurs dans MockData.kt

---

**Date de création:** 21 novembre 2025  
**Version de l'app:** 1.0.0

