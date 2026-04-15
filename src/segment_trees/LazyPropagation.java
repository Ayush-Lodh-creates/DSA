package segment_trees;

/**
 * Lazy Propagation implementation for Range Update and Range Query on Sum
 * Time Complexity: Build O(n), Update O(log n), Query O(log n)
 * Space Complexity: O(n)
 */
public class LazyPropagation {
    // Segment tree and lazy propagation arrays
    // Size is 4*n to safely store all nodes in a complete binary tree
    private long[] st;
    private long[] lazy;
    private int n;

    /**
     * Constructor to initialize the lazy propagation segment tree
     * @param n size of the input array
     */
    public LazyPropagation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Array size must be positive");
        }
        this.n = n;
        st = new long[4 * n];
        lazy = new long[4 * n];
    }

    /**
     * Build the segment tree from the given array
     * @param arr the input array to build the tree from
     */
    public void build(int[] arr) {
        if (arr == null || arr.length != n) {
            throw new IllegalArgumentException("Array size mismatch or null array");
        }
        buildHelper(0, 0, n - 1, arr);
    }

    /**
     * Helper method for building the segment tree (recursive)
     */
    private void buildHelper(int ind, int low, int high, int[] arr) {
        if (low == high) {
            st[ind] = arr[low];
            return;
        }
        int mid = low + (high - low) / 2;
        buildHelper(2 * ind + 1, low, mid, arr);
        buildHelper(2 * ind + 2, mid + 1, high, arr);
        st[ind] = st[2 * ind + 1] + st[2 * ind + 2];
    }

    /**
     * Update elements in range [l, r] by adding val to each element
     * @param l left boundary of the range (inclusive)
     * @param r right boundary of the range (inclusive)
     * @param val value to add to each element in the range
     */
    public void update(int l, int r, int val) {
        if (l < 0 || r >= n || l > r) {
            throw new IllegalArgumentException(
                    "Invalid range: l=" + l + ", r=" + r + ", array size=" + n
            );
        }
        updateHelper(0, 0, n - 1, l, r, val);
    }

    /**
     * Helper method for updating (recursive with lazy propagation)
     */
    private void updateHelper(int ind, int low, int high, int l, int r, int val) {
        // Apply pending lazy value
        if (lazy[ind] != 0) {
            st[ind] += (high - low + 1) * lazy[ind];
            if (low != high) {
                lazy[2 * ind + 1] += lazy[ind];
                lazy[2 * ind + 2] += lazy[ind];
            }
            lazy[ind] = 0;
        }

        // No overlap
        if (high < l || r < low) {
            return;
        }

        // Complete overlap - update node and mark children for lazy propagation
        if (low >= l && high <= r) {
            st[ind] += (high - low + 1) * (long) val;
            if (low != high) {
                lazy[2 * ind + 1] += val;
                lazy[2 * ind + 2] += val;
            }
            return;
        }

        // Partial overlap - recurse on children
        int mid = (low + high) >> 1;
        updateHelper(2 * ind + 1, low, mid, l, r, val);
        updateHelper(2 * ind + 2, mid + 1, high, l, r, val);
        st[ind] = st[2 * ind + 1] + st[2 * ind + 2];
    }

    /**
     * Query the sum of elements in range [l, r]
     * @param l left boundary of the query range (inclusive)
     * @param r right boundary of the query range (inclusive)
     * @return sum of elements in the specified range
     */
    public long query(int l, int r) {
        if (l < 0 || r >= n || l > r) {
            throw new IllegalArgumentException(
                    "Invalid range: l=" + l + ", r=" + r + ", array size=" + n
            );
        }
        return queryHelper(0, 0, n - 1, l, r);
    }

    /**
     * Helper method for querying (recursive with lazy propagation)
     */
    private long queryHelper(int ind, int low, int high, int l, int r) {
        // Apply pending lazy value
        if (lazy[ind] != 0) {
            st[ind] += (high - low + 1) * lazy[ind];
            if (low != high) {
                lazy[2 * ind + 1] += lazy[ind];
                lazy[2 * ind + 2] += lazy[ind];
            }
            lazy[ind] = 0;
        }

        // No overlap
        if (high < l || r < low) {
            return 0;
        }

        // Complete overlap
        if (low >= l && high <= r) {
            return st[ind];
        }

        // Partial overlap - recurse on children
        int mid = (low + high) >> 1;
        long left = queryHelper(2 * ind + 1, low, mid, l, r);
        long right = queryHelper(2 * ind + 2, mid + 1, high, l, r);
        return left + right;
    }
}
