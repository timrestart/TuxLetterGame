package game;

public class Chronometre {

	private long begin;
	private long end;
	private long current;
	private int limite;

	public Chronometre(int limite) {
		//intialisation
		this.limite = limite;
	}

	public void start() {
		begin = System.currentTimeMillis();
	}

	public void stop() {
		end = begin + (limite * 1000);
	}

	public long getTime() {
		return end - begin;
	}

	public long getMilliseconds() {
		return end - begin;
	}

	public int getSeconds() {
		return (int) ((end - begin) / 1000.0);
	}

	public double getMinutes() {
		return (end - begin) / 60000.0;
	}

	public double getHours() {
		return (end - begin) / 3600000.0;
	}

	/**
	 * Method to know if it remains time.
	 */
	public boolean remainsTime() {
		current = System.currentTimeMillis();
		int timeSpent;
		timeSpent = (int) ((current - begin) / 1000.0);
		return timeSpent < getSeconds();
	}

	// return the timeSpent in second
	public int timeSpent() {
		current = System.currentTimeMillis();
		int ts;
		ts = (int) ((current - begin) / 1000.0);
		return ts;
	}

}
