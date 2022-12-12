package vdm.p1.engine;

public class Notification {
	private int delaySecs;
	private String tittle, subtittle, content;
	public Notification(String tittle, String content, String subtittle, int delay ){
		this.tittle=tittle;
		this.subtittle=subtittle;
		this.content=content;
		this.delaySecs=delay;
	}
}
