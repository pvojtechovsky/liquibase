package liquibase.util;

import java.util.*;

public class CollectionUtil {

    public static List<Map<String, ?>> createSingleItemList(String key, Object value) {
        ArrayList<Map<String, ?>> returnList = new ArrayList<Map<String, ?>>();
        HashMap<String, Object> row = new HashMap<String, Object>();
        row.put(key, value);
        returnList.add(row);
        return returnList;

    }

    public static <T> Set<Set<T>> powerSet(Collection<T> originalSet) {
        Set<Set<T>> sets = new HashSet<Set<T>>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<T>());
            return sets;
        }
        List<T> list = new ArrayList<T>(originalSet);
        T head = list.get(0);
        Collection<T> rest = list.subList(1, list.size());
        for (Set<T> set : powerSet(rest)) {
            Set<T> newSet = new HashSet<T>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }

    public static <T> List<Map<String, T>> permutations(Map<String, List<T>> parameterValues) {
        List<Map<String, T>> list = new ArrayList<Map<String, T>>();
        if (parameterValues == null || parameterValues.size() == 0) {
            return list;
        }

        permute(new HashMap<String, T>(), new ArrayList<String>(parameterValues.keySet()), parameterValues, list);

        return list;


    }

    private static <T> void permute(Map<String, T> basePermutation, List<String> remainingKeys, Map<String, List<T>> parameterValues, List<Map<String, T>> returnList) {

        String thisKey = remainingKeys.get(0);
        remainingKeys = remainingKeys.subList(1, remainingKeys.size());
        for (T value : parameterValues.get(thisKey)) {
            Map<String, T> permutation = new HashMap<String, T>(basePermutation);

            permutation.put(thisKey, value);

            if (remainingKeys.size() == 0) {
                returnList.add(permutation);
            } else {
                permute(permutation, remainingKeys, parameterValues, returnList);
            }
        }
    }

}
