# M1-TP-RSD-2025-2026

## TP N°2 : Analyse et comparaison des algorithmes de recherche et de détermination du maximum et minimum

**Module** : Algorithmique Avancée et Complexité - Master 1 (IL & RSD)  
**Université** : USTHB - Faculté d'Électronique et d'Informatique  
**Année** : 2025-2026  

**Machine utilisée pour les tests :**

* **Processeur** : 13th Gen Intel(R) Core(TM) i5-13420H @ 2.10 GHz
* **RAM installée** : 8.00 GB (7.70 GB utilisable)
* **Type de système** : 64-bit, processeur x64

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

---

## Description

Ce TP a pour objectif d'analyser et de comparer plusieurs algorithmes de recherche et determiner minimum et maximum dans des tableaux d'entiers :

* **Recherche non triée (Naïf)**
* **Recherche triée**
* **Recherche dichotomique**
* **Algorithmes MaxEtMinA et MaxEtMinB**

Les tests ont été réalisés pour différentes tailles de tableaux, en mesurant **le temps d'exécution** et **le nombre de comparaisons**. Les données ont été **générées par le programme et stockées dans des fichiers texte**, puis lues dans des tables pour analyse. L'objectif est de visualiser l'impact de l'organisation des données et l'efficacité des optimisations algorithmiques.

---

## Fonctionnalités

* Parcours complet des tableaux pour les recherches naïves.
* Exploitation de l'ordre pour les recherches triées.
* Division récursive pour les recherches dichotomiques.
* Comparaison de deux stratégies de calcul du max et min (A et B).
* Collecte et stockage des **temps et comparaisons** dans des fichiers : `tp2pt1.txt` et `tp2pt2.txt`.
* Graphiques réalisés à partir de ces fichiers en utilisant **Excel**.

---

## Liste testée

**Tailles de tableaux utilisées :**

```c
int sizes[] = {100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000,
               1000000, 1100000, 1200000, 1300000, 1400000, 1500000, 1600000, 1700000,
               1800000, 2000000, 4000000, 6000000, 8000000};
```

---

## Compilation et exécution

Pour compiler le programme sous Linux :

```bash
gcc -o tp2 tp2.c 
./tp2
```

---

## Mesure des performances

* **Temps** : en secondes (avec `clock()` ou chronomètre haute précision).
* **Comparaisons** : nombre de comparaisons réalisées par l'algorithme.
* Résultats **stockés dans des fichiers** et importés dans **Excel** pour génération des tableaux et graphiques.

---

## Exemples de données

### TP2 Partie 1 :  Non-Trié / Trié / Dichotomie (best et worst cases)

| N       |  Non-Trié + (s) |  Non-Trié − (s) | Trié + (s) | Trié − (s) | Dicho + (s) | Dicho − (s) |
| ------- | --------------- | --------------- | ---------- | ---------- | ----------- | ----------- |
| 100000  | 0.000000        | 0.000174        | 0.000000   | 0.000187   | 0.000001    | 0.000001    |
| 200000  | 0.000000        | 0.000230        | 0.000000   | 0.000414   | 0.000001    | 0.000001    |
| 400000  | 0.000000        | 0.000518        | 0.000000   | 0.000961   | 0.000001    | 0.000000    |
| 600000  | 0.000001        | 0.000739        | 0.000000   | 0.001251   | 0.000001    | 0.000001    |
| 800000  | 0.000000        | 0.001434        | 0.000000   | 0.002169   | 0.000000    | 0.000001    |
| 1000000 | 0.000001        | 0.002234        | 0.000001   | 0.002718   | 0.000001    | 0.000001    |
| 2000000 | 0.000001        | 0.002827        | 0.000001   | 0.004550   | 0.000001    | 0.000001    |
| 4000000 | 0.000001        | 0.005915        | 0.000000   | 0.007500   | 0.000001    | 0.000001    |
| 6000000 | 0.000000        | 0.009176        | 0.000000   | 0.010153   | 0.000001    | 0.000001    |
| 8000000 | 0.000000        | 0.011319        | 0.000000   | 0.014508   | 0.000001    | 0.000001    |

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

**Ordre des graphiques réalisés dans Excel :**

