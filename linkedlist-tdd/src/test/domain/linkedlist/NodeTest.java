package test.domain.linkedlist;


import main.domain.Node;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {

    @Test
    @DisplayName("Node 1개로 기본기능 테스트")
    public void testNode(){
        int data1= 1234;
        Node node1 = new Node(data1);
        assertEquals(data1, node1.getData());
        assertFalse(node1.hasNext());
    }

    @Test
    @DisplayName("Node 여러개로 기본 기능 테스트")
    public void testNodes(){
        int data1= 1234;
        Node node1 = new Node(data1);
        int data2 = 5678;
        Node node2 = new Node(data2);
        //node1->node2 로 가리키게 세팅
        node1.setNext(node2);
        assertTrue(node1.hasNext());
        assertEquals(node2, node1.getNext());
        //node1 - node2 가리키는 것 제거
        node1.deleteNext();
        assertFalse(node1.hasNext());
    }
}