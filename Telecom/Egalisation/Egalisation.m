close all;
clear all;
%%%%%%%%%%%%%%%%%%%%%%%%%% Egalisation %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%Déclaration des variables
Fe=24000; %Fréquence d'échantillonnage
Rb=3000; %Le débit binaire en bits/s
M_BPSK=2;
Ts=1/Rb;
T0=0;%Le retard sur la ligne de vue directe
T1=T0+Ts;%Le retard sur le trajet réfléchi
alpha0=1; %coeff d'atténuation sur la ligne de vue directe
alpha1=0.5;%coeff d'atténuation sur le trajet réfléchi
nb_bits=10000; %nombre de bits à générer

%% Question 2: Implantation de la chaine de transmission sans canal
      %Géneration de l'information binaire :
       bits=randi([0 1],1,nb_bits);  
      %Mapping :
       symboles=2*bits-1;
      
      %surechantillonage:
       Ns=Ts*Fe;
       suite_dirac=kron(symboles,[1 zeros(1,Ns-1)]); 

      %Filtrage de mise en forme :
       h=ones(1,Ns); %reponse impultionnelle
       xe=filter(h,1,suite_dirac);
       figure(1);plot(linspace(0,Ts,length(h)),h);
       ylabel("Reponse impultionelle du filtre de mise en forme");
       xlabel('t');
       figure(2);plot(xe);
       title('signal en sortie du filtre de mise en forme');
       axis([0 length(xe)-1 -2 2]);

      %Filtrage de réception :
       hr=ones(1,Ns);
       z=filter(hr,1,xe);
       figure(3);plot(z);
       title('signal après son passage par le filtre de reception');
       ylabel('z(t)');
       xlabel("temps");
       axis([0 length(z)-1 -10 10]);

      %Echantillonnage :
       z_echantillonne=z(Ns:Ns:end);
       figure(4); plot(z_echantillonne);
       title('signal échantillonné');
       ylabel('z_echantillonné(t)');
       xlabel("temps");

      %Décision :
       symboles_decides=sign(z_echantillonne);  
      
       %Demapping :
       bits_decides=(symboles_decides+1)/2;
       TEB=length(find(bits_decides~=bits))/nb_bits; 

  %% Question 3: Implantation de la chaine avec la partie filtrage réalisée par le canal
       %Canal :
       F=(1:length(xe))/Fe;
       hc=zeros(1,Ns);
       hc(1,1)=alpha0;
       hc(1,Ns)=alpha1;
       %hc=[alpha0 alpha1];
       figure(5);stem(hc);
       title("Réponse impulsionnelle du canal")
       ylabel('hc(t)');
       xlabel('Temps')
      %ye=conv(xe(1),hc(1))+conv(xe(Ns),alpha1);
       ye=filter(hc,1,xe);

       %Filtrage de réception :
       hr=h;
       zc=filter(hr,1,ye);
       figure(6);plot(zc);
       title('signal après son passage par le filtre de reception et le canal');
       ylabel('z(t)');
       xlabel("temps");
       axis([0 length(zc)-1 -30 30]);

       %Echantillonnage :
       zc_echantillonne=zc(Ns:Ns:end);
       figure(7); plot(zc_echantillonne);
       title('signal échantillonné');
       ylabel('zc_echantillonné(t)');
       xlabel("temps");

       %Décision :
       symboles_decides=sign(zc_echantillonne);  
      
       %Demapping :
       bits_decides=(symboles_decides+1)/2;
       TEB_Canal=length(find(bits_decides~=bits))/nb_bits; 

       %Constellations en sortie  
       figure(8); plot(real(bits_decides),imag(bits_decides),'b*','linewidth',2);
       title(' Constellations obtenue en réception');
       axis([-1 2 -1 2]);

%%  Question 4 : La chaine de transmission complète avec le filtrage canal et le bruit

    p_signal=mean(abs(xe).^2);
    rsb_db = 0:10;
    TEB_bruit_canal=zeros(1,length(rsb_db)); 
    for i = 1:length(rsb_db)
         rsb_dec = 10^(rsb_db(i)/10);
         p_bruit=p_signal*Ns/(2*rsb_dec);
         bruit=sqrt(p_bruit)*randn(1,length(xe));
         xe_bruit = bruit + xe;
        %passage par le Canal :
         ye_bruit=filter(hc,1,xe_bruit);
        %Filtrage de réception :
         z=filter(hr,1,ye_bruit);
        %Diagramme de l'oeil
         figure(9);plot(reshape(z,Ns,length(z)/Ns));
         title("Diagramme de l'oeil");
        %Echantillonnage :
         x_echantillonne=z(Ns:Ns:end);
        %Décision :
         Information_decidee=sign(x_echantillonne);
         TEB_bruit_canal(i)=(nb_bits-length(find(Information_decidee==symboles)))/nb_bits;
        %Demapping :
         bits_decides_bruit=(Information_decidee+1)/2;
        
    end
    
    %%partie A : TEB simulé et théorique 
    figure(10);semilogy(rsb_db, TEB_bruit_canal,'r-*');
    hold on;
    title("TEB en fonction du rapport Eb/N0 pour la chaine de réference");
    xlabel('Eb/N0(dB)');
    ylabel('TEB');
    rsb_dec=10.^(rsb_db/10);
    TEB_theorique_canal=qfunc(sqrt(2*rsb_dec));
    semilogy(rsb_db, TEB_theorique_canal,'g-o');
    legend('TEB simulé','TEB théorique');

    %%Partie B :
    TEB_bruit=zeros(1,length(rsb_db)); 
    for i = 1:length(rsb_db)
         rsb_dec = 10^(rsb_db(i)/10);
         p_bruit=p_signal*Ns/(2*rsb_dec);
         bruit=sqrt(p_bruit)*randn(1,length(xe));
         xe_bruit = bruit + xe;
        %Filtrage de réception :
         z=filter(hr,1,xe_bruit);
        %Diagramme de l'oeil
         figure(11);plot(reshape(z,Ns,length(z)/Ns));
         title("Diagramme de l'oeil");
        %Echantillonnage :
         x_echantillonne=z(Ns:Ns:end);
        %Décision :
         Information_decidee=sign(x_echantillonne);
         TEB_bruit(i)=(nb_bits-length(find(Information_decidee==symboles)))/nb_bits;
        %Demapping :
         bits_decides_bruit=(Information_decidee+1)/2;
        
    end

    figure(12);semilogy(rsb_db, TEB_bruit,'r-*');
    hold on;
    title("TEB en fonction du rapport Eb/N0 pour la chaine de réference");
    xlabel('Eb/N0(dB)');
    ylabel('TEB');
    semilogy(rsb_db, TEB_bruit_canal,'g-o');
    legend('TEB sans canal','TEB avec canal');
