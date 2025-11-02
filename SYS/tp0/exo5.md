# TP Processus et Signaux — Exercice 5 : Communication Père-Fils

**Module:** Système d'exploitation  
**Sujet:** Synchronisation de processus par signaux  
**Année:** 2025-2026  
**Étudiant:** Abdenour GACEM  
**Filière:** M1 RSD  

---

## 🧩 Exercice 5 — Alternance Majuscules-Minuscules avec Signaux

## Énoncé

Écrire un programme dans lequel le processus principal crée un processus fils. Les deux processus communiquent en utilisant le signal **SIGUSR1**. Le fils affiche les lettres minuscules de **a** à **z**, le père affiche des majuscules de **A** à **Z**. Ce programme doit être écrit de telle sorte que le texte affiché sera comme suit :

```
AabcBCdefDEFghijGHIJklmnoKLMNOpqrstuPQRSTUvwxyzVWXYZ
```

---

## Analyse du motif

Pour comprendre le motif demandé, décomposons la chaîne :

| Étape | Père (MAJ) | Fils (min) | Nb MAJ | Nb min |
|-------|------------|------------|--------|--------|
| 1 | **A** | abc | 1 | 3 |
| 2 | **BC** | def | 2 | 3 |
| 3 | **DEF** | ghij | 3 | 4 |
| 4 | **GHIJ** | klmno | 4 | 5 |
| 5 | **KLMNO** | pqrstu | 5 | 6 |
| 6 | **PQRSTU** | vwxyz | 6 | 5 |
| 7 | **VWXYZ** | - | 5 | 0 |

**Observation :** 
- Père : séquence `{1, 2, 3, 4, 5, 6, 5}` lettres majuscules
- Fils : séquence `{3, 3, 4, 5, 6, 5}` lettres minuscules

---

## Réponse

### Stratégie de synchronisation

Le protocole de communication entre père et fils utilise **SIGUSR1** :

1. **Père** affiche son bloc de majuscules
2. **Père** envoie **SIGUSR1** au **fils**
3. **Fils** se réveille, affiche son bloc de minuscules
4. **Fils** envoie **SIGUSR1** au **père**
5. Retour à l'étape 1

```
┌─────────┐           SIGUSR1            ┌─────────┐
│  PÈRE   │ ──────────────────────────> │  FILS   │
│ (MAJ)   │                              │  (min)  │
│         │ <────────────────────────── │         │
└─────────┘           SIGUSR1            └─────────┘
    │                                        │
    │ Affiche A                              │
    ├───────────────────────────────────────>│ pause()
    │                                        │ Affiche abc
    │<───────────────────────────────────────┤
    │ pause()                                │
    │ Affiche BC                             │
    ├───────────────────────────────────────>│ pause()
    │                                        │ Affiche def
    └────────────────────────────────────────┘
```

### Code complet

```c
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>

pid_t pid_fils;
pid_t pid_pere;

// Gestionnaire de signal simple pour débloquer pause()
void handler(int sig) {
    // Le handler ne fait rien, il sert juste à réveiller le processus
}

int main() {
    // Installation des gestionnaires de signaux
    signal(SIGUSR1, handler);
    
    pid_fils = fork();
    
    if (pid_fils < 0) {
        perror("Erreur fork");
        exit(1);
    }
    
    if (pid_fils == 0) {
        // ========== PROCESSUS FILS ==========
        // Affiche les lettres minuscules : a-z
        
        pid_pere = getppid();
        
        // Séquence : 3, 3, 4, 5, 6, 5 lettres minuscules
        int sequence[] = {3, 3, 4, 5, 6, 5};
        int idx_seq = 0;
        char c = 'a';
        
        while (c <= 'z') {
            pause();  // Attendre le signal du père
            
            // Afficher le nombre de lettres selon la séquence
            int nb_lettres = sequence[idx_seq];
            
            for (int i = 0; i < nb_lettres && c <= 'z'; i++) {
                printf("%c", c);
                fflush(stdout);
                c++;
            }
            
            // Envoyer signal au père pour indiquer qu'on a fini
            kill(pid_pere, SIGUSR1);
            idx_seq++;
        }
        
        exit(0);
        
    } else {
        // ========== PROCESSUS PÈRE ==========
        // Affiche les lettres majuscules : A-Z
        
        // Séquence : 1, 2, 3, 4, 5, 6, 5 lettres majuscules
        int sequence[] = {1, 2, 3, 4, 5, 6, 5};
        char c = 'A';
        
        for (int idx = 0; idx < 7 && c <= 'Z'; idx++) {
            int nb_lettres = sequence[idx];
            
            // Afficher le nombre de lettres selon la séquence
            for (int i = 0; i < nb_lettres && c <= 'Z'; i++) {
                printf("%c", c);
                fflush(stdout);
                c++;
            }
            
            // Envoyer signal au fils pour qu'il affiche
            kill(pid_fils, SIGUSR1);
            
            // Attendre que le fils ait terminé d'afficher
            pause();
        }
        
        // Attendre la fin du processus fils
        wait(NULL);
        printf("\n");
    }
    
    return 0;
}
```

