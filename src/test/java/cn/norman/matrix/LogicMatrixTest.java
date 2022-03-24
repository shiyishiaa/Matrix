package cn.norman.matrix;

import org.junit.Assert;
import org.junit.Test;


public class LogicMatrixTest {
    @Test
    public void testLogicMultiple() {
        boolean[] a = {true, false, false, false};
        boolean[] b = {true, false, true, false};

        Assert.assertTrue(LogicMatrix.logicMultiple(a, b));

        boolean[] c = {false, false, true, false};
        Assert.assertFalse(LogicMatrix.logicMultiple(a, c));
    }
}
