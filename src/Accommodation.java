import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Iterator;
import java.util.*;

class Accommodation{
    private String type;
    private String name;
    private String idnumber;
    private String price; //int?
    private String description;
    private String area;
    private boolean hasImage;
    private String owner;
    private HashMap<String,String> facilities;
    private ArrayList<Reservation> reservationsList;
    private ArrayList<Reservation> cancellationsList;

    /**
     * This is the main constructor for every new Accommodation object.
     * It creates a new HashMap that contains the accommodation's facilities.txt and puts some facilities.txt in it.
     * It also creates a new ArrayList, which is a list of the reservations for this accommodation.
     */
    public Accommodation(){
        reservationsList = new ArrayList<>();
        cancellationsList = new ArrayList<>();
    }

    /**
     * This is another constructor for every new Accommodation object.
     * It initializes the fields of this class based on the values of the parameters.
     * @param type the type of the accommodation
     * @param name the name of the accommodation
     * @param idnumber the id number of the accommodation
     * @param price the price of the accommodation for each night
     * @param description the description of the accommodation
     * @param area the area the accommodation is located in
     * @param reserved checks if the accommodation is reserved
     * @param hasImage checks if the accommodation has an image
     * @param owner the username of the owner
     */
    public Accommodation(String type, String name, String idnumber, String price, String description, String area, boolean reserved, boolean hasImage, String owner){
        this.type=type;
        this.name=name;
        this.idnumber=idnumber;
        this.price=price;
        this.description=description;
        this.area=area;
        this.hasImage=hasImage;
        this.owner=owner;

        reservationsList = new ArrayList<>();
        cancellationsList = new ArrayList<>();
    }

