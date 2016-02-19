

import java.util.ArrayList;

/**
 * Collision objects represent individual collisions occuring on NYC streets. 
 * Each object contains information regarding the time, location, number 
 * of injuries and fatalities and types of involved vehicles. 
 * @author Joanna K. and Dana Kirsanov
 *
 */
public class Collision implements Comparable <Collision>{
	
	static private SortOrder sortOrder = SortOrder.UNIQUEKEY; 
	
	
	private String date;
	private String time;
	private String borough;
	private String zip;
	private int personsInjured;
	private int personsKilled;
	private int pedestriansInjured;
	private int pedestriansKilled;
	private int cyclistsInjured;
	private int cyclistsKilled;
	private int motoristsInjured;
	private int motoristsKilled;
	private String vehicleCode1;
	private String vehicleCode2;
	private String uniqueKey;


	
	/**
	 * Creates a Collision object given an array of entries. 
	 * There should be 21 string entries in the following order:
	 * date
	 * time
	 * borough
	 * zip
	 * lattitude^
	 * longitude ^
	 * on street name^
	 * cross street name ^
	 * personsInjured
	 * personsKilled
	 * pedestriansInjured
	 * pedestriansKilled
	 * cyclistsInjured
	 * cyclistsKilled
	 * motoristsInjured
	 * motoristsKilled
	 * contributing factor vehicle 1^
	 * contributing factor vehicle 2^
	 * uniqueKey
	 * vehicleCode1
	 * vehicleCode2
	 * The entries indicated with ^ are not used. 
	 * 
	 * @param entries an array of entries containing information about the
	 * collision
	 * @throws IllegalArgumentException when the Collision object cannot be created
	 * due to errors or incompleteness of the entries parameter 
	 */
	public Collision ( ArrayList<String> entries ) throws IllegalArgumentException {
		
		date = entries.get(0);
		time = entries.get(1);
		borough = entries.get(2);
		zip = entries.get(3);
		if (!verifyZip(zip)) {
			throw new IllegalArgumentException ("invalid zip");
		}
		try {
			personsInjured = Integer.parseInt(entries.get(8));
			personsKilled = Integer.parseInt(entries.get(9));
			pedestriansInjured = Integer.parseInt(entries.get(10));
			pedestriansKilled = Integer.parseInt(entries.get(11));
			cyclistsInjured = Integer.parseInt(entries.get(12));
			cyclistsKilled = Integer.parseInt(entries.get(13));
			motoristsInjured = Integer.parseInt(entries.get(14));
			motoristsKilled = Integer.parseInt(entries.get(15));			
		}
		catch (NumberFormatException ex ) {
			throw new IllegalArgumentException( ex.getMessage() );
		}
		
		uniqueKey = entries.get(18);
		vehicleCode1 = entries.get(19);
		vehicleCode2 = entries.get(20);
		
	}
	
	/*
	 * Verifies accuracy of the zip code. 
	 * @param zip the zip code to be verified 
	 * @return true if zip is a valid zip code, false otherwise 
	 */
	private boolean verifyZip (String zip ) {
		if ( zip.length()!=5 ) return false;
		for (int i = 0; i < zip.length(); i++ ) {
			if ( !Character.isDigit( zip.charAt(i) ) ) {
				return false;
			}
		}
		return true;
	}


	/** 
	 * Computes and returns string representation of this Collision object. 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Collision [date=" + date + ", time=" + time + ", borough=" + borough + ", zip=" + zip
				+ ", personsInjured=" + personsInjured + ", personsKilled=" + personsKilled + ", pedestriansInjured="
				+ pedestriansInjured + ", pedestriansKilled=" + pedestriansKilled + ", cyclistsInjured="
				+ cyclistsInjured + ", cyclistsKilled=" + cyclistsKilled + ", motoristsInjured=" + motoristsInjured
				+ ", motoristsKilled=" + motoristsKilled + ", vehicleCode1=" + vehicleCode1 + ", vehicleCode2="
				+ vehicleCode2 + ", uniqueKey=" + uniqueKey + "]";
	}
	
	

	/**
	 * Set the sort order for Collision objects to be one of the allowed values by the SortOrder enumerator. 
	 * @param sortOrder the sortOrder to set
	 */
	public static void setSortOrder(SortOrder sortOrder) {
		Collision.sortOrder = sortOrder;
	}

	/**
	 * Compares two Collision objects based on their zip code, number of cyclist-injuries or 
	 * number of person-injuries. The comparison is determined by the value of a flag set by 
	 * setSortOrder() method. 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Collision other) {
		if ( sortOrder == SortOrder.ZIP ) {
			return this.zip.compareTo(other.zip); 
		}
		else if (sortOrder == SortOrder.CYCLISTS) {
			return ( (this.cyclistsInjured + this.cyclistsKilled) 
					- (other.cyclistsInjured + this.cyclistsKilled ) );
		}
		else if (sortOrder == SortOrder.PERSONS) {
			return ( (this.personsInjured + this.personsKilled) 
					- (other.personsInjured + this.personsKilled ) );
		}
		else if (sortOrder == SortOrder.UNIQUEKEY) {
			return this.uniqueKey.compareTo(other.uniqueKey); 
		}
		return 0;
	}

	/**
	 * Return the time of this Collision object. 
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Return the zip code of this Collision object. 
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * Return the number of injured cyclists of this Collision object. 
	 * @return the cyclistsInjured
	 */
	public int getCyclistsInjured() {
		return cyclistsInjured;
	}

	/**
	 * Return the number of killed cyclists of this Collision object. 
	 * @return the cyclistsKilled
	 */
	public int getCyclistsKilled() {
		return cyclistsKilled;
	}

	/**
	 * Return the number of injured persons of this Collision object.
	 * @return the personsInjured
	 */
	public int getPersonsInjured() {
		return personsInjured;
	}

	/**
	 * Return the number of killed persons of this Collision object. 
	 * @return the personsKilled
	 */
	public int getPersonsKilled() {
		return personsKilled;
	}

	
	
	/**
	 * Return the number of injured pedestrians of this Collision object.
	 * @return the pedestriansInjured
	 */
	public int getPedestriansInjured() {
		return pedestriansInjured;
	}

	/**
	 * Return the number of killed pedestrians of this Collision object. 
	 * @return the pedestriansKilled
	 */
	public int getPedestriansKilled() {
		return pedestriansKilled;
	}

	/**
	 * Return the number of injured motorists of this Collision object.
	 * @return the motoristsInjured
	 */
	public int getMotoristsInjured() {
		return motoristsInjured;
	}

	/**
	 * Return the number of killed motorists of this Collision object. 
	 * @return the motoristsKilled
	 */
	public int getMotoristsKilled() {
		return motoristsKilled;
	}

	/**
	 * Return the vehicle 1 of this Collision object. 
	 * @return the vehicleCode1
	 */
	public String getVehicleCode1() {
		return vehicleCode1;
	}

	/**
	 * Return the vehicle 2 of this Collision object. 
	 * @return the vehicleCode2
	 */
	public String getVehicleCode2() {
		return vehicleCode2;
	}
	
	/**
	 * Return the unique key of this Collision object. 
	 * @return the uniqueKey
	 */
	public String getUniqueKey() {
		return uniqueKey;
	}
}




