# Nouvelles Fonctionnalités Ajoutées

## 📱 Fonctionnalités Implémentées

### 1. 👤 Écran de Profil
- **Accès** : Cliquer sur l'icône de profil dans la barre supérieure
- **Contenu** :
  - Informations de l'étudiant (nom, ID, spécialité)
  - Résumé académique (GPA, Crédits, Cours)
  - Année et semestre actuels
  - Date d'inscription
  - Informations de contact (email, téléphone, département)
  - Récompenses et distinctions
  - Options de paramètres (apparence, déconnexion)

### 2. 📷 Scanner QR Code
- **Accès** : Bouton "Scan QR" dans la section Quick Actions de l'écran d'accueil
- **Fonctionnalités** :
  - Scanner QR code pour l'assiduité
  - Option alternative : saisie manuelle d'un code à 6 chiffres
  - Dialog de confirmation avec détails du cours après scan réussi
  - Affichage des informations : cours, salle, date, heure, instructeur

### 3. 🗺️ Carte du Campus
- **Accès** : Bouton "Campus Map" dans la section Quick Actions de l'écran d'accueil
- **Fonctionnalités** :
  - Vue de la carte interactive du campus (version simplifiée pour démo)
  - Marqueurs des bâtiments principaux :
    - Computer Science Building (CS)
    - Mathematics Building (MATH)
    - Central Library (LIB)
    - Student Center (SC)
    - Engineering Building (ENG)
    - Campus Cafe (CAFE)
  - Légende des types de bâtiments
  - Liste détaillée des bâtiments avec :
    - Nom et code du bâtiment
    - Description
    - Nombre d'étages
    - Installations disponibles
  - Dialog d'information détaillée au clic sur un bâtiment

### 4. 🌓 Thème Sombre/Clair
- **Accès** : Icône dans la barre supérieure de l'écran d'accueil
- **Fonctionnalités** :
  - Basculer entre thème clair et thème sombre
  - L'icône change selon le thème actuel (soleil/lune)
  - Le thème s'applique à toute l'application immédiatement

## 🎨 Design

Toutes les nouvelles fonctionnalités suivent le design Material 3 avec :
- Palette de couleurs cohérente (bleu #3557D5 comme couleur principale)
- Coins arrondis pour les cartes et boutons
- Animations et transitions fluides
- Support du thème sombre complet

## 📂 Fichiers Créés/Modifiés

### Nouveaux Fichiers
- `ProfileScreen.kt` - Écran de profil de l'étudiant
- `ScanQRScreen.kt` - Écran de scan QR code et saisie manuelle
- `CampusMapScreenNew.kt` - Écran de carte du campus
- `ThemeViewModel.kt` - ViewModel pour gérer le thème

### Fichiers Modifiés
- `MainActivity.kt` - Intégration du ThemeViewModel
- `MainScreen.kt` - Navigation vers les nouveaux écrans
- `HomeScreen.kt` - Ajout des callbacks pour Quick Actions
- `Screen.kt` - Ajout des nouvelles routes de navigation

## 🚀 Utilisation

1. **Profil** : Cliquez sur l'icône de profil en haut à droite de l'écran d'accueil
2. **Scanner QR** : Cliquez sur le bouton "Scan QR" dans Quick Actions
3. **Carte Campus** : Cliquez sur le bouton "Campus Map" dans Quick Actions
4. **Changer Thème** : Cliquez sur l'icône soleil/lune en haut à droite

## 📝 Notes

- Le scanner QR est simulé pour la démonstration
- La carte du campus est une version simplifiée avec des marqueurs de bâtiments
- Les données affichées sont des données de démonstration (MockData)

## 🔮 Améliorations Futures

- Intégration de la caméra réelle pour le scanner QR
- Carte interactive réelle avec Google Maps ou OpenStreetMap
- Sauvegarde de la préférence de thème
- Ajout d'autres paramètres de personnalisation du profil

