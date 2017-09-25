"""
In place sort using heap operations
"""
import operator

def sort(arr, **kwargs):
    # setup comparator function
    compare = kwargs['compare'] if 'compare' in kwargs else operator.lt
    key = kwargs['key'] if 'key' in kwargs else None
    less = _build_less(compare, key)
    length = len(arr)
    # build up heapsort - use start with the parent of the last child
    # beacuse there is not point sinking leaf nodes of the heap - they are already at the bottom
    for idx in range(_parent(length - 1), -1, -1):
        _sink(arr, idx, length, less)

    while length > 1:
        length = length - 1
        _swap(arr, 0, length)
        _sink(arr, 0, length, less)
    
    return arr



def _build_less(compare, key):
    if key is not None:
        def less(left, right):
            return compare(key(left), key(right))
    else:
        def less(left, right):
            return compare(left, right)
    return less

def _delMax(arr, idx, length, less):
    _swap(arr, 0, length - 1)
    _sink(arr, 0, length - 1, less)

def _swim(arr, idx, less):
    while idx > 0 and not less(arr[idx], arr[_parent(idx)]):
        _swap(arr, idx, _parent(idx))
        idx = _parent(idx)

def _sink(arr, idx, length, less):
    while _left_child(idx) < length:
        child = _left_child(idx)
        # if right child exists and is bigger, use it instead
        if child < length - 1 and less(arr[child], arr[child + 1]):
            child = child + 1 # right child
        if not less(arr[idx], arr[child]):
            break
        _swap(arr, child, idx)
        idx = child

def _swap(arr, idx1, idx2):
    arr[idx1], arr[idx2] = arr[idx2], arr[idx1]

def _parent(idx):
    return (idx - 1) // 2

def _left_child(idx):
    return 2 * idx + 1

# [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

# #children 
# 0 -> 1, 2
# 1 -> 3, 4
# 2 -> 5, 6
# 3 -> 7, 8





