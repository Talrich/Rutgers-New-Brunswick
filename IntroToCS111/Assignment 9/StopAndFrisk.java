import java.util.ArrayList;

/**
 * The StopAndFrisk class represents stop-and-frisk data, provided by
 * the New York Police Department (NYPD), that is used to compare
 * during when the policy was put in place and after the policy ended.
 * 
 * @author Tanvi Yamarthy
 * @author Vidushi Jindal
 */
public class StopAndFrisk {

    /*
     * The ArrayList keeps track of years that are loaded from CSV data file.
     * Each SFYear corresponds to 1 year of SFRecords. 
     * Each SFRecord corresponds to one stop and frisk occurrence.
     */ 
    private ArrayList<SFYear> database; 

    /*
     * Constructor creates and initializes the @database array
     * 
     * DO NOT update nor remove this constructor
     */
    public StopAndFrisk () {
        database = new ArrayList<>();
    }

    /*
     * Getter method for the database.
     * *** DO NOT REMOVE nor update this method ****
     */
    public ArrayList<SFYear> getDatabase() {
        return database;
    }

    /**
     * This method reads the records information from an input csv file and populates 
     * the database.
     * 
     * Each stop and frisk record is a line in the input csv file.
     * 
     * 1. Open file utilizing StdIn.setFile(csvFile)
     * 2. While the input still contains lines:
     *    - Read a record line (see assignment description on how to do this)
     *    - Create an object of type SFRecord containing the record information
     *    - If the record's year has already is present in the database:
     *        - Add the SFRecord to the year's records
     *    - If the record's year is not present in the database:
     *        - Create a new SFYear 
     *        - Add the SFRecord to the new SFYear
     *        - Add the new SFYear to the database ArrayList
     * 
     * @param csvFile
     */
    public void readFile(String csvFile) {
    // DO NOT remove these two lines
    StdIn.setFile(csvFile); // Opens the file
    StdIn.readLine();       // Reads and discards the header line

    String[] recordEntries;
    int year;
    String description;
    String gender;
    String race;
    String location;
    Boolean arrested;
    Boolean frisked;
    SFRecord record;
    SFYear yearRecord = null;  // Initialize yearRecord outside the loop
    String lineValue;

    while ((lineValue = StdIn.readLine()) != null) {
        recordEntries = lineValue.split(",");
        year = Integer.parseInt(recordEntries[0]);
        description = recordEntries[2];
        gender = recordEntries[52];
        race = recordEntries[66];
        location = recordEntries[71];
        arrested = recordEntries[13].equals("Y");
        frisked = recordEntries[16].equals("Y");
        record = new SFRecord(description, arrested, frisked, gender, race, location);

        // Check if the database contains the year
        boolean yearFound = false;
        for (int i = 0; i < database.size(); i++) {
            SFYear existingYear = database.get(i);
            if (existingYear.getcurrentYear() == year) {
                yearRecord = existingYear;
                yearFound = true;
                break;
            }
        }

        if (yearFound) {
            // If year is found in the database, add the record to its designated year
            yearRecord.addRecord(record);
            database.add(yearRecord);

        } 
        else 
        {
            // If year is not found, instantiate a new SFYear, add the record, and add the year to the database array
            yearRecord = new SFYear(year);
            yearRecord.addRecord(record);
            database.add(yearRecord);
        }
        
    }
}


    /**
     * This method returns the stop and frisk records of a given year where 
     * the people that was stopped was of the specified race.
     * 
     * @param year we are only interested in the records of year.
     * @param race we are only interested in the records of stops of people of race. 
     * @return an ArrayList containing all stop and frisk records for people of the 
     * parameters race and year.
     */

    public ArrayList<SFRecord> populationStopped ( int year, String race ) {

        ArrayList<SFRecord> arrList = new ArrayList<SFRecord>();
        
        for (int i = 0; i < database.size(); i++)
        {
            SFYear yearRecord = database.get(i);

            if (yearRecord.getcurrentYear() == year)
            {
                ArrayList<SFRecord> recordsForYear = yearRecord.getRecordsForYear();
                for (int j = 0; j < recordsForYear.size(); j++)
                {
                    SFRecord sfRecord = recordsForYear.get(j);
                    if (sfRecord.getRace().equals(race))
                    {
                        arrList.add(sfRecord);
                    }

                }
                break;
            }
        }
        
        return arrList;
    }

