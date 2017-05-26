package eu.griend.grpf.event;

import java.io.Serializable;

public class GrapefruitEvent implements Serializable {
	private static final long serialVersionUID = -8228249914366133603L;
	private Object parent = null;
	private String command = null;

	public GrapefruitEvent(Object parent, String command) {
		this.parent = parent;
		this.command = command;
	}

	public Object getParent() {
		return this.parent;
	}

	public String getCommand() {
		return this.command;
	}
}
