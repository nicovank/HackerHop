package com.hackerhop.game.core.objects.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.hackerhop.game.core.utils.toggleable.ToggleableSprite;
import org.jbox2d.common.Vec2;

import static com.hackerhop.game.core.utils.GDXUtils.getMousePosition;

public class Button extends ToggleableSprite {

	/**
	 * Creates a new button with the given texture when not hovered, and the active texture when hovered.
	 *
	 * @param inactiveTexture the texture to display when the button is not hovered.
	 * @param activeTexture the texture to display when the button is hovered.
	 * @param position the position of the button.
	 */
	public Button(String inactiveTexture, String activeTexture, Vec2 position) {
		this(
				new Sprite(new Texture(activeTexture)),
				new Sprite(new Texture(inactiveTexture)),
				position
		);
	}

	private Button(Sprite activeSprite, Sprite inactiveSprite, Vec2 position) {
		super(
				() -> inactiveSprite.getBoundingRectangle().contains(getMousePosition()),
				activeSprite,
				inactiveSprite
		);

		super.getActiveSprite().setPosition(position.x, position.y);
		super.getInactiveSprite().setPosition(position.x, position.y);
	}

	/**
	 * Creates a new button with the given texture when not hovered, and the active texture when hovered.
	 * Also specifies the size of the button.
	 *
	 * @param inactiveTexture the texture to display when the button is not hovered.
	 * @param activeTexture the texture to display when the button is hovered.
	 * @param position the position of the button.
	 * @param size the size of the button.
	 */
	public Button(String inactiveTexture, String activeTexture, Vec2 position, Vec2 size) {
		this(inactiveTexture, activeTexture, position);

		super.getActiveSprite().setSize(size.x, size.y);
		super.getInactiveSprite().setSize(size.x, size.y);
	}
}
