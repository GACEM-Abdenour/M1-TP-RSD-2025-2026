# M1-TP-RSD-2025-2026

## TP N°4 : Analyse expérimentale et comparaison des algorithmes de tri

**Module** : Algorithmique Avancée et Complexité - Master 1 (IL & RSD)  
**Université** : USTHB - Faculté d'Électronique et d'Informatique  
**Année** : 2025-2026  

**Numéro de groupe** : 08  

**Rôles et contributions** :  
- **Rédaction du rapport** :  
- **Génération des données** :  
- **Analyse des résultats** :  
- **Conception et implémentation des codes** :  

---

## Introduction

Dans ce travail pratique, une étude expérimentale a été menée sur plusieurs algorithmes classiques de tri : **tri à bulles**, **tri à bulles optimisé**, **tri gnome**, **tri rapide**, **tri par tas** et **tri par distribution (tri par base)**. [file:21]  
L’objectif principal est d’analyser et de comparer leurs performances en fonction de la taille et du type des données d’entrée : données aléatoires, déjà triées et triées en ordre inverse. [file:21]  

---

## 1. Programme du tri à bulles

### Principe de l’algorithme

Le tri à bulles repose sur la comparaison successive d’éléments adjacents dans un tableau. [file:21]  
À chaque parcours, les éléments mal ordonnés sont échangés jusqu’à ce que le tableau soit trié, et l’algorithme s’arrête lorsque plus aucun échange n’a lieu. [file:21]  

### Structure du programme

- Lecture des données d’entrée  
- Implémentation du tri à bulles (version simple et optimisée)  
- Mesure du temps d’exécution pour différents cas (meilleur, moyen, pire)  

### Complexité

#### a) Complexité théorique

- **Meilleur cas** : tableau déjà trié → **O(n)**  
  *Justification* : un seul parcours est effectué sans aucun échange. [file:21]  

- **Cas moyen** : données aléatoires → **O(n²)** [file:21]  

- **Pire cas** : tableau trié en ordre inverse → **O(n²)**  
  *Justification* : chaque élément doit être déplacé jusqu’à sa position finale. [file:21]  

#### b) Complexité spatiale

La complexité spatiale est **O(1)**, car le tri est effectué en place. [file:21]  

### Temps d’exécution

Les mesures expérimentales montrent une croissance quadratique très rapide du temps d’exécution, rendant cet algorithme inefficace pour de grandes tailles de données. [file:21]  

### Version récursive (rappel)

Une version récursive du tri rapide est parfois présentée en rappel algorithmique :  

```c

void tri_rapide_recursive(int T[], int gauche, int droite) {
if (gauche < droite) {
int pivot = partition(T, gauche, droite);
tri_rapide_recursive(T, gauche, pivot - 1);
tri_rapide_recursive(T, pivot + 1, droite);
}
}

```

*(Cette version illustre le schéma récursif, le code exact peut varier selon l’implémentation du TP.)* [file:21]  

### Représentation graphique

Les graphes associés mettent en évidence la différence entre le meilleur cas linéaire et les cas moyen/pire en \(O(n^2)\). [file:21]  

---

## 2. Programme du tri gnome

### Principe de l’algorithme

Le tri gnome procède par déplacements locaux successifs en comparant un élément à son prédécesseur. [file:21]  
Si l’élément courant est plus petit, un échange est effectué et l’algorithme recule d’une position jusqu’à rétablir l’ordre, sinon il avance. [file:21]  

### Structure du programme

- **permuter** : échange deux valeurs  
- **tri_gnome** : implémentation itérative du tri gnome  
- **lire_fichier** : lecture des données à trier  
- **main** : exécution des tests et mesure du temps [file:21]  

Un squelette possible de la fonction de tri est :

```c

void tri_gnome(int *t, int n) {
int i = 0;
while (i < n) {
if (i == 0 || t[i] >= t[i - 1]) {
i++;
} else {
int tmp = t[i];
t[i] = t[i - 1];
t[i - 1] = tmp;
i--;
}
}
}

```

### Complexité

