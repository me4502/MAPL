package com.me4502.MAPL.GUI;

import com.me4502.MAPL.MAPL;

public abstract class MAPLSlider extends MAPLButton {

	public float currentValue = 1f;
	float maxValue = 1f;

	public MAPLSlider(int x, int y, int width, int height, String name, float currentValue, float maxValue) {
		super(x, y, width, height, name);
		this.currentValue = currentValue;
		this.maxValue = maxValue;
	}

	public MAPLSlider(int x, int y, int width, int height, String name, float currentValue, float maxValue, float scaleX, float scaleY) {
		super(x, y, width, height, name, null, scaleX, scaleY);
		this.currentValue = currentValue;
		this.maxValue = maxValue;
	}

	public abstract void onChange(MAPLSlider slider);

	public boolean onDrag(int oldX, int oldY, int newX, int newY) {

		if(!isVisible()) return false;
		if(isMouseOver(oldX, oldY)) {

			int difX = newX - x;
			currentValue = (float)difX/(width-40) * maxValue;
			if(currentValue < 0)
				currentValue = 0f;
			if(currentValue > maxValue)
				currentValue = maxValue;
			onChange(this);
			return true;
		}
		return false;
	}

	@Override
	public void clickAction(int button) {
	}

	@Override
	public void onRender(int x, int y) {
		int currentX = (int) (currentValue / maxValue * (width-40));
		MAPL.inst().getGUIRenderer().renderSlider(this, x, y, currentX + 20);
	}
}