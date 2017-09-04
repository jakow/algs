"""
Heap test
"""
import unittest
import operator
import random
from heap import Heap


class HeapTest(unittest.TestCase):
    """
    Test Heap implementation
    """

    def test_max(self):
        heap = Heap()
        items = [1, 2, 8, 13, 4, 6, 20, 6, 25]
        for item in items:
            heap.insert(item)

        self.assertEqual(heap.front(), 25)



    def test_len(self):
        """
        Test insert method
        """
        heap = Heap()
        heap.insert(3)
        heap.insert(2)
        heap.insert(1)
        heap.insert(4)
        self.assertEqual(4, len(heap))

    def test_del_front(self):
        heap = Heap()
        heap.insert(1)
        heap.insert(2)
        heap.insert(3)
        heap.insert(5)
        heap.insert(4)
        heap.insert(4)
        heap.insert(0)
        self.assertEqual(5, heap.del_front())
        self.assertEqual(4, heap.del_front())
        self.assertEqual(4, heap.del_front())
        self.assertEqual(3, heap.del_front())
        self.assertEqual(2, heap.del_front())
        self.assertEqual(1, heap.del_front())
        self.assertEqual(0, heap.del_front())


    def test_front_random(self):
        """
        Not really a deterministic test!
        """
        arr = []
        heap = Heap()
        for _ in range(0, 10000):
            rand = random.random()
            arr.append(rand)
            heap.insert(rand)
        self.assertEqual(max(arr), heap.front())

    def test_comparator(self):
        heap = Heap(key=operator.itemgetter(0))
        heap.insert((0, 1))
        heap.insert((1, 1000))
        heap.insert((2, 3000))
        heap.insert((10, 3000))
        heap.insert((15, 3000))
        heap.insert((7, 100000))
        self.assertEqual((15, 3000), heap.front())

    def test_min_heap(self):
        heap = Heap(type='min')
        heap.insert(1)
        heap.insert(2)
        heap.insert(3)
        heap.insert(5)
        heap.insert(4)
        heap.insert(4)
        heap.insert(0)
        self.assertEqual(0, heap.front())


if __name__ == '__main__':
    unittest.main()
