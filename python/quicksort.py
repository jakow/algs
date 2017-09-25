from random import randint

def sort(arr):
    # to guarantee performance
    shuffle(arr)
    quicksort(arr, 0, len(arr))

def quicksort(arr, start, end):
    if end - start <= 1:
        return
    pivot = randint(start, end - 1)
    less, more = partition3way(arr, pivot, start, end)
    quicksort(arr, start, less)
    quicksort(arr, more, end)



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
    l = len(arr)
    for i in range(0, l):
        j = randint(i, l - 1)
        arr[i], arr[j] = arr[j], arr[i]

def main():
    arr = [3, 8, 2, 5, 1, 4, 7, 6]
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




if __name__ == '__main__':
    main()
