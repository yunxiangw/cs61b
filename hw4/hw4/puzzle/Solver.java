package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;
import java.util.Collections;

public class Solver {

    private class Searchnode implements Comparable<Searchnode> {
        private WorldState state;
        private int distToInitial;
        private int distToGoal;
        private Searchnode prev;

        Searchnode(WorldState state, int distToInitial, Searchnode prev) {
            this.state = state;
            this.distToInitial = distToInitial;
            this.prev = prev;
            this.distToGoal = state.estimatedDistanceToGoal();
        }

        public int compareTo(Searchnode other) {
            if (distToInitial + distToGoal == other.distToInitial + other.distToGoal) {
                return 0;
            } else if (distToInitial + distToGoal < other.distToInitial + other.distToGoal) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    private MinPQ<Searchnode> fringe;
    private int count;
    private ArrayList<WorldState> solution = new ArrayList<>();

    public Solver(WorldState initial) {
        fringe = new MinPQ<>();
        Searchnode initialNode = new Searchnode(initial, 0, null);
        fringe.insert(initialNode);

        while (!fringe.isEmpty()) {
            Searchnode current = fringe.delMin();
            if (current.distToGoal == 0) {
                count = current.distToInitial;
                trackBack(current);
                break;
            }
            for (WorldState s: current.state.neighbors()) {
                if (current.prev == null || !s.equals(current.prev.state)) {
                    Searchnode newNode = new Searchnode(s, current.distToInitial + 1, current);
                    fringe.insert(newNode);
                }
            }
        }
    }

    private void trackBack(Searchnode n) {
        while (n != null) {
            solution.add(n.state);
            n = n.prev;
        }
        Collections.reverse(solution);
    }

    public int moves() {
        return count;
    }

    public Iterable<WorldState> solution() {
        return solution;
    }
}
