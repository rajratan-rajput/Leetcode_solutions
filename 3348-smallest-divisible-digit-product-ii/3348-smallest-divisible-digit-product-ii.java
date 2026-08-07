class Solution {

    public String smallestNumber(String num, long t) {
        int n = num.length();

        // Check if t has prime factors other than 2,3,5,7
        long temp = t;
        int[] primes = {2, 3, 5, 7};

        for (int p : primes) {
            while (temp % p == 0) {
                temp /= p;
            }
        }

        if (temp != 1) {
            return "-1";
        }

        // remainingFactor[i] = remaining factor after using first i digits
        long[] remainingFactor = new long[n + 1];
        remainingFactor[0] = t;

        for (int i = 0; i < n; i++) {
            int digit = num.charAt(i) - '0';

            if (digit == 0) {
                break;
            }

            remainingFactor[i + 1] =
                    remainingFactor[i] / gcd(remainingFactor[i], digit);
        }

        if (remainingFactor[n] == 1) {
            return num;
        }

        int zeroPos = num.indexOf('0');
        int zeroIdx = (zeroPos == -1) ? n - 1 : zeroPos;

        for (int i = zeroIdx; i >= 0; i--) {

            long required = remainingFactor[i];
            int freeSlots = n - 1 - i;

            for (int digit = (num.charAt(i) - '0') + 1; digit <= 9; digit++) {

                long furtherRequired =
                        required / gcd(required, digit);

                String suffix = freeSlotsFiller(furtherRequired, freeSlots);

                if (suffix.length() == freeSlots) {
                    return num.substring(0, i)
                            + (char) ('0' + digit)
                            + suffix;
                }
            }
        }

        return freeSlotsFiller(t, n + 1);
    }

    private String freeSlotsFiller(long required, int length) {

        StringBuilder sb = new StringBuilder();

        for (int digit = 9; digit >= 2; digit--) {

            while (required % digit == 0) {
                sb.append((char) ('0' + digit));
                required /= digit;
            }
        }

        while (sb.length() < length) {
            sb.append('1');
        }

        sb.reverse();
        return sb.toString();
    }

    private long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}