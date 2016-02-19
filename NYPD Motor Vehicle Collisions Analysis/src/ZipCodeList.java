
import java.util.ArrayList;
import java.util.Iterator;

/**
 * ZipCodeList contains collision objects that all occured in the same zip
 * code. It keeps track of additional information like total number of
 * collisions, injuries and fatalities. 
 * @author Joanna K. and Dana Kirsanov
 *
 */
public class ZipCodeList  implements Iterable <Collision>, Comparable<ZipCodeList>{
	
	private ArrayList<Collision> list; 
	
	private String zip; 
	
	private int totalNumOfCollisions;
	private int totalNumOfPersonsInjured;
	private int totalNumOfPersonsKilled;
	private int totalNumOfCyclistsInjured;
	private int totalNumOfCyclistsKilled;
	private int totalNumOfPedestriansInjured;
	private int totalNumOfPedestriansKilled;
	private int totalNumOfMotoristsInjured;
	private int totalNumOfMotoristsKilled;
	
	/**
	 * Creates a ZipCodeList objects based on the first collision. The 
	 * zip code for this ZipCodeList is set to the zip code
	 * associated with the collision col. 
	 * @param col the initial collisions for this ZipCodeList object 
	 */
	public ZipCodeList ( Collision col ) { 
		list = new ArrayList<Collision> () ;
		zip = col.getZip();
		add(col);
	}
	
	/**
	 * Adds another Collision object to this ZipCodeList object. 
	 * @param col a Collision object to be added to this ZipCodeList object 
	 * @throws IllegalArgumentException when the zip code of the new Collision 
	 * object col is not the same as the zip code for this ZipCodeList object 
	 */
	public void add (Collision col) throws IllegalArgumentException {
		if (col == null ) return;
		if (!col.getZip().equals(zip))
			throw new IllegalArgumentException ("Error: zip codes are not matching. ") ;
		
		list.add(col);
		
		totalNumOfCollisions ++;
		totalNumOfPersonsInjured += col.getPersonsInjured();
		totalNumOfPersonsKilled += col.getPersonsKilled();
		totalNumOfCyclistsInjured += col.getCyclistsInjured();
		totalNumOfCyclistsKilled += col.getCyclistsKilled();
		totalNumOfPedestriansInjured += col.getPedestriansInjured();
		totalNumOfPedestriansKilled += col.getPedestriansKilled();
		totalNumOfMotoristsInjured += col.getMotoristsInjured();
		totalNumOfMotoristsKilled += col.getMotoristsKilled();
		
	}

	/**
	 * Returns an iterator for this ZipCodeList object. 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Collision> iterator() {
		return list.iterator();
	}



	/**
	 * Computes the hash code for this ZipCodeList object. The hashcode
	 * is based on the zip code associated with this object. 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
		return result;
	}

	/**
	 * Returns true if this ZipCodeList object and the parameter have the 
	 * same zip code associated with them. Returns false otherwise. 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ZipCodeList other = (ZipCodeList) obj;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}

	/** 
	 * Compares two ZioCodeList objects based on the zip code value stored in them.
	 * The results are based on string comparison of the two zip codes. 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * Compares two ZipCodeList objects based on the zip codes. 
	 */
	@Override
	public int compareTo(ZipCodeList o) {
		return zip.compareTo(o.zip);
	}

	

	/**
	 * Returns the zip code of this ZipCodeList object 
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}


	/**
	 * Returns the total number of collisions of this ZipCodeList object 
	 * @return the totalNumOfCollisions
	 */
	public int getTotalNumOfCollisions() {
		return totalNumOfCollisions;
	}

	/**
	 * Returns the total number of persons injured  of this ZipCodeList object 
	 * @return the totalNumOfPersonsInjured
	 */
	public int getTotalNumOfPersonsInjured() {
		return totalNumOfPersonsInjured;
	}

	/**
	 * Returns the total number of persons killed  of this ZipCodeList object 
	 * @return the totalNumOfPersonsKilled
	 */
	public int getTotalNumOfPersonsKilled() {
		return totalNumOfPersonsKilled;
	}

	/**
	 * Returns the total number of cyclists injured  of this ZipCodeList object 
	 * @return the totalNumOfCyclistsInjured
	 */
	public int getTotalNumOfCyclistsInjured() {
		return totalNumOfCyclistsInjured;
	}

	/**
	 * Returns the total number of cyclists killed  of this ZipCodeList object 
	 * @return the totalNumOfCyclistsKilled
	 */
	public int getTotalNumOfCyclistsKilled() {
		return totalNumOfCyclistsKilled;
	}

	/**
	 * Returns the total number of pedestrians injured  of this ZipCodeList object 
	 * @return the totalNumOfPedestriansInjured
	 */
	public int getTotalNumOfPedestriansInjured() {
		return totalNumOfPedestriansInjured;
	}

	/**
	 * Returns the total number of pedestrians killed  of this ZipCodeList object 
	 * @return the totalNumOfPedestriansKilled
	 */
	public int getTotalNumOfPedestriansKilled() {
		return totalNumOfPedestriansKilled;
	}

	/**
	 * Returns the total number of motorists injured  of this ZipCodeList object 
	 * @return the totalNumOfMotoristsInjured
	 */
	public int getTotalNumOfMotoristsInjured() {
		return totalNumOfMotoristsInjured;
	}

	/**
	 * Returns the total number of motorists killed  of this ZipCodeList object 
	 * @return the totalNumOfMotoristsKilled
	 */
	public int getTotalNumOfMotoristsKilled() {
		return totalNumOfMotoristsKilled;
	}

	/** 
	 * Computes and returns a string representation of this ZipCodeList object. 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ZipCodeList for " + zip + ": " + totalNumOfCollisions + ", " + totalNumOfPersonsInjured + ", "
				+ totalNumOfPersonsKilled + ", " + totalNumOfCyclistsInjured + ", " + totalNumOfCyclistsKilled + ", "
				+ totalNumOfPedestriansInjured + ", " + totalNumOfPedestriansKilled + ", " + totalNumOfMotoristsInjured
				+ ", " + totalNumOfMotoristsKilled ;
	}
	
	
}
