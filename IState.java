
import java.util.ArrayList;

/**
 * Interface that describes a state of the problem
 * 
 * @author jt
 * @version 2.1
 */

public interface IState
{
	/*
	 * heuristic function
	 * returns the value of h for this state
	 */
    double h();
    /*
     * returns true if the state is a goal
     */
    boolean goal();
    /*
     * returns a collection of successor states, and the cost of the transition
     */
    ArrayList<NickDime> suc();
    /*
     * returns the initial state
     */
    static IState getInitialState() { return null;};
    /*
     * returns true if the state is equal to p
     */
    boolean equals( Object p);
    /*
     * returns an integer with the value of the hash code of the state
     */
    int hashCode();
}