    /**
     * This method computes the percentage of records where the person was frisked and the
     * percentage of records where the person was arrested.
     * 
     * @param year we are only interested in the records of year.
     * @return the percent of the population that were frisked and the percent that
     *         were arrested.
     */
    public double[] friskedVSArrested ( int year ) {
        
        double friskedNum = 0; 
        double arrestedNum = 0;
        int recordNumber = 0;

        for (int i = 0; i < database.size(); i++)
        {
            SFYear yearRecord = database.get(i);

            if (yearRecord.getcurrentYear() == year)
            {
                ArrayList <SFRecord> recordsForYear = yearRecord.getRecordsForYear();
                for (int j = 0; j < recordsForYear.size(); j++)
                {
                    SFRecord sfRecord = recordsForYear.get(j); 
                    if (sfRecord.getFrisked())
                    {
                        friskedNum++;
                    }
                    if (sfRecord.getArrested())
                    {
                        arrestedNum++;
                    }
                    recordNumber++;
                }
                break;
            }

        }
        double friskedPercent = friskedNum/recordNumber * 100;
        double arrestedPercent = arrestedNum/recordNumber * 100;
        double[] output = {friskedPercent, arrestedPercent};


        return output; // update the return value
    }

    /**
     * This method keeps track of the fraction of Black females, Black males,
     * White females and White males that were stopped for any reason.
     * Drawing out the exact table helps visualize the gender bias.
     * 
     * @param year we are only interested in the records of year.
     * @return a 2D array of percent of number of White and Black females
     *         versus the number of White and Black males.
     */
    public double[][] genderBias ( int year ) {

        double blackPeopleNum = 0; 
        double whitePeopleNum = 0;
        double blackMenNum = 0;
        double whiteMenNum = 0; 
        double blackWomenNum = 0;
        double whiteWomenNum = 0;

        for (int i = 0; i < database.size(); i++)
        {
            SFYear yearRecord = database.get(i);

            if (yearRecord.getcurrentYear() == year)
            {
                ArrayList <SFRecord> recordsForYear = yearRecord.getRecordsForYear();
                for (int j = 0; j < recordsForYear.size(); j++)
                {
                    SFRecord sfRecord = recordsForYear.get(j); 
                    if ("B".equals(sfRecord.getRace()))
                    {
                        blackPeopleNum++;
                        if ("F".equals(sfRecord.getGender()))
                        {
                            blackWomenNum++;
                        }
                        if ("M".equals(sfRecord.getGender()))
                        {
                            blackMenNum++;
                        }
                    }
                    if ("W".equals(sfRecord.getRace()))
                    {
                        whitePeopleNum++;
                        if ("F".equals(sfRecord.getGender()))
                        {
                            whiteWomenNum++;
                        }
                        if ("M".equals(sfRecord.getGender()))
                        {
                            whiteMenNum++;
                        }
                    }

                }
                break;
            }

        }
        double blackFemalePercent = (blackWomenNum/blackPeopleNum) * 0.5 * 100; 
        double whiteFemalePercent = (whiteWomenNum/whitePeopleNum) * 0.5 * 100; 
        double blackMalePercent = (blackMenNum/blackPeopleNum) * 0.5 * 100; 
        double whiteMalePercent = (whiteMenNum/whitePeopleNum) * 0.5 * 100; 

        double[][] percentages = new double[2][3]; 
        percentages[0][0] = blackFemalePercent; 
        percentages[0][1] = whiteFemalePercent; 
        percentages[0][2] = blackFemalePercent + whiteFemalePercent; 
        percentages[1][0] = blackMalePercent; 
        percentages[1][1] = whiteMalePercent; 
        percentages[1][2] = blackMalePercent + whiteMalePercent; 

        return percentages; // update the return value
    }

    /**
     * This method checks to see if there has been increase or decrease 
     * in a certain crime from year 1 to year 2.
     * 
     * Expect year1 to preceed year2 or be equal.
     * 
     * @param crimeDescription
     * @param year1 first year to compare.
     * @param year2 second year to compare.
     * @return 
     */

