import java.io.File;
import java.util.Scanner;


/**
 * @author Mehrdad Sabetzadeh, University of Ottawa
 */
public class ParkingLot {
	/**
	 * The delimiter that separates values
	 */
	private static final String SEPARATOR = ",";

	/**
	 * The delimiter that separates the parking lot design section from the parked
	 * car data section
	 */
	private static final String SECTIONER = "###";

	/**
	 * Instance variable for storing the number of rows in a parking lot
	 */
	private int numRows;

	/**
	 * Instance variable for storing the number of spaces per row in a parking lot
	 */
	private int numSpotsPerRow;

	/**
	 * Instance variable (two-dimensional array) for storing the lot design
	 */
	private CarType[][] lotDesign;

	/**
	 * Instance variable (two-dimensional array) for storing occupancy information
	 * for the spots in the lot
	 */
	private Car[][] occupancy;

	/**
	 * Constructs a parking lot by loading a file
	 * 
	 * @param strFilename is the name of the file
	 */
	public ParkingLot(String strFilename) throws Exception {

		if (strFilename == null) {
			System.out.println("File name cannot be null.");
			return;
		}

		// determine numRows and numSpotsPerRow; you can do so by
		// writing your own code or alternatively completing the 
		// private calculateLotDimensions(...) that I have provided
		String[][] theArray = calculateLotDimensions(strFilename);

		int rows = theArray.length;
		int spotRows = theArray[0].length;
		
			
		

		// instantiate the lotDesign and occupancy variables!
		// WRITE YOUR CODE HERE!
		lotDesign = new CarType[rows][spotRows];

		for (int i = 0; i < rows ; i++){
			for (int j = 0; j < spotRows; j++){
				if (theArray[i][j] == "E"){
					lotDesign[i][j] = CarType.ELECTRIC;
				}
				else if (theArray[i][j] == "S"){
					lotDesign[i][j] = CarType.SMALL;
				}
				else if (theArray[i][j] == "R"){
					lotDesign[i][j] = CarType.REGULAR;
				}
				else if (theArray[i][j] == "L"){
					lotDesign[i][j] = CarType.LARGE;
				}
			}
		}

		occupancy = new Car[rows][spotRows];

		// populate lotDesign and occupancy; you can do so by
		// writing your own code or alternatively completing the 
		// private populateFromFile(...) that I have provided
		String[][] theArray1;
		theArray1 = populateFromFile(strFilename);
		int rows1 = theArray.length;
		int spotRows1 = theArray[0].length;

		for (int i = 0; i < rows1; i++){
			for(int j = 0; i < spotRows1; j++){
				
			}
		}
	}

	/**
	 * Parks a car (c) at a give location (i, j) within the parking lot.
	 * 
	 * @param i is the parking row index
	 * @param j is the index of the spot within row i
	 * @param c is the car to be parked
	 */
	public void park(int i, int j, Car c) {
		// WRITE YOUR CODE HERE!
	}

	/**
	 * Removes the car parked at a given location (i, j) in the parking lot
	 * 
	 * @param i is the parking row index
	 * @param j is the index of the spot within row i
	 * @return the car removed; the method returns null when either i or j are out
	 *         of range, or when there is no car parked at (i, j)
	 */
	public Car remove(int i, int j) {
		// WRITE YOUR CODE HERE!
		return null; // REMOVE THIS STATEMENT AFTER IMPLEMENTING THIS METHOD

	}

	/**
	 * Checks whether a car (which has a certain type) is allowed to park at
	 * location (i, j)
	 * 
	 * @param i is the parking row index
	 * @param j is the index of the spot within row i
	 * @return true if car c can park at (i, j) and false otherwise
	 */
	public boolean canParkAt(int i, int j, Car c) {
		// WRITE YOUR CODE HERE!
		return false; // REMOVE THIS STATEMENT AFTER IMPLEMENTING THIS METHOD

	}

	/**
	 * @return the total capacity of the parking lot excluding spots that cannot be
	 *         used for parking (i.e., excluding spots that point to CarType.NA)
	 */
	public int getTotalCapacity() {
		// WRITE YOUR CODE HERE!
		return -1; // REMOVE THIS STATEMENT AFTER IMPLEMENTING THIS METHOD

	}

	/**
	 * @return the total occupancy of the parking lot (i.e., the total number of
	 *         cars parked in the lot)
	 */
	public int getTotalOccupancy() {
		// WRITE YOUR CODE HERE!
		return -1; // REMOVE THIS STATEMENT AFTER IMPLEMENTING THIS METHOD		
	}

	private String[][] calculateLotDimensions(String strFilename) throws Exception {

		Scanner scanner = new Scanner(new File(strFilename));
		int numberOfRows = 0;
		int numberOfColumns = 0;
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			// WRITE YOUR CODE HERE!
			if (!((str.startsWith("N"))||(str.startsWith("E"))||(str.startsWith("S"))||(str.startsWith("R"))||(str.startsWith("L")))) {
				continue;
			}
			else{
				//System.out.println(str.replaceAll("\\s",""));
				str = str.replaceAll("\\s","");
				numberOfRows++;

				if (numberOfRows == 1){
			for (int i=0; i<str.length(); i++){
				if (str.charAt(i) != ','){
					numberOfColumns++;
				}
			}
			}
			}
			}
			//int numberOfColumns = 0;
			//for (int i=0; i<lineOne.length(); i++){
				//if (lineOne.charAt(i) != ","){
					//numberOfColumns++;
				//}
			//}
		//System.out.println(numberOfRows);
		//System.out.println(numberOfColumns);
		
