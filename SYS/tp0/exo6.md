# TP Processus et Signaux — Exercice 6 : Horloge avec Processus

**Module:** Système d'exploitation  
**Sujet:** Synchronisation de processus par signaux  
**Année:** 2025-2026  
**Étudiant:** Abdenour GACEM  
**Filière:** M1 RSD  

---

## 🧩 Exercice 6 — Horloge avec trois processus fils

## Énoncé

Écrire un programme qui crée trois processus fils : **H, M, S** (Heure, Minute, Seconde). Chacun des trois processus possédera un compteur initialisé à une heure quelconque.

Le processus **S** incrémente son compteur de 1 toutes les 1 seconde pendant que **M** et **H** sont bloqués avec la fonction `pause()`. Quand le compteur de **S** atteint 60, il le remet à zéro et envoie un signal **SIGUSR1** au processus **M** qui va réagir en incrémentant à son tour son propre compteur et en vérifiant s'il atteint 60. Auquel cas, il remet à zéro et envoie un signal à **H** qui va incrémenter son propre compteur et appeler `pause()` encore une fois.

---

## Réponse

### Analyse du problème

Ce programme simule une horloge numérique en utilisant :
- **3 processus distincts** (H, M, S) créés avec `fork()`
- **Communication inter-processus** via les signaux (`SIGUSR1`)
- **Synchronisation** avec `pause()` pour bloquer les processus en attente de signaux
- **Handlers de signaux** pour réagir aux événements

### Architecture

```
Processus père (main)
    │
    ├─→ Processus H (Heures)  ─── pause() → attend SIGUSR1 de M
    │
    ├─→ Processus M (Minutes) ─── pause() → attend SIGUSR1 de S
    │
    └─→ Processus S (Secondes) ─── sleep(1) → envoie SIGUSR1 à M toutes les 60s
```

### Code complet

```c
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>

int compteur_S = 0;  
int compteur_M = 0; 
int compteur_H = 0;  

pid_t pid_M, pid_H, pid_S;

void processus_S() {
    while (1) {
        sleep(1);
        compteur_S++;
        printf("S: %02d secondes\n", compteur_S);
        
        if (compteur_S >= 60) {
            compteur_S = 0;
            printf("S: Remise à zéro, envoi signal à M\n");
            kill(pid_M, SIGUSR1);
        }
    }
}

void handler_M(int sig) {
    compteur_M++;
    printf("M: %02d minutes\n", compteur_M);
    
    if (compteur_M >= 60) {
        compteur_M = 0;
        printf("M: Remise à zéro, envoi signal à H\n");
        kill(pid_H, SIGUSR1);
    }
}

void handler_H(int sig) {
    compteur_H++;
    printf("H: %02d heures\n", compteur_H);
    
    if (compteur_H >= 24) {
        compteur_H = 0;
        printf("H: Remise à zéro (nouveau jour)\n");
    }
}

void processus_M() {
    signal(SIGUSR1, handler_M);
    
    while (1) {
        pause();
    }
}

void processus_H() {
    signal(SIGUSR1, handler_H);
    
    while (1) {
        pause();
    }
}

int main() {
    int heure_init = 0, minute_init = 0, seconde_init = 0;
    
    // Initialisation de l'heure
    printf("Entrez l'heure initiale (HH MM SS) : ");
    scanf("%d %d %d", &heure_init, &minute_init, &seconde_init);
    
    if (heure_init < 0 || heure_init >= 24 || 
        minute_init < 0 || minute_init >= 60 || 
        seconde_init < 0 || seconde_init >= 60) {
        fprintf(stderr, "Erreur: Valeurs invalides\n");
        exit(1);
    }
    
    compteur_H = heure_init;
    compteur_M = minute_init;
    compteur_S = seconde_init;
    
    printf("Horloge initialisée à %02d:%02d:%02d\n\n", 
           compteur_H, compteur_M, compteur_S);
    
    pid_H = fork();
    if (pid_H < 0) {
        perror("Erreur fork H");
        exit(1);
    }
    
    if (pid_H == 0) {
        processus_H();
        exit(0);
    }
    
    pid_M = fork();
    if (pid_M < 0) {
        perror("Erreur fork M");
        kill(pid_H, SIGKILL);
        exit(1);
    }
    
    if (pid_M == 0) {
        processus_M();
        exit(0);
    }
    
    pid_S = fork();
    if (pid_S < 0) {
        perror("Erreur fork S");
        kill(pid_H, SIGKILL);
        kill(pid_M, SIGKILL);
        exit(1);
    }

    if (pid_S == 0) {
        processus_S();
        exit(0);
    }
    
    int status;
    pid_t pid;
    
    // Attendre que tous les processus se terminent
    while ((pid = wait(&status)) > 0) {
        printf("Processus %d terminé\n", pid);
    }
    
    return 0;
}
```

