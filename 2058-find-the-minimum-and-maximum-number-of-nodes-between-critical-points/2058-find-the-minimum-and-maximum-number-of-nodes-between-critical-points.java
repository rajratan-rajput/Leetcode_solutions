class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int firstCritical = -1;
        int previousCritical = -1;

        int minDistance = Integer.MAX_VALUE;

        ListNode previous = head;
        ListNode current = head.next;

        int position = 1;

        while (current.next != null) {

            ListNode next = current.next;

            // Check if current node is a critical point
            boolean isCritical =
                (current.val > previous.val && current.val > next.val) ||
                (current.val < previous.val && current.val < next.val);

            if (isCritical) {

                // First critical point
                if (firstCritical == -1) {
                    firstCritical = position;
                }

                // If we already found a critical point
                if (previousCritical != -1) {
                    minDistance = Math.min(
                        minDistance,
                        position - previousCritical
                    );
                }

                previousCritical = position;
            }

            previous = current;
            current = current.next;
            position++;
        }

        // Fewer than two critical points
        if (firstCritical == -1 || firstCritical == previousCritical) {
            return new int[]{-1, -1};
        }

        int maxDistance = previousCritical - firstCritical;

        return new int[]{minDistance, maxDistance};
    }
}