import java.util.ArrayList;
import java.util.Date;

public class Tester {
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }

    public void testUniqueIPs() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
        int IPs = la.countUniqueIPs();
        System.out.println("The number of unique IP addresses is " + IPs);

    }

    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        //la.printAll();
        la.printAllHigherThanNum(400);
    }

    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        ArrayList<String> uniqueIPAddrs = la.uniqueIPVisitsOnDay("Mar 17");
        for (String IPs : uniqueIPAddrs) {
            System.out.println(IPs);
        }
        System.out.println(uniqueIPAddrs.size());
    }

    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int inRange = la.countUniqueIPsInRange(200, 299);
        System.out.println(inRange);
    }

    public static void main(String[] args) {
        Tester t = new Tester();
        //t.testLogAnalyzer();
        //t.testUniqueIPs();
        //t.testPrintAllHigherThanNum();
        //t.testUniqueIPVisitsOnDay();
        t.testCountUniqueIPsInRange();
    }
}
