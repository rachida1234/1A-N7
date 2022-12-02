close all;

%% Implantation des modulateurs:
Fe=24000;  %Frequence d'echantillonage
Rb=3000; %Debit binaire
nb_bits=100;

%%  Sequence 1 :

  %% Modulateur1:

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
      figure(1);plot(linspace(0,Ts1,length(h1)),h1);
      ylabel("Reponse impultionelle 1");
      xlabel('t');
      figure(2);
      subplot(3,1,1);
      plot(signal1);
      title('Modulateur1');
      axis([0 length(signal1)-1 -2 2]);

      %DSP :
      figure(3);subplot(3,1,1);
      DSP1=fftshift(abs(1/length(signal1)*fft(signal1)).^2);
      F=linspace(-Fe/2,Fe/2,length(signal1));
      semilogy(F,DSP1);
      hold on;
      xlabel("Frequence en Hz");
      ylabel("DSP du signal");
      title('Densité spectrale logarithmique du signal1');
      DSP_theorique1=Ts1*sinc(F*Ts1).^2;
      semilogy(F,DSP_theorique1);
      hold on;
      DSP=fftshift(pwelch(signal1,[],[],[],Fe,'twosided'));
      F2=linspace(-Fe/2,Fe/2,length(DSP));
      plot(F2,DSP);
      
      
  %% Modulateur2:
     %Mapping:
      symboles_4aires= (2*bi2de(reshape(bits, 2, length(bits)/2).') - 3).';

      %surechantillonage :

      Ts2=log2(4)/Rb;
      Ns2=Ts2*Fe;
      suite_dirac2=kron(symboles_4aires,[1 zeros(1,Ns2-1)]);

      %Filtrage de mise en forme :
      h2=ones(1,Ns2); %reponse impultionnelle
      signal2=filter(h2,1,suite_dirac2); 

      %Affichage du signal géneré :
      figure(2);subplot(3,1,2);plot(signal2);
      title('Modulateur2');
      axis([0 length(signal2)-1 -4 4]);
      
      %DSP :
      figure(3);subplot(3,1,2);
      DSP2=fftshift(abs(1/length(signal2)*fft(signal2)).^2);
      F=linspace(-Fe/2,Fe/2,length(signal2));
      semilogy(F,DSP2);
      xlabel("Frequence en Hz");
      ylabel("DSP du signal")
      title('Densité spectrale logarithmique du signal2');
      hold on; 
      DSP_theorique2=Ts2*sinc(F*Ts2).^2;
      semilogy(F,DSP_theorique2);
     
  %% Modulateur3 :
    %Filtrage de mise en forme:
         %Parametres:
         alpha=0.5; %Roll off
         L=Ns1; %Longueur de la reponse impultionnelle
         %Cosinus surélvé :
         h3 = rcosdesign(alpha, L,Ns1);
         figure(6);plot(linspace(0,Ts1,length(h3)),h3);
         ylabel("Réponse impulsionnelle h3(t)");
         xlabel('t')
         signal3=filter(h3,1,suite_dirac);

    %Affichage du signal géneré :
          figure(2);subplot(3,1,3);plot(signal3);
          title('Modulateur3');
          axis([0 length(signal3)-1 -4 4]);
         
    %DSP  : 
      figure(3);subplot(3,1,3);
      DSP3=fftshift(abs(1/length(signal3)*fft(signal3)).^2);
      F3=linspace(-Fe/2,Fe/2,length(DSP3));
      semilogy(F3,DSP3);
      xlabel("Frequence en Hz");
      ylabel("DSP du signa3");
      title('Densité spectrale logarithmique du signal3');
 
 %% Efficacité des modulateurs :
   figure(8);
   semilogy(F,1/max(DSP1)*DSP1);
   axis([-1.5e4 1.5e4 1e-8 1]);
   hold on;
   semilogy(F,1/max(DSP2)*DSP2);
   hold on;
   semilogy(F,1/max(DSP3)*DSP3);
   title('Efficacité des modulateurs');
   xlabel('Frequence(Hz)')
   ylabel('DSP');
   legend('Modulateur1','Modulateur2','Modulateur3');
   %le modulateur3 est plus efficace par rapport aux autres vu l'absence
   %des laubes en dehors de la bande où on souhaite transmettre le signal 
   %il faut jouer sur le paramètre roll off pour augmenter l'efficacité.
      
 