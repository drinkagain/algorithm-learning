package com.jiuxian;

import java.util.*;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-07-08 20:03:05
 * *
 * @comment: 二叉树的遍历
 */
public class BinaryTree {

    private static TreeNode createBinaryTree(LinkedList<Integer> inputList) {
        if (inputList == null || inputList.isEmpty()) {
            return null;
        }
        TreeNode node = null;
        Integer data = inputList.removeFirst();
        if (data != null) {
            node = new TreeNode(data);
            node.leftChild = createBinaryTree(inputList);
            node.rightChild = createBinaryTree(inputList);
        }
        return node;
    }

    /**
     * 前序遍历
     * 顺序：根节点、左子树、右子树
     * @param node
     */
    private static void preOrderTraversal(TreeNode node) {
        if (node == null) return;

        System.out.print(node.data + "\t");
        preOrderTraversal(node.leftChild);
        preOrderTraversal(node.rightChild);
    }

    /**
     * 前序遍历，非栈实现
     * @param root
     */
    private static void preOrderTraversalWithStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                System.out.print(treeNode.data + "\t");
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.rightChild;
            }
        }
    }


    /**
     * 中序遍历
     * 顺序：左子树、根节点、右子树
     * @param node
     */
    private static void inorderTraversal(TreeNode node) {
        if (node == null) return;

        inorderTraversal(node.leftChild);
        System.out.print(node.data + "\t");
        inorderTraversal(node.rightChild);
    }

    /**
     * 后序遍历
     * 顺序：左子树、右子树、根节点
     * @param node
     */
    private static void postOrderTraversal(TreeNode node) {
        if (node == null) return;

        postOrderTraversal(node.leftChild);
        postOrderTraversal(node.rightChild);
        System.out.print(node.data + "\t");
    }


    /**
     * 二叉树为：
     *          3
     *       /   \
     *      2     8
     *    / \      \
     *  9    10     4
     */
    private static void sequenceTraversal(TreeNode node) {
        if (node == null) return;
        LinkedList<StringBuilder>result = new LinkedList<>();
        levelRecursion(node,result,0);
        while (!result.isEmpty()){
            System.out.print(result.removeLast()+"\t");
        }
    }

    private static void levelRecursion(TreeNode node, LinkedList<StringBuilder> stack, int level) {

        if (node == null) return;
        if (stack.size() < level + 1) {
            stack.addFirst(new StringBuilder());
        }
        stack.get(stack.size() - 1 - level).append(node.data).append("\t");

        levelRecursion(node.leftChild, stack, level + 1);
        levelRecursion(node.rightChild, stack, level + 1);
    }

    private static class TreeNode {
        int data;
        TreeNode leftChild;
        TreeNode rightChild;

        TreeNode(int data) {
            this.data = data;
        }
    }


    /**
     * 二叉树为：
     *          3
     *       /   \
     *      2     8
     *    / \      \
     *  9    10     4
     * @param args
     */
    public static void main(String[] args) {
        LinkedList<Integer> inputList = new LinkedList<>(Arrays.asList(3, 2, 9, null, null, 10, null, null, 8, null, 4));
        TreeNode treeNode = createBinaryTree(inputList);

        System.out.print("前序遍历：");
        preOrderTraversal(treeNode);
        System.out.println();

        System.out.print("前序遍历：");
        preOrderTraversalWithStack(treeNode);
        System.out.println();

        System.out.print("中序遍历：");
        inorderTraversal(treeNode);
        System.out.println();

        System.out.print("后序遍历：");
        postOrderTraversal(treeNode);
        System.out.println();

        System.out.print("中序遍历：");
        sequenceTraversal(treeNode);
        System.out.println();
    }
}
