package SCMAgent;

import java.util.*;

public class PublicStaticData {
	public static Integer[] refineryMaterialType={};//从数据库读入,用数值表示对应的物料，本质上还是用String为好，但是判断就要用
	public static Integer[] petrochemicalMaterialType={}; //把当前的数据存入到其中
	
	public static List<Integer> refineryMaterial=Arrays.asList(refineryMaterialType);
	public static List<Integer> petrochemicalMaterial=Arrays.asList(refineryMaterialType);
	  
}
