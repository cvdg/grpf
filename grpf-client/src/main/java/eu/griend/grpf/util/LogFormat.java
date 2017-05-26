package eu.griend.grpf.util;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogFormat {
	private static final DateFormat FORMAT = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	
	private static Object lock = new Object();
	private static long pid = 0;

	public static long getPID() {
		synchronized (LogFormat.lock) {
			if (LogFormat.pid == 0) {
				RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
				String name = bean.getName();
				LogFormat.pid = Long.parseLong(name.split("@")[0]);
			}
		}

		return LogFormat.pid;
	}

	public static String format(String message) {
		StringBuilder tmp = new StringBuilder();
		tmp.append(FORMAT.format(new Date()));
		tmp.append(" grpf[");
		tmp.append(getPID());
		tmp.append("] ");
		tmp.append(message);

		return tmp.toString();
	}
}
