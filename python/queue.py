class Queue(object):
    """
    FIFO queue based on linked list. Because python only has threadsafe locking queues
    which incur a penalty
    """
    def __init__(self):
        self.head = None
        self.tail = None
        self.len = 0

    def __len__(self):
        return self.len

    def __nonzero__(self):
        return not self.empty()

    def __repr__(self):
        items = []
        node = self.head
        while node is not None:
            items.append(node.item)
            node = node.next
        return "Queue <- " + str(items)

    def empty(self):
        return self.len == 0

    def enqueue(self, item):
        """
        add an item to the back of the queue
        """
        new_node = Node(item)
        if self.head is None:
            self.head = new_node
            self.tail = new_node
        else: # if has a head then has a tail
            self.tail.next = new_node
            self.tail = new_node
        self.len = self.len + 1

    def dequeue(self):
        """
        remove item from the front of the queue
        """
        if self.head is None:
            raise Exception('Queue empty')
        node = self.head
        self.head = node.next
        if self.head is None: # queue is empty
            self.tail = None
        self.len = self.len - 1
        return node.item

class Node(object):
    """
    Singly-linked list node
    """
    def __init__(self, item):
        self.item = item
        self.next = None

def main():
    """
    Test queue
    """
    q = Queue()
    q.enqueue(2)
    q.enqueue(3)
    q.enqueue(1)
    print "len is {}".format(len(q))
    print q.dequeue()
    print q.dequeue()
    print q.dequeue()
    q.enqueue(2)
    q.enqueue(3)
    print q

if __name__ == '__main__':
    main()
