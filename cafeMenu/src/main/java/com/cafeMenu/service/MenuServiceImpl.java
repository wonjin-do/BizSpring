package com.cafeMenu.service;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cafeMenu.vo.MenuVO;
import com.cafeMenu.vo.Node;
import com.opencsv.CSVReader;

@Service
public class MenuServiceImpl implements MenuService {
	public static final String filePath = "C:\\Users\\Bizspring\\Desktop\\menu.txt";

	@Override
	public List<Node> getnodeList() {
		// TODO Auto-generated method stub
		Map<Integer, Node> nodeMap = new LinkedHashMap<>();
		Node root = new Node(0,"root", null);
		nodeMap.put(0, root);
		fillNodeMap(nodeMap);
		List<Node> nonRecursiveList = new ArrayList<Node>();
		recursive(nonRecursiveList, root, 0);
		return nonRecursiveList;
	}
	
	@Override
	public String getMenu() {
		// TODO Auto-generated method stub
		Map<Integer, Node> nodeMap = new LinkedHashMap<>();
		Node root = new Node(0,"root", null);
		nodeMap.put(0, root);
		fillNodeMap(nodeMap);
		List<Node> nonRecursiveList = new ArrayList<Node>();
		recursive(nonRecursiveList, root, 0);
		StringBuffer res = new StringBuffer();
		
		for(int i=0;i<nonRecursiveList.size();i++) {
			Node elem = nonRecursiveList.get(i);

			if(elem.getChildrenNodes().size() > 0)
				
			res.append("<li>" + elem.getTitle());
			
			
		}
		
		
		
		
		return null;
	}


	public void recursive(List<Node> nonRecursiveList, Node curr, int depth) {
		if(depth != 0) {
			tab(depth);
			System.out.print("<li>" + curr.getTitle());
		}
		nonRecursiveList.add(curr);
		curr.setDepth(depth-1);
		List<Node> childrenList = curr.getChildrenNodes();
		if(childrenList.size() <= 0) {
			return;
		}else {
			for(Node child : childrenList) {
				recursive(nonRecursiveList, child, depth + 1);
			}
		}
	}
	
	private void fillNodeMap(Map<Integer, Node> nodeMap) {
		List<String[]> rows	= readOneFile();
		for(String[] row : rows) {
			//나 자신
			int nodeNum = Integer.parseInt(row[0]);
			int parentNum = Integer.parseInt(row[1]);
			Node parentNode = nodeMap.get(parentNum);
			String title = row[2];
	
			Node me = new Node(nodeNum, title, parentNode);
			nodeMap.put(nodeNum, me);
			
			//나의 부모노드안에서 자식정보 추가
			parentNode.getChildrenNodes().add(me);
		}
	}

	private List<String[]> readOneFile() {
		List<String[]> strSoreList = new ArrayList<String[]>();
        
		try{
			FileInputStream fis = new FileInputStream(filePath);
			InputStreamReader is = new InputStreamReader(fis, "utf-8");
            CSVReader reader = new CSVReader(is,'\t');
			strSoreList = reader.readAll();
			reader.close();
		}catch(Exception e){
            e.printStackTrace();
        }
		return strSoreList;// 리턴타입  배열 [] 로 한 이유. 과목 수는 정해져 있기 때문
	}

	
	
	public static void main(String[] args) {
		MenuService menuService = new MenuServiceImpl();
		List<Node> nodeList = menuService.getnodeList();
		
		
	}

	private static void nonRecursive(List<Node> nodeList) {
		for(Node elem : nodeList) {
			tab(elem.getDepth());
			System.out.println(elem.getTitle());
		}
	}
	
	private static void tab(int depth) {
		for(int i=0;i<depth;i++) {
			System.out.print("\t");
		}
	}
	
	public static void DFS(Node curr, int depth) {
		//System.out.println(curr.getNodeNum() + ", size(): " + curr.getChildrenNodes().size());
		if(depth != 0) {
			tab(depth);
			System.out.print("<li>" + curr.getTitle());
		}
		List<Node> childrenList = curr.getChildrenNodes();
		if(childrenList.size() <= 0) {
			System.out.println("</li>");
			return;
		}else {
			System.out.println("");
			tab(depth+1);
			System.out.println("<ul>");
			
			for(Node child : childrenList) {
				
				DFS(child, depth + 1);
			}
			tab(depth+1);
			System.out.println("</ul>");
			if(depth != 0) {
				tab(depth);
				System.out.println("</li>");	
			}
		}
	}

}
