import java.util.*;

/*
https://leetcode.com/problems/design-log-storage-system/
You are given several logs that each log contains a unique id and timestamp. Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.

Design a log storage system to implement the following functions:

void Put(int id, string timestamp): Given a log's unique id and timestamp, store the log in your storage system.


int[] Retrieve(String start, String end, String granularity): Return the id of logs whose timestamps are within the range from start to end. Start and end all have the same format as timestamp. However, granularity means the time level for consideration. For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day", it means that we need to find the logs within the range from Jan. 1st 2017 to Jan. 2nd 2017.

Example 1:

put(1, "2017:01:01:23:59:59");
put(2, "2017:01:01:22:59:59");
put(3, "2016:01:01:00:00:00");
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.
Note:

There will be at most 300 operations of Put or Retrieve.
Year ranges from [2000,2017]. Hour ranges from [00,23].
Output for Retrieve has no order required.
 */
public class Q0635_DesignLogStorageSystem {
/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */

    //https://leetcode.com/problems/design-log-storage-system/discuss/105008/Concise-Java-Solution
    public static class LogSystem {

        List<String[]> timestamps = new LinkedList<>();
        List<String> units = Arrays.asList("Year", "Month", "Day", "Hour", "Minute", "Second");
        int[] indices = new int[]{4,7,10,13,16,19};

        public void put(int id, String timestamp) { timestamps.add(new String[]{Integer.toString(id), timestamp}); }

        public List<Integer> retrieve(String s, String e, String gra) {
            List<Integer> res = new LinkedList<>();
            int idx = indices[units.indexOf(gra)];
            for (String[] timestamp : timestamps) {
                if (timestamp[1].substring(0, idx).compareTo(s.substring(0, idx)) >= 0 &&
                        timestamp[1].substring(0, idx).compareTo(e.substring(0, idx)) <= 0)
                    res.add(Integer.parseInt(timestamp[0]));
            }
            return res;
        }
    }

    public static class LogSystemDavid {
        TreeMap <String, Integer> map;

        public LogSystemDavid() {
            map = new TreeMap<> ();
        }

        // "2017:01:01:23:59:59"
        // "0123456789012345678"
        List<String> units = Arrays.asList("Year", "Month", "Day", "Hour", "Minute", "Second");
        int[] endIndices = new int[] {4,7,10,13,16,19};
        int[] startIndices = new int[] {0,5,8,11,14,17};
        String zeroTime = "0000:00:00:00:00:00";

        public void put(int id, String timestamp) { map.put(timestamp, id); }  //new String[]{Integer.toString(id), timestamp}); }

        public List<Integer> retrieve(String s, String e, String gra) {
            List<Integer> res = new LinkedList<>();
            int startIdx = startIndices[units.indexOf(gra)];
            int endIdx = endIndices[units.indexOf(gra)];

            String startTimeInclusive = s.substring(0, endIdx) + zeroTime.substring(endIdx);

            int endGraValue = Integer.parseInt(e.substring(startIdx, endIdx));
            // Year is always starting from 2000, so we are only dealing with month, day, hour, minute, and second, i.e. two digits here
            // even though when the hour is 23, it adds to 24, it's an incorrect representative of time, but the comparison is still correct for map.subMap comparison
            String endGraValueString = (endGraValue + 1 < 10) ? ("0" + (endGraValue + 1)) : "" + (endGraValue + 1);
            String endTimeExclusive = e.substring(0, startIdx) + endGraValueString + zeroTime.substring(endIdx);

            for (Map.Entry<String, Integer> entry :  map.subMap(startTimeInclusive, endTimeExclusive).entrySet()) {
                res.add(entry.getValue());
            }
            return res;
        }
    }

    // https://leetcode.com/problems/design-log-storage-system/discuss/105006/Java-range-query-using-TreeMap.subMap()
    public static class LogSystemBest {
        private String min, max;
        private HashMap<String, Integer> map;
        private TreeMap<String, LinkedList<Integer>> logs;
        public LogSystemBest() {
            min = "2000:01:01:00:00:00";
            max = "2017:12:31:23:59:59";
            map = new HashMap<>();
            map.put("Year", 4);
            map.put("Month", 7);
            map.put("Day", 10);
            map.put("Hour", 13);
            map.put("Minute", 16);
            map.put("Second", 19);
            logs = new TreeMap<>();
        }

        public void put(int id, String timestamp) {
            if(!logs.containsKey(timestamp)) logs.put(timestamp, new LinkedList<>());
            logs.get(timestamp).add(id);
        }

        public List<Integer> retrieve(String s, String e, String gra) {
            int index = map.get(gra);
            String start = s.substring(0, index)+min.substring(index), end = e.substring(0, index)+max.substring(index);
            NavigableMap<String, LinkedList<Integer>> sub = logs.subMap(start, true, end, true);
            LinkedList<Integer> ans = new LinkedList<>();
            for (Map.Entry<String, LinkedList<Integer>> entry : sub.entrySet()) {
                ans.addAll(entry.getValue());
            }
            return ans;
        }
    }

    // https://leetcode.com/problems/design-log-storage-system/solution/
    public static class LogSystemOfficialSolution1 {
        ArrayList< long[] > list;
        public LogSystemOfficialSolution1() {
            list = new ArrayList < long[] > ();
        }

