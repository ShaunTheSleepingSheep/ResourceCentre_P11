import java.util.ArrayList;

public class ResourceCentre {
	
	private static final int OPTION_QUIT = 5;

	public static void main(String[] args) {

		ArrayList<Camcorder> camcorderList = new ArrayList<Camcorder>();
		ArrayList<Chromebook> chromebookList = new ArrayList<Chromebook>();

		camcorderList.add(new Camcorder("CC001", "Sony HDR-CX405", 35));
		camcorderList.add(new Camcorder("CC002", "Panasonic HC-MDH2", 10));
		chromebookList.add(new Chromebook("CB001", "ASUS Chromebook ", "Win 10"));
		chromebookList.add(new Chromebook("CB002", "HP Chromebook", "Win 10"));

		int option = 0;

		while (option != OPTION_QUIT) {

			ResourceCentre.menu();
			option = Helper.readInt("Enter an option > ");

			if (option == 1) {
				// View all items
				ResourceCentre.viewAllCamcorder(camcorderList);
				ResourceCentre.viewAllChromebook(chromebookList);
			} 
			
			else if (option == 2) {
				// Add a new item
				ResourceCentre.itemTypeMenu("ADD");
				
				int itemType = Helper.readInt("Enter option to select item type > ");

				if (itemType == 1) {
					// Add a camcorder
					Camcorder cc = (Camcorder)inputItem("cc");
					ResourceCentre.addCamcorder(camcorderList, cc);

				} 
				
				else if (itemType == 2) {
					// Add Chromebook
					Chromebook cb = (Chromebook)inputItem("cb");
					ResourceCentre.addChromebook(chromebookList, cb);

				} 
				
				else {
					System.out.println("Invalid type");
				}

			} 
			
			else if (option == 3) {
				// Loan item
				ResourceCentre.itemTypeMenu("LOAN");
				
				int itemType = Helper.readInt("Enter option to select item type > ");

				if (itemType == 1) {
					// Loan camcorder
					ResourceCentre.loanCamcorder(camcorderList);
				} 
				
				else if (itemType == 2) {
					// Loan Chromebook
					ResourceCentre.loanChromebook(chromebookList);
				} 
				
				else {
					System.out.println("Invalid type");
				}

			} 
			
			else if (option == 4) {
				// Return item
				ResourceCentre.itemTypeMenu("RETURN");
				
				int itemType = Helper.readInt("Enter option to select item type > ");
				
				if (itemType == 1) {
					// Return camcorder
					ResourceCentre.returnCamcorder(camcorderList);
				} 
				
				else if (itemType == 2) {
					// Return Chromebook
					ResourceCentre.returnChromebook(chromebookList);
				} 
				
				else {
					System.out.println("Invalid type");
				}

			} 
			
			else if (option == 5) {
				System.out.println("Bye!");
			} 
			
			else {
				System.out.println("Invalid option");
			}
		}
	}

	public static void menu() {
		ResourceCentre.setHeader("RESOURCE CENTRE APP");
		System.out.println("1. Display Inventory");
		System.out.println("2. Add item");
		System.out.println("3. Loan item");
		System.out.println("4. Return item");
		System.out.println("5. Quit");
		Helper.line(80, "-");
	}
	
	public static void setHeader(String header) {
		Helper.line(80, "-");
		System.out.println(header);
		Helper.line(80, "-");
	}

	public static String showAvailability(boolean isAvailable) {
		String avail;

		if (isAvailable == true) {
			avail = "Yes";
		} 
		
		else {
			avail = "No";
		}
	
		return avail;
	}
	
	public static void itemTypeMenu(String type) {
		ResourceCentre.setHeader(type);				
		ResourceCentre.setHeader("ITEM TYPES");
		System.out.println("1. Camcorder");
		System.out.println("2. Chromebook");
	}

	//================================= Option 1 View (CRUD - Read) =================================
	public static String retrieveAllCamcorder(ArrayList<Camcorder> camcorderList) {
		String output = "";

		for (Camcorder i : camcorderList) {
			output += String.format(ResourceCentre.columnSpacing("cc"), i.getAssetTag(),	i.getDescription(), 
					ResourceCentre.showAvailability(i.getIsAvailable()), i.getDueDate(), i.getOpticalZoom());
		}
		
		return output;
	}
	
	public static void viewAllCamcorder(ArrayList<Camcorder> camcorderList) {
		String output = ResourceCentre.headerAndColumns("CAMCORDER LIST", "OPTICAL ZOOM");
		
		output += retrieveAllCamcorder(camcorderList);	
		System.out.println(output);
	}

	public static String retrieveAllChromebook(ArrayList<Chromebook> chromebookList) {
		String output = "";
		
		for (Chromebook i : chromebookList) {
			output += String.format(ResourceCentre.columnSpacing("cb"), i.getAssetTag(),
					i.getDescription(), ResourceCentre.showAvailability(i.getIsAvailable()),
					i.getDueDate(), i.getOs());
		}
		
		return output;
	}
	
	public static void viewAllChromebook(ArrayList<Chromebook> chromebookList) {
		String output = ResourceCentre.headerAndColumns("CHROMEBOOK LIST", "OPERATING SYSTEM");
		
		output += retrieveAllChromebook(chromebookList);
		System.out.println(output);
	}
	
	public static String headerAndColumns(String header, String lastColumn) {
		ResourceCentre.setHeader(header);
		String output = String.format(ResourceCentre.columnSpacing(""), "ASSET TAG", "DESCRIPTION",
				"AVAILABLE", "DUE DATE", lastColumn);
		
		return output;
	}
	