    public double crimeIncrease(String crimeDescription, int year1, int year2) {
    
        if (year1 >= year2) 
        {
            return 0.0;
        }

        double totalNumCrimesYear1 = 0;
        double totalNumCrimesYear2 = 0;
        double crime1Num = 0;
        double crime2Num = 0;

        // Process records for year1
        for (int i = 0; i < database.size(); i++) 
        {
            SFYear yearRecord = database.get(i);
            if (yearRecord.getcurrentYear() == year1) 
            {
                ArrayList<SFRecord> recordsForYear = yearRecord.getRecordsForYear();
                for (int j = 0; j < recordsForYear.size(); j++) 
                {
                    SFRecord sfRecord = recordsForYear.get(j);
                    if (sfRecord.getDescription().indexOf(crimeDescription) != -1) 
                    {
                        crime1Num++;
                    }
                    totalNumCrimesYear1++;
                }
                break; // Exit the loop once year1 is processed
            }
        }

        // Process records for year2
        for (int i = 0; i < database.size(); i++) 
        {
            SFYear yearRecord = database.get(i);
            if (yearRecord.getcurrentYear() == year2) 
            {
                ArrayList<SFRecord> recordsForYear = yearRecord.getRecordsForYear();
                for (int j = 0; j < recordsForYear.size(); j++) 
                {
                    SFRecord sfRecord = recordsForYear.get(j);
                    if (sfRecord.getDescription().indexOf(crimeDescription) != -1) 
                    {
                        crime2Num++;
                    }
                    totalNumCrimesYear2++;
                }
                break; // Exit the loop once year2 is processed
            }
        }

        double percentageChange = 0.0;
        double crime2Percent = (crime2Num/totalNumCrimesYear2) * 100;
        double crime1Percent = (crime1Num/totalNumCrimesYear1) * 100;
        percentageChange = crime2Percent-crime1Percent;
 
    return percentageChange; // update the return value
}

    /**
     * This method outputs the NYC borough where the most amount of stops 
     * occurred in a given year. This method will mainly analyze the five 
     * following boroughs in New York City: Brooklyn, Manhattan, Bronx, 
     * Queens, and Staten Island.
     * 
     * @param year we are only interested in the records of year.
     * @return the borough with the greatest number of stops
     */
    public String mostCommonBorough ( int year ) {

        int BrooklynCounter = 0;
        int ManhattanCounter = 0;
        int BronxCounter = 0; 
        int QueensCounter = 0; 
        int Staten_IslandCounter = 0; 

        for (int i = 0; i < database.size(); i++) 
        {
            SFYear yearRecord = database.get(i);
            if (yearRecord.getcurrentYear() == year) 
            {
                ArrayList<SFRecord> recordsForYear = yearRecord.getRecordsForYear();
                for (int j = 0; j < recordsForYear.size(); j++) 
                {
                    SFRecord sfRecord = recordsForYear.get(j);
                    if (sfRecord.getLocation().equals("BROOKLYN")) 
                    {
                        BrooklynCounter++;
                    }
                    if (sfRecord.getLocation().equals("MANHATTAN")) 
                    {
                        ManhattanCounter++;
                    }
                    if (sfRecord.getLocation().equals("BRONX")) 
                    {
                        BronxCounter++;
                    }
                    if (sfRecord.getLocation().equals("QUEENS")) 
                    {
                        QueensCounter++;
                    }
                    if (sfRecord.getLocation().equals("STATEN ISLAND")) 
                    {
                        Staten_IslandCounter++;
                    }
                }
                break; // Exit the loop once year1 is processed
            }
        }
        int max = 0;
        String location = null;
        if (BrooklynCounter > max)
        {
            max = BrooklynCounter;
            location = "Brooklyn";
        }
        if (ManhattanCounter > max)
        {
            max = ManhattanCounter;
            location = "Manhattan";
        }
        if (BronxCounter > max)
        {
            max = BronxCounter;
            location = "Bronx";
        }
        if (QueensCounter > max)
        {
            max = QueensCounter;
            location = "Queens";
        }
        if (Staten_IslandCounter > max)
        {
            max = Staten_IslandCounter;
            location = "Staten Island";
        }


        return location; // update the return value
    }

}
