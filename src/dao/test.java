package dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import domain.files;

public class test {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		filesDao filesdao = ctx.getBean("filesDao", filesDao.class);
		// TODO Auto-generated method stub
		// 1
		// Session sess = HibernateSessionFactory.getSession();
		// Transaction tx = sess.beginTransaction();
		// String sql = "select * from tb_trainusers";
		// SQLQuery s = sess.createSQLQuery(sql);
		// s.addScalar("age", StandardBasicTypes.INTEGER);
		// s.addScalar("education", StandardBasicTypes.STRING);
		// List l = s.list();
		// for (Iterator iterator = l.iterator(); iterator.hasNext();) {
		// // 每个集合元素都是一个数组，数组元素师person_id,person_name,person_age三列值
		// Object[] objects = (Object[]) iterator.next();
		// System.out.println("age=" + objects[0]);
		// System.out.println("education=" + objects[1]);
		// System.out.println("----------------------------");
		// }

		// 2创建目录
		// filesDao filesdao = new filesDao();
		// filesdao.createDirectory("hdfs://localhost:9000/user/ubuntu/root/Tom/file2","file2",
		// "Tom", 2, "Tom",2);

		// 3
		// filesDao filesdao = new filesDao();
		// filesdao.createDirectory(currentPath, file_name, file_owner,
		// security_level, prenode_name, prenode_id);

		// 4
//		filesDao filesdao = new filesDao();
		List<files> list = filesdao.listFiles();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getFile_id() + list.get(i).getFile_name());
		}
		List<files> listpersonal;
		listpersonal = filesdao.listFilesPersonal(list, "Tom");
		for (int i = 0; i < listpersonal.size(); i++) {
			System.out.println(listpersonal.get(i).getFile_id() + listpersonal.get(i).getFile_name());
		}

	}
}
