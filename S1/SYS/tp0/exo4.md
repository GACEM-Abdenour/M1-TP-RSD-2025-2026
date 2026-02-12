# TP Processus et Signaux — USTHB

**Module:** Système d'exploitation  
**Sujet:** Gestion des processus et signaux sous Linux  
**Année:** 2025-2026  
**Étudiant:** Abdenour GACEM  
**Filière:** M1 RSD  

---

## 🧩 Exercice 4 — Signaux
	
## Énoncé
	
Signaux
	
NB. La commande `kill -l` permet d'obtenir la liste complète des signaux sous Linux.
	
### Question 1
Écrire un programme « infini.c » qui réalise une boucle infinie. Lancer le programme puis taper les touches "Ctrl+C". Que se passe-t-il ? Quel est le signal associé à l'action Ctrl+C ?
	
### Question 2
Relancer le programme infini. Sur un autre terminal, déterminer le PID du processus correspondant au programme infini en utilisant "ps -A". Ensuite taper "kill -9 pid". Que se passe-t-il ? Quel est le signal envoyé par kill -9 ?
	
### Question 3
Créer un programme « assassin.c ». Ce programme demande la saisie d'un PID, puis fait appel à la fonction `kill(pid, SIGINT)`. Lancer le programme infini. Sur un autre terminal, déterminer le PID du processus correspondant à infini. Lancer assassin et saisir le PID de infini. Que se passe-t-il ?
	
### Question 4
Quel est le comportement par défaut d'un processus à la réception d'un signal (selon le signal reçu) ?
	
### Question 5
Modifier le programme infini de telle sorte qu'il affiche un message « Hello ! I am here » au lieu de se terminer lorsqu'il reçoit un signal SIGINT (par Ctrl+C ou par kill).
	
---
	
## Réponses
	
### 1. Programme infini.c et signal Ctrl+C
	
**Code infini.c :**
	
```c
	#include <stdio.h>
	#include <unistd.h>
	
	int main()
	{
		printf("Programme infini lancé (PID=%d) \n", getpid());
		printf("Appuyez sur Ctrl+C pour terminer... \n \n");
		
		while(1) {
			printf("En cours d'exécution... \n");
			sleep(1);
		}
		
		return 0;
	}
```
	
**Compilation et exécution :**
	
```bash
	gcc infini.c -o infini
	./infini
```
	
**Sortie :**
	
```
	Programme infini lancé (PID=10234)
	Appuyez sur Ctrl+C pour terminer...
	
	En cours d'exécution...
	En cours d'exécution...
	En cours d'exécution...
	^C
```
	
**Que se passe-t-il ?**
	
Lorsqu'on appuie sur `Ctrl+C`, le programme se termine immédiatement.
	
**Signal associé :**
	
Le signal envoyé par `Ctrl+C` est **SIGINT** (signal numéro 2). C'est un signal d'interruption demandant au processus de se terminer proprement.
	
**Vérification :**
	
```bash
	kill -l | grep INT
	# Affiche : 2) SIGINT
```
	
### 2. Commande kill -9
	
**Étapes :**
	
1. Lancer le programme infini dans un terminal :
```bash
	./infini
```
	
2. Dans un autre terminal, trouver le PID :
```bashObservations
	ps -A | grep infini
	# Résultat : 24436 pts/1    00:00:00 infini
```
	
3. Envoyer le signal avec kill -9 :
```bash
	kill -9 24436
```
	
**Que se passe-t-il ?**
	
Le programme se termine **immédiatement et brutalement** sans possibilité de résistance, et on voit ce message :
```
[1]    24436 killed     ./infini
```
	
**Signal envoyé :**
	
`kill -9` envoie le signal **SIGKILL** (signal numéro 9). C'est un signal de terminaison forcée qui **ne peut pas être intercepté, ignoré ou géré** par le processus.
	
**Différence entre SIGINT et SIGKILL :**
	
| Signal | Numéro | Peut être intercepté ? | Usage |
|--------|--------|------------------------|-------|
| SIGINT | 2 | ✅ Oui | Demande de terminaison "propre" |
| SIGKILL | 9 | ❌ Non | Terminaison forcée immédiate |
	
### 3. Programme assassin.c
	
**Code assassin.c :**
	
