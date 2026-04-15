package segment_trees;

public class SegmentTree {

    private int[] seg;

    SegmentTree(int n) {
        seg = new int[4*n+1];
    }

    public void build(int ind, int low, int high, int[] arr) {
        if(low == high) {
            seg[ind] = arr[low];
            return;
        }

        int mid = low + (high - low) / 2;
        build(2*ind+1, low, mid, arr);
        build(2*ind+2, mid+1, high, arr);
        seg[ind] = Math.min(seg[2*ind+1], seg[2*ind+2]);
    }

    public int query(int ind, int low, int high, int l, int r) {
        if(r < low || high < l) return Integer.MAX_VALUE;
        if(low >= l && high <= r) return seg[ind];

        int mid = low + (high - low) / 2;
        int left = query(2*ind+1, low, mid, l, r);
        int right = query(2*ind+2, mid+1, high, l, r);
        return Math.min(left, right);
    }

    public void update(int ind, int low, int high, int i, int val) {
        if(low == high) {
            seg[ind] = val;
            return;
        }

        int mid = low + (high - low) / 2;
        if(i <= mid) {
            update(2*ind+1, low, mid, i, val);
        } else {
            update(2*ind+2, mid+1, high, i, val);
        }
        seg[ind] = Math.min(seg[2*ind+1], seg[2*ind+2]);
    }
}