#### a) Complexité théorique

- **Meilleur cas** : tableau déjà trié → **O(n)** [file:21]  
- **Cas moyen** : données aléatoires → **O(n²)** [file:21]  
- **Pire cas** : tableau inversé → **O(n²)** [file:21]  

#### b) Complexité spatiale

La complexité spatiale est **O(1)**, le tri étant en place. [file:21]  

### Version récursive

Une version récursive peut être définie sous la forme :  

```c

void tri_gnome_rec(int *t, int n, int i) {
if (i >= n) return;
if (i == 0 || t[i] >= t[i - 1]) {
tri_gnome_rec(t, n, i + 1);
} else {
int tmp = t[i];
t[i] = t[i - 1];
t[i - 1] = tmp;
tri_gnome_rec(t, n, i - 1);
}
}

```

[file:21]  

### Représentation graphique

Les courbes expérimentales confirment un comportement quadratique très proche de celui du tri à bulles. [file:21]  

---

## 3. Programme du tri par distribution (tri par base)

### Principe de l’algorithme

Le tri par base (radix sort) trie les entiers chiffre par chiffre, en commençant par le chiffre de poids faible (unités), puis dizaines, centaines, etc. [file:21]  
À chaque étape, un tri auxiliaire stable est appliqué sur la clé extraite (chiffre courant). [file:21]  

### Structure du programme

- **cle** : extraction d’un chiffre donné pour une base fixée  
- **TriAux** : tri auxiliaire stable selon un chiffre (par exemple comptage)  
- **TriBase** : boucle principale appliquant TriAux sur chaque position de chiffre  
- **lire_fichier**, **main** : gestion des entrées/sorties et des tests [file:21]  

Exemple de structure simplifiée :

```c

int cle(int x, int position, int base) {
int div = 1;
for (int i = 0; i < position; i++) div *= base;
return (x / div) % base;
}

void TriAux(int *T, int n, int position, int base) {
int *C = calloc(base, sizeof(int));
int *B = malloc(n * sizeof(int));

    for (int i = 0; i < n; i++)
        C[cle(T[i], position, base)]++;
    
    for (int i = 1; i < base; i++)
        C[i] += C[i - 1];
    
    for (int i = n - 1; i >= 0; i--) {
        int d = cle(T[i], position, base);
        B[--C[d]] = T[i];
    }
    
    for (int i = 0; i < n; i++)
        T[i] = B[i];
    
    free(C);
    free(B);
    }

void TriBase(int *T, int n, int k, int base) {
for (int pos = 0; pos < k; pos++)
TriAux(T, n, pos, base);
}

```

### Complexité

#### a) Complexité théorique

Pour un tableau de \(n\) entiers et un nombre de chiffres \(k\) :  

- **Tous les cas** : **O(n × k)**, indépendant de l’ordre initial des données. [file:21]  

#### b) Complexité spatiale

Complexité spatiale **O(n)** en raison des tableaux auxiliaires utilisés à chaque passe. [file:21]  

### Version récursive

Une écriture récursive conceptuelle peut prendre la forme :  

```c

void TriBaseRec(int *T, int n, int position, int k, int base) {
if (position >= k) return;
TriAux(T, n, position, base);
TriBaseRec(T, n, position + 1, k, base);
}

```

[file:21]  

### Représentation graphique

Les résultats expérimentaux montrent une croissance quasi linéaire des temps d’exécution, cohérente avec une complexité en \(O(n \times k)\) pour un \(k\) fixé. [file:21]  

---

## 4. Programme du tri rapide

### Principe de l’algorithme

Le tri rapide repose sur la stratégie **diviser pour régner** : choix d’un pivot, partition du tableau en deux sous-tableaux, puis appel récursif sur chaque partie. [file:21]  

### Structure du programme

- **partition** : réorganisation autour du pivot  
- **tri_rapide** : fonction récursive principale  
- **lire_fichier** : lecture des données  
- **main** : lancement des tests et mesures [file:21]  

Implémentation typique :

