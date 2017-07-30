package dao;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.StandardBasicTypes;

import domain.files;
import domain.users;

public class filesDao {
	private Session sess;
	private Transaction tx;
	private Configuration conf = new Configuration();
	private FileSystem fs;

	public filesDao() {
		// TODO Auto-generated constructor stub
	}

	private void init() {
		sess = HibernateSessionFactory.getSession();
	}

	private void close() {
		HibernateSessionFactory.closeSession();
	}

	//判断label
	public boolean judgeLabel(String username){
		if(getLabel(username).equals(">50K")){
			return true;
		}
		return false;
	}
	
	
	//获得label
	public String getLabel(String username){
		init();
		String sql = "from users where username='" + username + "'";
		tx = sess.beginTransaction();
		Query q = sess.createQuery(sql);
		List<users> list = (List<users>) q.list();
		tx.commit();
		close();
		System.out.println(list.get(0).getLabel()+"-------------------------------------------------------------------------");
		return list.get(0).getLabel();
	}
	
	
	// 用户注册
	public boolean registerAccount(String username, String password, String email, String age, String workclass,
			String education, String marital_status, String occupation, String race, String sex, String native_country)
					throws Exception {
		init();
		// ------------------------------------------------------------------------------------------------------------------------------------------------------------
		String sql = "from users where username='" + username + "'";
		tx = sess.beginTransaction();
		Query q = sess.createQuery(sql);
		List<files> list = (List<files>) q.list();
		tx.commit();
		System.out.println(list.size());
		if (list.size() > 0) {
			return false;
		} else {
			// ------------------------------------------------------------------------------------------------------------------------------------------------------------
			tx=sess.beginTransaction();
			sql = "insert into tb_users(username,password,email,age,workclass,education,marital_status,occupation,race,sex,native_country) values ('"
					+ username + "','" + password + "','" + email + "','" + age + "','" + workclass + "','" + education
					+ "','" + marital_status + "','" + occupation + "','" + race + "','" + sex + "','" + native_country
					+ "')";
			System.out.println(sql);
			sess.createSQLQuery(sql).executeUpdate();
			tx.commit();
			// ------------------------------------------------------------------------------------------------------------------------------------------------------------
			tx = sess.beginTransaction();
			String label = weka.classify(username);
			System.out.println(label);
			sql = "update tb_users set label='" + label + "' where username='" + username + "'";
			sess.createSQLQuery(sql).executeUpdate();
			sql = "insert into tb_train values ('" + age + "','" + workclass + "','" + education + "','"
					+ marital_status + "','" + occupation + "','" + race + "','" + sex + "','" + native_country + "','"
					+ label + "')";
			System.out.println(sql);
			sess.createSQLQuery(sql).executeUpdate();
			// ------------------------------------------------------------------------------------------------------------------------------------------------------------
			sql = "insert into tb_files(file_name,file_owner,security_level,file_type,file_id,prenode_name,prenode_id) values ('"
					+ username + "','" + username
					+ "',2,'userdirectory',(select max(file_id)+1 from tb_files as t1),'root',1)";
			System.out.println(sql);
			sess.createSQLQuery(sql).executeUpdate();
			tx.commit();
			close();
			// ------------------------------------------------------------------------------------------------------------------------------------------------------------
			fs = FileSystem.get(URI.create("hdfs://localhost:9000/user/ubuntu/"), conf);
			fs.mkdirs(new Path("hdfs://localhost:9000/user/ubuntu/root/" + username));
			fs.close();
			return true;
		}
	}

	//登录验证
	public boolean login(String username, String password) {
		init();
		String sql = "from users where username='" + username + "' and password='"+password+"'";
		tx = sess.beginTransaction();
		Query q = sess.createQuery(sql);
		List<files> list = (List<files>) q.list();
		tx.commit();
		close();
		System.out.println(list.size());
		if (list.size() > 0) {
			return true;
		} 
		return false;
	}

