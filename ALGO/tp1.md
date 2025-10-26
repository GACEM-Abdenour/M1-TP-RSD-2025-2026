# TP N°1 : Test de Primalité - Solution Complète

**Module :** Algorithmique Avancée et Complexité - Master 1 (IL & RSD)  
**Université :** USTHB - Faculté d'Électronique et d'Informatique  
**Année :** 2019-2020

## Objectif du TP

Ce travail pratique vise à :
- Implémenter et comparer quatre algorithmes de test de primalité
- Analyser leurs complexités théoriques et expérimentales
- Mesurer les temps d'exécution avec la bibliothèque `time.h`
- Comprendre l'impact des optimisations algorithmiques

## Rappel Théorique

Un nombre entier naturel N est **premier** s'il n'admet que deux diviseurs : 1 et N lui-même.

## Solutions des Quatre Algorithmes

### Algorithme 1 (A1) : Approche Naïve

**Principe :** Tester tous les diviseurs potentiels de 2 à N-1.

**Complexité théorique :** O(N)

```c
int estPremier_A1(long long N) {
    if (N <= 1) return 0;
    if (N == 2) return 1;
    
    for (long long i = 2; i < N; i++) {
        if (N % i == 0) {
            return 0;  // N est divisible par i, donc non premier
        }
    }
    return 1;  // N est premier
}
```

**Analyse :**
- **Avantages :** Simple à comprendre et implémenter
- **Inconvénients :** Très inefficace pour les grands nombres
- **Complexité spatiale :** O(1)

### Algorithme 2 (A2) : Amélioration de l'Approche Naïve

**Principe :** Utiliser la propriété que tout diviseur i de N (avec i ≠ N) vérifie i ≤ N/2.

**Complexité théorique :** O(N/2) = O(N)

```c
int estPremier_A2(long long N) {
    if (N <= 1) return 0;
    if (N == 2) return 1;
    
    for (long long i = 2; i <= N/2; i++) {
        if (N % i == 0) {
            return 0;
        }
    }
    return 1;
}
```

**Analyse :**
- **Avantages :** Deux fois plus rapide que A1
- **Inconvénients :** Toujours de complexité linéaire
- **Amélioration :** Réduction constante du nombre d'itérations

### Algorithme 3 (A3) : Optimisation avec √N

**Principe :** Exploiter la propriété mathématique que les diviseurs de N sont répartis symétriquement autour de √N.

**Complexité théorique :** O(√N)

```c
int estPremier_A3(long long N) {
    if (N <= 1) return 0;
    if (N == 2) return 1;
    
    long long limite = (long long)sqrt((double)N);
    for (long long i = 2; i <= limite; i++) {
        if (N % i == 0) {
            return 0;
        }
    }
    return 1;
}
```

**Analyse :**
- **Avantages :** Amélioration drastique de la complexité
- **Justification mathématique :** Si N = a × b avec a ≤ b, alors a ≤ √N
- **Impact :** Réduction exponentielle des calculs

### Algorithme 4 (A4) : Optimisation Maximale

**Principe :** Combiner l'optimisation √N avec le test des nombres impairs uniquement.

**Complexité théorique :** O(√N/2) = O(√N)

```c
int estPremier_A4(long long N) {
    if (N <= 1) return 0;
    if (N == 2) return 1;
    if (N % 2 == 0) return 0;  // Élimine les nombres pairs
    
    long long limite = (long long)sqrt((double)N);
    // Teste uniquement les diviseurs impairs
    for (long long i = 3; i <= limite; i += 2) {
        if (N % i == 0) {
            return 0;
        }
    }
    return 1;
}
```

**Analyse :**
- **Avantages :** Le plus efficace des quatre algorithmes
- **Optimisations combinées :** √N + nombres impairs seulement
- **Performance :** Environ deux fois plus rapide que A3

## Code Complet avec Mesure de Performance

