# TP N°0 : Génération des nombres premiers

**Module :** Algorithmique Avancée et Complexité - Master 1 (IL & RSD)  
**Université :** USTHB - Faculté d'Électronique et d'Informatique  
**Année :** 2025-2026

## Description
Ce programme en C permet de générer automatiquement des fichiers contenant des **nombres premiers** de différentes tailles et répartitions, utiles pour **tester et évaluer des algorithmes de primalité**. Il fournit plusieurs modes de génération pour créer des jeux de données personnalisés et reproductibles.

## Fonctionnalités

### Fichiers générés
1. **Random100.txt** — 100 nombres premiers entre 100,000 et 10,000,000  
2. **Random1000.txt** — 1,000 nombres premiers entre 100,000 et 10,000,000  
3. **Test-1.txt** — 100 nombres premiers dans l’intervalle [1,000, 1,000,000]  
4. **Test-2.txt** — Nombres premiers organisés par nombre de chiffres (configuration personnalisable)  
5. **Test-3.txt** — Nombres premiers à 3, 6, 9 et 12 chiffres (20 par catégorie)

## 💻 Compilation

```bash
gcc -o generateur_primes main.c -lm
./generateur_primes
```

## 📊 Utilisation

### Menu principal :
```text
=================================================
  GÉNÉRATEUR DE JEUX DE DONNÉES - TEST DE PRIMALITÉ
=================================================

1. Générer Random100.txt (100 nombres premiers)
2. Générer Random1000.txt (1000 nombres premiers)
3. Générer Test-1.txt (100 nombres dans [1000, 1000000])
4. Générer Test-2.txt (Structuré par nombre de chiffres - Personnalisé)
5. Générer Test-3.txt (3, 6, 9, 12 chiffres)
6. Générer TOUS les fichiers
0. Quitter
```

### Option 4 — Test-2 Personnalisé
Cette option permet de générer un jeu de données entièrement personnalisé.

#### Questions posées :
- Nombre de catégories : combien de types de longueurs de chiffres différents ?
- Nombres par catégorie : combien de nombres premiers dans chaque catégorie ?
- Chiffres de départ : à partir de combien de chiffres commencer ?

#### Exemple :
```text
Combien de catégories de nombres de chiffres différents voulez-vous ? 4
Combien de nombres premiers voulez-vous par catégorie ? 6
À partir de combien de chiffres voulez-vous commencer ? 2
```
Cela génère : 6 nombres à 2 chiffres, 6 à 3 chiffres, 6 à 4 chiffres et 6 à 5 chiffres.

## Algorithmes Implémentés

### Vérification de primalité
- Test efficace excluant immédiatement les multiples de 2 et 3.
- Itération sur des pas de 6 (vérifie uniquement les nombres de la forme 6k ± 1).
- Complexité : O(√n)

### Génération des nombres premiers
- Génération aléatoire dans un intervalle donné, vérification de primalité jusqu’à obtenir un nombre valide.
- Gestion automatique des bornes et des boucles sécurisées (limite de 100,000 essais).

## Structure des sorties

Chaque fichier généré contient un nombre premier par ligne :

```text
Nombre premier 1
Nombre premier 2
...
Nombre premier N
```

### Exemple : contenu typique de `Test-2.txt`
```text
11
13
17
101
103
107
...
```

## Architecture du code

- `estPremier()` : vérifie si un nombre est premier.  
- `genererPremierAleatoire()` : génère un nombre premier aléatoire dans un intervalle donné.  
- `genererPremierAvecNChiffres()` : produit un premier avec N chiffres exacts.  
- `genererTest1()`, `genererTest2Personnalise()`, `genererTest3()` : gèrent chaque type de scénario de test.  
- `lireFichier()` : lit et affiche le contenu d’un fichier texte ligne par ligne.

## Fichiers produits
| Fichier | Description | Intervalle / Structure |
|----------|--------------|--------------------------|
| Random100.txt | 100 nombres premiers aléatoires | [100,000 – 10,000,000] |
| Random1000.txt | 1,000 nombres premiers aléatoires | [100,000 – 10,000,000] |
| Test-1.txt | 100 nombres premiers intermédiaires | [1,000 – 1,000,000] |
| Test-2.txt | Jeu personnalisé par longueur de chiffres | Définie par l’utilisateur |
| Test-3.txt | 3, 6, 9, 12 chiffres | 20 nombres par catégorie |

## Conseils d’utilisation
- Utilisez la commande `6` pour générer tous les fichiers d’un coup.  
- Les résultats sont enregistrés dans le répertoire du programme sous leurs noms respectifs.  
- Les fichiers peuvent être utilisés pour tester des programmes de primalité ou de cryptographie (RSA, Miller–Rabin, etc.).

## Exemple de session
```text
Votre choix: 1

Génération de Random100.txt...
  Progression: 10/100
  Progression: 20/100
  Progression: 30/100
  Progression: 40/100
  Progression: 50/100
  Progression: 60/100
  Progression: 70/100
  Progression: 80/100
  Progression: 90/100
  Progression: 100/100
✓ Random100.txt créé avec succès!


Voulez-vous afficher le contenu de Random100.txt ? (o/n): o

=== Contenu du fichier Random100.txt ===
1: 8743807
2: 8321543
3: 6386711
4: 4194439
5: 6357667
6: 6232159
7: 4661353
8: 2974759
9: 923851
10: 1129433
11: 8537839
12: 9185149
13: 4992359
14: 9649261
15: 4141691
16: 334403
17: 7377191
18: 9361423
19: 6931193
20: 8675743

--- Appuyez sur Entrée pour continuer ---

```

