from queue import Queue

def group_zeroes(arr):
    # maintain where the position of a current non-zero element should be
    # the invariant is that at each index where we perform a swap,
    #we only have either trailing zeroes or the elements we havent touched yet
    zero_idx = 0
    for idx in range(0, len(arr)):
        print  arr[idx], 'in', arr
        if arr[idx] != 0:
            print idx, '<->', zero_idx, '\n'
            arr[idx], arr[zero_idx] = arr[zero_idx], arr[idx]
            zero_idx += 1



def main():
    arr = [1,2, 0, 0, 4, 6, 8, 0, 0, 12, 1]
    group_zeroes(arr)
    print arr

    arr2 = [0, 0, 0, 0, 0, 0, 0, 3, 4]
    group_zeroes(arr2)
    print arr2

if __name__ == '__main__':
    main()
