
close all;
clear all;
format long;
%% Variation de nombre d'itération en fonction de p :
%%%%%%%%%%%%
% PARAMÈTRES
%%%%%%%%%%%%

% taille de la matrice symétrique
n = 500; 

% type de la matrice (voir matgen_csad)
% imat == 1 valeurs propres D(i) = i
% imat == 2 valeurs propres D(i) = random(1/cond, 1) avec leur logarithmes
%                                  uniformément répartie, cond = 1e10
% imat == 3 valeurs propres D(i) = cond**(-(i-1)/(n-1)) avec cond = 1e5
% imat == 4 valeurs propres D(i) = 1 - ((i-1)/(n-1))*(1 - 1/cond) avec cond = 1e2


for imat = 1:4
    
    % on génère la matrice (1) ou on lit dans un fichier (0)
    % si vous avez déjà généré la matrice d'une certaine taille et d'un type donné
    % vous pouvez mettre cette valeur à 0
    genere = 1;
    
    % tolérance
    eps = 1e-8;
    % nombre d'itérations max pour atteindre la convergence
    maxit = 10000;
    % nombre maximum de couples propres calculés
    m = 20;
    percentage = 0.4;
    % Génération d'une matrice rectangulaire aléatoire symétrique définie
    % positive A de taille (n x n)
    % A matrice
    % D ses valeurs propres
    fprintf('\n******* création de la matrice ******\n');
    t_v =  cputime;
    [A, D, ~] = matgen_csad(imat,n);
    t_v = cputime-t_v;
    fprintf('\nTemps de création de la matrice = %0.3e\n',t_v)
    save(['A_' num2str(n) '_' num2str(imat)], 'A', 'D', 'imat', 'n');

    % p le vecteurs des valeurs de p à tester pour A^p
    p=1:1:10;

    % temps de calcul
    temps_IT = zeros(1,length(p));

    for i = 1:length(p)

        % Retenir le temps courant
        t_v1 = cputime;   

        % Calculer les valeurs propres nécessaires avec subspace_iter_v0
        [ W, V ] = eig(A);

        % Comparer le temps courant au temps retenu
        t_v1 = cputime-t_v1;
        temps_IT(i)=t_v1;

    end
    
    % Afficher
    figure(1);plot(p,temps_IT,'-');
    hold on;

end

% Légende de la courbe
xlabel('p');
ylabel("Processing time (s)");
legend('Mat1','Mat2','Mat3','Mat4');
title("Variation of processing time as a function of p");