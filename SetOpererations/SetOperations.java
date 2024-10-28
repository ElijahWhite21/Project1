import java.util.ArrayList;

public class SetOperations {
    public static ArrayList<String> union(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> unionList = new ArrayList<>(list1);
        for (String element : list2) {
            if (!unionList.contains(element)) {
                unionList.add(element);
            }
        }
        return unionList;
    }

    public static ArrayList<String> intersection(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> intersectionList = new ArrayList<>();
        for (String element : list1) {
            if (list2.contains(element)) {
                intersectionList.add(element);
            }
        }
        return intersectionList;
    }

    public static ArrayList<String> complement(ArrayList<String> mainList, ArrayList<String> subList) {
        ArrayList<String> complementList = new ArrayList<>(mainList);
        for (String element : subList) {
            if (complementList.contains(element)) {
                complementList.remove(element);
            }
        }
        return complementList;
    }
}