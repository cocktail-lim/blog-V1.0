package com.finn.utils;

import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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

class Test1{
    int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};

    public int[][] reconstructQueue(int[][] people) {
        List<int[]> list = new LinkedList<>();
        Arrays.sort(people, (a, b) -> {
            if (a[0] != b[0]) return b[0] - a[0];
            else
                return b[1] - b[0];
        });

        for (int[] peo : people) {
            list.add(peo);
        }
        return list.toArray(new int[0][]);
    }
}