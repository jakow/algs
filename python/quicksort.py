from random import randint

def sort(arr):
    # to guarantee performance
    # shuffle(arr)
    quicksort(arr, 0, len(arr))

def quicksort(arr, start, end):
    if end - start <= 1:
        return
    pivot = median(arr, start, (start + end - 1)/2, end - 1)
    less, more = partition3way(arr, pivot, start, end)
    quicksort(arr, start, less)
    quicksort(arr, more, end)

def median(arr, left, mid, right):
    if arr[left] < arr[mid]: # left < mid
        if arr[mid] < arr[right]: # left < mid < right
            return mid
        else: # left < mid && right < mid
            return left if arr[left] > arr[right] else right
    else: # left > mid
        if arr[mid] > arr[right]: # right < mid < left
            return mid
        else: # left > mid && right > mid
            return left if arr[left] < arr[right] else right

def partition(arr, pivot, start, end):
    if pivot != start:
        arr[pivot], arr[start] = arr[start], arr[pivot]
        pivot = start
    i = start + 1
    for j in range(start + 1, end):
        if arr[j] < arr[pivot]:
            arr[j], arr[i] = arr[i], arr[j]
            i += 1

    # put pivot in place
    arr[pivot], arr[i - 1] = arr[i - 1], arr[pivot]
    return i - 1 # and return the place of the pivot

def partition3way(arr, pivot, start, end):
    # first element equal to the pivot, giving the upper boundary on elements less than the pivot
    less = start
    i = start
    # first element more than the pivot
    more = end
    pivot = arr[pivot]
    while i < more:
        if arr[i] < pivot:
            arr[i], arr[less] = arr[less], arr[i]
            less += 1
            i += 1
        elif arr[i] > pivot:
            more -= 1
            arr[i], arr[more] = arr[more], arr[i]

        else:
            i += 1
    return less, more


def shuffle(arr):
    """
    Fisher-Yates shuffle.
    """
    length = len(arr)
    for i in range(0, length - 1):
        j = randint(i, length - 1)
        arr[i], arr[j] = arr[j], arr[i]

def quicksort_pivot_1st(arr, start, end):
    if end - start <= 1:
        return 0
    pivot = start
    middle = partition(arr, pivot, start, end)
    number_of_comparisons = end - start - 1
    number_of_comparisons += quicksort_pivot_1st(arr, start, middle)
    number_of_comparisons += quicksort_pivot_1st(arr, middle + 1, end)
    return number_of_comparisons

def quicksort_pivot_last(arr, start, end):
    if end - start <= 1:
        return 0
    pivot = end - 1
    middle = partition(arr, pivot, start, end)
    number_of_comparisons = end - start - 1
    number_of_comparisons += quicksort_pivot_last(arr, start, middle)
    number_of_comparisons += quicksort_pivot_last(arr, middle + 1, end)
    return number_of_comparisons


def quicksort_pivot_median3(arr, start, end):
    if end - start <= 1:
        return 0
    pivot = median(arr, start, (start + end - 1) / 2, end - 1)
    middle = partition(arr, pivot, start, end)
    number_of_comparisons = end - start - 1
    number_of_comparisons += quicksort_pivot_median3(arr, start, middle)
    number_of_comparisons += quicksort_pivot_median3(arr, middle + 1, end)
    return number_of_comparisons

def main():
    arr = [3, 8, 2, 5, 1, 4, 7]
    partition(arr, 0, 0, len(arr))
    sort(arr)
    print arr

    arr = [2, 4, 8, 1, 0,4,4,4, 5, 6 ,4, 7]
    partition3way(arr, 1, 0, len(arr))
    print arr
    sort(arr)
    print arr

    arr = ['c', 'x', 'd', 'b', 'a', 'z', 'f']
    sort(arr)
    print arr

    arr = []
    sort(arr)

    arr = [1]
    sort(arr)
    print arr

    arr = [2, 1]
    sort(arr)
    print arr

    with open('QuickSort.txt', 'r') as f:
        arr = [float(line) for line in f.readlines()]
        arr1 = arr[:]
        print quicksort_pivot_1st(arr1, 0, len(arr1))
        arr2 = arr[:]
        print quicksort_pivot_last(arr2, 0, len(arr2))
        arr3 = arr[:]
        print quicksort_pivot_median3(arr3, 0, len(arr3))



if __name__ == '__main__':
    main()
