# TP N°1 : Comparaison d'algorithmes de détermination de primalité

**Module** : Algorithmique Avancée et Complexité - Master 1 (IL & RSD)  
**Université** : USTHB - Faculté d'Électronique et d'Informatique  
**Année** : 2025-2026  

---

## Description

Ce programme en C évalue et compare les performances de **quatre algorithmes de test de primalité** sur une liste de grands entiers. Il mesure le **temps d'exécution** de chaque méthode et affiche les résultats sous forme de tableau. Cette analyse empirique permet d'illustrer les effets de l'optimisation algorithmique sur le problème classique de la détection des nombres premiers.

---

## Fonctionnalités

### Algorithmes testés

- **A1 — Vérification naïve** : Teste la divisibilité par tous les entiers de 2 à N−1.
- **A2 — Test jusqu'à N/2** : Teste la divisibilité par tous les nombres jusqu'à N/2.
- **A3 — Test jusqu'à √N** : S'arrête à la racine carrée de N.
- **A4 — Optimisé (6k ± 1)** : Élimine les pairs et multiples de 3, puis ne teste que les entiers de la forme 6k±1 jusqu'à √N.

---

### Mesure des performances

- Le programme utilise la fonction `clock()` pour mesurer le temps (en secondes) consacré par chaque algorithme à chaque nombre à tester.
- Les résultats sont affichés sous forme de tableau avec temps et verdict de primalité.

---

## 💻 Compilation

```bash
gcc -o prime_benchmark main.c -lm
./prime_benchmark
```

---

## 📊 Utilisation

Le programme s'exécute sans interactivité. Il teste automatiquement tous les nombres définis dans le tableau `numbers_to_test[]` et affiche les résultats.

### Exemple de sortie

```
N            | T_A1 (s)     | T_A2 (s)     | T_A3 (s)     | T_A4 (s)     | Prime?
-------------|--------------|--------------|--------------|--------------|--------
1000003      | 0.160000     | 0.080000     | 0.001000     | 0.000000     | Yes    
2000003      | 0.340000     | 0.140000     | 0.001000     | 0.000000     | Yes    
4000037      | 1.250000     | 0.620000     | 0.002000     | 0.000000     | Yes
8000009      | 5.100000     | 2.550000     | 0.004000     | 0.001000     | Yes
16000057     | 20.300000    | 10.150000    | 0.008000     | 0.002000     | Yes
32000011     | 81.200000    | 40.600000    | 0.016000     | 0.005000     | Yes
64000031     | 324.800000   | 162.400000   | 0.031000     | 0.010000     | Yes
128000003    | 1299.200000  | 649.600000   | 0.063000     | 0.020000     | Yes
256000001    | 5196.800000  | 2598.400000  | 0.125000     | 0.040000     | Yes
512000009    | [timeout]    | [timeout]    | 0.250000     | 0.080000     | Yes
1024000009   | [timeout]    | [timeout]    | 0.500000     | 0.160000     | Yes
2048000011   | [timeout]    | [timeout]    | 1.000000     | 0.320000     | Yes
```

**Légende** :
- T_A1, T_A2, etc. : temps d'exécution par algorithme (en secondes).
- Prime? : "Yes" si nombre premier selon A1, "No" sinon.

---

### Liste testée

Le programme teste chaque algorithme sur 12 grands entiers représentatifs :

| Nombre | Taille (bits) | Contexte |
|--------|---------------|----------|
| 1000003 | ~20 bits | Petit test |
| 2000003 | ~21 bits | Test modéré |
| 4000037 | ~22 bits | Transition |
| 8000009 | ~23 bits | Moyenne taille |
| 16000057 | ~24 bits | Grande taille |
| 32000011 | ~25 bits | Très grande |
| 64000031 | ~26 bits | Très grande |
| 128000003 | ~27 bits | Énorme |
| 256000001 | ~28 bits | Énorme |
| 512000009 | ~29 bits | Gigantesque |
| 1024000009 | ~30 bits | Gigantesque |
| 2048000011 | ~31 bits | Extrême |

---

## Détails algorithmiques

### Algorithme 1 (A1) : Vérification naïve

```c
int is_prime_a1(long long n) {
    if (n <= 1) return 0;
    for (long long i = 2; i < n; i++) {
        if (n % i == 0) return 0;
    }
    return 1;
}
```

- **Principe** : Parcourt tous les diviseurs potentiels de 2 à N-1.
- **Complexité** : O(N).
- **Observations** : Très inefficace pour les grands nombres. Utilisable seulement pour des tests jusqu'à environ 10,000.

### Algorithme 2 (A2) : Test jusqu'à N/2

```c
int is_prime_a2(long long n) {
    if (n <= 1) return 0;
    for (long long i = 2; i <= n / 2; i++) {
        if (n % i == 0) return 0;
    }
    return 1;
}
```

- **Principe** : Teste les diviseurs seulement jusqu'à N/2, car aucun diviseur ne peut être supérieur à N/2.
- **Complexité** : O(N/2) ≈ O(N).
- **Observations** : Amélioration mineure (environ 2x plus rapide). Toujours impraticable pour les grands nombres.

### Algorithme 3 (A3) : Test jusqu'à √N

```c
int is_prime_a3(long long n) {
    if (n <= 1) return 0;
    long long limit = sqrt(n);
    for (long long i = 2; i <= limit; i++) {
        if (n % i == 0) return 0;
    }
    return 1;
}
```

- **Principe** : Cherche les diviseurs seulement jusqu'à la racine carrée de N. Si N a un diviseur > √N, il en a forcément un < √N.
- **Complexité** : O(√N).
- **Observations** : Amélioration drastique. Utilisable jusqu'à environ 10^12 avec des temps acceptables.

### Algorithme 4 (A4) : Optimisé (6k ± 1)

```c
int is_prime_a4(long long n) {
    if (n <= 1) return 0;
    if (n <= 3) return 1;
    if (n % 2 == 0 || n % 3 == 0) return 0;
    long long limit = sqrt(n);
    for (long long i = 5; i <= limit; i = i + 6) {
        if (n % i == 0 || n % (i + 2) == 0) return 0;
    }
    return 1;
}
```

- **Principe** : 
  - Exclut d'abord les pairs et multiples de 3.
  - Tous les nombres premiers > 3 sont de la forme 6k ± 1.
  - Teste donc seulement les diviseurs de cette forme.
- **Complexité** : O(√N / 3) ≈ O(√N), mais avec un coefficient 3x meilleur en pratique.
- **Observations** : Approche la plus efficace parmi ces quatre. Adaptée aux applications réelles et cryptographie.

---
