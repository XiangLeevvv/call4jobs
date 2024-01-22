、、、java
import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {

    public static void main(String[] args) {
        // 使用LinkedList实现Queue
        Queue<String> queue = new LinkedList<>();

        // 添加元素到队列
        queue.offer("A");
        queue.offer("B");
        queue.offer("C");

        System.out.println("Queue elements: " + queue);

        // 检查队首元素（不移除）
        String peekElement = queue.peek();
        System.out.println("Peek element: " + peekElement);

        // 检查并移除队首元素
        String pollElement = queue.poll();
        System.out.println("Polled element: " + pollElement);
        System.out.println("Updated Queue elements: " + queue);

        // 添加更多元素
        queue.offer("D");
        queue.offer("E");

        // 遍历队列并处理每个元素
        System.out.println("Iterating through the Queue:");
        while (!queue.isEmpty()) {
            String element = queue.poll();
            System.out.println("Processed element: " + element);
        }
    }
}
、、、

LinkedList:

LinkedList 实现了基本的双向链表数据结构，因此它不仅可以用作队列，还可以用作列表（List）和双端队列（Deque）。
添加和删除元素的开销较小，适用于频繁的插入和删除操作。
不支持快速的随机访问元素，访问某个特定位置的元素可能需要遍历链表。
PriorityQueue:

PriorityQueue 是一个基于优先级堆的实现，它对元素进行排序并保证在出队时返回最小（或最大）元素。
适用于需要按照特定顺序处理元素的场景。
不保证元素的顺序，只保证最小（或最大）元素优先出队。
ArrayDeque:

ArrayDeque 实现了一个基于数组的双端队列，可以在队列的两端进行高效的插入和删除操作。
在没有容量限制的情况下，它可以根据需要动态扩展。
适用于需要在队列两端执行操作的场景，性能较好。
选择使用哪种实现类取决于具体的需求和使用场景：

如果需要在队列两端执行高效的插入和删除操作，可以选择 ArrayDeque。
如果需要按照元素的优先级进行处理，可以选择 PriorityQueue。
如果需要同时具备列表和双端队列的特性，并且对于插入和删除操作的性能要求较高，可以选择 LinkedList。
