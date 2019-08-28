package view.Assets;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.TreeMap;

import javax.swing.ImageIcon;

public class Assets {
	
	private int frameWidth;
	private int frameHeight;
	private int cellWidth;
	private int cellHeight;
	
	private Image plainBackground;
	private Image greenBackground;
	private Image yellowBackground;
	
	private ImageIcon startPageIcon;
	private Image infoBackground;
	private Image payloadBackground;
	private Image startBackground1;
	private Image startBackground2;
	
	private Image abilityIcon;
	private Image graveyardIcon;
	private Image armorIcon;
	
	TreeMap<String, Image> characters;
	TreeMap<String, Image> payloads;
	
	public Assets() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameWidth = (int)screenSize.getWidth();
		frameHeight = (int)screenSize.getHeight();
		cellWidth = frameWidth/7;
		cellHeight = frameHeight/7;
		loadCharacters();
		loadBackgrounds();
		loadIcons();
		loadPanelsBackgrounds();
		loadPayloads();
	}
	
	public Font getGameFont(Float size) {
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, Assets.class.getResourceAsStream("gameFont2.ttf"));
		} catch(Exception ex) {
			System.out.println("File not Found!");
		}
		font = font.deriveFont(size);
		return font;
	}
	
	public TreeMap<String, Image> getCharacters() {
		return characters;
	}
	
	public Image getCharacter(String name) {
		return characters.get(name);
	}
	
	
	public int getFrameWidth() {
		return frameWidth;
	}

	public int getFrameHeight() {
		return frameHeight;
	}

	public int getCellWidth() {
		return cellWidth;
	}

	public int getCellHeight() {
		return cellHeight;
	}

	public void loadCharacters() {
		characters = new TreeMap<>();
		ImageIcon character = null;
		Image scaledCharacter = null;
		String[] names = {"ArmoredP1", "ArmoredP2", "MedicP1", "MedicP2", "RangedP1",
				"RangedP2", "SideKickP1", "SideKickP2", "SpeedsterP1", "SpeedsterP2",
				"SuperP1", "SuperP2", "TechP1", "TechP2"};
		for(String name : names) {
			character = new ImageIcon(Assets.class.getResource(name + ".gif"));
			int widthMargin = 50;
			int heightMargin = 0;
			scaledCharacter = character.getImage().getScaledInstance
					(cellWidth - widthMargin, cellHeight - heightMargin, Image.SCALE_DEFAULT);
			characters.put(name, scaledCharacter);
		}
	}
	
	public void loadBackgrounds() {
		ImageIcon backGround;
		backGround = new ImageIcon(Assets.class.getResource("plainBackground.png"));
		plainBackground = backGround.getImage().getScaledInstance(cellWidth, cellHeight, Image.SCALE_DEFAULT);
		
		backGround = new ImageIcon(Assets.class.getResource("greenBackground.png"));
		greenBackground = backGround.getImage().getScaledInstance(cellWidth, cellHeight, Image.SCALE_DEFAULT);
		
		backGround = new ImageIcon(Assets.class.getResource("yellowBackground.png"));
		yellowBackground = backGround.getImage().getScaledInstance(cellWidth, cellHeight, Image.SCALE_DEFAULT);
		
		startPageIcon = new ImageIcon(Assets.class.getResource("startPage.png"));
	}
	
	public void loadIcons() {
		ImageIcon icon;
		icon = new ImageIcon(Assets.class.getResource("abilityIcon.png"));
		abilityIcon = icon.getImage().getScaledInstance(cellWidth, cellHeight/2, Image.SCALE_DEFAULT);
		
		icon = new ImageIcon(Assets.class.getResource("graveyardIcon.png"));
		graveyardIcon = icon.getImage().getScaledInstance(cellWidth, cellHeight/2, Image.SCALE_DEFAULT);
		
	}
	
	public void loadPanelsBackgrounds() {
		ImageIcon backGround;
		backGround = new ImageIcon(Assets.class.getResource("infoBackground.png"));
		infoBackground = backGround.getImage().getScaledInstance(frameWidth, cellHeight/2, Image.SCALE_DEFAULT);

		backGround = new ImageIcon(Assets.class.getResource("payloadBackground.png"));
		payloadBackground = backGround.getImage().getScaledInstance(frameWidth, cellHeight/3, Image.SCALE_DEFAULT);
	}
	
	public void loadPayloads() {
		payloads = new TreeMap<>();
		ImageIcon payload = null;
		Image img = null;
		String[] names = {"0p1", "0p2", "1p1", "1p2", "2p1",
				"2p2", "3p1", "3p2", "4p1", "4p2",
				"5p1", "5p2", "6p1", "6p2"};
		for(String name : names) {
			payload = new ImageIcon(Assets.class.getResource(name + ".png"));
			img = payload.getImage().getScaledInstance(frameWidth/3, cellHeight/3, Image.SCALE_DEFAULT);
			payloads.put(name, img);
		}
		
	}
	
	public ImageIcon getStartPageIcon() {
		return startPageIcon;
	}

	public Image getPayload(String name) {
		return payloads.get(name);
	}

	public Image getPlainBackground() {
		return plainBackground;
	}

	public Image getGreenBackground() {
		return greenBackground;
	}

	public Image getYellowBackground() {
		return yellowBackground;
	}

	public Image getInfoBackground() {
		return infoBackground;
	}

	public Image getPayloadBackground() {
		return payloadBackground;
	}

	public Image getStartBackground1() {
		return startBackground1;
	}

	public Image getStartBackground2() {
		return startBackground2;
	}

	public Image getAbilityIcon() {
		return abilityIcon;
	}

	public Image getGraveyardIcon() {
		return graveyardIcon;
	}

	public Image getArmorIcon() {
		return armorIcon;
	}

}
