public class AVLTree
{
    TreeNode root;
    public AVLTree()
    {
        root = null;
    }

    public boolean insert (String targetKey)
    {
        TreeNodeWrapper p = new TreeNodeWrapper();
        TreeNodeWrapper c = new TreeNodeWrapper();
        TreeNode n = new TreeNode();

        n.node = targetKey;
        n.bf = 0;
        n.lc = null;
        n.rc = null;

        if(root == null)
        {
            root = n;
            n = root;
        }
        else
        {
            while(n != null)
            {
                findNode(n, p, c);
                if (n.node.compareTo(p.get().node) == 0)
                    n = null; //breaks out of while
                else
                    {
                        if (n.node.compareTo(p.get().node) < 0)
                            p.get().lc = n;
                        else
                            p.get().rc = n;
                        n = balance(p.get());
                    }
            }
        }
        return true;
    }

    //balances the tree
    TreeNode balance(TreeNode p)
    {
        TreeNode c;
        balanceFactor(p);
        if(p.bf == -2)
        {
            c = p.lc;
            if(c.bf == 1)
                p = zigzagLeft(p);
            else
                p = straightLeft(p);
        }
        else
            if(p.bf == 2)
            {
                c = p.rc;
                if(c.bf == -1)
                    p = zigzagRight(p);
                else
                    p = straightRight(p);
            }
            return p;
    }

    //calculates the balance factor of each node
    public void balanceFactor(TreeNode p)
    {
        int lheight = 0;
        int rheight = 0;
        if (p.lc != null)
            lheight = height(p.lc);
        if (p.rc != null)
            rheight = height(p.rc);
        p.bf = rheight - lheight;
    }

    //calculates height of nodes
    public void nodeHeight(String targetKey)
    {
        TreeNodeWrapper p = new TreeNodeWrapper();
        TreeNodeWrapper c = new TreeNodeWrapper();
        TreeNode n = new TreeNode();
        n.node = targetKey;

        int lheight = 0;
        int rheight = 0;
        while(n != null)
        {
            findNode(n, p, c);
            n = c.get();
            //System.out.println("n: " + n.node + " p: " + p.get().node);
            if (n.node.compareTo(p.get().node) == 0)
            {
                lheight = height(p.get().lc);
                rheight = height(p.get().rc);
                System.out.println("\t" + lheight + "\t\t-\t" + n.node + "\t-\t\t" + rheight);
                n = null; //breaks out of while
            }
            else
                {
                    //System.out.println("n: " + n.node + " parent's lc: " + p.get().lc.node + " parent's rc: " + p.get().rc.node);
                    if (n == p.get().lc)
                    {
                        //System.out.println("node's lc: " + p.get().lc.lc.node + " node's rc: " + p.get().lc.rc.node);
                        lheight = height(p.get().lc.lc);
                        rheight = height(p.get().lc.rc);
                    }
                    if (n == p.get().rc)
                    {
                        //System.out.println("node's lc: " + p.get().rc.lc.node + " node's rc: " + p.get().rc.rc.node);
                        lheight = height(p.get().rc.lc);
                        rheight = height(p.get().rc.rc);
                    }
                    System.out.println("\t" + lheight + "\t\t-\t" + n.node + "\t-\t\t" + rheight);
                    n = p.get();
                }
        }
    }

    //calculates the height of the subtree of the current node
    public int height(TreeNode n)
    {
        if (n == null)
            return 0;
        else
            return (max(height(n.lc), height(n.rc)) + 1);
    }
    public int max(int lheight, int rheight)
    {
        if (lheight > rheight)
            return lheight;
        else
            return rheight;
    }

    //rotates nodes if unbalanced if:
    //nodes are straight and to the left
    public TreeNode straightLeft(TreeNode p)
    {
        TreeNodeWrapper q = new TreeNodeWrapper();
        TreeNodeWrapper r = new TreeNodeWrapper();
        TreeNode c;

        c = p.lc;
        p.lc = c.rc;
        c.rc = p;
        balanceFactor(p);
        balanceFactor(c);
        if(p == root)
        {
            root = c;
            p = c;
        }
        else
            {
                p = c;
                findNode(p.rc, q, r);
                if(p.rc == q.get().rc)
                    q.get().rc = p;
                else
                    q.get().lc = p;
            }
        return p;
    }
    //nodes are straight and to the right
    public TreeNode straightRight(TreeNode p)
    {
        TreeNodeWrapper q = new TreeNodeWrapper();
        TreeNodeWrapper r = new TreeNodeWrapper();
        TreeNode c;

        c = p.rc;
        p.rc = c.lc;
        c.lc = p;
        balanceFactor(p);
        balanceFactor(c);
        if(p == root)
        {
            root = c;
            p = c;
        }
        else
            {
                p = c;
                findNode(p.lc, q, r);
                if(p.lc == q.get().lc)
                    q.get().lc = p;
                else
                    q.get().rc = p;
            }
        return p;
    }
    //nodes are zigzagged to the left
    public TreeNode zigzagLeft(TreeNode p)
    {
        p.lc = straightRight(p.lc);
        p = straightLeft(p);
        return p;
    }
    //nodes are zigzagged to the right
    public TreeNode zigzagRight(TreeNode p)
    {
        p.rc = straightLeft(p.rc);
        p = straightRight(p);
        return p;
    }

    /*public void showAll()
    {
        if(root == null)
            System.out.println("the structure is empty");
        else
        {
            LNRoutputTraversal(root);
            System.out.println("");
        }
    }
    public void LNRoutputTraversal(TreeNode root)
    {
        if(root.lc != null)
            LNRoutputTraversal(root.lc);
        System.out.println(root.node);
        if(root.rc != null)
            LNRoutputTraversal(root.rc);
    }*/

    //creates nodes for AVL Tree
    public class TreeNode
    {
        private String node;
        private int bf; //balancing factor
        private TreeNode lc;
        private TreeNode rc;
        public TreeNode()
        {

        }
    }

    //finds current node and returns parent and child
    private boolean findNode(TreeNode n, TreeNodeWrapper parent, TreeNodeWrapper child)
    {
        parent.set(root);
        child.set(root);
        if(root == null)
            return true;
        while(child.get() != null)
        {
            if(child.get().node.compareTo(n.node) == 0)
                return true;
            else
            {
                parent.set(child.get());
                if(n.node.compareTo(child.get().node) < 0)
                    child.set(child.get().lc);
                else
                    child.set(child.get().rc);
            }
        }
        return false;
    }

    //wrapper class for parent and child in findNode method
    public class TreeNodeWrapper
    {
        TreeNode treeRef = null;
        public TreeNodeWrapper()
        {

        }

        public TreeNode get()
        {
            return treeRef;
        }

        public void set(TreeNode t)
        {
            treeRef = t;
        }
    }
}
