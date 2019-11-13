package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.reflect.Method;
import com.mygdx.game.Main;
import com.mygdx.game.Player;

public class ChoiceButton {
	TextButton button;
    TextButtonStyle textButtonStyle;
    BitmapFont font;
	
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/YeonSung-Regular.ttf"));
	FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	
	int index;
    
	ChoiceButton (final String text) {
		
		generator.scaleForPixelHeight((int)Math.ceil(Main.SCREEN_HEIGHT));
		parameter.size = (int)Math.ceil(25);
		
		parameter.minFilter = TextureFilter.Nearest;
		parameter.magFilter = TextureFilter.MipMapLinearNearest;
		
		textButtonStyle = new TextButtonStyle();
		font = generator.generateFont(parameter);
		
		font.getData().setScale(2.5f, 2.5f);
		textButtonStyle.font = font;
		button = new TextButton(text, textButtonStyle);
	}

	// If the event requires a skill check, it sees whether you can pass it or not
	boolean choice () {
		if (button.getText().toString().contains("]")) {
			String prefix = button.getText().toString().split("]")[0].substring(1);
			System.out.println(prefix);
			String stat = prefix.split(" ")[0];
			int req = Integer.parseInt(prefix.split(" ")[1]);
					
			if (!requirnments(stat, req))
				return false;
		}
		return true;
		
	}
	
	boolean requirnments (String stat, int req) {
		if (stat.equals ("str") && req < Player.strength)
			return true;
		else if (stat.equals ("dex") && req < Player.dexterity)
			return true;
		else if (stat.equals ("chr") && req < Player.charisma)
			return true;
		else if (stat.equals ("int") && req < Player.intelligence)
			return true;
		else if (stat.equals ("wis") && req < Player.wisdom)
			return true;
		return false;
	}
	
}
