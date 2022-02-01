import java.io.File;
import java.util.Scanner;

public class Test {
	
public static String[] populateFromFile(String strFilename) throws Exception {

		Scanner scanner = new Scanner(new File(strFilename));

		// YOU MAY NEED TO DEFINE SOME LOCAL VARIABLES HERE!

		// while loop for reading the lot design
		
		String[] myArray1;
		int numberOfRows = 0;
		System.out.println("begin");
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
		System.out.println(numberOfRows);

		myArray1 = new String[numberOfRows];
		// while loop for reading occupancy data

		Scanner scanners = new Scanner(new File(strFilename));
		int increment  = 0;
		while (scanners.hasNext()) {
			String stri = scanners.nextLine();
			if (!( ((stri.startsWith("1"))||(stri.startsWith("2"))||(stri.startsWith("3"))||(stri.startsWith("4"))||(stri.startsWith("5")) || (stri.startsWith("6")) || (stri.startsWith("7")) || (stri.startsWith("8")) || (stri.startsWith("9")) || (stri.startsWith("0"))))){ 
				
				continue;
				}
			else{
				stri = stri.replaceAll("\\s","");
				stri = stri.replaceAll(",","");
				System.out.println(stri);
				myArray1[increment] = stri;
				increment++;
				
				//for(int i = 0; i < numberOfRows; i++){
					//System.out.println(i);
					//System.out.println(stri);
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
	public static void main(String[] args) throws Exception {

	System.out.print("Please enter the name of the file to process: ");

	Scanner scanner = new Scanner(System.in);

	String strFilename = scanner.nextLine();

	populateFromFile(strFilename);

	System.out.println("success!");

	//ParkingLot lot = new ParkingLot(strFilename);



	}
}