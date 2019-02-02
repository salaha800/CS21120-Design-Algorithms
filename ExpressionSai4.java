package cs21120.assignment2018;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.Random;

/**
 * Its my main class using my id (sai4),i have four  variables in the class to store
 * to store left & right child ,operation and value.
 *
 * @author sai4
 * @version 1st
 */
public class ExpressionSai4 implements IExpression, IExpressionFactory {
    IExpression left, right;
    Integer value;
    Operation op;

    /**
     * I am checking  the right and left child here ,carrying out the operations i.e add,subtract,multiply,divide
     * Add operation was easy ,just adding th right and left side and displaying the results
     * Subtracting operation was a bit tedious as i had to  check for types of values and throw  different exceptions in the code
     * To see if i had  negative values ,i subtracted left from right and used ArithmeticException to throw an exception
     * For division i had to check for fraction values and if the result was zero :
     * To check  and throw an exception if we divide right side by left result will be a fraction value & we cant have fraction values in the tree
     * To check  and throw an exception if the right side is equal to zero throw an exception,as diving the right side by zero will give zero
     * To check if there is any other value in the operation
     * For multiplication it was simple multiplying right side by left ,didn't have to throw na exception
     *
     * @return values
     * @throws throw an exception Exception
     */
    public int evaluateCountdown() throws Exception {
        if (right == null) {
            if (left == null) {
                return value;
            }
        }
        //tried checking for negative values
        if (op == Operation.ADD) {
            return left.evaluateCountdown() + right.evaluateCountdown();

        }
        //Checking operation by doing left - right  Substraction//
        int subresult;
        if (op == Operation.SUBTRACT) {
            subresult = left.evaluateCountdown() - right.evaluateCountdown();
            if (subresult >= 0) {
                return subresult;
            } else throw new ArithmeticException("if its a negative value throw the exception");
        }
        /**
         * Checking operation by doing left * right multiply
         */

        if (op == Operation.MULTIPLY) {
            return left.evaluateCountdown() * right.evaluateCountdown();
        }
        /**
         * Checking operation by dividing  left by right to see if its equal to zero or fraction result
         * and to see if there is something else going on in the operation
         * used ArithmeticException to show the message
         */

        if (op == Operation.DIVIDE) {
            if (right.evaluateCountdown() != 0) {


                if (left.evaluateCountdown() % right.evaluateCountdown() == 0) {

                    return left.evaluateCountdown() / right.evaluateCountdown();
                } else
                    throw new ArithmeticException("if we divide right side by left result will be a fraction value & we cant have fraction values in the tree");
            } else
                throw new ArithmeticException("if the right side is equal to zero throw an exception,as diving the right side by zero will give zero");
        }
        throw new ArithmeticException("have something different it should be in the op");//throw an exception
    }

    /**
     * This part for creating my binary tree
     * Assigning  the values of an internal node
     *
     * @param left  the expression to use for the left hand side
     * @param right the expression to use for the right hand side
     * @param op    the operation to use at the node
     */
    public void set(IExpression left, IExpression right, Operation op) {//use this to create  part of binary tree where you have operations
        this.op = op;
        this.left = left;
        this.right = right;
    }

    /**
     * Assigning the value of a leaf node
     * used when we have value in the part binary tree,its the lowest level of binary tree
     * checking if left and right sides are equal to null
     *
     * @param value the value to use at this leaf
     */
    public void set(int value) {//used when we have value in the part binary tree,its the lowest level of binary tree
        this.value = value;
        left = null;
        right = null;

    }

    /**
     * Getting the value of a leaf node
     *
     * @return returns the leaf node value
     * @throws Exception checking if its  not null ,if it is null throwing an exception
     */

    public int getValue() throws Exception {

        if (value == null) {
            throw new ArithmeticException("if the value doesn't exist throw an exception");
        }

        return value;
    }

    /**
     * Checks if this is a leaf node (both children null) or not (both children not null)
     *
     * @return returns true if this is a leaf node
     */
    public boolean isLeaf() {
        if (right == null) {
            if (left == null) {
                return true;
            }
        }

        return false;
    }

    /**
     * Return an iterator for this equation.  An iterator implementation is provided,
     * so you just need to return an instance of it.
     *
     * @return returns an iterator
     */
    public Iterator<IExpression> getIterator() {
        return new ExpressionIterator(this);
    }

    /**
     * Getting the left side of the equation
     *
     * @return the left side node, null if a leaf
     */
    public IExpression getLeft() {
        return left;
    }

    /**
     * Getting the right side of the equation
     *
     * @return return the right side, null if it is a leaf
     */
    public IExpression getRight() {
        return right;
    }

    /**
     * Get the operations
     *
     * @return returns the operation, null if it is a leaf
     */
    public Operation getOperation() {
        return op;
    }


