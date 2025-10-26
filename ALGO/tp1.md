
# Let me create the complete solution for all 4 primality testing algorithms

# First, let me create the complete C code for all algorithms
# I'll create a comprehensive solution with all implementations, analysis, and documentation

# Algorithm implementations in C with time measurement
c_code = """#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

// Algorithme 1 : Approche naïve - teste tous les diviseurs de 2 à N-1
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

// Algorithme 2 : Amélioration - teste les diviseurs jusqu'à N/2
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

// Algorithme 3 : Optimisation - teste les diviseurs jusqu'à sqrt(N)
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

// Algorithme 4 : Optimisation maximale - teste uniquement les nombres impairs
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

// Fonction pour mesurer le temps d'exécution
double mesurerTemps(int (*fonction)(long long), long long N) {
    clock_t debut, fin;
    double temps_cpu;
    
    debut = clock();
    int resultat = fonction(N);
    fin = clock();
    
    temps_cpu = ((double) (fin - debut)) / CLOCKS_PER_SEC;
    
    printf("N = %lld, Premier: %s, Temps: %.6f secondes\\n", 
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
    
    printf("=== TEST DES ALGORITHMES DE PRIMALITÉ ===\\n\\n");
    
    // Test Algorithme 1
    printf("\\n--- Algorithme 1 (Approche naïve) ---\\n");
    for (int i = 0; i < taille; i++) {
        mesurerTemps(estPremier_A1, echantillon[i]);
    }
    
    // Test Algorithme 2
    printf("\\n--- Algorithme 2 (Optimisation N/2) ---\\n");
    for (int i = 0; i < taille; i++) {
        mesurerTemps(estPremier_A2, echantillon[i]);
    }
    
    // Test Algorithme 3
    printf("\\n--- Algorithme 3 (Optimisation sqrt(N)) ---\\n");
    for (int i = 0; i < taille; i++) {
        mesurerTemps(estPremier_A3, echantillon[i]);
    }
    
    // Test Algorithme 4
    printf("\\n--- Algorithme 4 (Nombres impairs uniquement) ---\\n");
    for (int i = 0; i < taille; i++) {
        mesurerTemps(estPremier_A4, echantillon[i]);
    }
    
    return 0;
}
"""

print("Code C généré avec succès")
print(f"Longueur du code: {len(c_code)} caractères")
