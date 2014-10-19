package SCMAgent;

import java.util.*;
import java.util.Map.Entry;

public class ToolsClass {
	
	
	 public static Map<SCMElements, Integer> sortPriorityMapByValue(Map<SCMElements, Integer> map){  
	        if (map == null || map.isEmpty()) {  
	            return null;  
	        }  
	        Map<SCMElements, Integer> sortedMap = new LinkedHashMap<SCMElements, Integer>();  
	        List<Map.Entry<SCMElements, Integer>> entryList = new ArrayList<Map.Entry<SCMElements, Integer>>(map.entrySet());  
	        Collections.sort(entryList, new MapValueComparator());  
	        Iterator<Map.Entry<SCMElements, Integer>> iter = entryList.iterator();  
	        Map.Entry<SCMElements, Integer> tmpEntry = null;  
	        while (iter.hasNext()) {  
	            tmpEntry = iter.next();  
	            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());  
	        }  
	        return sortedMap;  
	    }  
	 
	   public static double getrandom(int min, int max){
		    Random random = new Random();
		    double x=random.nextDouble()*(max-min)+min;
		    return x;
		}
	 
}  


	  
	//�Ƚ�����  
	class MapValueComparator implements Comparator<Map.Entry<SCMElements, Integer>> {  
	    public int compare(Entry<SCMElements, Integer> me1, Entry<SCMElements, Integer> me2) {  
	        return me1.getValue().compareTo(me2.getValue());  
	    }

	
		
	}
	
	

