package src.base_de_donnees;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResizeImageExample {
    
	public ResizeImageExample() {
        try {
			resizeFile("///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/spaghBolo.jpg",
			        "///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/spaghBolo_resized.jpg",
			        200, 100);
	        resizeFile("///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/pizza.jpg",
	                "///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/pizza_resized.jpg",
	                200, 100);
	        resizeFile("///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/cassoulet.jpg",
	                "///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/cassoulet_resized.jpg",
	                200, 100);
	        resizeFile("///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/paella.jpg",
	                "///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/paella_resized.jpg",
	                200, 100);
	        resizeFile("///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/hamburger.jpg",
	                "///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/hamburger_resized.jpg",
	                200, 100);
	        resizeFile("///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/tartiflette.jpg",
	                "///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/tartiflette_resized.jpg",
	                200, 100);
	        resizeFile("///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/boeufbourgignon.jpg",
	                "///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/boeufbourgignon_resized.jpg",
	                200, 100);
	        resizeFile("///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/raclette.jpg",
	                "///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/raclette_resized.jpg",
	                200, 100);
	        resizeFile("///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/fajitas.png",
	                "///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/fajitas_resized.png",
	                200, 100);
	        resizeFile("///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/rizcantonais.jpg",
	                "///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/rizcantonais_resized.jpg",
	                200, 100);
	        resizeFile("///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/patesthon.jpg",
	                "///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/patesthon_resized.jpg",
	                200, 100);
	        resizeFile("///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/aligot.jpg",
	                "///home/hdupuis/Annee_1/Tob/KL-4/src/base_de_donnees/aligot_resized.jpg",
	                200, 100);
        } catch (IOException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
        
    }

    public static void resizeFile(String imagePathToRead,
                              String imagePathToWrite, int resizeWidth, int resizeHeight)
            throws IOException {

        File fileToRead = new File(imagePathToRead);
        BufferedImage bufferedImageInput = ImageIO.read(fileToRead);

        BufferedImage bufferedImageOutput = new BufferedImage(resizeWidth,
                resizeHeight, bufferedImageInput.getType());

        Graphics2D g2d = bufferedImageOutput.createGraphics();
        g2d.drawImage(bufferedImageInput, 0, 0, resizeWidth, resizeHeight, null);
        g2d.dispose();

        String formatName = imagePathToWrite.substring(imagePathToWrite
                .lastIndexOf(".") + 1);

        ImageIO.write(bufferedImageOutput, formatName, new File(imagePathToWrite));
    }
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ResizeImageExample();
			}
		});
	}
}
