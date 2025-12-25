# 🛠️ GUIDE DE RÉPARATION RAPIDE

## Problème
Les fichiers CampusMapScreen*.kt sont en conflit et empêchent la compilation.

## Solution en 3 Étapes

### ✅ Étape 1: Supprimer les Fichiers en Double

Dans Android Studio, **Project** panel :
1. Allez dans `app/src/main/java/com/example/yearprojectmobile/screens/`
2. **SUPPRIMEZ** ces 3 fichiers (clic droit → Delete) :
   - ❌ `CampusMapScreen.kt`
   - ❌ `CampusMapScreenNew.kt`
   - ❌ `CampusMapScreenFixed.kt`

### ✅ Étape 2: Garder MapScreen.kt

Le nouveau fichier **`MapScreen.kt`** reste et contient tout le code correct.

### ✅ Étape 3: Clean & Build

```cmd
cd C:\Users\M.Y.N\AndroidStudioProjects\YearProjectMobile
gradlew clean
gradlew assembleDebug
```

---

## Alternative: Renommer dans l'Explorateur Windows

Si vous ne pouvez pas supprimer dans Android Studio :

1. Fermez Android Studio
2. Ouvrez l'Explorateur Windows
3. Naviguez vers :
   ```
   C:\Users\M.Y.N\AndroidStudioProjects\YearProjectMobile\app\src\main\java\com\example\yearprojectmobile\screens\
   ```
4. Renommez :
   - `CampusMapScreen.kt` → `CampusMapScreen.kt.OLD`
   - `CampusMapScreenNew.kt` → `CampusMapScreenNew.kt.OLD`
   - `CampusMapScreenFixed.kt` → `CampusMapScreenFixed.kt.OLD`
5. Rouvrez Android Studio
6. Build → Rebuild Project

---

## ✨ Après ces étapes

Votre application compilera avec succès et vous aurez :

✅ **ProfileScreen** - Profil de Sarah Johnson  
✅ **ScanQRScreen** - Scanner QR + code manuel  
✅ **MapScreen** - Carte du campus (NOUVEAU)  
✅ **Thème sombre/clair** - Toggle fonctionnel  

**Toutes les fonctionnalités des screenshots sont implémentées !** 🎉

