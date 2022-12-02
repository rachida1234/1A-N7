%% Sequence 2 :

   %% Etude dans canal de propagation :
   Fe=24000;  %Frequence d'echantillonage
   Rb=3000; %Debit binaire
   nb_bits=10;
   Ts1=1/Rb;
   Ns1=Ts1*Fe;
   %Mapping&surechantillonnage :
      bits=randi([0 1],1,nb_bits);  
      symboles=2*bits-1;
      suite_dirac=kron(symboles,[1 zeros(1,Ns1-1)]); 
      h1=ones(1,Ns1); %reponse impultionnelle
      signal1=filter(h1,1,suite_dirac);
     %Demodulateur
        %Filtrage de réception :
        hr=ones(1,Ns1);
        z=filter(hr,1,signal1);
        figure(4);plot(z);
        title('signal après son passage par le filtre de reception');
        ylabel('z(t)');
        xlabel("temps");
        axis([0 length(z)-1 -10 10]);
        %La réponse impulsionnelle globale de la chaine de transmission :
        g=conv(h1,hr);
        figure(5);plot(g);
        title('reponse impulsionnelle globale de la chaine de transmission');
        ylabel('g(t)');
        xlabel("temps");
        n0=Ns1; %L'instant optimal permettant d'échantillonner sans interference entre symboles car g(t) respecte le critère de Nyquist
        %g(t) non nul en n0 et g(n0+kNs1)=0 pour tout k!=0;

        %Diagramme de l'oeil :
        figure(6);plot(reshape(z,Ns1,length(z)/Ns1));
        title("Diagramme de l'oeil");  %on retrouve le resultat car à l'échantillon Ns1 l'information binaire ne prend que 2 valeurs contrairement aux autres instants

        %Echantillonnage :
        z_echantillonne=z(Ns1:Ns1:end);
        figure(7); plot(z_echantillonne);
        title('signal échantillonné');
        ylabel('z_echantillonné(t)');
        xlabel("temps");

        %Décision :
        Information_decidee=sign(z_echantillonne);  %Pour les échantillons negatifs on leur attribue -1 et les échantillons positifs 1 
        TEB=length(find(Information_decidee~=symboles))/nb_bits; %Taux d'erreur binaire 
        %Demapping :
        Info_binaire=(Information_decidee+1)/2;
        
        %Question8: n=3
                    n=3; %sur le diagramme de l'oeil on voit qu'à n=3 on a 5 valeurs possibles pour une information binaire 
                    %Echantillonnage :
                    z_echantillonne_n=z(n:Ns1:end);
                    figure(9); plot(z_echantillonne_n);
                    title('signal échantillonné');
                    ylabel('z_echantillonné_n(t)');
                    xlabel("temps");
                    
                    %Décision :
                    Information_decidee_n=sign(z_echantillonne_n);  
                    TEB_n=length(find(Information_decidee_n~=symboles))/nb_bits; %Taux d'erreur binaire 
                    %Explication : pour n=3,le diagramme de l'oeil nous
                    %montre qu'à chaque valeur de la chaine transmise
                    %correpond 5 valeurs équiprobables ,ainsi au niveau de
                    %la décision les valeurs qui sont proches de 0 peuvent
                    %etre mal décidées.

      %% Etude avec canal de propagation sans bruit  :  
         %Pour BW_1=8000 :
                BW1=8000;
                %Réponse impulsionnelle du filtre passe-bas:
                freqc_tilde1=BW1/Fe;   
                M=1003;
                K=[-(M-1)/2:(M-1)/2];
                filtre_passe_bas1=2*freqc_tilde1*sinc(2*freqc_tilde1*K); 
                figure(10);plot(Fe*K,filtre_passe_bas1);
                xlabel("Frequence en Hz");
                ylabel("filtre_passe_bas");
                title('Reponse impulsionnelle du filtre passe bas');
                F=linspace(-Fe/2,Fe/2,length(signal1));
                TFD_pb1=abs(fftshift(fft(filtre_passe_bas1,length(F))));
                figure(11); plot(F,TFD_pb1);
                xlabel("Frequence en Hz");
                ylabel("TFD_pb1");
                title('Reponse en fréquence du filtre passe bas');
                %Reponse impulsionnelle globale de la chaine de transmission:
                g_global1=conv(g,filtre_passe_bas1,"same");
                figure(12);plot(g_global1);
                xlabel("échantillons");
                ylabel("g_global1");
                title('reponse impulsionnelle globale');
                g_global_freq1=fftshift(fft(g_global1,length(F)));
                figure(13);plot(F,g_global_freq1);
                xlabel("Frequence en Hz");
                ylabel("g_global");
                title('reponse en fréquence globale');
                %Diagramme de l'oeil :
                z_canal1=conv(signal1,filtre_passe_bas1,"same"); 
                z_final1=filter(hr,1,z_canal1);
                figure(14);plot(reshape(z_final1,Ns1,length(z_final1)/Ns1));
                title("Diagramme de l'oeil"); 
                %representation de |H(f)Hr(f)| et |Hc(f)|:
                figure(15);plot(F,1/max(TFD_pb1)*TFD_pb1);
                hold on;
                plot(F,1/max(g_global_freq1)*g_global_freq1);
                title('representation de |H(f)Hr(f)| et |Hc(f)|');
                xlabel("Frequence(Hz)");
                legend('|Hc(f)|','|H(f)Hr(f)|');
                hold off;
                %Calcul de TEB :
                z_final_echantillonne1=z_final1(Ns1:Ns1:end);
                Info_decidee_final1=sign(z_final_echantillonne1);
                TEB_final_8000=length(find(Info_decidee_final1~=symboles))/nb_bits;

 %Pour BW_2=1000 :
                BW=1000;
                %Réponse impulsionnelle du filtre passe-bas:
                freqc_tilde=BW/Fe;   
                M=1003;
                K=[-(M-1)/2:(M-1)/2];
                filtre_passe_bas=2*freqc_tilde*sinc(2*freqc_tilde*K); 
                figure(17);plot(Fe*K,filtre_passe_bas);
                xlabel("Frequence en Hz");
                ylabel("filtre_passe_bas");
                title('Reponse impulsionnelle du filtre passe bas');
                F=linspace(-Fe/2,Fe/2,length(signal1));
                TFD_pb=abs(fftshift(fft(filtre_passe_bas,length(F))));
                figure(18); plot(F,TFD_pb);
                xlabel("Frequence en Hz");
                ylabel("TFD_pb");
                title('Reponse en fréquence du filtre passe bas');
                %Reponse impulsionnelle globale de la chaine de transmission:
                g_global=conv(g,filtre_passe_bas,"same");
                figure(19);plot(g_global);
                xlabel("échantillons");
                ylabel("g_global");
                title('reponse impulsionnelle globale');
                g_global_freq=fftshift(fft(g_global,length(F)));
                figure(20);plot(F,g_global_freq);
                xlabel("Frequence en Hz");
                ylabel("g_global");
                title('reponse en fréquence globale');
                %Diagramme de l'oeil :
                z_canal=conv(signal1,filtre_passe_bas,"same"); 
                z_final=filter(hr,1,z_canal);
                figure(21);plot(reshape(z_final,Ns1,length(z_final)/Ns1));
                title("Diagramme de l'oeil"); 
                %representation de |H(f)Hr(f)| et |Hc(f)|:
                figure(22);plot(F,1/max(TFD_pb)*TFD_pb);
                hold on;
                plot(F,1/max(g_global_freq)*g_global_freq);
                title('representation de |H(f)Hr(f)| et |Hc(f)|');
                xlabel("Frequence(Hz)");
                legend('|Hc(f)|','|H(f)Hr(f)|');
                %tracage du filtre de Reception et filtre de mise en forme:
                figure(23);plot(F,fftshift(fft(h1,length(F))).^2);
                xlabel('Frequence(Hz)');
                ylabel('TF(h1)');
                title('TF du filtre de mise enn forme');
                
                %Calcul de TEB :
                z_final_echantillonne=z_final(Ns1:Ns1:end);
                Info_decidee_final=sign(z_final_echantillonne);
                TEB_final_1000=length(find(Info_decidee_final~=symboles))/nb_bits;
                
                %Commentaire : pour BW=1000 la bande est assez serrée ce
                %qui engendre une perte d'information et donc un TEB plus
                %élévé.(et cela se voit sur le diagramme de l'oeil)
                