	public static String columnSpacing(String type) {
		String columnSpacing = "";
	
		if (type == "cb") {
			columnSpacing = "%-10s %-30s %-10s %-10s %-20s\n";
		}
		
		else if (type == "cc") {
			columnSpacing = "%-10s %-30s %-10s %-10s %-20d\n";
		}
		
		else {
			columnSpacing = "%-10s %-30s %-10s %-10s %-20s\n";
		}
		
		return columnSpacing;
	}

	//================================= Option 2 Add (CRUD - Create)=================================
	public static void addCamcorder(ArrayList<Camcorder> camcorderList, Camcorder cc) {
		
		camcorderList.add(cc);
		System.out.println("Camcorder added");
	}
	
	public static Item inputItem(String type) {
		String tag = Helper.readString("Enter asset tag > ");
		String description = Helper.readString("Enter description > ");
		
		if (type == "cc") {
			int zoom = Helper.readInt("Enter optical zoom > ");
			
			Camcorder cc = new Camcorder(tag, description, zoom);
			return cc;
		}
		
		else if (type == "cb"){
			String os = Helper.readString("Enter operating system > ");
			
			Chromebook cb = new Chromebook(tag, description, os);
			return cb;
		}
		
		else {
			return null;
		}
	}
	
	public static void addChromebook(ArrayList<Chromebook> chromebookList, Chromebook cb) {

		chromebookList.add(cb);
		System.out.println("Chromebook added");
	}
	
	//================================= Option 3 Loan (CURD- Update) =================================
	public static boolean doLoanCamcorder(ArrayList<Camcorder> camcorderList, String tag, String dueDate) {
		
		boolean isLoaned = false;

		for (Camcorder i : camcorderList) {
			
			String assetTag = i.getAssetTag();
			
			if (tag.equalsIgnoreCase(assetTag) && i.getIsAvailable() == true) {
				i.setIsAvailable(false);
				i.setDueDate(dueDate);
				
				isLoaned = true;
			}
		}
		return isLoaned;
	}

	public static void loanCamcorder(ArrayList<Camcorder> camcorderList) {
		ResourceCentre.viewAllCamcorder(camcorderList);
		
		String tag = ResourceCentre.userInput("tag");
		String due = ResourceCentre.userInput("due");
		
		Boolean isLoaned = doLoanCamcorder(camcorderList, tag, due);
		
		if (isLoaned == false) {
			System.out.println("Invalid asset tag");
		} 
		
		else {
			System.out.println("Camcorder " + tag + " loaned out");
		}
	}
	

	public static boolean doLoanChromebook(ArrayList<Chromebook> chromebookList, String tag, String dueDate) {
		
		boolean isLoaned = false;

		for (Chromebook i : chromebookList) {
			String assetTag = i.getAssetTag();
			
			if (tag.equalsIgnoreCase(assetTag) && i.getIsAvailable() == true) {
				
				i.setIsAvailable(false);
				i.setDueDate(dueDate);
				
				isLoaned = true;
			}
		}
		return isLoaned;
	}

	public static void loanChromebook(ArrayList<Chromebook> chromebookList) {
		ResourceCentre.viewAllChromebook(chromebookList);
	
		String tag = ResourceCentre.userInput("tag");
		String due = ResourceCentre.userInput("due");
	
		Boolean isLoaned = doLoanChromebook(chromebookList, tag, due);
	
		if (isLoaned == false) {
			System.out.println("Invalid asset tag");
		} 
		
		else {
			System.out.println("Chromebook " + tag + " loaned out");
		}	
	}
	
	public static String userInput(String type) {
		
		if (type == "tag") {
			String tag = Helper.readString("Enter asset tag > ");
			
			return tag;
		}
		
		else if (type == "due") {
			String due = Helper.readString("Enter due date > ");
			
			return due;
		}
		
		else {
			return null;
		}
	}

	//================================= Option 4 Return (CURD- Update)=================================
	public static boolean doReturnCamcorder(ArrayList<Camcorder> camcorderList,String tag) {
		boolean isReturned = false;

		for (Camcorder i : camcorderList) {
			
			if (tag.equalsIgnoreCase(i.getAssetTag()) && i.getIsAvailable() == false) {
				i.setIsAvailable(true);
				i.setDueDate("");
			
				isReturned = true;
			}
		}
		return isReturned;
	}

	public static void returnCamcorder(ArrayList<Camcorder> camcorderList) {
		ResourceCentre.viewAllCamcorder(camcorderList);
		String tag = ResourceCentre.userInput("tag");
	
		boolean isReturned = doReturnCamcorder(camcorderList, tag);
		
		if (isReturned == false) {
			System.out.println("Invalid asset tag");
		} 
		
		else {
			System.out.println("Camcorder " + tag + " returned");
		}
	}
	
	public static boolean doReturnChromebook(ArrayList<Chromebook> chromebookList,String tag) {
		boolean isReturned = false;

		for (Chromebook i : chromebookList) {
			if (tag.equalsIgnoreCase(i.getAssetTag()) && i.getIsAvailable() == false) {
				i.setIsAvailable(true);
				i.setDueDate("");
				
				isReturned = true;
			}
		}
		return isReturned;
	}
	
	public static void returnChromebook(ArrayList<Chromebook> chromebookList) {
		// write your code here
		ResourceCentre.viewAllChromebook(chromebookList);
		String tag = ResourceCentre.userInput("tag");
		
		boolean isReturned = doReturnChromebook(chromebookList, tag);
		
		if (isReturned == false) {
			System.out.println("Invalid asset tag");
		} 
		
		else {
			System.out.println("Chromebook " + tag + " returned");
		}
	}
}
