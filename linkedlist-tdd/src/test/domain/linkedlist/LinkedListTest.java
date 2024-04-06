package test.domain.linkedlist;


import main.domain.LinkedList;
import main.domain.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * 1.assertJ대신 JUnit5 썼습니다(External Libraries에 jar 파일이 더 추가될 시 용량 증가.... (답안 제출 : 10MB 제한)
 * 2.테스트 내용은 displayName을 봐주세요..
 */
public class LinkedListTest {

    private final String whenLengthIsZero = "empty"; //from testTarget class

    @Test
    @DisplayName("2-1.1)연결 리스트의 마지막에 새로운 노드를 추가")
    public void insertNodeToListAtEnd() {
        Node newNode = new Node(100);
        LinkedList list = getListSet1to5();
        list.insertNodeAtEnd(newNode);
        assertEquals("1-2-3-4-5-" + newNode.getData(), list.getAllDataByString());
    }

    @Test
    @DisplayName("2-1.1)비어있는 연결 리스트의 마지막에 새로운 노드를 추가")
    public void insertNodeToEmptyListAtEnd() {
        Node newNode = new Node(100);
        LinkedList emptyList = new LinkedList();
        emptyList.insertNodeAtEnd(newNode);
        assertEquals(newNode.getData(), Integer.parseInt(emptyList.getAllDataByString()));
    }

    @Test
    @DisplayName("2-1.2)연결 리스트의 마지막 노드를 삭제")
    public void deleteNodeToListAtEnd() {
        LinkedList list = getListSet1to5();
        list.deleteNodeAtEnd();
        assertEquals("1-2-3-4", list.getAllDataByString());
    }

    @Test
    @DisplayName("2-1.2)비어있는 연결 리스트의 마지막 노드는 삭제 불가능(예외발생!!!)")
    public void deleteNodeToEmptyListAtEnd() {
        LinkedList emptyList = new LinkedList();
        Assertions.assertThrows(NullPointerException.class, emptyList::deleteNodeAtEnd);
    }

    @Test
    @DisplayName("2-2.1)연결 리스트의 모든 요소를 출력 (원소가 있는 리스트, 비어있는 리스트)")
    public void checkAllDataByDefault() {
        LinkedList list = getListSet1to5(); //원소 있음
        assertEquals("1-2-3-4-5", list.getAllDataByString());
        LinkedList emptyList = new LinkedList(); // 빈 리스트
        assertEquals(whenLengthIsZero, emptyList.getAllDataByString());
    }

    @Test
    @DisplayName("2-2.2) 연결 리스트의 모든 요소를 역순으로 출력 (원소가 있는 리스트, 비어있는 리스트)")
    public void checkAllDataByReverseDirection() {
        LinkedList list = getListSet1to5(); //원소 있음
        assertEquals("5-4-3-2-1", list.getAllDataByStringReverse());
        LinkedList emptyList = new LinkedList(); // 빈 리스트
        assertEquals(whenLengthIsZero, emptyList.getAllDataByStringReverse());
    }

    @Test
    @DisplayName("2-3. 0)연결 리스트의 N번째에... 범위 밖의 N을 사용 불가능 (예외발생!!!)")
    public void insertNewToNthWithOutOfBound() {
        LinkedList list = getListSet1to5();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.insertNodeAtIndex(10, new Node(10)); //해당범위에 연결된 Node 없음
        });
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.deleteNodeAtIndex(10);
        });

        LinkedList emptyList = new LinkedList();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            emptyList.insertNodeAtIndex(1, new Node(10));
            ; //해당범위에 연결된 Node 없음
        });
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            emptyList.deleteNodeAtIndex(1);
        });
    }

    @Test
    @DisplayName("2-3. 1)연결 리스트의 N번째에 새로운 노드를 추가(※N은 0부터 시작)")
    public void insertNewToNth() {
        LinkedList list = getListSet1to5();
        list.insertNodeAtIndex(3, new Node(10));
        assertEquals("1-2-3-4-10-5", list.getAllDataByString());
        list.insertNodeAtIndex(0, new Node(100));
        assertEquals("100-1-2-3-4-10-5", list.getAllDataByString());
        int endNextIndex = list.getLength() + 1;
        list.insertNodeAtIndex(endNextIndex, new Node(1234));
        assertEquals("100-1-2-3-4-10-5-1234", list.getAllDataByString());

        LinkedList emptyList = new LinkedList();
        emptyList.insertNodeAtIndex(0, new Node(10));
        assertEquals("10", emptyList.getAllDataByString());
    }

    @Test
    @DisplayName("2-3. 2)연결 리스트의 N번째 노드를 삭제(※N은 0부터 시작)")
    public void deleteNth() {
        LinkedList list = getListSet1to5();
        list.deleteNodeAtIndex(3);
        assertEquals("1-2-3-5", list.getAllDataByString());
        list.deleteNodeAtIndex(0);
        assertEquals("2-3-5", list.getAllDataByString());
        list.deleteNodeAtIndex(2);
        assertEquals("2-3", list.getAllDataByString());
        //emptyList는 테스트하지않음.
    }


    /**
     * 테스트 조건 준비용
     *
     * @return 1부터 5까지를 데이터로 가진 연결리스트 반환
     */
    private LinkedList getListSet1to5() {
        LinkedList list = new LinkedList();
        for (int i = 1; i <= 5; i++) {
            list.insertNodeAtEnd(new Node(i));
        }
        return list;
    }
}