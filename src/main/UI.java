package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font EightBitDragon;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		InputStream is = getClass().getResourceAsStream("/font/EightBitDragon-anqx.ttf");
		try {
			EightBitDragon = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {

		this.g2 = g2;

		g2.setFont(EightBitDragon);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);

		if (gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		if (gp.gameState == gp.playState) {

		}
		if (gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		if (gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
	}

	private void drawTitleScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
		String text = "Dingle Game";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 3;
		g2.setColor(Color.gray);
		g2.drawString(text, x + 5, y + 5);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);

		x = gp.screenWidth / 2 - gp.tileSize;
		y += gp.tileSize * 2;
		g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));

		text = "NEW GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize * 3.5;
		g2.drawString(text, x, y);
		if(commandNum==0) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "LOAD GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum==1) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		text = "EXIT";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum==2) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		if(commandNum>2) {
			commandNum=0;
		}
		if(commandNum<0) {
			commandNum=2;
		}
	}

	private void drawDialogueScreen() {
		int x = gp.tileSize * 2;
		int y = gp.tileSize / 2;
		int width = gp.screenWidth - (gp.tileSize * 4);
		int height = gp.tileSize * 4;

		drawSubWindow(x, y, width, height);

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 23));
		x += gp.tileSize;
		y += gp.tileSize;

		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
	}

	public void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(0, 0, 0, 210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		c = new Color(255, 255, 255);
		g2.setStroke(new BasicStroke(5));
		g2.setColor(c);
		g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
	}

	private void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		String text = "Paused";

		int x = getXforCenteredText(text);

		int y = gp.screenHeight / 2;
		g2.drawString(text, x, y);

	}

	public int getXforCenteredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}
}