```c
	#include <stdio.h>
	#include <signal.h>
	#include <stdlib.h>
	
	int main()
	{
		int pid;
		
		printf("=== Programme Assassin ===\\n");
		printf("Entrez le PID du processus à terminer : ");
		scanf("%d", &pid);
		
		printf("\\nEnvoi du signal SIGINT au processus %d...\\n", pid);
		
		if (kill(pid, SIGINT) == 0) {
			printf("✓ Signal SIGINT envoyé avec succès au PID %d\\n", pid);
		} else {
			perror("✗ Erreur lors de l'envoi du signal");
			exit(1);
		}
		
		return 0;
	}
	```
	
	**Compilation :**
	
	```bash
	gcc assassin.c -o assassin
	```
	
	**Test :**
	
	Terminal 1 :
	```bash
	./infini
	Programme infini lancé (PID=10456)
	Appuyez sur Ctrl+C pour terminer...
	
	En cours d'exécution...
	En cours d'exécution...
	```
	
	Terminal 2 :
	```bash
	ps -A | grep infini
	# 10456 pts/1    00:00:00 infini
	
	./assassin
	=== Programme Assassin ===
	Entrez le PID du processus à terminer : 10456
	
	Envoi du signal SIGINT au processus 10456...
	✓ Signal SIGINT envoyé avec succès au PID 10456
```
	
**Que se passe-t-il ?**
	
Dans le Terminal 1, le programme `infini` se termine exactement comme si on avait appuyé sur `Ctrl+C`. Le programme `assassin` envoie le même signal SIGINT mais de manière programmatique via la fonction `kill()`.
	
### 4. Comportement par défaut des signaux
	
Le comportement par défaut d'un processus à la réception d'un signal dépend du type de signal :
	
| Action par défaut | Signaux concernés | Description |
|-------------------|-------------------|-------------|
| **Term** (Terminaison) | SIGINT, SIGTERM, SIGQUIT | Le processus se termine |
| **Kill** (Terminaison forcée) | SIGKILL, SIGSTOP | Terminaison immédiate, non interceptable |
| **Core** (Core dump) | SIGSEGV, SIGABRT, SIGFPE | Terminaison + génération d'un fichier core |
| **Ign** (Ignoré) | SIGCHLD, SIGURG | Le signal est ignoré |
| **Stop** (Suspension) | SIGTSTP (Ctrl+Z) | Le processus est suspendu |
| **Cont** (Continuation) | SIGCONT | Reprend un processus suspendu |
	
**Principaux signaux :**
	
```bash
kill -l
```
	
Sortie (extrait) :
```
1) SIGHUP       2) SIGINT       3) SIGQUIT      4) SIGILL
5) SIGTRAP      6) SIGABRT      7) SIGBUS       8) SIGFPE
9) SIGKILL     10) SIGUSR1     11) SIGSEGV     12) SIGUSR2
13) SIGPIPE     14) SIGALRM     15) SIGTERM     16) SIGSTKFLT
17) SIGCHLD     18) SIGCONT     19) SIGSTOP     20) SIGTSTP
```
	
**Signaux non interceptables :**
- **SIGKILL (9)** : Terminaison immédiate
- **SIGSTOP (19)** : Suspension immédiate

Tous les autres signaux peuvent être interceptés et gérés par le processus via la fonction `signal()` ou `sigaction()`.
	
### 5. Interception de SIGINT
	
**Code infini_modifie.c :**
	
```c
	#include <stdio.h>
	#include <signal.h>
	#include <unistd.h>
	
	// Gestionnaire de signal pour SIGINT
	void gestionnaire_sigint(int sig)
	{
		printf("\n \n✋ Hello ! I am here \n \n");
		// Le programme continue après avoir affiché le message
	}
	
	int main()
	{
		// Installation du gestionnaire de signal
		signal(SIGINT, gestionnaire_sigint);
		
		printf("Programme infini modifié lancé (PID=%d) \n", getpid());
		printf("Appuyez sur Ctrl+C (le programme ne se terminera pas) \n");
		printf("Pour terminer : utilisez Ctrl+\\\\ ou kill -9 %d \n \n", getpid());
		
		while(1) {
			printf("En cours d'exécution... \n");
			sleep(4);
		}
		
		return 0;
	}
	```
	
	**Compilation et exécution :**
	
	```bash
	gcc infini_modifie.c -o infini_modifie
	./infini_modifie
	```
	
	**Sortie avec Ctrl+C :**
	
	```
	Programme infini modifié lancé (PID=10678)
	Appuyez sur Ctrl+C (le programme ne se terminera pas)
	Pour terminer : utilisez Ctrl+\ ou kill -9 10678
	
	En cours d'exécution...
	En cours d'exécution...
	En cours d'exécution...
	^C
	
	✋ Hello ! I am here
	
	En cours d'exécution...
	En cours d'exécution...
	^C
	
	✋ Hello ! I am here
	
	En cours d'exécution...
```
