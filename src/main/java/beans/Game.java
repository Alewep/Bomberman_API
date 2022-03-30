package beans;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Game implements Validation{
	private String winer,nameMap;
	private Timestamp endTime;
	private Timestamp startTime;
	
	
	public Game(String winer, String nameMap, Timestamp startTime, Timestamp endTime) {
		
		this.winer = winer;
		this.nameMap = nameMap;
		this.startTime = startTime;
		this.endTime = endTime;
		
	}


	public String getWiner() {
		return winer;
	}

	public void setWiner(String winer) {
		this.winer = winer;
	}

	public String getNameMap() {
		return nameMap;
	}

	public void setNameMap(String name_map) {
		this.nameMap = name_map;
	}

	

	public Timestamp getEndTime() {
		return endTime;
	}


	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}


	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	
	public long getDurationMinutes() {
		return TimeUnit.MILLISECONDS.toMinutes(startTime.getTime() - endTime.getTime());
 	}

	@Override
	public void validate() throws BeanException {
		if (startTime == null)
			throw new BeanException("Game start time is required");
		else if (endTime == null)
			throw new BeanException("The duration of the game is required");
	}
	
	
	

}
