package samples.simple.groupdata;

import java.io.Serializable;
import java.util.Comparator;

import scala.Tuple2;

public class MaxComparator implements Serializable,Comparator<Tuple2<String,Integer>>{

	@Override
		public int compare(Tuple2<String, Integer> tuple1, Tuple2<String, Integer> tuple2) {
			return tuple1._2.compareTo(tuple2._2);
		}
		
	

}
