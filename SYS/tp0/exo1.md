# TP Processus et Signaux — USTHB

**Module:** Système d'exploitation  
**Sujet:** Gestion des processus et signaux sous Linux  
**Année:** 2025-2026  
**Étudiant:** Abdenour GACEM  
**Filière:** M1 RSD  

---

## ⚙️ Environnement

- **OS:** Linux (Parrot)
- **Compilateur:** `gcc`
- **Commandes utiles:** `ps`, `pstree`, `kill`, `man`
- **Notions étudiées:** `fork()`, `wait()`, `waitpid()`, `exit()`, `getpid()`, `getppid()`

---

## 🧩 Exercice 1 — Création de processus avec `fork()`

## Énoncé

### Question 1
A partir d'une console, lancer un éditeur de texte et saisir le code du programme suivant.

```c
// fils.c
#include <stdio.h>
#include <unistd.h> // fork
#include <stdlib.h> // exit
void main()
{
	int pid= fork();
	if (pid == - 1)
	{ /* code si échec : */
		perror ( "fork " ) ;
		exit(1) ; //sortir sur un code d'erreur
	}
	if (pid==0)
	{
		// Code du fils
		printf("Début fils \n ");
		printf("Processus fils de pid=%d, ppid=%d \n ", getpid(), getppid());
		sleep(6);
		exit(0);
		// Fin du processus fils
	}
	// Suite code du père, si pid > 0
	sleep(2);
	printf("Processus père de pid=%d, ppid=%d \n ", getpid(), getppid());
}
```

### Question 2
Compiler et exécuter le programme généré. Que font les fonctions `getpid()`, `getppid()` et `exit()` ? Modifier pour que le père affiche aussi le pid du fils créé. Utiliser `man`.

### Question 3
Exécuter la commande shell `ps aux` à partir d'une autre fenêtre du terminal pour visualiser les processus créés. Trouver qui est le processus père de votre programme (le père ici).

### Question 4
Modifier le programme précédent pour que le père crée n fils. Chaque fils devra afficher un message du type "fils de pid x : bonjour, le pid de mon père est y" au départ de son exécution, puis dormir 6 secondes et afficher un message du type "fils de pid x : au revoir, pid de mon père est y" à la fin de son exécution.

### Question 5
Que remarquez-vous ? NB. vérifier les valeurs du pid du père au bonjour et au revoir de chaque fils.

### Question 6
Processus orphelins : Que se passe-t-il quand un processus devient orphelin ? Que faut-il faire pour éviter cette situation ?

---

## Réponses

### 1. Code initial

```c
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

void main()
{
	int pid = fork();
	
	if (pid == -1) {
		perror("fork");
		exit(1);
	}
	
	if (pid == 0) {
		// Code du fils
		printf("Début fils \n ");
		printf("Processus fils de pid=%d, ppid=%d \n ", getpid(), getppid());
		sleep(6);
		exit(0);
	}
	
	// Suite code du père
	sleep(2);
	printf("Processus père de pid=%d, ppid=%d \n ", getpid(), getppid());
}
```

**Compilation et exécution :**
```bash
gcc fils.c -o fils
./fils
```

```
Début fils 
Processus fils de pid=19814, ppid=19813
Processus père de pid=19813, ppid=15674
```

### 2. Fonctions et modification

**Rôle des fonctions :**

- `getpid()` : Retourne le PID (Process ID) du processus courant
- `getppid()` : Retourne le PPID (Parent Process ID), c'est-à-dire le PID du processus père
- `exit(code)` : Termine le processus et retourne un code de sortie au processus père

**Code modifié avec affichage du PID du fils par le père :**

```c
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

void main()
{
	int pid = fork();
	
	if (pid == -1) {
		perror("fork");
		exit(1);
	}
	
	if (pid == 0) {
		// Code du fils
		printf("Début fils \n ");
		printf("Processus fils de pid=%d, ppid=%d \n ", getpid(), getppid());
		sleep(6);
		exit(0);
	}
	
	// Suite code du père
	sleep(2);
	printf("Processus père de pid=%d, ppid=%d, pid_fils=%d \n ", 
		   getpid(), getppid(), pid);
}
```
On lance le programme: 
```
./fils
```

```
Début fils
Processus fils de pid=20060, ppid=20059
Processus père de pid=20059, ppid=15674, pid_fils=20060
```

### 3. Visualisation des processus

**Commande à exécuter dans un autre terminal :**
```bash
ps aux | grep fils
```

**Le père :**
```
abdenour    8422  0.0  0.0   2472  1024 pts/1    S    18:36   0:00 ./fils

```

### 4. Création de n fils

