import java.io.FileNotFoundException;   
import java.io.IOException;   
import java.net.URISyntaxException;   
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;   
import org.apache.hadoop.io.IOUtils;   
import org.apache.hadoop.security.AccessControlException;   
  
public class Test { 
	 static Configuration conf = new Configuration();
	 //static FileSystem hdfs;
	
	
    public static void main(String[] args) throws AccessControlException,   
            FileNotFoundException, IOException, URISyntaxException {   
    
    	FileSystem fileSystem=FileSystem.get(conf);
    	Path path=new Path("/tmp/hadoop-zhwang1988/persons.rtf");
    	//if(!fileSystem.exists(path)){
    	//	System.out.println("File does not exists");
    	//}
    	
    	
       // FileContext fc = FileContext   
                //.getFileContext();//如果运行在hadoop location中，不需要配置URI，否则需要给一个URI   
        //FSDataInputStream fsInput = fileSystem.open(path); 
        
       
      // IOUtils.copyBytes(fsInput, System.out, 4090, false);   
       //fsInput.seek(0);   
        //IOUtils.copyBytes(fsInput, System.out, 4090, false);   
    }   
}  