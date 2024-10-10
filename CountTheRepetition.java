import java.util.Scanner;

class Solution {

    // Function to calculate the maximum repetitions of str2 in str1
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int l1 = s1.length();  // Length of s1
        int l2 = s2.length();  // Length of s2
        int[] next = new int[l2 + 1];  // To track the next index of s2 after each traversal of s1
        int[] count = new int[l2 + 1];  // To track how many times s2 can be formed

        int cnt = 0;  // Count of how many times s2 has been formed
        int p = 0;    // Pointer to keep track of the current index in s2
        
        // Iterate over the length of n1 (how many times s1 is concatenated)
        for (int i = 0; i < n1; i++) {
            // Loop over characters in s1
            for (int j = 0; j < l1; j++) {
                if (s1.charAt(j) == s2.charAt(p)) {
                    p++;  // If characters match, move to the next character in s2
                }
                if (p == l2) {  // If all characters of s2 are matched
                    cnt++;  // Increment the count of how many times s2 has been formed
                    p = 0;  // Reset the pointer for the next traversal
                }
            }
            
            count[i] = cnt;  // Store the current count of s2 formations
            next[i] = p;  // Store the next index in s2 after the current traversal
            
            // Check if a repeating pattern is found
            for (int j = 0; j < i; j++) {
                if (next[j] == p) {  // If the next index in s2 matches, we have a repeating pattern
                    int prev_count = count[j];  // Number of s2 formations up to the start of the pattern
                    int pattern_count = (count[i] - count[j]) * ((n1 - j - 1) / (i - j));  // Total s2 formations within the pattern
                    int remain_count = count[j + (n1 - j - 1) % (i - j)] - count[j];  // Remaining s2 formations after the pattern
                    return (prev_count + pattern_count + remain_count) / n2;  // Total s2 formations divided by n2
                }
            }
        }
        
        // Return the total count of s2 formations divided by n2
        return count[n1 - 1] / n2;
    }

    public static void main(String[] args) {
        // Create an instance of the Solution class
        Solution solution = new Solution();

        // Create a Scanner object for input
        Scanner scanner = new Scanner(System.in);
        
        // Input the first string s1 and the integer n1
        System.out.print("Enter the first string (s1): ");
        String s1 = scanner.nextLine();
        System.out.print("Enter the number of repetitions (n1): ");
        int n1 = scanner.nextInt();
        
        // Input the second string s2 and the integer n2
        System.out.print("Enter the second string (s2): ");
        scanner.nextLine();  // Consume newline character
        String s2 = scanner.nextLine();
        System.out.print("Enter the number of repetitions (n2): ");
        int n2 = scanner.nextInt();
        
        // Call the getMaxRepetitions function and store the result
        int result = solution.getMaxRepetitions(s1, n1, s2, n2);
        
        // Output the result
        System.out.println("Maximum repetitions of str2 in str1: " + result);
        
        // Close the scanner
        scanner.close();
    }
}
