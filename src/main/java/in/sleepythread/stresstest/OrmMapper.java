package in.sleepythread.stresstest;

import in.sleepythread.HiveBuilderFactory;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class OrmMapper extends Mapper<Object,Text,NullWritable,NullWritable> {

    @Override
    public void map(Object key,Text record,Context context){
        HiveBuilderFactory factory = null;
        try {
            factory = HiveBuilderFactory.Instance("thrift://resource-mgr.hadoopcluster.org:9083");
            Student student = factory.deSerialize(Student.class, record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