    /**
     * Created a function with randomEquation
     * created an arrylist
     * There doesn't need to be a minimum tree size.  The application makes sure that the result is between 100 and 999 so it is very unlikely to allow a single leaf tree, it would have to be 100, and the chances are very small.
     * <p>
     * Also, the trees generate should be randomly distributed over all possible trees with the given numbers.  It shouldn't always give trees of the same shape, or ordering of values, or size etc.
     *
     * @param vals the values that can be used in the equation
     * @return
     */
    public IExpression createRandomEquation(int[] vals) {//create this function
        ArrayList<IExpression> arrli = new ArrayList<IExpression>(6);
        IExpression name1 = createLeaf(vals[0]);
        arrli.add(name1);

        IExpression name2 = createLeaf(vals[1]);
        arrli.add(name2);

        IExpression name3 = createLeaf(vals[2]);
        arrli.add(name3);

        IExpression name4 = createLeaf(vals[3]);
        arrli.add(name4);

        IExpression name5 = createLeaf(vals[4]);
        arrli.add(name5);

        IExpression name6 = createLeaf(vals[5]);
        arrli.add(name6);
/**
 * Created random base and and created rand.nextInt
 * so adding and removing an element from the list ,I used arrli
 * and created a for loop to check the list elements
 * i used a swicth case to assign the number with operator i.e case o is for add operation
 */
        Random rand = new Random();
        int howmany = rand.nextInt(6);
        IExpression main = new ExpressionSai4();
        int firstelement = rand.nextInt(arrli.size());
        main = arrli.get(firstelement);
        arrli.remove(firstelement);
        for (int i = 0; i < howmany; i++) {
            int howmanyoperators = rand.nextInt(4);
            Operation op = null;
            /**
             * created the switch case to show different operations for each value
             */
            switch (howmanyoperators) {
                case 0:

                    op = Operation.ADD;

                    break;
                case 1:

                    op = Operation.SUBTRACT;

                    break;
                case 2:

                    op = Operation.MULTIPLY;

                    break;
                case 3:

                    op = Operation.DIVIDE;

                    break;
            }
            /**
             * Added single number with the operation on the right side e.g. (2+3) -> (2+3)-5
             * Added single number with  the operation on the left side e.g. (2+3) -> 5-(2+3)
             * Added another equation with the operation on the right side e.g. (2+3) -> (2+3) * (2-1)//its hard tp get these possibilties
             * Added another equation with the operation on the left side e.g. (2+3) -> (2-1) * (2+3)//its hard to get these possibities
             **/

            int item = rand.nextInt(arrli.size());
            IExpression temp = new ExpressionSai4();
            int item1 = rand.nextInt(2);//used a switch case here to check for different cases
            switch (item1) {
                case 0:
                    temp.set(arrli.get(item), main, op);
                    break;
                case 1:
                    temp.set(main, arrli.get(item), op);
                    break;
            }


            main = temp;
            arrli.remove(item);
        }
        return main;

    }

    /**
     * to find the best solution ,i couldt get this part as i am too sure how to go about it
     *
     * @param vals   the values available to use in the equation
     * @param target the target value
     * @return
     */
    public IExpression findBestSolution(int[] vals, int target) {
        return null;
    }

    /**
     * created  a new object and set a value to it to check for child leaf
     *
     * @param val the value to store in the leaf
     * @return
     */
    public IExpression createLeaf(int val) {
        IExpression newleaf = new ExpressionSai4();
        newleaf.set(val);
        return newleaf;
    }

    /**
     * Tostring method  showing all operations
     *
     * @return
     */
    @Override
    public String toString() {
        if (isLeaf()) {
            return String.valueOf(value);
        } else {
            if (op == Operation.ADD) {
                return "(" + left.toString() + "+" + right.toString() + ")";
            }
            if (op == Operation.SUBTRACT) {
                return "(" + left.toString() + "-" + right.toString() + ")";
            }
            if (op == Operation.MULTIPLY) {
                return "(" + left.toString() + "*" + right.toString() + ")";
            }
            if (op == Operation.DIVIDE) {
                return "(" + left.toString() + "/" + right.toString() + ")";
            }
        }
        return "0";
    }

    /**
     * created a note with operation and left and right child
     *
     * @param l  the left side of the equation
     * @param r  the right side of the equation
     * @param op the operation to use
     * @return
     */
    public IExpression createInternalNode(IExpression l, IExpression r, Operation op) {
        IExpression createInternalNode = new ExpressionSai4();
        createInternalNode.set(l, r, op);
        return createInternalNode;
    }
}
/**
 * Evaluation :
 * I spent 2 days lliterlly  reading the assignment  pdf doc and trying to get my head around the problem and writing down my
 * my plan on paper(getting my algorithm ready).
 * I went through my lecture notes on trees ,also revised arrays and other related topics.
 * The hardest part was to figure out how to get started after that it was just writing syntax
 * I had to go through my previous labs on binary trees and tutorial material for writing my syntax for the assignment .
 * I looked up few things on google (https://www.geeksforgeeks.org/) to see a similar problem and see if my work was on the right track.
 * over all i enjoyed doing the assignment and it broadened my understanding of binary trees ,leafs and nodes .
 * one challenge i had was to do the right left operations for substraction and division ,as sometimes we have negative values and
 * fraction results.Getting my head around these was a bit challenges ,i tried gto do it ,not sure how correct my solution is.
 I got the test fully working and displaying values in a box with different operations running ,so that was the good part of doing
 the assignment.
 **/