package in.sleepythread.stresstest;


import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SimpleMapper extends Mapper<Object,Text,NullWritable,NullWritable> {

    @Override
    public void map(Object key,Text record, Context context){
        String[] split = record.toString().split(",");
        String name = split[0];
        Integer age = Integer.parseInt(split[1]);
        Double phoneNo = Double.parseDouble(split[2]);
        Student student = new Student(name, age, phoneNo);

    }
}
