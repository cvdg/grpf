/*
 * grpf - Grapefruit
 *   
 * Copyright (C) 2017 C.A. van de Griend <c.vande.griend@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package eu.griend.grpf.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Simple logging framework.
 * 
 * Do NOT use for a high volume production environment.
 * 
 * @author cvdg
 */
public class LogUtil {
	private static final DateFormat FORMAT = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	private static String nameLog = null;

	public static void setNameLog(String name) {
		LogUtil.nameLog = name;
	}

	public static void log(String message) {
		Date now = new Date();
		File file = null;
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		PrintWriter printWriter = null;
		StringBuilder stringBuilder = null;

		try {
			file = new File(MessageFormat.format(nameLog, now));
			fileWriter = new FileWriter(file, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(bufferedWriter);

			stringBuilder = new StringBuilder();
			stringBuilder.append(FORMAT.format(now));
			stringBuilder.append(" grpf[");
			stringBuilder.append(ProcessUtil.getPID());
			stringBuilder.append("] ");
			stringBuilder.append(message);

			printWriter.println(stringBuilder);
			printWriter.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (printWriter != null) {
				printWriter.close();
			}
		}
	}
}
