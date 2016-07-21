package org.txazo.java.collection.compare;

import org.junit.Test;

import java.util.*;

/**
 * Comparable
 * <p>
 * 1) 排序接口
 * 2) 可对数组 List Set Map进行排序
 */
public class ComparableTest {

    @Test
    public void test() {
        Map<Grade, Object> grades = new HashMap<Grade, Object>();
        grades.put(new Grade("张三", 90, 60, 70), new Object());
        grades.put(new Grade("李四", 80, 80, 80), new Object());
        grades.put(new Grade("王五", 99, 99, 99), new Object());
        grades.put(new Grade("赵六", 80, 90, 90), new Object());

        /** 数组排序, Arrays.sort(), 底层调用Comparable.compareTo()排序 */
        Grade[] gradeArray = grades.keySet().toArray(new Grade[0]);
        Arrays.sort(gradeArray);
        for (int i = 0; i < gradeArray.length; i++) {
            System.out.println(gradeArray[i]);
        }

        /** List排序, Collections.sort(), 底层调用Arrays.sort() */
        List<Grade> gradeList = new ArrayList<Grade>(grades.keySet());
        Collections.sort(gradeList);
        for (int i = 0, n = gradeList.size(); i < n; i++) {
            System.out.println(gradeList.get(i));
        }

        /** Set排序, TreeSet, 底层依赖TreeMap */
        Set<Grade> gradeSet = new TreeSet<Grade>(grades.keySet());
        for (Iterator<Grade> i = gradeSet.iterator(); i.hasNext(); ) {
            System.out.println(i.next());
        }

        /** Map排序, TreeMap, 红黑树 */
        Map<Grade, Object> gradeMap = new TreeMap<Grade, Object>(grades);
        for (Iterator<Grade> i = gradeMap.keySet().iterator(); i.hasNext(); ) {
            System.out.println(i.next());
        }
    }

    private class Grade implements Comparable<Grade> {

        private String name;
        private int chinese;
        private int math;
        private int english;

        public Grade(String name, int chinese, int english, int math) {
            this.name = name;
            this.chinese = chinese;
            this.english = english;
            this.math = math;
        }

        private int getTotalGrade() {
            return chinese + math + english;
        }

        @Override
        public int compareTo(Grade g) {
            return g.getTotalGrade() - getTotalGrade();
        }

        @Override
        public String toString() {
            return name + ", 总分: " + getTotalGrade() + ", 语文: " + chinese + ", 数学: " + math + ", 英语: " + english;
        }

    }

}
