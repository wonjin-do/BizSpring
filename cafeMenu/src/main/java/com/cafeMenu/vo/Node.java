package com.cafeMenu.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class Node {
	private int nodeNum;
	private String title;
	
	private Node parentNode;
	private List<Node> childrenNodes = new ArrayList<Node>();
	
	private int depth;
	
	public Node(int nodeNum, String title, Node parentNode) {
		super();
		this.nodeNum = nodeNum;
		this.title = title;
		this.parentNode = parentNode;
	}

	public int getNodeNum() {
		return nodeNum;
	}

	public void setNodeNum(int nodeNum) {
		this.nodeNum = nodeNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	public List<Node> getChildrenNodes() {
		return childrenNodes;
	}

	public void setChildrenNodes(List<Node> childrenNodes) {
		this.childrenNodes = childrenNodes;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	
	
	
}