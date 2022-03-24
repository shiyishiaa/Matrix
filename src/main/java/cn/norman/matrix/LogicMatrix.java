package cn.norman.matrix;

import java.util.Arrays;

/**
 * 逻辑矩阵
 */
public class LogicMatrix {
    /**
     * 尺寸
     */
    private final int size;
    /**
     * 布尔矩阵
     */
    private final boolean[][] matrix;

    /**
     * 初始值全为{@link Boolean#FALSE}的逻辑矩阵
     *
     * @param size 尺寸
     */
    public LogicMatrix(int size) {
        this.size = size;
        this.matrix = new boolean[size][size];
        for (int x = 0; x < this.matrix.length; x++) {
            for (int y = 0; y < this.matrix.length; y++) {
                this.matrix[x][y] = false;
            }
        }
    }

    /**
     * 用另一个逻辑矩阵来初始化一个逻辑矩阵
     *
     * @param o 另一个逻辑矩阵
     */
    public LogicMatrix(LogicMatrix o) {
        this.size = o.size;
        this.matrix = Arrays.copyOf(o.matrix, o.size);
    }

    /**
     * 两个逻辑向量的逻辑相乘
     *
     * @param a 逻辑向量a
     * @param b 逻辑向量b
     * @return 逻辑相乘结果
     */
    static boolean logicMultiple(boolean[] a, boolean[] b) {
        if (a.length != b.length) throw new IndexOutOfBoundsException("数组长度不相等！");
        boolean rst = false;
        for (int i = 0; i < a.length; i++) {
            rst = rst || (a[i] && b[i]);
        }
        return rst;
    }

    /**
     * 复制逻辑矩阵
     *
     * @return 复制后的逻辑矩阵
     */
    public LogicMatrix copy() {
        return new LogicMatrix(this);
    }

    /**
     * 修改逻辑矩阵某处的值
     *
     * @param row 行号
     * @param col 列号
     * @param val 值
     */
    public void at(int row, int col, boolean val) {
        this.matrix[row - 1][col - 1] = val;
    }

    /**
     * 取得逻辑矩阵某处的值
     *
     * @param row 行号
     * @param col 列号
     * @return 值
     */
    public boolean of(int row, int col) {
        return this.matrix[row - 1][col - 1];
    }

    /**
     * 取得逻辑矩阵某行
     *
     * @param row 行号
     * @return 逻辑向量
     */
    public boolean[] rowAt(int row) {
        return this.matrix[row - 1];
    }

    /**
     * 取得逻辑矩阵某列
     *
     * @param col 列号
     * @return 逻辑向量
     */
    public boolean[] colAt(int col) {
        boolean[] column = new boolean[this.size];
        for (int i = 0; i < this.size; i++) {
            column[i] = this.matrix[i][col - 1];
        }
        return column;
    }

    /**
     * 逻辑矩阵的逻辑相乘
     *
     * @param right 右矩阵
     * @return 逻辑相乘结果
     */
    public LogicMatrix logicMultiple(LogicMatrix right) {
        LogicMatrix ans = new LogicMatrix(this.size);
        for (int row = 1; row <= size; row++) {
            for (int col = 1; col <= size; col++) {
                ans.matrix[row - 1][col - 1] = logicMultiple(this.rowAt(row), right.colAt(col));
            }
        }
        return ans;
    }

    /**
     * 逻辑矩阵的幂
     *
     * @param times 指数
     * @return 幂结果
     */
    public LogicMatrix power(int times) {
        if (times == 0) {
            LogicMatrix logicMatrix = new LogicMatrix(this.size);
            for (int i = 0; i < size; i++) {
                logicMatrix.at(i + 1, i + 1, true);
            }
            return logicMatrix;
        }

        LogicMatrix ans = this.copy();
        for (int i = 1; i < times; i++) {
            ans = ans.logicMultiple(this);
        }
        return ans;
    }

    /**
     * 逻辑矩阵的平方
     *
     * @return 平方结果
     */
    public LogicMatrix square() {
        return power(2);
    }

    /**
     * 逻辑矩阵的立方
     *
     * @return 立方结果
     */
    public LogicMatrix cubic() {
        return power(3);
    }

    /**
     * 逻辑矩阵的逻辑相加
     *
     * @param another 另一个逻辑矩阵
     * @return 逻辑相加结果
     */
    public LogicMatrix logicPlus(LogicMatrix another) {
        LogicMatrix ans = new LogicMatrix(this.size);
        for (int row = 1; row <= size; row++) {
            for (int col = 1; col <= size; col++) {
                ans.at(row, col, this.of(row, col) || another.of(row, col));
            }
        }
        return ans;
    }
}
