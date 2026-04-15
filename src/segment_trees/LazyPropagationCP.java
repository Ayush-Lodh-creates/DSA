package segment_trees;

import java.util.List;

public class LazyPropagationCP {
    int[] st, lazy;

    public LazyPropagationCP(int n) {
        st = new int[4*n];
        lazy = new int[4*n];
    }

    public void build(int ind, int low, int high, int[] arr) {
        if(low == high) {
            st[ind] = arr[low];
            return;
        }
        int mid = low + (high - low) / 2;
        build(2*ind+1, low, mid, arr);
        build(2*ind+2, mid+1, high, arr);
        st[ind] = st[2*ind+1] + st[2*ind+2];
    }

    public void update(int ind, int low, int high, int l, int r, int val) {
        if(lazy[ind] != 0) {
            st[ind] += (high - low + 1) * lazy[ind];
            if(low != high) {
                lazy[2*ind+1] += lazy[ind];
                lazy[2*ind+2] += lazy[ind];
            }
            lazy[ind] = 0;
        }
        if(high < l || r < low) {
            return;
        }
        if(low >= l && high <= r) {
            st[ind] += (high - low + 1) * val;
            if(low != high) {
                lazy[2*ind+1] += val;
                lazy[2*ind+2] += val;
            }
            return;
        }
        int mid = (low + high) >> 1;
        update(2*ind+1, low, mid, l, r, val);
        update(2*ind+2, mid+1, high, l, r, val);
        st[ind] = st[2*ind+1] + st[2*ind+2];
    }

    public int query(int ind, int low, int high, int l, int r) {
        if(lazy[ind] != 0) {
            st[ind] += (high - low + 1) * lazy[ind];
            if(low != high) {
                lazy[2*ind+1] += lazy[ind];
                lazy[2*ind+2] += lazy[ind];
            }
            lazy[ind] = 0;
        }
        if(high < l || r < low) {
            return 0;
        }
        if(low >= l && high <= r) return st[ind];
        int mid = (low + high) >> 1;
        int left = query(2*ind+1, low, mid, l, r);
        int right = query(2*ind+2, mid+1, high, l, r);
        return left + right;
    }
}