        public void put(int id, String timestamp) {
            int[] st = Arrays.stream(timestamp.split(":")).mapToInt(Integer::parseInt).toArray();
            list.add(new long[] {convert(st), id});
        }
        public long convert(int[] st) {
            st[1] = st[1] - (st[1] == 0 ? 0 : 1);
            st[2] = st[2] - (st[2] == 0 ? 0 : 1);
            return (st[0] - 1999L) * (31 * 12) * 24 * 60 * 60 + st[1] * 31 * 24 * 60 * 60 + st[2] * 24 * 60 * 60 + st[3] * 60 * 60 + st[4] * 60 + st[5];
        }
        public List < Integer > retrieve(String s, String e, String gra) {
            ArrayList < Integer > res = new ArrayList();
            long start = granularity(s, gra, false);
            long end = granularity(e, gra, true);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i)[0] >= start && list.get(i)[0] < end)
                    res.add((int) list.get(i)[1]);
            }
            return res;
        }

        public long granularity(String s, String gra, boolean end) {
            HashMap< String, Integer > h = new HashMap();
            h.put("Year", 0);
            h.put("Month", 1);
            h.put("Day", 2);
            h.put("Hour", 3);
            h.put("Minute", 4);
            h.put("Second", 5);
            String[] res = new String[] {"1999", "00", "00", "00", "00", "00"};
            String[] st = s.split(":");
            for (int i = 0; i <= h.get(gra); i++) {
                res[i] = st[i];
            }
            int[] t = Arrays.stream(res).mapToInt(Integer::parseInt).toArray();
            if (end)
                t[h.get(gra)]++;
            return convert(t);
        }
    }

    public static class LogSystemOfficialSolution2 {
        TreeMap <Long, Integer> map;

        public LogSystemOfficialSolution2() {
            map = new TreeMap < Long, Integer > ();
        }

        public void put(int id, String timestamp) {
            int[] st = Arrays.stream(timestamp.split(":")).mapToInt(Integer::parseInt).toArray();
            map.put(convert(st), id);
        }
        public long convert(int[] st) {
            st[1] = st[1] - (st[1] == 0 ? 0 : 1);
            st[2] = st[2] - (st[2] == 0 ? 0 : 1);
            return (st[0] - 1999L) * (31 * 12) * 24 * 60 * 60 + st[1] * 31 * 24 * 60 * 60 + st[2] * 24 * 60 * 60 + st[3] * 60 * 60 + st[4] * 60 + st[5];
        }
        public List < Integer > retrieve(String s, String e, String gra) {
            ArrayList < Integer > res = new ArrayList();
            long start = granularity(s, gra, false);
            long end = granularity(e, gra, true);
            for (long key: map.tailMap(start).keySet()) {
                if (key >= start && key < end)
                    res.add(map.get(key));
            }
            return res;
        }

        public long granularity(String s, String gra, boolean end) {
            HashMap < String, Integer > h = new HashMap();
            h.put("Year", 0);
            h.put("Month", 1);
            h.put("Day", 2);
            h.put("Hour", 3);
            h.put("Minute", 4);
            h.put("Second", 5);
            String[] res = new String[] {"1999", "00", "00", "00", "00", "00"};
            String[] st = s.split(":");
            for (int i = 0; i <= h.get(gra); i++) {
                res[i] = st[i];
            }
            int[] t = Arrays.stream(res).mapToInt(Integer::parseInt).toArray();
            if (end)
                t[h.get(gra)]++;
            return convert(t);
        }
    }


    public static void main(String[] args) {
        LogSystem log = new LogSystem();
        log.put(1, "2017:01:01:23:59:59");
        log.put(2, "2017:01:01:22:59:59");
        log.put(3, "2016:01:01:00:00:00");
        List<Integer> results1 = log.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
        List<Integer> results2 = log.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.


        LogSystemOfficialSolution1 log1 = new LogSystemOfficialSolution1();
        log1.put(1, "2017:01:01:23:59:59");
        log1.put(2, "2017:01:01:22:59:59");
        log1.put(3, "2016:01:01:00:00:00");
        List<Integer> resultsOs1_1 = log1.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
        List<Integer> resultsOs1_2 = log1.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.


        LogSystemOfficialSolution2 log2 = new LogSystemOfficialSolution2();
        log2.put(1, "2017:01:01:23:59:59");
        log2.put(2, "2017:01:01:22:59:59");
        log2.put(3, "2016:01:01:00:00:00");
        List<Integer> resultsOs2_1 = log2.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
        List<Integer> resultsOs2_2 = log2.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.

        LogSystemDavid log3 = new LogSystemDavid();
        log3.put(1, "2017:01:01:23:59:59");
        log3.put(2, "2017:01:01:22:59:59");
        log3.put(3, "2016:01:01:00:00:00");
        List<Integer> resultsDavid_1 = log3.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
        List<Integer> resultsDavid_2 = log3.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.

        LogSystemBest log4 = new LogSystemBest();
        log4.put(1, "2017:01:01:23:59:59");
        log4.put(2, "2017:01:01:22:59:59");
        log4.put(3, "2016:01:01:00:00:00");
        List<Integer> resultsBest_1 = log4.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
        List<Integer> resultsBest_2 = log4.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.

    }
}
