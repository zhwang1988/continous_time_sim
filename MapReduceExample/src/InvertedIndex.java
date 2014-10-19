import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;


public class InvertedIndex {
	
	public static class Map extends Mapper<Object, Text, Text, Text>{
		private Text keyInfo=new Text();
		private Text valueInfo=new Text();//��Ƶ
		private FileSplit split;
		
		public void map(Object key,Text value, Context context) 
				throws IOException, InterruptedException
		{
			split=(FileSplit) context.getInputSplit();//��ȡ�ļ���Ϣ
			StringTokenizer itr=new StringTokenizer(value.toString());
			
			while(itr.hasMoreTokens())
			{
				//int splitIndex=split.getPath().toString().indexOf("file");
			
				//keyInfo.set(itr.nextToken()+""+split.getPath().toString().substring(splitIndex));
				keyInfo.set(itr.nextToken()+":"+split.getPath().toString());
				context.write(keyInfo, valueInfo);
			
			}
					
					
		}
		
	}
	
	public static class Combine extends Reducer<Text, Text, Text, Text>
	{
		public Text info=new Text();
		public void reduce(Text key, Iterable<Text> values, Context context) 
				throws IOException, InterruptedException
		{
			int sum=0;
			for(Text value:values){
				sum+=Integer.parseInt(value.toString());
			}
			
			int splitIndex=key.toString().indexOf(":");
			
			info.set(key.toString().substring(splitIndex+1)+":"+sum);
			
			key.set(key.toString().substring(0,splitIndex));
			
			context.write(key,info);
			
		}
	}
	
	public static class Reduce extends Reducer<Text,Text, Text, Text>
	{
		private Text result=new Text();
		
		public void reduce(Text key, Iterable<Text> values, Context context) 
				throws IOException, InterruptedException
		{
			String fileList=new String();
			for(Text value:values){
				fileList+=value.toString()+";";
			}
			
			result.set(fileList);
			context.write(key, result);
		}
	}
	
	public static void main(String[] args) throws Exception {
	    Configuration conf = new Configuration();
	    conf.set("mapred.job.tracker", "localhost:8021");
	    conf.set("fs.default.name", "hdfs://localhost:8020");
	    
	    String[] ars = new String[]{"index_in", "index_out"};
	    String[] otherArgs = new GenericOptionsParser(conf, ars).getRemainingArgs();
	    if (otherArgs.length != 2) {
	      System.err.println("Usage: Inverted Index <in> <out>");
	      System.exit(2);
	    }
	    Job job = new Job(conf, "Inverted Index");
	    
	    File jarFile = EJob.createTempJar("bin");
	    EJob.addClasspath("/Users/zhwang1988/desktop/hadoop-1.2.1/conf");
	    ClassLoader classLoader = EJob.getClassLoader();
	    Thread.currentThread().setContextClassLoader(classLoader);
	    job.setJarByClass(InvertedIndex.class);
	    ((JobConf) job.getConfiguration()).setJar(jarFile.toString()); 
	    
	   
	    job.setNumReduceTasks(2);
	    job.setMapperClass(Map.class);
	    job.setCombinerClass(Combine.class);
	    job.setReducerClass(Reduce.class);
	    
	    job.setMapOutputKeyClass(Text.class);
	    job.setMapOutputValueClass(Text.class);
	    
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(Text.class);
	    
	
	    FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
	    FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
	    
	    System.exit(job.waitForCompletion(true) ? 0 : 1);
	  }
   
	
	

}