    /**
     * This method reads the file "accommodtypes.txt" and checks if the accommodation type exists or not.
     * @param keytype the type of the accommodation we are searching for
     * @return        if the accommodation type is in the file or not
     */
    public static boolean accommodTypeExists(String keytype){
        boolean found=false;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/accommodtypes.txt"));
            String line;
            while(((line = br.readLine()) != null) && !found){
                if(line.equals(keytype)){
                    found=true;
                }
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return found;
    }

    /**
     * This methods writes in the file "accommodtypes.txt" the type of accommodation
     * that is passed as a parameter.
     * @param type
     */
    public static void addAccommodTypeToFile(String type) {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("src/files/accommodtypes.txt",true));
            bw.write(type+"\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method gets the details of the accommodation by calling accommodDetailsString() from the Menu class.
     * Then the details of the accommodation are added to the file "accommodationsdatabase.txt".
     * @param type the type of the accommodation
     * @param name the name of the accommodation
     * @param owner the username of the owner of the accommodation
     */
    public static void addAccommodationToFile(String type, String name, String owner,String ID,String description,String area,String price) {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("src/files/accommodationsdatabase.txt",true));
            //String[] accommodDetails = Menu.accommodDetailsString();
            bw.write(name+"%"+type+"%"+ID+"%"+description+"%"+area+"%"+price+"%"+"false"+"%"+owner+"\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method writes in the file "facilities.txt" the facilities.txt of the accommodation.
     * @param name the name of the accommodation
     * @param facilities the array that contains the facilities.txt of the accommodation
     */
    public static void addFacilitiesToFile(String name, String[] facilities) {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("src/files/facilities.txt",true));
            bw.write(name+"%");
            int i=1;
            for(String facility: facilities){
                if(i==facilities.length){
                    bw.write(facility);
                }
                else{
                    bw.write(facility+"%");
                }
                i++;
            }
            bw.write("\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns an Accommodation object which is created based on what the Provider types in terminal.
     * @param username     the username of the provider
     */
    public static void addAccommodation(String username,String name,String type,String ID,String description,String area,String price,String[] facilities){
        Accommodation newaccom = new Accommodation();
        newaccom.owner = username;

        if(!(accommodTypeExists(type))){
            addAccommodTypeToFile(type);
        }

        addAccommodationToFile(type,name,username,ID,description,area,price);
        addFacilitiesToFile(name,facilities);
    }

    /**
     * This method reads the file "accommodationsdatabase.txt" and checks if the accommodation exists or not.
     * @param keyname the name of the accommodation we are searching for
     * @return        if the accommodation type is in the file or not
     */
    public static boolean accommodationExists(String keyname){
        boolean found=false;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/accommodationsdatabase.txt"));
            String line;
            while(((line = br.readLine()) != null) && !found){
                String[] words = line.split("%");
                if(words[0].equals(keyname)){
                    found=true;
                }
            }
            br.close();
        }
        catch (FileNotFoundException p){
            p.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return found;
    }

    /**
     * The method reads the file "accommodationsdatabase.txt" and checks if the provider is the owner of the accommodation
     * based on their username and the name of the accommodation.
     * @param username the username of the provider
     * @param accommod_name the name of the accommodation
     * @return              if the provider is the owner of this accommodation
     */
    public static boolean usernameIsOwnerOfThisAccommod(String username, String accommod_name){
        boolean found=false;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/accommodationsdatabase.txt"));
            String line;
            while(((line = br.readLine()) != null) && !found){
                String[] words = line.split("%");
                if(words[0].equals(accommod_name)) {
                    if (words[7].equals(username)) {
                        found = true;
                    }
                }
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return found;
    }

    /**
     * This method reads the file "accommodationsdatabase.txt" and removes the line from the file that
     * is about the accommodation that we want to remove.
     * To achieve this, the method reads the file line by line, writing each line out to a temporary output file.
     * When it encounters the line that is about the accommodation that we want to remove, it skips writing that one out.
     * It then renames the output file.
     * @param keyname the name of the accommodation the we want to remove
     */
    public static void removeAcccommodationFromFile(String keyname){
        try {
            File inputFile = new File("src/files/accommodationsdatabase.txt");
            File tempFile = new File("myTempFile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] words = currentLine.split("%");
                if (words[0].equals(keyname)) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();

            inputFile.delete();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method reads the file "facilities.txt" and removes the line from the file that
     * is about the facilitites of the accommodation that we want to remove.
     * To achieve this, the method reads the file line by line, writing each line out to a temporary output file.
     * When it encounters the line that is about the accommodation that we want to remove, it skips writing that one out.
     * It then renames the output file.
     * @param keyname the name of the accommodation that we want to remove
     */
    public static void removeFacilitiesFromFile(String keyname){
        try {
            File inputFile = new File("src/files/facilities.txt");
            File tempFile = new File("myTempFile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] words = currentLine.split("%");
                if (words[0].equals(keyname)) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();

            inputFile.delete();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method removes one or more Accommodation objects from the list of accommodations (accommodList)
     * based on the name of the Accommodation the the Provider types in terminal.
     * @param username    the username of the provider
     */
    public static String removeaccomodation(String username,String deletekey){
        String message;
            if(accommodationExists(deletekey) && usernameIsOwnerOfThisAccommod(username, deletekey)) {
                removeAcccommodationFromFile(deletekey);
                removeFacilitiesFromFile(deletekey);
                message = "ok";
            }
            else{
                message = "no";
            }
        return message;
    }

    /**
     * This method reads the file "facilities.txt" and edits the name of the accommodation with the new one.
     * To achieve this, we read the file one line at a time and replace the line as we read the file
     * with the new one and store the updated line in StringBuffer.
     * @param name the name of the accommodation
     * @param newtext the new name of the accommodation
     */
    public static void editNameInFacilities(String name, String newtext) {
        try {
            // input the (modified) file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(
                    new FileReader("src/files/facilities.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            while ((line = file.readLine()) != null) {
                String[] words = line.split("%");
                if(words[0].equals(name)) {
                    line = newtext+"%";
                    int i=1;
                    for(String facility: words){
                        if(i!=1) {
                            if (i == words.length) {
                                line = line + facility;
                            } else {
                                line = line + facility + "%";
                            }
                        }
                        i++;
                    }
                }
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream("src/files/facilities.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method reads the file "accommodationsdatabase.txt" and edits the field of the accommodation that
     * the provider wants to change.
     * To achieve this, we read the file one line at a time and replace the line as we read the file
     * with the new one and store the updated line in StringBuffer.
     * @param name the name of the accommodation
     * @param newtext the newtext that is going to replace the old one
     */
    public static void editAccommodInFile(String name, String newtext) {
        String[] splitnewtext=newtext.split("%");
        try {
            // input the (modified) file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(
                    new FileReader("src/files/accommodationsdatabase.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                String[] words = line.split("%");
                if(words[0].equals(name)) {
                      if(!(words[0].equals(splitnewtext[0]))) { //change the name of the accommodation
                            editNameInFacilities(name, splitnewtext[0]);
                      }
                      line = splitnewtext[0]+"%"+splitnewtext[1]+"%"+splitnewtext[2]+"%"+splitnewtext[3]+"%"+splitnewtext[4]+"%"+splitnewtext[5]+"%"+"false"+"%"+words[7]; // replace the line here
                }
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream("src/files/accommodationsdatabase.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (Exception e) {
            //System.out.println("Problem reading file.");
            e.printStackTrace();
        }
    }

    /**
     * This method reads the file "facilities.txt" and removes from the file the facility that the provider wants to remove.
     * To achieve this, we read the file one line at a time and replace the line as we read the file
     * with the new one and store the updated line in StringBuffer.
     * @param name the name of the accommodation
     * @param keyfacility the facility that the provider wants to remove
     */
    public static void removeFacilityFromFile(String name, String keyfacility) {
        try {
            // input the (modified) file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(
                    new FileReader("src/files/facilities.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            while ((line = file.readLine()) != null) {
                String[] words = line.split("%");
                if(words[0].equals(name)) {
                    line = name+"%";
                    int i=0; //index of words
                    int cnt=1; //number of facilities.txt
                    for(String facility: words){
                        if(i!=0 && !facility.equals(keyfacility)) {
                            if (cnt == words.length-2) {
                                line = line + facility;
                            } else {
                                line = line + facility + "%";
                            }
                            cnt++;
                        }
                        i++;
                    }
                }
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream("src/files/facilities.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (Exception e) {
            //System.out.println("Problem reading file.");
            e.printStackTrace();
        }
    }

    /**
     * This method reads the file "facilities.txt" and adds to the file the facility that the provider wants to add.
     * To achieve this, we read the file one line at a time and replace the line as we read the file
     * with the new one and store the updated line in StringBuffer.
     * @param name the name of the accommodation
     * @param keyfacility the facility that the provider wants to add
     */
    public static void addFacilityToFile(String name, String[] keyfacility) {
        boolean found = false;
        try {
            // input the (modified) file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(
                    new FileReader("src/files/facilities.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            while ((line = file.readLine()) != null) {
                String[] words = line.split("%");
                if(words[0].equals(name)) {
                    found = true;
                    line = name+"%";
                    for(int i=0;i< keyfacility.length-1;i++){
                        line += keyfacility[i]+"%";
                    }
                    line += keyfacility[keyfacility.length-1];
                }
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream("src/files/facilities.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (Exception e) {
            //System.out.println("Problem reading file.");
            e.printStackTrace();
        }
        if(!found){
            System.out.println("Could not remove facility");
            System.out.println("Your accommodation did not contain facility");
        }
    }

    /**
     * This method lets a Provider edit an Accommodation object that they have added in the list of
     * accommodations (accommodList), based on the name of the Accommodation object.
     * @param username     the username of the provider
     */
    public static String editAccommodation(String username,String keyname,String type,String ID,String description,String area,String price,String[] facilities){
        String choice;
            if(accommodationExists(keyname) && usernameIsOwnerOfThisAccommod(username, keyname)){
                    String newtext;
                    newtext=keyname+"%"+type+"%"+ID+"%"+description+"%"+area+"%"+price;
                    editAccommodInFile(keyname, newtext);
                    addFacilitiesToFile(keyname, facilities);
                    choice="ok";
            }
            else{
                choice="not";
            }
        return choice;
    }

    /**
     * This method prints the accommodations that are located in this area.
     * @param area the area that the user is searching for accommodations in
     * @return the number of accommodations in this area
     */
    public static String[] accommodationsInThisArea(String area){
        try {
        BufferedReader count = new BufferedReader(
                new FileReader("src/files/accommodationsdatabase.txt"));
        int cnt = 0,noOfLines=0;
        String line;
        while ((line = count.readLine()) != null) {
            String words[] = line.split("%");
            noOfLines++;
        }
        String[] accommodationsInThisArea = new String[noOfLines];
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/accommodationsdatabase.txt"));
            while((line = br.readLine()) != null){
                String[] words = line.split("%");
                if(words[4].equals(area)){
                    //System.out.println(words[0]+" "+words[1]+" "+words[2]+" "+words[3]+" "+words[4]+" "+words[5]+" "+words[7]+"\n");
                    accommodationsInThisArea[cnt]=words[0];
                    cnt++;
                }
            }
            br.close();
            return accommodationsInThisArea;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method prints the accommodations that are available based on the area, the type
     * and the price range that the customer wants.
     * @param area the area that the customer wants the accommodation to be located
     * @param type the type of accommodation the customer is searching for
     * @param lowestprice the lowest price the customer is willing to pay
     * @param highestprice the highest price the customer is willing to pay
     * @return the number of accommodations in this price range
     */
    public static int accommodationsInThisPriceRange(String area, String type, int lowestprice, int highestprice){
        int cnt = 0;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/accommodationsdatabase.txt"));
            String line;
            while((line = br.readLine()) != null){
                String[] words = line.split("%");
                if(words[4].equals(area) && words[1].equals(type)){
                    int price = Integer.parseInt(words[5]);
                    if(price>=lowestprice && price<=highestprice){
                        System.out.println(words[0]+" "+words[1]+" "+words[2]+" "+words[3]+" "+words[4]+" "+words[5]+" "+words[7]+"\n");
                        cnt++;
                    }
                }
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return cnt;
    }

    /**
     * This method return "true" if the accommodation has all the facilities.txt the customer wants and "false" if it doesn't.
     * @param name name of the accommodation
     * @param facilitiesWanted the facilities.txt the customer wants
     * @return accommodHasAllTheseFacilities when all conditions are met
     */
    public static boolean accommodHasAllTheseFacilities(String name, ArrayList<String> facilitiesWanted){
        boolean accommodHasAllTheseFacilities = false;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/facilities.txt"));
            String line;
            while((line = br.readLine()) != null){
                String[] words = line.split("%");
                if(words[0].equals(name)){
                    int cnt=0;
                    for(String fw : facilitiesWanted){
                        int i=0;
                        for(String facility: words){
                            if(i!=0){ //ignore the name of the accommodation
                                if(facility.equals(fw)){
                                    cnt++;
                                }
                            }
                            i++;
                        }
                    }
                    if(cnt==facilitiesWanted.size()){
                        accommodHasAllTheseFacilities = true;
                    }
                }
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return accommodHasAllTheseFacilities;
    }

    /**
     * This method prints the accommodations that are available bases on the criteria of the customer.
     * @param area the area that the customer wants the accommodation to be located
     * @param type the type of accommodation the customer is searching for
     * @param lowestprice the lowest price the customer is willing to pay
     * @param highestprice the highest price the customer is willing to pay
     * @param facilitiesWanted the facilities.txt the customer wants
     * @return the number of accommodation that meet the customer's criteria
     */
    public static int findAccommodations(String area, String type, int lowestprice, int highestprice, ArrayList<String> facilitiesWanted){
        int cnt = 0;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/accommodationsdatabase.txt"));
            String line;
            while((line = br.readLine()) != null){
                String[] words = line.split("%");
                if(words[4].equals(area) && words[1].equals(type)){
                    int price = Integer.parseInt(words[5]);
                    if(price>=lowestprice && price<=highestprice){
                        if(accommodHasAllTheseFacilities(words[0], facilitiesWanted)){
                            //System.out.println("Found accommodation: " + words[0]);
                            cnt++;
                        }
                    }
                }
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return cnt;
    }

    /**
     * This method reads the file "accommodtypes.txt" and print the types of accommodations that are available.
     */
    public static void printAccommodTypes(){
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/accommodtypes.txt"));
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * This method takes a String as a parameter and checks if it is numeric or not.
     * @param str a string
     * @return if it numeric or not
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * This method lets a Customer search for accommodations based on the area they are looking for
     * and the price range they are willing to pay.
     * The costumer also has the option to choose the facilities.txt they want the Accommodation to have.
     */
    public static void searchAccommodation(){
        String choice;
        Scanner in = new Scanner(System.in);
        do{
            System.out.println("Type the area you are searching for accommodation");
            String areaWanted = in.nextLine();
            int cnt=0;
            //int cnt = accommodationsInThisArea(areaWanted);
            if(cnt==0){
                System.out.println("There are no accommodations in this area");
            }
            else{ //found accommodations in this area
                System.out.println("Type the type of accommodation you are searching for");
                System.out.println("Choose from the available types below");
                printAccommodTypes();
                String typeWanted = in.nextLine();
                //έλεγχος αν υπάρχει ο τύπος
                while(!accommodTypeExists(typeWanted)){
                    System.out.println("This accommodation type does not exist!");
                    System.out.println("Please try again.");
                    typeWanted = in.nextLine();
                }
                System.out.println("Type the lowest price you are willing to pay");
                String lowestprice_str = in.nextLine();
                while(!isNumeric(lowestprice_str)){
                    System.out.println("This is not a number!");
                    System.out.println("Please try again.");
                    lowestprice_str = in.nextLine();
                }

                System.out.println("Type the highest price you are willing to pay");
                String highestprice_str = in.nextLine();
                while(!isNumeric(highestprice_str)){
                    System.out.println("This is not a number!");
                    System.out.println("Please try again.");
                    highestprice_str = in.nextLine();
                }

                int lowestprice = Integer.parseInt(lowestprice_str);
                int highestprice = Integer.parseInt(highestprice_str);

                cnt = accommodationsInThisPriceRange(areaWanted, typeWanted, lowestprice, highestprice);
                if(cnt==0){
                    System.out.println("There are no accommodations matching your criteria!");
                }

                else{ //there are accommodations matching the customer's criteria
                    ArrayList<String> facilitiesWanted = new ArrayList<>();
                    //search for facilities.txt
                    System.out.println("Do you want to choose facilities.txt? (Y/N)");
                    choice = in.nextLine();
                    if(choice.equals("Y") || choice.equals("y")){
                        //show available facilities.txt ?
                        System.out.println("Type the facility you want");
                        choice = in.nextLine();
                        facilitiesWanted.add(choice);

                        System.out.println("Do you want to choose more facilities.txt? (Y/N)");
                        choice = in.nextLine();
                        while(choice.equals("Y") || choice.equals("y")){
                            //show available facilities.txt with all previous wanted facilities.txt
                            System.out.println("Type the facility you want");
                            choice = in.nextLine();
                            facilitiesWanted.add(choice);

                            System.out.println("Do you want to choose more facilities.txt? (Y/N)");
                            choice = in.nextLine();
                        }
                    }
                    cnt = findAccommodations(areaWanted, typeWanted, lowestprice, highestprice, facilitiesWanted);
                    if (cnt == 0) {
                        System.out.println("There are no accommodations based on your criteria");
                    }
                }
            }
            System.out.println("Do you want to search something else? (Type (1) for Yes or (2) for No)");
            choice = in.nextLine();
        }while(choice.equals("1"));
    }

    /**
     * Getters and setters of the private fields of Reservations class.
     */
    public void setType(String type){
        this.type=type;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setIDnumber(String idnumber){
        this.idnumber=idnumber;
    }

    public void setPrice(String price){
        this.price=price;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public void setArea(String area){
        this.area=area;
    }

    public void setHasImage(boolean hasImage){
        this.hasImage=hasImage;
    }

    public String getType(){
        return this.type;
    }

    public String getName(){
        return this.name;
    }

    public String getIDnumber(){
        return this.idnumber;
    }

    public String getPrice(){
        return this.price;
    }

    public String getDescription(){
        return this.description;
    }

    public String getArea(){
        return this.area;
    }

    public String getOwner(){
        return this.owner;
    }

    public boolean getHasImage(){
        return this.hasImage;
    }

    public Map<String,String> getFacilities(){
        return this.facilities;
    }

    public ArrayList<Reservation> getReservationsList(){
        return this.reservationsList;
    }

    public ArrayList<Reservation> getCancellationsList(){ return this.cancellationsList; }

    public void setCancellationsList(Reservation reservation){ cancellationsList.add(reservation); }

}