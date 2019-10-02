package com.lg.test;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;

public class FileUtil {
	/**
	 * 输入文件夹的路径，以JSON字符串的格式返回该文件夹的目录结构
	 * 
	 * @author lingang
	 * @param absoluteFolderName
	 * @return
	 */
	public static String getAllFromFolder(String absoluteFolderName) {
		// 构造该JSON字符串的Map对象
		Map<Object, Object> dirStructure = new HashMap<Object, Object>();
		File currentFolder = new File(absoluteFolderName);
		if (!currentFolder.exists()) {
			// 如果输入的路径不存在，则把dirExist字段设为false
			dirStructure.put("dirExist", "false");
		} else {
			// 如果输入的路径存在，则把dirExist字段设为true
			dirStructure.put("dirExist", "true");
			if (currentFolder.isDirectory()) {
				// 如果输入的路径是文件夹，则把isDirectory字段设为true
				dirStructure.put("isDirectory", "true");
				// 目录结构包含在targetDirContent字段中
				dirStructure.put("targetDirContent", readFolder(currentFolder));

			} else {
				// 如果输入的路径不是文件夹，则把isDirectory字段设为false
				dirStructure.put("isDirectory", "false");
			}
		}
		// 把dirStructure转化成JSON格式后返回
		// return mapToJosn(dirStructure);
		return null;
	}

	/**
	 * 传入文件夹名递归读取该文件夹的目录结构
	 * 
	 * @author lingang
	 * @param folder
	 * @return
	 */
	private static List<Map<String, Object>> readFolder(File folder) {
		// 创建一个List来存储该文件夹中的所有条目，包括文件和文件夹
		List<Map<String, Object>> filesList = null;
		// 创建一个Map来存储该文件夹中的每个条目，包括文件和文件夹
		Map<String, Object> fileMap = null;
		if (folder.exists() && folder.isDirectory()) {
			// 如果文件夹存在才实例化List对象
			filesList = new ArrayList<Map<String, Object>>();
			File[] files = folder.listFiles();
			for (File file : files) {
				// 为每个条目实例化一个Map
				fileMap = new HashMap<String, Object>();
				if (file.isFile()) {
					// 如果该条目是一个文件，则把file字段设置为该文件的文件名
					fileMap.put("file", file.getName());
				}
				if (file.isDirectory()) {
					// 如果该条目是一个文件夹，则将subDirName字段设置为该文件夹的名字
					fileMap.put("subDirName", file.getName());
					// 然后传入该文件夹的名字递归调用本方法，再把返回值设置给subDirContent字段
					fileMap.put("subDirContent", readFolder(file));
				}
				// 将该条目添加到List中
				filesList.add(fileMap);
			}
		}
		return filesList;
	}

	/**
	 * 传入文件夹名和文件后缀名，返回满足该后缀名的所有文件名列表
	 * 
	 * @author lingang
	 * @param folderName
	 * @param postfix
	 * @return
	 */
	public static List<String> getAllFileName(String folderName, String postfix) {
		List<String> fileList = null;
		File folder = new File(folderName);
		if (folder.exists()) {
			if (folder.isDirectory()) {
				fileList = new ArrayList<String>();
				File[] files = folder.listFiles();
				for (File file : files) {
					if (file.isFile()) {
						String fileName = file.getName();
						if (fileName.endsWith(postfix)) {
							fileList.add(fileName);
						}
					}
					if (file.isDirectory()) {
						List<String> list = getAllFileName(file.getAbsolutePath(), postfix);
						if (list != null && !list.isEmpty()) {
							fileList.addAll(list);
						}
					}
				}
			}
		}
		return fileList;
	}

