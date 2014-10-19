
import java.io.File;
import java.io.IOException;  
  


import org.apache.hadoop.conf.Configuration;  
import org.apache.hadoop.fs.Path;  
import org.apache.hadoop.io.IntWritable;  
import org.apache.hadoop.io.LongWritable;  
import org.apache.hadoop.io.Text;  
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;  
import org.apache.hadoop.mapreduce.Mapper;  
import org.apache.hadoop.mapreduce.Reducer;  
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;  
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;  
import org.apache.hadoop.util.GenericOptionsParser;


  
  
public class MaxTemperature {  
      
    static class MaxTemperatureMapper extends Mapper<LongWritable , Text , Text , IntWritable>{  
        private static final int MISSING=9999;  
        public void map(LongWritable key , Text value , Context context) throws IOException , InterruptedException{  
              
            String line = value.toString();  
            String year = line.substring(15 , 19);   // 代表年份  
            int airTemperature ;   // 代表气温值  
            if(line.charAt(87) == '+'){  
                airTemperature = Integer.parseInt(line.substring(88, 92));  
            }else{  
                airTemperature = Integer.parseInt(line.substring(87, 92));  
            }  
            String quality=line.substring(92,93);
            if(airTemperature !=MISSING && quality.matches("[01459]"))
            context.write(new Text(year) , new IntWritable(airTemperature));  
      
        }  
          
    }  
      
    static class MapTemperatureReducer extends Reducer<Text , IntWritable, Text , IntWritable>{  
          
         public void reduce(Text key , Iterable<IntWritable> values , Context context) throws IOException , InterruptedException{  
             int maxValues = Integer.MIN_VALUE ;  
             for(IntWritable value : values){  
                 maxValues = Math.max(maxValues, value.get());  
             }               
             context.write(key, new IntWritable(maxValues));  
              
               
         }  
          
    }  
      
      
    public static void main(String[] args) throws Exception{  
          
    	   Configuration conf = new Configuration();
    	    conf.set("mapred.job.tracker", "localhost:8021");
    	    conf.set("fs.default.name", "hdfs://localhost:8020");
    	    
    	   // String[] ars = new String[]{"/user/zhwang1988/input01/sample.txt", "/user/zhwang1988/newout01"};
    	    //String[] otherArgs = new GenericOptionsParser(conf, ars).getRemainingArgs();
    	  //  if (otherArgs.length != 2) {
    	    //  System.err.println("Usage: wordcount <in> <out>");
    	     // System.exit(2);
    	   // }
    	    Job job = new Job(conf, "Max temperature");  
    	    
    	    File jarFile = EJob.createTempJar("bin");
    	    EJob.addClasspath("/Users/zhwang1988/desktop/hadoop-1.2.1/conf");
    	    ClassLoader classLoader = EJob.getClassLoader();
    	    Thread.currentThread().setContextClassLoader(classLoader);
    	    job.setJarByClass(WordCount.class);
    	    ((JobConf) job.getConfiguration()).setJar(jarFile.toString()); 
    	    
    	    job.setNumReduceTasks(2);	
    	//
    	
      job.setMapperClass(MaxTemperatureMapper.class);  
      job.setReducerClass(MapTemperatureReducer.class);   
      job.setOutputKeyClass(Text.class);  
      job.setOutputValueClass(IntWritable.class);       
      FileInputFormat.addInputPath(job, new Path("hdfs://localhost:8020/user/zhwang1988/input01/1901"));  
      FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:8020/user/zhwang1988/output02"));  
      System.exit(job.waitForCompletion(true) ? 0 : 1);  
  
          
    }  
  
}  
