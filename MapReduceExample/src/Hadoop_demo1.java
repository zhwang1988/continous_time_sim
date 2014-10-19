import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;


public class Hadoop_demo1 {
  public static void main(String[] args) throws Exception{
	 String uri=args[0];
	 Configuration conf=new Configuration();
	 FileSystem fs=FileSystem.get(URI.create(uri), conf);
	 FSDataInputStream in=null;
	 try{
		 in=fs.open(new Path(uri));
		 IOUtils.copyBytes(in, System.out, 4096, false);
		 in.seek(2);
		 IOUtils.copyBytes(in, System.out, 4096, false);
	 }finally{
		 IOUtils.closeStream(in);
	 }
  }
	
	
}
