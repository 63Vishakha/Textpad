import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Notepad extends JFrame implements ActionListener , WindowListener{
    
    JTextArea textarea = new JTextArea();
    File fnameContainer;

    public Notepad(){
        Font fnt = new Font("Aerial",Font.PLAIN,15);
        Container con = getContentPane();
        JMenuBar jmb = new JMenuBar();
        JMenu jmfile = new JMenu("File");
        JMenu jmedit = new JMenu("Edit");
        JMenu jmhelp = new JMenu("Help");

        con.setLayout(new BorderLayout());
        JScrollPane sbrtext = new JScrollPane(textarea);
        sbrtext.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sbrtext.setVisible(true);

        textarea.setFont(fnt);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);

        con.add(sbrtext);

        createMenuItem(jmfile,"New");
        createMenuItem(jmfile,"Open");
        createMenuItem(jmfile,"Save");
        jmfile.addSeparator();
        createMenuItem(jmfile,"Exit");

        createMenuItem(jmedit,"Cut");
        createMenuItem(jmedit,"Copy");
        createMenuItem(jmedit,"Paste"); 

        createMenuItem(jmhelp,"About");

        jmb.add(jmfile);
        jmb.add(jmedit);
        jmb.add(jmhelp);

        setJMenuBar(jmb);

        setIconImage(Toolkit.getDefaultToolkit().getImage("notepad.gif"));
        addWindowListener(this);
        setSize(500,500);
        setTitle("Untitled.txt - Notepad");
        setVisible(true);
    }   
    public void createMenuItem(JMenu jm, String text){
            JMenuItem jmi = new JMenuItem(text);
            jmi.addActionListener(this);
            jm.add(jmi);
    }
    public void actionPerformed(ActionEvent e){
        JFileChooser jfc = new JFileChooser();
        if(e.getActionCommand().equals("New")){
         this.setTitle("Untitled.txt - Notepad");   
         textarea.setText("");
         fnameContainer = null;
        }else if(e.getActionCommand().equals("Open")){
            int ret = jfc.showDialog(null,"Open");
            if(ret == JFileChooser.APPROVE_OPTION){
                try{
                    File fyl = jfc.getSelectedFile();
                    OpenFile(fyl.getAbsolutePath());
                    this.setTitle(fyl.getName()+" - Notepad");
                    fnameContainer = fyl;
                }catch(IOException ers){}
            }
        }else if(e.getActionCommand().equals("Save")){
            if(fnameContainer!=null){
                jfc.setCurrentDirectory(fnameContainer);
                jfc.setSelectedFile(fnameContainer);
            }else{
                jfc.setSelectedFile(new File("Untitled.txt"));
            }

            int ret = jfc.showDialog(null,"");
            if(ret == JFileChooser.APPROVE_OPTION){
               try{
                   File fyl =jfc.getSelectedFile();
                   SaveFile(fyl.getAbsolutePath());
                   this.setTitle(fyl.getName()+" - Notepad");
                   fnameContainer = fyl;
               }
               catch(Exception ers){

               } 
            }
        }else if(e.getActionCommand().equals("Exit")){
            Exiting();
        }else if(e.getActionCommand().equals("Copy")){
            textarea.copy();
        }else if(e.getActionCommand().equals("Paste")){
            textarea.paste();
        }else if(e.getActionCommand().equals("About")){
            JOptionPane.showMessageDialog(this,"Created using Java swing methods for text related operations","Notepad",JOptionPane.INFORMATION_MESSAGE);
        }else if(e.getActionCommand().equals("Cut")){
            textarea.cut();
        }
    }
       
    public void OpenFile(String fname) throws IOException {
        BufferedReader d = new BufferedReader(new InputStreamReader(new FileInputStream(fname)));
        String l;
        textarea.setText("");
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        while((l = d.readLine())!=null){
            textarea.setText(textarea.getText()+ "\r\n");
        }
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        d.close();
    }
    public void SaveFile(String fname) throws IOException {
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        DataOutputStream o = new DataOutputStream(new FileOutputStream(fname));
        o.writeBytes(textarea.getText());
        o.close();
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
   public void windowDeactivated(WindowEvent e){}

   public void windowActivated(WindowEvent e){}

   public void windowDiconified(WindowEvent e){}
    
    public void Exiting(){
        System.exit(0);
    }
    public void windowOpened(WindowEvent e){}


public static void main(String[] args) {
    Notepad notepad = new Notepad();
}
@Override
public void windowClosing(WindowEvent e) {
    
    
}
@Override
public void windowClosed(WindowEvent e) {
    
    
}
@Override
public void windowIconified(WindowEvent e) {
    
    
}
@Override
public void windowDeiconified(WindowEvent e) {
    
    
}}