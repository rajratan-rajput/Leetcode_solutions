class Solution {
    private int digitProd(int n){
        int prod =1;
        while(n != 0){
            int digit = n % 10;
            prod = prod*digit;
            n = n /10;
        }
        return prod;
    }
    public int smallestNumber(int n, int t) {
        // if(n % t ==0) return n;
        int ans =0;
         for(int i = n; i<150;i++){
            int val = digitProd(i);
            if(val % t == 0) {
                ans = i;
                break;
            }
        }

        return ans;
    }
}