	// 创建文件目录，修改数据库，修改hdfs
	public void createDirectory(String createPath, String file_name, String file_owner, int security_level,
			String prenode_name, int prenode_id) {
		init();
		String sql = "insert into tb_files(file_name,file_owner,security_level,file_type,file_id,prenode_name,prenode_id) values (\""
				+ file_name + "\",\"" + file_owner + "\"," + security_level
				+ ",\"directory\",(select max(file_id)+1 from tb_files as t1),\"" + prenode_name + "\"," + prenode_id
				+ ")";
		System.out.println(sql);
		tx = sess.beginTransaction();
		sess.createSQLQuery(sql).executeUpdate();
		tx.commit();
		close();
		try {
			fs = FileSystem.get(URI.create("hdfs://localhost:9000/user/ubuntu/"), conf);
			fs.mkdirs(new Path(createPath));
			fs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 重命名文件，修改数据库，修改hdfs
	public void rename(int file_id, String renamePath, String oldfile_name, String newfile_name) {
		init();
		String sql = "update tb_files set file_name=\"" + newfile_name + "\" where file_id=" + file_id;
		tx = sess.beginTransaction();
		sess.createSQLQuery(sql).executeUpdate();
		sql = "update tb_files set prenode_name='" + newfile_name + "' where prenode_id=" + file_id;
		sess.createSQLQuery(sql).executeUpdate();
		tx.commit();
		close();
		try {
			fs = FileSystem.get(URI.create("hdfs://localhost:9000/user/ubuntu/"), conf);
			fs.rename(new Path(renamePath + "/" + oldfile_name), new Path(renamePath + "/" + newfile_name));
			fs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 修改文件安全等级
	public void changeslevel(List<files> list, int slevel, int prenode_id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getFile_id() == prenode_id) {
				init();
				tx = sess.beginTransaction();
				if (list.get(i).getFile_type().equals("file")) {
					String sql = "update tb_files set security_level =" + slevel + " where file_id=" + prenode_id;
					sess.createSQLQuery(sql).executeUpdate();
				} else {
					changesleveliteration(list, prenode_id, slevel);
					String sql = "update tb_files set security_level =" + slevel + " where file_id=" + prenode_id;
					sess.createSQLQuery(sql).executeUpdate();
				}
				tx.commit();
				close();
				break;
			}
		}
	}

	// 迭代修改文件安全等级
	public void changesleveliteration(List<files> list, int prenode_id, int slevel) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPrenode_id() == prenode_id) {
				if (list.get(i).getFile_type().equals("file")) {
					if (slevel > list.get(i).getSecurity_level()) {
						String sql = "update tb_files set security_level =" + list.get(i).getSecurity_level()
								+ " where file_id=" + list.get(i).getFile_id();
						sess.createSQLQuery(sql).executeUpdate();
						System.out.println("prenode slevel " + list.get(i).getSecurity_level());
					}
				} else {
					changesleveliteration(list, list.get(i).getFile_id(), slevel);
					if (slevel > list.get(i).getSecurity_level()) {
						String sql = "update tb_files set security_level =" + list.get(i).getSecurity_level()
								+ " where file_id=" + list.get(i).getFile_id();
						sess.createSQLQuery(sql).executeUpdate();
						System.out.println("prenode slevel " + list.get(i).getSecurity_level());
					}
				}
			}
		}
	}

	// 删除文件，修改数据库，修改hdfs
	public void delete(List<files> list, int prenode_id, String deletePath, String deletefile_name) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getFile_id() == prenode_id) {
				init();
				tx = sess.beginTransaction();
				if (list.get(i).getFile_type().equals("file")) {
					String sql = "delete from tb_files where file_id=" + prenode_id;
					sess.createSQLQuery(sql).executeUpdate();
					System.out.println("删除file   id为" + prenode_id);
				} else {
					deleteiteration(list, prenode_id);
					String sql = "delete from tb_files where file_id=" + prenode_id;
					sess.createSQLQuery(sql).executeUpdate();
					System.out.println("删除directory   id为" + prenode_id);
				}
				tx.commit();
				close();
				break;
			}
		}
		try {
			fs = FileSystem.get(URI.create("hdfs://localhost:9000/user/ubuntu/"), conf);
			fs.delete(new Path(deletePath + "/" + deletefile_name), true);
			fs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 迭代删除
	public void deleteiteration(List<files> list, int prenode_id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPrenode_id() == prenode_id) {
				if (list.get(i).getFile_type().equals("file")) {
					String sql = "delete from tb_files where file_id=" + list.get(i).getFile_id();
					sess.createSQLQuery(sql).executeUpdate();
					System.out.println("删除file    id为" + list.get(i).getFile_id());
				} else {
					deleteiteration(list, list.get(i).getFile_id());
					String sql = "delete from tb_files where file_id=" + list.get(i).getFile_id();
					sess.createSQLQuery(sql).executeUpdate();
					System.out.println("删除id为" + list.get(i).getFile_id());
				}
			}
		}
	}
	// public listRootFilesPersonal listRootFilesPersonal(String file_owner) {
	// init();
	// String sql = "from files where file_owner='" + file_owner + "' and
	// file_type='userdirectory'";
	// tx = sess.beginTransaction();
	// Query q = sess.createQuery(sql);
	// // SQLQuery s =
	// // sess.createSQLQuery(sql);
	// // s.addScalar("age", StandardBasicTypes.INTEGER);
	// // s.addScalar("education", StandardBasicTypes.STRING);
	// List<files> list = (List<files>) q.list();
	// tx.commit();
	// close();
	// return new listRootFilesPersonal(list, list.get(0).getFile_name(),
	// list.get(0).getFile_id());
	// }
	//
	// public List<files> listFilesPersonal(int prenode_id) {
	// init();
	// String sql = "from files where prenode_id=" + prenode_id;
	// tx = sess.beginTransaction();
	// Query q = sess.createQuery(sql);
	// List<files> list = (List<files>) q.list();
	// tx.commit();
	// close();
	// return list;
	// }
	//
	// public void listFilesAll() {
	//
	// }

	// 列出所有文件，以对象形式存入list
	public List<files> listFiles() {
		init();
		String sql = "from files";
		tx = sess.beginTransaction();
		Query q = sess.createQuery(sql);
		List<files> list = (List<files>) q.list();
		tx.commit();
		close();
		return list;
	}

	// 列出个人用户的文件，以对象形式存入list
	public List<files> listFilesPersonal(List<files> list, String file_owner) {
		List<files> listpersonal = new ArrayList<files>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getFile_owner().equals(file_owner)) {
				files file = new files();
				file.setId(list.get(i).getId());
				file.setFile_name(list.get(i).getFile_name());
				file.setFile_owner(list.get(i).getFile_owner());
				file.setSecurity_level(list.get(i).getSecurity_level());
				file.setFile_type(list.get(i).getFile_type());
				file.setFile_id(list.get(i).getFile_id());
				file.setPrenode_name(list.get(i).getPrenode_name());
				file.setPrenode_id(list.get(i).getPrenode_id());
				listpersonal.add(file);
			}
		}
		return listpersonal;
	}

	// 列出共享文件，以对象形式存入list
	public List<files> listFilesShare(List<files> list) {
		List<files> l = new ArrayList<files>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSecurity_level() != 1) {
				files file = new files();
				file.setId(list.get(i).getId());
				file.setFile_name(list.get(i).getFile_name());
				file.setFile_owner(list.get(i).getFile_owner());
				file.setSecurity_level(list.get(i).getSecurity_level());
				file.setFile_type(list.get(i).getFile_type());
				file.setFile_id(list.get(i).getFile_id());
				file.setPrenode_name(list.get(i).getPrenode_name());
				file.setPrenode_id(list.get(i).getPrenode_id());
				l.add(file);
			}
		}
		return l;
	}

	// // 返回个人用户根目录下的文件list和prenode
	// public UserDirectory UserDirectory(List<files> listpersonal, String
	// file_owner) {
	// List<files> l = new ArrayList<files>();
	// for (int i = 0; i < listpersonal.size(); i++) {
	// if (listpersonal.get(i).getFile_name().equals(file_owner)
	// && listpersonal.get(i).getFile_type().equals("userdirectory")) {
	// files file = new files();
	// file.setId(listpersonal.get(i).getId());
	// file.setFile_name(listpersonal.get(i).getFile_name());
	// file.setFile_owner(listpersonal.get(i).getFile_owner());
	// file.setSecurity_level(listpersonal.get(i).getSecurity_level());
	// file.setFile_type(listpersonal.get(i).getFile_type());
	// file.setFile_id(listpersonal.get(i).getFile_id());
	// file.setPrenode_name(listpersonal.get(i).getPrenode_name());
	// file.setPrenode_id(listpersonal.get(i).getPrenode_id());
	// l.add(file);
	// }
	// }
	// return new UserDirectory(l, l.get(0).getFile_name(),
	// l.get(0).getFile_id());
	// }

	// 返回个人用户根路径文件目录
	public List<files> showUserDirectory(List<files> listpersonal, String file_owner) {
		int prenode_id = 0;
		List<files> l = new ArrayList<files>();
		for (int i = 0; i < listpersonal.size(); i++) {
			if (listpersonal.get(i).getFile_name().equals(file_owner)
					&& listpersonal.get(i).getFile_type().equals("userdirectory")) {
				prenode_id = listpersonal.get(i).getFile_id();
				break;
			}
		}
		for (int i = 0; i < listpersonal.size(); i++) {
			if (listpersonal.get(i).getPrenode_id() == prenode_id) {
				files file = new files();
				file.setId(listpersonal.get(i).getId());
				file.setFile_name(listpersonal.get(i).getFile_name());
				file.setFile_owner(listpersonal.get(i).getFile_owner());
				file.setSecurity_level(listpersonal.get(i).getSecurity_level());
				file.setFile_type(listpersonal.get(i).getFile_type());
				file.setFile_id(listpersonal.get(i).getFile_id());
				file.setPrenode_name(listpersonal.get(i).getPrenode_name());
				file.setPrenode_id(listpersonal.get(i).getPrenode_id());
				l.add(file);
			}
		}
		return l;
	}

	// 返回共享用户跟路径文件目录
	public List<files> showShareDirectory(List<files> listshare) {
		List<files> l = new ArrayList<files>();
		for (int i = 0; i < listshare.size(); i++) {
			if (listshare.get(i).getPrenode_id() == 1) {
				files file = new files();
				file.setId(listshare.get(i).getId());
				file.setFile_name(listshare.get(i).getFile_name());
				file.setFile_owner(listshare.get(i).getFile_owner());
				file.setSecurity_level(listshare.get(i).getSecurity_level());
				file.setFile_type(listshare.get(i).getFile_type());
				file.setFile_id(listshare.get(i).getFile_id());
				file.setPrenode_name(listshare.get(i).getPrenode_name());
				file.setPrenode_id(listshare.get(i).getPrenode_id());
				l.add(file);
			}
		}
		return l;
	}

	// 根据file_name，返回当前路径文件目录
	public List<files> showDirectoryByName(List<files> listCurrentDirectory, List<files> listpersonal, int file_name) {
		int prenode_id = 0;
		List<files> l = new ArrayList<files>();
		for (int i = 0; i < listCurrentDirectory.size(); i++) {
			if (listCurrentDirectory.get(i).getFile_name().equals(file_name)) {
				prenode_id = listCurrentDirectory.get(i).getFile_id();
				break;
			}
		}
		for (int i = 0; i < listpersonal.size(); i++) {
			if (listCurrentDirectory.get(i).getPrenode_id() == prenode_id) {
				files file = new files();
				file.setId(listpersonal.get(i).getId());
				file.setFile_name(listpersonal.get(i).getFile_name());
				file.setFile_owner(listpersonal.get(i).getFile_owner());
				file.setSecurity_level(listpersonal.get(i).getSecurity_level());
				file.setFile_type(listpersonal.get(i).getFile_type());
				file.setFile_id(listpersonal.get(i).getFile_id());
				file.setPrenode_name(listpersonal.get(i).getPrenode_name());
				file.setPrenode_id(listpersonal.get(i).getPrenode_id());
				l.add(file);
			}
		}
		return l;
	}

	// 根据file_id，返回当前路径文件目录
	public List<files> showDirectoryByID(List<files> listpersonal, int file_id) {
		List<files> l = new ArrayList<files>();
		for (int i = 0; i < listpersonal.size(); i++) {
			if (listpersonal.get(i).getPrenode_id() == file_id) {
				files file = new files();
				file.setId(listpersonal.get(i).getId());
				file.setFile_name(listpersonal.get(i).getFile_name());
				file.setFile_owner(listpersonal.get(i).getFile_owner());
				file.setSecurity_level(listpersonal.get(i).getSecurity_level());
				file.setFile_type(listpersonal.get(i).getFile_type());
				file.setFile_id(listpersonal.get(i).getFile_id());
				file.setPrenode_name(listpersonal.get(i).getPrenode_name());
				file.setPrenode_id(listpersonal.get(i).getPrenode_id());
				l.add(file);
			}
		}
		return l;
	}

}