import edu.duke.FileResource;

import java.util.ArrayList;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()) {
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
    }

    public int countUniqueIPs() {
        //This method should return an integer representing the number of unique IP addresses.
        // It should also assume that the instance variable records already has its ArrayList
        // of Strings read in from a file, and should access records in computing this value.

        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            if (!uniqueIPs.contains(le.getIpAddress())) {
                uniqueIPs.add(le.getIpAddress());
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num) {
        //This method should examine all the web log entries in records and print those LogEntrys
        // that have a status code greater than num.

        for (LogEntry le : records) {
            if (num < le.getStatusCode()) {
                System.out.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        //This method accesses the web logs in records and returns an ArrayList of Strings of unique IP
        // addresses that had access on the given day.

        ArrayList<String> uniqueIPAddrs = new ArrayList<String>();
        for (LogEntry le : records) {
            String str = le.getAccessTime().toString();
            str = str.substring(4, 10);
            if ((str.equals(someday)) && (!uniqueIPAddrs.contains(le.getIpAddress()))) {
                uniqueIPAddrs.add(le.getIpAddress());
            }
        }
        return uniqueIPAddrs;
    }

    public int countUniqueIPsInRange(int low, int high) {
        // This method returns the number of unique IP addresses in records that have a status code in
        // the range from low to high, inclusive.

        int inRange = 0;
        ArrayList<String> uniqueIP = new ArrayList<String>();
        for (LogEntry le : records) {
            if (le.getStatusCode() >= low && le.getStatusCode() <= high && !uniqueIP.contains(le.getIpAddress())) {
                inRange += 1;
                uniqueIP.add(le.getIpAddress());
            }
        }
        return inRange;
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }


}
