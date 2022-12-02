clear all;
close all;
%Déclaration des variables
Fe=10000;
Rb=48000/8; %debit binaire en bauds (1kbps=1/8kbauds)
alpha=0.5; %Roll-off
span=4; %paramètre de cos surelevé
nb_bits=120000;
rsb_db=0:6; %Rapport signal sur bruit en dB
rsb_dec=10.^(rsb_db/10);

%Génération de l'information binaire
bits = randi(2,1,nb_bits)-1;

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Question 1: %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%% chaine QPSK sans bruit:
    M_QPSK= 4;% Ordre de modulation
    nb_QPSK = log2(M_QPSK);% Nombre de bits par symbole
    %Génération des symboles binaires
    bits_QPSK = reshape(bits,[nb_bits/nb_QPSK,nb_QPSK]);
    %Mapping
    map_QPSK = pskmod(bi2de(bits_QPSK).',M_QPSK,pi/M_QPSK,'gray');
    %surechantillonage
    Rs_QPSK=Rb/nb_QPSK;
    Ns_QPSK=floor(Fe/Rs_QPSK);
    N0_QPSK=span*Ns_QPSK/2;
    suite_dirac_ak=kron(real(map_QPSK),[1 zeros(1,Ns_QPSK-1)]);
    suite_dirac_bk=kron(imag(map_QPSK),[1 zeros(1,Ns_QPSK-1)]);
    %Filtrage de mise en forme :
    h_QPSK=rcosdesign(alpha,span,Ns_QPSK,'sqrt'); %reponse impultionnelle
    %génération du signal sur les voies en phase:
    I=filter(h_QPSK,1,[suite_dirac_ak zeros(1,N0_QPSK)]);
    I=I(N0_QPSK+1:end);
    %génération du signal sur les voies en quadrarture:
    Q=filter(h_QPSK,1,[suite_dirac_bk zeros(1,N0_QPSK)]);
    Q=Q(N0_QPSK+1:end);
    Enveloppe_QPSK=I+1i*Q; %Enveloppe complexe
    %Demodulation bande de base :
    z=filter(h_QPSK,1,[Enveloppe_QPSK zeros(1,N0_QPSK)]);
    z=z(N0_QPSK+1:end);
    %Echantillonnage:
    zm_QPSK=z(1:Ns_QPSK:end);
    %Demapping
    bits_decides_QPSK = pskdemod(zm_QPSK,M_QPSK,pi/M_QPSK,'gray');
    bits_decides_QPSK = de2bi(bits_decides_QPSK.');
    bits_sortie_QPSK= bits_decides_QPSK(:)';
    %Calcul du TEB :
    TEB_QPSK= length(find(bits~=bits_sortie_QPSK))/nb_bits;

    %Constellations en sortie du mapping 
    figure(1); plot(real(map_QPSK),imag(map_QPSK),'b*','linewidth',2);
    title(' Constellations en sortie du mapping de la chaine QPSK');
    axis([-1 1 -1 1]);

%% Chaine 4-ASK sans bruit:
    M_ASK= 4;% Ordre de modulation
    nb_ASK = log2(M_ASK);% Nombre de bits par symbole
    Rs_ASK=Rb/nb_ASK;
    Ns_ASK=floor(Fe/Rs_ASK);
    N0=span*Ns_ASK/2;
    %Génération des symboles binaires
    bits_ASK = reshape(bits,[nb_bits/nb_ASK,nb_ASK]);
    %Mapping
    map_ASK = (2*bi2de(bits_ASK)-3).';
    %Surechantillonnage
    suite_dirac=kron(map_ASK,[1 zeros(1,Ns_ASK-1)]);
    %Filtrage de mise en forme :
    h_ASK=rcosdesign(alpha,span,Ns_ASK,'sqrt'); %reponse impultionnelle
    x_ASK=filter(h_ASK,1,[suite_dirac zeros(1,N0)]); %Signal mis en forme
    x_ASK=x_ASK(N0+1:end);
    %Demodulation de base :
     hr=h_ASK;
     z_ASK=filter(hr,1,[x_ASK zeros(1,N0)]);
     z_ASK=z_ASK(N0+1:end);
    %Echantillonnage:
     ze_ASK=z_ASK(1:Ns_ASK:end);
    %Demapping
    symboles_decides_ASK = zeros(1,length(ze_ASK));
    for i=1:length(symboles_decides_ASK)
        if (ze_ASK(i)>=2)
            symboles_decides_ASK(i) = 3;
        elseif (ze_ASK(i) >= 0)
            symboles_decides_ASK(i) = 1;
        elseif (ze_ASK(i) <= -2)
            symboles_decides_ASK(i) = -3;
        else
            symboles_decides_ASK(i) = -1;
        end
    end
    bits_decides_ASK = reshape(de2bi((symboles_decides_ASK+3)/2),1,nb_bits);
    TEB_ASK = length(find(bits~=bits_decides_ASK))/nb_bits;

    %Constellations en sortie du mapping 
    figure(2); plot(real(map_ASK),imag(map_ASK),'g*','linewidth',2);
    title(' Constellations en sortie du mapping de la chaine 4-ASK');
    axis([-4 4 -4 4]);

%% chaine 8-PSK sans bruit:
   M_PSK= 8;% Ordre de modulation
   nb_PSK = log2(M_PSK);% Nombre de bits par symbole
   %Génération des symboles binaires
   bits_psk = reshape(bits,[nb_bits/nb_PSK,nb_PSK]);
   %Mapping
   map_psk = pskmod(bi2de(bits_psk).',M_PSK,pi/M_PSK,'gray');
   %surechantillonage
   Rs_PSK=Rb/nb_PSK;
   Ns_PSK=floor(Fe/Rs_PSK);
   N0_PSK=span*Ns_PSK/2;
   suite_dirac_ak=kron(real(map_psk),[1 zeros(1,Ns_PSK-1)]);
   suite_dirac_bk=kron(imag(map_psk),[1 zeros(1,Ns_PSK-1)]);
   %Filtrage de mise en forme :
   h_PSK=rcosdesign(alpha,span,Ns_PSK,'sqrt'); %reponse impultionnelle
   %génération du signal sur les voies en phase:
   I=filter(h_PSK,1,[suite_dirac_ak zeros(1,N0_PSK)]);
   I=I(N0_PSK+1:end);
   %génération du signal sur les voies en quadrarture:
   Q=filter(h_PSK,1,[suite_dirac_bk zeros(1,N0_PSK)]);
   Q=Q(N0_PSK+1:end);
   Enveloppe_PSK=I+1i*Q; %Enveloppe complexe
   %Demodulation bande de base :
   z=filter(h_PSK,1,[Enveloppe_PSK zeros(1,N0_PSK)]);
   z=z(N0_PSK+1:end);
   %Echantillonnage:
   zm_PSK=z(1:Ns_PSK:end);
   %Demapping :
    bits_decides_psk = pskdemod(zm_PSK,M_PSK,pi/M_PSK,'gray');
    bits_decides_psk = de2bi(bits_decides_psk.');
    bits_sortie_psk = bits_decides_psk(:)';
    %Calcul du TEB :
    TEB_PSK= length(find(bits~=bits_sortie_psk))/nb_bits;
    
    %Constellations en sortie du mapping :
    figure(3);plot(real(map_psk),imag(map_psk),'r*','linewidth',2);
    title('Constellation en sortie du mapping de la chaine 8-psk');
    axis([-2 2 -2 2]);

%% chaine 16-QAM sans bruit:
    M_QAM = 16;% Ordre de modulation
    nb_QAM = log2(M_QAM);% Nombre de bits par symbole
    %Génération des symboles binaires
    bits_QAM = reshape(bits,[(nb_bits/nb_QAM),nb_QAM]);
    %Mapping
    map_QAM = qammod(bi2de(bits_QAM).',M_QAM,'gray');
    %surechantillonage
    Rs_QAM=Rb/nb_QAM;
    Ns_QAM=floor(Fe/Rs_QAM);
    N0_QAM=span*Ns_QAM/2;
    suite_dirac_ak=kron(real(map_QAM),[1 zeros(1,Ns_QAM-1)]);
    suite_dirac_bk=kron(imag(map_QAM),[1 zeros(1,Ns_QAM-1)]);
    %Filtrage de mise en forme :
    h_QAM=rcosdesign(alpha,span,Ns_QAM,'sqrt'); %reponse impultionnelle
    %génération du signal sur les voies en phase:
    I=filter(h_QAM,1,[suite_dirac_ak zeros(1,N0_QAM)]);
    I=I(N0_QAM+1:end);
    %génération du signal sur les voies en quadrarture:
    Q=filter(h_QAM,1,[suite_dirac_bk zeros(1,N0_QAM)]);
    Q=Q(N0_QAM+1:end);
    Enveloppe_QAM=I+1i*Q; %Enveloppe complexe
    %Demodulation bande de base :
    z=filter(h_QAM,1,[Enveloppe_QAM zeros(1,N0_QAM)]);
    z=z(N0_QAM+1:end);
    %Echantillonnage:
    zm_PSK=z(1:Ns_QAM:end);
    %Demapping :
    bits_decides_QAM = qamdemod(map_QAM,M_QAM,'gray');
    bits_decides_QAM = de2bi(bits_decides_QAM.');
    bits_sortie_QAM = bits_decides_QAM(:)';
    %Calcul du TEB :
    TEB_QAM = length(find(bits~=bits_sortie_QAM))/nb_bits;

    % Constellations en sortie du mapping :
    figure(4);
    plot(real(map_QAM),imag(map_QAM),'b*','linewidth',2);
    title('Constellation en sortie du mapping de la chaine 16-QAM');
    axis([-5 5 -5 5]);

    %%%%%%%%%%%%%%%%%%%%%%%%%%% Question 2 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    %Question 2.1 : Traçage des constellations pour différentes valeurs de Eb/N0 : 
    %% Chaine QPSK :
        pre=mean(abs(Enveloppe_QPSK).^2); %Puissance de l'enveloppe complexe
        rsb_db_QPSK=6; %On prends une valeur de 6 et on change après à une val=2
        rsb_dec_QPSK = 10^(rsb_db_QPSK/10);
        %Ajout de bruit :
        p_bruit=pre*Ns_QPSK/(2*rsb_dec_QPSK*log2(M_QPSK));
        bruitQ=sqrt(p_bruit)*randn(1,length(Enveloppe_QPSK));
        bruitI=sqrt(p_bruit)*randn(1,length(Enveloppe_QPSK));
        bruit =bruitI + 1i*bruitQ;
        x_sortie_bruit1=Enveloppe_QPSK+bruit;
        %Demodulation de base :
        z_bruit1=filter(h_QPSK,1,[x_sortie_bruit1 zeros(1,N0_QPSK)]);
        z_bruit1=z_bruit1(N0+1:end);
        %Echantillonnage:
        zm_bruit_QPSK1=z_bruit1(1:Ns_QPSK:end);
        figure(5);
        plot(real(zm_bruit_QPSK1),imag(zm_bruit_QPSK1),'b*','linewidth',2);
        title("Constellation en sortie du l'échantillonneur de la chaine QPSK");
        axis([-2 2 -2 2]);
        %Decision :
        bits_decides_QPSK1 = pskdemod(zm_bruit_QPSK1,M_QPSK,pi/M_QPSK,'gray');
        bits_decides_QPSK1 = de2bi(bits_decides_QPSK1.');
        bits_sortie_QPSK1= bits_decides_QPSK1(:)';
        %Calcul du TEB :
        TEB_bruit_QPSK1= length(find(bits~=bits_sortie_QPSK))/nb_bits;

  %% chaine 4-ASK avec bruit :
       pre=mean(abs(x_ASK).^2); %Puissance de l'enveloppe complexe
       rsb_db_ASK=5; %on change la valeur en 1 après pour voir la différence
       rsb_dec_ASK = 10^(rsb_db_ASK/10);
       %Ajout de bruit :
       p_bruit=pre*Ns_ASK/(2*rsb_dec_ASK*log2(M_ASK));
       bruitQ=sqrt(p_bruit)*randn(1,length(x_ASK));
       bruitI=sqrt(p_bruit)*randn(1,length(x_ASK));
       bruit =sqrt(1/2)*(bruitI + 1i*bruitQ);
       x_sortie_bruit=x_ASK+bruit;
       z_bruit_ASK1=filter(hr,1,[x_sortie_bruit zeros(1,N0)]);
       z_bruit_ASK1=z_bruit_ASK1(N0+1:end);
       %Echantillonnage:
       ze_bruit_ASK1=z_bruit_ASK1(1:Ns_ASK:end);
       figure(6);
       plot(real(ze_bruit_ASK1),imag(ze_bruit_ASK1),'b*','linewidth',2);
       title("Constellation en sortie du l'échantillonneur de la chaine ASK");
       axis([-5 5 -5 5]);
       %Demapping
        symboles_decides_ASK = zeros(1,length(ze_ASK));
        for j=1:length(symboles_decides_ASK)
            if (ze_bruit_ASK1(j)>=2)
                symboles_decides_ASK(j) = 3;
            elseif (ze_bruit_ASK1(j) >= 0)
                symboles_decides_ASK(j) = 1;
            elseif (ze_bruit_ASK1(j) <= -2)
                symboles_decides_ASK(j) = -3;
            else
                symboles_decides_ASK(j) = -1;
            end
        end
        bits_decides_ASK = reshape(de2bi((symboles_decides_ASK+3)/2),1,nb_bits);
        TEB_bruit_ASK1 = length(find(bits~=bits_decides_ASK))/nb_bits;

       %% chaine 8-psk avec bruit :
       pre=mean(abs(Enveloppe_PSK).^2); %Puissance de l'enveloppe complexe
       rsb_db_PSK=5; %on change à la valeur 1 après
       rsb_dec_PSK = 10^(rsb_db_PSK/10);
       %Ajout de bruit :
       p_bruit=pre*Ns_PSK/(2*rsb_dec_PSK*log2(M_PSK));
       bruitQ=sqrt(p_bruit)*randn(1,length(Enveloppe_PSK));
       bruitI=sqrt(p_bruit)*randn(1,length(Enveloppe_PSK));
       bruit =(1/sqrt(2))*( bruitI + 1i*bruitQ);
       x_sortie_bruit_PSK1=Enveloppe_PSK+bruit;
       %Demodulation de base :
       z_bruit1=filter(h_PSK,1,[x_sortie_bruit_PSK1 zeros(1,N0_PSK)]);
       z_bruit1=z_bruit1(N0_PSK+1:end);
       %Echantillonnage:
       zm_bruit_PSK1=z_bruit1(1:Ns_PSK:end);
       figure(7);
       plot(real(zm_bruit_PSK1),imag(zm_bruit_PSK1),'b*','linewidth',2);
       title("Constellation en sortie du l'échantillonneur de la chaine 8-PSK");
       axis([-2 2 -2 2]);
       %Decision :
       bits_decides_bruit_PSK1 = pskdemod(zm_bruit_PSK1,M_PSK,pi/M_PSK,'gray');
       bits_decides_bruit_PSK1 = de2bi(bits_decides_bruit_PSK1.');
       bits_sortie_bruit_PSK1 = bits_decides_bruit_PSK1(:)';
       %Calcul du TEB :
       TEB_bruit_PSK1 = length(find(bits~=bits_sortie_bruit_PSK1))/nb_bits;

  %% chaine 16-QAM avec bruit :

    pre=mean(abs(Enveloppe_QAM).^2); %Puissance de l'enveloppe complexe
    rsb_db_QAM=5; %On change la valeur du rapport à 1 pour voir la différence
    rsb_dec_QAM = 10^(rsb_db_QAM/10);
    %Ajout de bruit :
     p_bruit=pre*Ns_QAM/(2*rsb_dec_QAM*log2(M_QAM));
     bruitQ=sqrt(p_bruit)*randn(1,length(Enveloppe_QAM));
     bruitI=sqrt(p_bruit)*randn(1,length(Enveloppe_QAM));
     bruit =sqrt(1/2)*(bruitI + 1i*bruitQ);
     x_sortie_bruit_QAM1=Enveloppe_QAM+bruit;
     %Demodulation de base :
     z_bruit1=filter(h_QAM,1,[x_sortie_bruit_QAM1 zeros(1,N0_QAM)]);
     z_bruit1=z_bruit1(N0_QAM+1:end);
     %Echantillonnage:
     zm_bruit_QAM1=z_bruit1(1:Ns_QAM:end);
     figure(8);
     plot(real(zm_bruit_QAM1),imag(zm_bruit_QAM1),'b*','linewidth',2);
     title("Constellation en sortie du l'échantillonneur de la chaine 16-QAM");
     axis([-6 6 -6 6]);
     %Decision :
      bits_decides_bruit_QAM = qamdemod(zm_bruit_QAM1,M_QAM,'gray');
      bits_decides_bruit_QAM = de2bi(bits_decides_bruit_QAM.');
      bits_sortie_bruit_QAM = bits_decides_bruit_QAM(:)';
     %Calcul du TEB :
      TEB_bruit_QAM1 = length(find(bits~=bits_sortie_bruit_QAM))/nb_bits;
    
  %%%%%%%%%%%%%%%%%% Question 2.2 & 2.3 : Ajout du bruit aux chaines précedentes :%%%%%%%%%%%%%%%%%%%% 

    %% chaine QPSK avec bruit :
    pre=mean(abs(Enveloppe_QPSK).^2); %Puissance de l'enveloppe complexe
    TEB_bruit_QPSK=zeros(1,length(rsb_db));
    for i = 1:length(rsb_db)
       rsb_dec = 10^(rsb_db(i)/10);
       %Ajout de bruit :
       p_bruit=pre*Ns_QPSK/(2*rsb_dec*log2(M_QPSK));
       bruitQ=sqrt(p_bruit)*randn(1,length(Enveloppe_QPSK));
       bruitI=sqrt(p_bruit)*randn(1,length(Enveloppe_QPSK));
       bruit =bruitI + 1i*bruitQ;
       x_sortie_bruit=Enveloppe_QPSK+bruit;
       %Demodulation de base :
       z_bruit=filter(h_QPSK,1,[x_sortie_bruit zeros(1,N0_QPSK)]);
       z_bruit=z_bruit(N0+1:end);
       %Echantillonnage:
       zm_bruit_QPSK=z_bruit(1:Ns_QPSK:end);
       %Decision :
       bits_decides_QPSK = pskdemod(zm_bruit_QPSK,M_QPSK,pi/M_QPSK,'gray');
       bits_decides_QPSK = de2bi(bits_decides_QPSK.');
       bits_sortie_QPSK= bits_decides_QPSK(:)';
       %Calcul du TEB :
       TEB_bruit_QPSK(i)= length(find(bits~=bits_sortie_QPSK))/nb_bits;
   end

    figure(9);semilogy(rsb_db, TEB_bruit_QPSK,'r-o');
    xlabel('Eb/N0(dB)');
    ylabel('TEB QPSK');
    title("TEB en fonction du rapport signal sur bruit");
    figure(10);semilogy(rsb_db, TEB_bruit_QPSK,'r-o');
    xlabel('Eb/N0(dB)');
    ylabel('TEB QPSK');
    title("comparaison entre TEB QPSK simulé et théorique");
    hold on;
    rsb_dec=10.^(rsb_db/10);
    TEB_theorique_QPSK=qfunc(sqrt(2*rsb_dec));
    semilogy(rsb_db, TEB_theorique_QPSK,'b-*');
    legend('TEB QPSK simulé','TEB QPSK théorique');


    %% chaine 4-ASK avec bruit :
    pre=mean(abs(x_ASK).^2); %Puissance de l'enveloppe complexe
    TEB_bruit_ASK=zeros(1,length(rsb_db));
   for i = 1:length(rsb_db)
       rsb_dec = 10^(rsb_db(i)/10);
       %Ajout de bruit :
       p_bruit=pre*Ns_ASK/(2*rsb_dec*log2(M_ASK));
       bruitQ=sqrt(p_bruit)*randn(1,length(x_ASK));
       bruitI=sqrt(p_bruit)*randn(1,length(x_ASK));
       bruit =sqrt(1/2)*(bruitI + 1i*bruitQ);
       x_sortie_bruit=x_ASK+bruit;
       z_bruit_ASK=filter(hr,1,[x_sortie_bruit zeros(1,N0)]);
       z_bruit_ASK=z_bruit_ASK(N0+1:end);
       %Echantillonnage:
       ze_bruit_ASK=z_bruit_ASK(1:Ns_ASK:end);
       ze_bruit_ASK2=real(ze_bruit_ASK);
       %Demapping
        symboles_decides_ASK = zeros(1,length(ze_ASK));
        for j=1:length(symboles_decides_ASK)
            if (ze_bruit_ASK2(j)>=2)
                symboles_decides_ASK(j) = 3;
            elseif (ze_bruit_ASK2(j) >= 0)
                symboles_decides_ASK(j) = 1;
            elseif (ze_bruit_ASK2(j) <= -2)
                symboles_decides_ASK(j) = -3;
            else
                symboles_decides_ASK(j) = -1;
            end
        end
        bits_decides_ASK = reshape(de2bi((symboles_decides_ASK+3)/2),1,nb_bits);
        TEB_bruit_ASK(i) = length(find(bits~=bits_decides_ASK))/nb_bits;
  end

    figure(11);semilogy(rsb_db, TEB_bruit_ASK,'r-o');
    xlabel('Eb/N0(dB)');
    ylabel('TEB ASK');
    title("TEB en fonction du rapport signal sur bruit");
    figure(12);semilogy(rsb_db, TEB_bruit_ASK,'g-o');
    xlabel('Eb/N0(dB)');
    ylabel('TEB ASK');
    title("comparaison entre TEB ASK simulé et théorique");
    hold on;
    rsb_dec=10.^(rsb_db/10);
    TEB_theorique_ASK=qfunc(sqrt(2*rsb_dec));
    semilogy(rsb_db, TEB_theorique_ASK,'b-*');
    legend('TEB ASK simulé','TEB ASK théorique');


    %% chaine 8-psk avec bruit :

     pre=mean(abs(Enveloppe_PSK).^2); %Puissance de l'enveloppe complexe
     TEB_bruit_PSK=zeros(1,length(rsb_db));
    for i = 1:length(rsb_db)
       rsb_dec = 10^(rsb_db(i)/10);
       %Ajout de bruit :
       p_bruit=pre*Ns_PSK/(2*rsb_dec*log2(M_PSK));
       bruitQ=sqrt(p_bruit)*randn(1,length(Enveloppe_PSK));
       bruitI=sqrt(p_bruit)*randn(1,length(Enveloppe_PSK));
       bruit =(1/sqrt(2))*( bruitI + 1i*bruitQ);
       x_sortie_bruit_PSK=Enveloppe_PSK+bruit;
       %Demodulation de base :
       z_bruit=filter(h_PSK,1,[x_sortie_bruit_PSK zeros(1,N0_PSK)]);
       z_bruit=z_bruit(N0_PSK+1:end);
       %Echantillonnage:
       zm_bruit_PSK=z_bruit(1:Ns_PSK:end);
       %Decision :
       bits_decides_bruit_PSK = pskdemod(zm_bruit_PSK,M_PSK,pi/M_PSK,'gray');
       bits_decides_bruit_PSK = de2bi(bits_decides_bruit_PSK.');
       bits_sortie_bruit_PSK = bits_decides_bruit_PSK(:)';
      %Calcul du TEB :
       TEB_bruit_PSK(i) = length(find(bits~=bits_sortie_bruit_PSK))/nb_bits;
   end

    figure(13);semilogy(rsb_db, TEB_bruit_PSK,'r-o');
    xlabel('Eb/N0(dB)');
    ylabel('TEB PSK');
    title("TEB en fonction du rapport signal sur bruit");
    figure(14);semilogy(rsb_db, TEB_bruit_PSK,'g-o');
    xlabel('Eb/N0(dB)');
    ylabel('TEB PSK');
    title("comparaison entre TEB PSK simulé et théorique");
    hold on;
    rsb_dec=10.^(rsb_db/10);
    TEB_theorique_PSK=qfunc(sqrt(2*rsb_dec)); 
    semilogy(rsb_db, TEB_theorique_PSK,'b-*');
    legend('TEB PSK simulé','TEB PSK théorique');

    %% chaine 16-QAM avec bruit :

    pre=mean(abs(Enveloppe_QAM).^2); %Puissance de l'enveloppe complexe
    TEB_bruit_QAM=zeros(1,length(rsb_db));
    for i = 1:length(rsb_db)
       rsb_dec = 10^(rsb_db(i)/10);
       %Ajout de bruit :
       p_bruit=pre*Ns_QAM/(2*rsb_dec*log2(M_QAM));
       bruitQ=sqrt(p_bruit)*randn(1,length(Enveloppe_QAM));
       bruitI=sqrt(p_bruit)*randn(1,length(Enveloppe_QAM));
       bruit =sqrt(1/2)*(bruitI + 1i*bruitQ);
       x_sortie_bruit_QAM=Enveloppe_QAM+bruit;
       %Demodulation de base :
       z_bruit=filter(h_QAM,1,[x_sortie_bruit_QAM zeros(1,N0_QAM)]);
       z_bruit=z_bruit(N0_QAM+1:end);
       %Echantillonnage:
       zm_bruit_QAM=z_bruit(1:Ns_QAM:end);
       %Decision :
       bits_decides_bruit_QAM = qamdemod(zm_bruit_QAM,M_QAM,'gray');
       bits_decides_bruit_QAM = de2bi(bits_decides_bruit_QAM.');
       bits_sortie_bruit_QAM = bits_decides_bruit_QAM(:)';
      %Calcul du TEB :
       TEB_bruit_QAM(i) = length(find(bits~=bits_sortie_bruit_QAM))/nb_bits;
   end

    figure(15);semilogy(rsb_db, TEB_bruit_QAM,'r-o');
    xlabel('Eb/N0(dB)');
    ylabel('TEB QAM');
    title("TEB en fonction du rapport signal sur bruit");
    figure(16);semilogy(rsb_db, TEB_bruit_QAM,'r-o');
    xlabel('Eb/N0(dB)');
    ylabel('TEB QAM');
    title("comparaison entre TEB QAM simulé et théorique");
    hold on;
    rsb_dec=10.^(rsb_db/10);
    TEB_theorique_QAM=qfunc(sqrt(2*rsb_dec)); 
    semilogy(rsb_db, TEB_theorique_QAM,'b-*');
    legend('TEB QAM simulé','TEB QAM théorique');

   %% Question 4:
    % %%%%%%%%%%%%%%% Comparaison des chaines de transmissions en terme d'efficacité en puissance %%%%%%%%%%%%%%%%%%%%%%%%%%
   figure(17);semilogy(rsb_db,TEB_bruit_QPSK,'b-*');
   xlabel('Eb/N0(dB)');
   ylabel('TEB');
   hold on;
   semilogy(rsb_db,TEB_bruit_ASK,'g-*');
   hold on;
   semilogy(rsb_db,TEB_bruit_PSK,'r-*');
   hold on;
   semilogy(rsb_db,TEB_bruit_QAM,'m-*');
   legend('TEB QPSK','TEB ASK','TEB PSK','TEB QAM');

   % %%%%%%%%%%%%%%% Comparaison des chaines de transmissions en terme d'efficacité spectrale %%%%%%%%%%%%%%%%%%%%%%%%%%