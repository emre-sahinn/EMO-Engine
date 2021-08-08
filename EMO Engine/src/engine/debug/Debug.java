package engine.debug;

public class Debug {
	public static void Log(String msg) {
		Log(msg, LogLevel.Debug);
	}
	
	public static void Log(String msg, LogLevel lvl) {
		String prefix = "[UNKNOWN]";
		if(lvl == LogLevel.Debug) {
			prefix = "[DEBUG]";
		}
		else if(lvl == LogLevel.Low) {
			prefix = "[LOW]";
		}
		else if(lvl == LogLevel.Medium) {
			prefix = "[MEDIUM]";
		}
		else if(lvl == LogLevel.High) {
			prefix = "[HIGH]";
		}
		else if(lvl == LogLevel.Error) {
			prefix = "[ERROR]";
		}
		
		
		prefix += " ";
		System.out.println(prefix + msg);
	}
	
	public static void LogError(String msg) {
		System.err.println(msg);
	}
}