```c

int partition(int *T, int gauche, int droite) {
int pivot = T[droite];
int i = gauche - 1;
for (int j = gauche; j < droite; j++) {
if (T[j] <= pivot) {
i++;
int tmp = T[i]; T[i] = T[j]; T[j] = tmp;
}
}
int tmp = T[i + 1]; T[i + 1] = T[droite]; T[droite] = tmp;
return i + 1;
}

void tri_rapide(int *T, int gauche, int droite) {
if (gauche < droite) {
int p = partition(T, gauche, droite);
tri_rapide(T, gauche, p - 1);
tri_rapide(T, p + 1, droite);
}
}

```

### Complexité

#### a) Complexité théorique

- **Cas moyen** : **O(n log n)** grâce à des partitions équilibrées. [file:21]  
- **Pire cas** : **O(n²)** lorsque le pivot choisit des partitions très déséquilibrées (par exemple tableau déjà trié avec pivot extrême). [file:21]  

#### b) Complexité spatiale

La complexité spatiale est **O(log n)**, due à la profondeur de la récursivité. [file:21]  

### Représentation graphique

Les graphes montrent de très bonnes performances moyennes, nettement supérieures aux algorithmes quadratiques pour des grandes tailles de tableaux. [file:21]  

---

## 5. Programme du tri par tas

### Principe de l’algorithme

Le tri par tas transforme d’abord le tableau en un **tas binaire maximal**, puis extrait successivement l’élément maximal pour le placer en fin de tableau. [file:21]  
La structure de tas garantit des opérations d’extraction en temps logarithmique. [file:21]  

### Structure du programme

- **tamiser** : rétablit la propriété de tas à partir d’un sommet donné  
- **tri_tas** : construction du tas puis phase d’extraction  
- **lire_fichier**, **main** : gestion des tests et des mesures [file:21]  

Exemple de schéma de code :

```c

void tamiser(int *t, int n, int i) {
int plus_grand = i;
int g = 2 * i + 1;
int d = 2 * i + 2;

    if (g < n && t[g] > t[plus_grand]) plus_grand = g;
    if (d < n && t[d] > t[plus_grand]) plus_grand = d;
    
    if (plus_grand != i) {
        int tmp = t[i]; t[i] = t[plus_grand]; t[plus_grand] = tmp;
        tamiser(t, n, plus_grand);
    }
    }

void tri_tas(int *t, int n) {
for (int i = n / 2 - 1; i >= 0; i--)
tamiser(t, n, i);

    for (int i = n - 1; i > 0; i--) {
        int tmp = t; t = t[i]; t[i] = tmp;
        tamiser(t, i, 0);
    }
    }

```

### Complexité

#### a) Complexité théorique

- **Meilleur, moyen et pire cas** : **O(n log n)**, indépendamment de l’ordre initial des données. [file:21]  

#### b) Complexité spatiale

La complexité spatiale est **O(1)**, car le tri est effectué en place. [file:21]  

### Version récursive

Les fonctions `tamiser` et une variante récursive de `tri_tas` peuvent être exprimées entièrement de manière récursive, tout en conservant la même complexité. [file:21]  

### Représentation graphique

Les courbes expérimentales montrent des performances stables, légèrement moins bonnes que le tri rapide dans le cas moyen mais sans dégradation en pire cas. [file:21]  

---

## Conclusion générale

- Les algorithmes quadratiques (**tri à bulles**, **tri gnome**) deviennent rapidement inefficaces lorsque la taille des données augmente. [file:21]  
- Les algorithmes en **O(n log n)** (**tri rapide**, **tri par tas**) et en **O(n × k)** (**tri par base**) offrent des performances nettement supérieures pour de grandes tailles, avec des sensibilités différentes à l’ordre initial des données. [file:21]  

---

## Rôles et Contributions

| Membre | Matricule | Rôle principal |
|--------|-----------|----------------|
|        |           | Rédaction du rapport |
|        |           | Génération des données |
|        |           | Analyse des résultats |
|        |           | Conception et implémentation des codes |

*(À compléter selon la composition exacte du groupe.)* [file:21]  

---

**Fin du rapport**
```


