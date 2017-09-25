import operator

class Heap(object):
    """
    Heap based priority queue. Supports min and max queues
    and arbitrary comparison function
    """
    def __init__(self, **kwargs):
        # max pq by default
        self.compare = operator.lt
        if 'type' in kwargs:
            type = kwargs['type']
            # can be also a min PQ
            if type == 'min':
                self.compare = operator.gt
            # but the attribute cannot be an arbitrary string
            elif type != 'max':
                raise Exception('Invalid heap type, it must be "max" or "min"')

        if 'key' in kwargs:
            self.get_key = kwargs['key'] 
        else:
            self.get_key = self._identity
            
        self.arr = [None]
        self.length = 0

    def __len__(self):
        return self.length

    def __repr__(self):
        return 'Heap ' + str(self.arr)
    
    def __nonzero__(self):
        return self.length

    def is_empty(self):
        return self.length == 0
    
    def del_front(self):
        """
        Remove and return the front element of the heap queue, that is:
          - The largest element of a max heap queue
          - The smallest element of a min heap queue
        """
        return self.del_idx(1)

    def del_idx(self, idx):
        """
        Remove item at a given index of the heap queue array
        """
        if idx > self.length:
            raise Exception('No such element')
        self._swap(idx, self.length)
        self.length = self.length - 1
        value = self.arr.pop()

        # if we are at root or the idx's parent is larger than idx,
        # we sink the new (recently swapped) value
        if idx == 1 or self._less(idx, idx / 2):
            self._sink(idx)
        # if idx is larger than its parent, we restore heap order 
        # up the tree
        else:
            self._swim(idx)
        return value

    def front(self):
        """
        Peek at the front element of the heap queue, that is:
          - The largest element of a max heap queue
          - The smallest element of a min heap queue
        """
        if not self.length:
            raise Exception('No such element')
        return self.arr[1]
    
    def insert(self, item):
        """
        Insert an item into this heap.
        """
        self.arr.append(item)
        self.length = self.length + 1
        #reheapify
        self._swim(self.length)
        # increment

    # private methods



    def _sink(self, idx):
        """
        From Alorithms 4th Edition:
        If the heap order is violated because a node's key becomes smaller than
        one or both of that node's children's keys, then we can make progress 
        toward fixing the violation by exchanging the node with the LARGER 
        of its two children (if two children exist). This switch may cause 
        a violation at the child; we fix that violation in the same way, 
        and so forth, moving down the heap until we reach a node with both
        children smaller, or the bottom.
        """
        while 2 * idx <= self.length:
            # choose left child
            child_idx = 2 * idx
            # if right child is larger than left child, choose right child
            if child_idx < self.length and self._less(child_idx, child_idx + 1):
                child_idx = child_idx + 1
            # if the sinking element is not smaller than the child, sinking stops
            if not self._less(idx, child_idx):
                break
            self._swap(child_idx, idx)
            idx = child_idx
            
    def _swim(self, idx):
        """
        From Algorithms 4th Edition:
        If the heap order is violated because a node's key becomes larger than
        that node's parents key, then we can make progress toward fixing
        the violation by exchanging the node with its parent.
        After the exchange, the node is larger than both its children
        (one is the old parent, and the other is smaller than the old parent
        because it was a child of that node) but the node may still be larger than
        its parent. We can fix that violation in the same way, and so forth,
        moving up the heap until we reach a node with a larger key, or the root.
        """
        while idx > 1 and self._less(idx/2, idx):
            self._swap(idx/2, idx)
            idx = idx / 2


    def _less(self, idx_a, idx_b):
        return self.compare(
            self.get_key(self.arr[idx_a]),
            self.get_key(self.arr[idx_b])
            )

    def _swap(self, idx_a, idx_b):
        self.arr[idx_a], self.arr[idx_b] = self.arr[idx_b], self.arr[idx_a]

    def _identity(self, val):
        return val
