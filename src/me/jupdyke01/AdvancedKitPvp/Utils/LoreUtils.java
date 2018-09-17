package me.jupdyke01.AdvancedKitPvp.Utils;

import org.apache.commons.lang.StringUtils;

import net.md_5.bungee.api.ChatColor;

public class LoreUtils {
	
	@SuppressWarnings("deprecation")
	public static String formatMaterial(String mat) {
		String end = mat;
		
		end = end.replace("_", " ");
		end = end.toLowerCase();
		end = StringUtils.capitaliseAllWords(end);
		end = ChatColor.AQUA + end;
		return end;
	}
	
}
