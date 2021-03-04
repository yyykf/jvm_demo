package cn.ykf.oom;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Heap内存溢出
 *
 * @author YuKaiFan <1092882580@qq.com>
 * @date 2021/3/4
 */
public class HeadOom {

    public static void main(String[] args) {

        List<List<Integer>> subLists = new ArrayList<>();

        IntStream.rangeClosed(1, 1000).forEach(i -> {
            // 每次循环生成一个1000个元素的父列表
            List<Integer> parentList = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toList());

            // 每次循环从父列表中截取一个元素的子列表
            // 由于子列表中强引用了父列表，所以导致父列表无法被GC，内存泄漏，堆积最终造成OOM
            subLists.add(parentList.subList(0, 1));
        });
    }
}
