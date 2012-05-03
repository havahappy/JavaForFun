package de.android.mobiads.list;

import android.graphics.Bitmap;


/**
 * Encapsulates information about an ads entry
 */
public final class AdsEntry {
	
	private final String title;
	private final String text;
	private final Bitmap icon;

	public AdsEntry(final String title, final String text, final Bitmap icon) {
		this.title = title;
		this.text = text;
		this.icon = icon;
	}

	/**
	 * @return Title of ads entry
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return Text of ads entry
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return Icon of this ads entry
	 */
	public Bitmap getIcon() {
		return icon;
	}

}
