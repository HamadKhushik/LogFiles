import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.HashMap;

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

    public HashMap<String, Integer> countVisitsPerIP() {
        //This method returns a HashMap<String, Integer> that maps an IP address to the number of times that
        // IP address appears in records

        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        //has one parameter, a HashMap<String, Integer> that maps an IP address to the number of
        // times that IP address appears in the web log file. This method returns the maximum
        // number of visits to this website by any single IP address.

        int maxNumber = 0;
        for (int currNumber : counts.values()) {
            if (currNumber > maxNumber) {
                maxNumber = currNumber;
            }
        }
        return maxNumber;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {

        // has one parameter, a HashMap<String, Integer> that maps an IP address to the number of times
        // that IP address appears in the web log file. This method returns an ArrayList of Strings of
        // IP addresses that all have the maximum number of visits to this website

        ArrayList<String> mostVisits = new ArrayList<String>();
        int maxVisits = mostNumberVisitsByIP(counts);
        for (String currIP : counts.keySet()) {
            if (maxVisits == counts.get(currIP)) {
                mostVisits.add(currIP);
            }
        }
        return mostVisits;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        // This method returns a HashMap<String, ArrayList<String>> that uses records and maps days
        // from web logs to an ArrayList of IP addresses that occurred on that day
        // (including repeated IP addresses)

        HashMap<String, ArrayList<String>> iPDays = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records) {
            ArrayList<String> iPsInDay = new ArrayList<String>();
            String day = le.getAccessTime().toString().substring(4, 10);
            if (!iPDays.containsKey(day)) {
                iPsInDay.add(le.getIpAddress());
                iPDays.put(day, iPsInDay);
            } else {
                iPsInDay = iPDays.get(day);
                iPsInDay.add(le.getIpAddress());
                iPDays.put(day, iPsInDay);
            }
        }
        return iPDays;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPDays) {
        //has one parameter that is a HashMap<String, ArrayList<String>> that uses records and maps
        // days from web logs to an ArrayList of IP addresses that occurred on that day.
        // This method returns the day that has the most IP address visits

        String dayWithMaxVisits = "";
        int max = 0;
        for (String day : iPDays.keySet()) {
            ArrayList currDay = iPDays.get(day);
            if (currDay.size() > max) {
                max = currDay.size();
                dayWithMaxVisits = day;
            }
        }
        return dayWithMaxVisits;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPDays, String day) {
        // has two parameters—the first one is a HashMap<String, ArrayList<String>> that uses records and
        // maps days from web logs to an ArrayList of IP addresses that occurred on that day, and the second
        // parameter is a String representing a day in the format “MMM DD” described above. This method
        // returns an ArrayList<String> of IP addresses that had the most accesses on the given day

        HashMap<String, Integer> iPFreq = new HashMap<String, Integer>();
        ArrayList<String> iPsInDay = iPDays.get(day);
        for (String currIP : iPsInDay) {
            if (!iPFreq.containsKey(currIP)) {
                iPFreq.put(currIP, 1);
            } else {
                iPFreq.put(currIP, iPFreq.get(currIP) + 1);
            }
        }
        return iPsMostVisits(iPFreq);
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }


}
