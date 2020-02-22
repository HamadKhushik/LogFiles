import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Tester {

    private LogAnalyzer la;

    public Tester() {
        la = new LogAnalyzer();
    }

    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        //LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }

    public void testUniqueIPs() {
        //LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
        int IPs = la.countUniqueIPs();
        System.out.println("The number of unique IP addresses is " + IPs);

    }

    public void testPrintAllHigherThanNum() {
        //LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        //la.printAll();
        la.printAllHigherThanNum(400);
    }

    public void testUniqueIPVisitsOnDay() {
        //LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        ArrayList<String> uniqueIPAddrs = la.uniqueIPVisitsOnDay("Mar 17");
        for (String IPs : uniqueIPAddrs) {
            System.out.println(IPs);
        }
        System.out.println(uniqueIPAddrs.size());
    }

    public void testCountUniqueIPsInRange() {
        //LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int inRange = la.countUniqueIPsInRange(200, 299);
        System.out.println(inRange);
    }

    private void testCountVisitsPerIP() {
        la.readFile("weblog1_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
    }

    private void testMostNumberVisitsByIP() {
        la.readFile("weblog1_log");
        int maxNumber = la.mostNumberVisitsByIP(la.countVisitsPerIP());
        System.out.println("The maximum number of times any IP Visited site is " + maxNumber);
    }

    private void testIPsMostVisits() {
        la.readFile("weblog1_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        ArrayList<String> mostVisits = la.iPsMostVisits(counts);
        System.out.println("The IPs visited maximum number of times are " + mostVisits);
    }

    private void testIPsForDays() {
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> iPDays = la.iPsForDays();
        System.out.println("The Website was visited by following IP Addresses on corresponding days \n" + iPDays);
    }

    private void testDayWithMostIPVisits() {
        la.readFile("weblog1_log");
        HashMap<String, ArrayList<String>> iPDays = la.iPsForDays();
        String dayWithMostVisits = la.dayWithMostIPVisits(iPDays);
        System.out.println("The day with Most Visits in the Log is: \t" + dayWithMostVisits);
    }

    private void testIPsWithMostVisitsOnDay() {
        la.readFile("weblog1_log");
        HashMap<String, ArrayList<String>> iPDays = la.iPsForDays();
        String str = "Mar 17";
        ArrayList<String> iPMostVisits = la.iPsWithMostVisitsOnDay(iPDays, str);
        System.out.println("The most frequent IP Addresses on " + str + " are: " + iPMostVisits);
    }


    public static void main(String[] args) {
        Tester t = new Tester();
        //t.testLogAnalyzer();
        //t.testUniqueIPs();
        //t.testPrintAllHigherThanNum();
        //t.testUniqueIPVisitsOnDay();
        //t.testCountUniqueIPsInRange();
        //t.testCountVisitsPerIP();
        //t.testMostNumberVisitsByIP();
        //t.testIPsMostVisits();
        //t.testIPsForDays();
        t.testDayWithMostIPVisits();
        //t.testIPsWithMostVisitsOnDay();
    }
}