	/**
	 * 传入文件夹名和文件后缀名，返回满足该后缀名的所有文件名列表(不包含后缀名)
	 * 
	 * @author lingang
	 * @param folderName
	 * @param postfix
	 * @return
	 */
	public static List<String> getAllFileNameWithoutPostfix(String folderName, String postfix) {
		List<String> fileList = null;
		File folder = new File(folderName);
		if (folder.exists()) {
			if (folder.isDirectory()) {
				fileList = new ArrayList<String>();
				File[] files = folder.listFiles();
				for (File file : files) {
					if (file.isFile()) {
						String fileName = file.getName();
						if (fileName.endsWith(postfix)) {
							fileName = fileName.substring(0, fileName.length() - postfix.length());
							fileList.add(fileName);
						}
					}
					if (file.isDirectory()) {
						List<String> list = getAllFileNameWithoutPostfix(file.getAbsolutePath(), postfix);
						if (list != null && !list.isEmpty()) {
							fileList.addAll(list);
						}
					}
				}
			}
		}
		return fileList;
	}

	/**
	 * 传入文件夹名，返回所有文件名列表
	 * 
	 * @author lingang
	 * @param folderName
	 * @return
	 */
	public static List<String> getAllFileName(String folderName) {
		List<String> fileList = null;
		File folder = new File(folderName);
		if (folder.exists()) {
			if (folder.isDirectory()) {
				fileList = new ArrayList<String>();
				File[] files = folder.listFiles();
				for (File file : files) {
					if (file.isFile()) {
						String fileName = file.getName();
						fileList.add(fileName);
					}
					if (file.isDirectory()) {
						List<String> list = getAllFileName(file.getAbsolutePath());
						if (list != null && !list.isEmpty()) {
							fileList.addAll(list);
						}
					}
				}
			}
		}
		return fileList;
	}

	/**
	 * 传入文件夹名和文件后缀名，返回满足该后缀名的所有文件的绝对路径列表
	 * 
	 * @author lingang
	 * @param folderName
	 * @param postfix
	 * @return
	 */
	public static List<String> getAllAbsolutePath(String folderName, String postfix) {
		List<String> fileList = null;
		File folder = new File(folderName);
		if (folder.exists()) {
			if (folder.isDirectory()) {
				fileList = new ArrayList<String>();
				File[] files = folder.listFiles();
				for (File file : files) {
					if (file.isFile()) {
						String absoluteFileName = file.getAbsolutePath();
						if (absoluteFileName.contains(postfix)) {
							fileList.add(absoluteFileName);
						}
					}
					if (file.isDirectory()) {
						List<String> list = getAllAbsolutePath(file.getAbsolutePath(), postfix);
						if (list != null && !list.isEmpty()) {
							fileList.addAll(list);
						}
					}
				}
			}
		}
		return fileList;
	}

	/**
	 * 传入文件夹名，返回所有文件和文件夹的绝对路径
	 * 
	 * @author lingang
	 * @param folderName
	 * @return
	 */
	public static List<String> getAllAbsolutePath(String folderName) {
		List<String> fileList = null;
		File folder = new File(folderName);
		if (folder.exists()) {
			if (folder.isDirectory()) {
				fileList = new ArrayList<String>();
				File[] files = folder.listFiles();
				for (File file : files) {
					fileList.add(file.getAbsolutePath());
					if (file.isFile()) {
					}
					if (file.isDirectory()) {
						List<String> list = getAllAbsolutePath(file.getAbsolutePath());
						if (list != null && !list.isEmpty()) {
							fileList.addAll(list);
						}
					}
				}
			}
		}
		return fileList;
	}

	/**
	 * 传入文件夹名和文件后缀名，返回满足该后缀名的所有文件的文件名-绝对路径键值对
	 * 
	 * @author lingang
	 * @param folderName
	 * @param postfix
	 * @return
	 */
	public static Map<String, String> getAllFileMap(String folderName, String postfix) {
		Map<String, String> fileMap = null;
		File folder = new File(folderName);
		if (folder.exists()) {
			if (folder.isDirectory()) {
				fileMap = new HashMap<String, String>();
				File[] files = folder.listFiles();
				for (File file : files) {
					if (file.isFile()) {
						String fileName = file.getName();
						if (fileName.endsWith(postfix)) {
							fileMap.put(fileName, file.getAbsolutePath());
						}
					}
					if (file.isDirectory()) {
						Map<String, String> map = getAllFileMap(file.getAbsolutePath(), postfix);
						if (map != null && !map.isEmpty()) {
							fileMap.putAll(map);
						}
					}
				}
			}
		}
		return fileMap;
	}

