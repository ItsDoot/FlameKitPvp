package com.bear53.ecore.chatmechanics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatMechanics {

	public static Logger log = Logger.getLogger("Minecraft");
	
	public static List<String> staff_only = new ArrayList<String>();

	public static List<String> bad_words = new ArrayList<String>(Arrays.asList(
			"shit", "fuck", "cunt", "bitch", "whore", "slut", "wank",
			"asshole", "cock", "dick", "clit", "homo", "fag", "queer",
			"nigger", "dike", "dyke", "retard", "motherfucker", "vagina",
			"boob", "pussy", "rape", "gay", "penis", "cunt", "titty", "anus",
			"faggot"));

	public static boolean hasBadWord(String msg) {
		for (String s : msg.split(" ")) {
			for (String bad : bad_words) {
				if (s.toLowerCase().contains(bad.toLowerCase())) {
					return true;
				}
			}
		}

		return false;
	}

	public static String censorMessage(String msg) {
		String personal_msg = "";
		if (msg == null) {
			return "";
		}
		if (!(msg.contains(" "))) {
			msg += " ";
		}
		for (String s : msg.split(" ")) {
			for (String bad : bad_words) {
				if (s.toLowerCase().contains(bad.toLowerCase())) {
					int letters = bad.length();
					String replace_char = "";
					while (letters > 0) {
						replace_char += "*";
						letters--;
					}
					int censor_start = 0;
					int censor_end = 1;

					censor_start = s.toLowerCase().indexOf(bad);
					censor_end = censor_start + bad.length();

					String real_bad_word = s
							.substring(censor_start, censor_end);

					s = s.replaceAll(real_bad_word, replace_char);
				}
			}
			personal_msg += s + " ";
		}

		if (personal_msg.endsWith(" ")) {
			personal_msg = personal_msg.substring(0,
					personal_msg.lastIndexOf(" "));
		}
		return personal_msg;
	}

	public static void sendStaffMessage(Player sender, String raw_message) {
		List<Player> to_send_local = new ArrayList<Player>();

		for (Player pl : Bukkit.getOnlinePlayers()) {
			if (pl.hasPermission("evolution.staff")) {
				to_send_local.add(pl);
			}
		}

		for (Player staff : to_send_local) {

			String message = raw_message;
			if (hasAdultFilter(staff)) {
				message = censorMessage(message);
			}
			if (message.endsWith(" ")) {
				message = message.substring(0, message.length() - 1);
			}

			message = fixCapsLock(message);

			staff.sendMessage(ChatColor.GOLD + "<" + ChatColor.BOLD + "SC"
					+ ChatColor.GOLD + "> " + sender.getName() + ": " + message);
		}
		log.info("<SC>" + sender.getName() + ": " + raw_message);
	}

	public static boolean hasAdultFilter(Player string) {
		if (!string.hasPermission("evolution.staff")) {
			return false; // Filter
			// is
			// disabled.
		}
		return true;
	}

	public static String fixCapsLock(String msg) {
		StringTokenizer st = new StringTokenizer(msg);
		String new_msg = "";
		int exception = 0;
		while (st.hasMoreTokens()) {
			String a = st.nextToken();
			if (a.equals(a.toUpperCase())
					&& !((a.startsWith(":") || a.startsWith(";")))) {
				exception++;
				if (exception <= 1) {
					new_msg += a + " ";
					continue;
				}
				a = a.charAt(0) + a.substring(1).toLowerCase();
			}

			new_msg += a + " ";
		}

		if (new_msg.endsWith(" ")) {
			new_msg = new_msg.substring(0, new_msg.length() - 1);
		}

		return new_msg;
	}
}