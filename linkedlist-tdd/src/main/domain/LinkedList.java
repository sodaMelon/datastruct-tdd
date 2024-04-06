package main.domain;

public class LinkedList {

    private int length;
    private Node head;

    private final int firstIndex = 0;
    private final int handledSingleNodeValue = 1;
    private final String whenLengthIsZero = "empty";
    public LinkedList() {
        this.length = 0;
        this.head = null; //init: empty (has no element)
    }

    public int getLength() {
        return length;
    }

    /**
     * 연결리스트 길이 증가. (삽입, append 등으로 원소길이가 증가했을때 사용
     */
    private void increaseLength(int value) {
        this.length+=value;
    }

    /**
     * 연결리스트 길이 감소 (삭제, append 등으로 원소길이가 증가했을때 사용
     */
    private void decreaseLength(int value) {

        this.length-=value;
    }

    /**
     * 2-1.1) 신규 Node 삽입 (마지막에 삽입)
     *
     * @param newNode 신규 Node
     */
    public void insertNodeAtEnd(Node newNode) {
        if (head == null) {
            this.head = newNode;
        } else { // head 노드가 null이 아닌 경우 temp 노드가 head를 참조.
            Node tempNode = head; // tempNode는 마지막 노드를 찾아서 참조하기 위해 사용.
            while (tempNode.hasNext()) {
                tempNode = tempNode.getNext();// tempNode.next는 다음 노드를 참조하고 있으므로,
                // while문이 모두 실행되면 tempNode는 가장 마지막 노드를 참조하게 됨.
            }
            tempNode.setNext(newNode);// tempNode(마지막 노드)의 next가 newNode를 참조하도록 함.
            increaseLength(handledSingleNodeValue);
        }
    }

    /**
     * 2-1.2)마지막에 위치한 Node 삭제
     */
    public void deleteNodeAtEnd() {
        if (head == null) {
            throw new NullPointerException();
        }
        if (length == 1) { //head만 있으면 (head.hasNext==false)
            head = null;
            return;
        }
        Node tempNode = head;
        Node prevNode = null;
        while (tempNode.hasNext()) {
            prevNode = tempNode;
            tempNode = tempNode.getNext();    // 다음 노드를 참조
        }//루프 밖에서 벗어나면 prevNode(마지막바로앞)-tempNode(마지막)
        prevNode.deleteNext(); //temp로 가는 경로 지워지므로 자유로워짐..
        decreaseLength(handledSingleNodeValue);
    }


    /**
     * 2-2.1)연결 리스트의 모든 요소를 출력하는 메소드
     */
    public void printAllData() {
        System.out.println(getAllDataByString());
        System.out.println("listLength:" + length);
    }

    /**
     * 2-2.2)연결 리스트의 거꾸로 요소를 출력하는 메소드
     */
    public void printAllDataReverse() {
        System.out.println(getAllDataByStringReverse());
        System.out.println("listLength:" + length);
    }

    public String getAllDataByStringReverse() {
        StringBuilder sb = new StringBuilder(getAllDataByString());
        if (this.length == 0) {
            return whenLengthIsZero;
        }
        return (sb.reverse().toString());
        //todo 구성요소 재구성해서 reverse 해야할 경우, 다른 메서드를 사용한다. (추가 구현해야함)
    }

    /**
     *
     * @return "1-2-...-5"의 형태, 리스트 내부의 Node가 갖고 있는 data들을 순서대로 붙인 String (구분기호 "-")
     */
    public String getAllDataByString() {
        if (head == null) {
            return whenLengthIsZero;
        }
        StringBuilder sb = new StringBuilder(""); //(멀티스레드환경 예정없으므로)스트링빌더사용
        Node cursorNode = this.head;
        while (cursorNode != null) { //null이 아닐 때까지 반복하여 출력
            sb.append(cursorNode.getData());
            cursorNode = cursorNode.getNext();
            if (cursorNode != null) sb.append("-");
        }
        return sb.toString();
    }

    /**
     * 2-3.1) 신규 Node N번째 인덱스에 삽입 (※N은 0부터 시작)
     *
     * @param targetIndex N번째 인덱스
     * @param newNode     신규 Node
     */
    public void insertNodeAtIndex(int targetIndex, Node newNode) {
        if (targetIndex > length) {
            if (head!=null && targetIndex-length == 1) {
                insertNodeAtEnd(newNode);
                return;
            }
            throw new IndexOutOfBoundsException();
        }
        if (targetIndex == firstIndex) {
            newNode.setNext(head);
            head = newNode;
            return;
        }

        Node currentNode = head;
        for (int i = firstIndex; i < length; i++) {
            if (i == targetIndex) {
                Node tempNode = currentNode.getNext(); //다음가리키고 있는걸 임시로 세팅
                currentNode.setNext(newNode);
                newNode.setNext(tempNode);
            }
            currentNode = currentNode.getNext();
        }
        increaseLength(handledSingleNodeValue);
    }

    /**
     * 2-3.2) N번째 인덱스에 위치한 노드 삭제(※N은 0부터 시작)
     *
     * @param targetIndex N번째 인덱스
     */
    public void deleteNodeAtIndex(int targetIndex) {
        if (targetIndex > length) {
            throw new IndexOutOfBoundsException();
        }
        if (targetIndex == firstIndex) {
            head = head.getNext();
            return;
        }

        Node currentNode = head;
        Node previousNode = null;
        for (int i = firstIndex; i < length; i++) {
            if (i == targetIndex) {
                Node nextNode = currentNode.getNext();
                previousNode.setNext(nextNode);//이전노드에 다음에 가리키는 값 저장
                break;
            }
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }
        decreaseLength(handledSingleNodeValue);
    }


}