	/**
	 * 传入文件夹名和文件后缀名，返回满足该后缀名的所有文件的文件名-绝对路径键值对(非递归查找)
	 * 
	 * @author lingang
	 * @param folderName
	 * @param postfix
	 * @return
	 */
	public static Map<String, String> getAllFileMapWithoutRecursion(String folderName, String postfix) {
		Map<String, String> fileMap = null;
		File folder = new File(folderName);
		if (folder.exists()) {
			if (folder.isDirectory()) {
				fileMap = new HashMap<String, String>();
				File[] files = folder.listFiles();
				for (File file : files) {
					if (file.isFile()) {
						String fileName = file.getName();
						if (fileName.endsWith(postfix)) {
							fileMap.put(fileName, file.getAbsolutePath());
						}
					}
				}
			}
		}
		return fileMap;
	}

	/**
	 * 传入文件夹名和文件后缀名，返回满足条件的List<Map<Object, Object>>对象；
	 * 每个文件是一个Map对象，该Map对象包含三个键值对， 第一个键值对的键为"fileName"-值是文件名；
	 * 第二个键值对的键为"absolutePath"-值是该文件的绝对路径； 第三个键值对的键为"size"-值为文件的大小(单位为字节)。
	 * 
	 * @author lingang
	 * @param folderName
	 * @param postfix
	 * @param relativeDir
	 * @return
	 */
	public static List<Object> getAllFileObjectList(String folderName, String postfix) {
		List<Object> filesList = null;
		Map<Object, Object> fileMap = null;
		File folder = new File(folderName);
		if (folder.exists()) {
			if (folder.isDirectory()) {
				filesList = new ArrayList<Object>();
				File[] files = folder.listFiles();
				for (File file : files) {
					if (file.isFile()) {
						fileMap = new HashMap<Object, Object>();
						String fileName = file.getName();
						if (fileName.endsWith(postfix)) {
							String absoluteName = file.getAbsolutePath();
							// 把绝对路径截取为相对于本项目根目录的路径
							fileMap.put("fileName", fileName);
							fileMap.put("absoluteName", absoluteName);
							fileMap.put("size", file.length());
							filesList.add(fileMap);
						}
					}
					if (file.isDirectory()) {
						List<Object> list = getAllFileObjectList(file.getAbsolutePath(), postfix);
						if (list != null && !list.isEmpty()) {
							filesList.addAll(list);
						}
					}
				}
			}
		}
		return filesList;
	}

	/**
	 * 传入文件夹名，文件后缀名以及相对目录名，返回满足条件的List<Map<Object, Object>>对象；
	 * 每个文件是一个Map对象，该Map对象包含三个键值对， 第一个键值对的键为"fileName"-值是文件名；
	 * 第二个键值对的键为"relativePath"-值是该文件的相对于relativeDir目录的相对路径(该路径最前面已经包含文件分隔符)；
	 * 第三个键值对的键为"size"-值为文件的大小(单位为字节)。
	 * 
	 * @author lingang
	 * @param folderName
	 * @param postfix
	 * @param relativeDir
	 * @return
	 */
	public static List<Object> getAllFileObjectList(String folderName, String postfix, String relativeDir) {
		List<Object> filesList = null;
		Map<Object, Object> fileMap = null;
		File folder = new File(folderName);
		if (folder.exists()) {
			if (folder.isDirectory()) {
				filesList = new ArrayList<Object>();
				File[] files = folder.listFiles();
				for (File file : files) {
					if (file.isFile()) {
						fileMap = new HashMap<Object, Object>();
						String fileName = file.getName();
						if (fileName.endsWith(postfix)) {
							String absoluteName = file.getAbsolutePath();
							// 把绝对路径截取为相对于本项目根目录的路径
							String[] sa = absoluteName.split(relativeDir);
							String relativePath = sa[sa.length - 1];
							fileMap.put("fileName", fileName);
							fileMap.put("relativePath", relativePath);
							fileMap.put("size", file.length());
							filesList.add(fileMap);
						}
					}
					if (file.isDirectory()) {
						List<Object> list = getAllFileObjectList(file.getAbsolutePath(), postfix, relativeDir);
						if (list != null && !list.isEmpty()) {
							filesList.addAll(list);
						}
					}
				}
			}
		}
		return filesList;
	}

