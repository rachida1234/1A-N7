clear all;
close all %(à supprimer pour la derniere question :Remi compile porteuse et apres ce fichier)
Fe=10000;
Fp=2000;
Rb=2000; %debit binaire en kbauds
alpha=0.35; %Roll-off
span=4; %paramètre de cos surelevé
nb_bits=10000;
%% Question1:
 %Traçage des signaux générés:
bits=randi([0 1],1,nb_bits);
ak=2*bits(1:2:end)-1;
bk=2*bits(2:2:end)-1;
dk=ak+1i*bk;%Mapping complexe

%surechantillonage:
Ts=1/Rb;
Ns=Ts*Fe;
N0=span*Ns/2;
suite_dirac_ak=kron(ak,[1 zeros(1,Ns-1)]);
suite_dirac_bk=kron(bk,[1 zeros(1,Ns-1)]);
%Filtrage de mise en forme :
h=rcosdesign(alpha,span,Ns,'sqrt'); %reponse impultionnelle

% génération du signal sur les voies en phase:
I=filter(h,1,[suite_dirac_ak zeros(1,N0)]);
I=I(N0+1:end);
figure(1);plot(linspace(-Fe,Fe,length(I)),I);
title("Signal sur les voies en phase");
xlabel('Frequence(Hz)');
ylabel('I');
% génération du signal sur les voies en quadrarture:
Q=filter(h,1,[suite_dirac_bk zeros(1,N0)]);
Q=Q(N0+1:end);
figure(2);plot(linspace(-Fe,Fe,length(Q)),Q);
title("Signal sur les voies en quadrature");
xlabel('Frequence(Hz)');
ylabel('Q');

%% Question2:
Enveloppe=I+1i*Q; %Enveloppe complexe
DSP=fftshift(1/length(Enveloppe)*abs(fft(Enveloppe).^2));
figure(3);plot(DSP);
title("Densité spectrale de puissance du signal transmis ");
xlabel('Frequence(Hz)');
ylabel('DSP');

%% Question3:
 %Demodulation bande de base :
 z=filter(h,1,[Enveloppe zeros(1,N0)]);
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
pre=mean(abs(Enveloppe).^2); %Puissance de l'enveloppe complexe
rsb_db = 0:6;
M=4;
TEB_bruit_pb=zeros(1,length(rsb_db));
for i = 1:length(rsb_db)
    rsb_dec = 10^(rsb_db(i)/10);
    %Ajout de bruit :
    p_bruit=pre*Ns/(2*rsb_dec*log2(M));
    bruitQ=sqrt(p_bruit)*randn(1,length(Enveloppe));
    bruitI=sqrt(p_bruit)*randn(1,length(Enveloppe));
    bruit = (bruitI + 1i*bruitQ);
    x_sortie_bruit=Enveloppe+bruit;
    %Demodulation de base :
     z_bruit=filter(h,1,[x_sortie_bruit zeros(1,N0)]);
     z_bruit=z_bruit(N0+1:end);
    %Echantillonnage:
     zm_bruit=z_bruit(1:Ns:end);
    %Decision :
     Indice1=find(real(zm_bruit)>=0 & imag(zm_bruit)>=0);  %Indice de 1+i
     Indice2=find(real(zm_bruit)>=0 & imag(zm_bruit)<=0); %Indice de 1-i
     Indice3=find(real(zm_bruit)<=0 & imag(zm_bruit)>=0); %Indice de -1+i
     Indice4=find(real(zm_bruit)<=0 & imag(zm_bruit)<=0); %Indice de -1-i
     dk_decides_bruit=zm_bruit;
     dk_decides_bruit(Indice1)=1+1i;
     dk_decides_bruit(Indice2)=1-1i;
     dk_decides_bruit(Indice3)=-1+1i;
     dk_decides_bruit(Indice4)=-1-1i;
     TES_bruit=(length(find(dk_decides_bruit~=dk))/length(dk));
     TEB_bruit_pb(i)=TES_bruit/4;
       
    
end

figure(4);semilogy(rsb_db, TEB_bruit_pb,'r-o');
xlabel('Eb/N0(dB)');
ylabel('TEB');
title("TEB en fonction du rapport signal sur bruit");

%% Question 5: traçage des constellations
    % Constellations en sortie du mapping
    figure(5);plot(real(dk),imag(dk),'ored','linewidth',2);
    title('Constellations en sortie du mapping');
    axis([-2 2 -2 2]);
    % Constellations en sortie de l’échantillonneur
    figure(6);plot(real(zm_bruit),imag(zm_bruit),'ored','linewidth',2);
    axis([-2 2 -2 2]);
    title('Constellations en sortie de l’échantillonneur');

%% Question 6 : Comparaison du TEB de la chaine sur fréquence porteuse et du passe bas équivalent
figure(7);semilogy(rsb_db, TEB_bruit_pb,'b-o');
hold on;
xlabel('Eb/N0(dB)');
ylabel('TEB');
title("Comparaison du TEB(passe bas équivalent) et TEB(frequence porteuse");
rsb_dec=10.^(rsb_db/10);
TEB_theorique=(1/2)*qfunc(sqrt(2*rsb_dec));
legend('TEB porteuse','TEB théorique','TEB passe bas');
