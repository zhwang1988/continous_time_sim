
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



public class PageRank {
	public static class MyMapper extends
    Mapper<Object, Text, IntWritable, Text> {

//�洢��ҳID
private IntWritable id;
//�洢��ҳPRֵ
private String pr;
//�洢��ҳ������������Ŀ
private int count;
//��ҳ��ÿ���ⲿ���ӵ�ƽ������ֵ
private float average_pr;

public void map(Object key, Text value, Context context) {
    StringTokenizer str = new StringTokenizer(value.toString());
    if (str.hasMoreTokens()) {
        // �õ���ҳID
        id = new IntWritable(Integer.parseInt(str.nextToken()));
    } else {
        return;
    }
    // �õ���ҳpr
    pr = str.nextToken();
    // �õ�����������Ŀ
    count = str.countTokens();
    // ��ÿ���ⲿ����ƽ������ֵ
    average_pr = Float.parseFloat(pr) / count;
    // �õ���ҳ����������ID�����
    while (str.hasMoreTokens()) {
        try {
            String nextId = str.nextToken();
            //����ҳ�������ӵ�ID�ԡ�@+�õ�����ֵ����ʽ���
            Text t = new Text("@" + average_pr);
            context.write(id, t);
            // ����ҳID��PRֵ���
            Text tt = new Text("&" + nextId);
            context.write(id, tt);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

}
	
 public  static class MyReducer extends Reducer<Text, Text, Text, Text>{ 	
	public void reduce(Text key, Iterable<Text> values, Context context) {

        // ����һ���洢��ҳ����ID�Ķ���
        ArrayList<String> ids = new ArrayList<String>();
        // �����е�����ID��String��ʽ����
        String lianjie = "  ";
        // ����һ��������ҳPRֵ�ı���
        float pr = 0;
        //����
        for(Text id : values) {
              String idd = id.toString();
            //�ж�value�ǹ���ֵ�������ⲿ������
              if (idd.substring(0, 1).equals("@")) {
                // ����ֵ
                pr += Float.parseFloat(idd.substring(1));
            } else if (idd.substring(0, 1).equals("&")) {
                // ����id
                String iddd = idd.substring(1);
                System.out.println("idddd= " + iddd);
                ids.add(iddd);
            }
        }
        // ��������pr
        pr = pr * 0.85f + 0.15f;
        // �õ���������ID��String��ʽ
        for (int i = 0; i < ids.size(); i++) {
            lianjie += ids.get(i) + "  ";
        }
        // ���pr+lianjie��ԭ�ļ��ĸ�ʽ����
        String result = pr + lianjie;
        System.out.println("Reduce    result=" + result);
        try {
            context.write(key, new Text(""));
            System.out.println("reduce ִ����ϡ���������");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 }
public static void main(String[] args) throws IOException,
        InterruptedException, ClassNotFoundException {
	
	

    Configuration conf = new Configuration();
    String pathIn1 = "/users/zhwang/desktop/hadoop-1.2.1/input01";//����·��
    String pathOut="";//���·��
    
    
            //����10��
    for (int i = 0; i < 10; i++) {
        System.out.println("xunhuan cishu=" + i);
        
        Job job = new Job(conf, "MapReduce pagerank");
        
	    File jarFile = EJob.createTempJar("bin");
	    EJob.addClasspath("/Users/zhwang1988/desktop/hadoop-1.2.1/conf");
	    ClassLoader classLoader = EJob.getClassLoader();
	    Thread.currentThread().setContextClassLoader(classLoader);
	    job.setJarByClass(PageRank.class);
	    ((JobConf) job.getConfiguration()).setJar(jarFile.toString()); 	    
	    job.setNumReduceTasks(2);	
        
        
        pathOut = pathIn1 + i;
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(pathIn1));
        FileOutputFormat.setOutputPath(job, new Path(pathOut));
        pathIn1 = pathOut;
        job.waitForCompletion(true);

    }

}
	
	
}
