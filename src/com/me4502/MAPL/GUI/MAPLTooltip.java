package com.me4502.MAPL.GUI;

import com.me4502.MAPL.MAPL;

public class MAPLTooltip extends MAPLGui {

	String text;

	public MAPLTooltip(int x, int y, String text) {
		super(x, y);
		this.text = text;
	}

	public String getText() {

		return text;
	}

	@Override
	public void onRender(int x, int y) {
		MAPL.inst().getGUIRenderer().renderTooltip(this);
	}

	@Override
	public boolean onClick(int x, int y, int button) {
		return false;
	}

	@Override
	public boolean isMouseOver(int x, int y) {
		return false;
	}
}