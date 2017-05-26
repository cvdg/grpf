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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {
	private static final DateFormat FORMAT = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	
	public static String format(String message) {
		StringBuilder tmp = new StringBuilder();
		tmp.append(FORMAT.format(new Date()));
		tmp.append(" grpf[");
		tmp.append(ProcessUtil.getPID());
		tmp.append("] ");
		tmp.append(message);

		return tmp.toString();
	}
}