	/**
	 * 传入文件夹名和文件后缀名，返回满足条件的Map对象， 其中的键为文件名-值是该文件绝对路径。
	 * 
	 * @author lingang
	 * @param folderName
	 * @param postfix
	 * @return
	 */
	public static List<Map<Object, Object>> getAllFilePathMaps(String folderName, String postfix) {
		List<Map<Object, Object>> maps = null;
		Map<Object, Object> fileMap = null;
		File folder = new File(folderName);
		if (folder.exists()) {
			if (folder.isDirectory()) {
				maps = new ArrayList<Map<Object, Object>>();
				File[] files = folder.listFiles();
				if (files != null) {
					for (File file : files) {
						if (file.isFile()) {
							String fileName = file.getName();
							if (fileName.endsWith(postfix)) {
								fileMap = new HashMap<Object, Object>();
								String absoluteName = file.getAbsolutePath();
								fileMap.put("name", fileName);
								fileMap.put("path", absoluteName);
								maps.add(fileMap);
							}
						}
						if (file.isDirectory()) {
							List<Map<Object, Object>> temp = getAllFilePathMaps(file.getAbsolutePath(), postfix);
							if (temp != null && !temp.isEmpty()) {
								maps.addAll(temp);
							}
						}
					}
				}

			}
		}
		return maps;
	}

	/**
	 * 传入文件夹名，文件后缀名以及相对目录名，返回满足条件的Map对象，
	 * 其中的键为文件名-值是该文件相对于所传入的相对目录的相对路径(该相对路径带有最前面带有文件分隔符)。
	 * 
	 * @author lingang
	 * @param folderName
	 * @param postfix
	 * @return
	 */
	public static List<Map<Object, Object>> getAllFilePathMaps(String folderName, String postfix, String relativeDir) {
		List<Map<Object, Object>> maps = null;
		Map<Object, Object> fileMap = null;
		File folder = new File(folderName);
		if (folder.exists()) {
			if (folder.isDirectory()) {
				maps = new ArrayList<Map<Object, Object>>();
				File[] files = folder.listFiles();
				for (File file : files) {
					if (file.isFile()) {
						String fileName = file.getName();
						if (fileName.endsWith(postfix)) {
							fileMap = new HashMap<Object, Object>();
							String absoluteName = file.getAbsolutePath();
							// 把绝对路径截取为相对于本项目根目录的路径
							String[] sa = absoluteName.split(relativeDir);
							String relativePath = sa[sa.length - 1];
							fileMap.put("name", fileName);
							fileMap.put("path", relativePath);
							maps.add(fileMap);
						}
					}
					if (file.isDirectory()) {
						List<Map<Object, Object>> temp = getAllFilePathMaps(file.getAbsolutePath(), postfix,
								relativeDir);
						if (temp != null && !temp.isEmpty()) {
							maps.addAll(temp);
						}
					}
				}
			}
		}
		return maps;
	}

	// /**
	// * 将Map转换为JSON字符串
	// *
	// * @author lingang
	// * @param map
	// * @return
	// */
	// public static String mapToJosn(Map<Object, Object> map) {
	// String json = null;
	// if (map != null && !map.isEmpty()) {
	// JSONObject jsonObject = new JSONObject();
	// for (Object key : map.keySet()) {
	// jsonObject.put(key, map.get(key));
	// }
	// json = jsonObject.toString();
	// }
	// return json;
	// }
	//
	// /**
	// * 将List转换为JSON字符串
	// *
	// * @author lingang
	// * @param list
	// * @return
	// */
	// public static String listToJosn(List<Object> list) {
	// String json = null;
	// if (list != null && !list.isEmpty()) {
	// JSONArray jsonArray = new JSONArray();
	// jsonArray = JSONArray.fromObject(list);
	// json = jsonArray.toString();
	// }
	// return json;
	// }