### Compilation et exécution

```bash
gcc exo6.c -o exo6
./exo6
```

### Exemple d'exécution

```
Entrez l'heure initiale (HH MM SS) : 23 58 55
Horloge initialisée à 23:58:55

S: 56 secondes
S: 57 secondes
S: 58 secondes
S: 59 secondes
S: 60 secondes
S: Remise à zéro, envoi signal à M
M: 59 minutes
S: 01 secondes
S: 02 secondes
S: 03 secondes
...
S: 58 secondes
S: 59 secondes
S: 60 secondes
S: Remise à zéro, envoi signal à M
M: 60 minutes
M: Remise à zéro, envoi signal à H
H: 00 heures
H: Remise à zéro (nouveau jour)
S: 01 secondes
S: 02 secondes
...
```

### Explications détaillées

#### 1. Variables globales et PIDs

```c
int compteur_S = 0;  
int compteur_M = 0; 
int compteur_H = 0;  

pid_t pid_M, pid_H, pid_S;
```

**Important :** Après un `fork()`, chaque processus fils obtient **sa propre copie** de ces variables. Les modifications dans un processus n'affectent pas les autres processus.

- Le processus **S** utilise uniquement `compteur_S`
- Le processus **M** utilise uniquement `compteur_M`
- Le processus **H** utilise uniquement `compteur_H`
- Les PIDs sont partagés car ils sont initialisés **avant** les `fork()`

#### 2. Processus S (Secondes)

```c
void processus_S() {
    while (1) {
        sleep(1);              // Attendre 1 seconde
        compteur_S++;
        printf("S: %02d secondes\n", compteur_S);
        
        if (compteur_S >= 60) {
            compteur_S = 0;
            printf("S: Remise à zéro, envoi signal à M\n");
            kill(pid_M, SIGUSR1);  // Envoyer signal à M
        }
    }
}
```

**Rôle :** 
- Incrémente son compteur toutes les secondes
- Affiche la valeur courante
- Envoie `SIGUSR1` à M quand il atteint 60

#### 3. Processus M (Minutes)

```c
void handler_M(int sig) {
    compteur_M++;
    printf("M: %02d minutes\n", compteur_M);
    
    if (compteur_M >= 60) {
        compteur_M = 0;
        printf("M: Remise à zéro, envoi signal à H\n");
        kill(pid_H, SIGUSR1);  // Envoyer signal à H
    }
}

void processus_M() {
    signal(SIGUSR1, handler_M);  // Installer le gestionnaire
    
    while (1) {
        pause();  // Bloquer jusqu'à réception d'un signal
    }
}
```

**Rôle :**
- Reste bloqué avec `pause()`
- Se réveille à la réception de `SIGUSR1` de S
- Incrémente son compteur via `handler_M()`
- Envoie `SIGUSR1` à H quand il atteint 60

#### 4. Processus H (Heures)

```c
void handler_H(int sig) {
    compteur_H++;
    printf("H: %02d heures\n", compteur_H);
    
    if (compteur_H >= 24) {
        compteur_H = 0;
        printf("H: Remise à zéro (nouveau jour)\n");
    }
}

void processus_H() {
    signal(SIGUSR1, handler_H);  // Installer le gestionnaire
    
    while (1) {
        pause();  // Bloquer jusqu'à réception d'un signal
    }
}
```

**Rôle :**
- Reste bloqué avec `pause()`
- Se réveille à la réception de `SIGUSR1` de M
- Incrémente son compteur via `handler_H()`
- Remet à zéro quand il atteint 24 (nouveau jour)

#### 5. Création des processus dans main()

```c
// Ordre de création : H → M → S
pid_H = fork();  // Créer processus H
if (pid_H == 0) {
    processus_H();
    exit(0);
}

pid_M = fork();  // Créer processus M
if (pid_M == 0) {
    processus_M();
    exit(0);
}

pid_S = fork();  // Créer processus S
if (pid_S == 0) {
    processus_S();
    exit(0);
}
```

**Important :** L'ordre de création garantit que tous les PIDs sont disponibles avant que les processus n'en aient besoin.


**Conclusion :**  
Ce programme démontre l'utilisation efficace des signaux POSIX pour la synchronisation de processus dans une application temps réel simple. Il illustre la puissance de la communication inter-processus tout en montrant les limites de l'approche sans mémoire partagée.
