%% Séquence 3
close all;

Fe=24000;  %Frequence d'echantillonage
Rb=3000; %Debit binaire
nb_bits=30000;

%Mapping :
bits=randi([0 1],1,nb_bits);
symboles=2*bits-1;

%surechantillonage:
Ts1=1/Rb;
Ns1=Ts1*Fe;
suite_dirac=kron(symboles,[1 zeros(1,Ns1-1)]);

%Filtrage de mise en forme :
h1=ones(1,Ns1); %reponse impultionnelle
signal1=filter(h1,1,suite_dirac);
hr=ones(1,Ns1);

%% Chaine de référence
p_signal=mean(abs(signal1).^2);
rsb_db = 0:8;
TEB=zeros(1,length(rsb_db));
for i = 1:length(rsb_db)
    rsb_dec = 10^(rsb_db(i)/10);
    p_bruit=p_signal*Ns1/(2*rsb_dec);
    bruit=sqrt(p_bruit)*randn(1,length(signal1));
    signal_bruite = bruit + signal1;

    %Diagramme de l'oeil
    x_final=filter(hr,1,signal_bruite);
   % figure(1);plot(reshape(x_final,Ns1,length(x_final)/Ns1));
    %title("Diagramme de l'oeil");

    %Taux d'erreur binaire
    %Echantillonnage :
    x_echantillonne=x_final(Ns1:Ns1:end);
    %Décision :
    Information_decidee=sign(x_echantillonne);
    TEB(i)=(nb_bits-length(find(Information_decidee==symboles)))/nb_bits;
    %Demapping :
    Info_binaire=(Information_decidee+1)/2;
    
end

figure(2);semilogy(rsb_db, TEB,'r-*');
hold on;
title("TEB en fonction du rapport Eb/N0 pour la chaine de réference");
xlabel('Eb/N0(dB)');
ylabel('TEB');
rsb_dec=10.^(rsb_db/10);
TEB_theorique=qfunc(sqrt(2*rsb_dec));
semilogy(rsb_db, TEB_theorique,'g-o');
legend('TEB simulé','TEB théorique');


%% Première chaine 
    % Chaine sans bruit :

    hr2 = ones(1,Ns1);
    hr2(Ns1/2 +1 : Ns1) = 0;
    h3 = conv(h1,hr2);
    Ts = linspace(0,3*Ts1/2,length(h3));
    figure(3);plot(Ts, h3);
    title('reponse impulsionnelle globale de la chaine de transmission');
    xlabel('t');
    ylabel('h3');
    signal1_filtre=filter(hr2,1,signal1); %filtrage de reception
    figure(4);plot(reshape(signal1_filtre,Ns1,length(signal1_filtre)/Ns1));
    title("Diagramme de l'oeil");
    %Echantillonnage :
    signal1_echantillonne=signal1_filtre(Ns1:Ns1:end);
    Information_decidee1=sign(signal1_echantillonne);
    TEB1=(nb_bits-length(find(Information_decidee1==symboles)))/nb_bits;
    %Demapping :
    Info_binaire1=(Information_decidee1+1)/2;

    % Chaine avec bruit :
    TEB_bruit=zeros(1,length(rsb_db));
    p_signal1=mean(abs(signal1).^2);
    for i = 1:length(rsb_db)
    rsb_dec = 10^(rsb_db(i)/10);
    p_bruit1=p_signal1*Ns1/(2*rsb_dec);
    bruit1=sqrt(p_bruit1)*randn(1,length(signal1));
    signal_bruite1 = bruit1 + signal1;

    %Diagramme de l'oeil
    x1_final=filter(hr2,1,signal_bruite1);
    %figure(5);plot(reshape(x_final,Ns1,length(x_final)/Ns1));
    %title("Diagramme de l'oeil");

    %Taux d'erreur binaire
    %Echantillonnage :
    x1_echantillonne=x1_final(Ns1:Ns1:end);
    %Décision :
    Information_decidee_bruit=sign(x1_echantillonne);
    TEB_bruit(i)=(nb_bits-length(find(Information_decidee_bruit==symboles)))/nb_bits;
    %Demapping :
    Info_binaire1_bruit=(Information_decidee_bruit+1)/2;
    
    end

