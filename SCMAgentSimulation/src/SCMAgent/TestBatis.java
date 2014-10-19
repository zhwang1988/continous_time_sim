package SCMAgent;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class TestBatis {
	public static void main(String[] args) throws IOException{
		String resource="Configuration.xml";
		Reader reader=Resources.getResourceAsReader(resource);
		SqlSessionFactory ssf=new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=ssf.openSession();
		
		try{
			List<Object> ref=session.selectList("selectRefinerys");
			System.out.println(ref.get(0));
			//System.out.println(ref.getRefineryId());
			System.out.println("----------------");
			RefineryBatis refadd=new RefineryBatis(2,"refinery2");
			refadd.setRefineryCap(4000);
			refadd.setRefineryId(2);
			refadd.setRefineryName("refinery2");
			refadd.setRefineryType(1);
			System.out.println(refadd);
			session.insert("addRefinery",refadd);
			//��Ҫ��commit ��Ҫ����
			session.commit();
			
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
	}
}
