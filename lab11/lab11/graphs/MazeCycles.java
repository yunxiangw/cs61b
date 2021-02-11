package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int[] parent;
    private boolean haveCycle;

    public MazeCycles(Maze m) {
        super(m);
        parent = new int[m.N() * m.N()];
        distTo[0] = 0;
        edgeTo[0] = 0;
    }

    @Override
    public void solve() {
        cycle(0);
    }

    private void cycle(int v) {
        marked[v] = true;
        announce();
        for (int w : maze.adj(v)) {
            if (haveCycle) {
                return;
            }
            if (!marked[w]) {
                parent[w] = v;
                distTo[w] = distTo[v] + 1;
                cycle(w);
            } else if (parent[v] != w) {
                haveCycle = true;
                edgeTo[w] = v;
                drawCycle(v, w);
                return;
            }
        }
    }

    private void drawCycle(int s, int t) {
        if (s == t) {
            announce();
            return;
        } else {
            edgeTo[s] = parent[s];
            drawCycle(edgeTo[s], t);
        }
    }
}

