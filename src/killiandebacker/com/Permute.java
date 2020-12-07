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
}
