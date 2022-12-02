#include <stdlib.h> 
#include <stdio.h>
#include <assert.h>
#include <stdbool.h>
#define capacite 5
// Definition du type monnaie
struct monnaie{
  float valeur;
  char devise;
};
typedef struct monnaie monnaie;

/**
 * \brief Initialiser une monnaie 
 * \param[out] monnaie à initialiser
 * \param[in] valeur de monnaie
  * \param[in] devise de monnaie
 * \pre 
 * // valeur>0.0
 */
 
 
void initialiser(monnaie* m,char devise,float valeur){
    assert(valeur>=0.0);
    m->valeur=valeur;
    m->devise=devise;
}


/**
 * \brief Ajouter une monnaie m2 à une monnaie m1 
 * \param[in] monnaie m1
 * \param[in out] monnaie m2 qui va recevoir m1
 * //
 */ 
bool ajouter(monnaie* m1,monnaie* m2){
  
  if (m1->devise==m2->devise){
      m2->valeur+=m1->valeur;
  }
      
  else {
      return false;    }
}


/**
 * \brief Tester Initialiser 
 * \param[out] m1 initialisée
 * // TODO
 */ 
void tester_initialiser(){
       monnaie* m1= NULL;
       initialiser(m1,1,'$');
       assert(m1->valeur==1 && m1->devise=='$');
}

/**
 * \brief Tester Ajouter 
 * \param[]
 * // TODO
 */ 
void tester_ajouter(){
     monnaie* m1=NULL;
     monnaie* m2= NULL;
     initialiser(m1,1,'$');
     initialiser(m2,10,'$');
     ajouter(m1,m2);
     assert(m2->valeur==11);
     assert(m1->valeur==1);
}



int main(void){
    // Un tableau de 5 monnaies
    typedef struct monnaie porte_monnaie[capacite];
    porte_monnaie Tab;
    float valeur;
    char devise;
    //Initialiser les monnaies
    for (int i=0;i<capacite;i++) {
        printf("entrer la valeur de la monnaie %d=",i+1);
        scanf("%f",&valeur);
        printf("entrer sa devise =");
        scanf("%c",&devise);
        printf("\n");
        initialiser(&Tab[i],valeur,devise);
        }
 
    // Afficher la somme des toutes les monnaies qui sont dans une devise entrée par l'utilisateur.
    char devise_souhaitee;
    printf("entrer une devise que vous souhaitez calculer sa somme = ");
    scanf("%s",&devise_souhaitee);
    printf("\n");
    monnaie Somme_monnaie;
    for (int i=0;i<capacite;i++){
        if (Tab[i].devise=devise_souhaitee){
            ajouter(&Tab[i],&Somme_monnaie);
        } 
    }
    printf("la somme des monnaies de %c est %f\n",devise_souhaitee,Somme_monnaie.valeur);

    return EXIT_SUCCESS;
}


