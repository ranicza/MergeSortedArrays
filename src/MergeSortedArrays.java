import java.util.*;

/**
 * Merge several integer sorted arrays into one sorted array.
 * <p>
 * Created by ranicza on 26.11.2017.
 */
public class MergeSortedArrays {

    public static class Item {
        int value;
        int x; // very informative name :)
        int y; // very informative name :)

        public Item(int value, int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
        }
    }

    public static List<Integer> merge(List<List<Integer>> lists) {
        List<Integer> result = new ArrayList<Integer>();

        if (lists == null || lists.isEmpty()) {
            return result;
        }

        PriorityQueue<Item> queue = new PriorityQueue<Item>(lists.size(), new Comparator<Item>() {
            public int compare(Item a, Item b) {
                return a.value - b.value;
            }
        });

        // init the queue - from each array insert the minimum value
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).size() != 0) {
                queue.offer(new Item(lists.get(i).get(0), i, 0));
            }
        }

        Item item;

        while (!queue.isEmpty()) {
            item = queue.poll();
            result.add(item.value);

            if (item.y < lists.get(item.x).size() - 1) {
                queue.offer(new Item(lists.get(item.x).get(item.y + 1), item.x, item.y + 1));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{-1, 0, 1};
        Integer[] b = new Integer[]{-100, 0};
        Integer[] c = new Integer[]{};
        Integer[] d = new Integer[]{-150, -100, 1, 2, 7};

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(Arrays.asList(a));
        lists.add(Arrays.asList(b));
        lists.add(Arrays.asList(c));
        lists.add(Arrays.asList(d));

        System.out.println("lists:  " + lists);
        System.out.println("result  : " + merge(lists));
    }
}
