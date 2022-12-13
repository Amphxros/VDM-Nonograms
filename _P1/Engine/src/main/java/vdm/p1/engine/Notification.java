package vdm.p1.engine;

public final class Notification {
	private final String title;
	private final String subtitle;
	private final String content;
	private final int delay;

	public Notification(String title, String subtitle, String content, int delay) {
		this.title = title;
		this.subtitle = subtitle;
		this.content = content;
		this.delay = delay;
	}

	/**
	 * @return The delay in seconds.
	 */
	public int getDelay() {
		return delay;
	}

	/**
	 * @return The notification's contents.
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @return The notification's title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return The notification's subtitle.
	 */
	public String getSubtitle() {
		return subtitle;
	}
}