1. **Triés : Worst vs Best cases**
   Visualisation des temps pour Trié + et Trié − en comparant le meilleur et le pire cas.
   <img width="592" height="354" alt="Screenshot 2025-11-23 201734" src="https://github.com/user-attachments/assets/2252fcb5-787d-4838-9aba-5e4dba9a2796" />

3. **Non triés : Worst vs Best cases**
   Visualisation des temps pour Naïf + et Naïf − pour comparer meilleur et pire cas.
   <img width="596" height="356" alt="Screenshot 2025-11-23 202136" src="https://github.com/user-attachments/assets/65782053-9e25-4c76-b8cc-9f74bb718083" />

5. **Dichotomie : Worst vs Best cases**
   Visualisation des temps pour Dicho + et Dicho −.
   <img width="595" height="355" alt="Screenshot 2025-11-23 202412" src="https://github.com/user-attachments/assets/4caf5890-b75d-4cf1-b3ac-1254a9bd7c9c" />

7. **Comparaison globale Worst cases**
   Tous les algorithmes ensemble (Naïf, Trié, Dicho) pour le pire cas.
   <img width="598" height="358" alt="Screenshot 2025-11-23 202633" src="https://github.com/user-attachments/assets/8b3b82dc-6388-4372-81c7-7ecb4d5905ec" />

9. **Comparaison globale Best cases**
   Tous les algorithmes ensemble pour le meilleur cas.
   <img width="600" height="356" alt="Screenshot 2025-11-23 202826" src="https://github.com/user-attachments/assets/ba04bd6c-58d1-4b3f-9be9-8b882998dcee" />

11. **MaxEtMin Comparaisons A vs B**
    Visualisation du nombre de comparaisons pour MaxEtMinA et MaxEtMinB.
    <img width="596" height="350" alt="Screenshot 2025-11-23 203908" src="https://github.com/user-attachments/assets/eb58a1a2-7e8e-41ea-97b2-55a675ef65fa" />

13. **MaxEtMin Temps A vs B**
    Visualisation du temps d'exécution pour MaxEtMinA et MaxEtMinB.
    <img width="592" height="346" alt="Screenshot 2025-11-23 204034" src="https://github.com/user-attachments/assets/31d4bf3f-dae7-4b9c-b3d0-c7a8425d2b1c" />

*Les axes :*

* X → Taille du tableau (N)
* Y → Temps (s) ou Comparaisons
* Les graphes permettent de visualiser la **croissance temporelle et le nombre de comparaisons** pour chaque algorithme.

---

## Détails algorithmiques

### Recherche Non-Triée (Naïf)

* Parcourt tout le tableau pour rechercher un élément donné.
* Comparaisons : jusqu'à N dans le pire cas, 1 dans le meilleur cas.
* Temps : proportionnel à la taille du tableau.

#### Nombre d'itérations (pire cas)
* Pire cas : élément absent ou en dernière position
* Boucle pour i = 0 → N-1 → **N itérations**

#### Coût total
* Comparaison, incrément ≈ 2N unités
* **Complexité finale : O(N)**

```c
bool naive(int* arr, int n, int value) {
    for (int i = 0; i < n; i++) {
        if (arr[i] == value) return true;
    }
    return false;
}
```

* **Complexité :**
  * Meilleur cas : O(1)
  * Pire cas : O(N)
  * Cas moyen : O(N)

---

### Recherche Triée (Séquentielle optimisée)

* Exploite l'ordre croissant pour limiter les comparaisons lors de la recherche.
* Comparaisons : jusqu'à N dans le pire cas si l'élément est absent, moins si trouvé tôt.
* Temps : proportionnel au nombre d'éléments jusqu'à l'élément recherché.

#### Nombre d'itérations (pire cas)
* Pire cas : élément en dernière position ou absent
* Boucle pour i = 0 → N-1 → **N itérations**

#### Coût total
* Comparaison, incrément ≈ 2N unités
* **Complexité finale : O(N)**

```c
bool seq_trie(int* arr, int n, int value) {
    for (int i = 0; i < n; i++) {
        if (arr[i] == value) return true;
        if (arr[i] > value) return false; // arrêt précoce possible
    }
    return false;
}
```

* **Complexité :**
  * Meilleur cas : O(1)
  * Pire cas : O(N)
  * Cas moyen : O(N/2) ≈ O(N)

