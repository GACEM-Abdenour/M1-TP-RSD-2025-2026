# M1-TP-RSD-2025-2026

## TP N°2 : Analyse et comparaison des algorithmes de recherche et de détermination du maximum et minimum

**Module** : Algorithmique Avancée et Complexité - Master 1 (IL & RSD)  
**Université** : USTHB - Faculté d'Électronique et d'Informatique  
**Année** : 2025-2026  

**Machine utilisée pour les tests :**

- Processeur : 13th Gen Intel(R) Core(TM) i5-13420H @ 2.10 GHz  
- RAM installée : 8.00 GB (7.70 GB utilisable)  
- Type de système : 64-bit, processeur x64  

---

## Table des matières

1. [Description](#description)  
2. [Fonctionnalités](#fonctionnalités)  
3. [Liste testée](#liste-testée)  
4. [Compilation et exécution](#compilation-et-exécution)  
5. [Mesure des performances](#mesure-des-performances)  
6. [Exemples de données](#exemples-de-données)  
7. [Graphiques](#graphiques)  
8. [Détails algorithmiques](#détails-algorithmiques)  
9. [Rôles et contributions](#rôles-et-contributions)  
10. [Fonctions C utilisées](#fonctions-c-utilisées)  
11. [Lien entre fonctions et tâches](#lien-entre-fonctions-et-tâches)  

---

## Description

Ce TP a pour objectif d'analyser et de comparer plusieurs algorithmes de recherche et de détermination du minimum et du maximum dans des tableaux d'entiers :

- Recherche non triée (Naïf)  
- Recherche triée  
- Recherche dichotomique  
- Algorithmes MaxEtMinA et MaxEtMinB  

Les tests ont été réalisés pour différentes tailles de tableaux, en mesurant le temps d’exécution et le nombre de comparaisons. Les données ont été générées par le programme et stockées dans des fichiers texte, puis lues dans des tables pour analyse.

---

## Fonctionnalités

- Parcours complet des tableaux pour les recherches naïves.  
- Exploitation de l’ordre pour les recherches triées.  
- Division récursive / itérative de l’intervalle pour les recherches dichotomiques.  
- Comparaison de deux stratégies de calcul du max et min (A et B).  
- Collecte et stockage des temps et comparaisons dans des fichiers : `tp2pt1.txt` et `tp2pt2.txt`.  
- Graphiques réalisés à partir de ces fichiers en utilisant Excel.  

---

## Liste testée

Tailles de tableaux utilisées :

int sizes[] = {
100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000,
1000000, 1100000, 1200000, 1300000, 1400000, 1500000, 1600000, 1700000,
1800000, 2000000, 4000000, 6000000, 8000000
};


---

## Compilation et exécution

Pour compiler le programme sous Linux :

```
gcc -o tp2 tp2.c

./tp2
```



Le programme propose alors un menu interactif pour générer les fichiers de données et lancer les benchmarks.

---

## Mesure des performances

- Temps : en secondes, mesuré à l’aide de `clock()` et converti en secondes via `CLOCKS_PER_SEC`.  
- Comparaisons : nombre de comparaisons réalisées par l’algorithme (théorique et observé).  
- Résultats stockés dans des fichiers et importés dans Excel pour génération de tableaux et graphiques.  

---

## Exemples de données

### TP2 Partie 1 : Non-Trié / Trié / Dichotomie (best et worst cases)

| N       | Non-Trié + (s) | Non-Trié − (s) | Trié + (s) | Trié − (s) | Dicho + (s) | Dicho − (s) |
| ------- | -------------- | -------------- | ---------- | ---------- | ----------- | ----------- |
| 100000  | 0.000000       | 0.000174       | 0.000000   | 0.000187   | 0.000001    | 0.000001    |
| 200000  | 0.000000       | 0.000230       | 0.000000   | 0.000414   | 0.000001    | 0.000001    |
| 400000  | 0.000000       | 0.000518       | 0.000000   | 0.000961   | 0.000001    | 0.000000    |
| 600000  | 0.000001       | 0.000739       | 0.000000   | 0.001251   | 0.000001    | 0.000001    |
| 800000  | 0.000000       | 0.001434       | 0.000000   | 0.002169   | 0.000000    | 0.000001    |
| 1000000 | 0.000001       | 0.002234       | 0.000001   | 0.002718   | 0.000001    | 0.000001    |
| 2000000 | 0.000001       | 0.002827       | 0.000001   | 0.004550   | 0.000001    | 0.000001    |
| 4000000 | 0.000001       | 0.005915       | 0.000000   | 0.007500   | 0.000001    | 0.000001    |
| 6000000 | 0.000000       | 0.009176       | 0.000000   | 0.010153   | 0.000001    | 0.000001    |
| 8000000 | 0.000000       | 0.011319       | 0.000000   | 0.014508   | 0.000001    | 0.000001    |

### TP2 Partie 2 : MaxEtMinA vs MaxEtMinB

| N       | Comparaisons A | Comparaisons B | Temps A (s) | Temps B (s) |
| ------- | -------------- | -------------- | ----------- | ----------- |
| 100000  | 199998         | 149998         | 0.000120    | 0.000332    |
| 200000  | 399998         | 299998         | 0.000234    | 0.000699    |
| 300000  | 599998         | 449998         | 0.000603    | 0.001215    |
| 400000  | 799998         | 599998         | 0.000469    | 0.001327    |
| 500000  | 999998         | 749998         | 0.000584    | 0.001929    |
| 600000  | 1199998        | 899998         | 0.000851    | 0.002356    |
| 700000  | 1399998        | 1049998        | 0.000933    | 0.002330    |
| 800000  | 1599998        | 1199998        | 0.001311    | 0.003090    |
| 900000  | 1799998        | 1349998        | 0.001329    | 0.003404    |
| 1000000 | 1999998        | 1499998        | 0.001167    | 0.003748    |
| 2000000 | 3999998        | 2999998        | 0.002863    | 0.006940    |
| 4000000 | 7999998        | 5999998        | 0.005816    | 0.013533    |
| 6000000 | 11999998       | 8999998        | 0.010562    | 0.020386    |
| 8000000 | 15999998       | 11999998       | 0.010600    | 0.027189    |

---

## Graphiques

Ordre des graphiques réalisés dans Excel :

1. Triés : Worst vs Best cases  
3. Non triés : Worst vs Best cases  
5. Dichotomie : Worst vs Best cases  
7. Comparaison globale Worst cases  
9. Comparaison globale Best cases  
11. MaxEtMin Comparaisons A vs B  
13. MaxEtMin Temps A vs B  

Les axes :

- Axe X : Taille du tableau (N).  
- Axe Y : Temps (s) ou nombre de comparaisons.  

Les graphes permettent de visualiser la croissance temporelle et le nombre de comparaisons pour chaque algorithme.

---

## Détails algorithmiques

### Algorithmes Non-Trié

- Parcourt tout le tableau pour rechercher un élément donné.  
- Comparaisons : jusqu’à N dans le pire cas, 1 dans le meilleur cas.  
- Temps : proportionnel à la taille du tableau.  

Complexité :

- Meilleur cas : O(1)  
- Pire cas : O(N)  
- Cas moyen : O(N)  

### Algorithmes Triés

- Exploitent l'ordre croissant pour limiter les comparaisons lors de la recherche.  
- Comparaisons : jusqu’à N dans le pire cas si l’élément est absent, moins si trouvé tôt.  
- Temps : proportionnel au nombre d’éléments visités jusqu’à l’élément recherché.  

Complexité :

- Meilleur cas : O(1)  
- Pire cas : O(N)  
- Cas moyen : O(N/2) ≈ O(N)  

### Algorithme Dichotomique

- Recherche itérative en divisant le tableau trié par 2 à chaque étape.  
- Comparaisons : logarithmiques par rapport à N.  

Complexité :

- Meilleur cas : O(1)  
- Pire cas : O(log N)  
- Cas moyen : O(log N)  

### MaxEtMinA vs MaxEtMinB

- Détermination simultanée du maximum et du minimum.  
- MaxEtMinA : compare chaque élément individuellement au max et au min courants.  
- MaxEtMinB : compare les éléments par paires pour réduire le nombre de comparaisons.  

Complexité théorique :

- MaxEtMinA :  
  - Comparaisons : \(2N - 2\)  
  - Temps : O(N)  
- MaxEtMinB :  
  - Comparaisons : \(\lfloor 3N/2 \rfloor - 2\)  
  - Temps : O(N)  

---

## Rôles et Contributions

- Code et développement : ABDERRAHIM Sidali 222231402319  
- Rédaction du rapport : BELDJERDI Tayeb Yasser 222231404112  
- Tests et évaluations : GACEM Abdenour 222231640608  
- Collecte des données : DALIL Faycal 222231658510  

---

## Fonctions C utilisées

Cette section liste les principales fonctions C du projet, avec leur rôle.

### Fonctions de recherche

- `bool naive(int* arr, int n, int value);`  
  Recherche séquentielle dans un tableau non trié, utilisée pour les cas Non-Trié (best et worst).  

- `bool dicho(int* arr, int n, int value);`  
  Recherche dichotomique dans un tableau trié, utilisée pour les scénarios Dicho + et Dicho −.  

- `bool seq_trie(int* arr, int n, int value);`  
  Recherche séquentielle optimisée pour un tableau trié, avec arrêt dès que `arr[i] > value`.  

### Fonctions de mesure du temps

- `double get_time(clock_t start, clock_t end);`  
  Convertit la différence de temps processeur en secondes à l’aide de `CLOCKS_PER_SEC`.  

- `double mesurer_temps(bool (*search)(int*, int, int), int* arr, int n, int value, int reps);`  
  Mesure le temps moyen d’un algorithme de recherche donné (pointeur de fonction) sur plusieurs répétitions.  

### Fonctions de génération et collecte de données

- `void generer_fichiers(int n);`  
  Génère deux fichiers texte :  
  - `elements_tries.txt` contenant un tableau trié de taille n.  
  - `elements_non_tries.txt` contenant les mêmes éléments mélangés aléatoirement.  

- `typedef struct { int* tableau; int taille; bool est_trie; } TableauInfo;`  
  Structure pour encapsuler un tableau, sa taille et un indicateur “trié / non trié”.  

- `TableauInfo* creer_tableaux_depuis_fichiers();`  
  Lit les fichiers de données et crée 44 tableaux en mémoire (22 triés + 22 non triés) correspondant aux différentes tailles N.  

- `void liberer_tableaux(TableauInfo* tableaux);`  
  Libère la mémoire allouée pour les 44 tableaux.  

### Fonction de benchmarks

- `void run_benchmarks(TableauInfo* tableaux);`  
  - Pour chaque taille N, sélectionne les tableaux triés et non triés.  
  - Mesure les temps best et worst pour :  
    - Non Trié (naive)  
    - Trié séquentiel (seq_trie)  
    - Dichotomie (dicho)  
  - Affiche les résultats sous forme de tableau formaté, prêt à être exporté vers des fichiers texte ou Excel.  

### Fonction principale

- `int main();`  
  - Gère un menu interactif :  
    1) Générer les fichiers de données (8 000 000 éléments)  
    2) Créer les tableaux depuis les fichiers et exécuter les benchmarks  
    3) Quitter  
  - Coordonne l’appel aux fonctions de génération, de création des tableaux et de benchmarks.  

---

## Lien entre fonctions et tâches

Cette section relie les tâches du projet aux fonctions concrètes utilisées dans le code.

### Code et développement

Fonctions principalement impliquées :

- `naive`  
- `dicho`  
- `seq_trie`  
- `generer_fichiers`  
- `creer_tableaux_depuis_fichiers`  
- `run_benchmarks`  
- `liberer_tableaux`  
- `main`  

Ces fonctions constituent la logique principale du TP (implémentation des algorithmes, gestion des données et organisation du programme).

### Rédaction du rapport

La rédaction du rapport s’appuie sur :

- La compréhension des fonctions `naive`, `dicho`, `seq_trie` (comportement et complexité).  
- Les résultats fournis par `run_benchmarks` (tableau de temps best/worst pour chaque N).  
- La description des structures de données `TableauInfo` et de la génération de données via `generer_fichiers` et `creer_tableaux_depuis_fichiers`.  

### Tests et évaluations

Les tests et évaluations s’appuient sur :

- `run_benchmarks` pour lancer systématiquement les mesures sur toutes les tailles de tableaux.  
- `mesurer_temps` pour obtenir un temps moyen plus fiable sur plusieurs répétitions `reps`.  
- Les différents scénarios testés dans `run_benchmarks` :  
  - Best case (élément au début, au milieu, etc.).  
  - Worst case (élément absent ou en dernière position, puis sélection du pire temps).  

### Collecte des données

La collecte des données expérimentales repose sur :

- `generer_fichiers` pour produire des jeux de données cohérents et reproductibles.  
- `creer_tableaux_depuis_fichiers` pour reconstruire en mémoire les tableaux triés et non triés correspondant aux différentes tailles N.  
- `run_benchmarks` pour afficher/écrire les temps dans un format tabulaire pouvant être récupéré dans des fichiers (`tp2pt1.txt`, `tp2pt2.txt`) et ensuite importé dans Excel.  

---

**Fin du rapport**
