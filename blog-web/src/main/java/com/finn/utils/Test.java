package com.finn.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @description: test
 * @author: Finn
 * @create: 2022/02/06 16:48
 */
public class Test {
    /* 回溯 */
    List<List<Integer>> res;
    List<Integer> path;
    boolean[] visited;

    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        visited = new boolean[nums.length];

        Arrays.sort(nums);
        backtrace(nums, visited);
        return res;
    }

    public void backtrace(int[] nums, boolean[] visited) {
        if(path.size() == nums.length) {
            System.out.println(path);

            res.add(path);
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            if(visited[i])
                continue;
            visited[i] = true;
            path.add(nums[i]);
            backtrace(nums, visited);
            path.remove(path.size()-1);
            visited[i] = false;
        }

        return;
    }

    public static void main(String[] args) {
        Test test = new Test();
        int[] nums = {1, 2, 3};
        test.permute(nums);


    }
}
