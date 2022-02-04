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

				//System.out.println(theArray[i][j]);

				if (theArray[i][j].equals("E")){

					//System.out.println("TEST");

					lotDesign[i][j] = CarType.ELECTRIC;
				}
				else if (theArray[i][j].equals("S")){
					lotDesign[i][j] = CarType.SMALL;
				}
				else if (theArray[i][j].equals("R")){
					lotDesign[i][j] = CarType.REGULAR;
				}
				else if (theArray[i][j].equals("L")){
					lotDesign[i][j] = CarType.LARGE;
				}
				else if (theArray[i][j].equals("N")){
					lotDesign[i][j] = CarType.NA;
				}
			}
		}
		occupancy = new Car[rows][spotRows];

		// populate lotDesign and occupancy; you can do so by
		// writing your own code or alternatively completing the 
		// private populateFromFile(...) that I have provided

		// making an array to store all of the cars that will evetully go into the occupancy array
		String[] stringArrayOfCars;
		stringArrayOfCars = populateFromFile(strFilename);
		int length = stringArrayOfCars.length;

		Car[] arrayOfCars = new Car[length];

		//using a loop to make the Car objects

		for(int i = 0; i < length; i++){
			String information = stringArrayOfCars[i];
			String subString = information.substring(3);
			char typeOfCarChar = information.charAt(2);

			//System.out.println(typeOfCarChar);

			String typeOfCarString = String.valueOf(typeOfCarChar);

			//System.out.println(typeOfCarString);

			CarType typeOfCar;

			// converting the string to a Cartype so that I can create Car objects

			if (typeOfCarString.equals("E")){
					typeOfCar = CarType.ELECTRIC;
				}
				else if (typeOfCarString.equals("S")){
					typeOfCar = CarType.SMALL;
				}
				else if (typeOfCarString.equals("R")){
					typeOfCar = CarType.REGULAR;
				}
				else {
					typeOfCar = CarType.LARGE;
				}



			arrayOfCars[i] = new Car(typeOfCar,subString);
		}

		
		// now that arrayOfCars is complete, another loop is used to put them where they belong in the occupancy array
		for (int i = 0; i < length ; i++){
			//System.out.println(stringArrayOfCars[i]);

			char whichRow = stringArrayOfCars[i].charAt(0);
			char whichColumn = stringArrayOfCars[i].charAt(1);

			//System.out.println(whichRow);
			//System.out.println(whichColumn);


			int  whichRowInt = Character.getNumericValue(whichRow);
			int  whichColumnInt = Character.getNumericValue(whichColumn);

			//System.out.println("Break");


			//System.out.println(whichRowInt);
			//System.out.println(whichColumnInt);

			//System.out.println(arrayOfCars[i]);

			//System.out.println(whichRowInt + " " + whichColumnInt);
			if (canParkAt(whichRowInt,whichColumnInt,arrayOfCars[i])){
				occupancy[whichRowInt][whichColumnInt] = arrayOfCars[i];
			}
			else{
				System.out.println("Car " + Util.getLabelByCarType(arrayOfCars[i].getType()) +"(" + arrayOfCars[i].getPlateNum() +") cannot be parked at" + "(" + whichRowInt + "," + whichColumnInt +")" );
			}
			//occupancy[whichRowInt][whichColumnInt] = arrayOfCars[i];
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
		occupancy[i][j] = c;
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
		if ( i < 0 || i > occupancy.length || j < 0 || j > occupancy[0].length || occupancy[i][j] == null){
			return null;
		}
		return occupancy[i][j];
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
		//System.out.println(occupancy.length);

		//System.out.println(occupancy[0].length);


		if ( (i < 0) || (i > occupancy.length - 1) || (j < 0) || (j > occupancy[0].length - 1)){
			//System.out.println("TEST");
			return false; //checking to see if i and j are out of range
		}
		if (c == null){
			return false;
		}
		if (occupancy[i][j] != null){
			return false; // checks if there is a car already  parked there
		}

		if (c.getType() == CarType.ELECTRIC){
			if (lotDesign[i][j] == CarType.NA){
				return false;
			}
			else{
				return true;
			}
		}
		else if (c.getType() == CarType.SMALL){
			if (lotDesign[i][j] != CarType.ELECTRIC || lotDesign[i][j] != CarType.NA){
				return true;
			}
			else{
				return false;
			}
		}
		else if (c.getType() == CarType.REGULAR){
			if (lotDesign[i][j] == CarType.REGULAR || lotDesign[i][j] == CarType.LARGE){
				return true;
			}
			else{
				return false;
			}
		}
		else if (c.getType() == CarType.LARGE){
			if (lotDesign[i][j] == CarType.LARGE){
				return true;
			}
			else{
				return false;
			}
		}
		
		return false; //remove later??
	}

	/**
	 * @return the total capacity of the parking lot excluding spots that cannot be
	 *         used for parking (i.e., excluding spots that point to CarType.NA)
	 */
	public int getTotalCapacity() {
		// WRITE YOUR CODE HERE!
		int counter = 0;

		//System.out.println(lotDesign.length - 1);
		//System.out.println(lotDesign[0].length - 1);


		for (int i = 0; i < (lotDesign.length ) ; i++){
			for (int j = 0; j < (lotDesign[0].length ) ; j++){

				//System.out.println(i);
				//System.out.println(j);

				if (lotDesign[i][j] != CarType.NA){
					
					counter++;
				}
			}
		}
		return counter;
		//return -1; // REMOVE THIS STATEMENT AFTER IMPLEMENTING THIS METHOD

	}

	/**
	 * @return the total occupancy of the parking lot (i.e., the total number of
	 *         cars parked in the lot)
	 */
	public int getTotalOccupancy() {
		// WRITE YOUR CODE HERE!
		int counter = 0;

		//System.out.println(occupancy.length);
		//System.out.println(occupancy[0].length);

		for (int i = 0; i < occupancy.length  ; i++){
			for (int j = 0; j < occupancy[0].length  ; j++){
				if (occupancy[i][j] != null){
					counter++;
				}
			}
		}
		return counter;
		//return -1; // REMOVE THIS STATEMENT AFTER IMPLEMENTING THIS METHOD		
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

	private String[] populateFromFile(String strFilename) throws Exception {

	Scanner scanner = new Scanner(new File(strFilename));

		// YOU MAY NEED TO DEFINE SOME LOCAL VARIABLES HERE!

		// while loop for reading the lot design
		
		String[] myArray1;
		int numberOfRows = 0;
		//System.out.println("begin");
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			if (!( ((str.startsWith("1"))||(str.startsWith("2"))||(str.startsWith("3"))||(str.startsWith("4"))||(str.startsWith("5")) || (str.startsWith("6")) || (str.startsWith("7")) || (str.startsWith("8")) || (str.startsWith("9")) || (str.startsWith("0"))))){
				
				continue;
				}
			else {
				
				numberOfRows++;
				
				
			}
		}
		
		scanner.close();
		
		myArray1 = new String[numberOfRows];
		// while loop for reading occupancy data

		Scanner scanners = new Scanner(new File(strFilename));
		int increment = 0;
		while (scanners.hasNext()) {
			String stri = scanners.nextLine();
			if (!( ((stri.startsWith("1"))||(stri.startsWith("2"))||(stri.startsWith("3"))||(stri.startsWith("4"))||(stri.startsWith("5")) || (stri.startsWith("6")) || (stri.startsWith("7")) || (stri.startsWith("8")) || (stri.startsWith("9")) || (stri.startsWith("0"))))){ 
				
				continue;
				}
			else{
				stri = stri.replaceAll("\\s","");
				stri = stri.replaceAll(",","");
				myArray1[increment] = stri;
				increment++;
				//for(int i = 0; i < numberOfRows; i++){
					//myArray1[i] = stri;

					//for (int j = 0; j < str.length(); j++){
					//	char myArrayString2 = str.charAt(j);
					//	String myArrayString3 = String.valueOf(myArrayString2);						
					//	myArray1[i][j] = myArrayString3;
					//}
					
				//}
			}

		}
		scanners.close();

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
				//buffer.append(", ");

				if (j != (lotDesign[i].length-1)) { 
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
