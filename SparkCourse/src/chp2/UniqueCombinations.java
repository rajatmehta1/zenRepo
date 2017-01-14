package chp2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.HashedMap;

public class UniqueCombinations {

	private static List<String> fullSet = new ArrayList<>();
	private static Map<String,String> globalKeys = new LinkedHashMap<>();
	
	public static void main(String[] args) {
		String row = "a,b,c";
		List<String> previousPattern = fetchSingleSet(row); 
			fullSet.addAll(previousPattern);

		int loopRunLength = previousPattern.size() - 2;
		for(int i = 0; i < loopRunLength ; i++) {
			previousPattern = fetchMorePatternsFromPrevPatterns(previousPattern);
		}
		
		for (String key : globalKeys.keySet()) {
			System.out.println("Key --> " + key);
		}
	}
	
	private static List<String> fetchMorePatternsFromPrevPatterns(List<String> previousPattern) {
		
		Map<String,String> keys = new LinkedHashMap<>();

		for(int i = 0 ; i < previousPattern.size() ; i++) {
			for(int j = i+1 ; j < previousPattern.size() ; j++) {
	            Set<String> combinationList = new HashSet<>();
	            	combinationList.addAll(Arrays.asList(previousPattern.get(i).split(",")));
	            	combinationList.addAll(Arrays.asList(previousPattern.get(j).split(",")));
	            String[] combinationArr = combinationList.toArray(new String [combinationList.size()]);
	            Arrays.sort(combinationArr);
	            String newKey = "";
	            for (String strVal : combinationArr) {
	            	newKey = ("".equals(newKey)) ? strVal : newKey + "," + strVal;
				}
	            keys.put(newKey, "PRESENT");
			}
		}
		globalKeys.putAll(keys);
		Set<String> keySet = keys.keySet();
		for (String k : keySet) {
			fullSet.add(k);
		}
		
		return new ArrayList<>(keySet);
		
	}

	public static List<String> fetchSingleSet(String pattern) {
		List<String> result = new ArrayList<>();
		String[] arr = pattern.split(",");
        for(int i=0; i<arr.length; i++)
        {
        	globalKeys.put(arr[i], "PRESENT");
        	result.add(arr[i]);
        }
        return result;
	}

}