	/**
	 * 将字符串写到指定路径的文件中(直接替换该文件中的内容，而不是在后面添加)，
	 * 并且可以指定写入的编码方式，是否替换同名文件写入成功返回true，写入失败返回false
	 * 
	 * @author lingang
	 * @param content
	 * @param path
	 * @param encoding
	 * @param replace
	 * @return
	 */
	public static boolean writeStringToFile(String content, String path, String encoding, boolean replace) {
		boolean writeSuccess = false;
		File file = new File(path);
		OutputStream os = null;
		if (file.exists() && !replace) {
			// 如果文件已经存在，且replace为false，那么不执行写文件操作
			writeSuccess = false;
		} else {
			if (content != null) {
				// 当字符串中有内容时，才创建输出流
				try {
					os = new FileOutputStream(file);
					try {
						os.write(content.getBytes(encoding));
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} finally {
					try {
						if (os != null) {
							os.close();
						}
						writeSuccess = true;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				writeSuccess = false;
			}
		}
		return writeSuccess;
	}

	/**
	 * 从指定路径的文件中读取为字符串，并且指定读取的编码
	 * 
	 * @author lingang
	 * @param path
	 * @param encoding
	 * @return
	 */
	public static String readStringFromFile(String path, String encoding) {
		String fileContent = null;
		File file = new File(path);
		InputStream is = null;
		if (file.exists() && file.isFile()) {
			fileContent = new String();
			try {
				is = new FileInputStream(file);
				byte[] buffer = new byte[4 * 1024];
				int readIndex = 0;
				while ((readIndex = is.read(buffer)) != -1) {
					fileContent += new String(buffer, 0, readIndex, encoding);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (is != null) {
						is.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return fileContent;
	}

	/**
	 * 从指定路径的文件中读取为字符串列表，并且指定读取的编码
	 * 
	 * @author lingang
	 * @param path
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	public static List<String> readLinesFromFile(String path, String encoding) throws IOException {
		List<String> fileContent = null;
		File file = new File(path);
		BufferedReader br = null;
		if (file.exists() && file.isFile()) {
			fileContent = new ArrayList<String>();
			br = new BufferedReader(new FileReader(file));
			String temp = null;
			while ((temp = br.readLine()) != null) {
				fileContent.add(temp);
			}
		}
		return fileContent;
	}

	/**
	 * 从指定路径的文件中读取为字符串，并且指定读取的编码
	 * 
	 * @author lingang
	 * @param path
	 * @param encoding
	 * @return
	 */
	public static byte[] readBytesFromFile(String path) {
		ByteArrayOutputStream output = null;
		File file = new File(path);
		InputStream is = null;
		byte[] data = null;
		if (file.exists() && file.isFile()) {
			output = new ByteArrayOutputStream();
			try {
				is = new FileInputStream(file);
				byte[] buffer = new byte[4 * 1024];
				int readIndex = 0;
				while ((readIndex = is.read(buffer)) != -1) {
					output.write(buffer, 0, readIndex);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (is != null) {
						is.close();
						output.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			data = output.toByteArray();
		}
		return data;
	}

	/**
	 * 读取字节数组，适用于javaee
	 * 
	 * @param path
	 * @param loader
	 * @return
	 */
	public static byte[] readBytesFromSource(String path, Class loader) {
		ByteArrayOutputStream output = null;
		InputStream is = loader.getResourceAsStream(path);
		byte[] data = null;
		if (is != null) {
			output = new ByteArrayOutputStream();
			try {
				byte[] buffer = new byte[4 * 1024];
				int readIndex = 0;
				while ((readIndex = is.read(buffer)) != -1) {
					output.write(buffer, 0, readIndex);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (is != null) {
						is.close();
						output.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			data = output.toByteArray();
		}
		return data;
	}

	/**
	 * 根据输入流和指定的编码方式返回字符串
	 * 
	 * @author lingang
	 * @param is
	 * @param encoding
	 * @return
	 */
	public static String readStringFromInputStream(InputStream is, String encoding) {
		String fileContent = null;
		if (is != null) {
			fileContent = new String();
			try {
				byte[] buffer = new byte[4 * 1024];
				int readIndex = 0;
				while ((readIndex = is.read(buffer)) != -1) {
					fileContent += new String(buffer, 0, readIndex, encoding);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (is != null) {
						is.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return fileContent;
	}

	/**
	 * 将对象写入指定文件中(覆盖写入)
	 * 
	 * @param object
	 * @param filePath
	 * @return
	 */
	public static boolean writeObjectToFile(Object object, String filePath) {
		boolean flag = false;
		ObjectOutputStream oos = null;
		File file = null;
		FileOutputStream fos = null;
		if (object != null) {
			file = new File(filePath);
			try {
				fos = new FileOutputStream(file);
				oos = new ObjectOutputStream(fos);
				oos.writeObject(object);
				flag = true;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (oos != null) {
					try {
						oos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return flag;
	}

	/**
	 * 从文件中读出对象
	 * 
	 * @param filePath
	 * @return
	 */
	public static Object readObjectFromFile(String filePath) {
		Object object = null;
		File file = new File(filePath);
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		if (file.exists()) {
			try {
				fis = new FileInputStream(file);
				ois = new ObjectInputStream(fis);
				object = ois.readObject();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (ois != null) {
					try {
						ois.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

		return object;
	}

	/**
	 * 创建文件夹
	 * 
	 * @param folderPath
	 * @return
	 */
	public static boolean createFolder(String folderPath) {
		boolean flag = false;
		File folder = new File(folderPath);
		if (!folder.exists()) {
			folder.mkdirs();
			flag = true;
		}
		return flag;
	}

	/**
	 * 将输入流写到文件中
	 * 
	 * @param is
	 * @param filePath
	 * @return
	 */
	public static boolean createFileFromStream(InputStream is, String filePath) {
		boolean flag = false;
		FileOutputStream fos = null;
		File file = new File(filePath);
		if (is != null) {
			try {
				fos = new FileOutputStream(file);
				byte buffer[] = new byte[4 * 1024];
				int index = 0;
				while ((index = is.read(buffer)) != -1) {
					fos.write(buffer, 0, index);
				}
				flag = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return flag;
	}

	/**
	 * 将输入流写到文件中
	 * 
	 * @param is
	 * @param filePath
	 * @return
	 */
	public static boolean createFileFromBytes(byte[] data, String filePath) {
		boolean flag = false;
		FileOutputStream fos = null;
		File file = new File(filePath);
		if (data != null) {
			try {
				fos = new FileOutputStream(file);
				fos.write(data, 0, data.length);
				flag = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return flag;
	}

	/**
	 * 获得指定目录下的所有目录名，不递归
	 * 
	 * @param folderPath
	 * @return
	 */
	public static List<String> getFirstLevelFolderList(String folderPath) {
		List<String> folderList = null;
		File searchFolder = new File(folderPath);
		if (searchFolder != null && !searchFolder.isDirectory()) {
		} else {
			File[] folders = searchFolder.listFiles();
			if (folders != null && folders.length > 0) {
				folderList = new ArrayList<String>();
				for (File file : folders) {
					if (file.isDirectory()) {
						String fileName = file.getName();
						folderList.add(fileName);
					}
				}
			}
		}
		return folderList;
	}

	/**
	 * 文件复制
	 * 
	 * @author lingang
	 * @param sourceFilePath
	 * @param destinationFilePath
	 * @return
	 */
	public static boolean copyFile(String sourceFilePath, String destinationFilePath) {
		boolean flag = false;
		File srcFile = new File(sourceFilePath);
		if (!srcFile.exists()) {
			return flag;
		}
		File destFile = new File(destinationFilePath);
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(srcFile);
			if (!destFile.exists()) {
				if (!destFile.getParentFile().exists()) {
					destFile.getParentFile().mkdirs();
				}
				destFile.createNewFile();
			}
			fos = new FileOutputStream(destFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 复制缓冲区大小为1k
		byte[] bufData = new byte[4 * 1024];
		int readSize = 0;
		try {
			while ((readSize = fis.read(bufData)) > 0) {
				fos.write(bufData, 0, readSize);
			}
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return flag;
	}
	//
	// /**
	// * 读取properties文件
	// *
	// * @param filePath
	// * @return
	// */
	// public static Properties readProperties(String filePath) {
	// Properties props = new Properties();
	// ResourceLoader loader = new DefaultResourceLoader();
	// InputStream stream = null;
	// try {
	// stream = loader.getResource(filePath).getInputStream();
	// props.load(stream);
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// if (stream != null) {
	// stream.close();
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// return props;
	// }

	/**
	 * 读取properties文件，返回Map
	 * 
	 * @param filePath
	 * @return
	 */
	public static Map<String, Object> readPropertiesAsMap(String filePath) {
		Map<String, Object> resultMap = null;

		Properties prop = new Properties();
		File file = new File(filePath);
		InputStream stream = null;
		try {
			stream = new FileInputStream(file);
			prop.load(stream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stream != null) {
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Enumeration<Object> keys = prop.keys();
		if (keys != null) {
			resultMap = new HashMap<String, Object>();
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				resultMap.put(key, prop.getProperty(key));
			}
		}
		return resultMap;
	}

	/**
	 * 资源文件方式读取properties文件
	 * 
	 * 适用于javeee
	 * 
	 * @param filePath
	 * @return
	 */
	public static Properties readPropertiesFromSource(String filePath, Class loader) {
		Properties props = new Properties();
		InputStream stream = null;
		try {
			stream = loader.getResourceAsStream(filePath);
			props.load(stream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stream != null) {
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return props;
	}
	//
	// public static byte[] compressImage(byte[] srcBytes, int width, int
	// height, boolean equalRatio) {
	// InputStream is = null;
	// ByteArrayOutputStream out = null;
	// try {
	// // 获得源文件流
	// is = new ByteArrayInputStream(srcBytes);
	// Image img = ImageIO.read(is);
	// // 判断图片格式是否正确
	// if (img.getWidth(null) == -1) {
	// System.out.println(" can't read,retry!" + "<BR>");
	// return null;
	// } else {
	// int newWidth;
	// int newHeight;
	// // 判断是否是等比缩放
	// if (equalRatio == true) {
	// // 为等比缩放计算输出的图片宽度及高度
	// double rate1 = ((double) img.getWidth(null)) / (double) width + 0.1;
	// double rate2 = ((double) img.getHeight(null)) / (double) height + 0.1;
	// // 根据缩放比率大的进行缩放控制
	// double rate = rate1 > rate2 ? rate1 : rate2;
	// newWidth = (int) (((double) img.getWidth(null)) / rate);
	// newHeight = (int) (((double) img.getHeight(null)) / rate);
	// } else {
	// newWidth = width; // 输出的图片宽度
	// newHeight = height; // 输出的图片高度
	// }
	// BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight,
	// BufferedImage.TYPE_INT_RGB);
	//
	// /*
	// * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
	// */
	// tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight,
	// Image.SCALE_SMOOTH), 0, 0, null);
	// out = new ByteArrayOutputStream();
	//
	// // JPEGImageEncoder可适用于其他图片类型的转换
	// JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	// encoder.encode(tag);
	//
	// return out.toByteArray();
	// }
	// } catch (IOException ex) {
	// ex.printStackTrace();
	// } finally {
	// try {
	// if (out != null) {
	// out.close();
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// return null;
	// }
}