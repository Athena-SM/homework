package blog.jsm.cn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 
 * @author cute
 * 
 * 
 *         ʵ�ִ��ļ��ж���Ӣ�����£�ͳ�Ƶ��ʸ���,����ֵ�Ӵ�С���
 */

public class Cp {
	
	
	public static final String SRC_PATH = "F:\\Download\\word.txt";
	public static final String DES_PATH = "F:\\Download\\result.txt";
	
	

	public static void main(String[] args) throws Exception { // ��Щ������д����

		BufferedReader infile = new BufferedReader(new FileReader(SRC_PATH));
		String string;
		StringBuilder sb = new StringBuilder();
		while ((string = infile.readLine()) != null) {
			sb.append(string).append(" ");
		}
		infile.close();
		String file = sb.toString();
		file = file.toLowerCase();
		file = file.replaceAll("[^a-z]", " ").replaceAll("\\n", " ").replaceAll("\\s+", " ");

		Map<String, Integer> wordsCount = new TreeMap<String, Integer>(); // �洢���ʼ�����Ϣ��keyֵΪ���ʣ�valueΪ������

		// ���ʵĴ�Ƶͳ��
		String[] wordsArr1 = file.split("[^a-zA-Z]"); // ���˳�ֻ������ĸ��
		
		
		for (String word : wordsArr1) {
			if (!wordsCount.containsKey(word)) {
				wordsCount.put(word, 0);
			}
			wordsCount.put(word, wordsCount.get(word) + 1);
		}

		ArrayList<Map.Entry<String, Integer>> list = SortMap(wordsCount); // ��ֵ��������
		
		saveResult2Txt(list);
		
		showKWordData(list);

	}
	
	public static void showKWordData(ArrayList<Map.Entry<String, Integer>> list) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Ոݔ��K��");
		int k = scanner.nextInt();
		scanner.close();
		showData(list, k);
		
	}
	
	private static void showData(ArrayList<Map.Entry<String, Integer>> list, int k) {
		for (int i = 0; i < k; i++) {
			System.out.println(list.get(i).getKey() + ":" + list.get(i).getValue());
		}
	}
	
	
	public static void saveResult2Txt(ArrayList<Map.Entry<String, Integer>> list) {
		try {
		File file = new File(DES_PATH);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file);
		for(int i = 0; i < list.size(); i++) {
			String string = list.get(i).getKey() + ":" + list.get(i).getValue() + "\r\n";
			fw.write(string);
		}
		fw.close();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		
	}
	
	
	

	// ��value�Ĵ�С��������
	public static ArrayList<Map.Entry<String, Integer>> SortMap(Map<String, Integer> oldmap) {

		ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldmap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue() - o1.getValue(); // ����
			}
		});

//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).getKey() + ": " + list.get(i).getValue());
//		}
		return list;
	}

}