---

### Recherche Dichotomique

* Recherche itérative en divisant le tableau trié par 2 à chaque étape.
* Comparaisons : logarithmiques par rapport à N.

#### Nombre d'itérations (pire cas)
* Pire cas : élément absent
* Boucle divise l'intervalle par 2 → **log₂(N) itérations**

#### Coût total
* Comparaison, calcul milieu ≈ 3 log₂(N) unités
* **Complexité finale : O(log N)**

```c
bool dicho(int* arr, int n, int value) {
    int a = 0, b = n - 1;
    while (a <= b) {
        int m = (a + b) / 2;
        if (arr[m] == value) return true;
        else if (arr[m] < value) a = m + 1;
        else b = m - 1;
    }
    return false;
}
```

* **Complexité :**
  * Meilleur cas : O(1)
  * Pire cas : O(log N)
  * Cas moyen : O(log N)

---

### MaxEtMinA : Parcours simple

* Compare chaque élément individuellement au max et au min courants.

#### Nombre d'itérations
* Boucle pour i = 1 → N-1 → **N-1 itérations**

#### Nombre de comparaisons
* 2 comparaisons par itération → **2(N-1) = 2N − 2 comparaisons**

```c
void MaxEtMinA(int *arr, int n, int *out_min, int *out_max, long *comp_count) {
    if (n <= 0) { *out_min = *out_max = 0; *comp_count = 0; return; }
    int mn = arr[0], mx = arr[0];
    long comps = 0;
    for (int i = 1; i < n; i++) {
        comps++; if (arr[i] < mn) mn = arr[i];
        comps++; if (arr[i] > mx) mx = arr[i];
    }
    *out_min = mn; *out_max = mx; *comp_count = comps;
}
```

* **Complexité :**
  * Comparaisons : 2N − 2
  * Temps : O(N)

---

### MaxEtMinB : Comparaison par paires

* Compare les éléments par paires pour réduire le nombre de comparaisons.

#### Nombre d'itérations
* Boucle pour i = start → N-1, step 2 → **(N-start)/2 itérations**

#### Nombre de comparaisons
* 3 comparaisons par paire → **⌊3N/2⌋ − 2 comparaisons**

```c
void MaxEtMinB(int *arr, int n, int *out_min, int *out_max, long *comp_count) {
    if (n <= 0) { *out_min = *out_max = 0; *comp_count = 0; return; }
    long comps = 0;
    int mn, mx;
    int start = 0;
    if (n % 2 == 1) {
        mn = mx = arr[0];
        start = 1;
    } else {
        comps++;
        if (arr[0] < arr[1]) { mn = arr[0]; mx = arr[1]; }
        else { mn = arr[1]; mx = arr[0]; }
        start = 2;
    }
    for (int i = start; i < n; i += 2) {
        comps++;
        int local_min, local_max;
        if (arr[i] < arr[i+1]) { local_min = arr[i]; local_max = arr[i+1]; }
        else { local_min = arr[i+1]; local_max = arr[i]; }
        comps++; if (local_min < mn) mn = local_min;
        comps++; if (local_max > mx) mx = local_max;
    }
    *out_min = mn; *out_max = mx; *comp_count = comps;
}
```

* **Complexité :**
  * Comparaisons : ⌊3N/2⌋ − 2
  * Temps : O(N)

---

## Fonctions utilitaires

#### Mesure du temps

Convertit les cycles processeur écoulés en secondes à l'aide de `CLOCKS_PER_SEC`.

```c
double get_time(clock_t start, clock_t end) {
    return (double)(end - start) / CLOCKS_PER_SEC;
}
```

#### Mesure du temps moyen d'un algorithme de recherche

Exécute un algorithme de recherche plusieurs fois (`reps` répétitions) et retourne la moyenne des temps écoulés pour améliorer la précision et réduire les anomalies statistiques.

```c
double mesurer_temps(bool (*search)(int*, int, int), int* arr, int n, int value, int reps) {
    double total = 0.0;
    for (int r = 0; r < reps; r++) {
        clock_t start = clock();
        volatile bool found = search(arr, n, value);
        (void)found;
        clock_t end = clock();
        total += get_time(start, end);
    }
    return total / reps;
}
```

