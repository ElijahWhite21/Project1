import java.util.ArrayList;

public class SetOperations {

    /**
     * @param list1
     * @param list2
     * @return
     */
    public static ArrayList<String> union(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> unionList = new ArrayList<>(list1); // create a new list with the elements of the first list
        for (String element : list2) { // iterate through the elements of the second list
            if (!unionList.contains(element)) { // check if the element is not already present in the union list
                unionList.add(element); // add the element to the union list if it is not already present
            }
        }
        return unionList; // return the union list
    }

    /**
     * @param list1
     * @param list2
     * @return
     */
    public static ArrayList<String> intersection(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> intersectionList = new ArrayList<>(); // create a new list to store the intersection of the
                                                                // two lists
        for (String element : list1) { // iterate through the elements of the first list
            if (list2.contains(element)) { // check if the element is present in the second list
                intersectionList.add(element); // add the element to the intersection list if it is present in both
                                               // lists
            }
        }
        return intersectionList; // return the intersection list
    }

    /**
     * @param mainList
     * @param subList
     * @return
     */
    public static ArrayList<String> complement(ArrayList<String> mainList, ArrayList<String> subList) {
        ArrayList<String> complementList = new ArrayList<>(mainList); // create a new list with the elements of the main
                                                                      // list
        for (String element : subList) { // iterate through the elements of the sub list
            if (complementList.contains(element)) { // check if the element is present in the main list
                complementList.remove(element); // remove the element from the complement list if it is present in the
                                                // main list
            }
        }
        return complementList; // return the complement list
    }
}