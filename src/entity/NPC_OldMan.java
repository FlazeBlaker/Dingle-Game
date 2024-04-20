package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity {

	public NPC_OldMan(GamePanel gp) {
		super(gp);

		direction = "down";
		speed = 1;
		getImage();
		setDialogue();
	}

	public void getImage() {

		up1 = setup("/npc/oldman_up_1");
		up2 = setup("/npc/oldman_up_2");
		down1 = setup("/npc/oldman_down_1");
		down2 = setup("/npc/oldman_down_2");
		right1 = setup("/npc/oldman_right_1");
		right2 = setup("/npc/oldman_right_2");
		left1 = setup("/npc/oldman_left_1");
		left2 = setup("/npc/oldman_left_2");
	}

	public void setDialogue() {
		dialogues[0] = "Waddup blud.";
		dialogues[1] = "So you decided to come to this rizz \nisland to find someone?";
		dialogues[2] = "I used to be a great rizzler just like \nkai cenat and duke dennis.";
		dialogues[3] = "Hope you find a skibidi ohio goblin \ngirl!";
	}

	public void setAction() {
		actionLookCounter++;

		if (actionLookCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100) + 1;
			if (i <= 25) {
				direction = "up";
			}
			if (i > 25 && i <= 50) {
				direction = "down";
			}
			if (i > 50 && i <= 75) {
				direction = "left";
			}
			if (i > 75) {
				direction = "right";
			}
			actionLookCounter = 0;
		}
	}

	public void speak() {
		super.speak();
	}

}