```c
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
	int n = 1; 
	
	if (argc == 2) {
		n = atoi(argv[1]);
	}
	
	printf("Création de %d fils... \n ", n);
	
	for(int i = 0; i < n; i++) {
		int pid = fork();
		if (pid == -1) {
			perror("fork");
			exit(1);
		}
		if (pid == 0) {
			// Code du fils
			int ppid_avant = getppid();
			printf("fils de pid %d : bonjour, le pid de mon père est %d \n ", 
				   getpid(), ppid_avant);
			sleep(6);
			int ppid_apres = getppid();
			printf("fils de pid %d : au revoir, pid de mon père est %d \n ", 
				   getpid(), ppid_apres);
			exit(0);
		}
	}
	
	// Le père NE attend PAS ses fils - il se termine immédiatement
	printf("Père terminé, pid=%d \n ", getpid());
	return 0;
}
```

### 5. Observation

**Ce qu'on remarque :**

Lorsqu'on compare les valeurs du PPID (pid du père) au moment du "bonjour" et du "au revoir" de chaque fils, on peut observer que :

- Au moment du "bonjour", le PPID correspond bien au PID du processus père
- Au moment du "au revoir" (après 6 secondes), si le père s'est terminé avant, le PPID change et devient généralement 1 (ou le PID du processus init/systemd)

**Exemple de sortie :**
On lance le programme: 
```
./fils 3
```

```
Création de 3 fils...
Père terminé, pid=17939
fils de pid 17940 : bonjour, le pid de mon père est 17939
fils de pid 17942 : bonjour, le pid de mon père est 17939
fils de pid 17941 : bonjour, le pid de mon père est 17939
fils de pid 17940 : au revoir, pid de mon père est 1575
fils de pid 17942 : au revoir, pid de mon père est 1575
fils de pid 17941 : au revoir, pid de mon père est 1575
```

Les fils deviennent **orphelins** car le père se termine avant eux.

### 6. Processus orphelins

**Qu'est-ce qu'un processus orphelin ?**

Un processus devient orphelin lorsque son processus père se termine avant lui. Dans ce cas, le système adopte automatiquement le processus orphelin en changeant son PPID pour qu'il devienne un enfant du processus init (PID = 1) ou le PId du systemd ( dans notre cas PID = 1575 ).

**Conséquences :**
- Le processus continue de s'exécuter normalement
- Son PPID devient 1 (init/systemd)
- Le processus init se charge de récupérer son code de sortie

**Comment éviter cette situation ?**

Pour éviter que les fils deviennent orphelins, le père doit **attendre** la terminaison de tous ses fils avant de se terminer lui-même. On utilise les fonctions :

- `wait(NULL)` : Attend la terminaison d'un fils quelconque
- `waitpid(pid, &status, 0)` : Attend la terminaison d'un fils spécifique

**Code corrigé avec wait() :**

```c
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>

int main(int argc, char *argv[])
{
	int n = 1;
	
	if (argc == 2) {
		n = atoi(argv[1]);
		if (n <= 0) {
			printf("Erreur: le nombre de fils doit être positif \n");
			exit(1);
		}
	} else if (argc > 2) {
		printf("Usage: %s [nombre_de_fils] \n ", argv[0]);
		printf("Exemple: %s 3    (crée 3 fils) \n ", argv[0]);
		printf("Exemple: %s      (crée 1 fils par défaut) \n ", argv[0]);
		exit(1);
	}
	
	printf("Création de %d fils... \n", n);
	
	for(int i = 0; i < n; i++) {
		int pid = fork();
		if (pid == -1) {
			perror("fork");
			exit(1);
		}
		if (pid == 0) {
			printf("fils de pid %d : bonjour, le pid de mon père est %d \n ", 
				   getpid(), getppid());
			sleep(6);
			printf("fils de pid %d : au revoir, pid de mon père est %d \n ", 
				   getpid(), getppid());
			exit(0);
		}
	}
	
	for(int i = 0; i < n; i++) {
		wait(NULL);
	}
	
	printf("Tous les fils sont terminés. Processus père (%d) se termine.\n ", getpid());
	return 0;
}
```

**Résultat avec wait() :**
On lance le programme: 
```
./fils 4
```

```
Création de 4 fils...
fils de pid 14036 : bonjour, le pid de mon père est 14035
fils de pid 14037 : bonjour, le pid de mon père est 14035
fils de pid 14038 : bonjour, le pid de mon père est 14035
fils de pid 14039 : bonjour, le pid de mon père est 14035
fils de pid 14036 : au revoir, pid de mon père est 14035
fils de pid 14037 : au revoir, pid de mon père est 14035
fils de pid 14038 : au revoir, pid de mon père est 14035
fils de pid 14039 : au revoir, pid de mon père est 14035
Tous les fils sont terminés. Processus père (14035) se termine.
```

Maintenant, le PPID reste constant et les fils ne deviennent plus orphelins.
'''
