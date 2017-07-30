package dao;


import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
class test2 extends JFrame
{
    test2(String s)
    {
        super(s);
        Container con=getContentPane();
        DefaultMutableTreeNode root=new DefaultMutableTreeNode("c:\\");
        DefaultMutableTreeNode t1=new DefaultMutableTreeNode("备份资料");
        DefaultMutableTreeNode t2=new DefaultMutableTreeNode("Java学习");
        DefaultMutableTreeNode t1_1=new DefaultMutableTreeNode("思维论坛精华帖子");
        DefaultMutableTreeNode t1_2=new DefaultMutableTreeNode("来往邮件");
        DefaultMutableTreeNode t2_1=new DefaultMutableTreeNode("视频教程");
        DefaultMutableTreeNode t2_2=new DefaultMutableTreeNode("Java 3D");
        JTree tree=new JTree(root);
        root.add(t1);
        root.add(t2);
        t1.add(t1_1);
        t1.add(t1_2);
        t2.add(t2_1);
        t2.add(t2_2);
        JScrollPane scrollpane=new JScrollPane(tree);
        con.add(scrollpane);
        setSize(300,200);
        setVisible(true);
        validate();
        addWindowListener(
            new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        }
        );
    }
}
