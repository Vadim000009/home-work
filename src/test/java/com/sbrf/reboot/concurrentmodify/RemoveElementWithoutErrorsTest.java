package com.sbrf.reboot.concurrentmodify;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemoveElementWithoutErrorsTest {

    @Test
    public void successConcurrentModificationException() {

        List<Integer> list = new ArrayList() {{
            add(1);
            add(2);
            add(3);
        }};

        assertThrows(ConcurrentModificationException.class, () -> {
            
            for (Integer integer : list) {
                list.remove(1);
            }
            
        });

    }

    /*  Удаление через removeIf */
    @Test
    public void successRemoveElementWithRemoveIf() {
        List<Integer> fstList = new ArrayList() {{add(1); add(2); add(3);}};
        List<Integer> secList = new ArrayList() {{add(2); add(3);}};
        fstList.removeIf(i -> i == 1);

        assertEquals(fstList, secList);
    }

    /*  Удаление из ArrayList*/
    @Test
    public void successRemoveElementFromArrayList() {
        List<Integer> fstList = new ArrayList() {{add(1); add(2); add(3);}};
        List<Integer> secList = new ArrayList() {{add(2); add(3);}};
        List<Integer> listForDelete = new ArrayList<>();

        fstList.forEach((x) -> {
            if (x == 1) listForDelete.add(x);
        });
        fstList.removeAll(listForDelete);

        assertEquals(fstList, secList);
    }

    /*  Удаление через stream   */
    @Test
    public void successRemoveElementFromStream() {
        List<Integer> fstList = new ArrayList() {{add(1); add(2); add(3);}};
        List<Integer> secList = new ArrayList() {{add(2); add(3);}};

        fstList = fstList.stream().filter((x)-> x != 1).collect(Collectors.toList());

        assertEquals(fstList, secList);
    }

}
