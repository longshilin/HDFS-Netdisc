package com.elon33.netdisc.model;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.JobConf;

/**
 * Hadoop-hdfs文件操作模型
 * 
 * @author elon@elon33.com
 *
 */
public class HdfsDAO {

	// HDFS访问地址
	private static final String HDFS = "hdfs://192.168.1.101:9000";
	// hdfs路径
	private String hdfsPath;
	// Hadoop系统配置
	private Configuration conf;

	public HdfsDAO(Configuration conf) {
		this(HDFS, conf);
	}

	public HdfsDAO(String hdfs, Configuration conf) {
		this.hdfsPath = hdfs;
		this.conf = conf;
	}

	public static String getHdfs() {
		return HDFS;
	}

	// 启动函数
	public static void main(String[] args) throws IOException {
		JobConf conf = config();
		HdfsDAO hdfs = new HdfsDAO(conf);

		try {
			System.out.println(conf.get("fs.defaultFS"));
			hdfs.mkdirs("/Tom1/tom1/ttt");
			// hdfs.copyFile("c:/mr.jar", "/aa/bb/1234");
			// hdfs.ls("/");
			// hdfs.rmr("/wgc/files");
			// hdfs.download("/jdk.exe", "f:/");
		} catch (Exception e) {
			System.out.println("执行失败!");
			e.printStackTrace();
			return;
		}
		System.out.println("执行成功!");
	}

	// 加载Hadoop配置文件
	public static JobConf config() {
		JobConf conf = new JobConf(HdfsDAO.class);
		conf.setJobName("HdfsDAO");
		conf.addResource("classpath:/hadoop-config/core-site.xml");
		conf.addResource("classpath:/hadoop-config/hdfs-site.xml");
		conf.addResource("classpath:/hadoop-config/mapred-site.xml");
		return conf;
	}

	// 在根目录下创建文件夹
	public void mkdirs(String folder) throws IOException {
		Path path = new Path(folder);
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		if (!fs.exists(path)) {
			fs.mkdirs(path);
			System.out.println("Create: " + folder);
		}
		fs.close();
	}

	// 某个文件夹的文件列表
	public FileStatus[] ls(String folder) throws IOException {
		Path path = new Path(folder);
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		FileStatus[] list = fs.listStatus(path);
		System.out.println("ls: " + folder);
		System.out.println("==========================================================");
		if (list != null)
			for (FileStatus f : list) {
				// System.out.printf("name: %s, folder: %s, size: %d\n",
				// f.getPath(), f.isDir(), f.getLen());
				System.out.printf("%s, folder: %s, 大小: %dK\n", f.getPath().getName(), (f.isDirectory() ? "目录" : "文件"),
						f.getLen() / 1024);
			}
		System.out.println("==========================================================");
		fs.close();

		return list;
	}

	// 将文件上传到hdfs
	public void copyFile(String local, String remote) throws IOException {
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		// remote---/用户/用户下的文件或文件夹
		fs.copyFromLocalFile(new Path(local), new Path(remote));
		System.out.println("copy from: " + local + " to " + remote);
		fs.close();
	}

	// 删除文件或文件夹
	public void rmr(String folder) throws IOException {
		Path path = new Path(folder);
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		fs.deleteOnExit(path);
		System.out.println("Delete: " + folder);
		fs.close();
	}

	// 下载文件到本地系统
	public void download(String remote, String local) throws IOException {
		Path path = new Path(remote);
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		File localFile = new File(local);
		if(localFile.exists()){
			deleteFile(localFile);
		}
		fs.copyToLocalFile(path, new Path(local));
		System.out.println("download: from " + remote + " to " + local);
		fs.close();
	}

	// 递归删除指定目录及目录下的文件
	private void deleteFile(File file){
	     if(file.isDirectory()){
	          File[] files = file.listFiles();
	          for(int i=0; i<files.length; i++){
	               deleteFile(files[i]);
	          }
	     }
	     file.delete();
	}
}