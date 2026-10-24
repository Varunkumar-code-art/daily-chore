class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int count = 0;

        int aliceStart = convert(arriveAlice, days);
        int aliceEnd = convert(leaveAlice, days);
        int bobStart = convert(arriveBob, days);
        int bobEnd = convert(leaveBob, days);

        for (int day = 1; day <= 365; day++) {
            if (day >= aliceStart && day <= aliceEnd &&
                day >= bobStart && day <= bobEnd) {
                count++;
            }
        }

        return count;
    }

    public int convert(String date, int[] days) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        for (int i = 0; i < month - 1; i++) {
            day += days[i];
        }

        return day;
    }
}