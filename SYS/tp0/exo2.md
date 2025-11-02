# TP Processus et Signaux — USTHB

**Module:** Système d'exploitation  
**Sujet:** Gestion des processus et signaux sous Linux  
**Année:** 2025-2026  
**Étudiant:** Abdenour GACEM  
**Filière:** M1 RSD  

---

## 🧩 Exercice 2 — Synchronisation avec `wait()` et `waitpid()`

## Énoncé
Les fonctions `wait(..)` et `waitpid(..)`.
	
Écrire un programme dont le père, après avoir créé trois fils (f1, f2, f3), attend le retour de ces trois fils pour réaliser le calcul 3 × 10 + 5.
	
Les données :
	- le fils f1 retourne la valeur 5
	- le fils f2 retourne la valeur 10
	- le fils f3 retourne la valeur 3
	
---
	
## Réponse
	
	
```c
	#include <stdio.h>
	#include <unistd.h>
	#include <stdlib.h>
	#include <sys/wait.h>
	
	int main()
	{
		int f1, f2, f3;
		int status;
		int valeur_f1 = 0, valeur_f2 = 0, valeur_f3 = 0;
		
		// Création du fils f1
		f1 = fork();
		if (f1 == -1) {
			perror("fork f1");
			exit(1);
		}
		if (f1 == 0) {
			// Code du fils f1
			printf("Fils f1 (pid=%d) : retourne 5 \n", getpid());
			exit(5);
		}
		
		// Création du fils f2
		f2 = fork();
		if (f2 == -1) {
			perror("fork f2");
			exit(1);
		}
		if (f2 == 0) {
			// Code du fils f2
			printf("Fils f2 (pid=%d) : retourne 10 \n", getpid());
			exit(10);
		}
		
		// Création du fils f3
		f3 = fork();
		if (f3 == -1) {
			perror("fork f3");
			exit(1);
		}
		if (f3 == 0) {
			// Code du fils f3
			printf("Fils f3 (pid=%d) : retourne 3 \n", getpid());
			exit(3);
		}
		
		// Code du père : attente des trois fils
		printf("\n Père (pid=%d) : attente des fils... \n \n", getpid());
		
		// Attente et récupération de la valeur de f1
		waitpid(f1, &status, 0);
		if (WIFEXITED(status)) {
			
			valeur_f1 = WEXITSTATUS(status);
			printf("Père : f1 terminé, valeur récupérée = %d \n", valeur_f1);
		}
		
		// Attente et récupération de la valeur de f2
		waitpid(f2, &status, 0);
		if (WIFEXITED(status)) {
			valeur_f2 = WEXITSTATUS(status);
			printf("Père : f2 terminé, valeur récupérée = %d \n", valeur_f2);
		}
		
		// Attente et récupération de la valeur de f3
		waitpid(f3, &status, 0);
		if (WIFEXITED(status)) {
			valeur_f3 = WEXITSTATUS(status);
			printf("Père : f3 terminé, valeur récupérée = %d \n", valeur_f3);
		}
		
		// Calcul : 3 × 10 + 5
		int resultat = valeur_f3 * valeur_f2 + valeur_f1;
		
		printf("\n ================================= \n");
		printf("Calcul : %d × %d + %d = %d \n", valeur_f3, valeur_f2, valeur_f1, resultat);
		printf("================================= \n");
		
		return 0;
	}
```
	
### Compilation et exécution
	
```bash
	gcc exo2.c -o exo2
	./exo2
```
	
### Sortie attendue
	
```
	Fils f1 (pid=9421) : retourne 5
	Fils f2 (pid=9422) : retourne 10
	Fils f3 (pid=9423) : retourne 3
	
	Père (pid=9420) : attente des fils...
	
	Père : f1 terminé, valeur récupérée = 5
	Père : f2 terminé, valeur récupérée = 10
	Père : f3 terminé, valeur récupérée = 3
	
	=================================
	Calcul : 3 × 10 + 5 = 35
	=================================
```
