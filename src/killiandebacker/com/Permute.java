package killiandebacker.com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Permute {
    public static long generateFactorial(int num){
		long result = 1;
		for(int i = 1; i < num + 1; i++){
			result *= i;
		}
		return result;
	}

	// This is edited code
	// original solution found here https://stackoverflow.com/questions/2920315/permutation-of-array


	// BOTH OF THESE are from the article
	// I originally thought that the first one was causing issues
	// it was not I was not calculating expected properly
	public static void permute(List<ArrayList<Integer>> perms, ArrayList<Integer> arr, int k){
		for(int i = k; i < arr.size(); i++){
			Collections.swap(arr, i, k);
			permute(perms, arr, k+1);
			Collections.swap(arr, k, i);
		}
		if (k == arr.size() - 1){
			perms.add(new ArrayList(arr));
		}
	}

	public static ArrayList<ArrayList<Integer>> permute2(int[] nums) {
		ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
		if (nums == null || nums.length == 0) {
			return results;
		}
		ArrayList<Integer> result = new ArrayList<>();
		dfs(nums, results, result);
		return results;
	}
	
	public static void dfs(int[] nums, ArrayList<ArrayList<Integer>> results, ArrayList<Integer> result) {
		if (nums.length == result.size()) {
			ArrayList<Integer> temp = new ArrayList<>(result);
			results.add(temp);
		}        
		for (int i=0; i<nums.length; i++) {
			if (!result.contains(nums[i])) {
				result.add(nums[i]);
				dfs(nums, results, result);
				result.remove(result.size() - 1);
			}
		}
	}




	// The code below is taken from pseudocode shown on in this video: https://www.youtube.com/watch?v=cY4HiiFHO1o
	// ALL comments are mine

	/**
	 * Generate all integers of size size with count bits set to 1.
	 * @param count Number of bits to be 1
	 * @param size bit size  of integer
	 * @return Array of permutations of the bits
	 */
	public static ArrayList<Integer> combinations(int count, int size) {
		ArrayList<Integer> subsets = new ArrayList<>();
		generateCombinations(0, 0, count, size, subsets);
		return subsets;
	}

	/**
	 * Helper to generate the bit sets.
	 * @param set the current bit set
	 * @param loc current location in bit set
	 * @param bitsLeft number of bits left to be set
	 * @param size size of the bit set
	 * @param size all subsets
	 * @return void subsets is passed by reference
	 */
	public static void generateCombinations(int set, int loc, int bitsLeft, int size, ArrayList<Integer> subsets) {
		// If no bits are left this is a valid bitset
		if (bitsLeft == 0){
			subsets.add(set);
		} 
		else {
			for (int i = loc; i < size; i++) {
				// Flip the current bit
				set |= 1 << i;

				// Generate all the possible bitsets given this starting position
				generateCombinations(set, i + 1, bitsLeft - 1, size, subsets);

				// Unflip the current bit (all bitsets that have this bit flipped are now generated)
				set &= ~(1 << i);
			}
		}
	}
}