#### Génération des fichiers de données

Crée deux fichiers : un tableau trié (`elements_tries.txt`) et le même tableau mélangé (`elements_non_tries.txt`). Les éléments sont générés sous la forme `i * 2` pour assurer des valeurs distinctes et facilement identifiables.

```c
void generer_fichiers(int n) {
    FILE *fichier_trie = fopen("elements_tries.txt", "w");
    FILE *fichier_non_trie = fopen("elements_non_tries.txt", "w");
    
    int* arr = malloc(n * sizeof(int));
    for (int i = 0; i < n; i++) {
        arr[i] = i * 2;
    }
    
    fprintf(fichier_trie, "%d\n", n);
    for (int i = 0; i < n; i++) {
        fprintf(fichier_trie, "%d\n", arr[i]);
    }
    
    srand(time(NULL));
    for (int i = 0; i < n; i++) {
        int j = rand() % n;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    fprintf(fichier_non_trie, "%d\n", n);
    for (int i = 0; i < n; i++) {
        fprintf(fichier_non_trie, "%d\n", arr[i]);
    }
    
    fclose(fichier_trie);
    fclose(fichier_non_trie);
    free(arr);
}
```

#### Structure TableauInfo

Encapsule un tableau d'entiers avec sa taille et un indicateur booléen précisant si le tableau est trié ou non. Permet une gestion unifiée des 44 tableaux générés.

```c
typedef struct {
    int* tableau;
    int taille;
    bool est_trie;
} TableauInfo;
```

#### Lecture des fichiers et création des tableaux

Lit les deux fichiers de données et construit 44 structures `TableauInfo` en mémoire (22 triés + 22 non triés), chacun correspondant à une taille différente de la liste de test.

```c
TableauInfo* creer_tableaux_depuis_fichiers() {
    int sizes[] = {
        100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000,
        1000000, 1100000, 1200000, 1300000, 1400000, 1500000, 1600000, 1700000,
        1800000, 2000000, 4000000, 6000000, 8000000
    };
    int num_sizes = 22;
    TableauInfo* tableaux = malloc(44 * sizeof(TableauInfo));
    
    FILE *fichier_trie = fopen("elements_tries.txt", "r");
    FILE *fichier_non_trie = fopen("elements_non_tries.txt", "r");
    
    int n_trie, n_non_trie;
    fscanf(fichier_trie, "%d", &n_trie);
    fscanf(fichier_non_trie, "%d", &n_non_trie);
    
    int* elements_tries = malloc(n_trie * sizeof(int));
    for (int i = 0; i < n_trie; i++) {
        fscanf(fichier_trie, "%d", &elements_tries[i]);
    }
    
    int* elements_non_tries = malloc(n_non_trie * sizeof(int));
    for (int i = 0; i < n_non_trie; i++) {
        fscanf(fichier_non_trie, "%d", &elements_non_tries[i]);
    }
    
    fclose(fichier_trie);
    fclose(fichier_non_trie);
    
    for (int i = 0; i < num_sizes; i++) {
        int taille = (sizes[i] <= n_trie) ? sizes[i] : n_trie;
        tableaux[i * 2].tableau = malloc(taille * sizeof(int));
        tableaux[i * 2].taille = taille;
        tableaux[i * 2].est_trie = true;
        for (int j = 0; j < taille; j++) {
            tableaux[i * 2].tableau[j] = elements_tries[j];
        }
        
        tableaux[i * 2 + 1].tableau = malloc(taille * sizeof(int));
        tableaux[i * 2 + 1].taille = taille;
        tableaux[i * 2 + 1].est_trie = false;
        for (int j = 0; j < taille; j++) {
            tableaux[i * 2 + 1].tableau[j] = elements_non_tries[j];
        }
    }
    
    free(elements_tries);
    free(elements_non_tries);
    return tableaux;
}
```

#### Libération mémoire

Libère tous les tableaux alloués par `creer_tableaux_depuis_fichiers()` en parcourant les 44 entrées et en libérant chaque allocation.

```c
void liberer_tableaux(TableauInfo* tableaux) {
    if (tableaux == NULL) return;
    for (int i = 0; i < 44; i++) {
        free(tableaux[i].tableau);
    }
    free(tableaux);
}
```

