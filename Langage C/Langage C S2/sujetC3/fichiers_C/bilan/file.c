/**
 *  \author Xavier Crégut <nom@n7.fr>
 *  \file file.c
 *
 *  Objectif :
 *	Implantation des opérations de la file
*/

#include <malloc.h>
#include <assert.h>

#include "file.h"


void initialiser(File *f)
{   //Allocation de memoire: 
    f->queue=malloc(sizeof(Cellule));
    f->tete=malloc(sizeof(Cellule));
    f->queue=NULL;
    f->tete=NULL;

    assert(est_vide(*f)); //la file sera initialement vide
}


void detruire(File *f)
{   if(!est_vide(*f)) {
    free(f->tete);
    f->tete=NULL;
    }
}


char tete(File f)
{    if(!est_vide(f)){ 
     return f.tete->valeur;
     }
     //sinon on retourne rien car la file est vide
}


bool est_vide(File f)
{  //Test portera sur la tete et la queue de la file
   return (f.queue==NULL)&&(f.tete==NULL);
}

/**
 * Obtenir une nouvelle cellule allouée dynamiquement
 * initialisée avec la valeur et la cellule suivante précisé en paramètre.
 */
static Cellule * cellule(char valeur, Cellule *suivante)
{
    //allocation de mÈmoire pour une cellule(char,suivante)
    Cellule *C=malloc(sizeof(suivante)+sizeof(char));
    //Affectation des valeurs d'entrÈe ‡ la cellule C:
    C->suivante=suivante;
    C->valeur=valeur;
   
    return C;
}


void inserer(File *f, char v)
{
    assert(f != NULL);
    if(f->tete!=NULL){
    //CrÈation de la nouvelle cellule avec les paramËtres d'entrÈe de la mÈthode insÈrer
    Cellule *C=cellule(v,NULL);
    f->queue->suivante=C;
    f->queue=f->queue->suivante;
    } else {
    f->tete=cellule(v,NULL);
    f->queue=f->tete;
    }
   
}

void extraire(File *f, char *v)
{
    assert(! est_vide(*f));
    assert(f != NULL);
    *v=f->tete->valeur;
    Cellule *nv_tete=f->tete->suivante;
    free(f->tete);
    f->tete=NULL;
    f->tete=nv_tete;
    
}

int longueur(File f)
{   File temporaire;
    if (f.tete != NULL){
          temporaire.tete = f.tete->suivante; 
   //la taille de la file=taille de sa tete+taille(file suivante)
          return longueur(temporaire)+1;
    } else {
      return 0;
    }
}
