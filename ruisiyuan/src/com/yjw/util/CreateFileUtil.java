package com.yjw.util;

import java.io.File;
import java.io.IOException;

public class CreateFileUtil {

	/**
	 * 创建文件
	 * 
	 * @param destFileName
	 * @return
	 */
	public static boolean CreateFile(String destFileName) throws Exception {
		File file = new File(destFileName);
		if (file.exists()) {
			System.out.println();
			throw new Exception("创建单个文件" + destFileName + "失败，目标文件已存在！");
		}
		if (destFileName.endsWith(File.separator)) {
			throw new Exception("创建单个文件" + destFileName + "失败，目标不能是目录！");
		}
		if (!file.getParentFile().exists()) {
			System.out.println("目标文件所在路径不存在，准备创建。。。");
			if (!file.getParentFile().mkdirs()) {
				throw new Exception("创建目录文件所在的目录失败！");
			}
		}
		// 创建目标文件
		try {
			if (file.createNewFile()) {
				System.out.println("创建单个文件" + destFileName + "成功！");
				return true;
			} else {
				throw new Exception("创建单个文件" + destFileName + "失败！");
			}
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * 创建目录
	 * 
	 * @param destDirName
	 * @return
	 */
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			return false;
		}
		if (!destDirName.endsWith(File.separator))
			destDirName = destDirName + File.separator;
		// 创建单个目录
		if (dir.mkdirs()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 创建临时文件
	 * 
	 * @param prefix
	 * @param suffix
	 * @param dirName
	 * @return
	 */
	public static String createTempFile(String prefix, String suffix,
			String dirName) {
		File tempFile = null;
		try {
			if (dirName == null) {
				// 在默认文件夹下创建临时文件
				tempFile = File.createTempFile(prefix, suffix);
				return tempFile.getCanonicalPath();
			} else {
				File dir = new File(dirName);
				// 如果临时文件所在目录不存在，首先创建
				if (!dir.exists()) {
					if (!CreateFileUtil.createDir(dirName)) {
						System.out.println("创建临时文件失败，不能创建临时文件所在目录！");
						return null;
					}
				}
				tempFile = File.createTempFile(prefix, suffix, dir);
				return tempFile.getCanonicalPath();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("创建临asdadasd时文件失败" + e.getMessage());
			return null;
		}
	}

//	public static void main(String[] args) throws Exception {
//		// 创建目录
//		String dirName = "E:\\aaaa\\bbb\\ccc\\aaa\\IO流\\filedir";
//		CreateFileUtil.createDir(dirName);
//		// 创建文件
//		String fileName = dirName + "/filedir/test2/testFile.txt";
//		CreateFileUtil.CreateFile(fileName);
//		// 创建临时文件
//		String prefix = "temp";
//		String suffix = ".txt";
//		for (int i = 0; i < 10; i++) {
//			System.out.println("创建了临时文件:"
//					+ CreateFileUtil.createTempFile(prefix, suffix, dirName));
//		}
//
//	}
}
