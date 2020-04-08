public class AVLTree2
{
    TreeNode root;
    public AVLTree2()
    {
        root = null;
    }

    public boolean insertmethod(int num)
    {
        TreeNode n = new TreeNode();
        n.targetKey = num;
        n.bf = 0;
        n.rc = null;
        n.lc = null;
        //TreeNode c = new TreeNode();
        /*findnode(num, p);
        insert(p, num); //recursive*/
        return true;

    }
    public TreeNode insert(TreeNode p, TreeNode n, int num)
    {
        if (p == null)
        {
            /*p = new TreeNode();
            p.targetKey = num;
            p.bf = 0;
            p.rc = null;
            p.lc = null;*/
            return p;
        }
        else
        {
            /*findNode(num,p,c);
            if (num < p.targetKey)
                p.lc = insert(p.lc, num);
            else
                p.rc = insert(p.rc, num);*/
        }
        p = balance(p);
        return p;
    }
    /*public boolean insert (int num)
    {
        TreeNode p = new TreeNode();
        TreeNode c = new TreeNode();
        TreeNode n = new TreeNode();

        n = findNode(num, p, c);
        if (n == null)
            return false;
        n.targetKey = num;
        n.bf = 0;
        n.lc = null;
        n.rc = null;
        if(root == null)
            root = n;
        else
        {
            //n = findNode(num, p, c);
            if (n.targetKey < p.targetKey)
                p.lc = n;
            else
                p.rc = n;
        }
        balance(n);
        return true;*/

        /*if(root == null)
            root = n;
        else
        {
            findNode(num, p, c);
            if(num < p.targetKey)
                p.lc = n;
            else
                p.rc = n;
        }
        p = balance(p);
        return true;*/
    //}

    //calculates left and right height of the root
    //public void rootHeights()
    //{
    //
    //}
    //calculates left and right height of a given node
    public void nodeHeights (int num)
    {
        boolean found;
        TreeNode p = new TreeNode();
        TreeNode c = new TreeNode();;
        if(findNode(num, p, c) != null)
        {
            System.out.println(leftHeight(p));
            System.out.println(rightHeight(p));
        }
    }
    //calculates left and right height of a node
    public int leftHeight(TreeNode p)
    {
        int lheight = 1;
        if (p.lc != null)
            height(p.lc, lheight, 1);
        else lheight = 0;
        return lheight;
    }
    public int rightHeight(TreeNode p)
    {
        int rheight = 1;
        if (p.lc != null)
            height(p.lc, rheight, 1);
        else rheight = 0;
        return rheight;
    }

    /*public TreeNode insert(TreeNode p, int num) {
        if (p == null) {
            p = new TreeNode();
            p.targetKey = num;
            p.bf = 0;
            p.rc = null;
            p.lc = null;
            return p;
        } else {
            if (num < p.targetKey)
                p.lc = insert(p.lc, num);
            else //if (num > p.targetKey)
                p.rc = insert(p.rc, num);
        }
        p = balance(p);
        return p;
    }*/

    //balances tree if balance factor is greater than 1
    public TreeNode balance(TreeNode p) {
        TreeNode n;
        balanceFactor(p);
        if (p.bf == -2) {
            n = p.lc;
            if (n.bf == 1)
                p = zigzagLeft(p);
            else
                p = straightLeft(p);
        } else if (p.bf == 2) {
            n = p.rc;
            if (n.bf == -1)
                p = zigzagRight(p);
            else
                p = straightRight(p);
        }
        return p;
    }

    //rotates nodes if unbalanced if:
    //nodes are straight and to the left
    public TreeNode straightLeft(TreeNode p) {
        TreeNode c;
        c = p.lc;
        p.lc = c.rc;
        c.rc = p;
        balanceFactor(p);
        balanceFactor(c);
        p = c;
        return p;
    }
    //nodes are straight and to the right
    public TreeNode straightRight(TreeNode p) {
        TreeNode c;
        c = p.rc;
        p.rc = c.lc;
        c.lc = p;
        balanceFactor(p);
        balanceFactor(c);
        p = c;
        return p;
    }
    //nodes are zigzagged to the left
    public TreeNode zigzagLeft(TreeNode p) {
        p.lc = straightRight(p.lc);
        p = straightLeft(p);
        return p;
    }
    //nodes are zigzagged to the right
    public TreeNode zigzagRight(TreeNode p) {
        p.rc = straightLeft(p.rc);
        p = straightRight(p);
        return p;
    }

    //calculates balancefactor of each node
    public void balanceFactor(TreeNode p) {
        int lheight = 1;
        int rheight = 1;
        if (p.lc != null)
            height(p.lc, lheight, 1);
        else
            lheight = 0;
        if (p.rc != null)
            height(p.rc, rheight, 1);
    }

    //calculates longest road from current node(height of subtree)
    public void height(TreeNode p, int height, int length)
    {
        if (p != null)
        {
            height(p.rc, height, length + 1);
            if ((p.lc == null) && (p.rc == null) && (height < length))
                height = length;
            height(p.lc, height, length + 1);
        }
    }

    //print all nodes in tree in order
    public void showAll()
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
        System.out.println(root.targetKey);
        if(root.rc != null)
            LNRoutputTraversal(root.rc);
    }

    private TreeNode findNode(int num, TreeNode p, TreeNode c)
    {
        if(c == root)
            return p;
        if(c == null)
        {
            p = balance(p);
            return p;
            //insert(num);
        }
        p=c;
        if (num < c.targetKey)
        {
            c = p.lc;
            findNode(num, p.lc, c);
        }
        else //if(num > c.targetKey)
            {
                c = p.rc;
                findNode(num, p.rc, c);
            }
        p = balance(p);
        return p;
        /*
        p = root;
        c = root;
        while(c != null) //c
        {
            if(c.targetKey == num) //c
                return c;
            else
            {
                p = c;
                if(num < c.targetKey)  //c
                    c = c.lc; //c c
                else
                    c = c.rc; //c c
                //parent.set(child.get());
                //if(targetKey.compareTo(child.get().node.getId()) < 0)
                    //child.set(child.get().lc);
                //else
                    //child.set(child.get().rc);
            }
        }
        return p;*/
    }

        //creates nodes for AVL Tree
        public class TreeNode
        {
            private int targetKey; //num stored in node
            private int bf; //balancing factor
            private TreeNode lc;
            private TreeNode rc;

            public TreeNode() {

            }
        }
}