figure(6);semilogy(rsb_db, TEB_bruit,'g-o');
hold on;
title("TEB simulé et théorique en fct du rapport pour la première chaine");
xlabel('Eb/N0(dB)');
ylabel('TEB_bruit');
rsb_dec=10.^(rsb_db/10);
TEB_theorique1=qfunc(sqrt(rsb_dec));
semilogy(rsb_db, TEB_theorique1,'r-*');
legend('TEB_bruit simulé','TEB_theorique1');
figure(7);semilogy(rsb_db, TEB_bruit);
xlabel('Eb/N0(dB)');
ylabel('TEB');
hold on;
semilogy(rsb_db, TEB);
legend('TEB de la chaine de reference','TEB de la 1ère chaine');
title("comparaison de TEB de la 1ère chaine à celui de la chaine de réference");

  %Efficacité en puissance :
  %DSP  : 
      
   DSP1=fftshift(abs(1/length(signal1_filtre)*fft(signal1_filtre)).^2); %DSP de la 1ère chaine
   DSP2=fftshift(abs(1/length(signal1)*fft(filter(hr,1,signal1))).^2); %DSP de la chaine de réference
   F1=linspace(-Fe/2,Fe/2,length(DSP1));
   figure(8);
   semilogy(F1,1/max(DSP1)*DSP1);
   axis([-1.5e4 1.5e4 1e-8 1]);
   hold on;
   semilogy(F1,1/max(DSP2)*DSP2);
   legend('chaine 1','chaine de reference');
   axis([-1.5e4 1.5e4 1e-8 1]);
   xlabel('Fréquence(Hz)');
   ylabel('DSP');
   title("Comparaison d'efficacité en puissance");
   hold on;
  %commentaire : la 1ère chaine de trasmission est plus efficace que la
  %chaine de réference vu que sa bande spectrale est plus serrée + la
  %difference d'amplitude entre le laube principal et les laubes
  %secondaires est plus importante que celle de la chaine de transmission

  %% Deuxième chaine :
     symboles_4aires= (2*bi2de(reshape(bits, 2, length(bits)/2).') - 3).';

      %surechantillonage :
      Ts2=log2(4)/Rb;
      %Ns2=Ts2*Fe;
      Ns2=8;
      suite_dirac2=kron(symboles_4aires,[1 zeros(1,Ns2-1)]);
       
      %Filtrage de mise en forme :
      h2=ones(1,Ns2); %reponse impultionnelle
      signal2=filter(h2,1,suite_dirac2); 

      %Chaine sans bruit :
          hr2=h2;
          signal2_filtre=filter(hr2,1,signal2);
          figure(9);plot(reshape(signal2_filtre,Ns2,length(signal2_filtre)/Ns2));
          title("Diagramme de l'oeil");

          %Echantillonnage :
             signal2_echantillonne=signal2_filtre(Ns2:Ns2:end);
          %Prise de décision :
             symboles_decides=zeros(1,length(signal2_echantillonne));
             for i=1:length(signal2_echantillonne)
             if (signal2_echantillonne(i)>2*Ns2)
                 symboles_decides(i)=3;
             elseif (signal2_echantillonne(i)>=0)
                 symboles_decides(i)=1; 
             elseif (signal2_echantillonne(i)<-2*Ns2)
                 symboles_decides(i)=-3; 
             else
                symboles_decides(i)=-1;
             end
             end

             Information_binaire2=reshape(de2bi((symboles_decides + 3)/2).', 1, length(bits));
             TEB2=(nb_bits-length(find(Information_binaire2==bits)))/nb_bits;

   % Chaine avec bruit :          
          %TES :
           TES_bruit2=zeros(1,length(rsb_db));
           TEB_bruit2=zeros(1,length(rsb_db));
           p_signal2=mean(abs(signal2).^2);
           for i = 1:length(rsb_db)
               rsb_dec = 10^(rsb_db(i)/10);
               p_bruit2=p_signal2*Ns2/(4*rsb_dec);
               bruit2=sqrt(p_bruit2)*randn(1,length(signal2));
               signal_bruite2 = bruit2 + signal2;

               %Diagramme de l'oeil
               x2_final=filter(hr2,1,signal_bruite2);%Filtrage de reception
               %figure(10);plot(reshape(x2_final,Ns2,length(x2_final)/Ns2));
               %title("Diagramme de l'oeil");

                %Taux d'erreur binaire
                %Echantillonnage :
                x2_echantillonne=x2_final(Ns2:Ns2:end);
                %Décision :
                symboles_decides_bruit=zeros(1,length(x2_echantillonne));
                for k=1:length(x2_echantillonne)
                 if (x2_echantillonne(k)>2*Ns2)
                     symboles_decides_bruit(k)=3;
                 elseif (x2_echantillonne(k)>=0)
                     symboles_decides_bruit(k)=1; 
                 elseif (x2_echantillonne(k)<-2*Ns2)
                     symboles_decides_bruit(k)=-3; 
                 else
                    symboles_decides_bruit(k)=-1;
                 end
                 end
                %Demapping :
                Information_decidee_bruit2=reshape(de2bi((symboles_decides_bruit + 3)/2).', 1, length(bits));
                TES_bruit2(i)=(length(find(symboles_decides_bruit ~=symboles_4aires)))/length(symboles_4aires);
                TEB_bruit2(i)=(length(find(Information_decidee_bruit2~=bits)))/nb_bits;
           end
         %TES theorique :
         figure(11);semilogy(rsb_db, TES_bruit2);
         xlabel('Eb/N0(dB)');
         ylabel('TES de la chaine bruitée');
         title("TES en fonction du rapport SNR pour la deuxième chaine");
         
         figure(12);semilogy(rsb_db, TES_bruit2,'b-*');
         xlabel('Eb/N0(dB)');
         ylabel('TES');
         hold on;
         rsb_dec=10.^(rsb_db/10);
         TES_theorique2=3/2*qfunc(sqrt(4/5*rsb_dec));
         semilogy(rsb_db, TES_theorique2,'r-o');
         legend('TES simulé','TES théorique');
         title("comparaison de TES simulé avec le TES théorique");
         %TEB : 
         figure(13);semilogy(rsb_db, TEB_bruit2);
         xlabel('Eb/N0(dB)');
         ylabel('TEB');
         title("TEB en fonction du rapport SNR pour la deuxième chaine");
        %TEB theorique &TEB simulé:
         TEB_theorique2=3/4*qfunc(sqrt(4/5*rsb_dec));
         figure(14);semilogy(rsb_db, TEB_bruit2,'r-*');
         xlabel('Eb/N0(dB)');
         ylabel('TEB');
         hold on;
         semilogy(rsb_db, TEB_theorique2,'b-o');
         legend('TEB simulé','TEB théorique');
         title("comparaison de TEB simulé avec le TEB théorique");
        

         %TEB de la chaine de reference &TEB simulé:
         figure(15);semilogy(rsb_db, TEB_bruit2);
         xlabel('Eb/N0(dB)');
         ylabel('TEB');
         hold on;
         semilogy(rsb_db, TEB);
         legend('TEB chaine de reference','TEB de la 3 eme chaine');
         title("comparaison de TEB simulé avec le TEB de la chiane de ref");
         %Efficacité:
         %TEB de la 3 eme chaine s'annule pour un rapport signal sur bruit
         % faible par rapport à la chaine de reference donc la 3eme
         %chaine est plus performante que la chaine de réference .
         % car pour atteindre un TEB qui est egale au TEB de la 3eme chaine il faut
         %fournir un signal 3x plus puissant 