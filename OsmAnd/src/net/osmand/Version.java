package net.osmand;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.osmand.plus.R;
import android.content.Context;

public class Version {
	
	private final String appVersion; 
	private final String appName;
	private final static String FREE_VERSION_NAME = "net.osmand";
	
	private Version(Context ctx) {
		appVersion = ctx.getString(R.string.app_version);
		appName = ctx.getString(R.string.app_name);
	}

	private static Version ver = null;
	private static Version getVersion(Context ctx){
		if(ver == null){
			ver = new Version(ctx);
		}
		return ver;
	}
	
	public static String getFullVersion(Context ctx){
		Version v = getVersion(ctx);
		return v.appName + " " + v.appVersion;
	}
	
	public static String getAppVersion(Context ctx){
		Version v = getVersion(ctx);
		return v.appVersion;
	}
	
	public static String getAppName(Context ctx){
		Version v = getVersion(ctx);
		return v.appName;
	}
	
	public static boolean isProductionVersion(Context ctx){
		Version v = getVersion(ctx);
		return !v.appVersion.contains("#");
	}

	public static String getVersionAsURLParam(Context ctx) {
		try {
			return "osmandver=" + URLEncoder.encode(getVersionForTracker(ctx), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public static boolean isFreeVersion(Context ctx){
		return ctx.getPackageName().equals(FREE_VERSION_NAME);
		
	}
	
	public static String getVersionForTracker(Context ctx) {
		String v = Version.getAppName(ctx);
		if(Version.isProductionVersion(ctx)){
			v = Version.getFullVersion(ctx);
		} else {
			v +=" test";
		}
		return v;
	}
}
