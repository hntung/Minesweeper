package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class LoadData {
	private HashMap<String, BufferedImage> listImage;
	public  LoadData() {
		listImage = new HashMap<String, BufferedImage>();
		
		try{
			BufferedImage img = ImageIO.read(new File("minesweeper.png"));
			
			listImage.put("noUse", img.getSubimage(0, 39, 16, 16));
			listImage.put("smile", img.getSubimage(0, 55, 26, 26));
			listImage.put("0", img.getSubimage(0, 0, 13, 23));
			listImage.put("1", img.getSubimage(13, 0, 13, 23));
			listImage.put("2", img.getSubimage(26, 0, 13, 23));
			listImage.put("3", img.getSubimage(39, 0, 13, 23));
			listImage.put("4", img.getSubimage(52, 0, 13, 23));
			listImage.put("5", img.getSubimage(65, 0, 13, 23));
			listImage.put("6", img.getSubimage(78, 0, 13, 23));
			listImage.put("7", img.getSubimage(91, 0, 13, 23));
			listImage.put("8", img.getSubimage(104, 0, 13, 23));
			listImage.put("9", img.getSubimage(107, 0, 13, 23));
			listImage.put("VoCung", img.getSubimage(120, 0, 13, 23));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<String, BufferedImage> getListImage() {
		return listImage;
	}
	
	public void setListImage(HashMap<String, BufferedImage> listImage) {
		this.listImage = listImage;
	}
	
}
