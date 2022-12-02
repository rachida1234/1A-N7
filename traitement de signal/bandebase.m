close all;
load donnees1.mat ;
load donnees2.mat;
%parametres
f1=0;
f2=46*10^3;
Fe=120*10^3;
Te=1/Fe;
T_slot=40*(10^-3);  %La periode d'un seul slot et donc la durée totale du message à transmettre
N_tot=T_slot*Fe;    %Nombre d'echantillons total du signal
%% Implantation
   %Modulation bande base :
       %Traçage des signaux message1 et message2
        Ns=N_tot/length(bits_utilisateur2);
        bits_utilisateur1=2*bits_utilisateur1 -1;
        bits_utilisateur2=2*bits_utilisateur2 -1;
        message1=repmat(bits_utilisateur1,Ns,1);
        message2=repmat(bits_utilisateur2,Ns,1);
        message1=message1(:)';
        message2=message2(:)';
        T=0:Te:(length(message1)-1)*Te;
        figure(1);
        subplot(2,1,1);
        plot(T,message1);
        xlabel("Temps en s");
        ylabel("message1(t)")
        title('message1 de type NRZ');
        subplot(2,1,2);
        plot(T,message2);
        xlabel("Temps en s");
        ylabel("message2(t)")
        title('message2 de type NRZ');
        %Densite spectrale de puissance des messages 1 et 2
        DSP1=fftshift(abs(1/length(message1)*fft(message1)).^2);
        DSP2=fftshift(abs(1/length(message1)*fft(message2)).^2);
        F=linspace(-Fe/2,Fe/2,length(message1));
        figure(2);
        subplot(2,1,1);
        semilogy(F,DSP1);
        xlabel("Frequence en Hz");
        ylabel("DSP1")
        title('Densité spectrale du message1');
        subplot(2,1,2);
        semilogy(F,DSP2);
        xlabel("Frequence en Hz");
        ylabel("DSP2")
        title('Densité spectrale du message2');
       
   %Construction du signal MF-TDMA :
       %a:
        nul=zeros(1,length(message1));
        signal1=[nul,message1,nul,nul,nul];
        T1 = linspace(0,200*10^-3,length(signal1));
        figure(3);
        subplot(2,1,1);
        plot(T1,signal1);
        xlabel("Temps en s");
        ylabel("Signal 1 resultant");
        subplot(2,1,2);
        signal2=[nul,nul,nul,nul,message2];
        T2 = linspace(0,200*10^-3,length(signal2));
        plot(T2,signal2);
        xlabel("Temps en s");
        ylabel("Signal 2 resultant");
       
      %b:
        x1=signal1.*cos(2*pi*f1*T1);
        T_message1 = linspace(0,200*10^-3,length(x1));
        T_x2=0:Te:(length(signal2)-1)*Te;
        x2=signal2.*cos(2*pi*f2*T_x2);
        T_message2= linspace(0,200*10^-3,length(x2));
        figure(4);
        subplot(2,2,1);
        plot(T_message1,x1);
        xlabel("Temps en s");
        ylabel("Signal 1 modulé");
        subplot(2,1,2);
        plot(T_message2,x2);
        xlabel("Temps en s");
        ylabel("Signal 2 modulé");

        %2:
        x=x1+x2;
        p_signal=mean(abs(x).^2);
        SNR=1000;
        p_bruit=p_signal*10^(-SNR/10);
        bruit=sqrt(p_bruit)*randn(1,length(x1));
        signal_bruite=x+bruit;
        T_signal_bruite= linspace(0,200*10^-3,length(signal_bruite));
        figure(5);plot(T_signal_bruite,signal_bruite);
        xlabel("Temps en s");
        ylabel("Signal bruite");
            %le rapport signal sur bruit est important  ,donc le bruit n'affecte pas le signal;
       
        %3:
        figure(6);
        subplot(2,2,1);
        pwelch(signal_bruite,[],[],[],Fe,'twosided');
        xlabel("Frequence en Hz");
        ylabel("DSP du signal")
        title('Densité spectrale logarithmique du signal bruite');
        subplot(2,1,2);
        DSP=fftshift(abs(1/length(signal_bruite)*fft(signal_bruite)).^2);
        F=linspace(-Fe/2,Fe/2,length(signal_bruite));
        plot(F,DSP) ;
        hold on;
        xlabel("Frequence en Hz");
        ylabel("DSP du signal");
        title('Densité spectrale du signal bruite');

 %% Mise en oeuvre du recepteur MF_TDMA:
    %Demultiplexage des porteuses par filtrage:

      %% Filtre passe-bas  :
         %Reponse impulsionnelle
         freq_coupure=f2/2;   %frequence du coupure du filtre passe bas
         freqc_tilde=freq_coupure/Fe;   
         M=1003;
         K=[-(M-1)/2:(M-1)/2];
         retard=(M-1)/2;
         filtre_pb=2*freqc_tilde*sinc(2*freqc_tilde*K); 
         figure(7);plot(Fe*K,filtre_pb);
         xlabel("Frequence en Hz");
         ylabel("filtre_pb");
         title('Reponse impulsionnelle du filtre passe bas');
          %Reponse en fréquence
         TFD_pb=abs(fftshift(fft(filtre_pb,length(F))));
         figure(8); plot(F,TFD_pb);
         xlabel("Frequence en Hz");
         ylabel("TFD");
         title('Reponse en frequence du filtre passe bas');
         %Densite spectral de puissance du signal recu +Le module de rep en frequence du filtre
         figure(10);semilogy(F,DSP/max(DSP));
         hold on;
         semilogy(F,abs(TFD_pb)/max(abs(TFD_pb)));
         ylabel("DSP et Module(TFD)");
         xlabel("frequence en Hz");


      %% Filtre passe haut:
       
         %Reponse impulsionnelle:
         Dirac=zeros(1,length(filtre_pb));
         Dirac(1,floor(length(filtre_pb)/2)+1)=1;
         filtre_ph=Dirac-filtre_pb;
         figure(11);plot(Fe*K,filtre_ph);
         xlabel("Frequence en Hz");
         ylabel("filtre_ph");
         title('Reponse impulsionnelle du filtre passe haut');
         %Reponse en frequence:
         TFD_ph=1-TFD_pb;
         figure(12);plot(TFD_ph);
         xlabel("Frequence en Hz");
         ylabel("TFD_ph");
         title('Reponse en frequence du filtre passe haut');
         %Densite spectral de puissance du signal recu +Le module de rep en
         %frequence du filtre passe haut
         figure(13);semilogy(F,DSP/max(DSP));
         hold on;
         semilogy(F,abs(TFD_ph)/max(abs(TFD_ph)));
         ylabel("DSP et Module(TFD)");
         xlabel("frequence en Hz");

         %% Filtrage
         %messgae1
          nul1=zeros(1,retard);
          signal_bruite=[signal_bruite nul1];
          x1_tilde= filter(filtre_pb,1,signal_bruite);
          T_tilde_1 = linspace(0,200*(10^-3),length(x1_tilde));
          figure(14);
          subplot(2,1,1);
          plot(T_tilde_1,x1_tilde);
          xlabel("Temps en s ");
          ylabel("x1_tilde");
          title('signal x1_tilde filtre');
         %messgae2
          x2_tilde= filter(filtre_ph,1,signal_bruite);
          T_tilde_2 = linspace(0,200*(10^-3),length(x2_tilde));
          subplot(2,1,2);
          figure(14);plot(T_tilde_2,x2_tilde);
          xlabel("Temps en s ");
          ylabel("x2_tilde");
          title('signal x2_tilde filtre');
    
        
          %% retour en bande de base
           T2 = 0:Te:(length(x2_tilde)-1)*Te;  %
           x1_module=x1_tilde.*cos(2*pi*f1*T2);
           x1_module=[x1_module nul1];
           x1_demodule=filter(filtre_pb,1,x1_module);
           x2_module=x2_tilde.*cos(2*pi*f2*T2);
           x2_module=[x2_module nul1];

           %un filtre passe bas pour filtrer le signal x2_module
           freqc_tilde2=f2/(2*Fe);   
           filtre_pb2=2*freqc_tilde2*sinc(2*freqc_tilde2*K); 
           x2_demodule= filter(filtre_pb2,1,x2_module);
           T3=0:Te:(length(x1_module)-1)*Te;
           figure(15);
           subplot(2,1,1);
           plot(T3,x1_demodule);
           xlabel("Temps en s ");
           ylabel("x1_demodule");
           title('signal x1 demodulé');
           subplot(2,1,2);
           T3=0:Te:(length(x2_demodule)-1)*Te;
           plot(T3,x2_demodule);
           xlabel("Temps en s ");
           ylabel("x2_demodule");
           title('signal x2 demodulé');
           
           x1_demodule=x1_demodule(2*retard+1:end);
           x2_demodule=x2_demodule(2*retard+1:end);

         %% Detection du slot utile
           % recherche du slot contenant l'energie maximale du 1 er signal
           Max1=0;
           Slot1=1;
           for i=1:5 
                X=x1_demodule((i-1)*N_tot+1:i*N_tot);
                Energie=mean(sum(abs(X).^2,1));
                if Max1<Energie 
                    Max1=Energie;
                    Slot1=i;
                end    
            end
         % recherche du slot contenant l'energie maximale du 2 eme signal
           Max2=0;
           Slot2=1;
           for i=1:5 
                X=x2_demodule((i-1)*N_tot+1:i*N_tot);
                Energie=mean(sum(abs(X).^2,1));
                if Max2<Energie 
                    Max2=Energie;
                    Slot2=i;
                end    
           end 
                        %Les slots utiles pour le signal 1 et 2 sont respectivement 2 et 5 
        
           %% Demodulation bande de base
           SignalFiltre1=filter(ones(1,Ns),1,x1_demodule((Slot1-1)*N_tot+1:Slot1*N_tot)) ;
           SignalEchantillonne1=SignalFiltre1(Ns:Ns :end) ;
           %figure(16);plot(SignalEchantillonne1);
           BitsRecuperes1=(sign(SignalEchantillonne1)+1)/2 ;
           bin2str(BitsRecuperes1)

          SignalFiltre2=filter(ones(1,Ns),1,x2_demodule((Slot2-1)*N_tot+1:Slot2*N_tot)) ;
          SignalEchantillonne2=SignalFiltre2(Ns:Ns :end);
          % figure(16);plot(SignalEchantillonne2);
           BitsRecuperes2=(sign(SignalEchantillonne2)+1)/2 ;
           bin2str(BitsRecuperes2)