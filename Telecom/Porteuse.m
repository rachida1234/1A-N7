clear all;
close all;
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% CHAINE SUR FREQUENCE PORTEUSE
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
Fe=10000;
Fp=2000;
Rb=2000; %debit binaire en kbauds
alpha=0.35; %Roll-off
span=4; %paramètre de cos surelevé
nb_bits=10000;
%% Question1:
 %Traçage des signaux générés:

%Génération de l'information binaire
bits=randi([0 1],1,nb_bits);
ak=2*bits(1:2:end)-1;
bk=2*bits(2:2:end)-1;
dk=ak+1i*bk; %Mapping complexe

%surechantillonage:
Ts=1/Rb;
Ns=Ts*Fe;
N0=span*Ns/2;
suite_dirac_dk=kron(dk,[1 zeros(1,Ns-1)]);

%Filtrage de mise en forme :
h=rcosdesign(alpha,span,Ns,'sqrt'); %reponse impultionnelle
x_e=filter(h,1,[suite_dirac_dk zeros(1,N0)]); %Signal mis en forme
x_e=x_e(N0+1:end);
%Visualisation de la densité spectrale du filtre
figure(1);plot(linspace(0,Ts,length(h)),h);
ylabel("Réponse impulsionnelle h(t)");
xlabel('t')

% génération du signal sur les voies en phase:
I=real(x_e);
figure(2);subplot(2,1,1);
plot(I);
axis([0 length(I)-1 -1 1]);
title("Signal sur les voies en phase");
xlabel('Temps en s');
ylabel('I');

% génération du signal sur les voies en quadrarture:
Q=imag(x_e);
subplot(2,1,2);
plot(Q);
axis([0 length(Q)-1 -1 1]);
title("Signal sur les voies en quadrature");
xlabel('Temps en s');
ylabel('Q');

% Transposition en fréquence:
T=(1:length(x_e))/Fe;
x=real(x_e.*exp(2*1i*Fp*pi*T));
figure(4);plot(x);%Visualisation du signal transmis
title("Signal transmis sur frequence porteuse");
xlabel('Frequence(Hz)');
ylabel('x');

%% Question2:
F=linspace(-Fe/2,Fe/2,length(x));
DSP=fftshift(1/length(x)*abs(fft(x).^2));
figure(5);plot(F,DSP);
title("Densité spectrale de puissance du signal transmis sur fréquence porteuse");
xlabel('Frequence(Hz)');
ylabel('DSP');

%% Question3:
 %Retour en bande de base:
 x1=x.*cos(2*pi*Fp*T);
 x2=x.*sin(2*pi*Fp*T);
 filtre_passe_bas=ones(1,3);
 x1_filtre=filter(filtre_passe_bas,1,x1 );
 x2_filtre=filter(filtre_passe_bas,1,x2);
 x_sortie=x1_filtre-1i*x2_filtre;
 figure(3);plot(x_sortie);
 xlabel('temps en s');
 ylabel('x_sortie');
 title('signal transmis');

 %Demodulation de base :
 hr=h;
 z=filter(hr,1,[x_sortie zeros(1,N0)]);
 z=z(N0+1:end);

 %Echantillonnage:
 zm=z(1:Ns:end);

 %Decision :
 Indice1=find(real(zm)>=0 & imag(zm)>=0);  %Indice de 1+i
 Indice2=find(real(zm)>=0 & imag(zm)<=0); %Indice de 1-i
 Indice3=find(real(zm)<=0 & imag(zm)>=0); %Indice de -1+i
 Indice4=find(real(zm)<=0 & imag(zm)<=0); %Indice de -1-i
 dk_decides=zm;
 dk_decides(Indice1)=1+1i;
 dk_decides(Indice2)=1-1i;
 dk_decides(Indice3)=-1+1i;
 dk_decides(Indice4)=-1-1i;

 %Demapping :
 ak_decides=real(dk_decides);
 bk_decides=imag(dk_decides);
 TES=(length(find(dk_decides~=dk))/length(dk));
 TEB_QPSK=TES/4;

 %% Question4:
 rsb_db=0:6;
 TEB_bruit=zeros(1,length(rsb_db));
 pr=mean(abs(x).^2);
        for j = 1:length(rsb_db)
            rsb_dec = 10^(rsb_db(j)/10);
            sigma=pr*Ns/(2*log2(4)*rsb_dec);
            Bruit=sqrt(sigma)*randn(1, length(x));
            % Ajout de bruit 
            x_bruit = x + Bruit;
            % Retour en bande de base
            x1 = x_bruit.*cos(2*pi*Fp*T);
            x2 = x_bruit.*sin(2*pi*Fp*T);
            %filtre_passe_bas=ones(1,4);
            %x1_filtre= filter(filtre_passe_bas,1,x1);
            %x2_filtre = filter(filtre_passe_bas,1,x2);
            x_sortie_bruit = x1- 1i*x2;
           %Demodulation de base :
            z_bruit=filter(hr,1,[x_sortie_bruit zeros(1,N0)]);
            z_bruit=z_bruit(N0+1:end);
           %Echantillonnage:
            zm_bruit=z_bruit(1:Ns:end);
           %Décision :
             Indice1=find(real(zm_bruit)>=0 & imag(zm_bruit)>=0);  %Indice de 1+i
             Indice2=find(real(zm_bruit)>=0 & imag(zm_bruit)<=0); %Indice de 1-i
             Indice3=find(real(zm_bruit)<=0 & imag(zm_bruit)>=0); %Indice de -1+i
             Indice4=find(real(zm_bruit)<=0 & imag(zm_bruit)<=0); %Indice de -1-i
             dk_decides_bruit=zm_bruit;
             dk_decides_bruit(Indice1)=1+1i;
             dk_decides_bruit(Indice2)=1-1i;
             dk_decides_bruit(Indice3)=-1+1i;
             dk_decides_bruit(Indice4)=-1-1i;
           %Demapping
             ak_decides_bruit=real(dk_decides_bruit);
             bk_decides_bruit=imag(dk_decides_bruit);
             TES_bruit=(length(find(dk_decides_bruit~=dk))/length(dk));
             TEB_bruit(j)=TES_bruit/4;

        end


figure(6);semilogy(rsb_db, TEB_bruit,'r-o');
xlabel('Eb/N0(dB)');
ylabel('TEB');
title("TEB en fonction du rapport signal sur bruit");

%% Question 5:
figure(7);semilogy(rsb_db, TEB_bruit,'r-o');
hold on;
xlabel('Eb/N0(dB)');
ylabel('TEB');
title("Comparaison du TEB simulé au TEB théorique");
rsb_dec=10.^(rsb_db/10);
TEB_theorique=(1/2)*qfunc(sqrt(2*rsb_dec));
semilogy(rsb_db, TEB_theorique,'g-*');
legend('TEB simulé','TEB theorique');