		String[][] myArray;
		myArray = new String[numberOfRows][numberOfColumns];


		Scanner scanners = new Scanner(new File(strFilename));
		int numberOfRowsOne = 0;
		while (scanners.hasNext()) {
			
			String stri = scanners.nextLine();
			// WRITE YOUR CODE HERE!
				if (!((stri.startsWith("N"))||(stri.startsWith("E"))||(stri.startsWith("S"))||(stri.startsWith("R"))||(stri.startsWith("L")))) {
				
					continue;
				}
				else{
					//System.out.println(stri.replaceAll("\\s",""));
					stri = stri.replaceAll("\\s","");
					stri = stri.replaceAll(",","");
					
					for (int i=0; i<numberOfColumns; i++){
						char myArrayString = stri.charAt(i);
						String myArrayString1 = String.valueOf(myArrayString);
						myArray[numberOfRowsOne][i] = myArrayString1;
					}
				
					numberOfRowsOne++;
				
				}
		}
			//for (int i=0; i<numberOfRows; i++){
				//for (int j=0; j<numberOfColumns; j++){
					//System.out.println(myArray[i][j]);
				//}
			//}
		scanners.close();
		scanner.close();
		return myArray;
		
	}

	private String[][] populateFromFile(String strFilename) throws Exception {

		Scanner scanner = new Scanner(new File(strFilename));

		// YOU MAY NEED TO DEFINE SOME LOCAL VARIABLES HERE!

		// while loop for reading the lot design
		int numberOfColumns1 = 0;
		String[][] myArray1;
		int numberOfRows = 0;
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			if ((str.startsWith("N"))||(str.startsWith("E"))||(str.startsWith("S"))||(str.startsWith("R"))||(str.startsWith("L")) || (str.startsWith("#"))){
				continue;
				}
			else {
				str = str.replaceAll("\\s","");
				str = str.replaceAll(",","");
				numberOfRows++;
				numberOfColumns1 = str.length();
				
			}
		}
		myArray1 = new String[numberOfRows][numberOfColumns1];
		// while loop for reading occupancy data
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			if ((str.startsWith("N"))||(str.startsWith("E"))||(str.startsWith("S"))||(str.startsWith("R"))||(str.startsWith("L")) || (str.startsWith("#"))){ 
				continue;
				}
			else{
				str = str.replaceAll("\\s","");
				str = str.replaceAll(",","");
				System.out.println(str);
				for(int i = 0; i < numberOfRows; i++){
					for (int j = 0; j < str.length(); j++){
						char myArrayString2 = str.charAt(j);
						String myArrayString3 = String.valueOf(myArrayString2);						
						myArray1[i][j] = myArrayString3;
					}
					
				}
			}

		}scanner.close();

	return myArray1;
	}

	/**
	 * Produce string representation of the parking lot
	 * 
	 * @return String containing the parking lot information
	 */
	public String toString() {
		// NOTE: The implementation of this method is complete. You do NOT need to
		// change it for the assignment.
		StringBuffer buffer = new StringBuffer();
		buffer.append("==== Lot Design ====").append(System.lineSeparator());

		for (int i = 0; i < lotDesign.length; i++) {
			for (int j = 0; j < lotDesign[0].length; j++) {
				buffer.append((lotDesign[i][j] != null) ? Util.getLabelByCarType(lotDesign[i][j])
						: Util.getLabelByCarType(CarType.NA));
				if (j < numSpotsPerRow - 1) {
					buffer.append(", ");
				}
			}
			buffer.append(System.lineSeparator());
		}

		buffer.append(System.lineSeparator()).append("==== Parking Occupancy ====").append(System.lineSeparator());

		for (int i = 0; i < occupancy.length; i++) {
			for (int j = 0; j < occupancy[0].length; j++) {
				buffer.append(
						"(" + i + ", " + j + "): " + ((occupancy[i][j] != null) ? occupancy[i][j] : "Unoccupied"));
				buffer.append(System.lineSeparator());
			}

		}
		return buffer.toString();
	}

	/**
	 * <b>main</b> of the application. The method first reads from the standard
	 * input the name of the file to process. Next, it creates an instance of
	 * ParkingLot. Finally, it prints to the standard output information about the
	 * instance of the ParkingLot just created.
	 * 
	 * @param args command lines parameters (not used in the body of the method)
	 * @throws Exception
	 */

	public static void main(String args[]) throws Exception {

		StudentInfo.display();

		System.out.print("Please enter the name of the file to process: ");

		Scanner scanner = new Scanner(System.in);

		String strFilename = scanner.nextLine();

		ParkingLot lot = new ParkingLot(strFilename);

		System.out.println("Total number of parkable spots (capacity): " + lot.getTotalCapacity());

		System.out.println("Number of cars currently parked in the lot: " + lot.getTotalOccupancy());

		System.out.print(lot);

		//calculateLotDimensions(parking.inf);
		//populateFromFile("parking.inf");

	}
}