### Compilation et exécution

```bash
gcc exo5.c -o exo5
./exo5
```

### Sortie attendue

```
AabcBCdefDEFghijGHIJklmnoKLMNOpqrstuPQRSTUvwxyzVWXYZ
```

---

## Explications détaillées

### 1. Variables globales

```c
pid_t pid_fils;
pid_t pid_pere;
```

Ces variables stockent les PIDs des processus pour permettre la communication via `kill()`.

- `pid_fils` : Initialisé par le père avec le retour de `fork()`
- `pid_pere` : Récupéré par le fils avec `getppid()`

### 2. Gestionnaire de signal

```c
void handler(int sig) {
    // Le handler ne fait rien, il sert juste à réveiller le processus
}
```

**Rôle :** Ce handler minimaliste permet de débloquer la fonction `pause()`. Sans handler, le signal terminerait le processus (comportement par défaut de SIGUSR1).

**Installation :**
```c
signal(SIGUSR1, handler);
```

Ceci remplace le comportement par défaut de SIGUSR1 par notre handler personnalisé.

### 3. Processus FILS

```c
if (pid_fils == 0) {
    pid_pere = getppid();
    
    int sequence[] = {3, 3, 4, 5, 6, 5};
    int idx_seq = 0;
    char c = 'a';
    
    while (c <= 'z') {
        pause();  // Bloquer en attendant SIGUSR1
        
        int nb_lettres = sequence[idx_seq];
        
        for (int i = 0; i < nb_lettres && c <= 'z'; i++) {
            printf("%c", c);
            fflush(stdout);
            c++;
        }
        
        kill(pid_pere, SIGUSR1);  // Notifier le père
        idx_seq++;
    }
    
    exit(0);
}
```

**Déroulement :**
1. **Récupère** le PID du père avec `getppid()`
2. **Définit** la séquence du nombre de lettres à afficher
3. **Boucle** tant que toutes les lettres ne sont pas affichées :
   - Se **bloque** avec `pause()`
   - **Affiche** le nombre de lettres selon la séquence
   - **Envoie** SIGUSR1 au père pour signaler la fin
   - **Passe** à l'élément suivant de la séquence

**Fonction `fflush(stdout)` :**  
Force l'affichage immédiat sans attendre un retour à la ligne. Essentiel pour la synchronisation visuelle.

### 4. Processus PÈRE

```c
else {
    int sequence[] = {1, 2, 3, 4, 5, 6, 5};
    char c = 'A';
    
    for (int idx = 0; idx < 7 && c <= 'Z'; idx++) {
        int nb_lettres = sequence[idx];
        
        for (int i = 0; i < nb_lettres && c <= 'Z'; i++) {
            printf("%c", c);
            fflush(stdout);
            c++;
        }
        
        kill(pid_fils, SIGUSR1);  // Réveiller le fils
        pause();                   // Attendre que le fils finisse
    }
    
    wait(NULL);
    printf("\n");
}
```

**Déroulement :**
1. **Définit** la séquence du nombre de lettres majuscules
2. **Boucle** sur 7 itérations :
   - **Affiche** le nombre de majuscules selon la séquence
   - **Envoie** SIGUSR1 au fils
   - **Se bloque** avec `pause()` en attendant la réponse
3. **Attend** la terminaison du fils avec `wait(NULL)`
4. **Affiche** un retour à la ligne

### 5. Synchronisation avec `pause()` et `kill()`

| Fonction | Rôle | Utilisation |
|----------|------|-------------|
| `pause()` | Suspend le processus jusqu'à réception d'un signal | Synchronisation passive |
| `kill(pid, signal)` | Envoie un signal à un processus | Communication active |
| `signal(sig, handler)` | Installe un gestionnaire de signal | Configuration |

**Séquence typique :**
```
Père                           Fils
│                               │
├─ printf("A")                  │
├─ kill(fils, SIGUSR1) ────────>│
├─ pause() ─────────┐           ├─ (débloqué)
│                   │           ├─ printf("abc")
│                   │           ├─ kill(père, SIGUSR1)
│ <─────────────────┘           ├─ pause() ───────┐
├─ (débloqué)                   │                 │
├─ printf("BC")                 │                 │
└─ ...                          └─                └─
```

**Conclusion :**  
Ce programme démontre la puissance de la **communication inter-processus par signaux** pour créer une synchronisation stricte entre processus. L'utilisation de `SIGUSR1`, `pause()` et `kill()` permet de contrôler précisément l'ordre d'exécution et de produire un motif complexe d'affichage alterné.
