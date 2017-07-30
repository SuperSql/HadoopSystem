package dao;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServlet;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.experiment.InstanceQuery;

public class weka extends HttpServlet {
	private static InstanceQuery query = null;
	private static Instances data = null;
	private static Classifier classifier = null;

	public weka() {
		// TODO Auto-generated constructor stub

	}

	static {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("构建分类器！。。。。。。。");
				try {
					build();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		Timer timer = new Timer();
		long delay = 0;
		long intevalPeriod = 86400 * 1000;
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);

	}

	public static void build() throws Exception {
		if (query == null) {
			query = new InstanceQuery();
		}
		if (classifier == null) {
			classifier = new J48();
		}
		query.setDatabaseURL("jdbc:mysql://localhost/HADOOPDATA");
		query.setUsername("root");
		query.setPassword("root");
		query.setQuery("select * from tb_train");
		data = query.retrieveInstances();
		if (data.classIndex() == -1) {
			data.setClassIndex(data.numAttributes() - 1);
		}
		// System.out.println(data);
		classifier.buildClassifier(data);
	}

	public static String classify(String username) throws Exception {
		// if (query == null) {
		// query = new InstanceQuery();
		// }
		// if (classifier == null) {
		// classifier = new J48();
		// }
		// query.setDatabaseURL("jdbc:mysql://localhost/HADOOPDATA");
		// query.setUsername("root");
		// query.setPassword("root");
		// query.setQuery("select * from tb_train");
		// data = query.retrieveInstances();
		// if (data.classIndex() == -1) {
		// data.setClassIndex(data.numAttributes() - 1);
		// }
		// // System.out.println(data);
		// classifier.buildClassifier(data);

		// query.setQuery("select * from tb_train limit 10");
		// data = query.retrieveInstances();
		// if (data.classIndex() == -1) {
		// data.setClassIndex(data.numAttributes() - 1);
		// }
		// System.out.println(data);
		// for (int i = 0; i < 10; i++) {
		// System.out.println(classifier.classifyInstance(data.instance(i)) +
		// "---"+data.instance(i).classValue());
		// }

		query.setQuery(
				"select age,workclass,education,marital_status,occupation,race,sex,native_country from tb_users where username='"
						+ username + "'");
		data = query.retrieveInstances();
		if (data.classIndex() == -1) {
			data.setClassIndex(data.numAttributes() - 1);
		}
		System.out.println(data);
		System.out.println(classifier.classifyInstance(data.instance(0)));
		if (classifier.classifyInstance(data.instance(0)) == 0.0) {
			return "<=50K";
		} else
			return ">50K";
	}
}
