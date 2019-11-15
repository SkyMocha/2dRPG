package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Action;
import com.mygdx.game.Entity;
import com.mygdx.game.Main;
import com.mygdx.game.Player;

public class Fight {
	Entity[] enemies;
	public Stage stage;
	
	BitmapFont font;
	TextButton button;
    TextButtonStyle textButtonStyle;
    LabelStyle labelStyle;
    Label actionText;
    	
	public Fight (Entity[] tenemies) {
		stage = new Stage(Main.viewport);
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/YeonSung-Regular.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		
		generator.scaleForPixelHeight((int)Math.ceil(Main.SCREEN_HEIGHT));
		parameter.size = (int)Math.ceil(40);
		
		parameter.minFilter = TextureFilter.Nearest;
		parameter.magFilter = TextureFilter.MipMapLinearNearest;
		
		font = generator.generateFont(parameter);
		
		textButtonStyle = new TextButtonStyle();
		labelStyle = new LabelStyle();
		
		textButtonStyle.font = font;
		labelStyle.font = font;
		
		enemies = tenemies;
		
//		actionText = new Label("", labelStyle);
		
		int i = 0;
		for (Action action : Player.actions) {
			action.button.setX(0, Align.bottomLeft);
			action.button.setY(Main.SCREEN_HEIGHT - action.button.getHeight() - i);
			stage.addActor(action.button);
			i += 70;
		}
		
	}
	
}