```c
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

// [Insérer ici les quatre fonctions ci-dessus]

// Fonction pour mesurer le temps d'exécution
double mesurerTemps(int (*fonction)(long long), long long N) {
    clock_t debut, fin;
    double temps_cpu;
    
    debut = clock();
    int resultat = fonction(N);
    fin = clock();
    
    temps_cpu = ((double) (fin - debut)) / CLOCKS_PER_SEC;
    
    printf("N = %lld, Premier: %s, Temps: %.6f secondes\n", 
           N, resultat ? "OUI" : "NON", temps_cpu);
    
    return temps_cpu;
}

int main() {
    // Échantillon de nombres premiers pour les tests
    long long echantillon[] = {
        1000003, 2000003, 4000037, 8000009, 16000057, 
        32000011, 64000031, 128000003, 256000001, 512000009, 
        1024000009, 2048000011
    };
    int taille = sizeof(echantillon) / sizeof(echantillon[0]);
    
    printf("=== TEST DES ALGORITHMES DE PRIMALITÉ ===\n\n");
    
    // Tests pour chaque algorithme
    const char* noms[] = {
        "Algorithme 1 (Approche naïve)",
        "Algorithme 2 (Optimisation N/2)",
        "Algorithme 3 (Optimisation sqrt(N))",
        "Algorithme 4 (Nombres impairs uniquement)"
    };
    
    int (*algorithmes[])(long long) = {
        estPremier_A1, estPremier_A2, estPremier_A3, estPremier_A4
    };
    
    for (int alg = 0; alg < 4; alg++) {
        printf("\n--- %s ---\n", noms[alg]);
        for (int i = 0; i < taille; i++) {
            mesurerTemps(algorithmes[alg], echantillon[i]);
        }
    }
    
    return 0;
}
```

## Compilation et Exécution

```bash
# Compilation avec optimisation
gcc -o test_primalite test_primalite.c -lm -O2

# Exécution
./test_primalite
```

## Analyse des Résultats Attendus

### Tableau de Comparaison des Complexités

| Algorithme | Complexité Théorique | Nombre d'Opérations (N=10^6) | Performance Relative |
|------------|---------------------|-------------------------------|---------------------|
| A1         | O(N)                | ~10^6                         | Baseline            |
| A2         | O(N/2)              | ~5×10^5                       | 2× plus rapide      |
| A3         | O(√N)               | ~10^3                         | 1000× plus rapide   |
| A4         | O(√N/2)             | ~500                          | 2000× plus rapide   |

### Observations sur l'Échantillon de Test

Les nombres de l'échantillon suivent une progression géométrique :
- **Progression :** Chaque nombre est approximativement le double du précédent
- **Propriété :** Tous sont des nombres premiers
- **Impact sur A1/A2 :** Le temps double à chaque étape
- **Impact sur A3/A4 :** Le temps augmente beaucoup plus lentement

### Comparaison Théorique vs Expérimentale

**Prédictions théoriques compatibles :**
- A1 et A2 montrent une croissance linéaire
- A3 et A4 montrent une croissance en racine carrée
- A4 est environ 2× plus rapide que A3
- Le rapport de performance entre A1 et A3 augmente avec N

## Visualisation des Performances

### Graphique Temps d'Exécution T(N)

```
Temps (secondes)
        ^
        |     A1 (linéaire)
        |    /
        |   /  A2 (linéaire/2)
        |  /
        | /
        |/_____ A3 (√N)
        |______ A4 (√N/2)
        +-------------------------> N
```

### Recommandations d'Utilisation

1. **A4** : Recommandé pour la production (optimal)
2. **A3** : Bon compromis simplicité/performance
3. **A2** : Uniquement pour l'apprentissage
4. **A1** : Éviter (sauf à des fins pédagogiques)

## Extensions Possibles

### Optimisations Supplémentaires

1. **Crible d'Ératosthène** : Pour tester plusieurs nombres
2. **Test de Miller-Rabin** : Algorithme probabiliste plus rapide
3. **Optimisation 6k±1** : Éliminer plus de candidats

### Code d'Extension (6k±1)

```c
int estPremier_A5(long long N) {
    if (N <= 1) return 0;
    if (N <= 3) return 1;
    if (N % 2 == 0 || N % 3 == 0) return 0;
    
    for (long long i = 5; i * i <= N; i += 6) {
        if (N % i == 0 || N % (i + 2) == 0)
            return 0;
    }
    return 1;
}
```

## Conclusion

Ce TP démontre l'importance cruciale des optimisations algorithmiques :

- **Impact des mathématiques** : L'utilisation de √N transforme un algorithme O(N) en O(√N)
- **Optimisations pratiques** : Éliminer les nombres pairs divise le temps par 2
- **Scalabilité** : Plus N est grand, plus l'écart de performance se creuse
- **Choix algorithmique** : A4 est clairement le meilleur choix pour les applications réelles

L'algorithme A4 combine élégamment les optimisations mathématiques et pratiques, offrant des performances excellentes tout en restant simple à implémenter et comprendre.

## Makefile (Bonus)

```makefile
CC = gcc
CFLAGS = -Wall -Wextra -O2 -lm
TARGET = test_primalite
SOURCE = test_primalite.c

$(TARGET): $(SOURCE)
	$(CC) -o $(TARGET) $(SOURCE) $(CFLAGS)

clean:
	rm -f $(TARGET)

run: $(TARGET)
	./$(TARGET)

.PHONY: clean run
```