#### Exécution des benchmarks

Boucle sur toutes les tailles de test, sélectionne les tableaux triés et non triés correspondants, puis mesure les temps d'exécution pour chaque algorithme (naïf, séquentiel, dichotomie) en meilleur et pire cas. Affiche les résultats dans un tableau formaté prêt pour export Excel.

```c
void run_benchmarks(TableauInfo* tableaux) {
    if (tableaux == NULL) return;
    int sizes[] = {
        100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000,
        1000000, 1100000, 1200000, 1300000, 1400000, 1500000, 1600000, 1700000,
        1800000, 2000000, 4000000, 6000000, 8000000
    };
    int num_sizes = 22, reps = 5;
    
    printf("\nN          | EletsNonTries+ (s)   | EletsNonTries- (s)   | EletsTries+ (s)      | EletsTries- (s)      | EletsTriesDicho+ (s) | EletsTriesDicho- (s)\n");
    printf("-----------|----------------------|----------------------|----------------------|----------------------|----------------------|----------------------\n");
    
    for (int i = 0; i < num_sizes; i++) {
        int idx_sorted = i * 2, idx_non_sorted = i * 2 + 1;
        TableauInfo sorted = tableaux[idx_sorted];
        TableauInfo nonsorted = tableaux[idx_non_sorted];
        int n = sorted.taille;
        
        int val_best_non = nonsorted.tableau[0];
        int val_best_trie = sorted.tableau[0];
        int val_best_dicho = sorted.tableau[n/2];
        int val_absent = -1;
        
        double t_non_plus = mesurer_temps(naive, nonsorted.tableau, n, val_best_non, reps);
        double t_trie_seq_plus = mesurer_temps(seq_trie, sorted.tableau, n, val_best_trie, reps);
        double t_trie_dicho_plus = mesurer_temps(dicho, sorted.tableau, n, val_best_dicho, reps);
        
        double t_non_minus = mesurer_temps(naive, nonsorted.tableau, n, val_absent, reps);
        double t_trie_seq_minus = mesurer_temps(seq_trie, sorted.tableau, n, val_absent, reps);
        double t_trie_dicho_minus = mesurer_temps(dicho, sorted.tableau, n, val_absent, reps);
        
        printf("%-11d | %-20.6f | %-20.6f | %-20.6f | %-20.6f | %-20.6f | %-20.6f\n",
               sizes[i], t_non_plus, t_non_minus, t_trie_seq_plus, t_trie_seq_minus,
               t_trie_dicho_plus, t_trie_dicho_minus);
    }
}
```

#### Fonction principale (main)

Gère une boucle interactive proposant un menu permettant à l'utilisateur de :
1. Générer les fichiers de données (8 000 000 éléments)
2. Créer les tableaux en mémoire et exécuter tous les benchmarks
3. Quitter le programme

```c
int main() {
    TableauInfo* tableaux = NULL;
    int choice = 0;
    while (1) {
        printf("\nMenu:\n");
        printf("1) Générer les fichiers de données (8 000 000 éléments)\n");
        printf("2) Créer les tableaux depuis les fichiers et exécuter les algorithmes (benchmarks)\n");
        printf("3) Quitter\n");
        printf("Choix: ");
        
        if (scanf("%d", &choice) != 1) {
            int c;
            while ((c = getchar()) != '\n' && c != EOF) {}
            continue;
        }
        
        if (choice == 1) {
            generer_fichiers(8000000);
        } else if (choice == 2) {
            if (tableaux != NULL) {
                liberer_tableaux(tableaux);
            }
            tableaux = creer_tableaux_depuis_fichiers();
            if (tableaux != NULL) {
                run_benchmarks(tableaux);
            }
        } else if (choice == 3) {
            break;
        }
    }
    
    if (tableaux != NULL) liberer_tableaux(tableaux);
    return 0;
}
```

---

## Rôles et Contributions

* **Code et développement** : ABDERRAHIM Sidali 222231402319
* **Rédaction du rapport** : BELDJERDI Tayeb Yasser 222231404112
* **Tests et évaluations** : GACEM Abdenour 222231640608
* **Collecte des données** : DALIL Faycal 222231658510

---

**Fin du rapport**
